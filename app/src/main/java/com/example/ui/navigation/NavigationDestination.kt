package com.example.ui.navigation

enum class MainTab(val title: String, val iconName: String) {
    HOME("Home", "home"),
    BOOK("Book", "menu_book"),
    TODAY("Today", "event_available"),
    PROGRESS("Progress", "trending_up"),
    SETTINGS("Settings", "settings")
}

enum class SectionStage(val title: String, val subtitle: String) {
    READ("Read", "Farr's Physics Notes & Text"),
    QUESTIONS("Questions", "FRCR Physics Questions"),
    ENFORCE("Enforce", "Flashcards / Anki")
}

enum class PaceStage(val code: String, val title: String, val subtitle: String) {
    PRIME("P", "Read", "Farr's Physics Notes & Text"),
    ACQUIRE("A", "Acquire", "Mechanism & Intuition"),
    CHALLENGE("C", "Questions", "FRCR Questions & Practice"),
    ENFORCE("E", "Enforce", "Flashcards / Anki")
}

sealed class Screen {
    object Home : Screen()
    object Book : Screen()
    object Today : Screen()
    object Progress : Screen()
    object Settings : Screen()
    data class ChapterDetail(val chapterId: Int) : Screen()
    data class SectionStudy(
        val chapterId: Int,
        val sectionId: String,
        val stage: SectionStage = SectionStage.READ
    ) : Screen()
    data class ConceptStudy(val conceptId: String, val stage: PaceStage = PaceStage.PRIME) : Screen()
    object Setup : Screen()
    object UnmappedQuestions : Screen()
    object UnmappedAnki : Screen()
    object QuestionBank : Screen()
    object FlashcardDeck : Screen()
    object FormulaBank : Screen()
}
