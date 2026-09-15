package com.example.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.content.CurriculumData
import com.example.data.content.FlashcardBank
import com.example.data.content.FormulaBank
import com.example.data.content.QuestionBank
import com.example.data.local.BookmarkEntity
import com.example.data.local.ConceptMasteryEntity
import com.example.data.local.FlashcardReviewEntity
import com.example.data.local.QuestionAttemptEntity
import com.example.data.local.SectionProgressEntity
import com.example.data.local.StudyPlanEntity
import com.example.data.local.UserProgressEntity
import com.example.data.model.Chapter
import com.example.data.model.Concept
import com.example.data.model.Flashcard
import com.example.data.model.FormulaItem
import com.example.data.model.Question
import com.example.data.model.QuestionType
import com.example.data.model.Section
import com.example.data.repository.FarrsPhysicsRepository
import com.example.ui.navigation.MainTab
import com.example.ui.navigation.PaceStage
import com.example.ui.navigation.Screen
import com.example.ui.navigation.SectionStage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.roundToInt

data class SearchResultItem(
    val id: String,
    val title: String,
    val subtitle: String,
    val type: String, // "SECTION", "CONCEPT", "QUESTION", "FORMULA", "FLASHCARD"
    val chapterId: Int,
    val targetId: String,
    val pageRef: String
)

data class ScheduleInfo(
    val totalPages: Int = 216,
    val completedPages: Int = 0,
    val remainingPages: Int = 216,
    val targetPagesPerDay: Double = 5.0,
    val currentPaceNeeded: Double = 5.0,
    val totalStudyDays: Int = 60,
    val remainingStudyDays: Int = 60,
    val daysAheadOrBehind: Int = 0, // >0 ahead, <0 behind, 0 on schedule
    val scheduleStatusText: String = "You are on schedule",
    val estimatedCompletionDateFormatted: String = "October 15, 2026",
    val targetSectionsPerDay: Double = 0.8,
    val completedSectionsCount: Int = 0,
    val totalSectionsCount: Int = 34,
    val completedChaptersCount: Int = 0,
    val totalChaptersCount: Int = 10,
    val overallProgressPercent: Int = 0,
    val totalQuestionsCount: Int = 0,
    val completedQuestionsCount: Int = 0,
    val questionAccuracyPercent: Int = 0,
    val totalAnkiCount: Int = 0,
    val completedAnkiCount: Int = 0
)

