package com.example.ui.navigation

enum class MainTab(val title: String, val iconName: String) {
    CURRICULUM("Chapters", "menu_book"),
    PACE_STUDY("Study", "school"),
    QUESTIONS("Q-Bank", "quiz"),
    FLASHCARDS("Cards", "style"),
    FORMULAS("Formulas", "calculate"),
    PROGRESS("Stats", "analytics")
}

enum class PaceStage(val code: String, val title: String, val subtitle: String) {
    PRIME("P", "Prime", "High-Yield Notes & Summary"),
    ACQUIRE("A", "Acquire", "Mechanism & Intuition"),
    CHALLENGE("C", "Challenge", "FRCR Questions & Practice"),
    ENFORCE("E", "Enforce", "Flashcards & Spaced Recall")
}

sealed class Screen {
    object Curriculum : Screen()
    data class ChapterDetail(val chapterId: Int) : Screen()
    data class ConceptStudy(val conceptId: String, val stage: PaceStage = PaceStage.PRIME) : Screen()
    object QuestionBank : Screen()
    object FlashcardDeck : Screen()
    object FormulaBank : Screen()
    object Progress : Screen()
}
