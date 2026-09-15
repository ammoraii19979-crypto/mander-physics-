package com.example.data.content

import com.example.data.model.Question
import com.example.data.model.QuestionType

object QuestionBank {
    val allQuestions: List<Question> by lazy {
        listOf(
            // --- CHAPTER 1: RADIATION PHYSICS ---
            Question(
                id = "Q1_1",
                chapterId = 1,
                sectionId = "1.1",
                conceptId = "1.1.1",
                type = QuestionType.TRUE_FALSE_5STEM,
                difficulty = "Core FRCR",
                stem = "Regarding the structure of the atom and electron binding energy:",
                options = listOf(
                    "A. The K-shell binding energy of tungsten is approximately 70 keV.",
                    "B. The valence shell can contain up to 18 electrons.",
                    "C. Ejection of an L-shell electron in tungsten requires incident energy of at least 70 keV.",
                    "D. The K-shell binding energy increases with atomic number Z.",
                    "E. In tungsten, characteristic Ka radiation has an energy of approximately 58 keV."
                ),
                tfAnswers = listOf(true, false, false, true, true),
                tfExplanations = listOf(
                    "True: Tungsten (Z=74) has a K-shell binding energy of 70 keV (Table 1.2).",
                    "False: The outermost (valence) shell can never contain more than 8 electrons.",
                    "False: L-shell binding energy in tungsten is only ~12 keV, so 12 keV is sufficient.",
                    "True: Binding energy is electrostatic attraction to nucleus, which increases sharply with atomic number Z.",
                    "True: Ka photon energy equals EK - EL = 70 - 12 = 58 keV."
                ),
                detailedExplanation = "According to Farr's Physics (Ch. 1, p. 1-2), Tungsten has Z=74, EK=70 keV, EL=12 keV, and EM=2 keV. Ka photon is emitted when an L-electron fills a K-shell vacancy (70 - 12 = 58 keV). Kb is 70 - 2 = 68 keV.",
                sourceChapter = "Chapter 1: Radiation Physics",
                sourceSection = "1.1 Structure of the Atom",
                printedPage = 2,
                pdfPage = 11,
                learningObjective = "Recall electron binding energies and calculate characteristic line energies for diagnostic target materials."
            ),
            Question(
                id = "Q1_2",
                chapterId = 1,
                sectionId = "1.3",
                conceptId = "1.3.1",
                type = QuestionType.SINGLE_BEST_ANSWER,
                difficulty = "Core FRCR",
                stem = "An X-ray tube with a tungsten target is operated at 65 kV. Which of the following statements regarding the emitted X-ray spectrum is correct?",
                options = listOf(
                    "A. The spectrum contains both Bremsstrahlung and characteristic K-lines at 58 keV.",
                    "B. Over 50% of the electrical energy is converted into X-rays.",
                    "C. No K-characteristic X-rays are emitted from the target.",
                    "D. The maximum photon energy in the beam is 58 keV.",
                    "E. The average photon energy is 65 keV."
                ),
                correctAnswerIndex = 2,
                detailedExplanation = "Tungsten K-shell binding energy is 70 keV. Electrons accelerated across a potential of 65 kV only have a maximum kinetic energy of 65 keV, which is insufficient to overcome the 70 keV binding energy. Therefore, no K-characteristic radiation is produced, and the beam is 100% Bremsstrahlung (Farr, p. 7). Efficiency of X-ray production is <1%, and maximum energy is 65 keV.",
                wrongOptionsExplanation = listOf(
                    "A: False; 65 kV is below the 70 kV threshold for Tungsten K-lines.",
                    "B: False; X-ray production efficiency is less than 1% in diagnostic radiology.",
                    "D: False; maximum photon energy equals tube kV = 65 keV.",
                    "E: False; average energy is approximately 1/3 to 1/2 of peak kV (around 30-35 keV)."
                ),
                sourceChapter = "Chapter 1: Radiation Physics",
                sourceSection = "1.3 Production of X-rays",
                printedPage = 7,
                pdfPage = 16,
                learningObjective = "Understand the voltage threshold for characteristic X-ray production."
            ),
            Question(
                id = "Q1_3",
                chapterId = 1,
                sectionId = "1.4",
                conceptId = "1.4.2",
                type = QuestionType.TRUE_FALSE_5STEM,
                difficulty = "Core FRCR",
                stem = "Regarding the interaction of X-rays with matter:",
                options = listOf(
                    "A. Compton scattering probability is directly proportional to the cube of atomic number (Z^3).",
                    "B. Photoelectric absorption predominates over Compton scatter in muscle above 30 keV.",
                    "C. The crossover energy where Compton and photoelectric interactions are equal is approximately 50 keV in bone.",
                    "D. In Compton scattering, the scattered photon always retains more than 80% of its initial energy in the diagnostic range.",
                    "E. Photoelectric absorption is proportional to 1 / E^3."
                ),
                tfAnswers = listOf(false, false, true, true, true),
                tfExplanations = listOf(
                    "False: Compton scatter is independent of atomic number Z; photoelectric absorption is proportional to Z^3.",
                    "False: In muscle/soft tissue, Compton scatter predominates above 30 keV.",
                    "True: In compact bone (Z=13.3), the crossover energy is ~50 keV (Farr, p. 14).",
                    "True: In the diagnostic range (30-150 keV), recoil electrons take <=20% energy; >80% remains with the scattered photon.",
                    "True: Photoelectric cross section tau is proportional to rho * Z^3 / E^3."
                ),
                detailedExplanation = "Farr's Physics (p. 13-14) details that Compton scatter is independent of Z and weakly dependent on energy (~1/E). Photoelectric absorption is proportional to Z^3 / E^3. Crossover energy is 30 keV in water/soft tissue, 50 keV in bone, 300 keV in iodine, and 500 keV in lead.",
                sourceChapter = "Chapter 1: Radiation Physics",
                sourceSection = "1.4 Interaction of X-rays with Matter",
                printedPage = 13,
                pdfPage = 22,
                learningObjective = "Compare Compton and photoelectric interaction dependencies and crossover energies."
            ),
            Question(
                id = "Q1_4",
                chapterId = 1,
                sectionId = "1.5",
                conceptId = "1.5.1",
                type = QuestionType.SINGLE_BEST_ANSWER,
                difficulty = "Foundation",
                stem = "According to UK radiological safety standards, what is the minimum total filtration required for a general diagnostic X-ray set operating above 70 kV?",
                options = listOf(
                    "A. 1.0 mm Al equivalent",
                    "B. 1.5 mm Al equivalent",
                    "C. 2.0 mm Al equivalent",
                    "D. 2.5 mm Al equivalent",
                    "E. 4.0 mm Al equivalent"
                ),
                correctAnswerIndex = 3,
                detailedExplanation = "General diagnostic equipment capable of operating above 70 kV must have a minimum total filtration (inherent + added) of at least 2.5 mm Aluminium equivalent (Farr, p. 16). Equipment operating up to 70 kV (e.g. dental) requires 1.5 mm Al eq.",
                sourceChapter = "Chapter 1: Radiation Physics",
                sourceSection = "1.5 Filtration",
                printedPage = 16,
                pdfPage = 25,
                learningObjective = "State statutory total filtration requirements in diagnostic radiography."
            ),
            Question(
                id = "Q1_5",
                chapterId = 1,
                sectionId = "1.6",
                conceptId = "1.6.1",
                type = QuestionType.TRUE_FALSE_5STEM,
                difficulty = "Core FRCR",
                stem = "Regarding Dose Area Product (DAP) meters:",
                options = listOf(
                    "A. DAP is measured in units of Gy·cm^2 or cGy·cm^2.",
                    "B. Moving the DAP chamber further from the X-ray tube increases the DAP reading.",
                    "C. DAP takes into account both radiation dose and field size.",
                    "D. Collimating the beam reduces DAP.",
                    "E. The DAP meter is positioned behind the patient."
                ),
                tfAnswers = listOf(true, false, true, true, false),
                tfExplanations = listOf(
                    "True: Standard units are Gy·cm^2, cGy·cm^2, or uGy·m^2.",
                    "False: DAP is invariant with distance from the source (inverse square decrease in dose exactly cancels square increase in area).",
                    "True: DAP = Air kerma × Cross-sectional area.",
                    "True: Tight collimation reduces beam area and therefore directly reduces DAP.",
                    "False: The DAP chamber is mounted on the tube housing collimator on the tube side, before the beam reaches the patient."
                ),
                detailedExplanation = "Farr's Physics (p. 18) describes the DAP meter as a transparent parallel-plate ionization chamber fitted to the collimator. Because dose decreases as 1/d^2 and area increases as d^2, the product DAP = dose × area is completely constant along the beam path.",
                sourceChapter = "Chapter 1: Radiation Physics",
                sourceSection = "1.6 Radiation Dosimetry",
                printedPage = 18,
                pdfPage = 27,
                learningObjective = "Explain DAP properties and the distance invariance principle."
            ),

            // --- CHAPTER 2: RADIATION HAZARDS AND PROTECTION ---
            Question(
                id = "Q2_1",
                chapterId = 2,
                sectionId = "2.1",
                conceptId = "2.1.1",
                type = QuestionType.SINGLE_BEST_ANSWER,
                difficulty = "Core FRCR",
                stem = "According to ICRP recommendations, which of the following tissues has the highest individual tissue weighting factor (wT = 0.20)?",
                options = listOf(
                    "A. Red Bone Marrow",
                    "B. Lung",
                    "C. Gonads",
                    "D. Stomach",
                    "E. Colon"
                ),
                correctAnswerIndex = 2,
                detailedExplanation = "In ICRP Publication 60 / Farr's Physics (p. 25, Table 2.3), the Gonads carry the highest weighting factor of 0.20 due to hereditary risk. Red bone marrow, lung, stomach, and colon each have wT = 0.12.",
                sourceChapter = "Chapter 2: Radiation Hazards and Protection",
                sourceSection = "2.1 Interactions with Tissue & Dosimetry",
                printedPage = 25,
                pdfPage = 34,
                learningObjective = "Identify tissue weighting factors used in calculating effective dose."
            ),
            Question(
                id = "Q2_2",
                chapterId = 2,
                sectionId = "2.3",
                conceptId = "2.3.1",
                type = QuestionType.TRUE_FALSE_5STEM,
                difficulty = "Core FRCR",
                stem = "Regarding the biological effects of ionizing radiation:",
                options = listOf(
                    "A. Radiation-induced cataract of the lens is a deterministic effect with a threshold dose of ~5 Gy.",
                    "B. For stochastic effects, the severity of the malignancy increases with increasing absorbed dose.",
                    "C. The risk of radiation-induced fatal cancer in the general population is approximately 5% per Sievert.",
                    "D. The fetus is most radiosensitive to structural malformations during the first 2 weeks post-conception.",
                    "E. Radon gas accounts for approximately half of the annual background radiation dose in the UK."
                ),
                tfAnswers = listOf(true, false, true, false, true),
                tfExplanations = listOf(
                    "True: Cataract is deterministic with a 5 Gy cumulative threshold (Table 2.1).",
                    "False: For stochastic effects, probability increases with dose; severity is independent of dose (all-or-nothing).",
                    "True: Nominal fatal cancer risk is 5% per Sv (1 in 20,000 per mSv).",
                    "False: Fetal malformations occur predominantly during major organogenesis (weeks 3 to 8). First 2 weeks is 'all-or-nothing' (resorption or normal survival).",
                    "True: Radon contributes ~1.3 mSv (49%) of the 2.2 mSv total average natural background in the UK."
                ),
                detailedExplanation = "Farr's Physics (p. 24-29) covers deterministic vs stochastic differences, fetal organogenesis vulnerability (weeks 3-8), and the UK natural background breakdown (Radon 49%, Terrestrial 14%, Cosmic 10%, Internal 12%).",
                sourceChapter = "Chapter 2: Radiation Hazards and Protection",
                sourceSection = "2.3 Biological Effects & Population Dose",
                printedPage = 26,
                pdfPage = 35,
                learningObjective = "Differentiate deterministic and stochastic radiation effects and organogenesis timing."
            ),
            Question(
                id = "Q2_3",
                chapterId = 2,
                sectionId = "2.5",
                conceptId = "2.5.1",
                type = QuestionType.SINGLE_BEST_ANSWER,
                difficulty = "Core FRCR",
                stem = "Under IRR99, what is the statutory annual effective dose limit for an unclassified employee aged 18 or over?",
                options = listOf(
                    "A. 1 mSv",
                    "B. 6 mSv",
                    "C. 15 mSv",
                    "D. 20 mSv",
                    "E. 50 mSv"
                ),
                correctAnswerIndex = 3,
                detailedExplanation = "Under IRR99 (Table 2.6, Farr p. 33), the annual effective dose limit for adult employees (>=18 yrs) is 20 mSv. The threshold for becoming a Classified Worker is 3/10th of this limit (>6 mSv/year). Public limit is 1 mSv/year.",
                sourceChapter = "Chapter 2: Radiation Hazards and Protection",
                sourceSection = "2.5 IRR99 and IRMER 2000 Legislation",
                printedPage = 33,
                pdfPage = 42,
                learningObjective = "Recall statutory occupational and public dose limits under IRR99."
            ),
            Question(
                id = "Q2_4",
                chapterId = 2,
                sectionId = "2.5",
                conceptId = "2.5.1",
                type = QuestionType.TRUE_FALSE_5STEM,
                difficulty = "Core FRCR",
                stem = "Regarding UK radiation legislation (IRR99 and IRMER 2000):",
                options = listOf(
                    "A. Dose limits apply to both occupationally exposed workers and patients undergoing diagnostic X-rays.",
                    "B. An IRMER Practitioner is responsible for the clinical justification of a medical radiation exposure.",
                    "C. An area must be designated as a Controlled Area if the instantaneous dose rate exceeds 7.5 uSv/h averaged over the working day.",
                    "D. A Radiation Protection Adviser (RPA) must be appointed in writing by the employer.",
                    "E. Diagnostic Reference Levels (DRLs) provide legal dose ceilings that must never be exceeded for an individual patient."
                ),
                tfAnswers = listOf(false, true, true, true, false),
                tfExplanations = listOf(
                    "False: Dose limits NEVER apply to patients undergoing medical exposures.",
                    "True: Under IRMER 2000, the Practitioner justifies and authorizes the exposure.",
                    "True: Dose rate > 7.5 uSv/h or dose likely to exceed 3/10 limit triggers Controlled Area designation.",
                    "True: Employer must appoint a qualified RPA (usually a medical physicist) in writing.",
                    "False: DRLs are benchmark investigation guides for standard-sized patients; they are not rigid individual limits."
                ),
                detailedExplanation = "Farr's Physics (p. 31-39) explains that IRR99 regulates staff/public safety with hard limits, while IRMER 2000 governs patient exposures through justification, optimization (ALARP), and DRL audits without dose caps.",
                sourceChapter = "Chapter 2: Radiation Hazards and Protection",
                sourceSection = "2.5 IRR99 and IRMER 2000 Legislation",
                printedPage = 34,
                pdfPage = 43,
                learningObjective = "Distinguish the regulatory frameworks and roles of IRR99 versus IRMER 2000."
            ),
            Question(
                id = "Q2_5",
                chapterId = 2,
                sectionId = "2.8",
                conceptId = "2.8.1",
                type = QuestionType.SINGLE_BEST_ANSWER,
                difficulty = "Foundation",
                stem = "In a fluoroscopy room, what is the primary source of ionizing radiation received by staff standing near the examination table?",
                options = listOf(
                    "A. Leakage through the X-ray tube housing",
                    "B. Compton scatter from the patient",
                    "C. Characteristic radiation from the lead apron",
                    "D. Secondary scatter from the ceiling and walls",
                    "E. Primary beam transmitted through the image intensifier"
                ),
                correctAnswerIndex = 1,
                detailedExplanation = "Scatter from the patient's body (predominantly Compton scatter) is by far the largest source of staff radiation dose, accounting for >98% of exposure. Tube housing leakage is legally restricted to <1 mGy/h at 1 meter and contributes <2% of scatter (Farr, p. 41).",
                sourceChapter = "Chapter 2: Radiation Hazards and Protection",
                sourceSection = "2.8 Practical Radiation Protection",
                printedPage = 41,
                pdfPage = 50,
                learningObjective = "Identify patient scatter as the dominant occupational hazard in radiology."
            ),

            // --- CHAPTER 3: IMAGING WITH X-RAYS ---
            Question(
                id = "Q3_1",
                chapterId = 3,
                sectionId = "3.4",
                conceptId = "3.4.1",
                type = QuestionType.TRUE_FALSE_5STEM,
                difficulty = "Core FRCR",
                stem = "Regarding antiscatter grids in diagnostic radiology:",
                options = listOf(
                    "A. Grid ratio is defined as the height of the lead strips divided by the width of the interspace material.",
                    "B. The Bucky factor is typically between 3 and 5.",
                    "C. Using an antiscatter grid decreases the patient radiation dose.",
                    "D. An inverted focused grid results in severe bilateral image cut-off with only a central exposed band.",
                    "E. Grids should be routinely used in neonatal chest radiography."
                ),
                tfAnswers = listOf(true, true, false, true, false),
                tfExplanations = listOf(
                    "True: Grid ratio = d / w (strip depth / interspace width).",
                    "True: Bucky factor (Grid factor) = 3 to 5 (Farr, p. 55).",
                    "False: Grids absorb scatter and some primary beam, requiring mAs to be increased 3-5x, which INCREASES patient dose.",
                    "True: Placing a focused grid upside down causes lead strips to diverge opposite to the beam, absorbing almost all rays except in the dead center.",
                    "False: Grids are omitted in neonates and infants because small anatomy produces very little scatter, sparing unnecessary dose."
                ),
                detailedExplanation = "Farr's Physics (p. 54-57) covers grid construction, grid ratio (typically 8:1), grid factor (3-5x dose penalty), and reasons for grid cut-off.",
                sourceChapter = "Chapter 3: Imaging with X-rays",
                sourceSection = "3.4 Scattered Radiation & Grids",
                printedPage = 55,
                pdfPage = 64,
                learningObjective = "Explain grid ratio, Bucky factor, and grid cut-off mechanisms."
            ),
            Question(
                id = "Q3_2",
                chapterId = 3,
                sectionId = "3.6",
                conceptId = "3.6.1",
                type = QuestionType.SINGLE_BEST_ANSWER,
                difficulty = "Core FRCR",
                stem = "According to the Line Focus Principle, angling the X-ray tube anode target from 20 degrees down to 10 degrees results in:",
                options = listOf(
                    "A. Increased effective focal spot size and increased maximum field coverage",
                    "B. Decreased effective focal spot size and decreased maximum field coverage",
                    "C. Decreased heat rating and increased geometric unsharpness",
                    "D. Elimination of the anode heel effect",
                    "E. Increased geometric unsharpness"
                ),
                correctAnswerIndex = 1,
                detailedExplanation = "Effective focal spot = Actual track length × sin(theta). Steeper anode angles (e.g. 10 deg) reduce effective focal spot size (improving geometric sharpness) and allow higher instantaneous heat rating, BUT narrow the maximum usable beam field coverage due to the anode heel effect cutting off the anode edge of the cone (Farr, p. 60).",
                sourceChapter = "Chapter 3: Imaging with X-rays",
                sourceSection = "3.6 Unsharpness & X-ray Tube Physics",
                printedPage = 60,
                pdfPage = 69,
                learningObjective = "Understand the trade-offs of anode angle in line focus geometry."
            ),

            // --- CHAPTER 4: FILM-SCREEN & MAMMOGRAPHY ---
            Question(
                id = "Q4_1",
                chapterId = 4,
                sectionId = "4.1",
                conceptId = "4.1.1",
                type = QuestionType.SINGLE_BEST_ANSWER,
                difficulty = "Foundation",
                stem = "If an X-ray film transmits exactly 1% of the incident light from a viewing illuminator, what is its Optical Density (D)?",
                options = listOf(
                    "A. 0.5",
                    "B. 1.0",
                    "C. 1.5",
                    "D. 2.0",
                    "E. 3.0"
                ),
                correctAnswerIndex = 3,
                detailedExplanation = "Optical Density D = log10(I0 / It). For 1% transmission, I0 / It = 100 / 1 = 100. log10(100) = 2.0 (Farr, p. 66). D=1 corresponds to 10% transmission; D=3 corresponds to 0.1% transmission.",
                sourceChapter = "Chapter 4: Film-Screen Radiography",
                sourceSection = "4.1 Film-Screen Image Formation & H&D Curve",
                printedPage = 66,
                pdfPage = 75,
                learningObjective = "Calculate optical density from light transmission fraction."
            ),
            Question(
                id = "Q4_2",
                chapterId = 4,
                sectionId = "4.3",
                conceptId = "4.3.1",
                type = QuestionType.TRUE_FALSE_5STEM,
                difficulty = "Core FRCR",
                stem = "Regarding film-screen mammography equipment and physics:",
                options = listOf(
                    "A. Molybdenum targets emit characteristic Ka radiation at 17.5 keV.",
                    "B. A Rhodium filter is appropriate when imaging thick, dense breasts.",
                    "C. Mammography film cassettes use two intensifying screens with double-emulsion film.",
                    "D. The single screen is positioned on the distal (back) side of the film.",
                    "E. Firm breast compression reduces geometric unsharpness and patient dose."
                ),
                tfAnswers = listOf(true, true, false, true, true),
                tfExplanations = listOf(
                    "True: Mo characteristic lines are Ka = 17.5 keV and Kb = 19.6 keV (p. 76).",
                    "True: Rhodium (K-edge 23.2 keV) transmits higher energy photons suited for dense breasts.",
                    "False: Mammography uses a SINGLE screen and SINGLE-emulsion film to eliminate crossover and parallax blur.",
                    "True: Screen is placed on the distal side so light generation occurs adjacent to the single emulsion.",
                    "True: Compression thins tissue, reducing scatter, dose, and brings structures closer to the film (lowering Ug)."
                ),
                detailedExplanation = "Farr's Physics (p. 74-77) details mammography technical choices: Mo/Rh targets and filters, 25-30 kV tube potentials, Beryllium windows, and single-screen distal geometry achieving >=15 lp/mm spatial resolution.",
                sourceChapter = "Chapter 4: Film-Screen Radiography",
                sourceSection = "4.3 Screens, Unsharpness & Mammography",
                printedPage = 76,
                pdfPage = 85,
                learningObjective = "Detail specialized mammographic target-filter combinations and single-screen geometry."
            ),

            // --- CHAPTER 5: DIGITAL RADIOGRAPHY ---
            Question(
                id = "Q5_1",
                chapterId = 5,
                sectionId = "5.3",
                conceptId = "5.3.1",
                type = QuestionType.TRUE_FALSE_5STEM,
                difficulty = "Core FRCR",
                stem = "Regarding digital radiography detectors and principles:",
                options = listOf(
                    "A. Computed Radiography (CR) plates utilize photostimulable phosphor BaFX:Eu.",
                    "B. In CR readout, stimulation by blue laser light releases red luminescence.",
                    "C. Direct Digital Radiography (DDR) uses amorphous selenium (a-Se) as a photoconductor.",
                    "D. The Detective Quantum Efficiency (DQE) of DDR flat-panel systems is higher than film-screen.",
                    "E. Digital detectors have a narrow, S-shaped dynamic range similar to photographic film."
                ),
                tfAnswers = listOf(true, false, true, true, false),
                tfExplanations = listOf(
                    "True: Barium fluorohalide doped with europium (BaFBr:Eu) is the CR storage phosphor.",
                    "False: RED laser light (~633 nm) stimulates the release of BLUE luminescent light (~400 nm).",
                    "True: a-Se directly converts X-rays into charge without an intermediate light conversion.",
                    "True: DDR DQE reaches ~65%, compared with ~30% for film-screen and CR (Farr, p. 86).",
                    "False: Digital detectors possess a wide linear dynamic range spanning over 10,000:1."
                ),
                detailedExplanation = "Farr's Physics (p. 83-87) covers photostimulable luminescence, a-Se direct vs CsI indirect flat panel architecture, and DQE advantages in dose efficiency.",
                sourceChapter = "Chapter 5: Digital Radiography",
                sourceSection = "5.3 Computed Radiography (CR) & DDR",
                printedPage = 84,
                pdfPage = 93,
                learningObjective = "Compare CR, Indirect DR, and Direct DR detector mechanisms and DQE."
            ),

            // --- CHAPTER 6: FLUOROSCOPY ---
            Question(
                id = "Q6_1",
                chapterId = 6,
                sectionId = "6.1",
                conceptId = "6.1.1",
                type = QuestionType.SINGLE_BEST_ANSWER,
                difficulty = "Core FRCR",
                stem = "An image intensifier has an input screen diameter of 30 cm and an output screen diameter of 3 cm. The flux gain is 50. What is the total brightness gain of the tube?",
                options = listOf(
                    "A. 100",
                    "B. 500",
                    "C. 1,000",
                    "D. 5,000",
                    "E. 50,000"
                ),
                correctAnswerIndex = 3,
                detailedExplanation = "Minification gain = (D_in / D_out)^2 = (30 / 3)^2 = 10^2 = 100. Total Brightness Gain = Minification Gain × Flux Gain = 100 × 50 = 5,000 (Farr, p. 92).",
                sourceChapter = "Chapter 6: Fluoroscopy",
                sourceSection = "6.1 The Image Intensifier",
                printedPage = 92,
                pdfPage = 101,
                learningObjective = "Calculate minification gain, flux gain, and total brightness gain."
            ),
            Question(
                id = "Q6_2",
                chapterId = 6,
                sectionId = "6.3",
                conceptId = "6.3.1",
                type = QuestionType.TRUE_FALSE_5STEM,
                difficulty = "Core FRCR",
                stem = "Regarding fluoroscopy dose and Automatic Brightness Control (ABC):",
                options = listOf(
                    "A. In the UK, the maximum legal entrance surface dose rate for fluoroscopy is 100 mGy/min.",
                    "B. Selecting magnification mode on an image intensifier reduces patient entrance skin dose.",
                    "C. In Digital Subtraction Angiography (DSA), logarithmic transformation is performed before image subtraction.",
                    "D. Pulsing the fluoroscopy beam at 15 pulses/s reduces dose compared to continuous 30 pulses/s.",
                    "E. An undercouch X-ray tube configuration gives higher operator eye doses than an overcouch tube."
                ),
                tfAnswers = listOf(true, false, true, true, false),
                tfExplanations = listOf(
                    "True: 100 mGy/min is the statutory upper limit (Farr, p. 95).",
                    "False: Magnification decreases minification gain; ABC compensates by boosting tube output, which INCREASES entrance skin dose.",
                    "True: Logarithmic conversion is mandatory because X-ray attenuation is exponential.",
                    "True: Pulsing significantly reduces exposure time and total patient dose.",
                    "False: Undercouch tubes direct intense entrance scatter downwards towards the feet, protecting operator eyes and thyroid."
                ),
                detailedExplanation = "Farr's Physics (p. 94-98) covers ABC curves, magnification penalties, pulsed fluoroscopy benefits, and radiation geometry.",
                sourceChapter = "Chapter 6: Fluoroscopy",
                sourceSection = "6.3 Automatic Brightness Control & DSA",
                printedPage = 95,
                pdfPage = 104,
                learningObjective = "Recognize fluoroscopic dose limits and dose reduction strategies."
            ),

            // --- CHAPTER 7: COMPUTED TOMOGRAPHY ---
            Question(
                id = "Q7_1",
                chapterId = 7,
                sectionId = "7.1",
                conceptId = "7.1.1",
                type = QuestionType.SINGLE_BEST_ANSWER,
                difficulty = "Foundation",
                stem = "In Computed Tomography, what is the CT number (Hounsfield Unit) of pure water and air respectively?",
                options = listOf(
                    "A. 0 HU and -500 HU",
                    "B. +100 HU and -1000 HU",
                    "C. 0 HU and -1000 HU",
                    "D. -100 HU and -1000 HU",
                    "E. +50 HU and 0 HU"
                ),
                correctAnswerIndex = 2,
                detailedExplanation = "By mathematical definition, CTn = 1000 × (u_tissue - u_water) / u_water. For water, u_tissue = u_water, so CTn = 0 HU. For air, u_tissue ~ 0, so CTn = -1000 HU (Farr, p. 104).",
                sourceChapter = "Chapter 7: Computed Tomography",
                sourceSection = "7.1 CT Numbers, Display & Equipment",
                printedPage = 104,
                pdfPage = 113,
                learningObjective = "Recall definition and baseline Hounsfield values for water and air."
            ),
            Question(
                id = "Q7_2",
                chapterId = 7,
                sectionId = "7.6",
                conceptId = "7.6.1",
                type = QuestionType.TRUE_FALSE_5STEM,
                difficulty = "Core FRCR",
                stem = "Regarding CT dosimetry and reconstruction:",
                options = listOf(
                    "A. Weighted CTDI (CTDIw) is calculated as: CTDIw = 1/3 CTDI_center + 2/3 CTDI_periphery.",
                    "B. Increasing beam pitch above 1.0 reduces the patient radiation dose.",
                    "C. Dose-Length Product (DLP) has units of mGy·cm.",
                    "D. The E/DLP conversion coefficient to calculate effective dose is highest for head CT.",
                    "E. Ring artefacts in 3rd-generation CT scanners are caused by a malfunctioning detector channel."
                ),
                tfAnswers = listOf(true, true, true, false, true),
                tfExplanations = listOf(
                    "True: CTDIw = 1/3 center + 2/3 periphery (Farr, p. 119).",
                    "True: CTDIvol = CTDIw / Pitch, so pitch > 1 reduces CTDIvol and dose proportionally.",
                    "True: DLP = CTDIvol × scan length L (units mGy·cm).",
                    "False: E/DLP factor is LOWEST for head CT (0.0023) and highest for chest/abdomen/pelvis (~0.017-0.018).",
                    "True: In 3rd-generation rotate-rotate scanners, a miscalibrated detector element traces a circle (ring artefact)."
                ),
                detailedExplanation = "Farr's Physics (p. 116-121) covers CTDI, pitch, DLP, and artefact generation including ring and cupping artefacts.",
                sourceChapter = "Chapter 7: Computed Tomography",
                sourceSection = "7.6 CT Artefacts & Dosimetry (CTDI/DLP)",
                printedPage = 119,
                pdfPage = 128,
                learningObjective = "Calculate CTDIw, CTDIvol, and DLP, and identify CT artefact causes."
            ),

            // --- CHAPTER 8: ULTRASOUND ---
            Question(
                id = "Q8_1",
                chapterId = 8,
                sectionId = "8.1",
                conceptId = "8.1.1",
                type = QuestionType.SINGLE_BEST_ANSWER,
                difficulty = "Foundation",
                stem = "What is the average calibrated speed of sound in human soft tissue assumed by diagnostic ultrasound scanners?",
                options = listOf(
                    "A. 330 m/s",
                    "B. 1450 m/s",
                    "C. 1540 m/s",
                    "D. 1580 m/s",
                    "E. 4080 m/s"
                ),
                correctAnswerIndex = 2,
                detailedExplanation = "Diagnostic ultrasound scanners assume an average speed of sound in soft tissue of exactly 1540 m/s (1.54 mm/us) (Farr, p. 128, Table 8.1). Air is 330 m/s, fat is 1450 m/s, muscle is 1580 m/s, and skull bone is 4080 m/s.",
                sourceChapter = "Chapter 8: Imaging with Ultrasound",
                sourceSection = "8.1 Sound Waves, Impedance & Reflection",
                printedPage = 128,
                pdfPage = 137,
                learningObjective = "State standard sound velocity in biological tissues."
            ),
            Question(
                id = "Q8_2",
                chapterId = 8,
                sectionId = "8.4",
                conceptId = "8.4.1",
                type = QuestionType.TRUE_FALSE_5STEM,
                difficulty = "Core FRCR",
                stem = "Regarding ultrasound transducers and Doppler physics:",
                options = listOf(
                    "A. The piezoelectric crystal thickness is equal to half the wavelength (lambda / 2) in the crystal.",
                    "B. Axial resolution equals the Spatial Pulse Length (SPL) divided by 2.",
                    "C. Lateral resolution is constant throughout the entire imaging depth.",
                    "D. In Doppler ultrasound, no Doppler shift is detected when the beam is at 90 degrees to blood flow.",
                    "E. Aliasing in pulsed Doppler occurs when the Doppler shift frequency exceeds half the Pulse Repetition Frequency (PRF / 2)."
                ),
                tfAnswers = listOf(true, true, false, true, true),
                tfExplanations = listOf(
                    "True: Resonant crystal thickness = lambda / 2.",
                    "True: Axial resolution = SPL / 2 (Farr, p. 133).",
                    "False: Lateral resolution equals beam width, which varies with depth (best at focal zone).",
                    "True: Doppler shift is proportional to cos(theta); cos(90 deg) = 0, so zero shift is measured.",
                    "True: Nyquist limit = PRF / 2; shifts exceeding this alias into reverse channel."
                ),
                detailedExplanation = "Farr's Physics (p. 131-138) covers crystal resonance, matching layers (lambda/4), axial/lateral resolution, and Doppler physics.",
                sourceChapter = "Chapter 8: Imaging with Ultrasound",
                sourceSection = "8.4 Transducers, Resolution & Doppler",
                printedPage = 134,
                pdfPage = 143,
                learningObjective = "Relate transducer construction to resolution limits and Doppler angle constraints."
            ),

            // --- CHAPTER 9: MAGNETIC RESONANCE IMAGING ---
            Question(
                id = "Q9_1",
                chapterId = 9,
                sectionId = "9.1",
                conceptId = "9.1.1",
                type = QuestionType.SINGLE_BEST_ANSWER,
                difficulty = "Core FRCR",
                stem = "Given that the gyromagnetic ratio (gamma / 2pi) of 1H hydrogen is 42.6 MHz/Tesla, what is the Larmor precession frequency in a 1.5 Tesla clinical MRI scanner?",
                options = listOf(
                    "A. 21.3 MHz",
                    "B. 42.6 MHz",
                    "C. 63.9 MHz",
                    "D. 85.2 MHz",
                    "E. 127.8 MHz"
                ),
                correctAnswerIndex = 2,
                detailedExplanation = "Larmor frequency f0 = (gamma / 2pi) × B0 = 42.6 MHz/T × 1.5 T = 63.9 MHz (Farr, p. 147). At 3.0 Tesla, it is 127.8 MHz.",
                sourceChapter = "Chapter 9: Magnetic Resonance Imaging",
                sourceSection = "9.1 Principles of NMR & Relaxation",
                printedPage = 147,
                pdfPage = 156,
                learningObjective = "Calculate Larmor precession frequency using the Larmor equation."
            ),
            Question(
                id = "Q9_2",
                chapterId = 9,
                sectionId = "9.3",
                conceptId = "9.3.1",
                type = QuestionType.TRUE_FALSE_5STEM,
                difficulty = "Core FRCR",
                stem = "Regarding MRI pulse sequences and relaxation:",
                options = listOf(
                    "A. T1-weighted images require a short Repetition Time (TR) and short Echo Time (TE).",
                    "B. Free water and CSF appear hyperintense (bright) on T2-weighted images.",
                    "C. A 180-degree refocusing pulse reverses true T2 spin-spin dephasing.",
                    "D. STIR sequences suppress fat signal by choosing an inversion time TI where fat longitudinal magnetization passes through zero.",
                    "E. Gadolinium-based contrast agents dramatically shorten T1 relaxation time."
                ),
                tfAnswers = listOf(true, true, false, true, true),
                tfExplanations = listOf(
                    "True: T1W requires short TR (<500 ms) and short TE (<20 ms).",
                    "True: Water has very long T2 (~1000 ms), retaining transverse signal and appearing bright on T2W.",
                    "False: The 180 deg pulse only refocuses static field inhomogeneities (T2*); true spin-spin dephasing (T2) is random and irreversible.",
                    "True: TI_null = T1 × ln(2) ~ 150 ms at 1.5T for fat.",
                    "True: Paramagnetic Gadolinium shortens T1, causing intense hyperintensity on T1W images."
                ),
                detailedExplanation = "Farr's Physics (p. 148-158) covers spin echo timing, relaxation curves, STIR fat suppression, and Gadolinium mechanism.",
                sourceChapter = "Chapter 9: Magnetic Resonance Imaging",
                sourceSection = "9.3 Spin Echo, Sequences & Encoding",
                printedPage = 152,
                pdfPage = 161,
                learningObjective = "Predict image contrast based on TR, TE, and inversion time settings."
            ),

            // --- CHAPTER 10: NUCLEAR MEDICINE & PET ---
            Question(
                id = "Q10_1",
                chapterId = 10,
                sectionId = "10.1",
                conceptId = "10.1.1",
                type = QuestionType.SINGLE_BEST_ANSWER,
                difficulty = "Core FRCR",
                stem = "In a Mo-99 / Tc-99m radionuclide generator, what is the physical half-life of parent Mo-99 and daughter Tc-99m respectively?",
                options = listOf(
                    "A. 6 hours and 66 hours",
                    "B. 66 hours and 6.0 hours",
                    "C. 23 hours and 6.0 hours",
                    "D. 78 hours and 13 hours",
                    "E. 66 days and 6.0 hours"
                ),
                correctAnswerIndex = 1,
                detailedExplanation = "Parent Mo-99 has a physical half-life of 66 hours (2.75 days) and beta-decays to metastable Tc-99m, which has a physical half-life of 6.0 hours and emits a 140 keV gamma photon (Farr, p. 169). Peak activity occurs at 23 hours.",
                sourceChapter = "Chapter 10: Nuclear Medicine & Gamma Imaging",
                sourceSection = "10.1 Radioactive Decay & Generators",
                printedPage = 169,
                pdfPage = 178,
                learningObjective = "Recall physical half-lives and decay modes of Mo-99 and Tc-99m."
            ),
            Question(
                id = "Q10_2",
                chapterId = 10,
                sectionId = "10.3",
                conceptId = "10.3.1",
                type = QuestionType.TRUE_FALSE_5STEM,
                difficulty = "Core FRCR",
                stem = "Regarding Positron Emission Tomography (PET):",
                options = listOf(
                    "A. Positron annihilation produces two 511 keV gamma photons travelling in opposite directions (180 degrees apart).",
                    "B. PET scanners require physical lead collimator septa similar to an Anger gamma camera.",
                    "C. Fluorine-18 has a radioactive half-life of approximately 110 minutes.",
                    "D. Electronic coincidence detection locates annihilation events along a Line of Response (LOR).",
                    "E. 18F-FDG accumulates preferentially in tissues with high rates of glycolysis."
                ),
                tfAnswers = listOf(true, false, true, true, true),
                tfExplanations = listOf(
                    "True: Mass of e+ and e- converts into two 511 keV photons at 180 degrees.",
                    "False: PET uses electronic coincidence timing without lead collimators, giving orders-of-magnitude higher sensitivity.",
                    "True: F-18 half-life = 110 minutes (Table 10.1).",
                    "True: Simultaneous arrival of twin 511 keV photons within nanoseconds defines the Line of Response.",
                    "True: FDG is a glucose analog phosphorylated and trapped in hypermetabolic malignant tumor cells."
                ),
                detailedExplanation = "Farr's Physics (p. 179-183) covers positron physics, coincidence detection, 511 keV annihilation photons, and 18F-FDG metabolic oncology principles.",
                sourceChapter = "Chapter 10: Nuclear Medicine & Gamma Imaging",
                sourceSection = "10.3 The Gamma Camera, SPECT & PET",
                printedPage = 180,
                pdfPage = 189,
                learningObjective = "Explain positron annihilation coincidence detection in PET."
            )
        )
    }

