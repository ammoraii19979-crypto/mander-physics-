package com.example.data.model

data class Chapter(
    val id: Int,
    val number: Int,
    val title: String,
    val subtitle: String,
    val iconName: String,
    val sections: List<Section>,
    val pdfStartPage: Int,
    val printStartPage: Int
)

data class Section(
    val id: String,
    val chapterId: Int,
    val number: String,
    val title: String,
    val concepts: List<Concept>,
    val printedPage: Int,
    val pdfPage: Int
)

data class Concept(
    val id: String,
    val chapterId: Int,
    val sectionId: String,
    val title: String,
    val printedPage: Int,
    val pdfPage: Int,
    val prime: PrimeContent,
    val acquire: AcquireContent
)

data class PrimeContent(
    val structuredNotes: List<String>,
    val keyConcepts: List<String>,
    val definitions: List<DefinitionItem>,
    val equations: List<String>,
    val tables: List<RevisionTable>,
    val whatYouMustUnderstand: List<String>,
    val whatYouShouldMemorize: List<String>,
    val highYieldFacts: List<String>,
    val chapterSummary: List<String>,
    val learningObjectives: List<String>
)

data class DefinitionItem(
    val term: String,
    val definition: String
)

data class RevisionTable(
    val title: String,
    val headers: List<String>,
    val rows: List<List<String>>
)

data class AcquireContent(
    val simpleExplanation: String,
    val stepByStepMechanism: List<String>,
    val whyItHappens: String,
    val clinicalRelevance: String,
    val commonMisconception: String,
    val analogy: String,
    val imageQualityAndDoseImpact: String,
    val interactiveDiagramType: String // e.g. "XRAY_SPECTRUM", "ATTENUATION_CURVE", "COMPTON_VS_PE", "GRID_CUTOFF", "CT_WINDOWING", "GAMMA_PHA", "US_TGC", "MRI_T1_T2"
)

enum class QuestionType {
    SINGLE_BEST_ANSWER,
    TRUE_FALSE_5STEM,
    PHYSICS_CALCULATION,
    CONCEPTUAL
}

data class Question(
    val id: String,
    val chapterId: Int,
    val sectionId: String,
    val conceptId: String,
    val type: QuestionType,
    val difficulty: String, // "Foundation", "Core FRCR", "Advanced"
    val stem: String,
    val options: List<String>, // for SBA or 5-stem
    val correctAnswerIndex: Int = 0, // for SBA
    val tfAnswers: List<Boolean> = emptyList(), // for 5-stem: true/false for each option
    val tfExplanations: List<String> = emptyList(), // individual explanation for each of 5 stems
    val detailedExplanation: String,
    val wrongOptionsExplanation: List<String> = emptyList(),
    val sourceChapter: String,
    val sourceSection: String,
    val printedPage: Int,
    val pdfPage: Int,
    val learningObjective: String
)

data class Flashcard(
    val id: String,
    val chapterId: Int,
    val category: String, // "Definitions", "Formulas", "Relationships", "Equipment", "Image Quality", "Radiation Protection", "CT", "Nuclear Medicine", "Ultrasound", "MRI", "High-yield"
    val question: String,
    val answer: String,
    val keyPoints: List<String>,
    val sourceSection: String,
    val printedPage: Int,
    val pdfPage: Int
)

data class FormulaItem(
    val id: String,
    val chapterId: Int,
    val title: String,
    val formulaLatex: String,
    val formulaDisplay: String,
    val variables: List<FormulaVariable>,
    val unitOfResult: String,
    val whenToUse: String,
    val clinicalExample: String,
    val sourceSection: String,
    val printedPage: Int,
    val pdfPage: Int,
    val defaultInputs: Map<String, Double>
)

data class FormulaVariable(
    val symbol: String,
    val name: String,
    val unit: String,
    val defaultValue: Double,
    val minValue: Double = 0.0,
    val maxValue: Double = 1000.0,
    val step: Double = 1.0
)

data class QuickTableItem(
    val concept: String,
    val definition: String,
    val keyRelationship: String,
    val clinicalRelevance: String,
    val chapterId: Int,
    val printedPage: Int
)

enum class MasteryStatus {
    NOT_STARTED,
    LEARNING,
    REVIEWING,
    MASTERED
}

data class DailyStudyTask(
    val dayNumber: Int,
    val primeTopic: String,
    val acquireTopic: String,
    val challengeCount: Int,
    val flashcardCount: Int,
    val targetChapterId: Int,
    val targetConceptId: String,
    val isCompleted: Boolean = false
)
