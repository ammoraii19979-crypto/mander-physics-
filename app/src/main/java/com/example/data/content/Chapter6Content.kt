package com.example.data.content

import com.example.data.model.AcquireContent
import com.example.data.model.Concept
import com.example.data.model.DefinitionItem
import com.example.data.model.PrimeContent
import com.example.data.model.RevisionTable
import com.example.data.model.Section

object Chapter6Content {
    val sections: List<Section> by lazy {
        listOf(
            Section(
                id = "6.1",
                chapterId = 6,
                number = "6.1",
                title = "The Image Intensifier",
                printedPage = 91,
                pdfPage = 100,
                concepts = listOf(
                    Concept(
                        id = "6.1.1",
                        chapterId = 6,
                        sectionId = "6.1",
                        title = "II Tube Physics, Gain & Magnification",
                        printedPage = 91,
                        pdfPage = 100,
                        prime = PrimeContent(
                            structuredNotes = listOf(
                                "Image intensifier (II) converts X-ray transmission pattern into a bright, minified visible light image in real time.",
                                "Input screen: thin curved Al/Ti window; CsI:Na needle crystals (0.1-0.4 mm thick) perpendicular to face for internal light reflection (absorbs ~60% of X-rays); Antimony caesium (SbCs3) photocathode emits electrons via photoelectric effect.",
                                "Accelerating anode: held at +25 kV relative to photocathode, accelerating electrons towards output phosphor.",
                                "Output screen: 25-35 mm diameter zinc cadmium sulphide (ZnCdS:Ag) phosphor emitting green light; covered with 0.5 um aluminium coat acting as anode and blocking backward light leak.",
                                "Brightness Gain = Flux Gain × Minification Gain.",
                                "Flux gain (~50): single 25 keV electron generates thousands of light photons in output phosphor.",
                                "Minification gain: ratio of screen areas = (D_input / D_output)^2. For 300 mm input and 30 mm output, minification gain = (300/30)^2 = 100.",
                                "Total Brightness Gain = 50 × 100 = 5000.",
                                "Conversion Factor (Gx): ratio of output luminance (Cd/m^2) to input dose rate (uGy/s). Typically 25-30 Cd·m^-2·(uGy/s)^-1. Degrades over time with phosphor aging.",
                                "Magnification mode: increasing electrode voltages moves electron crossover focus closer to input screen. Central area fills the entire output phosphor. Increases spatial resolution, but decreases minification gain, requiring higher input dose rate (higher patient skin dose)."
                            ),
                            keyConcepts = listOf("Input Screen (CsI Needles & SbCs3)", "Flux Gain & Minification Gain", "Conversion Factor (Gx)", "Electronic Magnification", "Output Screen (ZnCdS:Ag)"),
                            definitions = listOf(
                                DefinitionItem("Minification Gain", "The increase in image brightness resulting from concentrating electrons from the large input screen area onto the small output screen area: (D_in / D_out)^2."),
                                DefinitionItem("Flux Gain", "The increase in photon emission at the output screen caused by high-voltage electron acceleration (typically ~50 light photons per electron)."),
                                DefinitionItem("Conversion Factor (Gx)", "The luminance of the output screen in candelas per square meter divided by the input air kerma rate in uGy per second.")
                            ),
                            equations = listOf(
                                "Minification Gain = (D_input / D_output)^2",
                                "Total Brightness Gain = Flux Gain × Minification Gain (~5000)",
                                "Gx = Output Luminance (Cd/m^2) / Input Dose Rate (uGy/s) (25 - 30)"
                            ),
                            tables = listOf(
                                RevisionTable(
                                    title = "Image Intensifier Component Functions",
                                    headers = listOf("Component", "Material", "Physical Role"),
                                    rows = listOf(
                                        listOf("Input Window", "Aluminium / Titanium foil", "Maintains vacuum with minimal X-ray absorption"),
                                        listOf("Input Phosphor", "Caesium Iodide (CsI:Na)", "Needle crystals convert X-rays to light (K-edges 33/36 keV)"),
                                        listOf("Photocathode", "Antimony Caesium (SbCs3)", "Photoelectrically emits electrons from light"),
                                        listOf("Electrodes", "Charged metal rings", "Focuses electron trajectories (electron lens)"),
                                        listOf("Accelerating Anode", "+25 kV potential", "Imparts 25 keV kinetic energy to electrons"),
                                        listOf("Output Phosphor", "Zinc cadmium sulphide (ZnCdS:Ag)", "Converts 25 keV electrons into green light")
                                    )
                                )
                            ),
                            whatYouMustUnderstand = listOf(
                                "Selecting magnification mode drops brightness (lower minification gain); ABC responds by driving up tube output, increasing patient entrance skin dose.",
                                "Linear gamma response: in fluoroscopy, output brightness is strictly linear with input X-ray exposure (gamma = 1), unlike film (gamma = 2 to 3)."
                            ),
                            whatYouShouldMemorize = listOf(
                                "Accelerating potential = 25 kV.",
                                "Total brightness gain is typically ~5000.",
                                "Input dose rate is typically 0.2 to 0.3 uGy/s."
                            ),
                            highYieldFacts = listOf(
                                "Conversion factor (Gx) decreases by ~10% per year due to phosphor deterioration, requiring higher tube dose rates to maintain monitor brightness.",
                                "CsI input phosphors are grown in vertical needles to act as internal light guides, minimizing lateral light diffusion."
                            ),
                            chapterSummary = listOf(
                                "The image intensifier converts X-rays to light (CsI), to electrons (SbCs3), accelerates them through 25 kV, and minifies them onto a small ZnCdS screen with 5000-fold brightness gain."
                            ),
                            learningObjectives = listOf(
                                "Calculate minification gain, flux gain, and conversion factor of an image intensifier.",
                                "Explain how electronic magnification alters focal point, image resolution, and patient dose."
                            )
                        ),
                        acquire = AcquireContent(
                            simpleExplanation = "An image intensifier is an electronic funnel: it catches X-rays over a large dinner plate, turns them into electrons, speeds them up with 25,000 volts, and squeezes them onto a postage-stamp screen, creating a blazing 5000-times brighter picture.",
                            stepByStepMechanism = listOf(
                                "1. Transmitted X-rays pass through curved Al/Ti vacuum window.",
                                "2. Photons absorb in CsI needle crystals, creating green/blue light.",
                                "3. Light immediately strikes SbCs3 photocathode, releasing photoelectrons.",
                                "4. A 25 kV electric field accelerates electrons down the tube.",
                                "5. Electrostatic focusing rings bend electron paths through an internal crossover focus.",
                                "6. High-velocity electrons smash into tiny ZnCdS output screen, glowing brightly.",
                                "7. In magnification mode, electrode voltage shifts the crossover closer to the input, projecting only the central zone across the full output."
                            ),
                            whyItHappens = "Photoelectric effect in photocathode and kinetic energy conversion in output phosphor under electrostatic fields.",
                            clinicalRelevance = "Magnification provides high resolution to position coronary stents, but the radiologist must be mindful that patient skin dose increases inversely with magnified field area.",
                            commonMisconception = "Misconception: Magnification increases total patient effective dose because the dose rate goes up. Reality: Skin dose rate goes up, but beam area is collimated down proportionately, so total Dose Area Product (DAP) and effective dose remain approximately constant or slightly decrease.",
                            analogy = "Using a magnifying glass in sunlight: focusing light into a smaller spot makes that spot much more intense, but the total light from the sun is unchanged.",
                            imageQualityAndDoseImpact = "Magnified fields improve spatial resolution from 1.2 lp/mm to 3.0 lp/mm on the monitor, but elevate entrance surface dose rate.",
                            interactiveDiagramType = "IMAGE_INTENSIFIER_SIM"
                        )
                    )
                )
            ),
            Section(
                id = "6.3",
                chapterId = 6,
                number = "6.3",
                title = "Automatic Brightness Control & DSA",
                printedPage = 94,
                pdfPage = 103,
                concepts = listOf(
                    Concept(
                        id = "6.3.1",
                        chapterId = 6,
                        sectionId = "6.3",
                        title = "ABC Dose Curves, Pulsed Fluoro & DSA Subtraction",
                        printedPage = 94,
                        pdfPage = 103,
                        prime = PrimeContent(
                            structuredNotes = listOf(
                                "Automatic Brightness Control (ABC) / Automatic Dose Rate Control (ADRC): feedback loop adjusting kV and mA to maintain constant brightness at output screen/camera.",
                                "ABC Control Curves: 1. Anti-isowatt (Curve A): kV and mA both increase with patient thickness up to max tube power (~400 W); 2. Iodine contrast (Curve B): locks kV at 60-65 kV (near Iodine 33 keV K-edge) while mA increases; 3. High-kV paediatric (Curve C): ramps kV rapidly to minimize patient dose.",
                                "Dose limits in fluoroscopy: maximum entrance skin dose (ESD) rate must not exceed 100 mGy/min for any field size; remedial action required if >50 mGy/min for standard patient.",
                                "Pulsed fluoroscopy: beam pulsed at 25-30 pulses/s down to 1-15 pulses/s (pulse width 2-20 ms). Grid-controlled tubes apply -2 kV to a grid electrode to rapidly turn electron flow on/off without generator lag.",
                                "Image artefacts: Veiling glare (internal light scatter in output window reducing contrast), Vignetting (image center is brighter than edges), Pincushion distortion (geometric magnification at edges from curved input screen), S-distortion (from external magnetic fields).",
                                "Digital Subtraction Angiography (DSA): pre-contrast mask image is subtracted from contrast image on a pixel-by-pixel basis. Logarithmic transformation is mandatory before subtraction due to exponential attenuation: ln(I_mask) - ln(I_contrast) = u_iodine × t.",
                                "DSA pixel shifting: manual or automatic realignment of the mask to correct for minor patient motion.",
                                "Flat plate detectors in fluoroscopy: a-Si TFT arrays with CsI scintillators replace II tube, eliminating veiling glare, vignetting, and geometric distortion completely."
                            ),
                            keyConcepts = listOf("ABC Dose Curves (Anti-isowatt vs Iodine)", "Pulsed Fluoroscopy (Grid-controlled)", "Veiling Glare & Vignetting", "Logarithmic Subtraction in DSA", "Flat Plate Dynamic Detectors"),
                            definitions = listOf(
                                DefinitionItem("Automatic Brightness Control (ABC)", "An automatic feedback system in fluoroscopy that adjusts generator kV and mA based on image receptor brightness to maintain optimal image quality."),
                                DefinitionItem("Digital Subtraction Angiography (DSA)", "A fluoroscopic imaging technique where a non-contrast mask image is subtracted from an image containing radiopaque contrast to isolate blood vessels."),
                                DefinitionItem("Veiling Glare", "The degradation of contrast in an image intensifier caused by scattered light, X-rays, and secondary electrons bouncing inside the tube.")
                            ),
                            equations = listOf(
                                "Maximum legal fluoroscopy ESD rate <= 100 mGy/min",
                                "Typical fluoroscopy ESD rate = 10 to 30 mGy/min",
                                "DSA subtraction: ln(I_mask) - ln(I_contrast) = u_iodine × thickness"
                            ),
                            tables = listOf(
                                RevisionTable(
                                    title = "Fluoroscopy ABC Dose Control Curves",
                                    headers = listOf("Curve", "kV Behavior", "mA Behavior", "Clinical Purpose"),
                                    rows = listOf(
                                        listOf("Anti-isowatt (Curve A)", "Increases with thickness", "Increases with thickness", "General fluoroscopy (balanced quality/dose)"),
                                        listOf("Iodine Contrast (Curve B)", "Held fixed at 60-65 kV", "Increases to maximum power", "Angiography (maximizes Iodine K-edge contrast)"),
                                        listOf("High-kV Low Dose (Curve C)", "Ramps rapidly to maximum kV", "Kept as low as possible", "Paediatrics (prioritizes dose reduction)")
                                    )
                                )
                            ),
                            whatYouMustUnderstand = listOf(
                                "Logarithmic conversion is required before DSA subtraction because X-ray attenuation is exponential; linear subtraction would leave incomplete cancellation of bone and soft tissue.",
                                "Subtracting images reduces SNR by sqrt(2) (noise adds in quadrature), requiring higher mA per frame in DSA than in unsubtracted fluoroscopy."
                            ),
                            whatYouShouldMemorize = listOf(
                                "Fluoroscopy ESD statutory limit = 100 mGy/min; investigation level = 50 mGy/min.",
                                "Iodine contrast curve operates at 60 to 65 kV.",
                                "Pulsing at 7.5 pulses/s reduces dose by approximately 50-70% compared to 30 pulses/s."
                            ),
                            highYieldFacts = listOf(
                                "A single minute of fluoroscopy at 50 mGy/min gives the patient a skin dose equal to ~15 pelvis radiographs!",
                                "Pincushion distortion is caused by the curved spherical shape of the image intensifier input screen."
                            ),
                            chapterSummary = listOf(
                                "Automatic brightness control optimizes fluoroscopy exposure factors along tailored curves; DSA achieves vessel isolation via logarithmic subtraction of mask and contrast images."
                            ),
                            learningObjectives = listOf(
                                "Compare anti-isowatt, iodine contrast, and high-kV ABC curves.",
                                "Explain why logarithmic subtraction is necessary in DSA and how pixel shifting corrects motion misregistration."
                            )
                        ),
                        acquire = AcquireContent(
                            simpleExplanation = "In DSA, the computer takes a snapshot before the dye arrives (the mask), then takes another snapshot when dye fills the vessels, and subtracts the first from the second. The bones and organs vanish, leaving pure glowing blood vessels.",
                            stepByStepMechanism = listOf(
                                "1. Patient is positioned; non-contrast 'mask' image frame is captured.",
                                "2. Iodine contrast is injected intravenously or intra-arterially.",
                                "3. Contrast fills target arterial tree.",
                                "4. Signals in mask and contrast frames are converted into logarithms.",
                                "5. Log mask is subtracted from log contrast frame pixel-by-pixel.",
                                "6. Static bone and soft tissue cancel out to zero; only iodine-filled vessels remain.",
                                "7. If patient twitches, pixel shifting nudges the mask by fractional pixels to eliminate edge bone misregistration."
                            ),
                            whyItHappens = "Beer-Lambert exponential attenuation law: subtraction of logs equals log of ratios, isolating the contrast attenuation term.",
                            clinicalRelevance = "DSA allows tiny catheterizations with dilute contrast volumes, minimizing nephrotoxicity in diabetic or renal patients.",
                            commonMisconception = "Misconception: Digital fluoroscopy doesn't use as much radiation as radiography. Reality: Continuous fluoroscopy at 30 mGy/min delivers substantial doses: 10 minutes of screening delivers 300 mGy skin dose, risking deterministic erythema.",
                            analogy = "Tracing on translucent paper: drawing an outline of a landscape, then superimposing a new drawing and deleting everything identical, leaving only the newly arrived car on the road.",
                            imageQualityAndDoseImpact = "Pulsed fluoroscopy at 15 pulses/sec halves the patient dose without sacrificing diagnostic perception of cardiac or catheter motion.",
                            interactiveDiagramType = "DSA_SUBTRACTION_SIM"
                        )
                    )
                )
            )
        )
    }
}