    fun getQuestionsByChapter(chapterId: Int): List<Question> = allQuestions.filter { it.chapterId == chapterId }

    fun getQuestionsBySection(sectionId: String): List<Question> = allQuestions.filter { it.sectionId == sectionId }

    fun getQuestionsByConcept(conceptId: String): List<Question> = allQuestions.filter { it.conceptId == conceptId }

    val unmappedQuestions: List<Question> by lazy {
        listOf(
            Question(
                id = "Q_UNMAPPED_1",
                chapterId = 0,
                sectionId = "UNMAPPED",
                conceptId = "UNMAPPED_1",
                type = QuestionType.SINGLE_BEST_ANSWER,
                difficulty = "Core FRCR",
                stem = "In diagnostic radiography and radioprotection, which factor has the greatest influence on reducing patient skin entrance surface dose without compromising image quality?",
                options = listOf(
                    "A. Increasing tube current-time product (mAs)",
                    "B. Increasing tube voltage (kVp) while reducing mAs accordingly",
                    "C. Removing the added aluminum filtration",
                    "D. Reducing source-to-image receptor distance (SID)",
                    "E. Increasing field size collimation"
                ),
                correctAnswerIndex = 1,
                detailedExplanation = "Increasing kVp increases beam penetration (higher HVL), allowing a significant reduction in mAs. Since patient skin entrance dose is proportional to mAs and roughly proportional to kVp^2, the reduction in mAs easily outweighs the kVp increase, reducing entrance skin dose. Added filtration also reduces skin dose; removing it would increase dose.",
                sourceChapter = "Unmapped Questions",
                sourceSection = "General Diagnostic Radiology Principles",
                printedPage = 0,
                pdfPage = 0,
                learningObjective = "Identify exposure optimization techniques for radiation dose reduction."
            )
        )
    }
}
