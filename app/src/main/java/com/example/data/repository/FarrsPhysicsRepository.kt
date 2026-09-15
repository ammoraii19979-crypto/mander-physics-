package com.example.data.repository

import com.example.data.content.CurriculumData
import com.example.data.content.FlashcardBank
import com.example.data.content.FormulaBank
import com.example.data.content.QuestionBank
import com.example.data.local.BookmarkEntity
import com.example.data.local.ConceptMasteryEntity
import com.example.data.local.FarrsPhysicsDao
import com.example.data.local.FlashcardReviewEntity
import com.example.data.local.QuestionAttemptEntity
import com.example.data.local.UserProgressEntity
import com.example.data.model.Chapter
import com.example.data.model.Concept
import com.example.data.model.Flashcard
import com.example.data.model.FormulaItem
import com.example.data.model.Question
import kotlinx.coroutines.flow.Flow

class FarrsPhysicsRepository(
    private val dao: FarrsPhysicsDao
) {
    val userProgress: Flow<UserProgressEntity?> = dao.getUserProgress()
    val conceptMasteries: Flow<List<ConceptMasteryEntity>> = dao.getAllConceptMasteries()
    val questionAttempts: Flow<List<QuestionAttemptEntity>> = dao.getAllQuestionAttempts()
    val flashcardReviews: Flow<List<FlashcardReviewEntity>> = dao.getAllFlashcardReviews()
    val bookmarks: Flow<List<BookmarkEntity>> = dao.getAllBookmarks()

    // Curriculum queries
    fun getChapters(): List<Chapter> = CurriculumData.chapters

    fun getChapter(chapterId: Int): Chapter? = CurriculumData.getChapter(chapterId)

    fun getConcept(conceptId: String): Concept? = CurriculumData.getConcept(conceptId)

    fun getAllConcepts(): List<Concept> = CurriculumData.getAllConcepts()

    // Questions & Challenges
    fun getAllQuestions(): List<Question> = QuestionBank.allQuestions

    fun getQuestionsForChapter(chapterId: Int): List<Question> = QuestionBank.getQuestionsByChapter(chapterId)

    fun getQuestionsForConcept(conceptId: String): List<Question> = QuestionBank.getQuestionsByConcept(conceptId)

    // Flashcards & Enforce
    fun getAllFlashcards(): List<Flashcard> = FlashcardBank.allFlashcards

    fun getFlashcardsForChapter(chapterId: Int): List<Flashcard> = FlashcardBank.getFlashcardsByChapter(chapterId)

    fun getFlashcardsByCategory(category: String): List<Flashcard> = FlashcardBank.getFlashcardsByCategory(category)

    // Formulas
    fun getAllFormulas(): List<FormulaItem> = FormulaBank.formulas

    fun calculateFormula(formulaId: String, inputs: Map<String, Double>): Double = FormulaBank.calculate(formulaId, inputs)

    // User Progress Mutations
    suspend fun updateCurrentLocation(chapterId: Int, sectionId: String, conceptId: String, stage: String) {
        val current = dao.getUserProgressSync() ?: UserProgressEntity()
        dao.saveUserProgress(
            current.copy(
                currentChapterId = chapterId,
                currentSectionId = sectionId,
                currentConceptId = conceptId,
                currentStage = stage,
                lastStudyTimestamp = System.currentTimeMillis()
            )
        )
    }

    suspend fun recordStageCompleted(conceptId: String, stage: String) {
        val concept = getConcept(conceptId) ?: return
        val existing = dao.getConceptMasterySync(conceptId) ?: ConceptMasteryEntity(
            conceptId = conceptId,
            chapterId = concept.chapterId,
            sectionId = concept.sectionId
        )

        val updated = when (stage.uppercase()) {
            "PRIME" -> existing.copy(primeCompleted = true)
            "ACQUIRE" -> existing.copy(acquireCompleted = true)
            "CHALLENGE" -> existing.copy(
                questionsAttempted = existing.questionsAttempted + 1,
                questionsCorrect = existing.questionsCorrect + 1
            )
            "ENFORCE" -> existing.copy(
                flashcardsReviewed = existing.flashcardsReviewed + 1
            )
            else -> existing
        }

        // Calculate mastery score
        var score = 0
        if (updated.primeCompleted) score += 25
        if (updated.acquireCompleted) score += 25
        if (updated.questionsAttempted > 0) {
            val qRatio = (updated.questionsCorrect.toDouble() / updated.questionsAttempted).coerceIn(0.0, 1.0)
            score += (qRatio * 30).toInt()
        }
        if (updated.flashcardsReviewed > 0) score += 20
        score = score.coerceIn(0, 100)

        val status = when {
            score >= 80 -> "MASTERED"
            score >= 40 -> "REVIEWING"
            score > 0 -> "LEARNING"
            else -> "NOT_STARTED"
        }

        dao.saveConceptMastery(
            updated.copy(
                masteryScore = score,
                status = status,
                lastReviewedTimestamp = System.currentTimeMillis()
            )
        )
    }

    suspend fun recordQuestionAttempt(questionId: String, chapterId: Int, isCorrect: Boolean, scorePercent: Int) {
        dao.insertQuestionAttempt(
            QuestionAttemptEntity(
                questionId = questionId,
                chapterId = chapterId,
                isCorrect = isCorrect,
                userScorePercent = scorePercent,
                timestamp = System.currentTimeMillis()
            )
        )
        // Also update concept mastery if question belongs to a concept
        val question = QuestionBank.allQuestions.find { it.id == questionId }
        if (question != null) {
            val existing = dao.getConceptMasterySync(question.conceptId) ?: ConceptMasteryEntity(
                conceptId = question.conceptId,
                chapterId = question.chapterId,
                sectionId = question.sectionId
            )
            val newAttempted = existing.questionsAttempted + 1
            val newCorrect = if (isCorrect) existing.questionsCorrect + 1 else existing.questionsCorrect
            dao.saveConceptMastery(
                existing.copy(
                    questionsAttempted = newAttempted,
                    questionsCorrect = newCorrect
                )
            )
        }
    }

    suspend fun recordFlashcardReview(cardId: String, rating: String) {
        val days = when (rating) {
            "EASY" -> 5
            "GOOD" -> 3
            "HARD" -> 1
            else -> 1
        }
        dao.saveFlashcardReview(
            FlashcardReviewEntity(
                cardId = cardId,
                intervalDays = days,
                nextReviewTimestamp = System.currentTimeMillis() + (days * 24 * 60 * 60 * 1000L),
                lastRating = rating
            )
        )
    }

    suspend fun toggleBookmark(id: String, type: String, title: String, subtitle: String, targetId: String) {
        val current = dao.getAllBookmarks()
        // Simple insert or delete
        dao.addBookmark(
            BookmarkEntity(
                id = id,
                type = type,
                title = title,
                subtitle = subtitle,
                targetId = targetId
            )
        )
    }

    suspend fun removeBookmark(id: String) {
        dao.removeBookmark(id)
    }
}
