package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_progress")
data class UserProgressEntity(
    @PrimaryKey val id: Int = 1,
    val currentChapterId: Int = 1,
    val currentSectionId: String = "1.4",
    val currentConceptId: String = "1.4.1",
    val currentStage: String = "PRIME",
    val studyStreakDays: Int = 3,
    val lastStudyTimestamp: Long = System.currentTimeMillis(),
    val totalStudyMinutes: Int = 45,
    val dailyGoalMinutes: Int = 30,
    val todayStudyMinutes: Int = 18
)

@Entity(tableName = "concept_mastery")
data class ConceptMasteryEntity(
    @PrimaryKey val conceptId: String,
    val chapterId: Int,
    val sectionId: String,
    val primeCompleted: Boolean = false,
    val acquireCompleted: Boolean = false,
    val questionsAttempted: Int = 0,
    val questionsCorrect: Int = 0,
    val flashcardsReviewed: Int = 0,
    val confidenceRating: Int = 1, // 1 to 5
    val masteryScore: Int = 0, // 0 to 100%
    val status: String = "NOT_STARTED", // NOT_STARTED, LEARNING, REVIEWING, MASTERED
    val lastReviewedTimestamp: Long = System.currentTimeMillis()
)

@Entity(tableName = "question_attempts")
data class QuestionAttemptEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val questionId: String,
    val chapterId: Int,
    val isCorrect: Boolean,
    val userScorePercent: Int,
    val timestamp: Long = System.currentTimeMillis()
)

@Entity(tableName = "flashcard_reviews")
data class FlashcardReviewEntity(
    @PrimaryKey val cardId: String,
    val repetitions: Int = 0,
    val intervalDays: Int = 1,
    val easeFactor: Float = 2.5f,
    val nextReviewTimestamp: Long = System.currentTimeMillis(),
    val lastRating: String = "NEW" // NEW, AGAIN, HARD, GOOD, EASY
)

@Entity(tableName = "bookmarks")
data class BookmarkEntity(
    @PrimaryKey val id: String,
    val type: String, // CONCEPT, FORMULA, QUESTION, FLASHCARD
    val title: String,
    val subtitle: String,
    val targetId: String,
    val timestamp: Long = System.currentTimeMillis()
)
