package com.example.data.content

import com.example.data.model.AcquireContent
import com.example.data.model.Concept
import com.example.data.model.DefinitionItem
import com.example.data.model.PrimeContent
import com.example.data.model.RevisionTable
import com.example.data.model.Section

object Chapter2Content {
    val sections: List<Section> by lazy {
        listOf(
            Section(
                id = "2.1",
                chapterId = 2,
                number = "2.1",
                title = "Interactions with Tissue & Dosimetry",
                printedPage = 23,
                pdfPage = 32,
                concepts = listOf(
                    Concept(
                        id = "2.1.1",
                        chapterId = 2,
                        sectionId = "2.1",
                        title = "Radiolysis of Water & Radiation Quantities",
                        printedPage = 23,
                        pdfPage = 32,
                        prime = PrimeContent(
                            structuredNotes = listOf(
                                "Biological damage originates from radiation absorption in tissue, producing chemical changes in microseconds and biological damage over hours to decades.",
                                "Critical molecular targets are DNA and enzymes. Damage occurs via Direct action (rupturing covalent bonds) or Indirect action (free radicals from water radiolysis).",
                                "Because tissue is 70-90% water, indirect damage predominates: H2O + radiation -> H2O+ + e-; H2O+ decomposes to H+ + OH· (hydroxyl radical).",
                                "Hydroxyl radical OH· is an intensely reactive oxidizing agent producing damaging chain reactions.",
                                "Linear Energy Transfer (LET): energy deposited per unit path length (keV/um). High-LET (alpha particles) causes dense, non-repairable double-strand breaks; Low-LET (X-rays, electrons) causes sparse, repairable damage.",
                                "Equivalent dose (H): absorbed dose (D) multiplied by radiation weighting factor (wR). Unit is Sievert (Sv); 1 Sv = 1 J/kg.",
                                "wR values: X-rays, gamma rays, electrons = 1; Alpha particles = 20; Protons = 5; Neutrons = 5 to 20.",
                                "Effective dose (E): sum over all tissues of equivalent dose multiplied by tissue weighting factor wT: E = sum(wT × HT). Quantifies total stochastic (cancer & hereditary) risk.",
                                "Risk of fatal cancer for uniform whole-body exposure = 5% per Sv (1 in 20,000 per mSv) in the general population."
                            ),
                            keyConcepts = listOf("Direct vs Indirect Action", "Hydroxyl Radical (OH·)", "Linear Energy Transfer (LET)", "Equivalent Dose (wR)", "Effective Dose (wT)", "Cancer Risk (1 in 20,000 / mSv)"),
                            definitions = listOf(
                                DefinitionItem("Equivalent Dose (H)", "Absorbed dose in tissue multiplied by the radiation weighting factor wR (H = D × wR, measured in Sieverts)."),
                                DefinitionItem("Effective Dose (E)", "The sum of the weighted equivalent doses in all the tissues and organs of the body: E = sum(wT × HT)."),
                                DefinitionItem("Linear Energy Transfer (LET)", "The average energy deposited per unit distance along the track of an ionizing particle (keV/um).")
                            ),
                            equations = listOf(
                                "H = D × wR (Equivalent Dose in Sv)",
                                "E = sum(wT × HT) (Effective Dose in Sv)",
                                "Fatal cancer risk = 5% per Sv (1 in 20,000 per mSv)"
                            ),
                            tables = listOf(
                                RevisionTable(
                                    title = "Table 2.3: ICRP Tissue Weighting Factors (wT)",
                                    headers = listOf("Organ / Tissue", "Risk Factor (% per Sv)", "Weighting Factor (wT)"),
                                    rows = listOf(
                                        listOf("Gonads", "-", "0.20"),
                                        listOf("Stomach", "1.10", "0.12"),
                                        listOf("Colon", "0.85", "0.12"),
                                        listOf("Lung", "0.85", "0.12"),
                                        listOf("Red Bone Marrow", "0.50", "0.12"),
                                        listOf("Bladder", "0.30", "0.05"),
                                        listOf("Oesophagus", "0.30", "0.05"),
                                        listOf("Breast", "0.20", "0.05"),
                                        listOf("Liver", "0.15", "0.05"),
                                        listOf("Thyroid", "0.08", "0.05"),
                                        listOf("Bone surface", "0.05", "0.01"),
                                        listOf("Skin", "0.02", "0.01"),
                                        listOf("Remainder tissues (9 organs)", "0.50", "0.05"),
                                        listOf("Total", "5.0", "1.00")
                                    )
                                )
                            ),
                            whatYouMustUnderstand = listOf(
                                "X-rays have wR = 1, so for diagnostic X-rays, absorbed dose (Gy) and equivalent dose (Sv) are numerically identical.",
                                "Effective dose allows non-uniform exposures (like a chest X-ray or CT) to be expressed as an equivalent uniform whole-body risk.",
                                "Gonads have the highest individual weighting factor (0.20) because they carry hereditary risk for future generations."
                            ),
                            whatYouShouldMemorize = listOf(
                                "High-risk organs (wT = 0.12): Stomach, Colon, Lung, Red bone marrow.",
                                "Moderate risk (wT = 0.05): Bladder, Oesophagus, Breast, Liver, Thyroid.",
                                "Low risk (wT = 0.01): Bone surface, Skin."
                            ),
                            highYieldFacts = listOf(
                                "Thyroid has high cancer induction rate but low weighting factor (0.05) because thyroid cancer has very low mortality.",
                                "Children have more than double the radiation cancer risk compared to the population average due to longer life expectancy and higher mitotic rates."
                            ),
                            chapterSummary = listOf(
                                "Radiation harms cells primarily through indirect water radiolysis creating reactive hydroxyl radicals; effective dose integrates organ-specific radiosensitivities to quantify stochastic detriment."
                            ),
                            learningObjectives = listOf(
                                "Calculate effective dose given organ equivalent doses and tissue weighting factors.",
                                "Differentiate LET and RBE and explain why alpha particles have wR = 20."
                            )
                        ),
                        acquire = AcquireContent(
                            simpleExplanation = "When an X-ray shoots through the body, 80% of the time it hits water molecules, splitting them into aggressive chemical fragments called free radicals that attack cell DNA.",
                            stepByStepMechanism = listOf(
                                "1. Secondary electron passes through intracellular fluid.",
                                "2. Knocks electron out of H2O molecule, creating H2O+ ion and free electron.",
                                "3. H2O+ breaks apart into H+ and OH· (hydroxyl radical).",
                                "4. The uncharged OH· has an unpaired electron, making it chemically hyperactive.",
                                "5. OH· diffuses nanometers to the DNA backbone, stealing hydrogen and causing single- or double-strand breaks.",
                                "6. If unrepaired or misrepaired, this can lead to cell death (deterministic) or neoplastic mutation (stochastic)."
                            ),
                            whyItHappens = "Water radiolysis chemistry in aqueous cellular environments.",
                            clinicalRelevance = "Effective dose allows a radiologist to explain risk to a patient: e.g. a 7 mSv CT scan represents approximately a 1 in 2800 additional lifetime cancer risk.",
                            commonMisconception = "Misconception: 1 mSv of dose delivered to the hand carries the same cancer risk as 1 mSv to the chest. Reality: Hands have low weighting factor tissues (skin wT=0.01), whereas chest contains lungs (0.12) and breasts (0.05). Effective dose specifically normalizes this disparity.",
                            analogy = "Direct action is a bullet hitting the computer motherboard directly. Indirect action is a bullet hitting the sprinkler pipe, causing water to spray over the motherboard.",
                            imageQualityAndDoseImpact = "Collimating the beam off high-wT organs (e.g. shielding thyroid and breast in spine exams) drastically reduces effective dose without degrading diagnostic image content.",
                            interactiveDiagramType = "EFFECTIVE_DOSE_CALC"
                        )
                    )
                )
            ),
            Section(
                id = "2.3",
                chapterId = 2,
                number = "2.3",
                title = "Biological Effects & Population Dose",
                printedPage = 25,
                pdfPage = 34,
                concepts = listOf(
                    Concept(
                        id = "2.3.1",
                        chapterId = 2,
                        sectionId = "2.3",
                        title = "Deterministic vs Stochastic Effects & Radon",
                        printedPage = 25,
                        pdfPage = 34,
                        prime = PrimeContent(
                            structuredNotes = listOf(
                                "Deterministic effects (non-stochastic): possess a threshold dose below which effect does NOT occur. Above threshold, severity increases with dose. Caused by extensive cell killing.",
                                "Threshold doses: Skin erythema = 2-5 Gy; Hair loss (epilation) = 2-5 Gy; Sterility = 2-3 Gy; Cataracts (lens of eye) = 5 Gy cumulative; Whole-body lethality = 3-5 Gy; Fetal abnormality = 0.1-0.5 Gy (100-500 mGy).",
                                "Stochastic effects: occur by chance without a threshold dose (Linear No-Threshold / LNT theory). Probability increases linearly with dose; severity is independent of dose (all-or-nothing).",
                                "Stochastic examples: Carcinogenesis (solid tumors latency ~40 yrs, leukemias peak at 7-10 yrs) and Genetic hereditary effects (germ cell mutations).",
                                "Fetal irradiation: greatest risk of congenital malformation during organogenesis (weeks 3 to 8 of pregnancy). Childhood cancer fatal risk is 1 in 33,000 per mGy (3% per Gy).",
                                "UK natural population dose averages 2.2 mSv/year (Radon gas = 1.3 mSv / 49%, Terrestrial = 0.35 mSv / 14%, Internal radionuclides like K-40 = 0.27 mSv / 12%, Cosmic = 0.32 mSv / 10%).",
                                "Artificial radiation in UK averages 0.4 mSv/year, of which diagnostic medical X-rays account for >90% (370 uSv/year)."
                            ),
                            keyConcepts = listOf("Deterministic Effects (Threshold)", "Stochastic Effects (LNT)", "Latency Period", "Fetal Sensitivity (Weeks 3-8)", "Natural Background & Radon"),
                            definitions = listOf(
                                DefinitionItem("Deterministic Effect", "A biological effect that has a threshold dose and for which the severity increases with increasing absorbed dose due to cell killing."),
                                DefinitionItem("Stochastic Effect", "A biological effect occurring by chance without a threshold dose, where the probability of occurrence is proportional to dose, but severity is independent of dose."),
                                DefinitionItem("Linear No-Threshold (LNT)", "The radiological protection hypothesis that any radiation dose, no matter how small, carries a proportional risk of inducing cancer or genetic mutation.")
                            ),
                            equations = listOf(
                                "Natural UK dose = 2.2 mSv/yr (Radon 49%, Terrestrial 14%, Medical 14%, Internal 12%, Cosmic 10%)",
                                "Childhood cancer risk from fetal irradiation = 1 in 33,000 per mGy"
                            ),
                            tables = listOf(
                                RevisionTable(
                                    title = "Table 2.1: Threshold Doses for Deterministic Effects",
                                    headers = listOf("Effect", "Threshold Dose (Gy)"),
                                    rows = listOf(
                                        listOf("Skin erythema", "2 - 5 Gy"),
                                        listOf("Irreversible skin ulceration", "20 - 40 Gy"),
                                        listOf("Temporary hair loss", "2 - 5 Gy"),
                                        listOf("Sterility (gonads)", "2 - 3 Gy"),
                                        listOf("Cataracts (lens of eye)", "5 Gy (cumulative)"),
                                        listOf("Whole body lethality (LD50)", "3 - 5 Gy"),
                                        listOf("Fetal malformation (weeks 3-8)", "0.1 - 0.5 Gy (100-500 mGy)")
                                    )
                                )
                            ),
                            whatYouMustUnderstand = listOf(
                                "Diagnostic radiology doses (typically 0.01 to 20 mGy) are far below deterministic thresholds, EXCEPT in prolonged fluoroscopy (skin erythema) and interventional work (finger skin dose, lens cataracts).",
                                "In stochastic effects, getting a higher dose does NOT make the cancer worse; it simply makes you more likely to get cancer."
                            ),
                            whatYouShouldMemorize = listOf(
                                "Lens cataract threshold is 5 Gy (cumulative).",
                                "Radon represents nearly half (49%) of all natural background radiation in the UK.",
                                "Potassium-40 provides ~60% of all internal natural exposure."
                            ),
                            highYieldFacts = listOf(
                                "The 10-day rule (scheduling pelvic X-rays within 10 days of LMP) has been superseded in modern practice by the 28-day rule for standard examinations.",
                                "Fetal organogenesis in weeks 3 to 8 is the most radiosensitive period for teratogenesis."
                            ),
                            chapterSummary = listOf(
                                "Radiation effects divide into deterministic (cell killing with thresholds) and stochastic (DNA mutation with no threshold); diagnostic protection concentrates on stochastic optimization."
                            ),
                            learningObjectives = listOf(
                                "Compare deterministic and stochastic effects with clinical examples and dose thresholds.",
                                "List the major contributors to natural background and artificial radiation in the population."
                            )
                        ),
                        acquire = AcquireContent(
                            simpleExplanation = "Deterministic effects are like a sunburn: you won't get burned below a certain sun exposure, but once past the threshold, more sun means a worse burn. Stochastic effects are like buying lottery tickets: each ticket (dose) gives you another chance to win cancer, but winning cancer doesn't make the cancer 'twice as bad'.",
                            stepByStepMechanism = listOf(
                                "1. Deterministic: high radiation dose kills thousands of epithelial or germ cells at once.",
                                "2. When surviving stem cell fraction drops below tissue repair capacity, organ failure/erythema occurs.",
                                "3. Stochastic: a single X-ray photon causes a single sublethal double-strand break in an oncogene or tumor-suppressor gene.",
                                "4. The cell survives with altered genetic code.",
                                "5. Over decades (latency), clonal proliferation produces clinical malignancy."
                            ),
                            whyItHappens = "Distinction between macroscopic tissue destruction vs single-cell sub-lethal mutagenic transformation.",
                            clinicalRelevance = "Protracted fluoroscopy exceeding 60 minutes can deliver skin doses >2 Gy, causing radiation-induced erythema or epilation if the tube position is stationary.",
                            commonMisconception = "Misconception: A person exposed to 10 mSv will develop a milder cancer than someone exposed to 100 mSv. Reality: Both will develop the exact same type/severity of malignancy; the 100 mSv individual merely had a 10x higher probability of developing it.",
                            analogy = "Deterministic is dropping 100 kg on a table until it cracks (threshold). Stochastic is Russian roulette: pulling the trigger with one chamber loaded vs multiple chambers loaded.",
                            imageQualityAndDoseImpact = "Dose reduction (ALARA/ALARP) directly scales down the trainee's and patient's stochastic lifetime cancer hazard.",
                            interactiveDiagramType = "DOSE_RESPONSE_CURVE"
                        )
                    )
                )
            ),
            Section(
                id = "2.5",
                chapterId = 2,
                number = "2.5",
                title = "IRR99 and IRMER 2000 Legislation",
                printedPage = 31,
                pdfPage = 40,
                concepts = listOf(
                    Concept(
                        id = "2.5.1",
                        chapterId = 2,
                        sectionId = "2.5",
                        title = "IRR99 (Staff/Public) vs IRMER 2000 (Patients)",
                        printedPage = 31,
                        pdfPage = 40,
                        prime = PrimeContent(
                            structuredNotes = listOf(
                                "UK Radiation Legislation: IRR99 protects staff and the public (enforced by the Health and Safety Executive / HSE under criminal law).",
                                "IRMER 2000 protects patients undergoing medical exposure (enforced by the Healthcare Commission / CQC).",
                                "Three core ICRP principles: Justification (benefit > risk), Optimization (ALARP - As Low As Reasonably Practicable), and Dose Limitation (limits apply to workers and public, NEVER to patients!).",
                                "IRR99 Employer duties: appoint a Radiation Protection Adviser (RPA - qualified medical physicist), appoint Radiation Protection Supervisors (RPS - day-to-day management), conduct prior risk assessments, write Local Rules.",
                                "Controlled Area: where worker dose could exceed 3/10th of any dose limit, special procedures are required, or dose rate > 7.5 uSv/h averaged over working day. For mobile units, area within 2 m is controlled.",
                                "Supervised Area: where someone could exceed public dose limit (1 mSv/yr) or conditions need review (e.g. nuclear medicine waiting room).",
                                "Classified Person: employee whose dose is likely to exceed 3/10th of any dose limit (>6 mSv/yr whole body). Must be >=18 yrs old, certified medically fit, monthly dose monitoring, records kept for 50 years.",
                                "IRMER 2000 Key Roles: Referrer (requests exam, supplies clinical data), IRMER Practitioner (justifies and authorizes exposure), Operator (practical aspects: radiographer, physicist, engineer).",
                                "Diagnostic Reference Levels (DRLs): benchmark doses for standard-sized patients; audited locally to detect consistently excessive exposures."
                            ),
                            keyConcepts = listOf("IRR99 vs IRMER 2000", "RPA vs RPS", "Controlled vs Supervised Areas", "Classified Workers (>3/10 limit)", "Referrer, Practitioner, Operator", "Diagnostic Reference Levels (DRLs)"),
                            definitions = listOf(
                                DefinitionItem("Radiation Protection Adviser (RPA)", "An accredited expert (almost invariably a medical physicist) appointed in writing by the employer to advise on compliance with IRR99."),
                                DefinitionItem("Radiation Protection Supervisor (RPS)", "A departmental employee with supervisory responsibility appointed to ensure local rules are followed."),
                                DefinitionItem("IRMER Practitioner", "A registered healthcare professional entitled to justify and authorize an individual medical radiation exposure based on benefit vs risk."),
                                DefinitionItem("Diagnostic Reference Level (DRL)", "A dose level for typical examinations for groups of standard-sized patients or standard phantoms for broadly defined equipment types.")
                            ),
                            equations = listOf(
                                "Controlled area threshold = >3/10th of any limit OR >7.5 uSv/h",
                                "Classified person threshold = >3/10th of any annual dose limit (>6 mSv/yr)"
                            ),
                            tables = listOf(
                                RevisionTable(
                                    title = "Table 2.6: IRR99 Annual Dose Limits",
                                    headers = listOf("Dose Category", "Employees (>=18 yrs)", "Trainees (16-18 yrs)", "Public"),
                                    rows = listOf(
                                        listOf("Effective dose (whole body)", "20 mSv", "6 mSv", "1 mSv"),
                                        listOf("Equivalent dose: Lens of the eye", "150 mSv", "50 mSv", "15 mSv"),
                                        listOf("Equivalent dose: Skin / Hands / Feet", "500 mSv", "150 mSv", "50 mSv"),
                                        listOf("Abdomen of woman of reproductive capacity", "13 mSv / 3 months", "-", "-"),
                                        listOf("Fetus of pregnant employee", "1 mSv (over pregnancy)", "-", "-")
                                    )
                                )
                            ),
                            whatYouMustUnderstand = listOf(
                                "Dose limits DO NOT apply to patients undergoing diagnostic examinations; patient protection is managed purely through justification and ALARP optimization.",
                                "A pregnant employee has a fetal dose limit of 1 mSv over the declared term of pregnancy once notified in writing.",
                                "Equipment overexposure notification to HSE: required if interventional/CT/fluoro >5 mSv has a dose multiplier >=1.5; mammography >=10; extremities/chest >=20."
                            ),
                            whatYouShouldMemorize = listOf(
                                "Annual dose limits: Worker 20 mSv, Public 1 mSv, Lens 150 mSv, Extremities 500 mSv.",
                                "Classified worker records must be retained for 50 years.",
                                "Medical X-ray leakage limit: <1 mGy/hour at 1 meter from focus."
                            ),
                            highYieldFacts = listOf(
                                "Over 99% of NHS radiology staff receive less than 1 mSv/year (public limit). Classification is rarely needed except for interventionalists' finger doses.",
                                "In mobile radiography, the operator exposure switch must be on an extending cable of at least 2 meters."
                            ),
                            chapterSummary = listOf(
                                "IRR99 regulates employer systems and worker/public dose limits; IRMER 2000 governs patient exposure justification, clinical optimization, and diagnostic reference levels."
                            ),
                            learningObjectives = listOf(
                                "Differentiate the legal scope and enforcing authorities of IRR99 versus IRMER 2000.",
                                "State statutory dose limits for employees, trainees, the public, and pregnant staff.",
                                "Define the criteria for designating controlled areas and classified persons."
                            )
                        ),
                        acquire = AcquireContent(
                            simpleExplanation = "Think of IRR99 as the workplace safety shield for hospital staff and visitors, with hard legal ceilings (dose limits). Think of IRMER as the patient care compass: you can never put a ceiling on a patient's necessary medical dose, but a doctor must justify every single exposure and keep it ALARP.",
                            stepByStepMechanism = listOf(
                                "1. Referrer sends a formal radiology request with explicit clinical indication.",
                                "2. IRMER Practitioner evaluates clinical benefit vs radiation risk, authorizing the scan.",
                                "3. Operator checks patient identity (3 identifiers) and pregnancy status (28-day rule).",
                                "4. Operator selects optimized technique (collimation, high kV, low mAs) adhering to ALARP.",
                                "5. Patient is imaged inside a designated Controlled Area under written Local Rules supervised by an RPS.",
                                "6. Dose Area Product (DAP) is recorded and audited against National DRLs."
                            ),
                            whyItHappens = "Legal framework established under the UK Health and Safety at Work Act 1974 enacting European radiation safety directives.",
                            clinicalRelevance = "A radiologist who performs a procedure without justification or on the wrong patient commits a statutory violation under IRMER that must be formally reported to the CQC/Healthcare Commission.",
                            commonMisconception = "Misconception: Dose limits exist to stop doctors from giving sick patients too many CT scans. Reality: Dose limits never apply to patients! Restricting a patient's diagnostic dose with an arbitrary cap could cause fatal missed diagnoses.",
                            analogy = "Speed limits apply to commuters on the highway (workers/public dose limits), but ambulances rushing to save an injured patient have no fixed speed limit—they are guided by medical necessity and driving safely (justification & ALARP).",
                            imageQualityAndDoseImpact = "Auditing against DRLs ensures radiology rooms with drifting calibrations or outdated protocols are detected and repaired before patient doses creep up.",
                            interactiveDiagramType = "LEGISLATION_FLOW"
                        )
                    )
                )
            ),
            Section(
                id = "2.8",
                chapterId = 2,
                number = "2.8",
                title = "Practical Radiation Protection",
                printedPage = 41,
                pdfPage = 50,
                concepts = listOf(
                    Concept(
                        id = "2.8.1",
                        chapterId = 2,
                        sectionId = "2.8",
                        title = "Staff & Patient Protection, Shielding & DRLs",
                        printedPage = 41,
                        pdfPage = 50,
                        prime = PrimeContent(
                            structuredNotes = listOf(
                                "Main source of staff radiation in an X-ray room is scatter from the patient (Compton interactions), NOT tube leakage (<2% of scatter).",
                                "Scatter dose rule of thumb: at 1 m from the patient, maximum scatter is approximately 5 uGy per Gy·cm^2 of patient DAP.",
                                "X-ray tube should be positioned UNDER the table in fluoroscopy: entrance scatter is high towards the tube; undercouch tubes direct this high scatter downwards towards the operator's feet rather than eyes/thyroid.",
                                "Staff protection triad: Time (minimize fluoroscopy screening time), Distance (step back; inverse square law), Shielding (lead aprons, thyroid collars, lead glass ceiling screens).",
                                "Lead apron specifications: 0.25 mm Pb eq transmits ~5%; 0.35 mm transmits ~3%; 0.5 mm transmits ~1.5%. Interventionalists typically use 0.5 mm aprons with 0.5 mm thyroid collars.",
                                "Room shielding: 1-2 mm lead in walls, or 120 mm solid brick (equivalent to 1 mm lead), or barium plaster.",
                                "Patient dose reduction: strict collimation, compression, high kV technique, increasing focus-to-film distance (FFD), and removing the antiscatter grid in pediatric patients.",
                                "Typical patient doses: PA chest ESD = 0.15-0.2 mGy (0.015 mSv); AP abdomen = 5-6 mGy (0.6 mSv); Lateral lumbar spine = 10-14 mGy (0.8 mSv); CT abdomen/pelvis = 10-30 mGy (8-10 mSv); Barium enema = 7 mSv."
                            ),
                            keyConcepts = listOf("Patient Scatter as Primary Staff Hazard", "Undercouch vs Overcouch Tube Geometry", "Lead Apron Lead Equivalences", "Collimation & Grid Removal", "Typical Exam DRLs"),
                            definitions = listOf(
                                DefinitionItem("Entrance Surface Dose (ESD)", "The absorbed dose to air at the point of intersection of the X-ray beam axis with the entrance surface of the patient, including backscatter from tissue."),
                                DefinitionItem("Backscatter Factor", "The ratio of total dose at the patient entrance surface (including scatter) to the dose at the same point in space in the absence of the patient (typically 1.25 to 1.5)."),
                                DefinitionItem("Lead Equivalence", "The thickness of lead that provides the same degree of attenuation as the material in question under specified radiation conditions.")
                            ),
                            equations = listOf(
                                "Staff scatter at 1 m ~ 5 uGy / (Gy·cm^2 DAP)",
                                "Transmission: 0.25 mm Pb ~ 5%; 0.35 mm Pb ~ 3%; 0.5 mm Pb ~ 1.5%",
                                "ESD = Incident Air Kerma × Backscatter Factor (~1.25 to 1.5)"
                            ),
                            tables = listOf(
                                RevisionTable(
                                    title = "Table 2.9: National Diagnostic Reference Levels (NDRLs)",
                                    headers = listOf("Radiographic Examination", "Typical ESD (mGy)", "NDRL ESD (mGy)", "Typical DAP (Gy·cm^2)", "NDRL DAP (Gy·cm^2)"),
                                    rows = listOf(
                                        listOf("Chest PA", "0.12", "0.20", "0.09", "0.12"),
                                        listOf("Skull AP/PA", "1.9", "3.0", "-", "-"),
                                        listOf("Lumbar spine AP", "4.3", "6.0", "1.3", "1.6"),
                                        listOf("Lumbar spine Lateral", "10.0", "14.0", "2.1", "3.0"),
                                        listOf("Pelvis AP", "3.2", "4.0", "2.0", "3.0"),
                                        listOf("Barium meal", "-", "-", "9.0", "13.0"),
                                        listOf("Barium enema", "-", "-", "22.0", "31.0"),
                                        listOf("Coronary angiogram", "-", "-", "26.0", "36.0")
                                    )
                                )
                            ),
                            whatYouMustUnderstand = listOf(
                                "The patient is the primary source of scatter in the room. A single step back from 1 m to 2 m reduces staff scatter by 75%!",
                                "Undercouch tube configurations provide drastically lower operator eye and thyroid doses than overcouch tubes.",
                                "Lateral spine views have much higher ESD (10-14 mGy) than AP views (4-6 mGy) because the lateral thickness of the body requires greater mAs penetration."
                            ),
                            whatYouShouldMemorize = listOf(
                                "0.35 mm lead apron is standard for general radiography; 0.5 mm lead apron for interventional radiology.",
                                "Fluoroscopy skin entrance dose rate limit is 100 mGy/min (remedial action if >50 mGy/min for standard patient).",
                                "Solid brick wall of 120 mm provides shielding equal to 1 mm of lead."
                            ),
                            highYieldFacts = listOf(
                                "Removing the antiscatter grid in babies and infants reduces dose by a factor of 3 to 5 with minimal contrast loss because small bodies produce very little scatter.",
                                "Lead rubber aprons do NOT protect against high-energy gamma rays from Tc-99m (140 keV) or PET (511 keV)!"
                            ),
                            chapterSummary = listOf(
                                "Minimizing scatter, maximizing operator distance, utilizing undercouch tube geometry, wearing fitted lead PPE, and adhering to DRLs ensures patient and staff doses remain ALARP."
                            ),
                            learningObjectives = listOf(
                                "Explain why undercouch X-ray tubes protect staff better than overcouch tubes.",
                                "Evaluate methods for patient dose reduction including grid removal, collimation, and FFD.",
                                "Recall the National Diagnostic Reference Levels for chest, spine, pelvis, and fluoroscopy."
                            )
                        ),
                        acquire = AcquireContent(
                            simpleExplanation = "In the X-ray room, the patient acts like a glowing light bulb of scattered radiation. Standing right next to the patient bathes you in scatter; taking two steps back cuts your dose to almost nothing.",
                            stepByStepMechanism = listOf(
                                "1. Primary X-ray beam enters patient from X-ray tube.",
                                "2. Compton interactions deflect photons in all directions, turning irradiated tissue into a secondary radiation source.",
                                "3. High scatter bounces back towards the entrance side (tube side).",
                                "4. In undercouch fluoroscopy, this intense entrance scatter shoots downwards towards the leaded table skirts and floor.",
                                "5. At 1 meter, scatter is ~5 uGy per Gy·cm^2 of patient DAP.",
                                "6. Lead aprons (0.35-0.5 mm Pb) absorb 97-98.5% of scatter photons before reaching staff bone marrow and organs."
                            ),
                            whyItHappens = "Compton scattering cross-sections and differential backward/forward scatter angular distribution.",
                            clinicalRelevance = "Interventional radiologists performing prolonged catheterizations (DAP >100 Gy·cm^2) require ceiling-suspended lead glass shields and table-side lead flaps to prevent cataracts and leg dermatitis.",
                            commonMisconception = "Misconception: The main radiation hitting a nurse or doctor in the fluoroscopy suite is leakage leaking out of the X-ray tube housing. Reality: Tube housing leakage is <2% of scatter; >98% of your dose comes from Compton scatter escaping the patient's body.",
                            analogy = "Spraying a firehose into a boulder: the direct stream hits the rock, but fine mist (scatter) ricochets in all directions towards the bystanders.",
                            imageQualityAndDoseImpact = "Tight collimation to the region of interest slashes the volume of tissue irradiated, dropping total scatter and dramatically improving image contrast while lowering patient dose.",
                            interactiveDiagramType = "FLUORO_SCATTER_GEOMETRY"
                        )
                    )
                )
            )
        )
    }
}
