package com.example.data.content

import com.example.data.model.AcquireContent
import com.example.data.model.Concept
import com.example.data.model.DefinitionItem
import com.example.data.model.PrimeContent
import com.example.data.model.RevisionTable
import com.example.data.model.Section

object Chapter4Content {
    val sections: List<Section> by lazy {
        listOf(
            Section(
                id = "4.1",
                chapterId = 4,
                number = "4.1",
                title = "Film-Screen Image Formation & H&D Curve",
                printedPage = 65,
                pdfPage = 74,
                concepts = listOf(
                    Concept(
                        id = "4.1.1",
                        chapterId = 4,
                        sectionId = "4.1",
                        title = "Emulsion Chemistry, Optical Density & Characteristic Curve",
                        printedPage = 65,
                        pdfPage = 74,
                        prime = PrimeContent(
                            structuredNotes = listOf(
                                "X-ray film construction: polyester base (~0.2 mm) coated on both sides with silver iodobromide emulsion (5-10 um thick, 90% bromide, 10% iodide).",
                                "Latent image formation: light photon liberates electron, electron migrates to sensitivity speck, attracts mobile Ag+ ions, forming submicroscopic silver speck.",
                                "Processing stages: 1. Developer (alkaline electron donor reduces Ag+ to metallic silver grains); 2. Fixer (acid thiosulfate dissolves unexposed crystals); 3. Wash & Dry.",
                                "Optical Density (D): logarithmic measure of blackening: D = log10(incident light / transmitted light). D=1 means 10% light transmitted; D=2 means 1% transmitted; D=3 means 0.1% transmitted.",
                                "Characteristic curve (H&D curve): plot of optical density vs log exposure (air kerma).",
                                "Three regions of curve: Toe (low exposure, shallow slope), Straight line portion (region of correct exposure, slope = gamma), Shoulder (high exposure, approaching saturation D ~ 3.5).",
                                "Base plus fog (B+F): intrinsic density of processed unexposed film (typically 0.15 to 0.20).",
                                "Film gamma (gamma): average gradient between net densities 0.25 and 2.0 (typically 2 to 3). Higher gamma = greater contrast, narrower latitude.",
                                "Film latitude: range of exposures that produce net densities in the diagnostic range (0.25 to 2.0). Gamma and latitude are inversely related.",
                                "Sensitometry: 21-step wedge optical test used daily for processor quality assurance."
                            ),
                            keyConcepts = listOf("Latent Image Formation", "Processing (Developer & Fixer)", "Optical Density D = log10(I0/It)", "Characteristic H&D Curve", "Film Gamma & Latitude"),
                            definitions = listOf(
                                DefinitionItem("Optical Density (D)", "The logarithm to base 10 of the ratio of incident light intensity to transmitted light intensity: D = log10(I0/It)."),
                                DefinitionItem("Film Gamma (gamma)", "The average slope of the straight-line portion of the characteristic curve between net densities of 0.25 and 2.0, measuring film contrast amplification."),
                                DefinitionItem("Film Latitude", "The range of radiation exposures over which a film produces useful diagnostic optical densities (0.25 to 2.0).")
                            ),
                            equations = listOf(
                                "D = log10(I0 / It)",
                                "Gamma = (D2 - D1) / (log E2 - log E1) (typically 2 to 3)",
                                "Transmitted light = I0 × 10^-D (e.g. D=1 -> 10%; D=2 -> 1%; D=3 -> 0.1%)"
                            ),
                            tables = listOf(
                                RevisionTable(
                                    title = "Optical Density vs Percentage Light Transmitted",
                                    headers = listOf("Optical Density (D)", "Fraction Transmitted", "Percent Transmitted", "Visual Appearance"),
                                    rows = listOf(
                                        listOf("0.0", "1 / 1", "100%", "Completely transparent"),
                                        listOf("0.2", "1 / 1.58", "63%", "Base plus fog level"),
                                        listOf("1.0", "1 / 10", "10%", "Standard radiograph average"),
                                        listOf("2.0", "1 / 100", "1%", "Dark lung field on chest film"),
                                        listOf("3.0", "1 / 1000", "0.1%", "Too dark for standard light box (hot light needed)")
                                    )
                                )
                            ),
                            whatYouMustUnderstand = listOf(
                                "High gamma amplifies image contrast (gamma = 2 to 3), but reduces exposure latitude, making technique settings more critical.",
                                "Chest radiography requires wide latitude (low gamma) to show both lucent lungs and dense mediastinum; mammography requires high gamma for low-contrast tissue."
                            ),
                            whatYouShouldMemorize = listOf(
                                "Standard diagnostic optical density range: 0.25 to 2.0.",
                                "Base plus fog = 0.15 - 0.20.",
                                "Optical density is additive across double-emulsion layers (1.0 front + 1.0 back = 2.0 total)."
                            ),
                            highYieldFacts = listOf(
                                "Inadequate washing of fixed film leaves residual thiosulfate, which oxidizes over time into yellow-brown silver sulfide with a vinegar odor.",
                                "Increasing developer temperature increases speed and gamma initially, but excessive heat increases fog and reduces net contrast."
                            ),
                            chapterSummary = listOf(
                                "Silver halide crystals convert light into a metallic silver latent image developed to an optical density D; the characteristic curve links log exposure to optical density."
                            ),
                            learningObjectives = listOf(
                                "Calculate optical density and percentage light transmission.",
                                "Interpret the H&D characteristic curve and explain the inverse relationship between gamma and latitude."
                            )
                        ),
                        acquire = AcquireContent(
                            simpleExplanation = "The film response curve is S-shaped: at very low exposure, barely anything shows (toe). In the middle sweet spot, slight exposure changes make dramatic contrast (gamma). At high exposure, all silver is used up and the film maxes out black (shoulder).",
                            stepByStepMechanism = listOf(
                                "1. X-ray photons strike intensifying screens, emitting visible light photons.",
                                "2. Light photons strike silver iodobromide grains in emulsion.",
                                "3. Trapped electrons attract mobile silver ions Ag+, forming submicroscopic metallic silver specks (latent image).",
                                "4. In the developer bath, exposed crystals are catalyzed into solid black silver grains.",
                                "5. In the fixer bath, unexposed silver halide crystals are dissolved away.",
                                "6. Transmitted light through the silver pattern forms the diagnostic negative image."
                            ),
                            whyItHappens = "Solid-state photocatalysis in silver halide crystals and reduction-oxidation chemistry.",
                            clinicalRelevance = "Sensitometry monitoring (speed, contrast, base+fog) detects processor chemistry degradation before repeat patient films are required.",
                            commonMisconception = "Misconception: A film with gamma=3 is 3 times faster. Reality: Gamma is the slope (contrast), not speed! Speed is the position of the curve along the horizontal exposure axis.",
                            analogy = "Developing photos in a darkroom: if you over-bake the chemical bath or leave it too long, the whole paper fogs gray and you lose picture punch.",
                            imageQualityAndDoseImpact = "Double emulsion with two intensifying screens cuts required patient dose by half compared to single-emulsion systems, but introduces crossover blur.",
                            interactiveDiagramType = "CHARACTERISTIC_CURVE"
                        )
                    )
                )
            ),
            Section(
                id = "4.3",
                chapterId = 4,
                number = "4.3",
                title = "Screens, Unsharpness & Mammography",
                printedPage = 70,
                pdfPage = 79,
                concepts = listOf(
                    Concept(
                        id = "4.3.1",
                        chapterId = 4,
                        sectionId = "4.3",
                        title = "Intensifying Screens & Mammography Physics",
                        printedPage = 70,
                        pdfPage = 79,
                        prime = PrimeContent(
                            structuredNotes = listOf(
                                "Film alone absorbs only ~2% of incident X-rays. Intensifying screens absorb ~30% of X-rays and convert each into ~1000 light photons (Intensification Factor IF = 30 to 100).",
                                "Speed class: speed = 1000 / K, where K is air kerma in uGy to produce D = 1.0 above base+fog. Standard general radiography uses 400 speed class (dose ~2.5 uGy). Detail extremity screens use 100 speed class.",
                                "Phosphor materials: historically calcium tungstate (CaWO4, blue); superseded by Rare Earth phosphors: Gadolinium oxysulphide (Gd2O2S, green emitting, K-edge 50 keV) and Lanthanum oxybromide (LaOBr, blue emitting, K-edge 39 keV).",
                                "Matching: green-emitting screens require green-sensitive orthochromatic film with red safelights; blue-emitting screens require standard film with amber safelights.",
                                "Screen unsharpness: light spreads out in thicker phosphors before hitting film. Fast screens = thick phosphor = high dose efficiency, but worse resolution.",
                                "Crossover: light from front screen penetrates base to expose opposite rear emulsion, contributing up to 25% of unsharpness.",
                                "Mammography physics: low kV (25-35 kV), Molybdenum target (Z=42, K-edge 20 keV) producing 17.5 & 19.6 keV characteristic lines, filtered by 0.03 mm Mo or 0.025 mm Rhodium.",
                                "Mammography film-screen: SINGLE screen with SINGLE-sided emulsion on distal side of film (eliminates crossover and parallax completely, achieving >= 15 lp/mm resolution).",
                                "Compression paddle (100-150 N force): evens breast thickness, reduces breast dose, brings tissues closer to film (lowers Ug), and immobilizes breast.",
                                "Mean glandular dose: standard metric in mammography, typically 1.5 to 3 mGy per view."
                            ),
                            keyConcepts = listOf("Intensification Factor (IF)", "Speed Class (1000/K)", "Rare Earth Phosphors", "Crossover & Parallax", "Mammography Mo/Rh Physics", "Single Screen & Emulsion"),
                            definitions = listOf(
                                DefinitionItem("Intensification Factor (IF)", "The ratio of exposure required to produce an optical density of 1.0 with film alone to the exposure required with an intensifying screen."),
                                DefinitionItem("Speed Class", "A descriptor of film-screen system sensitivity defined as 1000 / K, where K is the air kerma (in uGy) required to produce a net density of 1.0."),
                                DefinitionItem("Mean Glandular Dose (MGD)", "The average radiation absorbed dose delivered to the radiosensitive glandular tissue of the compressed breast in mammography.")
                            ),
                            equations = listOf(
                                "Speed Class = 1000 / K (uGy)",
                                "Standard 400 speed dose K = 1000 / 400 = 2.5 uGy",
                                "Total unsharpness Ut = sqrt(Ug^2 + Um^2 + Us^2)"
                            ),
                            tables = listOf(
                                RevisionTable(
                                    title = "Film-Screen Speed Classes and Applications",
                                    headers = listOf("Speed Class", "Typical Dose (uGy)", "Resolution (lp/mm)", "Clinical Application"),
                                    rows = listOf(
                                        listOf("100 (Detail)", "10 uGy", "8 - 12 lp/mm", "Extremities, fine skeletal detail"),
                                        listOf("200 (Medium)", "5 uGy", "6 - 8 lp/mm", "Paediatric examinations"),
                                        listOf("400 (Fast)", "2.5 uGy", "4 - 6 lp/mm", "Abdomen, spine, pelvis, general Bucky"),
                                        listOf("Mammography (Single screen)", "~100 uGy", ">= 15 lp/mm", "Breast microcalcifications (<100 um)")
                                    )
                                )
                            ),
                            whatYouMustUnderstand = listOf(
                                "In mammography, a single screen is placed on the DISTAL (back) side of the film so the most intense light interactions occur closest to the single emulsion, maximizing sharpness.",
                                "Compression is essential in mammography: it separates overlapping tissues, reduces scatter, decreases magnification unsharpness, and cuts glandular dose."
                            ),
                            whatYouShouldMemorize = listOf(
                                "Mammography target-filter: Mo-Mo (thinner breasts), Mo-Rh or Rh-Rh (denser/thicker breasts). Rh-Mo is NEVER used because Rh filter attenuates Mo characteristic lines.",
                                "Limiting spatial resolution: General film-screen ~5-8 lp/mm; Mammography film-screen >= 15 lp/mm."
                            ),
                            highYieldFacts = listOf(
                                "In mammography, the cathode is aligned towards the chest wall to match the anode heel effect to the thicker tissue.",
                                "AEC chambers in mammography are placed BEHIND the cassette to avoid imaging sensor shadows on the film."
                            ),
                            chapterSummary = listOf(
                                "Intensifying screens reduce radiation dose up to 100-fold; mammography utilizes low-kV characteristic X-rays, single-screen geometry, and vigorous compression for micron-level resolution."
                            ),
                            learningObjectives = listOf(
                                "Explain the trade-off between phosphor screen thickness, speed class, and spatial resolution.",
                                "Detail the specialized equipment features of a mammography unit (Mo/Rh target-filter, single-screen distal geometry, compression, and heel effect alignment)."
                            )
                        ),
                        acquire = AcquireContent(
                            simpleExplanation = "Regular X-ray film uses a sandwich: two glowing screens hugging film coated on both sides. Mammography needs extreme microscopic detail to see tiny cancer specks, so it uses a single screen behind a single layer of film—zero double vision (crossover), but takes a higher dose.",
                            stepByStepMechanism = listOf(
                                "1. Low-energy X-rays (28 kV) penetrate compressed breast tissue.",
                                "2. Photons pass through cassette front and transparent film base.",
                                "3. Photons strike the single rare-earth screen on the back side.",
                                "4. Light is produced at the entrance face of the screen, immediately touching the single emulsion.",
                                "5. Absence of a second screen eliminates crossover light scatter through the base.",
                                "6. Ultra-sharp edges (>=15 lp/mm) capture 50-100 um microcalcifications."
                            ),
                            whyItHappens = "Optical diffusion within phosphors and optical cross-talk through double-sided film base.",
                            clinicalRelevance = "Detecting ductal carcinoma in situ (DCIS) relies entirely on seeing clustered microcalcifications with fine branching morphology.",
                            commonMisconception = "Misconception: A double-screen double-emulsion system produces sharper images because both sides are exposed. Reality: Double-screen systems suffer crossover and parallax blur, limiting resolution to ~6 lp/mm; single-screen systems exceed 15 lp/mm.",
                            analogy = "Drawing with a fine needle versus drawing with a fat marker: the needle makes precise lines, but you have to press harder (higher dose) to see it.",
                            imageQualityAndDoseImpact = "Breast compression reduces thickness from 8 cm to 4.5 cm, reducing mean glandular dose by over 50% and dramatically cutting scatter.",
                            interactiveDiagramType = "MAMMO_SPECTRUM_SIM"
                        )
                    )
                )
            )
        )
    }
}
