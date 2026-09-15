package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FarrsPhysicsDao {
    @Query("SELECT * FROM user_progress WHERE id = 1 LIMIT 1")
    fun getUserProgress(): Flow<UserProgressEntity?>

    @Query("SELECT * FROM user_progress WHERE id = 1 LIMIT 1")
    suspend fun getUserProgressSync(): UserProgressEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveUserProgress(progress: UserProgressEntity)

    @Query("SELECT * FROM concept_mastery")
    fun getAllConceptMasteries(): Flow<List<ConceptMasteryEntity>>

    @Query("SELECT * FROM concept_mastery WHERE conceptId = :conceptId LIMIT 1")
    fun getConceptMastery(conceptId: String): Flow<ConceptMasteryEntity?>

    @Query("SELECT * FROM concept_mastery WHERE conceptId = :conceptId LIMIT 1")
    suspend fun getConceptMasterySync(conceptId: String): ConceptMasteryEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveConceptMastery(mastery: ConceptMasteryEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveAllConceptMasteries(list: List<ConceptMasteryEntity>)

    @Query("SELECT * FROM question_attempts ORDER BY timestamp DESC")
    fun getAllQuestionAttempts(): Flow<List<QuestionAttemptEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuestionAttempt(attempt: QuestionAttemptEntity)

    @Query("SELECT * FROM flashcard_reviews")
    fun getAllFlashcardReviews(): Flow<List<FlashcardReviewEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveFlashcardReview(review: FlashcardReviewEntity)

    @Query("SELECT * FROM bookmarks ORDER BY timestamp DESC")
    fun getAllBookmarks(): Flow<List<BookmarkEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addBookmark(bookmark: BookmarkEntity)

    @Query("DELETE FROM bookmarks WHERE id = :id")
    suspend fun removeBookmark(id: String)
}
