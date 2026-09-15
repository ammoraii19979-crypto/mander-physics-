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
    assertTrue(QuestionBank.allQuestions.isNotEmpty())
    assertTrue(FormulaBank.formulas.isNotEmpty())
  }
}

