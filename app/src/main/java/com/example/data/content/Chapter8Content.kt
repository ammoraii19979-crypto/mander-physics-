package com.example.data.content

import com.example.data.model.AcquireContent
import com.example.data.model.Concept
import com.example.data.model.DefinitionItem
import com.example.data.model.PrimeContent
import com.example.data.model.RevisionTable
import com.example.data.model.Section

object Chapter8Content {
    val sections: List<Section> by lazy {
        listOf(
            Section(
                id = "8.1",
                chapterId = 8,
                number = "8.1",
                title = "Sound Waves, Impedance & Reflection",
                printedPage = 127,
                pdfPage = 136,
                concepts = listOf(
                    Concept(
                        id = "8.1.1",
                        chapterId = 8,
                        sectionId = "8.1",
                        title = "Speed of Sound, Acoustic Impedance & Specular Reflection",
                        printedPage = 127,
                        pdfPage = 136,
                        prime = PrimeContent(
                            structuredNotes = listOf(
                                "Ultrasound: mechanical longitudinal pressure wave travelling through matter via compressions and rarefactions; frequencies > 20 kHz (diagnostic imaging 2 to 15 MHz).",
                                "Speed of sound (c): determined by medium density (rho) and bulk modulus (B, stiffness): c = sqrt(B / rho). Faster in stiff media (bone = 4080 m/s), slower in compressible media (air = 330 m/s).",
                                "Average speed in soft tissue: standard calibration is 1540 m/s (1.54 mm/us). Water = 1480 m/s; Fat = 1450 m/s; Muscle = 1580 m/s; Skull bone = 4080 m/s.",
                                "Wavelength lambda = c / f. At 3.5 MHz in soft tissue, lambda = 1.54 / 3.5 = 0.44 mm; at 7.5 MHz, lambda = 0.2 mm.",
                                "Acoustic impedance (Z): resistance of a medium to sound wave propagation: Z = rho × c (units: kg·m^-2·s^-1 or Rayls). Average soft tissue Z = 1.63 × 10^6 Rayls.",
                                "Specular reflection: occurs at large, smooth interfaces (boundary size >> lambda). Intensity reflection coefficient: R = ((Z2 - Z1) / (Z2 + Z1))^2.",
                                "Interface reflections: Soft tissue / Soft tissue: R ~ 0.1% to 1% (ideal for imaging internal organ parenchyma).",
                                "Soft tissue / Bone: R ~ 40-50% (strong reflection, causes dense acoustic shadowing).",
                                "Soft tissue / Air: R = 99.9% (virtually total reflection; acoustic coupling gel is mandatory to eliminate trapped air pockets!).",
                                "Scattering (diffuse reflection): occurs at small, rough structures (dimensions <= lambda), radiating sound in all directions; gives organs their characteristic speckle texture.",
                                "Refraction: change in direction of sound when crossing an interface at non-normal incidence with differing velocities, governed by Snell's Law: sin(theta1) / sin(theta2) = c1 / c2."
                            ),
                            keyConcepts = listOf("Speed of Sound (1540 m/s)", "Acoustic Impedance Z = rho × c", "Specular Reflection Coefficient R", "Acoustic Coupling Gel Requirement", "Snell's Law & Refraction"),
                            definitions = listOf(
                                DefinitionItem("Acoustic Impedance (Z)", "The product of the density of a medium and the speed of sound within it: Z = rho × c (measured in Rayls)."),
                                DefinitionItem("Intensity Reflection Coefficient (R)", "The fraction of incident sound wave intensity reflected back from an interface: R = ((Z2 - Z1) / (Z2 + Z1))^2."),
                                DefinitionItem("Specular Reflection", "Reflection occurring at large, flat boundaries (relative to wavelength) where angle of incidence equals angle of reflection.")
                            ),
                            equations = listOf(
                                "c = sqrt(B / rho)",
                                "lambda = c / f = 1.54 / f(MHz) mm",
                                "Z = rho × c (kg·m^-2·s^-1 or Rayls)",
                                "R = ((Z2 - Z1) / (Z2 + Z1))^2",
                                "Snell's Law: sin(theta1) / sin(theta2) = c1 / c2"
                            ),
                            tables = listOf(
                                RevisionTable(
                                    title = "Table 8.1: Acoustic Properties of Biological Media",
                                    headers = listOf("Material", "Speed of Sound (m/s)", "Density (kg/m^3)", "Impedance (×10^6 Rayls)"),
                                    rows = listOf(
                                        listOf("Air", "330", "1.2", "0.0004"),
                                        listOf("Fat", "1450", "920", "1.34"),
                                        listOf("Water", "1480", "1000", "1.48"),
                                        listOf("Soft Tissue (Mean)", "1540", "1060", "1.63"),
                                        listOf("Muscle", "1580", "1070", "1.71"),
                                        listOf("Bone (Skull)", "4080", "1900", "7.80")
                                    )
                                )
                            ),
                            whatYouMustUnderstand = listOf(
                                "Because the impedance mismatch between air and tissue is colossal (Z_air = 0.0004 vs Z_tissue = 1.63), 99.9% of sound reflects at dry skin. Coupling gel eliminates the air layer.",
                                "Acoustic shadowing occurs behind bone or gallstones because high impedance mismatch reflects ~50% and severe absorption attenuates the remainder, leaving zero sound beyond."
                            ),
                            whatYouShouldMemorize = listOf(
                                "Mean soft tissue speed of sound = 1540 m/s (1.54 mm/us).",
                                "Wavelength in tissue: at 3.5 MHz ~ 0.44 mm; at 5.0 MHz ~ 0.31 mm; at 10 MHz ~ 0.15 mm.",
                                "Soft tissue impedance Z ~ 1.63 × 10^6 Rayls."
                            ),
                            highYieldFacts = listOf(
                                "Sound travels faster in bone (4080 m/s) and slower in fat (1450 m/s). Speed errors assume 1540 m/s, causing structures behind fat to appear deeper than they actually are.",
                                "Refraction at the edges of fluid-filled cysts causes lateral edge shadows."
                            ),
                            chapterSummary = listOf(
                                "Ultrasound propagates as longitudinal waves at ~1540 m/s in soft tissue; reflection occurs at acoustic impedance boundaries, requiring acoustic gel to bypass air."
                            ),
                            learningObjectives = listOf(
                                "Calculate acoustic impedance, wavelength, and reflection coefficient at tissue boundaries.",
                                "Explain why acoustic gel is mandatory and why bone casts an acoustic shadow."
                            )
                        ),
                        acquire = AcquireContent(
                            simpleExplanation = "Sound moves through tissue like an echo down a canyon. Whenever it hits a boundary where density or stiffness changes (impedance mismatch), part bounces back as an echo. When it hits air, 100% bounces off—which is why the sonographer squirts gel on the skin.",
                            stepByStepMechanism = listOf(
                                "1. Transducer sends a pulse of ultrasound into the body.",
                                "2. Pulse travels at ~1540 m/s through homogeneous liver tissue.",
                                "3. Pulse encounters liver capsule/kidney boundary with slight impedance change (Z1 != Z2).",
                                "4. Approximately 1% of energy reflects back towards the probe as an echo; 99% transmits onward.",
                                "5. Probe detects the return echo; elapsed time gives exact target depth (d = c × t / 2).",
                                "6. If the pulse hits a gallstone, massive reflection and absorption stop the wave, leaving a black shadow behind."
                            ),
                            whyItHappens = "Conservation of acoustic energy and momentum at boundaries with differing characteristic acoustic impedances.",
                            clinicalRelevance = "Gallstones and renal calculi are diagnosed by identifying a bright hyperechoic anterior margin accompanied by posterior acoustic shadowing.",
                            commonMisconception = "Misconception: Sound travels faster in denser media. Reality: Speed depends on stiffness (bulk modulus) divided by density! If stiffness is equal, denser media are actually SLOWER. Bone is fast (4080 m/s) only because it is extraordinarily stiff.",
                            analogy = "Shouting across a room vs shouting into a brick wall: a curtain reflects a faint whisper; a solid brick wall bounces everything right back in your face.",
                            imageQualityAndDoseImpact = "Coupling gel ensures 100% acoustic transmission into the patient, maximizing signal-to-noise ratio with non-ionizing mechanical waves.",
                            interactiveDiagramType = "ULTRASOUND_REFLECTION"
                        )
                    )
                )
            ),
            Section(
                id = "8.4",
                chapterId = 8,
                number = "8.4",
                title = "Transducers, Resolution & Doppler",
                printedPage = 132,
                pdfPage = 141,
                concepts = listOf(
                    Concept(
                        id = "8.4.1",
                        chapterId = 8,
                        sectionId = "8.4",
                        title = "PZT Transducers, Axial/Lateral Resolution & Doppler",
                        printedPage = 132,
                        pdfPage = 141,
                        prime = PrimeContent(
                            structuredNotes = listOf(
                                "Transducer crystal: Lead Zirconate Titanate (PZT). Operates via Piezoelectric effect: mechanical stress produces electric charge, and alternating voltage produces mechanical vibration.",
                                "Resonance thickness: crystal thickness = half wavelength (thickness = lambda / 2 in PZT).",
                                "Backing (damping) block: absorbs backward sound waves and damps crystal ring-down to produce short Spatial Pulse Length (SPL = n × lambda, typically 2-3 cycles).",
                                "Matching layer: intermediate acoustic impedance (Z ~ 3 to 5 Rayls) and thickness = lambda / 4, maximizing sound transmission between PZT (Z=30) and skin (Z=1.6).",
                                "Beam zones: Near Field (Fresnel zone): length x = r^2 / lambda = D^2 / (4 × lambda). Beam is parallel with complex interference. Far Field (Fraunhofer zone): beam diverges: sin(theta) = 0.61 × lambda / r.",
                                "Axial Resolution: ability to separate two reflectors along the beam axis: Axial Resolution = SPL / 2. Independent of depth; improved by higher frequency and heavy damping.",
                                "Lateral Resolution: ability to separate reflectors perpendicular to beam axis: Lateral Resolution = Beam Width. Varies with depth; best at the focal zone.",
                                "Time-Gain Compensation (TGC): amplifiers boost late-arriving echoes exponentially to compensate for tissue attenuation (~1 dB/cm/MHz).",
                                "Doppler Effect: frequency shift fD caused by reflector motion: fD = (2 × v × f0 × cos(theta)) / c.",
                                "Doppler angle theta: must be between 30 and 60 degrees. At 90 deg, cos(90) = 0 (no Doppler shift detected!). Above 60 deg, angular error amplifies velocity calculation error.",
                                "Aliasing: occurs in Pulsed Doppler when Doppler shift exceeds Nyquist limit: fD > PRF / 2. Corrected by increasing PRF, switching to lower frequency probe, or shifting baseline."
                            ),
                            keyConcepts = listOf("Piezoelectric Effect (PZT)", "Matching Layer (lambda/4)", "Spatial Pulse Length & Axial Resolution", "Fresnel vs Fraunhofer Zones", "Doppler Equation & Angle (30-60 deg)", "Doppler Aliasing (PRF/2)"),
                            definitions = listOf(
                                DefinitionItem("Piezoelectric Effect", "The generation of an electric charge on the surfaces of certain asymmetric crystal materials (e.g. PZT) when subjected to mechanical compression, and vice versa."),
                                DefinitionItem("Axial Resolution", "The minimum distance between two reflectors along the line of the ultrasound beam that can be distinguished as separate echoes: Axial Resolution = SPL / 2."),
                                DefinitionItem("Doppler Shift (fD)", "The difference between transmitted and received ultrasound frequencies resulting from relative motion between transducer and blood cells: fD = (2 v f0 cos theta) / c.")
                            ),
                            equations = listOf(
                                "Crystal thickness = lambda_crystal / 2",
                                "Matching layer thickness = lambda_matching / 4",
                                "Fresnel Near Zone Length: x = r^2 / lambda",
                                "Axial Resolution = SPL / 2 = (n × lambda) / 2",
                                "Doppler Shift: fD = (2 × v × f0 × cos(theta)) / c",
                                "Nyquist Limit: fD_max = PRF / 2"
                            ),
                            tables = listOf(
                                RevisionTable(
                                    title = "Comparison: Axial Resolution vs Lateral Resolution",
                                    headers = listOf("Parameter", "Axial Resolution", "Lateral Resolution"),
                                    rows = listOf(
                                        listOf("Orientation", "Parallel to sound beam (along depth)", "Perpendicular to sound beam (across width)"),
                                        listOf("Determined by", "Spatial Pulse Length (SPL = n × lambda)", "Beam width at that depth"),
                                        listOf("Depth dependence", "Constant with depth", "Varies with depth (sharpest at focal zone)"),
                                        listOf("Typical value", "0.5 - 1.0 mm (always superior)", "1.0 - 3.0 mm (coarser)"),
                                        listOf("Methods to improve", "Higher frequency, heavier damping block", "Beam focusing (electronic delay phasing, lens)")
                                    )
                                )
                            ),
                            whatYouMustUnderstand = listOf(
                                "Axial resolution is ALWAYS superior to lateral resolution in clinical ultrasound.",
                                "Doppler angle must never be 90 degrees (cos 90 = 0 means zero shift). Optimum clinical angle is 45 to 60 degrees.",
                                "Aliasing in pulsed Doppler manifests as the waveform peak wrapping around into the reverse channel below the baseline."
                            ),
                            whatYouShouldMemorize = listOf(
                                "PZT thickness = lambda / 2; Matching layer thickness = lambda / 4.",
                                "Nyquist limit = PRF / 2.",
                                "Soft tissue attenuation rate ~ 0.5 to 1.0 dB/cm/MHz."
                            ),
                            highYieldFacts = listOf(
                                "Power Doppler displays the total amplitude (energy) of Doppler signals rather than frequency shift; it is angle-independent and highly sensitive to slow trickle flow, but gives no direction.",
                                "Reverberation artefacts (comet-tail/ring-down) arise from multiple internal reflections between two closely spaced reflective surfaces."
                            ),
                            chapterSummary = listOf(
                                "Piezoelectric transducers create short damped pulses for submillimeter axial resolution (SPL/2); Doppler measures blood flow velocities governed by the Doppler shift equation and Nyquist limit."
                            ),
                            learningObjectives = listOf(
                                "Explain how PZT thickness, damping block, and matching layer control transducer frequency and pulse length.",
                                "Calculate Doppler shift, blood velocity, and the Nyquist limit for aliasing.",
                                "Differentiate between Color Doppler, Power Doppler, and Spectral Pulsed Wave Doppler."
                            )
                        ),
                        acquire = AcquireContent(
                            simpleExplanation = "Axial resolution is how close two dots can be like beads on a string (front-to-back). Lateral resolution is how close they can be side-by-side. Doppler listens to the pitch change of blood cells rushing past: high pitch means flowing towards you, low pitch means flowing away.",
                            stepByStepMechanism = listOf(
                                "1. Electric pulse excites PZT crystal, which rings for 2-3 cycles.",
                                "2. Damping block chokes off ringing to create a short pulse length (SPL).",
                                "3. Wave reflects off moving red blood cells inside carotid artery.",
                                "4. Reflected frequency is shifted by Doppler effect: fD = 2 v f0 cos(theta) / c.",
                                "5. Machine compares received frequency to transmitted frequency.",
                                "6. If Doppler shift exceeds PRF/2, the top of the spectral wave wraps around to the bottom (aliasing).",
                                "7. Sonographer tilts the probe to keep angle theta between 30 and 60 degrees for accurate velocity readout."
                            ),
                            whyItHappens = "Wave propagation physics and classical Doppler frequency shift from moving point scatterers.",
                            clinicalRelevance = "Accurate grading of carotid artery stenosis (e.g. >70% internal carotid stenosis with peak systolic velocity >230 cm/s) relies strictly on holding Doppler angle <=60 degrees.",
                            commonMisconception = "Misconception: Color Doppler shows arterial blood as red and venous blood as blue. Reality: Color Doppler shows direction relative to the probe! Red means flowing TOWARDS the transducer; blue means flowing AWAY from the transducer.",
                            analogy = "Ambulance siren: rising pitch as it speeds toward you, falling pitch as it drives away. If you stand perpendicular to the road (90 degrees), you hear no pitch change at the exact moment it passes.",
                            imageQualityAndDoseImpact = "Ultrasound uses zero ionizing radiation; acoustic output is monitored via Thermal Index (TI < 1) and Mechanical Index (MI < 0.7) to prevent tissue heating and cavitation.",
                            interactiveDiagramType = "DOPPLER_SIMULATOR"
                        )
                    )
                )
            )
        )
    }
}
