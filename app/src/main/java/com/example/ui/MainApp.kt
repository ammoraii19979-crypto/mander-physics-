package com.example.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
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
import com.example.ui.navigation.Screen
import com.example.ui.navigation.SectionStage
import com.example.ui.screens.BookCurriculumScreen
import com.example.ui.screens.FirstTimeSetupDialog
import com.example.ui.screens.HomeScreen
import com.example.ui.screens.SectionStudyScreen
import com.example.ui.screens.SettingsScreen
import com.example.ui.screens.StudyProgressScreen
import com.example.ui.screens.TodayStudyScreen
import com.example.ui.screens.UnmappedAnkiScreen
import com.example.ui.screens.UnmappedQuestionsScreen
import com.example.ui.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainApp(
    viewModel: MainViewModel,
    modifier: Modifier = Modifier
) {
    val currentTab by viewModel.currentTab.collectAsStateWithLifecycle()
    val currentScreen by viewModel.currentScreen.collectAsStateWithLifecycle()

    val studyPlan by viewModel.studyPlan.collectAsStateWithLifecycle()
    val scheduleInfo by viewModel.scheduleInfo.collectAsStateWithLifecycle()
    val todaySection by viewModel.todayTargetSection.collectAsStateWithLifecycle()
    val lastStoppedSection by viewModel.lastStoppedSection.collectAsStateWithLifecycle()
    val sectionProgressMap by viewModel.sectionProgressMap.collectAsStateWithLifecycle()
    val isSetupOpen by viewModel.isSetupOpen.collectAsStateWithLifecycle()

    val userProgress by viewModel.userProgress.collectAsStateWithLifecycle()
    val conceptMasteries by viewModel.conceptMasteries.collectAsStateWithLifecycle()
    val questionAttempts by viewModel.questionAttempts.collectAsStateWithLifecycle()
    val bookmarks by viewModel.bookmarks.collectAsStateWithLifecycle()

    // Section study state
    val sectionChapterId by viewModel.selectedChapterId.collectAsStateWithLifecycle()
    val sectionId by viewModel.selectedSectionId.collectAsStateWithLifecycle()
    val sectionStage by viewModel.selectedSectionStage.collectAsStateWithLifecycle()

    val sectionQIndex by viewModel.sectionQIndex.collectAsStateWithLifecycle()
    val sectionQSbaSelected by viewModel.sectionQSbaSelected.collectAsStateWithLifecycle()
    val sectionQTfAnswers by viewModel.sectionQTfAnswers.collectAsStateWithLifecycle()
    val sectionQSubmitted by viewModel.sectionQSubmitted.collectAsStateWithLifecycle()

    val sectionCardIndex by viewModel.sectionCardIndex.collectAsStateWithLifecycle()
    val isSectionCardFlipped by viewModel.isSectionCardFlipped.collectAsStateWithLifecycle()

    // Search & Bookmarks Modals
    val isSearchOpen by viewModel.isSearchOpen.collectAsStateWithLifecycle()
    val searchQuery by viewModel.searchQuery.collectAsStateWithLifecycle()
    val searchResults by viewModel.searchResults.collectAsStateWithLifecycle()
    val isBookmarksOpen by viewModel.isBookmarksOpen.collectAsStateWithLifecycle()

    val isTopLevelScreen = currentScreen !is Screen.SectionStudy &&
            currentScreen !is Screen.UnmappedQuestions &&
            currentScreen !is Screen.UnmappedAnki

    Scaffold(
        modifier = modifier.fillMaxSize(),
        bottomBar = {
            if (isTopLevelScreen) {
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
                            label = {
                                Text(
                                    text = tab.title,
                                    fontWeight = if (currentTab == tab) FontWeight.Bold else FontWeight.Normal
                                )
                            },
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
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (val screen = currentScreen) {
                is Screen.Home -> {
                    HomeScreen(
                        todaySection = todaySection,
                        lastStoppedSection = lastStoppedSection,
                        sectionProgress = sectionProgressMap,
                        scheduleInfo = scheduleInfo,
                        userProgress = userProgress,
                        conceptMasteries = conceptMasteries,
                        questionAttempts = questionAttempts,
                        onStartToday = { viewModel.startTodayStudy() },
                        onResumeStopped = { viewModel.continueWhereStopped() },
                        onOpenSection = { chId, sId, stage -> viewModel.openSection(chId, sId, stage) },
                        onToggleRead = { sId -> viewModel.toggleSectionRead(sId) },
                        onToggleQuestions = { sId -> viewModel.toggleSectionQuestions(sId) },
                        onToggleEnforce = { sId -> viewModel.toggleSectionEnforce(sId) },
                        onOpenSearch = { viewModel.openSearch() },
                        onOpenBookmarks = { viewModel.openBookmarks() },
                        onOpenSetup = { viewModel.openSetup() },
                        onViewProgress = { viewModel.navigateToTab(MainTab.PROGRESS) }
                    )
                }

                is Screen.Book -> {
                    BookCurriculumScreen(
                        sectionProgress = sectionProgressMap,
                        onOpenSection = { chId, sId, stage -> viewModel.openSection(chId, sId, stage) },
                        onOpenUnmappedQuestions = { viewModel.openUnmappedQuestions() },
                        onOpenUnmappedAnki = { viewModel.openUnmappedAnki() },
                        onOpenSearch = { viewModel.openSearch() }
                    )
                }

                is Screen.Today -> {
                    TodayStudyScreen(
                        todaySection = todaySection,
                        sectionProgress = sectionProgressMap,
                        onOpenSection = { chId, sId, stage -> viewModel.openSection(chId, sId, stage) },
                        onToggleRead = { sId -> viewModel.toggleSectionRead(sId) },
                        onToggleQuestions = { sId -> viewModel.toggleSectionQuestions(sId) },
                        onToggleEnforce = { sId -> viewModel.toggleSectionEnforce(sId) },
                        onMarkAllDone = { sId -> viewModel.markSectionAllCompleted(sId) },
                        onNextSection = { viewModel.navigateToNextSection() }
                    )
                }

                is Screen.Progress -> {
                    StudyProgressScreen(
                        scheduleInfo = scheduleInfo,
                        sectionProgress = sectionProgressMap,
                        conceptMasteries = conceptMasteries,
                        questionAttempts = questionAttempts,
                        onHandleMissedDays = { keepOriginal -> viewModel.handleMissedDays(keepOriginal) },
                        onOpenSetup = { viewModel.openSetup() }
                    )
                }

                is Screen.Settings -> {
                    SettingsScreen(
                        studyPlan = studyPlan,
                        scheduleInfo = scheduleInfo,
                        onOpenSetup = { viewModel.openSetup() },
                        onOpenUnmappedQuestions = { viewModel.openUnmappedQuestions() },
                        onOpenUnmappedAnki = { viewModel.openUnmappedAnki() }
                    )
                }

                is Screen.SectionStudy -> {
                    SectionStudyScreen(
                        chapterId = screen.chapterId,
                        sectionId = screen.sectionId,
                        currentStage = sectionStage,
                        sectionProgress = sectionProgressMap[screen.sectionId],
                        bookmarks = bookmarks,
                        onSelectStage = { stage -> viewModel.setSectionStage(stage) },
                        onToggleRead = { sId -> viewModel.toggleSectionRead(sId) },
                        onToggleQuestions = { sId -> viewModel.toggleSectionQuestions(sId) },
                        onToggleEnforce = { sId -> viewModel.toggleSectionEnforce(sId) },
                        onMarkAllComplete = { sId -> viewModel.markSectionAllCompleted(sId) },
                        onToggleBookmark = { id, type, title, subtitle, targetId ->
                            viewModel.toggleBookmark(id, type, title, subtitle, targetId)
                        },
                        onPrevSection = { viewModel.navigateToPrevSection() },
                        onNextSection = { viewModel.navigateToNextSection() },
                        onBack = { viewModel.navigateToTab(MainTab.BOOK) },
                        questionIndex = sectionQIndex,
                        selectedOption = sectionQSbaSelected,
                        tfAnswers = sectionQTfAnswers,
                        isQSubmitted = sectionQSubmitted,
                        onSelectOption = { idx -> viewModel.selectSectionQOption(idx) },
                        onToggleTf = { idx, ans -> viewModel.toggleSectionQTf(idx, ans) },
                        onSubmitQuestion = { q -> viewModel.submitSectionQuestion(q) },
                        onNextQuestion = { total -> viewModel.nextSectionQuestion(total) },
                        onPrevQuestion = { viewModel.prevSectionQuestion() },
                        cardIndex = sectionCardIndex,
                        isCardFlipped = isSectionCardFlipped,
                        onFlipCard = { viewModel.flipSectionCard() },
                        onRateCard = { card, rating, total -> viewModel.rateSectionCard(card, rating, total) }
                    )
                }

                is Screen.UnmappedQuestions -> {
                    UnmappedQuestionsScreen(
                        onBack = { viewModel.navigateToTab(MainTab.BOOK) }
                    )
                }

                is Screen.UnmappedAnki -> {
                    UnmappedAnkiScreen(
                        onBack = { viewModel.navigateToTab(MainTab.BOOK) }
                    )
                }

                else -> {
                    // Fallback to Home
                    HomeScreen(
                        todaySection = todaySection,
                        lastStoppedSection = lastStoppedSection,
                        sectionProgress = sectionProgressMap,
                        scheduleInfo = scheduleInfo,
                        userProgress = userProgress,
                        conceptMasteries = conceptMasteries,
                        questionAttempts = questionAttempts,
                        onStartToday = { viewModel.startTodayStudy() },
                        onResumeStopped = { viewModel.continueWhereStopped() },
                        onOpenSection = { chId, sId, stage -> viewModel.openSection(chId, sId, stage) },
                        onToggleRead = { sId -> viewModel.toggleSectionRead(sId) },
                        onToggleQuestions = { sId -> viewModel.toggleSectionQuestions(sId) },
                        onToggleEnforce = { sId -> viewModel.toggleSectionEnforce(sId) },
                        onOpenSearch = { viewModel.openSearch() },
                        onOpenBookmarks = { viewModel.openBookmarks() },
                        onOpenSetup = { viewModel.openSetup() },
                        onViewProgress = { viewModel.navigateToTab(MainTab.PROGRESS) }
                    )
                }
            }
        }
    }

    // First Time / Edit Study Schedule Dialog
    if (isSetupOpen) {
        FirstTimeSetupDialog(
            initialPlan = studyPlan,
            onDismiss = { viewModel.closeSetup() },
            onSavePlan = { option, days, daysPerWeek, mins, requireAll ->
                viewModel.saveStudyPlan(option, days, daysPerWeek, mins, requireAll)
                viewModel.closeSetup()
            }
        )
    }

    // Search Sheet
    if (isSearchOpen) {
        SearchSheet(
            query = searchQuery,
            results = searchResults,
            onQueryChanged = { viewModel.onSearchQueryChanged(it) },
            onSelectResult = { item ->
                viewModel.closeSearch()
                viewModel.openSection(item.chapterId, item.targetId, SectionStage.READ)
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
                viewModel.openSection(1, b.targetId, SectionStage.READ)
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
        MainTab.HOME -> Icons.Default.Home
        MainTab.BOOK -> Icons.Default.MenuBook
        MainTab.TODAY -> Icons.Default.CalendarToday
        MainTab.PROGRESS -> Icons.Default.Analytics
        MainTab.SETTINGS -> Icons.Default.Settings
    }
}