class MainViewModel(
    private val repository: FarrsPhysicsRepository
) : ViewModel() {

    // Navigation State
    private val _currentTab = MutableStateFlow(MainTab.HOME)
    val currentTab: StateFlow<MainTab> = _currentTab.asStateFlow()

    private val _currentScreen = MutableStateFlow<Screen>(Screen.Home)
    val currentScreen: StateFlow<Screen> = _currentScreen.asStateFlow()

    // Active Study Location
    private val _selectedChapterId = MutableStateFlow(1)
    val selectedChapterId: StateFlow<Int> = _selectedChapterId.asStateFlow()

    private val _selectedSectionId = MutableStateFlow("1.1")
    val selectedSectionId: StateFlow<String> = _selectedSectionId.asStateFlow()

    private val _selectedSectionStage = MutableStateFlow(SectionStage.READ)
    val selectedSectionStage: StateFlow<SectionStage> = _selectedSectionStage.asStateFlow()

    private val _selectedConceptId = MutableStateFlow("1.1.1")
    val selectedConceptId: StateFlow<String> = _selectedConceptId.asStateFlow()

    private val _selectedPaceStage = MutableStateFlow(PaceStage.PRIME)
    val selectedPaceStage: StateFlow<PaceStage> = _selectedPaceStage.asStateFlow()

    // Room Database State Flows
    val userProgress: StateFlow<UserProgressEntity?> = repository.userProgress
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    val studyPlan: StateFlow<StudyPlanEntity?> = repository.studyPlan
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    val sectionProgressMap: StateFlow<Map<String, SectionProgressEntity>> = repository.sectionProgressList
        .map { list -> list.associateBy { it.sectionId } }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyMap())

    val conceptMasteries: StateFlow<Map<String, ConceptMasteryEntity>> = repository.conceptMasteries
        .map { list -> list.associateBy { it.conceptId } }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyMap())

    val questionAttempts: StateFlow<List<QuestionAttemptEntity>> = repository.questionAttempts
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val flashcardReviews: StateFlow<List<FlashcardReviewEntity>> = repository.flashcardReviews
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val bookmarks: StateFlow<List<BookmarkEntity>> = repository.bookmarks
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Setup Flow State
    private val _isSetupOpen = MutableStateFlow(false)
    val isSetupOpen: StateFlow<Boolean> = _isSetupOpen.asStateFlow()

    // In-Section Question State
    private val _sectionQIndex = MutableStateFlow(0)
    val sectionQIndex: StateFlow<Int> = _sectionQIndex.asStateFlow()

    private val _sectionQSbaSelected = MutableStateFlow<Int?>(null)
    val sectionQSbaSelected: StateFlow<Int?> = _sectionQSbaSelected.asStateFlow()

    private val _sectionQTfAnswers = MutableStateFlow<Map<Int, Boolean>>(emptyMap())
    val sectionQTfAnswers: StateFlow<Map<Int, Boolean>> = _sectionQTfAnswers.asStateFlow()

    private val _sectionQSubmitted = MutableStateFlow(false)
    val sectionQSubmitted: StateFlow<Boolean> = _sectionQSubmitted.asStateFlow()

    // In-Section Flashcard State
    private val _sectionCardIndex = MutableStateFlow(0)
    val sectionCardIndex: StateFlow<Int> = _sectionCardIndex.asStateFlow()

    private val _isSectionCardFlipped = MutableStateFlow(false)
    val isSectionCardFlipped: StateFlow<Boolean> = _isSectionCardFlipped.asStateFlow()

    // Legacy Concept Study Props for Backward Compatibility
    private val _conceptQOptionSelected = MutableStateFlow<Int?>(null)
    val conceptQOptionSelected: StateFlow<Int?> = _conceptQOptionSelected.asStateFlow()

    private val _conceptQTfAnswers = MutableStateFlow<Map<Int, Boolean>>(emptyMap())
    val conceptQTfAnswers: StateFlow<Map<Int, Boolean>> = _conceptQTfAnswers.asStateFlow()

    private val _conceptQSubmitted = MutableStateFlow(false)
    val conceptQSubmitted: StateFlow<Boolean> = _conceptQSubmitted.asStateFlow()

    private val _conceptCardIndex = MutableStateFlow(0)
    val conceptCardIndex: StateFlow<Int> = _conceptCardIndex.asStateFlow()

    private val _isConceptCardFlipped = MutableStateFlow(false)
    val isConceptCardFlipped: StateFlow<Boolean> = _isConceptCardFlipped.asStateFlow()

    // Dedicated Q-Bank Screen State
    private val _qBankChapterFilter = MutableStateFlow<Int?>(null)
    val qBankChapterFilter: StateFlow<Int?> = _qBankChapterFilter.asStateFlow()

    private val _activeQuestionIndex = MutableStateFlow(0)
    val activeQuestionIndex: StateFlow<Int> = _activeQuestionIndex.asStateFlow()

    private val _qBankSbaSelected = MutableStateFlow<Int?>(null)
    val qBankSbaSelected: StateFlow<Int?> = _qBankSbaSelected.asStateFlow()

    private val _qBankTfAnswers = MutableStateFlow<Map<Int, Boolean>>(emptyMap())
    val qBankTfAnswers: StateFlow<Map<Int, Boolean>> = _qBankTfAnswers.asStateFlow()

    private val _qBankSubmitted = MutableStateFlow(false)
    val qBankSubmitted: StateFlow<Boolean> = _qBankSubmitted.asStateFlow()

    // Dedicated Flashcard Deck State
    private val _flashcardChapterFilter = MutableStateFlow<Int?>(null)
    val flashcardChapterFilter: StateFlow<Int?> = _flashcardChapterFilter.asStateFlow()

    private val _flashcardIndex = MutableStateFlow(0)
    val flashcardIndex: StateFlow<Int> = _flashcardIndex.asStateFlow()

    private val _isFlashcardFlipped = MutableStateFlow(false)
    val isFlashcardFlipped: StateFlow<Boolean> = _isFlashcardFlipped.asStateFlow()

    // Formula Calculator State
    private val _selectedFormulaId = MutableStateFlow("F1_INVERSE_SQUARE")
    val selectedFormulaId: StateFlow<String> = _selectedFormulaId.asStateFlow()

    private val _formulaInputs = MutableStateFlow<Map<String, Double>>(
        FormulaBank.formulas.first().defaultInputs
    )
    val formulaInputs: StateFlow<Map<String, Double>> = _formulaInputs.asStateFlow()

    // Search & Bookmarks Modals
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _searchResults = MutableStateFlow<List<SearchResultItem>>(emptyList())
    val searchResults: StateFlow<List<SearchResultItem>> = _searchResults.asStateFlow()

    private val _isSearchOpen = MutableStateFlow(false)
    val isSearchOpen: StateFlow<Boolean> = _isSearchOpen.asStateFlow()

    private val _isBookmarksOpen = MutableStateFlow(false)
    val isBookmarksOpen: StateFlow<Boolean> = _isBookmarksOpen.asStateFlow()

    // Smart Schedule & Metrics Flow
    val scheduleInfo: StateFlow<ScheduleInfo> = combine(
        studyPlan,
        sectionProgressMap,
        questionAttempts,
        flashcardReviews
    ) { plan, secMap, qAttempts, fcReviews ->
        calculateSchedule(plan, secMap, qAttempts, fcReviews)
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        ScheduleInfo()
    )

    // Today's Target Section
    val todayTargetSection: StateFlow<Section?> = combine(
        sectionProgressMap,
        studyPlan
    ) { secMap, plan ->
        val requireAll = plan?.requireAllForCompletion ?: true
        val allSections = CurriculumData.allSections
        // Find first section not completed yet
        allSections.firstOrNull { sec ->
            val progress = secMap[sec.id]
            if (progress == null) {
                true
            } else if (requireAll) {
                !progress.isReadCompleted || !progress.isQuestionsCompleted || !progress.isEnforceCompleted
            } else {
                !progress.isReadCompleted
            }
        } ?: allSections.lastOrNull()
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), CurriculumData.allSections.firstOrNull())

    // Last Stopped Section
    val lastStoppedSection: StateFlow<Section?> = combine(
        userProgress,
        sectionProgressMap
    ) { progress, secMap ->
        val targetId = progress?.currentSectionId ?: "1.1"
        CurriculumData.getSectionById(targetId) ?: CurriculumData.allSections.firstOrNull()
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), CurriculumData.allSections.firstOrNull())

    init {
        // Initialize default study plan if not already present
        viewModelScope.launch {
            val existing = repository.getStudyPlanSync()
            if (existing == null) {
                repository.saveStudyPlan(
                    StudyPlanEntity(
                        id = 1,
                        isSetupCompleted = false,
                        targetTimeframeOption = "2_MONTHS",
                        targetDays = 60,
                        targetDateTimestamp = System.currentTimeMillis() + (60L * 24 * 60 * 60 * 1000L),
                        studyDaysPerWeek = 6,
                        dailyMinutes = 45,
                        requireAllForCompletion = true,
                        startDateTimestamp = System.currentTimeMillis()
                    )
                )
            }
        }
    }

    private fun calculateSchedule(
        plan: StudyPlanEntity?,
        secMap: Map<String, SectionProgressEntity>,
        qAttempts: List<QuestionAttemptEntity>,
        fcReviews: List<FlashcardReviewEntity>
    ): ScheduleInfo {
        val totalPages = CurriculumData.TOTAL_BOOK_PAGES
        val allSections = CurriculumData.allSections
        val requireAll = plan?.requireAllForCompletion ?: true

        // Completed reading pages
        var completedPages = 0
        var completedSections = 0
        allSections.forEach { sec ->
            val p = secMap[sec.id]
            val isSecComplete = if (requireAll) {
                p != null && p.isReadCompleted && p.isQuestionsCompleted && p.isEnforceCompleted
            } else {
                p != null && p.isReadCompleted
            }
            if (p != null && p.isReadCompleted) {
                completedPages += CurriculumData.getSectionPageCount(sec)
            }
            if (isSecComplete) {
                completedSections++
            }
        }
        completedPages = completedPages.coerceIn(0, totalPages)

        // Completed Chapters
        var completedChapters = 0
        CurriculumData.chapters.forEach { ch ->
            val chSections = ch.sections
            val allChSecsComplete = chSections.isNotEmpty() && chSections.all { sec ->
                val p = secMap[sec.id]
                if (requireAll) {
                    p != null && p.isReadCompleted && p.isQuestionsCompleted && p.isEnforceCompleted
                } else {
                    p != null && p.isReadCompleted
                }
            }
            if (allChSecsComplete) completedChapters++
        }

        val totalStudyDays = plan?.targetDays ?: 60
        val startDate = plan?.startDateTimestamp ?: System.currentTimeMillis()
        val elapsedDays = maxOf(1, ((System.currentTimeMillis() - startDate) / (24 * 60 * 60 * 1000L)).toInt())
        val remainingStudyDays = maxOf(1, totalStudyDays - elapsedDays)
        val remainingPages = maxOf(0, totalPages - completedPages)

        val targetPagesPerDay = ((totalPages.toDouble() / totalStudyDays) * 10.0).roundToInt() / 10.0
        val targetSectionsPerDay = ((allSections.size.toDouble() / totalStudyDays) * 10.0).roundToInt() / 10.0

        val expectedPagesByToday = targetPagesPerDay * elapsedDays
        val diffPages = completedPages - expectedPagesByToday
        val daysAheadOrBehind = (diffPages / maxOf(1.0, targetPagesPerDay)).roundToInt()

        val scheduleStatusText = when {
            daysAheadOrBehind > 0 -> "🚀 You are $daysAheadOrBehind day(s) ahead"
            daysAheadOrBehind < 0 -> "⚠️ You are ${-daysAheadOrBehind} day(s) behind"
            else -> "✅ You are on schedule"
        }

        val currentPaceNeeded = ((remainingPages.toDouble() / remainingStudyDays) * 10.0).roundToInt() / 10.0

        // Estimated completion date formatting
        val estimatedFinishTimestamp = if (completedPages >= totalPages) {
            System.currentTimeMillis()
        } else {
            val daysNeeded = if (currentPaceNeeded > 0) (remainingPages / currentPaceNeeded).roundToInt() else remainingStudyDays
            System.currentTimeMillis() + (daysNeeded.toLong() * 24 * 60 * 60 * 1000L)
        }
        val dateFormat = SimpleDateFormat("MMMM d, yyyy", Locale.getDefault())
        val estimatedDateStr = dateFormat.format(Date(estimatedFinishTimestamp))

        val overallProgressPercent = ((completedPages.toDouble() / totalPages) * 100).roundToInt().coerceIn(0, 100)

        val totalQuestions = QuestionBank.allQuestions.size
        val completedQuestions = qAttempts.map { it.questionId }.distinct().size
        val correctQuestions = qAttempts.filter { it.isCorrect }.map { it.questionId }.distinct().size
        val accuracy = if (completedQuestions > 0) ((correctQuestions.toDouble() / completedQuestions) * 100).roundToInt() else 0

        val totalAnki = FlashcardBank.allFlashcards.size
        val completedAnki = fcReviews.map { it.cardId }.distinct().size

        return ScheduleInfo(
            totalPages = totalPages,
            completedPages = completedPages,
            remainingPages = remainingPages,
            targetPagesPerDay = targetPagesPerDay,
            currentPaceNeeded = currentPaceNeeded,
            totalStudyDays = totalStudyDays,
            remainingStudyDays = remainingStudyDays,
            daysAheadOrBehind = daysAheadOrBehind,
            scheduleStatusText = scheduleStatusText,
            estimatedCompletionDateFormatted = estimatedDateStr,
            targetSectionsPerDay = targetSectionsPerDay,
            completedSectionsCount = completedSections,
            totalSectionsCount = allSections.size,
            completedChaptersCount = completedChapters,
            totalChaptersCount = CurriculumData.chapters.size,
            overallProgressPercent = overallProgressPercent,
            totalQuestionsCount = totalQuestions,
            completedQuestionsCount = completedQuestions,
            questionAccuracyPercent = accuracy,
            totalAnkiCount = totalAnki,
            completedAnkiCount = completedAnki
        )
    }

    // Navigation Actions
    fun navigateToTab(tab: MainTab) {
        _currentTab.value = tab
        when (tab) {
            MainTab.HOME -> _currentScreen.value = Screen.Home
            MainTab.BOOK -> _currentScreen.value = Screen.Book
            MainTab.TODAY -> _currentScreen.value = Screen.Today
            MainTab.PROGRESS -> _currentScreen.value = Screen.Progress
            MainTab.SETTINGS -> _currentScreen.value = Screen.Settings
        }
    }

    fun openChapter(chapterId: Int) {
        _selectedChapterId.value = chapterId
        _currentScreen.value = Screen.ChapterDetail(chapterId)
    }

    fun openSection(chapterId: Int, sectionId: String, stage: SectionStage = SectionStage.READ) {
        _selectedChapterId.value = chapterId
        _selectedSectionId.value = sectionId
        _selectedSectionStage.value = stage
        _sectionQIndex.value = 0
        _sectionQSbaSelected.value = null
        _sectionQTfAnswers.value = emptyMap()
        _sectionQSubmitted.value = false
        _sectionCardIndex.value = 0
        _isSectionCardFlipped.value = false
        _currentScreen.value = Screen.SectionStudy(chapterId, sectionId, stage)

        viewModelScope.launch {
            val section = CurriculumData.getSectionById(sectionId)
            val concept = section?.concepts?.firstOrNull()
            repository.updateCurrentLocation(
                chapterId = chapterId,
                sectionId = sectionId,
                conceptId = concept?.id ?: "${sectionId}.1",
                stage = stage.name
            )
        }
    }

    fun setSectionStage(stage: SectionStage) {
        _selectedSectionStage.value = stage
        val chId = _selectedChapterId.value
        val sId = _selectedSectionId.value
        _currentScreen.value = Screen.SectionStudy(chId, sId, stage)
    }

    fun startTodayStudy() {
        val target = todayTargetSection.value ?: CurriculumData.allSections.first()
        openSection(target.chapterId, target.id, SectionStage.READ)
    }

    fun continueWhereStopped() {
        val stopped = lastStoppedSection.value ?: CurriculumData.allSections.first()
        openSection(stopped.chapterId, stopped.id, SectionStage.READ)
    }

    fun openSetup() {
        _isSetupOpen.value = true
    }

    fun closeSetup() {
        _isSetupOpen.value = false
    }

    fun saveStudyPlan(
        targetOption: String,
        targetDays: Int,
        daysPerWeek: Int,
        dailyMinutes: Int,
        requireAll: Boolean
    ) {
        viewModelScope.launch {
            val targetTimestamp = System.currentTimeMillis() + (targetDays.toLong() * 24 * 60 * 60 * 1000L)
            val updated = StudyPlanEntity(
                id = 1,
                isSetupCompleted = true,
                targetTimeframeOption = targetOption,
                targetDays = targetDays,
                targetDateTimestamp = targetTimestamp,
                studyDaysPerWeek = daysPerWeek,
                dailyMinutes = dailyMinutes,
                requireAllForCompletion = requireAll,
                startDateTimestamp = System.currentTimeMillis()
            )
            repository.saveStudyPlan(updated)
            _isSetupOpen.value = false
        }
    }

    fun handleMissedDays(keepOriginalDate: Boolean) {
        viewModelScope.launch {
            val currentPlan = repository.getStudyPlanSync() ?: return@launch
            val schedule = scheduleInfo.value
            if (keepOriginalDate) {
                // Keep original target date, acknowledging rebalance
                repository.saveStudyPlan(
                    currentPlan.copy(missedDaysCount = currentPlan.missedDaysCount + 1)
                )
            } else {
                // Extend completion date by the days behind
                val daysToExtend = if (schedule.daysAheadOrBehind < 0) -schedule.daysAheadOrBehind else 7
                val newTargetTimestamp = currentPlan.targetDateTimestamp + (daysToExtend.toLong() * 24 * 60 * 60 * 1000L)
                repository.saveStudyPlan(
                    currentPlan.copy(
                        targetDays = currentPlan.targetDays + daysToExtend,
                        targetDateTimestamp = newTargetTimestamp,
                        missedDaysCount = 0
                    )
                )
            }
        }
    }

    // Section Study Actions
    fun toggleSectionRead(sectionId: String) {
        viewModelScope.launch {
            val current = repository.getSectionProgressSync(sectionId)
            val newStatus = !(current?.isReadCompleted ?: false)
            repository.setSectionReadCompleted(sectionId, newStatus)
        }
    }

    fun toggleSectionQuestions(sectionId: String) {
        viewModelScope.launch {
            val current = repository.getSectionProgressSync(sectionId)
            val newStatus = !(current?.isQuestionsCompleted ?: false)
            val questions = repository.getQuestionsForSection(sectionId)
            repository.setSectionQuestionsCompleted(sectionId, newStatus, questions.size, questions.size)
        }
    }

    fun toggleSectionEnforce(sectionId: String) {
        viewModelScope.launch {
            val current = repository.getSectionProgressSync(sectionId)
            val newStatus = !(current?.isEnforceCompleted ?: false)
            val cards = repository.getFlashcardsForSection(sectionId)
            repository.setSectionEnforceCompleted(sectionId, newStatus, cards.size)
        }
    }

    fun markSectionAllCompleted(sectionId: String) {
        viewModelScope.launch {
            repository.markSectionAllCompleted(sectionId)
        }
    }

    fun navigateToNextSection() {
        val currentId = _selectedSectionId.value
        val next = CurriculumData.getNextSection(currentId)
        if (next != null) {
            openSection(next.chapterId, next.id, SectionStage.READ)
        } else {
            navigateToTab(MainTab.BOOK)
        }
    }

    fun navigateToPrevSection() {
        val currentId = _selectedSectionId.value
        val prev = CurriculumData.getPreviousSection(currentId)
        if (prev != null) {
            openSection(prev.chapterId, prev.id, SectionStage.READ)
        }
    }

    // In-Section Question Solver Actions
    fun selectSectionQOption(index: Int) {
        if (!_sectionQSubmitted.value) {
            _sectionQSbaSelected.value = index
        }
    }

    fun toggleSectionQTf(index: Int, answer: Boolean) {
        if (!_sectionQSubmitted.value) {
            _sectionQTfAnswers.value = _sectionQTfAnswers.value + (index to answer)
        }
    }

    fun submitSectionQuestion(question: Question) {
        _sectionQSubmitted.value = true
        var isCorrect = false
        var scorePercent = 0
        if (question.type == QuestionType.SINGLE_BEST_ANSWER) {
            isCorrect = _sectionQSbaSelected.value == question.correctAnswerIndex
            scorePercent = if (isCorrect) 100 else 0
        } else {
            var correctCount = 0
            for (i in question.options.indices) {
                val userAns = _sectionQTfAnswers.value[i]
                if (userAns != null && userAns == question.tfAnswers.getOrNull(i)) {
                    correctCount++
                }
            }
            scorePercent = (correctCount * 100) / question.options.size
            isCorrect = scorePercent >= 80
        }

        viewModelScope.launch {
            repository.recordQuestionAttempt(question.id, question.chapterId, isCorrect, scorePercent)
            // Update section progress for questions
            val currentProgress = repository.getSectionProgressSync(question.sectionId)
            val newAttempted = (currentProgress?.questionsAttemptedCount ?: 0) + 1
            val newCorrect = (currentProgress?.questionsCorrectCount ?: 0) + (if (isCorrect) 1 else 0)
            repository.setSectionQuestionsCompleted(
                sectionId = question.sectionId,
                completed = true,
                attemptedCount = newAttempted,
                correctCount = newCorrect
            )
        }
    }

    fun nextSectionQuestion(total: Int) {
        if (_sectionQIndex.value + 1 < total) {
            _sectionQIndex.value += 1
            _sectionQSbaSelected.value = null
            _sectionQTfAnswers.value = emptyMap()
            _sectionQSubmitted.value = false
        }
    }

    fun prevSectionQuestion() {
        if (_sectionQIndex.value > 0) {
            _sectionQIndex.value -= 1
            _sectionQSbaSelected.value = null
            _sectionQTfAnswers.value = emptyMap()
            _sectionQSubmitted.value = false
        }
    }

    // In-Section Flashcard Actions
    fun flipSectionCard() {
        _isSectionCardFlipped.value = !_isSectionCardFlipped.value
    }

    fun rateSectionCard(card: Flashcard, rating: String, total: Int) {
        viewModelScope.launch {
            repository.recordFlashcardReview(card.id, rating)
            val current = repository.getSectionProgressSync(_selectedSectionId.value)
            val newCount = (current?.ankiCardsReviewedCount ?: 0) + 1
            repository.setSectionEnforceCompleted(
                sectionId = _selectedSectionId.value,
                completed = true,
                reviewedCount = newCount
            )
            _isSectionCardFlipped.value = false
            if (_sectionCardIndex.value + 1 < total) {
                _sectionCardIndex.value += 1
            }
        }
    }

    // Unmapped Questions & Cards Navigation
    fun openUnmappedQuestions() {
        _currentScreen.value = Screen.UnmappedQuestions
    }

    fun openUnmappedAnki() {
        _currentScreen.value = Screen.UnmappedAnki
    }

    // Search Actions
    fun openSearch() {
        _isSearchOpen.value = true
    }

    fun closeSearch() {
        _isSearchOpen.value = false
        _searchQuery.value = ""
        _searchResults.value = emptyList()
    }

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
        if (query.length < 2) {
            _searchResults.value = emptyList()
            return
        }

        val q = query.trim().lowercase()
        val results = mutableListOf<SearchResultItem>()

        // Search Sections & Page Numbers
        CurriculumData.allSections.forEach { sec ->
            val pageRange = CurriculumData.getSectionPageRange(sec)
            val pageMatches = q.startsWith("p") || q.contains("page") || q.toIntOrNull()?.let { it in pageRange.first..pageRange.second } == true
            if (sec.title.lowercase().contains(q) || sec.number.lowercase().contains(q) || pageMatches) {
                results.add(
                    SearchResultItem(
                        id = "section_${sec.id}",
                        title = "${sec.number} ${sec.title}",
                        subtitle = "Farr Chapter ${sec.chapterId}",
                        type = "SECTION",
                        chapterId = sec.chapterId,
                        targetId = sec.id,
                        pageRef = "Pages ${pageRange.first}–${pageRange.second}"
                    )
                )
            }
        }

        // Search Concepts
        CurriculumData.getAllConcepts().forEach { c ->
            if (c.title.lowercase().contains(q) ||
                c.prime.keyConcepts.any { it.lowercase().contains(q) } ||
                c.prime.highYieldFacts.any { it.lowercase().contains(q) }
            ) {
                results.add(
                    SearchResultItem(
                        id = "concept_${c.id}",
                        title = c.title,
                        subtitle = "Chapter ${c.chapterId} • Section ${c.sectionId}",
                        type = "CONCEPT",
                        chapterId = c.chapterId,
                        targetId = c.sectionId,
                        pageRef = "Farr p. ${c.printedPage}"
                    )
                )
            }
        }

        // Search Questions
        QuestionBank.allQuestions.forEach { question ->
            if (question.stem.lowercase().contains(q) || question.detailedExplanation.lowercase().contains(q)) {
                results.add(
                    SearchResultItem(
                        id = "question_${question.id}",
                        title = question.stem.take(65) + if (question.stem.length > 65) "..." else "",
                        subtitle = "${question.sourceChapter} • ${question.type.name}",
                        type = "QUESTION",
                        chapterId = question.chapterId,
                        targetId = question.sectionId,
                        pageRef = "Farr p. ${question.printedPage}"
                    )
                )
            }
        }

        // Search Flashcards
        FlashcardBank.allFlashcards.forEach { fc ->
            if (fc.question.lowercase().contains(q) || fc.answer.lowercase().contains(q)) {
                results.add(
                    SearchResultItem(
                        id = "card_${fc.id}",
                        title = fc.question,
                        subtitle = "Anki • ${fc.category}",
                        type = "FLASHCARD",
                        chapterId = fc.chapterId,
                        targetId = fc.sourceSection.split(" ").firstOrNull() ?: "1.1",
                        pageRef = "Farr p. ${fc.printedPage}"
                    )
                )
            }
        }

        _searchResults.value = results.take(30)
    }

    // Bookmarks Actions
    fun openBookmarks() {
        _isBookmarksOpen.value = true
    }

    fun closeBookmarks() {
        _isBookmarksOpen.value = false
    }

    fun toggleBookmark(id: String, type: String, title: String, subtitle: String, targetId: String) {
        viewModelScope.launch {
            val isBookmarked = bookmarks.value.any { it.id == id }
            if (isBookmarked) {
                repository.removeBookmark(id)
            } else {
                repository.toggleBookmark(id, type, title, subtitle, targetId)
            }
        }
    }

    // Compatibility helpers
    fun getChapter(id: Int): Chapter? = CurriculumData.getChapter(id)
    fun getSection(chapterId: Int, sectionId: String): Section? = CurriculumData.getSection(chapterId, sectionId)
    fun getConcept(id: String): Concept? = CurriculumData.getConcept(id)
    fun getAllQuestions(): List<Question> = QuestionBank.allQuestions
    fun getQuestionsForSection(sectionId: String): List<Question> = repository.getQuestionsForSection(sectionId)
    fun getFlashcardsForSection(sectionId: String): List<Flashcard> = repository.getFlashcardsForSection(sectionId)
    fun getUnmappedQuestions(): List<Question> = repository.getUnmappedQuestions()
    fun getUnmappedFlashcards(): List<Flashcard> = repository.getUnmappedFlashcards()
    fun getQuestionsForConcept(conceptId: String): List<Question> = QuestionBank.getQuestionsByConcept(conceptId)
    fun getFlashcardsForConcept(conceptId: String): List<Flashcard> {
        val concept = getConcept(conceptId) ?: return emptyList()
        return repository.getFlashcardsForSection(concept.sectionId)
    }

    // Formula Calculator helpers
    fun selectFormula(formulaId: String) {
        _selectedFormulaId.value = formulaId
        val formula = FormulaBank.formulas.find { it.id == formulaId }
        if (formula != null) {
            _formulaInputs.value = formula.defaultInputs
        }
    }

    fun updateFormulaInput(symbol: String, value: Double) {
        _formulaInputs.value = _formulaInputs.value + (symbol to value)
    }

    fun getFormulaResult(formula: FormulaItem): Double {
        return FormulaBank.calculate(formula.id, _formulaInputs.value)
    }

    // Dedicated Question Bank & Flashcard deck actions
    fun setQBankChapterFilter(chapterId: Int?) {
        _qBankChapterFilter.value = chapterId
        _activeQuestionIndex.value = 0
        _qBankSbaSelected.value = null
        _qBankTfAnswers.value = emptyMap()
        _qBankSubmitted.value = false
    }

    fun nextQBankQuestion(totalQuestions: Int) {
        if (_activeQuestionIndex.value + 1 < totalQuestions) {
            _activeQuestionIndex.value += 1
            _qBankSbaSelected.value = null
            _qBankTfAnswers.value = emptyMap()
            _qBankSubmitted.value = false
        }
    }

    fun prevQBankQuestion() {
        if (_activeQuestionIndex.value > 0) {
            _activeQuestionIndex.value -= 1
            _qBankSbaSelected.value = null
            _qBankTfAnswers.value = emptyMap()
            _qBankSubmitted.value = false
        }
    }

    fun selectQBankSba(index: Int) {
        if (!_qBankSubmitted.value) {
            _qBankSbaSelected.value = index
        }
    }

    fun toggleQBankTf(stemIndex: Int, answer: Boolean) {
        if (!_qBankSubmitted.value) {
            _qBankTfAnswers.value = _qBankTfAnswers.value + (stemIndex to answer)
        }
    }

    fun submitQBankQuestion(question: Question) {
        _qBankSubmitted.value = true
        var isCorrect = false
        var scorePercent = 0
        if (question.type == QuestionType.SINGLE_BEST_ANSWER) {
            isCorrect = _qBankSbaSelected.value == question.correctAnswerIndex
            scorePercent = if (isCorrect) 100 else 0
        } else {
            var correctCount = 0
            for (i in question.options.indices) {
                val userAns = _qBankTfAnswers.value[i]
                if (userAns != null && userAns == question.tfAnswers.getOrNull(i)) {
                    correctCount++
                }
            }
            scorePercent = (correctCount * 100) / question.options.size
            isCorrect = scorePercent >= 80
        }

        viewModelScope.launch {
            repository.recordQuestionAttempt(question.id, question.chapterId, isCorrect, scorePercent)
        }
    }

    fun setFlashcardChapterFilter(chapterId: Int?) {
        _flashcardChapterFilter.value = chapterId
        _flashcardIndex.value = 0
        _isFlashcardFlipped.value = false
    }

    fun flipFlashcard() {
        _isFlashcardFlipped.value = !_isFlashcardFlipped.value
    }

    fun nextFlashcard(totalCards: Int) {
        if (_flashcardIndex.value + 1 < totalCards) {
            _flashcardIndex.value += 1
            _isFlashcardFlipped.value = false
        }
    }

    fun prevFlashcard() {
        if (_flashcardIndex.value > 0) {
            _flashcardIndex.value -= 1
            _isFlashcardFlipped.value = false
        }
    }

    fun rateFlashcard(card: Flashcard, rating: String, totalCards: Int) {
        viewModelScope.launch {
            repository.recordFlashcardReview(card.id, rating)
            _isFlashcardFlipped.value = false
            if (_flashcardIndex.value + 1 < totalCards) {
                _flashcardIndex.value += 1
            }
        }
    }

    fun openConcept(conceptId: String, stage: PaceStage = PaceStage.PRIME) {
        _selectedConceptId.value = conceptId
        _selectedPaceStage.value = stage
        _currentScreen.value = Screen.ConceptStudy(conceptId, stage)
    }

    fun setPaceStage(stage: PaceStage) {
        _selectedPaceStage.value = stage
        _currentScreen.value = Screen.ConceptStudy(_selectedConceptId.value, stage)
    }

    fun markCurrentStageCompleted(stage: PaceStage) {
        viewModelScope.launch {
            repository.recordStageCompleted(_selectedConceptId.value, stage.name)
        }
    }

    fun selectConceptQOption(index: Int) {
        if (!_conceptQSubmitted.value) _conceptQOptionSelected.value = index
    }

    fun toggleConceptQTf(stemIndex: Int, answer: Boolean) {
        if (!_conceptQSubmitted.value) _conceptQTfAnswers.value = _conceptQTfAnswers.value + (stemIndex to answer)
    }

    fun submitConceptQ(question: Question) {
        _conceptQSubmitted.value = true
        var isCorrect = false
        var scorePercent = 0
        if (question.type == QuestionType.SINGLE_BEST_ANSWER) {
            isCorrect = _conceptQOptionSelected.value == question.correctAnswerIndex
            scorePercent = if (isCorrect) 100 else 0
        } else {
            var correctCount = 0
            for (i in question.options.indices) {
                val userAns = _conceptQTfAnswers.value[i]
                if (userAns != null && userAns == question.tfAnswers.getOrNull(i)) {
                    correctCount++
                }
            }
            scorePercent = (correctCount * 100) / question.options.size
            isCorrect = scorePercent >= 80
        }
        viewModelScope.launch {
            repository.recordQuestionAttempt(question.id, question.chapterId, isCorrect, scorePercent)
        }
    }

    fun flipConceptCard() {
        _isConceptCardFlipped.value = !_isConceptCardFlipped.value
    }

    fun rateConceptCard(card: Flashcard, rating: String, totalCards: Int) {
        viewModelScope.launch {
            repository.recordFlashcardReview(card.id, rating)
            _isConceptCardFlipped.value = false
            if (_conceptCardIndex.value + 1 < totalCards) {
                _conceptCardIndex.value += 1
            }
        }
    }
}
