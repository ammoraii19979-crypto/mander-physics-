package com.example.data.content

import com.example.data.model.AcquireContent
import com.example.data.model.Concept
import com.example.data.model.DefinitionItem
import com.example.data.model.PrimeContent
import com.example.data.model.RevisionTable
import com.example.data.model.Section

object Chapter1Content {
    val sections: List<Section> by lazy {
        listOf(
            Section(
                id = "1.1",
                chapterId = 1,
                number = "1.1",
                title = "Structure of the Atom",
                printedPage = 1,
                pdfPage = 10,
                concepts = listOf(
                    Concept(
                        id = "1.1.1",
                        chapterId = 1,
                        sectionId = "1.1",
                        title = "Atomic Structure and Electron Shells",
                        printedPage = 1,
                        pdfPage = 10,
                        prime = PrimeContent(
                            structuredNotes = listOf(
                                "An atom consists mainly of empty space with mass concentrated in a central nucleus containing A nucleons (Z protons and A-Z neutrons).",
                                "Z is the atomic number, which defines the element. A is the mass number.",
                                "Electrons orbit in discrete shells designated K, L, M, N outwards from the nucleus.",
                                "The outermost (valence) shell governs chemical, optical, and electrical properties, and cannot contain more than 8 electrons.",
                                "Metals possess 1 to 3 valence electrons that are easily detached ('free' conduction electrons).",
                                "The binding energy (EK > EL > EM) is the energy required to completely remove an electron from its shell against the positive nuclear pull."
                            ),
                            keyConcepts = listOf("Nucleons (Protons & Neutrons)", "Atomic Number (Z)", "Mass Number (A)", "Electron Binding Energy", "Valence Shell"),
                            definitions = listOf(
                                DefinitionItem("Nuclide", "A species of nucleus characterized by its atomic number Z and mass number A."),
                                DefinitionItem("Binding Energy", "The energy expended in completely removing an electron from an atom against the attractive electrostatic force of the nucleus (measured in eV or keV)."),
                                DefinitionItem("Ion Pair", "A detached negative electron and the remnant positively charged atom created during ionization.")
                            ),
                            equations = listOf(
                                "A = Z + N (Mass number = Protons + Neutrons)",
                                "1 eV = 1.6 × 10^-19 J"
                            ),
                            tables = listOf(
                                RevisionTable(
                                    title = "Table 1.2: K-shell Binding Energy (EK) of Imaging Elements",
                                    headers = listOf("Element", "Symbol", "Z", "EK (keV)"),
                                    rows = listOf(
                                        listOf("Aluminium", "Al", "13", "1.6"),
                                        listOf("Calcium", "Ca", "20", "4.0"),
                                        listOf("Molybdenum", "Mo", "42", "20.0"),
                                        listOf("Iodine", "I", "53", "33.0"),
                                        listOf("Barium", "Ba", "56", "37.0"),
                                        listOf("Gadolinium", "Gd", "64", "50.0"),
                                        listOf("Tungsten", "W", "74", "70.0"),
                                        listOf("Lead", "Pb", "82", "88.0")
                                    )
                                )
                            ),
                            whatYouMustUnderstand = listOf(
                                "K-shell binding energy increases sharply with atomic number Z (Tungsten = 70 keV, Molybdenum = 20 keV, Iodine = 33 keV).",
                                "An electron can only be ejected from the K-shell if the bombarding photon or electron has energy GREATER than EK."
                            ),
                            whatYouShouldMemorize = listOf(
                                "Tungsten: Z = 74, EK = 70 keV, EL = 12 keV, EM = 2 keV.",
                                "Molybdenum: Z = 42, EK = 20 keV, EL = 2.5 keV.",
                                "Iodine K-edge = 33 keV; Barium K-edge = 37 keV."
                            ),
                            highYieldFacts = listOf(
                                "Tungsten K-characteristic X-rays CANNOT be produced at tube voltages below 70 kV.",
                                "Ka photon energy in Tungsten = EK - EL = 70 - 12 = 58 keV.",
                                "Kb photon energy in Tungsten = EK - EM = 70 - 2 = 68 keV."
                            ),
                            chapterSummary = listOf(
                                "The inner shells (especially K-shell) are the locus of characteristic X-ray production and photoelectric absorption, while valence electrons govern electrical conduction."
                            ),
                            learningObjectives = listOf(
                                "Explain atomic composition, shell designations, and the relationship between Z and electron binding energy.",
                                "Recall the K-shell binding energies of Tungsten, Molybdenum, Iodine, Barium, and Lead."
                            )
                        ),
                        acquire = AcquireContent(
                            simpleExplanation = "Atoms have electron shells with fixed energy levels. Electrons closest to the nucleus (K-shell) are bound the tightest and require the most energy to knock out.",
                            stepByStepMechanism = listOf(
                                "1. Incident high-energy particle collides with an orbital electron.",
                                "2. If incident energy > EK, the electron is expelled as a photoelectron.",
                                "3. The atom is now ionized, leaving a vacancy (hole) in the K-shell.",
                                "4. An outer electron (L or M shell) transitions down to fill the hole.",
                                "5. The difference in binding energy is released as a characteristic X-ray photon."
                            ),
                            whyItHappens = "Electrostatic attraction between the positively charged nucleus (protons) and negative electrons creates a deep potential energy well.",
                            clinicalRelevance = "Dictates target materials in X-ray tubes (Tungsten vs Molybdenum) and choice of contrast agents (Iodine vs Barium) whose K-edges match diagnostic beam energies.",
                            commonMisconception = "Misconception: Tube kilovoltage determines characteristic photon energy. Reality: Characteristic photon energy is strictly an intrinsic property of the target material (Z). Tube kV only determines whether the threshold to excite them (>= 70 kV for W) is reached.",
                            analogy = "Bowling ball striking a heavy bowling pin: unless the ball has enough velocity (energy exceeding binding energy), the pin cannot be knocked out of its spot.",
                            imageQualityAndDoseImpact = "Characteristic peaks in mammography (Mo Ka 17.5 keV) give high subject contrast in soft tissue at low patient thickness.",
                            interactiveDiagramType = "ATOMIC_SHELLS"
                        )
                    )
                )
            ),
            Section(
                id = "1.2",
                chapterId = 1,
                number = "1.2",
                title = "Electromagnetic Radiation",
                printedPage = 3,
                pdfPage = 12,
                concepts = listOf(
                    Concept(
                        id = "1.2.1",
                        chapterId = 1,
                        sectionId = "1.2",
                        title = "Wave & Quantum Aspects & Inverse Square Law",
                        printedPage = 3,
                        pdfPage = 12,
                        prime = PrimeContent(
                            structuredNotes = listOf(
                                "Electromagnetic radiation travels across empty space at the speed of light c = 3 × 10^8 m/s in vacuo.",
                                "X-rays (emitted by X-ray tubes) and gamma rays (emitted by radioactive nuclei) have identical physical properties and differ only in their origin.",
                                "Wave model: sinusoidal oscillating electric and magnetic fields perpendicular to each other and direction of propagation; c = f × lambda.",
                                "Quantum model: stream of discrete packets (quanta) called photons; E = hf.",
                                "Useful numerical relation: E (in keV) = 1.24 / lambda (in nm).",
                                "Photon fluence: number of photons per unit area. Energy fluence: total energy per unit area.",
                                "Beam intensity (energy fluence rate) follows the Inverse Square Law for point sources in non-attenuating media: I1 / I2 = (d2 / d1)^2."
                            ),
                            keyConcepts = listOf("Dual Wave-Particle Nature", "Photon Energy Formula", "Photon Fluence", "Energy Fluence", "Inverse Square Law"),
                            definitions = listOf(
                                DefinitionItem("Photon", "A localized packet or quantum of electromagnetic energy travelling at velocity c."),
                                DefinitionItem("Photon Fluence", "The total number of photons passing through a unit cross-sectional area (photons / mm^2)."),
                                DefinitionItem("Energy Fluence", "The total amount of energy passing through a unit cross-sectional area (J / mm^2)."),
                                DefinitionItem("Beam Intensity", "The energy fluence rate, defined as total energy per unit cross-sectional area per unit time (W / mm^2).")
                            ),
                            equations = listOf(
                                "c = f × lambda (Velocity = Frequency × Wavelength)",
                                "E = hf (Photon Energy = Planck's Constant × Frequency)",
                                "E (keV) = 1.24 / lambda (nm)",
                                "I1 / I2 = (d2 / d1)^2 (Inverse Square Law)"
                            ),
                            tables = listOf(
                                RevisionTable(
                                    title = "Table 1.3: Electromagnetic Spectrum Summary",
                                    headers = listOf("Radiation", "Wavelength", "Frequency", "Energy"),
                                    rows = listOf(
                                        listOf("Radiowaves", "1000 - 0.1 m", "0.3 - 3000 MHz", "0.001 - 10 ueV"),
                                        listOf("Visible Light", "700 - 400 nm", "430 - 750 THz", "1.8 - 3 eV"),
                                        listOf("X- & Gamma rays", "1 nm - 0.1 pm", "3×10^5 - 3×10^9 THz", "1 keV - 10 MeV")
                                    )
                                )
                            ),
                            whatYouMustUnderstand = listOf(
                                "Doubling the distance from a point source reduces the radiation intensity to one quarter (1/4).",
                                "Inverse square law applies strictly to a point source without scattering or intervening attenuating material."
                            ),
                            whatYouShouldMemorize = listOf(
                                "E(keV) = 1.24 / lambda(nm). At 140 keV, lambda is approximately 0.01 nm.",
                                "c = 3 × 10^8 m/s in air/vacuum."
                            ),
                            highYieldFacts = listOf(
                                "X-rays and gamma rays have no mass and no electric charge.",
                                "Stepping back from 1 m to 2 m from a patient during fluoroscopy reduces staff scatter exposure by a factor of 4."
                            ),
                            chapterSummary = listOf(
                                "Electromagnetic energy propagates as transverse waves and interacts as quantized photons whose energy is inversely proportional to wavelength."
                            ),
                            learningObjectives = listOf(
                                "Apply the relationship between photon energy, wavelength, and frequency.",
                                "Calculate changes in radiation intensity using the inverse square law."
                            )
                        ),
                        acquire = AcquireContent(
                            simpleExplanation = "Radiation spreads out radially from a point source over the surface of an expanding sphere. Because surface area grows as radius squared, the energy per unit area drops inversely with distance squared.",
                            stepByStepMechanism = listOf(
                                "1. A point source emits N photons uniformly in all directions.",
                                "2. At distance d1, the photons are distributed over area 4*pi*d1^2.",
                                "3. At distance d2 = 2*d1, the same photons spread over area 4*pi*(2*d1)^2 = 4 times the area.",
                                "4. Therefore, the photon density (intensity) drops to 1/4th."
                            ),
                            whyItHappens = "Geometry of 3D spatial expansion with rectilinear propagation in straight lines.",
                            clinicalRelevance = "Distance is the single most powerful and cost-free radiation protection tool for radiologists and radiographers in fluoroscopy and interventional suites.",
                            commonMisconception = "Misconception: Inverse square law holds after a beam passes through a patient or filter. Reality: It strictly assumes no absorption or scatter and a point source.",
                            analogy = "Spraying a can of paint: at 10 cm it creates a small dense coat; at 20 cm it covers four times the wall area at 1/4 the thickness.",
                            imageQualityAndDoseImpact = "Increasing focus-to-film distance (FFD) spreads the beam over more skin surface, reducing patient skin entrance dose (ESD) for a given film dose.",
                            interactiveDiagramType = "INVERSE_SQUARE"
                        )
                    )
                )
            ),
            Section(
                id = "1.3",
                chapterId = 1,
                number = "1.3",
                title = "Production of X-rays",
                printedPage = 5,
                pdfPage = 14,
                concepts = listOf(
                    Concept(
                        id = "1.3.1",
                        chapterId = 1,
                        sectionId = "1.3",
                        title = "Bremsstrahlung and Characteristic Radiation",
                        printedPage = 5,
                        pdfPage = 14,
                        prime = PrimeContent(
                            structuredNotes = listOf(
                                "X-rays are produced when fast-moving electrons are suddenly decelerated upon impact with a metal target.",
                                "Energy conversion: >99% is converted into heat (unwanted outer-shell excitations); <1% is converted into X-rays.",
                                "X-ray tube components: negative cathode with incandescent tungsten filament (thermionic emission at ~2200 C) and positive anode target (tungsten Z=74).",
                                "Kilovoltage (kV, typically 30-150 kV) determines electron kinetic energy (keV) and maximum photon energy.",
                                "Tube current (mA, typically 0.5-1000 mA) is controlled by filament heating current (~10 A, 10 V). A small increase in filament temperature produces a large increase in mA.",
                                "Bremsstrahlung ('braking radiation'): bombarding electron penetrates inner shells, is deflected and slowed by the positive nuclear charge, emitting a single photon of energy up to the applied kV.",
                                "Bremsstrahlung forms a continuous spectrum; 80% or more of diagnostic X-rays are Bremsstrahlung.",
                                "Characteristic radiation: bombarding electron expels a K-shell electron; outer shell electron drops in, emitting a photon of discrete energy (EK - EL). Produces line spectrum.",
                                "Modern generators use High-Frequency (HF) technology converting mains to >1 kHz AC then steady DC with <1% voltage ripple."
                            ),
                            keyConcepts = listOf("Thermionic Emission", "Bremsstrahlung (Continuous Spectrum)", "Characteristic Radiation (Line Spectrum)", "X-ray Tube Efficiency", "High-Frequency Generators"),
                            definitions = listOf(
                                DefinitionItem("Bremsstrahlung", "Electromagnetic radiation produced by the deceleration of a fast charged particle (electron) deflected by the Coulomb field of an atomic nucleus."),
                                DefinitionItem("Thermionic Emission", "The boiling off of free electrons from the surface of a metal filament heated to incandescence (~2200 C)."),
                                DefinitionItem("Voltage Ripple", "The variation in high-tension potential during the generator cycle (Single-phase full wave = 100%, 3-phase 6-pulse = 13%, 3-phase 12-pulse = 4%, High-frequency < 1%).")
                            ),
                            equations = listOf(
                                "Emax (keV) = applied kV",
                                "Intensity is approximately proportional to kV^2 × mA",
                                "Efficiency = X-ray output / Electrical power supplied (proportional to kV × Z)"
                            ),
                            tables = listOf(
                                RevisionTable(
                                    title = "X-ray Generator Types & Waveforms",
                                    headers = listOf("Generator Type", "Pulses per Cycle", "Theoretical Ripple", "Clinical Application"),
                                    rows = listOf(
                                        listOf("Single-phase self-rectified", "1 (half-wave)", "100%", "Dental radiography (obsolete)"),
                                        listOf("Single-phase full-wave", "2", "100%", "Older mobile units (obsolete)"),
                                        listOf("Three-phase 6-pulse", "6", "13%", "Mid-generation static rooms"),
                                        listOf("Three-phase 12-pulse", "12", "4%", "High-power installations"),
                                        listOf("High-frequency (HF)", ">1000 Hz", "<1%", "Modern standard in all modern X-ray equipment")
                                    )
                                )
                            ),
                            whatYouMustUnderstand = listOf(
                                "Maximum photon energy in keV is numerically identical to peak kV applied across the tube.",
                                "The peak of the continuous spectrum (most common energy) is typically between 1/3 and 1/2 of the applied kV; effective energy is 50-60% of kV.",
                                "Increasing mA increases total output proportionately but DOES NOT alter spectrum shape or maximum energy.",
                                "Increasing kV shifts the spectrum rightwards, increases maximum energy, increases average energy, and dramatically increases total photon output (~kV^2)."
                            ),
                            whatYouShouldMemorize = listOf(
                                "Diagnostic X-ray efficiency is less than 1% (over 99% is dissipated as heat).",
                                "Tungsten K-characteristic lines: Ka = 58 keV, Kb = 68 keV (threshold 70 kV).",
                                "Molybdenum K-characteristic lines: Ka = 17.5 keV, Kb = 19.6 keV (threshold 20 kV)."
                            ),
                            highYieldFacts = listOf(
                                "Below 70 kV with a tungsten target, NO K-characteristic radiation is emitted; the beam is 100% Bremsstrahlung.",
                                "L-characteristic radiation of tungsten is only ~10 keV and cannot penetrate the tube glass envelope."
                            ),
                            chapterSummary = listOf(
                                "Electrons accelerated across the vacuum tube strike the anode, converting kinetic energy into a continuous Bremsstrahlung spectrum and discrete characteristic lines, with over 99% lost as heat."
                            ),
                            learningObjectives = listOf(
                                "Contrast the physical mechanisms of Bremsstrahlung and characteristic radiation.",
                                "Predict the effect of changing kV, mA, target material, and generator waveform on the X-ray spectrum."
                            )
                        ),
                        acquire = AcquireContent(
                            simpleExplanation = "When fast electrons slam into tungsten atoms, most brush past electrons making heat. A few graze atomic nuclei, brake sharply, and fling off their lost energy as Bremsstrahlung X-rays. A few knock out K-shell electrons, triggering an electron cascade that emits characteristic X-ray spikes.",
                            stepByStepMechanism = listOf(
                                "1. Filament current boils off electrons via thermionic emission.",
                                "2. High tube voltage (kV) accelerates electrons across vacuum towards the positive target (~0.5 speed of light).",
                                "3. Electrons penetrate a few micrometers into the tungsten target.",
                                "4. Bremsstrahlung: deflection by positive nuclear charge causes braking deceleration; lost kinetic energy emerges as an X-ray photon.",
                                "5. Characteristic: direct collision knocks out a K-shell electron; an L or M electron falls into the vacancy, emitting a photon equal to EK - EL or EK - EM.",
                                "6. Low-energy photons below 20 keV are filtered out by target and tube glass window."
                            ),
                            whyItHappens = "Conservation of energy: decelerating charged particles must radiate electromagnetic energy (Maxwell's equations and quantum transitions).",
                            clinicalRelevance = "Selecting appropriate kV controls beam penetration through thick anatomy; high-frequency generators deliver higher effective energy and less skin dose than older pulsating units.",
                            commonMisconception = "Misconception: Characteristic radiation energy increases if you increase the tube kV. Reality: Characteristic photon energy is completely independent of kV (as long as kV > EK). Increasing kV only increases the rate of production of characteristic photons.",
                            analogy = "Car braking: grazing near an obstacle and slamming the brakes produces screeching tires (Bremsstrahlung). Direct head-on collision knocking a specific parked car out of its numbered garage bay produces a specific replacement cascade (characteristic).",
                            imageQualityAndDoseImpact = "Higher kV increases beam penetration, lowering the entrance surface dose (ESD) required for adequate film/detector exposure.",
                            interactiveDiagramType = "XRAY_SPECTRUM"
                        )
                    )
                )
            ),
            Section(
                id = "1.4",
                chapterId = 1,
                number = "1.4",
                title = "Interaction of X-rays with Matter",
                printedPage = 9,
                pdfPage = 18,
                concepts = listOf(
                    Concept(
                        id = "1.4.1",
                        chapterId = 1,
                        sectionId = "1.4",
                        title = "Attenuation, Half-Value Layer & Coefficients",
                        printedPage = 9,
                        pdfPage = 18,
                        prime = PrimeContent(
                            structuredNotes = listOf(
                                "Three fates of photons in matter: Transmitted (unaffected primary beam), Absorbed (complete energy deposition), Scattered (deflected with or without energy loss).",
                                "Transmitted photons form the primary diagnostic image; absorbed and scattered photons constitute attenuation.",
                                "For a monoenergetic narrow beam, equal absorber thicknesses remove equal fractions of radiation (exponential attenuation): I = I0 * e^(-u*d).",
                                "Linear attenuation coefficient (u): probability of interaction per unit path length (units cm^-1 or mm^-1).",
                                "Half-Value Layer (HVL): thickness of stated material that reduces narrow-beam intensity by 50% (HVL = 0.693 / u).",
                                "Mass attenuation coefficient (u/rho): linear coefficient divided by density, independent of physical state/density, dependent only on Z and photon energy.",
                                "Heterogeneous beam attenuation: low-energy photons are preferentially absorbed, shifting average energy higher (beam hardening). Therefore, second HVL > first HVL.",
                                "Wide beam vs narrow beam: a wide beam scatters photons into the detector, resulting in an apparently larger measured HVL."
                            ),
                            keyConcepts = listOf("Attenuation", "Linear Attenuation Coefficient (u)", "Half-Value Layer (HVL)", "Mass Attenuation Coefficient (u/rho)", "Beam Hardening"),
                            definitions = listOf(
                                DefinitionItem("Attenuation", "The reduction in the intensity of an X-ray beam as it traverses matter, through the combined processes of absorption and scatter."),
                                DefinitionItem("Half-Value Layer (HVL)", "The thickness of a specified material that attenuates the intensity of a narrow X-ray beam to exactly half of its original value."),
                                DefinitionItem("Beam Hardening", "The progressive increase in the average/effective photon energy of a polyenergetic beam as it passes through an absorber due to preferential attenuation of lower-energy photons.")
                            ),
                            equations = listOf(
                                "I = I0 × e^(-u×d)",
                                "u = 0.693 / HVL",
                                "u/rho = Mass Attenuation Coefficient (cm^2/g)",
                                "n HVLs reduce intensity by factor 2^n (e.g. 10 HVLs reduce by 2^10 = 1024)"
                            ),
                            tables = listOf(
                                RevisionTable(
                                    title = "Typical HVL Values for Diagnostic X-ray Beams",
                                    headers = listOf("Material", "Typical HVL in Diagnostic Range"),
                                    rows = listOf(
                                        listOf("Soft Tissue", "30 mm (3 cm)"),
                                        listOf("Bone", "12 mm (1.2 cm)"),
                                        listOf("Lead (Pb)", "0.15 mm"),
                                        listOf("Aluminium (at 70 kV)", "2.5 mm Al"),
                                        listOf("Aluminium (at 120 kV)", "4.0 mm Al")
                                    )
                                )
                            ),
                            whatYouMustUnderstand = listOf(
                                "No matter how thick an absorber is, an X-ray beam is never completely absorbed (asymptotic exponential decay).",
                                "HVL measures beam quality (penetrating power). Higher HVL = harder, more penetrating beam.",
                                "For a polyenergetic beam, the second HVL is greater than the first HVL because the beam hardens as it passes through the first layer."
                            ),
                            whatYouShouldMemorize = listOf(
                                "u = 0.693 / HVL.",
                                "Diagnostic beam HVL: ~30 mm in tissue, ~12 mm in bone, ~0.15 mm in lead."
                            ),
                            highYieldFacts = listOf(
                                "Narrow beam geometry (good collimation and detector far from absorber) is mandatory for measuring true HVL to eliminate scatter.",
                                "Mass attenuation coefficient is independent of physical density: water and water vapor have different linear attenuation coefficients u, but the EXACT same mass attenuation coefficient u/rho."
                            ),
                            chapterSummary = listOf(
                                "Beam intensity decays exponentially through matter governed by linear attenuation coefficient u and HVL; polyenergetic diagnostic beams harden with depth."
                            ),
                            learningObjectives = listOf(
                                "Calculate transmitted intensity given initial intensity, attenuation coefficient, or number of HVLs.",
                                "Explain the difference between narrow-beam and wide-beam geometry and why beam hardening increases successive HVLs."
                            )
                        ),
                        acquire = AcquireContent(
                            simpleExplanation = "Imagine a crowd running through a dense forest: in every 10 meters, 20% of remaining runners bump into trees. The number of remaining runners shrinks by a fixed percentage per meter, never reaching absolute zero. Slower runners get caught first, so the surviving pack moves faster on average (beam hardening).",
                            stepByStepMechanism = listOf(
                                "1. Photons enter an absorber of thickness d.",
                                "2. Each photon has an independent probability (u per cm) of interacting with atomic electrons.",
                                "3. Lower energy photons have higher interaction probabilities (especially via photoelectric effect).",
                                "4. Lower energy photons are weeded out early; higher energy photons penetrate deeper.",
                                "5. The emerging beam has fewer total photons, but a higher average energy (hardened beam)."
                            ),
                            whyItHappens = "Quantum mechanics of stochastic atomic absorption and scattering cross-sections.",
                            clinicalRelevance = "Beam hardening causes CT cupping artefacts and dark bands between dense bones (e.g. petrous bones in the posterior fossa).",
                            commonMisconception = "Misconception: A beam can be 100% absorbed by a sufficiently thick lead sheet. Reality: Mathematically and physically, exponential decay approaches zero asymptotically; there is always a tiny residual transmitted fraction.",
                            analogy = "A sieve with multi-sized pebbles: the fine mesh catches the tiny pebbles immediately, leaving only large pebbles that easily pass through subsequent sieves.",
                            imageQualityAndDoseImpact = "Filtration hardens the beam before reaching the patient, cutting unnecessary entrance skin dose without reducing image-forming photons.",
                            interactiveDiagramType = "ATTENUATION_CURVE"
                        )
                    ),
                    Concept(
                        id = "1.4.2",
                        chapterId = 1,
                        sectionId = "1.4",
                        title = "Compton Effect vs Photoelectric Effect",
                        printedPage = 11,
                        pdfPage = 20,
                        prime = PrimeContent(
                            structuredNotes = listOf(
                                "Compton effect (inelastic/non-coherent scatter): photon collides with an outer-shell or 'free' electron (binding energy negligible).",
                                "Electron recoils with kinetic energy; scattered photon is deflected with reduced energy (longer wavelength).",
                                "Compton probability (sigma): independent of Z; proportional to physical density rho and electron density (Z/A, which is ~0.5 for all soft tissues except Hydrogen); inversely proportional to energy (~1/E).",
                                "Compton scatter angle: back-scattered photons (180 deg) suffer maximum energy loss; forward-scattered photons (0 deg) retain maximum energy. In diagnostic range, <=20% energy is absorbed, >80% is scattered.",
                                "Photoelectric absorption: photon collides with an inner-shell (bound) electron and is completely absorbed (disappears).",
                                "Photoelectron ejected with kinetic energy = Photon energy - EK.",
                                "Vacancy in K-shell is filled by outer electron, emitting characteristic radiation or an Auger electron. In soft tissue (low Z), characteristic photons are tiny (<0.5 keV) and immediately absorbed (pure absorption). In high Z (Iodine, Barium), characteristic photons can exit.",
                                "Photoelectric probability (tau): proportional to rho × Z^3 / E^3. Extremely sensitive to atomic number and photon energy.",
                                "K-absorption edge: sudden jump in photoelectric absorption when photon energy reaches the K-shell binding energy EK.",
                                "Crossover energy (equal probability of Compton and Photoelectric): ~30 keV for water and soft tissue; ~50 keV for bone; ~300 keV for Iodine/Barium; ~500 keV for Lead."
                            ),
                            keyConcepts = listOf("Compton Scattering", "Photoelectric Absorption", "Auger Electron", "K-Absorption Edge", "Crossover Energy"),
                            definitions = listOf(
                                DefinitionItem("Compton Scattering", "The inelastic scattering of a photon by an interaction with an unbound or loosely bound outer-shell electron, resulting in a deflected photon of lower energy and an energetic recoil electron."),
                                DefinitionItem("Photoelectric Absorption", "The total absorption of an incident photon by interaction with a bound inner-shell electron, resulting in ejection of a photoelectron and subsequent emission of characteristic radiation or Auger electrons."),
                                DefinitionItem("Auger Electron", "A monoenergetic electron ejected from an outer shell when the energy from an inner-shell transition is transferred internally instead of being emitted as a characteristic X-ray photon.")
                            ),
                            equations = listOf(
                                "Total linear coefficient u = sigma (Compton) + tau (Photoelectric)",
                                "Photoelectric probability tau is proportional to rho × Z^3 / E^3",
                                "Compton probability sigma is proportional to rho / E (independent of Z)",
                                "Photoelectron kinetic energy = E_photon - EK"
                            ),
                            tables = listOf(
                                RevisionTable(
                                    title = "Comparison: Compton Scattering vs Photoelectric Absorption",
                                    headers = listOf("Feature", "Photoelectric Effect", "Compton Scatter"),
                                    rows = listOf(
                                        listOf("Electron involved", "Inner shell (bound, K or L)", "Outer shell / free (unbound)"),
                                        listOf("Photon fate", "Completely absorbed (disappears)", "Scattered with lower energy"),
                                        listOf("Dependence on Z", "Proportional to Z^3", "Independent of Z"),
                                        listOf("Dependence on Energy", "Proportional to 1/E^3", "Weakly decreases (~1/E)"),
                                        listOf("Dependence on Density", "Proportional to rho", "Proportional to rho & electron density"),
                                        listOf("Diagnostic role", "Yields subject contrast (bone vs tissue)", "Causes scatter, degrades image contrast"),
                                        listOf("Predominates in", "Bone, contrast media, lead filters", "Soft tissue, water, air in diagnostic range")
                                    )
                                )
                            ),
                            whatYouMustUnderstand = listOf(
                                "Compton scatter dominates in soft tissue across the entire diagnostic range (above 30 keV).",
                                "Photoelectric absorption provides bone-soft tissue contrast because bone has effective Z=13.3 while soft tissue has Z=7.4 (contrast proportional to Z^3).",
                                "Raising kV reduces photoelectric absorption drastically (1/E^3) and decreases image contrast."
                            ),
                            whatYouShouldMemorize = listOf(
                                "Crossover energies: 30 keV (tissue/water), 50 keV (bone), 300 keV (iodine), 500 keV (lead).",
                                "Effective atomic numbers: Fat = 6.4, Soft tissue/water = 7.4, Air = 7.6, Bone = 13.3, Iodine = 53."
                            ),
                            highYieldFacts = listOf(
                                "Compton scatter is independent of Z! Bone and soft tissue have almost identical Compton mass attenuation coefficients within 10%.",
                                "The high attenuation of bone at 30-50 kV is almost entirely due to the Photoelectric effect (Z^3)."
                            ),
                            chapterSummary = listOf(
                                "Diagnostic imaging balances Photoelectric absorption (which yields anatomical contrast but deposits dose) and Compton scattering (which fogs the image with scatter)."
                            ),
                            learningObjectives = listOf(
                                "Compare Compton and photoelectric interactions in terms of atomic targets, energy dependence, and Z dependence.",
                                "Explain why lower kV produces higher image contrast between bone and soft tissue."
                            )
                        ),
                        acquire = AcquireContent(
                            simpleExplanation = "In Photoelectric absorption, an incoming photon is completely swallowed by an inner electron, which flies out. In Compton scattering, the photon ricochets off a loose outer electron like a billiard ball, losing some energy and flying off in a random direction to fog the detector.",
                            stepByStepMechanism = listOf(
                                "1. Incident photon enters tissue atom.",
                                "2. If it encounters a tightly bound K-electron and E >= EK, Photoelectric absorption occurs: photon vanished, photoelectron flies off, vacancy filled by outer electron with characteristic X-ray or Auger electron.",
                                "3. If it encounters a loosely bound outer electron, Compton effect occurs: photon deflected at angle theta, electron recoils, scattered photon exits patient towards room or detector.",
                                "4. At low photon energies (<30 keV) in soft tissue, Photoelectric dominates. Above 30 keV, Compton dominates.",
                                "5. In bone (Z=13.3), Photoelectric dominates up to 50 keV due to Z^3 dependence."
                            ),
                            whyItHappens = "Quantum interaction cross-sections: tight binding facilitates complete momentum transfer to the atom (Photoelectric); loose binding behaves as free particle collision (Compton).",
                            clinicalRelevance = "Mammography operates at 25-30 kV specifically to exploit Photoelectric absorption (Z^3) to distinguish between glandular tissue and fat, despite low atomic number differences.",
                            commonMisconception = "Misconception: Compton scatter is higher in lead than in tissue because lead is denser. Reality: Compton mass attenuation coefficient (sigma/rho) is nearly identical for lead, bone, water, and tissue! Lead stops X-rays because of massive Photoelectric absorption (Z=82).",
                            analogy = "Catching a fastball with a heavy mitt: you absorb all the energy (Photoelectric). Deflecting a tennis ball off the edge of a loose racket: the ball ricochets away with reduced speed (Compton).",
                            imageQualityAndDoseImpact = "Compton scattered photons produce a uniform grey veil over the image receptor, reducing subject contrast by up to a factor of 10 unless grids or air gaps are used.",
                            interactiveDiagramType = "COMPTON_VS_PE"
                        )
                    )
                )
            ),
            Section(
                id = "1.5",
                chapterId = 1,
                number = "1.5",
                title = "Filtration",
                printedPage = 15,
                pdfPage = 24,
                concepts = listOf(
                    Concept(
                        id = "1.5.1",
                        chapterId = 1,
                        sectionId = "1.5",
                        title = "Inherent, Added & K-Edge Filtration",
                        printedPage = 15,
                        pdfPage = 24,
                        prime = PrimeContent(
                            structuredNotes = listOf(
                                "Purpose of filtration: remove low-energy photons that would be absorbed in the patient's superficial tissues (skin) without reaching the image receptor.",
                                "Predominant process in filter: Photoelectric absorption (proportional to 1/E^3), preferentially absorbing low-energy photons.",
                                "Inherent filtration: filtration provided by the target itself, glass envelope, insulating oil, and exit window (typically ~1 mm Al equivalent).",
                                "Added filtration: uniform sheet of metal placed in the beam (typically ~1.5 mm Al).",
                                "Total filtration = Inherent + Added filtration. For general diagnostic radiology operating above 70 kV, total filtration must be AT LEAST 2.5 mm Al equivalent (1.5 mm Al for dental <= 70 kV).",
                                "Beryllium (Z=4) exit windows are used when inherent filtration must be minimized (e.g. mammography tubes).",
                                "Effects of increasing filtration: shrinks continuous spectrum, shifts peak to the right, increases minimum and effective energies, does NOT affect maximum energy, reduces total output, and increases exit-to-entrance dose ratio.",
                                "K-edge filters: materials like Erbium (Z=68, EK=57 keV) or Molybdenum (Z=42, EK=20 keV) absorb photons just above their K-edge while transmitting photons just below.",
                                "Compensating (wedge) filters: shaped filters used to equalize exposure across anatomy of varying thickness (e.g. thoracic spine, foot, or fluoroscopy edges)."
                            ),
                            keyConcepts = listOf("Inherent Filtration", "Added Filtration", "Total Filtration (>=2.5 mm Al)", "K-Edge Filters", "Compensating Wedge Filters"),
                            definitions = listOf(
                                DefinitionItem("Inherent Filtration", "The attenuation caused by permanent, non-removable parts of the X-ray tube assembly (glass insert, oil, window)."),
                                DefinitionItem("Total Filtration", "The sum of inherent filtration and added filtration, expressed as equivalent thickness of aluminium."),
                                DefinitionItem("K-Edge Filter", "A filter made of an element whose K-absorption edge is chosen to selectively shape the X-ray spectrum by absorbing both low energies and energies above the K-edge.")
                            ),
                            equations = listOf(
                                "Total Filtration = Inherent Filtration + Added Filtration >= 2.5 mm Al eq",
                                "Aluminium HVL at 70 kV ~ 2.5 mm Al; at 120 kV ~ 4.0 mm Al"
                            ),
                            tables = listOf(
                                RevisionTable(
                                    title = "Statutory Filtration Requirements",
                                    headers = listOf("Application", "Operating Tube Voltage", "Minimum Total Filtration"),
                                    rows = listOf(
                                        listOf("General Diagnostic", "> 70 kV", "2.5 mm Al equivalent"),
                                        listOf("Dental Equipment", "<= 70 kV", "1.5 mm Al equivalent"),
                                        listOf("Mammography", "25 - 35 kV", "0.03 mm Mo or 0.025 mm Rh (Beryllium window)")
                                    )
                                )
                            ),
                            whatYouMustUnderstand = listOf(
                                "Filtration reduces patient skin entrance dose significantly with negligible effect on the image-forming exit beam.",
                                "Filtration increases the HVL and effective energy of the beam, but DOES NOT change the maximum photon energy (which is set purely by kV)."
                            ),
                            whatYouShouldMemorize = listOf(
                                "Diagnostic minimum total filtration: 2.5 mm Al equivalent.",
                                "Inherent filtration is typically ~1.0 mm Al eq; added filtration is typically ~1.5 mm Al eq."
                            ),
                            highYieldFacts = listOf(
                                "Copper filters (0.1-0.3 mm) are more efficient than aluminium, but must be backed with aluminium on the patient side to absorb 9 keV copper characteristic X-rays.",
                                "Beyond a certain thickness, adding more filtration gives diminishing returns because it reduces total beam intensity, forcing longer exposure times and causing motion blur."
                            ),
                            chapterSummary = listOf(
                                "Filtration hardens the X-ray beam by selectively absorbing low-energy non-penetrating photons via Photoelectric absorption, protecting patient skin."
                            ),
                            learningObjectives = listOf(
                                "State the legal requirements for total filtration in general diagnostic and dental radiology.",
                                "Describe the spectral changes caused by aluminium and K-edge filters."
                            )
                        ),
                        acquire = AcquireContent(
                            simpleExplanation = "Low-energy X-rays have zero chance of passing through the patient to make an image—they only get absorbed in the patient's skin and increase cancer risk. Filters stop these useless soft X-rays before they touch the patient.",
                            stepByStepMechanism = listOf(
                                "1. Raw X-ray beam emerges from tungsten target containing photons from 0 to applied kV.",
                                "2. Photons pass through inherent filters (target, glass, oil) absorbing photons <15 keV.",
                                "3. Photons pass through added aluminium sheet (1.5 mm Al).",
                                "4. Photoelectric absorption strongly absorbs photons below 30 keV (tau proportional to 1/E^3).",
                                "5. Hardened beam emerges with low-energy cut-off around 20-25 keV.",
                                "6. Patient skin dose is dramatically reduced while image-forming exit photons pass unobstructed."
                            ),
                            whyItHappens = "Energy dependence of the photoelectric attenuation coefficient in aluminium (Z=13).",
                            clinicalRelevance = "In modern fluoroscopy, automated insertion of copper filters (0.1-0.2 mm Cu) saves up to 40% patient entrance dose in prolonged interventional cases.",
                            commonMisconception = "Misconception: Filtration reduces maximum photon energy. Reality: The maximum photon energy is completely unchanged; only the low and middle energies are attenuated.",
                            analogy = "Wearing sunglasses: UV and blinding low-frequency glare are filtered out, while visible detail passes through cleanly.",
                            imageQualityAndDoseImpact = "Filtration reduces skin dose; excessive filtration however reduces output, requiring higher mAs or tube loading.",
                            interactiveDiagramType = "FILTRATION_EFFECT"
                        )
                    )
                )
            ),
            Section(
                id = "1.6",
                chapterId = 1,
                number = "1.6",
                title = "Radiation Dosimetry",
                printedPage = 16,
                pdfPage = 25,
                concepts = listOf(
                    Concept(
                        id = "1.6.1",
                        chapterId = 1,
                        sectionId = "1.6",
                        title = "Absorbed Dose, Kerma, Ionization Chambers & DAP",
                        printedPage = 16,
                        pdfPage = 25,
                        prime = PrimeContent(
                            structuredNotes = listOf(
                                "Absorbed dose: energy deposited per unit mass of stated material. SI unit is the gray (Gy); 1 Gy = 1 J/kg. (Historic unit: 1 rad = 10 mGy, 1 Gy = 100 rad).",
                                "Kerma (Kinetic Energy Released to Matter): energy transferred per unit mass from photons to secondary electrons. For diagnostic energies, kerma and absorbed dose are practically equal.",
                                "Measurement in air: because direct measurement of temperature rise in tissue is impossible (1 Gy causes only ~0.1 mK rise), dose is measured in air (air kerma) and multiplied by tissue conversion factors.",
                                "Air equivalence: dry air has effective Z = 7.6, very close to soft tissue (Z = 7.4). Muscle-to-air dose conversion factor is close to unity (1.0 to 1.1) across the entire diagnostic range.",
                                "For compact bone (Z=13.3), the tissue-to-air dose conversion factor is ~5 at low keV and drops to 1.2 at 150 keV.",
                                "Thimble ionization chamber: plastic wall (Z~6 graphite coated) surrounding an air cavity with central collecting electrode; 100-300 V polarizing voltage prevents recombination. Measures air kerma (34 J deposited per coulomb collected in air).",
                                "Dose Area Product (DAP) meter: large, flat parallel-plate ionization chamber mounted on the collimator. Measures absorbed dose × beam area (units Gy·cm^2 or cGy·cm^2, uGy·m^2). Independent of distance from tube!",
                                "DAP is the standard metric for patient dose audit in fluoroscopy and general radiography.",
                                "Radiation quantity (air kerma) is proportional to kV^2 × mAs / d^2.",
                                "Radiation quality (penetrating power) is described by HVL in mm Al or effective energy (keV)."
                            ),
                            keyConcepts = listOf("Absorbed Dose (Gray)", "Kerma", "Air Kerma", "Thimble Ionization Chamber", "DAP Meter", "Radiation Quality (HVL)"),
                            definitions = listOf(
                                DefinitionItem("Absorbed Dose", "The energy deposited per unit mass of material by ionizing radiation (1 Gy = 1 J/kg)."),
                                DefinitionItem("Kerma", "The sum of initial kinetic energies of all charged ionizing particles liberated by uncharged ionizing particles per unit mass of material."),
                                DefinitionItem("Dose Area Product (DAP)", "The product of the absorbed dose to air in the beam and the cross-sectional area of the beam, measured in Gy·cm^2.")
                            ),
                            equations = listOf(
                                "1 Gy = 1 J/kg",
                                "DAP = Air Kerma × Beam Area (Gy·cm^2)",
                                "Air kerma is proportional to kV^2 × mAs / d^2",
                                "34 eV deposited per ion pair produced in dry air"
                            ),
                            tables = listOf(
                                RevisionTable(
                                    title = "Dose Conversion Factor from Air Kerma to Tissue",
                                    headers = listOf("Tissue Type", "Effective Z", "Conversion Ratio (Low keV)", "Conversion Ratio (150 keV)"),
                                    rows = listOf(
                                        listOf("Muscle / Soft tissue", "7.4", "1.0 - 1.1", "1.0 - 1.1"),
                                        listOf("Compact Bone", "13.3", "~5.0", "1.2"),
                                        listOf("Fat", "6.4", "~0.6", "1.1")
                                    )
                                )
                            ),
                            whatYouMustUnderstand = listOf(
                                "DAP is constant along the beam path with distance: as distance doubles, dose drops to 1/4 by inverse square law, but beam area quadruples (4x), so DAP (dose × area) remains unchanged!",
                                "Air is the standard dosimetry medium because its effective Z (7.6) closely matches soft tissue (7.4) and composition is invariant."
                            ),
                            whatYouShouldMemorize = listOf(
                                "1 Gy = 1 J/kg = 100 rad. 1 rad = 10 mGy.",
                                "Producing 1 ion pair in air requires an average of 34 eV."
                            ),
                            highYieldFacts = listOf(
                                "DAP meters are mounted on the collimator housing, inside the light beam diaphragm assembly, contributing to inherent filtration.",
                                "Collimating the beam reduces DAP and reduces scatter directly, lowering staff and patient risk."
                            ),
                            chapterSummary = listOf(
                                "Absorbed dose measures energy imparted per unit mass (Gy). In radiology, ionization in air is measured and converted to tissue dose, and DAP audits total radiation burden."
                            ),
                            learningObjectives = listOf(
                                "Distinguish absorbed dose, kerma, and exposure.",
                                "Explain how a thimble ionization chamber and DAP meter function and why DAP is independent of distance."
                            )
                        ),
                        acquire = AcquireContent(
                            simpleExplanation = "Absorbed dose is like the depth of water in a bucket (energy per kilogram). DAP is the total volume of water spilled over the whole floor (dose multiplied by the area of the beam).",
                            stepByStepMechanism = listOf(
                                "1. X-ray photons strike the plastic thimble chamber wall, ejecting secondary electrons into the air cavity.",
                                "2. Electrons ionize air molecules, producing positive ions and electrons (~34 eV per ion pair).",
                                "3. A polarizing voltage (100-300 V) pulls electrons to the positive central anode and positive ions to the cathode.",
                                "4. The flow of collected charge is measured by an electrometer.",
                                "5. Current is proportional to air kerma rate; total charge is proportional to air kerma."
                            ),
                            whyItHappens = "Conservation of charge and gas ionization physics.",
                            clinicalRelevance = "DAP values are recorded automatically in modern DICOM image headers and audited against National Diagnostic Reference Levels (NDRLs).",
                            commonMisconception = "Misconception: Moving the DAP meter closer or further from the X-ray tube changes the DAP reading. Reality: DAP is invariant with distance because dose decreases as 1/d^2 while area increases as d^2.",
                            analogy = "A flashlight beam: close up, it makes a tiny blinding circle. Far away, it makes a huge dim circle. The total light output (DAP) passing through any cross-section is exactly the same.",
                            imageQualityAndDoseImpact = "Collimating the field size reduces DAP directly, reducing total radiation risk to the patient and scatter to staff.",
                            interactiveDiagramType = "DAP_INVARIANCE"
                        )
                    )
                )
            ),
            Section(
                id = "1.7",
                chapterId = 1,
                number = "1.7",
                title = "Luminescence",
                printedPage = 19,
                pdfPage = 28,
                concepts = listOf(
                    Concept(
                        id = "1.7.1",
                        chapterId = 1,
                        sectionId = "1.7",
                        title = "Fluorescence, Phosphorescence & PSL",
                        printedPage = 19,
                        pdfPage = 28,
                        prime = PrimeContent(
                            structuredNotes = listOf(
                                "Luminescence: process in which a material absorbs energy from radiation and re-emits it as visible light.",
                                "Fluorescence: instantaneous emission of light following energy input (delay time < 10^-6 s). Used in intensifying screens and input phosphors.",
                                "Phosphorescence: delayed light emission ('afterglow', delay > 10^-6 s). Undesirable in radiography as it causes ghost images.",
                                "Band theory: valence band is full; conduction band is empty; forbidden zone lies between. Impurities introduce discrete unoccupied energy levels called electron traps.",
                                "Thermoluminescence (TLD): trapped electrons require heat (~250 C) to escape back to valence band, releasing light proportional to radiation dose (used in LiF personal dosimeters).",
                                "Photostimulable Luminescence (PSL): trapped electrons require red laser light stimulation to escape, releasing blue light. This is the foundation of Computed Radiography (CR, BaFX:Eu)."
                            ),
                            keyConcepts = listOf("Fluorescence", "Phosphorescence", "Electron Traps", "Thermoluminescence (TLD)", "Photostimulable Luminescence (PSL)"),
                            definitions = listOf(
                                DefinitionItem("Fluorescence", "The prompt emission of light by an excited phosphor within 10^-6 seconds of radiation absorption."),
                                DefinitionItem("Phosphorescence", "The delayed emission of light (afterglow) occurring after 10^-6 seconds due to metastable electron traps."),
                                DefinitionItem("Photostimulable Phosphor", "A phosphor (e.g. BaFBr:Eu) that stores absorbed X-ray energy in electron traps until read out by stimulation with a laser beam.")
                            ),
                            equations = listOf(
                                "Delay threshold: t < 10^-6 s = Fluorescence; t > 10^-6 s = Phosphorescence"
                            ),
                            tables = listOf(
                                RevisionTable(
                                    title = "Luminescent Phenomena in Radiology",
                                    headers = listOf("Phenomenon", "Mechanism", "Stimulation Trigger", "Clinical Application"),
                                    rows = listOf(
                                        listOf("Fluorescence", "Direct valence-conduction de-excitation", "Immediate (<10^-6 s)", "Intensifying screens, Image Intensifier"),
                                        listOf("Phosphorescence", "Metastable traps with slow thermal escape", "Delayed (afterglow)", "Generally an unwanted artefact"),
                                        listOf("Thermoluminescence", "Thermal liberation from deep traps", "Heating to ~250 C", "TLD badges (LiF:Mg,Ti)"),
                                        listOf("Photostimulable (PSL)", "Optical liberation from traps", "Red laser light scanning", "Computed Radiography (CR) plates")
                                    )
                                )
                            ),
                            whatYouMustUnderstand = listOf(
                                "Intensifying screens must use fluorescence, not phosphorescence, to prevent latent images from previous patients persisting.",
                                "Computed Radiography utilizes photostimulable luminescence where energy is stored until scanned by a laser."
                            ),
                            whatYouShouldMemorize = listOf(
                                "Arbitrary dividing time between fluorescence and phosphorescence is 10^-6 s (1 microsecond).",
                                "CR phosphor: Barium fluorohalide doped with europium (BaFX:Eu)."
                            ),
                            highYieldFacts = listOf(
                                "Scintillators used in gamma cameras (NaI:Tl) and PET (BGO, LSO) are crystalline luminescent phosphors.",
                                "The intensity of emitted light is strictly proportional to absorbed radiation energy."
                            ),
                            chapterSummary = listOf(
                                "Luminescence converts invisible X-ray photon energy into measurable visible light via electron band transitions, underpinning film screens, CR, and scintillators."
                            ),
                            learningObjectives = listOf(
                                "Distinguish fluorescence and phosphorescence by decay time constant.",
                                "Explain the band theory of electron trapping in TLD and photostimulable phosphors."
                            )
                        ),
                        acquire = AcquireContent(
                            simpleExplanation = "Radiation kicks electrons up into a higher energy band. In fluorescence, they immediately tumble back down, flashing light. In storage phosphors, they get stuck in potholes (electron traps) and only fall down when we heat them (TLD) or shine a red laser on them (CR).",
                            stepByStepMechanism = listOf(
                                "1. X-ray photon interaction excites electrons from the filled valence band across the forbidden gap into the conduction band.",
                                "2. Electrons wander in the conduction band until falling into impurity 'electron traps'.",
                                "3. For fluorescence: electrons immediately drop back to holes in the valence band, releasing visible light photons.",
                                "4. For photostimulable phosphor (CR): electrons remain trapped for hours (latent image).",
                                "5. Red laser beam imparts energy to trapped electrons, kicking them back to conduction band.",
                                "6. They drop into valence band holes, emitting blue light that is captured by a photomultiplier tube."
                            ),
                            whyItHappens = "Solid-state crystal band structure and activator impurity dopants.",
                            clinicalRelevance = "Computed Radiography (CR) plates can be reused thousands of times by erasing residual trapped electrons with intense bright light after laser readout.",
                            commonMisconception = "Misconception: CR plates develop an image like photographic film. Reality: CR plates store trapped electrons that must be optically stimulated by a laser to release light.",
                            analogy = "A mousetrap: loading the trap with energy (radiation). The trap holds the energy until a trigger springs it (laser or heat), releasing the stored energy instantly.",
                            imageQualityAndDoseImpact = "Thicker phosphor screens absorb more X-rays (higher speed, lower dose) but increase light spread (poorer spatial resolution).",
                            interactiveDiagramType = "BAND_THEORY"
                        )
                    )
                )
            )
        )
    }
}
