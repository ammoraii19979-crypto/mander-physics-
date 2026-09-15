package com.example.data.content

import com.example.data.model.AcquireContent
import com.example.data.model.Concept
import com.example.data.model.DefinitionItem
import com.example.data.model.PrimeContent
import com.example.data.model.RevisionTable
import com.example.data.model.Section

object Chapter10Content {
    val sections: List<Section> by lazy {
        listOf(
            Section(
                id = "10.1",
                chapterId = 10,
                number = "10.1",
                title = "Radioactive Decay & Generators",
                printedPage = 167,
                pdfPage = 176,
                concepts = listOf(
                    Concept(
                        id = "10.1.1",
                        chapterId = 10,
                        sectionId = "10.1",
                        title = "Decay Modes, Half-Life & Mo-99/Tc-99m Generator",
                        printedPage = 167,
                        pdfPage = 176,
                        prime = PrimeContent(
                            structuredNotes = listOf(
                                "Radioactivity is the spontaneous disintegration of unstable nuclei with emission of ionizing particles or gamma photons.",
                                "Activity (A): disintegrations per second. SI unit is the Becquerel (1 Bq = 1 dps). Historic unit: 1 Curie (Ci) = 3.7 × 10^10 Bq = 37 GBq.",
                                "Decay Law: exponential decay: N(t) = N0 × e^(-lambda × t); Activity A(t) = A0 × e^(-lambda × t). Decay constant lambda = 0.693 / T1/2.",
                                "Effective half-life (T_eff): combines physical radioactive half-life (T_p) and biological elimination half-life (T_b): 1 / T_eff = 1 / T_p + 1 / T_b.",
                                "Ideal diagnostic radionuclide properties: 1. Pure gamma emitter (no alpha or beta particles); 2. Gamma energy 100-250 keV (Tc-99m 140 keV matches NaI crystal); 3. Short physical half-life matching exam (few hours); 4. Inexpensive, carrier-free, chemically versatile.",
                                "Radionuclide production: Cyclotrons produce neutron-poor isotopes decaying via positron emission or electron capture (F-18, C-11, I-123, Ga-67, Tl-201); Reactors produce neutron-rich isotopes decaying via beta-minus (Mo-99, I-131, Xe-133).",
                                "Mo-99/Tc-99m Generator: Parent Mo-99 (T1/2 = 66 hours) is bound as molybdate to an aluminium oxide (alumina) column. Mo-99 beta-minus decays to Tc-99m (T1/2 = 6.0 hours, 140 keV gamma).",
                                "Transient equilibrium: reached after ~23 hours, when daughter Tc-99m activity reaches a maximum and thereafter decays with the apparent half-life of parent Mo-99.",
                                "Elution ('milking'): column is flushed with 0.9% sterile saline. Technetium forms pertechnetate (TcO4-), which has low affinity for alumina and elutes in saline, leaving Mo-99 behind.",
                                "Generator Quality Assurance: 1. Molybdenum breakthrough test (gamma assay in lead canister: limit <0.1% or <1 kBq Mo-99 per MBq Tc-99m); 2. Aluminium chemical breakthrough test (aurin tricarboxylic acid color strip: limit <10 ug Al per ml)."
                            ),
                            keyConcepts = listOf("Decay Law & Half-Life", "Effective Half-Life Formula", "Ideal Radionuclide Criteria", "Mo-99 / Tc-99m Generator", "Transient Equilibrium (23h)", "Molybdenum & Aluminium Breakthrough"),
                            definitions = listOf(
                                DefinitionItem("Becquerel (Bq)", "The SI unit of radioactivity, defined as one nuclear disintegration per second (1 Bq = 1 dps)."),
                                DefinitionItem("Effective Half-Life (T_eff)", "The time required for an administered radionuclide in the body to decrease to half its initial activity due to combined physical decay and biological clearance: 1/T_eff = 1/T_p + 1/T_b."),
                                DefinitionItem("Transient Equilibrium", "The steady-state condition reached in a generator when the parent half-life is slightly longer than the daughter half-life (Mo-99 66h vs Tc-99m 6h), peaking at approximately 23 hours.")
                            ),
                            equations = listOf(
                                "A(t) = A0 × e^(-lambda × t)",
                                "T1/2 = 0.693 / lambda",
                                "1 / T_eff = 1 / T_p + 1 / T_b -> T_eff = (T_p × T_b) / (T_p + T_b)",
                                "1 Ci = 3.7 × 10^10 Bq = 37 GBq"
                            ),
                            tables = listOf(
                                RevisionTable(
                                    title = "Table 10.1: Common Diagnostic Radiopharmaceuticals",
                                    headers = listOf("Radionuclide", "Production Source", "Half-Life (T1/2)", "Principal Gamma Energy", "Primary Clinical Role"),
                                    rows = listOf(
                                        listOf("Technetium-99m", "Mo-99 Generator", "6.0 hours", "140 keV (90% yield)", "Bone, renal, cardiac, V/Q lung"),
                                        listOf("Fluorine-18", "Cyclotron", "110 minutes", "511 keV (annihilation)", "18F-FDG Oncology PET/CT"),
                                        listOf("Iodine-123", "Cyclotron", "13.2 hours", "159 keV", "Thyroid uptake, MIBG neuroendocrine"),
                                        listOf("Gallium-67", "Cyclotron", "78 hours", "93, 185, 300 keV", "Infection and lymphoma imaging"),
                                        listOf("Indium-111", "Cyclotron", "2.8 days", "171, 245 keV", "White blood cell & octreotide imaging"),
                                        listOf("Thallium-201", "Cyclotron", "73 hours", "68 - 80 keV (Hg X-rays)", "Myocardial perfusion imaging")
                                    )
                                )
                            ),
                            whatYouMustUnderstand = listOf(
                                "Tc-99m is the workhorse of nuclear medicine because its 140 keV gamma ray easily escapes the body, is efficiently stopped by NaI crystals, and has zero beta particle dose.",
                                "Molybdenum breakthrough must be tested on EVERY elution because Mo-99 is a beta-emitter with 66h half-life that would deliver massive radiation dose to liver and bone marrow if injected."
                            ),
                            whatYouShouldMemorize = listOf(
                                "Tc-99m: T1/2 = 6.0 hours, 140 keV gamma (89% abundance).",
                                "Mo-99: T1/2 = 66 hours, beta-minus emitter.",
                                "Molybdenum breakthrough limit: <0.1% (or 1 kBq Mo per 1 MBq Tc). Alumina limit: <10 ug/ml."
                            ),
                            highYieldFacts = listOf(
                                "Maximal Tc-99m activity in a generator occurs at 23 hours after previous elution (transient equilibrium).",
                                "Tc-99m decays to Tc-99 (ground state) which is virtually non-radioactive (T1/2 = 210,000 years)."
                            ),
                            chapterSummary = listOf(
                                "Radionuclides decay exponentially; the Mo-99/Tc-99m generator provides pure 140 keV gamma rays under transient equilibrium for optimal patient imaging."
                            ),
                            learningObjectives = listOf(
                                "Calculate effective half-life from physical and biological half-lives.",
                                "Explain the operation, elution chemistry, and quality control tests of a Mo-99/Tc-99m generator."
                            )
                        ),
                        acquire = AcquireContent(
                            simpleExplanation = "A technetium generator is a radioactive coffee maker: parent Molybdenum (the coffee grounds) stays trapped in an alumina filter column. Saline pours through, dissolving only the fresh daughter Technetium into the cup for patient injection.",
                            stepByStepMechanism = listOf(
                                "1. Mo-99 (half-life 66h) adheres tightly to alumina beads inside a sterile lead-shielded column.",
                                "2. Mo-99 beta-decays into Tc-99m (half-life 6h).",
                                "3. Tc-99m accumulation peaks at 23 hours (transient equilibrium).",
                                "4. An evacuated vial draws sterile saline across the column.",
                                "5. Tc-99m elutes as sodium pertechnetate (Na 99mTcO4); Mo-99 remains bound.",
                                "6. Eluate is tested for Mo-99 breakthrough in a lead pot dose calibrator (<0.1% limit).",
                                "7. Eluate is radiolabelled with pharmaceutical kits (e.g. MDP for bone, DTPA for kidneys)."
                            ),
                            whyItHappens = "Differences in chemical oxidation states: MoO4(2-) binds tightly to positive Al2O3, whereas TcO4(-) has weak single charge and washes off easily in saline.",
                            clinicalRelevance = "Tc-99m MDP bone scans detect skeletal metastases months before osteolytic changes become visible on plain X-rays.",
                            commonMisconception = "Misconception: A generator is exhausted after 6 hours because Tc-99m has a 6-hour half-life. Reality: A generator lasts 1 to 2 weeks because the long-lived parent Mo-99 (66h half-life) continuously manufactures new Tc-99m inside the column.",
                            analogy = "An apple tree: apples fall off every 6 hours, but as long as the parent tree lives (66h half-life), new apples keep growing on the branches every morning.",
                            imageQualityAndDoseImpact = "Pure 140 keV gamma emission minimizes patient absorbed dose because no damaging particulate beta electrons are emitted into tissue.",
                            interactiveDiagramType = "GENERATOR_EQUILIBRIUM"
                        )
                    )
                )
            ),
            Section(
                id = "10.3",
                chapterId = 10,
                number = "10.3",
                title = "The Gamma Camera, SPECT & PET",
                printedPage = 172,
                pdfPage = 181,
                concepts = listOf(
                    Concept(
                        id = "10.3.1",
                        chapterId = 10,
                        sectionId = "10.3",
                        title = "Anger Gamma Camera, PHA, SPECT & PET/CT",
                        printedPage = 172,
                        pdfPage = 181,
                        prime = PrimeContent(
                            structuredNotes = listOf(
                                "Anger Gamma Camera components: Lead collimator, NaI(Tl) scintillation crystal, light guide, array of 37 to 91 Photomultiplier Tubes (PMTs), positioning network, Pulse Height Analyzer (PHA).",
                                "Lead collimator: defines the line of flight of incoming photons. Photons travelling obliquely strike the lead septa and are absorbed. Parallel-hole collimator maintains 1:1 image magnification at all depths, but spatial resolution degrades rapidly with increasing distance from collimator face.",
                                "Scintillation crystal: single large disc of Sodium Iodide doped with Thallium (NaI:Tl), typically 9.5 mm thick (3/8 inch). Thicker crystals improve sensitivity but degrade spatial resolution.",
                                "Pulse Height Analyzer (PHA): discriminates between unscattered primary photons and Compton-scattered photons that have lost energy. Uses a 15% to 20% energy window centered on the photopeak (e.g. 140 keV ± 10% = 126 to 154 keV for Tc-99m).",
                                "SPECT (Single Photon Emission Computed Tomography): gamma camera heads rotate 360 degrees around the patient, acquiring 2D projections reconstructed via filtered back-projection into 3D cross-sectional functional slices.",
                                "PET (Positron Emission Tomography): uses positron emitters (e.g. F-18 in FDG).",
                                "Positron physics: emitted positron travels 1-2 mm in tissue, loses kinetic energy, and encounters an electron: Annihilation occurs.",
                                "Annihilation reaction: mass is converted into two 511 keV gamma ray photons emitted in opposite directions (180 degrees apart).",
                                "Coincidence detection: ring of scintillator crystals (BGO, LSO, LYSO) detects two 511 keV photons striking opposite detectors within a narrow timing window (tau ~ 6-12 ns). No lead collimator needed (electronic collimation)!",
                                "PET coincidences: 1. True (single annihilation pair); 2. Scatter (one photon scattered before detection); 3. Random / Accidental (photons from two independent annihilations detected within timing window).",
                                "PET/CT combines functional metabolic uptake (18F-FDG glucose avidity) with anatomical localization and rapid CT-based attenuation correction."
                            ),
                            keyConcepts = listOf("Anger Gamma Camera Components", "Lead Collimator Types", "Pulse Height Analyzer (PHA)", "SPECT Tomographic Reconstruction", "Positron Annihilation (2 × 511 keV)", "PET Coincidence Detection & PET/CT"),
                            definitions = listOf(
                                DefinitionItem("Pulse Height Analyzer (PHA)", "An electronic device in a gamma camera that measures the voltage pulse amplitude from PMTs and rejects scattered photons whose energies fall outside a pre-set photopeak window."),
                                DefinitionItem("Positron Annihilation", "The mutual destruction of a positron and an electron, converting their rest mass into two 511 keV gamma photons travelling in opposite directions at 180 degrees."),
                                DefinitionItem("Electronic Collimation", "The localization of an annihilation event in PET along a Line of Response (LOR) defined by coincidence timing, without physical lead collimator septa.")
                            ),
                            equations = listOf(
                                "Positron Annihilation: e+ + e- -> 2 × 511 keV photons (at 180 deg)",
                                "PHA Energy Window: Photopeak ± 10% (for Tc-99m: 126 to 154 keV)",
                                "PET Coincidence Timing Window: tau approx 6 to 12 nanoseconds"
                            ),
                            tables = listOf(
                                RevisionTable(
                                    title = "Comparison: Gamma Camera (SPECT) vs PET Scanner",
                                    headers = listOf("Feature", "Gamma Camera / SPECT", "PET Scanner"),
                                    rows = listOf(
                                        listOf("Radionuclides", "Single photon (Tc-99m, I-123, In-111)", "Positron emitters (F-18, C-11, O-15, N-13)"),
                                        listOf("Photon Energy", "Typically 140 keV", "Fixed at 511 keV (annihilation pairs)"),
                                        listOf("Collimation", "Physical lead collimator septa", "Electronic coincidence detection (no lead)"),
                                        listOf("Scintillator Crystal", "NaI(Tl) (9.5 mm thick)", "Dense crystals: BGO, LSO, LYSO"),
                                        listOf("Sensitivity / Efficiency", "Very Low (<0.1% photons detected)", "High (up to 5% photons detected)"),
                                        listOf("Spatial Resolution", "7 - 12 mm", "4 - 5 mm (limited by positron range)")
                                    )
                                )
                            ),
                            whatYouMustUnderstand = listOf(
                                "PET has orders-of-magnitude higher sensitivity than SPECT because eliminating physical lead collimators allows thousands more photons to reach the crystals.",
                                "As distance from a parallel-hole collimator increases, spatial resolution DEGRADES rapidly, but sensitivity remains constant.",
                                "CT attenuation correction in PET/CT converts CT numbers (HU) at ~70 keV to linear attenuation coefficients at 511 keV via bilinear scaling."
                            ),
                            whatYouShouldMemorize = listOf(
                                "Annihilation photons: exactly 511 keV, emitted 180 degrees apart.",
                                "NaI(Tl) crystal thickness = 9.5 mm (3/8 inch).",
                                "PHA window for Tc-99m = 15-20% centered on 140 keV."
                            ),
                            highYieldFacts = listOf(
                                "Non-collinearity in PET occurs because the positron-electron pair possesses slight residual momentum at annihilation, causing photons to deviate by ~0.5 degrees from 180 degrees.",
                                "18F-FDG is trapped inside metabolically hyperactive cancer cells by hexokinase phosphorylation (FDG-6-phosphate cannot enter glycolysis)."
                            ),
                            chapterSummary = listOf(
                                "The Anger gamma camera uses NaI crystals and PHA windows for 140 keV imaging; PET exploits 511 keV annihilation coincidence pairs without lead collimators for high-sensitivity metabolic oncology."
                            ),
                            learningObjectives = listOf(
                                "Describe the function of each component of an Anger gamma camera (collimator, NaI crystal, PMTs, PHA).",
                                "Explain the physical mechanism of positron emission, annihilation, and coincidence detection in PET.",
                                "Contrast SPECT and PET in terms of resolution, sensitivity, and clinical applications."
                            )
                        ),
                        acquire = AcquireContent(
                            simpleExplanation = "A gamma camera uses a heavy lead colander (collimator) to only let straight-flying gamma rays hit the glowing crystal. A PET camera doesn't need lead: whenever an antimatter positron touches an electron, twin gamma rays shoot out in opposite directions at 511 keV. The ring detector spots them at the exact same nanosecond and draws a straight line between them.",
                            stepByStepMechanism = listOf(
                                "1. Patient receives IV injection of 18F-FDG.",
                                "2. FDG accumulates in glucose-avid cancer cells.",
                                "3. Fluorine-18 emits a positron (antimatter electron).",
                                "4. Positron travels ~1 mm, colliding with an atomic electron.",
                                "5. Annihilation occurs: mass vanishes into two 511 keV gamma rays shooting 180 deg apart.",
                                "6. Photons penetrate body and strike opposite crystal detectors simultaneously.",
                                "7. Coincidence timing circuit registers an event along the Line of Response (LOR).",
                                "8. Computer back-projects millions of LOR lines into high-resolution 3D metabolic cancer maps."
                            ),
                            whyItHappens = "Einstein mass-energy equivalence (E = mc^2) and Dirac antimatter-matter annihilation mechanics.",
                            clinicalRelevance = "PET/CT staging of lymphoma and lung carcinoma detects occult lymph node and distant metastases that appear normal in size on CT alone.",
                            commonMisconception = "Misconception: PET resolution can be infinitely improved by making smaller detector crystals. Reality: Resolution has a fundamental physical limit (~3-4 mm) imposed by positron travel range before annihilation and the ~0.5 degree non-collinearity angle.",
                            analogy = "Two runners starting back-to-back and running in opposite directions at the speed of light: if both trip sensors at the perimeter fence at the exact same moment, you know the event started on the line between them.",
                            imageQualityAndDoseImpact = "Combined PET/CT gives metabolic confirmation on the exact anatomical CT lymph node, preventing unnecessary biopsy surgeries.",
                            interactiveDiagramType = "PET_COINCIDENCE_SIM"
                        )
                    )
                )
            )
        )
    }
}
