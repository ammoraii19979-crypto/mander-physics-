package com.example.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.content.CurriculumData
import com.example.data.content.FlashcardBank
import com.example.data.content.FormulaBank
import com.example.data.content.QuestionBank
import com.example.data.local.BookmarkEntity
import com.example.data.local.ConceptMasteryEntity
import com.example.data.local.QuestionAttemptEntity
import com.example.data.local.UserProgressEntity
import com.example.data.model.Chapter
import com.example.data.model.Concept
import com.example.data.model.Flashcard
import com.example.data.model.FormulaItem
import com.example.data.model.Question
import com.example.data.repository.FarrsPhysicsRepository
import com.example.ui.navigation.MainTab
import com.example.ui.navigation.PaceStage
import com.example.ui.navigation.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

data class SearchResultItem(
    val id: String,
    val title: String,
    val subtitle: String,
    val type: String, // "CONCEPT", "QUESTION", "FORMULA", "FLASHCARD"
    val chapterId: Int,
    val targetId: String,
    val pageRef: String
)

class MainViewModel(
    private val repository: FarrsPhysicsRepository
) : ViewModel() {

    // Navigation & Current Screen State
    private val _currentTab = MutableStateFlow(MainTab.CURRICULUM)
    val currentTab: StateFlow<MainTab> = _currentTab.asStateFlow()

    private val _currentScreen = MutableStateFlow<Screen>(Screen.Curriculum)
    val currentScreen: StateFlow<Screen> = _currentScreen.asStateFlow()

    // Active Study State
    private val _selectedChapterId = MutableStateFlow(1)
    val selectedChapterId: StateFlow<Int> = _selectedChapterId.asStateFlow()

    private val _selectedConceptId = MutableStateFlow("1.1.1")
    val selectedConceptId: StateFlow<String> = _selectedConceptId.asStateFlow()

    private val _selectedPaceStage = MutableStateFlow(PaceStage.PRIME)
    val selectedPaceStage: StateFlow<PaceStage> = _selectedPaceStage.asStateFlow()

    // Room Database State Flows
    val userProgress: StateFlow<UserProgressEntity?> = repository.userProgress
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    val conceptMasteries: StateFlow<Map<String, ConceptMasteryEntity>> = repository.conceptMasteries
        .map { list -> list.associateBy { it.conceptId } }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyMap())

    val questionAttempts: StateFlow<List<QuestionAttemptEntity>> = repository.questionAttempts
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val bookmarks: StateFlow<List<BookmarkEntity>> = repository.bookmarks
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    // Question Bank State
    private val _qBankChapterFilter = MutableStateFlow<Int?>(null)
    val qBankChapterFilter: StateFlow<Int?> = _qBankChapterFilter.asStateFlow()

    private val _activeQuestionIndex = MutableStateFlow(0)
    val activeQuestionIndex: StateFlow<Int> = _activeQuestionIndex.asStateFlow()

    // In-study Challenge Question State (for single concept)
    private val _conceptQOptionSelected = MutableStateFlow<Int?>(null)
    val conceptQOptionSelected: StateFlow<Int?> = _conceptQOptionSelected.asStateFlow()

    private val _conceptQTfAnswers = MutableStateFlow<Map<Int, Boolean>>(emptyMap())
    val conceptQTfAnswers: StateFlow<Map<Int, Boolean>> = _conceptQTfAnswers.asStateFlow()

    private val _conceptQSubmitted = MutableStateFlow(false)
    val conceptQSubmitted: StateFlow<Boolean> = _conceptQSubmitted.asStateFlow()

    // Dedicated Q-Bank Screen State
    private val _qBankSbaSelected = MutableStateFlow<Int?>(null)
    val qBankSbaSelected: StateFlow<Int?> = _qBankSbaSelected.asStateFlow()

    private val _qBankTfAnswers = MutableStateFlow<Map<Int, Boolean>>(emptyMap())
    val qBankTfAnswers: StateFlow<Map<Int, Boolean>> = _qBankTfAnswers.asStateFlow()

    private val _qBankSubmitted = MutableStateFlow(false)
    val qBankSubmitted: StateFlow<Boolean> = _qBankSubmitted.asStateFlow()

    // Flashcard Deck State
    private val _flashcardChapterFilter = MutableStateFlow<Int?>(null)
    val flashcardChapterFilter: StateFlow<Int?> = _flashcardChapterFilter.asStateFlow()

    private val _flashcardIndex = MutableStateFlow(0)
    val flashcardIndex: StateFlow<Int> = _flashcardIndex.asStateFlow()

    private val _isFlashcardFlipped = MutableStateFlow(false)
    val isFlashcardFlipped: StateFlow<Boolean> = _isFlashcardFlipped.asStateFlow()

    // Concept-specific Enforce flashcards
    private val _conceptCardIndex = MutableStateFlow(0)
    val conceptCardIndex: StateFlow<Int> = _conceptCardIndex.asStateFlow()

    private val _isConceptCardFlipped = MutableStateFlow(false)
    val isConceptCardFlipped: StateFlow<Boolean> = _isConceptCardFlipped.asStateFlow()

    // Formula Calculator State
    private val _selectedFormulaId = MutableStateFlow("F1_INVERSE_SQUARE")
    val selectedFormulaId: StateFlow<String> = _selectedFormulaId.asStateFlow()

    private val _formulaInputs = MutableStateFlow<Map<String, Double>>(
        FormulaBank.formulas.first().defaultInputs
    )
    val formulaInputs: StateFlow<Map<String, Double>> = _formulaInputs.asStateFlow()

    // Search State
    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _searchResults = MutableStateFlow<List<SearchResultItem>>(emptyList())
    val searchResults: StateFlow<List<SearchResultItem>> = _searchResults.asStateFlow()

    private val _isSearchOpen = MutableStateFlow(false)
    val isSearchOpen: StateFlow<Boolean> = _isSearchOpen.asStateFlow()

    private val _isBookmarksOpen = MutableStateFlow(false)
    val isBookmarksOpen: StateFlow<Boolean> = _isBookmarksOpen.asStateFlow()

    // Navigation Actions
    fun navigateToTab(tab: MainTab) {
        _currentTab.value = tab
        when (tab) {
            MainTab.CURRICULUM -> _currentScreen.value = Screen.Curriculum
            MainTab.PACE_STUDY -> _currentScreen.value = Screen.ConceptStudy(_selectedConceptId.value, _selectedPaceStage.value)
            MainTab.QUESTIONS -> _currentScreen.value = Screen.QuestionBank
            MainTab.FLASHCARDS -> _currentScreen.value = Screen.FlashcardDeck
            MainTab.FORMULAS -> _currentScreen.value = Screen.FormulaBank
            MainTab.PROGRESS -> _currentScreen.value = Screen.Progress
        }
    }

    fun openChapter(chapterId: Int) {
        _selectedChapterId.value = chapterId
        _currentScreen.value = Screen.ChapterDetail(chapterId)
    }

    fun openConcept(conceptId: String, stage: PaceStage = PaceStage.PRIME) {
        _selectedConceptId.value = conceptId
        _selectedPaceStage.value = stage
        _conceptQOptionSelected.value = null
        _conceptQTfAnswers.value = emptyMap()
        _conceptQSubmitted.value = false
        _conceptCardIndex.value = 0
        _isConceptCardFlipped.value = false
        _currentScreen.value = Screen.ConceptStudy(conceptId, stage)
        _currentTab.value = MainTab.PACE_STUDY

        val concept = CurriculumData.getConcept(conceptId)
        if (concept != null) {
            _selectedChapterId.value = concept.chapterId
            viewModelScope.launch {
                repository.updateCurrentLocation(concept.chapterId, concept.sectionId, conceptId, stage.name)
            }
        }
    }

    fun setPaceStage(stage: PaceStage) {
        _selectedPaceStage.value = stage
        val currentConcept = _selectedConceptId.value
        _currentScreen.value = Screen.ConceptStudy(currentConcept, stage)
        viewModelScope.launch {
            val concept = CurriculumData.getConcept(currentConcept)
            if (concept != null) {
                repository.updateCurrentLocation(concept.chapterId, concept.sectionId, currentConcept, stage.name)
            }
        }
    }

    fun markCurrentStageCompleted(stage: PaceStage) {
        viewModelScope.launch {
            repository.recordStageCompleted(_selectedConceptId.value, stage.name)
            // Advance to next stage smoothly
            when (stage) {
                PaceStage.PRIME -> setPaceStage(PaceStage.ACQUIRE)
                PaceStage.ACQUIRE -> setPaceStage(PaceStage.CHALLENGE)
                PaceStage.CHALLENGE -> setPaceStage(PaceStage.ENFORCE)
                PaceStage.ENFORCE -> {
                    // All stages complete for this concept!
                }
            }
        }
    }

    // Concept Challenge Actions
    fun selectConceptQOption(index: Int) {
        if (!_conceptQSubmitted.value) {
            _conceptQOptionSelected.value = index
        }
    }

    fun toggleConceptQTf(stemIndex: Int, answer: Boolean) {
        if (!_conceptQSubmitted.value) {
            _conceptQTfAnswers.value = _conceptQTfAnswers.value + (stemIndex to answer)
        }
    }

    fun submitConceptQ(question: Question) {
        _conceptQSubmitted.value = true
        var isCorrect = false
        var scorePercent = 0
        if (question.type == com.example.data.model.QuestionType.SINGLE_BEST_ANSWER) {
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
            repository.recordStageCompleted(question.conceptId, "CHALLENGE")
        }
    }

    // Concept Enforce (Flashcards) Actions
    fun flipConceptCard() {
        _isConceptCardFlipped.value = !_isConceptCardFlipped.value
    }

    fun rateConceptCard(card: Flashcard, rating: String, totalCards: Int) {
        viewModelScope.launch {
            repository.recordFlashcardReview(card.id, rating)
            repository.recordStageCompleted(card.sourceSection, "ENFORCE")
            _isConceptCardFlipped.value = false
            if (_conceptCardIndex.value + 1 < totalCards) {
                _conceptCardIndex.value += 1
            }
        }
    }

    // Dedicated Question Bank Actions
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
        if (question.type == com.example.data.model.QuestionType.SINGLE_BEST_ANSWER) {
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

    // Dedicated Flashcard Deck Actions
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

    // Formula Calculator Actions
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

    // Bookmarks Actions
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

    fun openSearch() {
        _isSearchOpen.value = true
    }

    fun closeSearch() {
        _isSearchOpen.value = false
        _searchQuery.value = ""
        _searchResults.value = emptyList()
    }

    fun openBookmarks() {
        _isBookmarksOpen.value = true
    }

    fun closeBookmarks() {
        _isBookmarksOpen.value = false
    }

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
        if (query.length < 2) {
            _searchResults.value = emptyList()
            return
        }

        val q = query.trim().lowercase()
        val results = mutableListOf<SearchResultItem>()

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
                        targetId = c.id,
                        pageRef = "Farr p. ${c.printedPage} | PDF p. ${c.pdfPage}"
                    )
                )
            }
        }

        // Search Formulas
        FormulaBank.formulas.forEach { f ->
            if (f.title.lowercase().contains(q) || f.formulaDisplay.lowercase().contains(q) || f.whenToUse.lowercase().contains(q)) {
                results.add(
                    SearchResultItem(
                        id = "formula_${f.id}",
                        title = f.title,
                        subtitle = f.formulaDisplay,
                        type = "FORMULA",
                        chapterId = f.chapterId,
                        targetId = f.id,
                        pageRef = "p. ${f.printedPage}"
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
                        subtitle = fc.category,
                        type = "FLASHCARD",
                        chapterId = fc.chapterId,
                        targetId = fc.id,
                        pageRef = "p. ${fc.printedPage}"
                    )
                )
            }
        }

        _searchResults.value = results.take(30)
    }

    // Quick queries
    fun getChapter(id: Int): Chapter? = CurriculumData.getChapter(id)
    fun getConcept(id: String): Concept? = CurriculumData.getConcept(id)
    fun getAllQuestions(): List<Question> = QuestionBank.allQuestions
    fun getQuestionsForConcept(conceptId: String): List<Question> = QuestionBank.getQuestionsByConcept(conceptId)
    fun getFlashcardsForConcept(conceptId: String): List<Flashcard> {
        val concept = getConcept(conceptId) ?: return emptyList()
        val chapterCards = FlashcardBank.getFlashcardsByChapter(concept.chapterId)
        val filtered = chapterCards.filter { it.sourceSection.startsWith(concept.sectionId) }
        return if (filtered.isNotEmpty()) filtered else chapterCards.take(5)
    }
}
