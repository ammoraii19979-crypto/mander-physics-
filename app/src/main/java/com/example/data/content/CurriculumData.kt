package com.example.data.content

import com.example.data.model.Chapter
import com.example.data.model.Section

object CurriculumData {
    val chapters: List<Chapter> by lazy {
        listOf(
            Chapter(
                id = 1,
                number = 1,
                title = "Radiation Physics",
                subtitle = "Atomic structure, electromagnetic radiation, X-ray production & matter interaction",
                iconName = "atomic",
                pdfStartPage = 10,
                printStartPage = 1,
                sections = Chapter1Content.sections
            ),
            Chapter(
                id = 2,
                number = 2,
                title = "Radiation Hazards and Protection",
                subtitle = "Tissue interactions, dosimetry, biological effects, IRR99 & IRMER 2000",
                iconName = "shield",
                pdfStartPage = 32,
                printStartPage = 23,
                sections = Chapter2Content.sections
            ),
            Chapter(
                id = 3,
                number = 3,
                title = "Imaging with X-rays",
                subtitle = "Contrast, scatter, antiscatter grids, unsharpness, tube ratings & heel effect",
                iconName = "xray",
                pdfStartPage = 58,
                printStartPage = 49,
                sections = Chapter3Content.sections
            ),
            Chapter(
                id = 4,
                number = 4,
                title = "Film-Screen Radiography",
                subtitle = "Emulsion chemistry, characteristic H&D curve, rare earth screens & mammography",
                iconName = "film",
                pdfStartPage = 74,
                printStartPage = 65,
                sections = Chapter4Content.sections
            ),
            Chapter(
                id = 5,
                number = 5,
                title = "Digital Radiography",
                subtitle = "Digital sampling, MTF, Computed Radiography (CR), DDR, DQE, PACS & DICOM",
                iconName = "digital",
                pdfStartPage = 88,
                printStartPage = 79,
                sections = Chapter5Content.sections
            ),
            Chapter(
                id = 6,
                number = 6,
                title = "Fluoroscopy",
                subtitle = "Image intensifier, automatic brightness control (ABC), DSA & flat plate detectors",
                iconName = "monitor",
                pdfStartPage = 100,
                printStartPage = 91,
                sections = Chapter6Content.sections
            ),
            Chapter(
                id = 7,
                number = 7,
                title = "Computed Tomography",
                subtitle = "CT numbers, filtered back-projection, helical multislice, pitch, artefacts & CTDI/DLP",
                iconName = "ct",
                pdfStartPage = 112,
                printStartPage = 103,
                sections = Chapter7Content.sections
            ),
            Chapter(
                id = 8,
                number = 8,
                title = "Imaging with Ultrasound",
                subtitle = "Piezoelectric effect, acoustic impedance, B-mode, Doppler methods, artefacts & safety (TI/MI)",
                iconName = "ultrasound",
                pdfStartPage = 136,
                printStartPage = 127,
                sections = Chapter8Content.sections
            ),
            Chapter(
                id = 9,
                number = 9,
                title = "Magnetic Resonance Imaging",
                subtitle = "Nuclear spin precession, T1/T2 relaxation, spin-echo, spatial encoding, pulse sequences & safety",
                iconName = "mri",
                pdfStartPage = 154,
                printStartPage = 145,
                sections = Chapter9Content.sections
            ),
            Chapter(
                id = 10,
                number = 10,
                title = "Nuclear Medicine & Gamma Imaging",
                subtitle = "Radionuclides, Mo-99/Tc-99m generators, Gamma camera, SPECT & PET coincidence",
                iconName = "gamma",
                pdfStartPage = 176,
                printStartPage = 167,
                sections = Chapter10Content.sections
            )
        )
    }

    fun getChapter(chapterId: Int): Chapter? = chapters.find { it.id == chapterId }

    fun getSection(chapterId: Int, sectionId: String): Section? {
        return getChapter(chapterId)?.sections?.find { it.id == sectionId }
    }

    fun getAllConcepts(): List<com.example.data.model.Concept> {
        return chapters.flatMap { it.sections }.flatMap { it.concepts }
    }

    fun getConcept(conceptId: String): com.example.data.model.Concept? {
        return getAllConcepts().find { it.id == conceptId }
    }

    fun getTotalConceptsCount(): Int = getAllConcepts().size
}
