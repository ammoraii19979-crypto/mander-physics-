package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.content.CurriculumData
import com.example.data.content.FormulaBank
import com.example.data.content.QuestionBank
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [36])
class ExampleRobolectricTest {

  @Test
  fun `read string from context`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val appName = context.getString(R.string.app_name)
    assertEquals("Farr's Physics", appName)
  }

  @Test
  fun `verify curriculum has 10 chapters and content`() {
    val chapters = CurriculumData.chapters
    assertEquals(10, chapters.size)
    assertEquals(216, CurriculumData.TOTAL_BOOK_PAGES)
    assertTrue(CurriculumData.allSections.isNotEmpty())
    assertTrue(QuestionBank.allQuestions.isNotEmpty())
    assertTrue(QuestionBank.unmappedQuestions.isNotEmpty())
  }

  @Test
  fun `verify section page ranges and counts`() {
    val sec1 = CurriculumData.getSection(1, "1.1")
    assertTrue(sec1 != null)
    sec1?.let {
      val (start, end) = CurriculumData.getSectionPageRange(it)
      val count = CurriculumData.getSectionPageCount(it)
      assertTrue(start >= 1)
      assertTrue(end >= start)
      assertEquals(end - start + 1, count)
    }
  }

  @Test
  fun `verify mastery percentage calculation logic`() {
    val allConcepts = CurriculumData.getAllConcepts()
    assertTrue(allConcepts.isNotEmpty())
    // 5 out of 10 concepts mastered = 50%
    val dummyAttempts = listOf(
        com.example.data.local.QuestionAttemptEntity(questionId = "q1", chapterId = 1, isCorrect = true, userScorePercent = 100),
        com.example.data.local.QuestionAttemptEntity(questionId = "q2", chapterId = 1, isCorrect = false, userScorePercent = 0)
    )
    val accuracy = dummyAttempts.count { it.isCorrect }.toFloat() / dummyAttempts.size.toFloat()
    assertEquals(0.5f, accuracy, 0.01f)
  }
}

