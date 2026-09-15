package com.example.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Quiz
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Style
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.ui.components.BookmarksSheet
import com.example.ui.components.SearchSheet
import com.example.ui.navigation.MainTab
import com.example.ui.navigation.PaceStage
import com.example.ui.navigation.Screen
import com.example.ui.screens.ChapterDetailScreen
import com.example.ui.screens.ConceptStudyScreen
import com.example.ui.screens.CurriculumScreen
import com.example.ui.screens.FlashcardDeckScreen
import com.example.ui.screens.FormulaCalculatorScreen
import com.example.ui.screens.ProgressScreen
import com.example.ui.screens.QuestionBankScreen
import com.example.ui.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainApp(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val currentTab by viewModel.currentTab.collectAsStateWithLifecycle()
    val currentScreen by viewModel.currentScreen.collectAsStateWithLifecycle()

    val userProgress by viewModel.userProgress.collectAsStateWithLifecycle()
    val conceptMasteries by viewModel.conceptMasteries.collectAsStateWithLifecycle()
    val questionAttempts by viewModel.questionAttempts.collectAsStateWithLifecycle()
    val bookmarks by viewModel.bookmarks.collectAsStateWithLifecycle()

    val selectedConceptId by viewModel.selectedConceptId.collectAsStateWithLifecycle()
    val selectedPaceStage by viewModel.selectedPaceStage.collectAsStateWithLifecycle()

    // QBank state
    val qBankFilter by viewModel.qBankChapterFilter.collectAsStateWithLifecycle()
    val qBankIndex by viewModel.activeQuestionIndex.collectAsStateWithLifecycle()
    val qBankSbaSelected by viewModel.qBankSbaSelected.collectAsStateWithLifecycle()
    val qBankTfAnswers by viewModel.qBankTfAnswers.collectAsStateWithLifecycle()
    val qBankSubmitted by viewModel.qBankSubmitted.collectAsStateWithLifecycle()

    // Flashcard deck state
    val flashcardFilter by viewModel.flashcardChapterFilter.collectAsStateWithLifecycle()
    val flashcardIndex by viewModel.flashcardIndex.collectAsStateWithLifecycle()
    val isFlashcardFlipped by viewModel.isFlashcardFlipped.collectAsStateWithLifecycle()

    // In-study Challenge & Flashcards
    val conceptQOption by viewModel.conceptQOptionSelected.collectAsStateWithLifecycle()
    val conceptQTf by viewModel.conceptQTfAnswers.collectAsStateWithLifecycle()
    val conceptQSubmitted by viewModel.conceptQSubmitted.collectAsStateWithLifecycle()

    val conceptCardIndex by viewModel.conceptCardIndex.collectAsStateWithLifecycle()
    val isConceptCardFlipped by viewModel.isConceptCardFlipped.collectAsStateWithLifecycle()

    // Formula Calculator
    val selectedFormulaId by viewModel.selectedFormulaId.collectAsStateWithLifecycle()
    val formulaInputs by viewModel.formulaInputs.collectAsStateWithLifecycle()

    // Search & Bookmarks Modals
    val isSearchOpen by viewModel.isSearchOpen.collectAsStateWithLifecycle()
    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()
    val searchResults by viewModel.searchResults.collectAsStateWithLifecycle()
    val isBookmarksOpen by viewModel.isBookmarksOpen.collectAsStateWithLifecycle()

    val conceptForStudy = viewModel.getConcept(selectedConceptId)
    val isCurrentConceptBookmarked = bookmarks.any { it.id == "concept_$selectedConceptId" }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Farr's Physics",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                },
                actions = {
                    IconButton(
                        onClick = { viewModel.openSearch() },
                        modifier = Modifier.testTag("open_search_button")
                    ) {
                        Icon(imageVector = Icons.Default.Search, contentDescription = "Search Curriculum")
                    }
                    IconButton(
                        onClick = { viewModel.openBookmarks() },
                        modifier = Modifier.testTag("open_bookmarks_button")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Bookmark,
                            contentDescription = "Saved Bookmarks",
                            tint = if (bookmarks.isNotEmpty()) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.onSurface
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
        bottomBar = {
            NavigationBar(
                containerColor = MaterialTheme.colorScheme.surface,
                tonalElevation = 4.dp
            ) {
                MainTab.values().forEach { tab ->
                    NavigationBarItem(
                        selected = currentTab == tab,
                        onClick = { viewModel.navigateToTab(tab) },
                        icon = {
                            Icon(
                                imageVector = getTabIcon(tab),
                                contentDescription = tab.title
                            )
                        },
                        label = { Text(tab.title, fontWeight = if (currentTab == tab) FontWeight.Bold else FontWeight.Normal) },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = MaterialTheme.colorScheme.primary,
                            selectedTextColor = MaterialTheme.colorScheme.primary,
                            indicatorColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.5f)
                        ),
                        modifier = Modifier.testTag("nav_tab_${tab.name.lowercase()}")
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (val screen = currentScreen) {
                is Screen.Curriculum -> {
                    CurriculumScreen(
                        userProgress = userProgress,
                        conceptMasteries = conceptMasteries,
                        questionAttempts = questionAttempts,
                        onSelectChapter = { chId -> viewModel.openChapter(chId) },
                        onResumeStudy = { cId, stage -> viewModel.openConcept(cId, stage) },
                        onViewProgress = { viewModel.navigateToTab(MainTab.PROGRESS) }
                    )
                }
                is Screen.ChapterDetail -> {
                    ChapterDetailScreen(
                        chapterId = screen.chapterId,
                        conceptMasteries = conceptMasteries,
                        onBack = { viewModel.navigateToTab(MainTab.CURRICULUM) },
                        onSelectConcept = { cId, stage -> viewModel.openConcept(cId, stage) }
                    )
                }
                is Screen.ConceptStudy -> {
                    ConceptStudyScreen(
                        conceptId = selectedConceptId,
                        currentStage = selectedPaceStage,
                        conceptMastery = conceptMasteries[selectedConceptId],
                        isBookmarked = isCurrentConceptBookmarked,
                        onSelectStage = { stage -> viewModel.setPaceStage(stage) },
                        onCompleteStage = { stage -> viewModel.markCurrentStageCompleted(stage) },
                        onToggleBookmark = {
                            conceptForStudy?.let { c ->
                                viewModel.toggleBookmark(
                                    id = "concept_${c.id}",
                                    type = "CONCEPT",
                                    title = c.title,
                                    subtitle = "Chapter ${c.chapterId} • Section ${c.sectionId}",
                                    targetId = c.id
                                )
                            }
                        },
                        onBackToChapter = {
                            conceptForStudy?.let { viewModel.openChapter(it.chapterId) }
                                ?: viewModel.navigateToTab(MainTab.CURRICULUM)
                        },
                        // Challenge props
                        conceptQuestions = viewModel.getQuestionsForConcept(selectedConceptId),
                        selectedSbaOption = conceptQOption,
                        tfAnswers = conceptQTf,
                        isQSubmitted = conceptQSubmitted,
                        onSelectSbaOption = { idx -> viewModel.selectConceptQOption(idx) },
                        onToggleTfStem = { idx, ans -> viewModel.toggleConceptQTf(idx, ans) },
                        onSubmitQuestion = { q -> viewModel.submitConceptQ(q) },
                        // Flashcards props
                        conceptCards = viewModel.getFlashcardsForConcept(selectedConceptId),
                        cardIndex = conceptCardIndex,
                        isCardFlipped = isConceptCardFlipped,
                        onFlipCard = { viewModel.flipConceptCard() },
                        onRateCard = { card, rating, total -> viewModel.rateConceptCard(card, rating, total) }
                    )
                }
                is Screen.QuestionBank -> {
                    QuestionBankScreen(
                        selectedChapterFilter = qBankFilter,
                        questionIndex = qBankIndex,
                        selectedSbaOption = qBankSbaSelected,
                        tfAnswers = qBankTfAnswers,
                        isSubmitted = qBankSubmitted,
                        onSelectChapterFilter = { ch -> viewModel.setQBankChapterFilter(ch) },
                        onNextQuestion = { total -> viewModel.nextQBankQuestion(total) },
                        onPrevQuestion = { viewModel.prevQBankQuestion() },
                        onSelectSbaOption = { idx -> viewModel.selectQBankSba(idx) },
                        onToggleTfStem = { idx, ans -> viewModel.toggleQBankTf(idx, ans) },
                        onSubmitQuestion = { q -> viewModel.submitQBankQuestion(q) }
                    )
                }
                is Screen.FlashcardDeck -> {
                    FlashcardDeckScreen(
                        selectedChapterFilter = flashcardFilter,
                        cardIndex = flashcardIndex,
                        isFlipped = isFlashcardFlipped,
                        onSelectChapterFilter = { ch -> viewModel.setFlashcardChapterFilter(ch) },
                        onFlipCard = { viewModel.flipFlashcard() },
                        onNextCard = { total -> viewModel.nextFlashcard(total) },
                        onPrevCard = { viewModel.prevFlashcard() },
                        onRateCard = { card, rating, total -> viewModel.rateFlashcard(card, rating, total) }
                    )
                }
                is Screen.FormulaBank -> {
                    FormulaCalculatorScreen(
                        selectedFormulaId = selectedFormulaId,
                        inputs = formulaInputs,
                        onSelectFormula = { id -> viewModel.selectFormula(id) },
                        onUpdateInput = { sym, v -> viewModel.updateFormulaInput(sym, v) },
                        getFormulaResult = { f -> viewModel.getFormulaResult(f) }
                    )
                }
                is Screen.Progress -> {
                    ProgressScreen(
                        conceptMasteries = conceptMasteries,
                        questionAttempts = questionAttempts,
                        bookmarks = bookmarks,
                        onSelectConcept = { cId, stage -> viewModel.openConcept(cId, stage) }
                    )
                }
            }
        }
    }

    // Search Sheet
    if (isSearchOpen) {
        SearchSheet(
            query = searchQuery,
            results = searchResults,
            onQueryChanged = { viewModel.onSearchQueryChanged(it) },
            onSelectResult = { item ->
                viewModel.closeSearch()
                if (item.type == "CONCEPT") {
                    viewModel.openConcept(item.targetId, PaceStage.PRIME)
                } else if (item.type == "FORMULA") {
                    viewModel.navigateToTab(MainTab.FORMULAS)
                    viewModel.selectFormula(item.targetId)
                } else if (item.type == "FLASHCARD") {
                    viewModel.navigateToTab(MainTab.FLASHCARDS)
                    viewModel.setFlashcardChapterFilter(item.chapterId)
                }
            },
            onDismiss = { viewModel.closeSearch() }
        )
    }

    // Bookmarks Sheet
    if (isBookmarksOpen) {
        BookmarksSheet(
            bookmarks = bookmarks,
            onSelectBookmark = { b ->
                viewModel.closeBookmarks()
                if (b.type == "CONCEPT") {
                    viewModel.openConcept(b.targetId, PaceStage.PRIME)
                }
            },
            onDeleteBookmark = { id ->
                viewModel.toggleBookmark(id, "", "", "", "")
            },
            onDismiss = { viewModel.closeBookmarks() }
        )
    }
}

fun getTabIcon(tab: MainTab): ImageVector {
    return when (tab) {
        MainTab.CURRICULUM -> Icons.Default.MenuBook
        MainTab.PACE_STUDY -> Icons.Default.School
        MainTab.QUESTIONS -> Icons.Default.Quiz
        MainTab.FLASHCARDS -> Icons.Default.Style
        MainTab.FORMULAS -> Icons.Default.Calculate
        MainTab.PROGRESS -> Icons.Default.Analytics
    }
}
