package com.example.data.content

import com.example.data.model.AcquireContent
import com.example.data.model.Concept
import com.example.data.model.DefinitionItem
import com.example.data.model.PrimeContent
import com.example.data.model.RevisionTable
import com.example.data.model.Section

object Chapter9Content {
    val sections: List<Section> by lazy {
        listOf(
            Section(
                id = "9.1",
                chapterId = 9,
                number = "9.1",
                title = "Principles of NMR & Relaxation",
                printedPage = 145,
                pdfPage = 154,
                concepts = listOf(
                    Concept(
                        id = "9.1.1",
                        chapterId = 9,
                        sectionId = "9.1",
                        title = "Larmor Equation, T1 Recovery & T2 Decay",
                        printedPage = 145,
                        pdfPage = 154,
                        prime = PrimeContent(
                            structuredNotes = listOf(
                                "Nuclei with an odd number of protons or neutrons (e.g. 1H hydrogen, 31P, 23Na) possess nuclear spin and an intrinsic magnetic dipole moment.",
                                "Hydrogen 1H is imaged clinically because of its high biological abundance in water and fat, and large gyromagnetic ratio gamma = 42.6 MHz/Tesla.",
                                "In an external magnetic field B0, spins align either parallel (low energy, slight excess) or antiparallel (high energy), creating net longitudinal magnetization Mz.",
                                "Spins precess around B0 at the Larmor frequency: f0 = gamma × B0 / (2*pi). At 1.5 Tesla, f0 = 42.6 × 1.5 = 63.9 MHz (radiofrequency range).",
                                "A 90-degree RF pulse matching the Larmor frequency tips Mz into the transverse plane, creating coherent transverse magnetization Mxy.",
                                "T1 Relaxation (Spin-Lattice / Longitudinal recovery): energy is released from spins back into surrounding molecular lattice. Exponential regrowth of Mz: Mz(t) = M0 × (1 - e^(-t/T1)).",
                                "T1 values: Water/CSF has long T1 (~3000 ms); Fat has short T1 (~250 ms) because fat molecular tumbling rate matches Larmor frequency; Brain white matter ~700 ms; Grey matter ~900 ms.",
                                "T2 Relaxation (Spin-Spin / Transverse decay): spins exchange energy and lose phase coherence due to local magnetic field fluctuations. Exponential decay of Mxy: Mxy(t) = M0 × e^(-t/T2). T2 is always shorter than or equal to T1.",
                                "T2 values: Water/CSF has long T2 (~1000 ms); Fat has short T2 (~80 ms); Liver/muscle ~40-50 ms.",
                                "T2* ('T2-star'): faster transverse dephasing caused by true T2 spin-spin interactions PLUS static magnetic field inhomogeneities (delta B0): 1/T2* = 1/T2 + gamma × delta B0."
                            ),
                            keyConcepts = listOf("Larmor Equation (f0 = gamma B0)", "Net Magnetization (Mz)", "T1 Spin-Lattice Recovery", "T2 Spin-Spin Decay", "T2* and Field Inhomogeneity"),
                            definitions = listOf(
                                DefinitionItem("Larmor Equation", "The equation defining the precession frequency of a nuclear spin in a magnetic field: f0 = gamma × B0 / (2*pi)."),
                                DefinitionItem("T1 Relaxation Time", "The time required for longitudinal magnetization Mz to recover to 63% of its equilibrium value following an RF excitation pulse."),
                                DefinitionItem("T2 Relaxation Time", "The time required for transverse magnetization Mxy to decay to 37% of its initial value due to spin-spin dephasing.")
                            ),
                            equations = listOf(
                                "f0 = gamma × B0 (for 1H, gamma/2pi = 42.6 MHz/T)",
                                "At 1.5 T: f0 = 63.9 MHz; At 3.0 T: f0 = 127.8 MHz",
                                "Mz(t) = M0 × (1 - e^(-t/T1))",
                                "Mxy(t) = M0 × e^(-t/T2)",
                                "1 / T2* = 1 / T2 + gamma × delta B0"
                            ),
                            tables = listOf(
                                RevisionTable(
                                    title = "Table 9.1: Typical T1 and T2 Relaxation Times at 1.5 Tesla",
                                    headers = listOf("Tissue", "T1 (ms)", "T2 (ms)", "Appearance on T1W", "Appearance on T2W"),
                                    rows = listOf(
                                        listOf("Fat", "250 ms (Short)", "80 ms", "Bright (Hyperintense)", "Intermediate / Grey"),
                                        listOf("Water / CSF", "3000 ms (Long)", "1000 ms (Long)", "Dark (Hypointense)", "Bright (Hyperintense)"),
                                        listOf("Brain White Matter", "700 ms", "80 ms", "Light grey", "Darker grey"),
                                        listOf("Brain Grey Matter", "900 ms", "100 ms", "Darker grey", "Lighter grey"),
                                        listOf("Cortical Bone", "Very Long", "< 1 ms", "Completely Black (No signal)", "Completely Black"),
                                        listOf("Air", "-", "-", "Black (Signal void)", "Black (Signal void)")
                                    )
                                )
                            ),
                            whatYouMustUnderstand = listOf(
                                "Fat is bright on T1-weighted images because its carbon bonds tumble near the Larmor frequency, facilitating rapid energy transfer (short T1).",
                                "Water/CSF is dark on T1-weighted images (slow recovery) and bright on T2-weighted images (slow dephasing).",
                                "T2 is always shorter than or equal to T1 for any biological tissue."
                            ),
                            whatYouShouldMemorize = listOf(
                                "Hydrogen gyromagnetic ratio: gamma / 2pi = 42.6 MHz/T.",
                                "Larmor frequency at 1.5 T = 63.9 MHz; at 3.0 T = 127.8 MHz.",
                                "T1 recovery curve reaches 63% at t = T1; T2 decay drops to 37% at t = T2."
                            ),
                            highYieldFacts = listOf(
                                "Cortical bone and air appear black on all standard MRI sequences because bone protons are rigidly bound with near-zero T2 (<1 ms), and air has negligible proton density.",
                                "Increasing field strength B0 from 1.5 T to 3.0 T increases T1 times (tissues take longer to recover), while T2 times remain nearly constant."
                            ),
                            chapterSummary = listOf(
                                "Proton spins precess at the Larmor frequency (42.6 MHz/T); radiofrequency pulses tip magnetization into the transverse plane, where T1 spin-lattice recovery and T2 spin-spin decay dictate tissue contrast."
                            ),
                            learningObjectives = listOf(
                                "Apply the Larmor equation to calculate resonance frequency at different field strengths.",
                                "Differentiate T1, T2, and T2* relaxation mechanisms and predict tissue brightness on T1 and T2 weighted images."
                            )
                        ),
                        acquire = AcquireContent(
                            simpleExplanation = "Protons in your body act like tiny spinning compass needles. The giant MRI magnet lines them up. A radio wave knocks them sideways. As they stand back up (T1 recovery) and get out of sync with each other (T2 decay), they broadcast faint radio signals that the MRI antenna picks up.",
                            stepByStepMechanism = listOf(
                                "1. Patient enters strong static magnetic field B0 (e.g. 1.5 Tesla).",
                                "2. Millions of hydrogen protons align with the field, creating net vector Mz.",
                                "3. A 90-degree radiofrequency pulse tips the vector flat into the XY plane (Mxy).",
                                "4. RF pulse turns off; spinning protons induce an electric voltage in the receiver coil (Free Induction Decay).",
                                "5. Spins interact with the thermal lattice, standing back up along the z-axis (T1 recovery).",
                                "6. Spins dephase and cancel each other out in the XY plane (T2 decay).",
                                "7. Tissues with short T1 (fat) recover quickly, yielding high signal if sampled early.",
                                "8. Tissues with long T2 (water) stay in phase longer, yielding high signal if sampled late."
                            ),
                            whyItHappens = "Quantum magnetic resonance and thermodynamic energy dissipation in rotating frames of reference.",
                            clinicalRelevance = "Detecting multiple sclerosis plaques, stroke edema, or brain tumors relies on their high water content appearing bright on T2-weighted MRI.",
                            commonMisconception = "Misconception: Protons flip physically 180 degrees back and forth. Reality: Protons undergo quantum precession; the net statistical magnetization vector tips into the transverse plane and spirals back to equilibrium.",
                            analogy = "T1 is runners standing back up after falling down. T2 is runners who start running in step, gradually getting out of sync with each other as time goes on.",
                            imageQualityAndDoseImpact = "MRI uses non-ionizing RF and magnetic fields; SNR increases linearly with field strength B0.",
                            interactiveDiagramType = "MRI_RELAXATION_SIM"
                        )
                    )
                )
            ),
            Section(
                id = "9.3",
                chapterId = 9,
                number = "9.3",
                title = "Spin Echo, Sequences & Encoding",
                printedPage = 150,
                pdfPage = 159,
                concepts = listOf(
                    Concept(
                        id = "9.3.1",
                        chapterId = 9,
                        sectionId = "9.3",
                        title = "Spin Echo, TR/TE Weighting, k-Space & Fast Sequences",
                        printedPage = 150,
                        pdfPage = 159,
                        prime = PrimeContent(
                            structuredNotes = listOf(
                                "Spin Echo (SE) sequence: 90 deg excitation pulse followed at time TE/2 by a 180 deg refocusing pulse. The 180 deg pulse inverts the dephasing spins, causing them to rephase and produce a 'spin echo' at Echo Time (TE).",
                                "The 180 deg pulse cancels out static magnetic field inhomogeneities (delta B0), measuring true T2 instead of T2*.",
                                "Repetition Time (TR): time between successive 90 deg excitation pulses. Echo Time (TE): time from 90 deg pulse to peak of spin echo.",
                                "Image Weighting Rules: 1. T1-Weighted (T1W): Short TR (<500 ms) and Short TE (<20 ms). Short TR emphasizes T1 recovery differences; short TE minimizes T2 decay.",
                                "2. T2-Weighted (T2W): Long TR (>2000 ms) and Long TE (>80 ms). Long TR allows all tissues to fully recover Mz (erasing T1 differences); long TE allows water and tissue T2 dephasing differences to emerge.",
                                "3. Proton Density Weighted (PDW): Long TR (>2000 ms) and Short TE (<20 ms). Erases both T1 and T2 differences, imaging pure hydrogen proton concentration.",
                                "Spatial encoding triad: 1. Slice Selection Gradient (Gss) applied during RF pulse; 2. Phase Encoding Gradient (Gpe) applied between RF and readout; 3. Frequency Encoding Gradient (Gro) applied during echo readout.",
                                "k-space: raw data matrix of spatial frequencies. Central k-space lines contain low spatial frequencies governing image contrast and bulk SNR; Peripheral k-space lines contain high spatial frequencies governing sharp edge resolution.",
                                "Inversion Recovery (IR): 180 deg inverting pulse followed by delay TI (Inversion Time). Null point occurs when Mz passes through zero: TI_null = T1 × ln(2) = 0.693 × T1.",
                                "STIR (Short TI Inversion Recovery): TI ~ 150 ms at 1.5 T suppresses fat signal completely. Cannot be used with Gadolinium contrast!",
                                "FLAIR (Fluid Attenuated Inversion Recovery): long TI ~ 2000 ms at 1.5 T suppresses free CSF water, highlighting periventricular white matter lesions.",
                                "Gradient Echo (GRE): uses flip angles <90 deg and bipolar gradient reversals instead of 180 deg pulses; very fast, but preserves T2* susceptibility (shows blood products/hemosiderin as black blooming)."
                            ),
                            keyConcepts = listOf("Spin Echo (90 - 180 Pulse)", "TR and TE Parameters", "T1W, T2W, and PDW Weighting", "Spatial Encoding (Gss, Gpe, Gro)", "k-Space Organization", "STIR and FLAIR Inversion Recovery"),
                            definitions = listOf(
                                DefinitionItem("Repetition Time (TR)", "The time interval between successive 90-degree excitation pulses in a pulse sequence."),
                                DefinitionItem("Echo Time (TE)", "The time interval between the initial 90-degree RF pulse and the peak of the recorded spin echo signal."),
                                DefinitionItem("k-Space", "An abstract spatial frequency domain matrix in which raw MRI signal data is digitized before 2D Fourier transformation into an image.")
                            ),
                            equations = listOf(
                                "T1W: Short TR (<500 ms), Short TE (<20 ms)",
                                "T2W: Long TR (>2000 ms), Long TE (>80 ms)",
                                "PDW: Long TR (>2000 ms), Short TE (<20 ms)",
                                "Inversion Recovery Null Time: TI = 0.693 × T1",
                                "Scan Time = TR × Number of Phase Encoding Steps × Number of Excitations (NEX)"
                            ),
                            tables = listOf(
                                RevisionTable(
                                    title = "Table 9.2: Summary of MRI Pulse Sequence Parameters",
                                    headers = listOf("Sequence Weighting", "TR (ms)", "TE (ms)", "Fat Signal", "Fluid / CSF Signal"),
                                    rows = listOf(
                                        listOf("T1-Weighted (T1W)", "< 500 ms", "< 20 ms", "Bright", "Dark"),
                                        listOf("T2-Weighted (T2W)", "> 2000 ms", "> 80 ms", "Intermediate", "Bright"),
                                        listOf("Proton Density (PDW)", "> 2000 ms", "< 20 ms", "Intermediate", "Intermediate-Bright"),
                                        listOf("STIR (Fat Suppressed)", "> 3000 ms", "> 60 ms (TI ~ 150 ms)", "Completely Null / Black", "Bright"),
                                        listOf("FLAIR (CSF Suppressed)", "> 6000 ms", "> 100 ms (TI ~ 2000 ms)", "Intermediate", "Completely Null / Black")
                                    )
                                )
                            ),
                            whatYouMustUnderstand = listOf(
                                "Central k-space governs contrast; if central k-space data is corrupted, contrast is ruined. Peripheral k-space governs edges; if peripheral data is lost, the image becomes blurry.",
                                "STIR suppresses anything with short T1 (fat), so Gadolinium-enhanced lesions will ALSO be inadvertently suppressed on STIR! Use fat-saturated T1W instead.",
                                "Gradient echo lacks the 180 deg refocusing pulse, so it reflects T2* (susceptibility) and is exquisitely sensitive to acute/chronic hemorrhage and calcification."
                            ),
                            whatYouShouldMemorize = listOf(
                                "TR and TE rules: T1 = Short TR, Short TE; T2 = Long TR, Long TE; PD = Long TR, Short TE.",
                                "STIR TI ~ 150 ms; FLAIR TI ~ 2000 ms at 1.5 T.",
                                "Total scan time formula: Scan Time = TR × N_phase × NEX."
                            ),
                            highYieldFacts = listOf(
                                "Chemical shift artefact occurs because fat protons precess 3.5 ppm (220 Hz at 1.5 T) slower than water protons due to electron cloud shielding.",
                                "Gadolinium contrast agents (Gd-DTPA) shorten T1 relaxation times drastically, causing intense bright signal enhancement on T1-weighted images."
                            ),
                            chapterSummary = listOf(
                                "Spin echo sequences manipulate TR and TE to yield T1, T2, or PD contrast; frequency, phase, and slice gradients encode 3D space into k-space for 2D Fourier transformation."
                            ),
                            learningObjectives = listOf(
                                "Select TR and TE settings to produce T1-weighted, T2-weighted, and proton-density-weighted images.",
                                "Explain how slice selection, phase encoding, and frequency encoding gradients map raw data into k-space.",
                                "Describe the clinical principles of STIR, FLAIR, and Gradient Echo sequences."
                            )
                        ),
                        acquire = AcquireContent(
                            simpleExplanation = "In a spin echo, protons start running around a track. Slower runners lag behind faster runners (dephasing). Halfway through (TE/2), the referee shouts 'turn around and run back!' (180 deg pulse). Since the faster runners run back just as fast, everyone crosses the starting line at the exact same second (the echo).",
                            stepByStepMechanism = listOf(
                                "1. 90-degree pulse tips magnetization into transverse plane.",
                                "2. Protons dephase rapidly due to magnetic imperfections.",
                                "3. At time t = TE/2, a 180-degree RF pulse is transmitted.",
                                "4. The 180-degree pulse flips the fan of spins upside down.",
                                "5. Faster spins are now behind slower spins and catch up.",
                                "6. At t = TE, all spins rephase in synchrony, generating a strong spin echo.",
                                "7. Phase encoding gradient steps incrementally between TR repetitions, filling k-space line-by-line.",
                                "8. 2D Inverse Fourier Transformation reconstructs the final diagnostic image."
                            ),
                            whyItHappens = "Hahn spin echo rephasing dynamics in the rotating frame of reference.",
                            clinicalRelevance = "FLAIR is the cornerstone sequence for detecting periventricular demyelinating plaques in Multiple Sclerosis that would otherwise be hidden by bright CSF.",
                            commonMisconception = "Misconception: A 180-degree refocusing pulse reverses T2 spin-spin decay. Reality: The 180-degree pulse ONLY corrects static field inhomogeneities (T2* effects); true random molecular spin-spin dephasing (T2) is irreversible and causes the echo to be smaller than the initial FID.",
                            analogy = "Filling k-space is like painting a portrait: the broad paint roller (central k-space) fills the general colors and lighting; the fine detail brush (outer k-space) paints the eyelashes and wrinkles.",
                            imageQualityAndDoseImpact = "Fast Spin Echo (FSE) collects multiple echoes per TR (echo train length 8-16), slashing scan times from 8 minutes to 45 seconds.",
                            interactiveDiagramType = "SPIN_ECHO_TIMING"
                        )
                    )
                )
            )
        )
    }
}
