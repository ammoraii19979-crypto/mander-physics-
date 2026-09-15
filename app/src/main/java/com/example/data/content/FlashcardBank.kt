package com.example.data.content

import com.example.data.model.Flashcard

object FlashcardBank {
    val allFlashcards: List<Flashcard> by lazy {
        listOf(
            // Chapter 1
            Flashcard(
                id = "FC1_1",
                chapterId = 1,
                category = "Definitions",
                question = "What is the Half-Value Layer (HVL) of an X-ray beam?",
                answer = "The thickness of a specified material that attenuates the intensity of a narrow X-ray beam to exactly half (50%) of its original value.",
                keyPoints = listOf("HVL = 0.693 / u", "Measures beam quality (penetrating power)", "Requires narrow-beam geometry to exclude scatter"),
                sourceSection = "1.4 Interaction of X-rays with Matter",
                printedPage = 9,
                pdfPage = 18
            ),
            Flashcard(
                id = "FC1_2",
                chapterId = 1,
                category = "High-yield",
                question = "What are the K-shell binding energies of Tungsten (W) and Molybdenum (Mo)?",
                answer = "Tungsten (Z=74): 70 keV. Molybdenum (Z=42): 20 keV.",
                keyPoints = listOf("Tungsten Ka = 58 keV, Kb = 68 keV", "Molybdenum Ka = 17.5 keV, Kb = 19.6 keV", "Tube kV must exceed EK to produce K-characteristic lines"),
                sourceSection = "1.1 Structure of the Atom",
                printedPage = 2,
                pdfPage = 11
            ),
            Flashcard(
                id = "FC1_3",
                chapterId = 1,
                category = "Formulas",
                question = "How does Photoelectric absorption probability depend on atomic number (Z) and photon energy (E)?",
                answer = "Photoelectric probability (tau) is proportional to rho × Z^3 / E^3.",
                keyPoints = listOf("Highly sensitive to atomic number (Z^3)", "Falls dramatically with energy (1/E^3)", "Exploited in bone imaging, iodine (Z=53), and barium (Z=56)"),
                sourceSection = "1.4 Interaction of X-rays with Matter",
                printedPage = 12,
                pdfPage = 21
            ),

            // Chapter 2
            Flashcard(
                id = "FC2_1",
                chapterId = 2,
                category = "Radiation Protection",
                question = "What are the statutory annual effective dose limits under IRR99 for: (1) Employees, and (2) Members of the public?",
                answer = "Employees (>=18 yrs): 20 mSv/year. Public: 1 mSv/year.",
                keyPoints = listOf("Classified Person threshold = >3/10th of limit (>6 mSv/yr)", "Trainees (16-18 yrs) = 6 mSv/yr", "Fetus of pregnant employee = 1 mSv over pregnancy"),
                sourceSection = "2.5 IRR99 and IRMER 2000 Legislation",
                printedPage = 33,
                pdfPage = 42
            ),
            Flashcard(
                id = "FC2_2",
                chapterId = 2,
                category = "Definitions",
                question = "What is the difference between Deterministic and Stochastic radiation effects?",
                answer = "Deterministic has a threshold dose; severity increases with dose (cell killing). Stochastic has no threshold; probability increases with dose, but severity is independent of dose (DNA mutation).",
                keyPoints = listOf("Deterministic: erythema (2-5 Gy), cataracts (5 Gy), sterility (2-3 Gy)", "Stochastic: cancer (5% / Sv risk), hereditary mutations", "ALARP and DRLs protect against stochastic risks"),
                sourceSection = "2.3 Biological Effects & Population Dose",
                printedPage = 24,
                pdfPage = 33
            ),
            Flashcard(
                id = "FC2_3",
                chapterId = 2,
                category = "Radiation Protection",
                question = "Why does an undercouch X-ray tube provide superior radiation protection for fluoroscopy staff compared to an overcouch tube?",
                answer = "Patient entrance surface produces the highest backscatter. Undercouch geometry directs this intense scatter downwards towards the leaded skirts and floor, shielding operator eyes and thyroid.",
                keyPoints = listOf("Scatter is ~5 uGy / (Gy·cm^2 DAP) at 1 m", "Overcouch tube projects scatter directly up towards staff face/lens", "Always keep image intensifier as close to the patient as possible"),
                sourceSection = "2.8 Practical Radiation Protection",
                printedPage = 42,
                pdfPage = 51
            ),

            // Chapter 3
            Flashcard(
                id = "FC3_1",
                chapterId = 3,
                category = "Equipment",
                question = "What is the Line Focus Principle in an X-ray tube anode?",
                answer = "The anode target is angled (7-20 degrees) so the actual focal area bombarded by electrons is large (for heat loading), while the projected effective focal spot viewed from the patient is small (for sharp detail).",
                keyPoints = listOf("Effective Focal Spot = Actual Track × sin(theta)", "Smaller angle = smaller focal spot = sharper resolution", "Trade-off: smaller angle reduces maximum usable field size"),
                sourceSection = "3.6 Unsharpness & X-ray Tube Physics",
                printedPage = 59,
                pdfPage = 68
            ),
            Flashcard(
                id = "FC3_2",
                chapterId = 3,
                category = "Equipment",
                question = "What is the Anode Heel Effect and how should patient anatomy be oriented?",
                answer = "Beam intensity is reduced on the anode side due to self-attenuation of X-rays within the target heel. The thicker body part should be positioned towards the cathode side.",
                keyPoints = listOf("Cathode side is up to 30% more intense", "Anode side has lower intensity but slightly higher mean energy (harder)", "Exploited in mammography (cathode at chest wall)"),
                sourceSection = "3.6 Unsharpness & X-ray Tube Physics",
                printedPage = 61,
                pdfPage = 70
            ),
            Flashcard(
                id = "FC3_3",
                chapterId = 3,
                category = "Equipment",
                question = "What is the Bucky factor (Grid factor) and what is its typical value?",
                answer = "The ratio of radiation exposure needed with an antiscatter grid to that needed without a grid to achieve the same image receptor optical density. Typically 3 to 5.",
                keyPoints = listOf("Grid Ratio = lead strip height / interspace width (typically 8:1)", "Improves contrast by factor of 2-4 by stopping scatter", "Increases patient radiation dose by 3-5x"),
                sourceSection = "3.4 Scattered Radiation & Grids",
                printedPage = 55,
                pdfPage = 64
            ),

            // Chapter 4
            Flashcard(
                id = "FC4_1",
                chapterId = 4,
                category = "Definitions",
                question = "What is Optical Density (D) and what does D = 2.0 mean?",
                answer = "D = log10(Incident light / Transmitted light). D = 2.0 means exactly 1% of light is transmitted (1 / 100).",
                keyPoints = listOf("D = 1.0 -> 10% transmission", "D = 2.0 -> 1% transmission", "D = 3.0 -> 0.1% transmission", "Base plus fog is typically 0.15 - 0.20"),
                sourceSection = "4.1 Film-Screen Image Formation & H&D Curve",
                printedPage = 66,
                pdfPage = 75
            ),
            Flashcard(
                id = "FC4_2",
                chapterId = 4,
                category = "Equipment",
                question = "Why does mammography use a single screen with single-emulsion film positioned on the distal side?",
                answer = "To eliminate crossover light spread and parallax distortion completely, maximizing spatial resolution to >= 15 lp/mm for microcalcification detection.",
                keyPoints = listOf("Distal screen: X-rays pass through transparent base first, then hit screen right next to emulsion", "Uses Mo/Rh targets with 25-30 kV", "Firm compression reduces thickness, scatter, and dose"),
                sourceSection = "4.3 Screens, Unsharpness & Mammography",
                printedPage = 76,
                pdfPage = 85
            ),

            // Chapter 5
            Flashcard(
                id = "FC5_1",
                chapterId = 5,
                category = "Definitions",
                question = "What is Detective Quantum Efficiency (DQE) and why is it important?",
                answer = "DQE = (SNR_out / SNR_in)^2. It measures how efficiently a detector converts incident X-ray information into image signal without adding noise.",
                keyPoints = listOf("Film-screen and CR DQE ~ 30%", "Direct and Indirect Digital Radiography (DR) DQE ~ 65%", "Higher DQE allows substantial patient radiation dose reduction"),
                sourceSection = "5.3 Computed Radiography (CR) & DDR",
                printedPage = 86,
                pdfPage = 95
            ),
            Flashcard(
                id = "FC5_2",
                chapterId = 5,
                category = "High-yield",
                question = "What is the difference between Indirect DR and Direct DR (DDR)?",
                answer = "Indirect DR converts X-rays to light via a scintillator (CsI needles), then to charge via a-Si photodiodes. Direct DR uses a photoconductor (a-Se) to convert X-rays directly into charge with zero light spread.",
                keyPoints = listOf("Indirect DR: CsI:Tl + a-Si TFT", "Direct DR: Amorphous Selenium (a-Se) + TFT", "Direct DR achieves superior Modulation Transfer Function (MTF)"),
                sourceSection = "5.3 Computed Radiography (CR) & DDR",
                printedPage = 85,
                pdfPage = 94
            ),

            // Chapter 6
            Flashcard(
                id = "FC6_1",
                chapterId = 6,
                category = "Equipment",
                question = "How is the Brightness Gain of an Image Intensifier calculated?",
                answer = "Brightness Gain = Flux Gain × Minification Gain. Minification Gain = (Input Diameter / Output Diameter)^2.",
                keyPoints = listOf("Flux gain ~ 50 (from 25 kV electron acceleration)", "Minification gain for 30cm to 3cm = (30/3)^2 = 100", "Total brightness gain ~ 5,000"),
                sourceSection = "6.1 The Image Intensifier",
                printedPage = 92,
                pdfPage = 101
            ),
            Flashcard(
                id = "FC6_2",
                chapterId = 6,
                category = "Radiation Protection",
                question = "What is the legal maximum entrance skin dose rate in fluoroscopy in the UK?",
                answer = "100 mGy/min under any operating mode; remedial investigation is required if dose rate exceeds 50 mGy/min for a standard-sized patient.",
                keyPoints = listOf("Pulsed fluoroscopy (e.g. 7.5-15 p/s) halves the dose rate", "Last image hold allows review without continuous screening", "Magnification mode increases entrance skin dose rate"),
                sourceSection = "6.3 Automatic Brightness Control & DSA",
                printedPage = 95,
                pdfPage = 104
            ),

            // Chapter 7
            Flashcard(
                id = "FC7_1",
                chapterId = 7,
                category = "CT",
                question = "State the Hounsfield Unit (CT number) formula and typical values for air, fat, water, soft tissue, and dense bone.",
                answer = "CTn = 1000 × (ut - uw) / uw. Air = -1000 HU, Fat = -100 HU, Water = 0 HU, Muscle/soft tissue = +50 HU, Bone = +1000 HU.",
                keyPoints = listOf("Brain grey matter (40 HU) is denser than white matter (25-30 HU)", "Window width (WW) controls contrast; Window level (WL) controls brightness", "Partial volume effect averages densities in a single voxel"),
                sourceSection = "7.1 CT Numbers, Display & Equipment",
                printedPage = 104,
                pdfPage = 113
            ),
            Flashcard(
                id = "FC7_2",
                chapterId = 7,
                category = "CT",
                question = "How are CTDIw, CTDIvol, and DLP defined?",
                answer = "CTDIw = 1/3 Center + 2/3 Periphery. CTDIvol = CTDIw / Pitch. DLP = CTDIvol × Scan Length (mGy·cm).",
                keyPoints = listOf("Pitch > 1 stretches table feed, reducing CTDIvol and dose", "Effective dose E = DLP × (E/DLP) mSv", "Abdomen/pelvis factor ~ 0.017 mSv/(mGy·cm)"),
                sourceSection = "7.6 CT Artefacts & Dosimetry (CTDI/DLP)",
                printedPage = 119,
                pdfPage = 128
            ),

            // Chapter 8
            Flashcard(
                id = "FC8_1",
                chapterId = 8,
                category = "Ultrasound",
                question = "What is the formula for Axial Resolution in ultrasound and how can it be improved?",
                answer = "Axial Resolution = Spatial Pulse Length (SPL) / 2 = (n × lambda) / 2. Improved by higher frequency and heavier damping block.",
                keyPoints = listOf("Axial resolution is constant with depth", "Lateral resolution equals beam width and varies with depth", "Axial resolution is always superior to lateral resolution"),
                sourceSection = "8.4 Transducers, Resolution & Doppler",
                printedPage = 133,
                pdfPage = 142
            ),
            Flashcard(
                id = "FC8_2",
                chapterId = 8,
                category = "Ultrasound",
                question = "What causes Aliasing in Pulsed Wave Doppler and what is the Nyquist limit?",
                answer = "Aliasing occurs when the Doppler shift frequency exceeds half the Pulse Repetition Frequency (Nyquist limit: fD_max = PRF / 2).",
                keyPoints = listOf("Peak of waveform wraps around into reverse channel", "Corrected by increasing PRF, lowering frequency, or shifting baseline", "Doppler angle must be between 30 and 60 degrees"),
                sourceSection = "8.4 Transducers, Resolution & Doppler",
                printedPage = 138,
                pdfPage = 147
            ),

            // Chapter 9
            Flashcard(
                id = "FC9_1",
                chapterId = 9,
                category = "MRI",
                question = "What TR and TE combinations produce T1-weighted, T2-weighted, and Proton Density (PD) images?",
                answer = "T1W: Short TR (<500 ms) and Short TE (<20 ms). T2W: Long TR (>2000 ms) and Long TE (>80 ms). PDW: Long TR (>2000 ms) and Short TE (<20 ms).",
                keyPoints = listOf("T1: fat bright, water/CSF dark", "T2: water/CSF bright, fat intermediate", "T1 is spin-lattice recovery; T2 is spin-spin dephasing"),
                sourceSection = "9.3 Spin Echo, Sequences & Encoding",
                printedPage = 152,
                pdfPage = 161
            ),
            Flashcard(
                id = "FC9_2",
                chapterId = 9,
                category = "MRI",
                question = "What is the Inversion Time (TI) null point formula in Inversion Recovery (STIR and FLAIR)?",
                answer = "TI_null = T1 × ln(2) = 0.693 × T1.",
                keyPoints = listOf("STIR: TI ~ 150 ms at 1.5 T suppresses fat completely", "FLAIR: TI ~ 2000 ms at 1.5 T suppresses free CSF", "Gadolinium must NOT be used with STIR (it shortens T1 into fat range, causing lesion suppression)"),
                sourceSection = "9.3 Spin Echo, Sequences & Encoding",
                printedPage = 155,
                pdfPage = 164
            ),

            // Chapter 10
            Flashcard(
                id = "FC10_1",
                chapterId = 10,
                category = "Nuclear Medicine",
                question = "What are the physical characteristics of Technetium-99m and its generator parent Molybdenum-99?",
                answer = "Mo-99: T1/2 = 66 hours, beta-minus decay. Tc-99m: T1/2 = 6.0 hours, isomeric transition emitting 140 keV gamma ray.",
                keyPoints = listOf("Transient equilibrium occurs at ~23 hours", "Mo-99 breakthrough limit: <0.1% (<1 kBq Mo per 1 MBq Tc)", "Alumina chemical breakthrough limit: <10 ug/ml"),
                sourceSection = "10.1 Radioactive Decay & Generators",
                printedPage = 169,
                pdfPage = 178
            ),
            Flashcard(
                id = "FC10_2",
                chapterId = 10,
                category = "Nuclear Medicine",
                question = "What are the two 511 keV photons produced in PET and how are they detected?",
                answer = "A positron collides with an electron and annihilates, converting mass into two 511 keV gamma rays flying 180 degrees apart. They are detected by electronic coincidence within 6-12 nanoseconds without physical lead collimators.",
                keyPoints = listOf("18F half-life = 110 minutes", "Defines a Line of Response (LOR)", "Elimination of lead collimators yields 100x higher sensitivity than SPECT"),
                sourceSection = "10.3 The Gamma Camera, SPECT & PET",
                printedPage = 180,
                pdfPage = 189
            )
        )
    }

    fun getFlashcardsByChapter(chapterId: Int): List<Flashcard> = allFlashcards.filter { it.chapterId == chapterId }

    fun getFlashcardsByCategory(category: String): List<Flashcard> = allFlashcards.filter { it.category.equals(category, ignoreCase = true) }
}
