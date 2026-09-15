package com.example.data.content

import com.example.data.model.AcquireContent
import com.example.data.model.Concept
import com.example.data.model.DefinitionItem
import com.example.data.model.PrimeContent
import com.example.data.model.RevisionTable
import com.example.data.model.Section

object Chapter5Content {
    val sections: List<Section> by lazy {
        listOf(
            Section(
                id = "5.1",
                chapterId = 5,
                number = "5.1",
                title = "Digital Principles & Sampling",
                printedPage = 79,
                pdfPage = 88,
                concepts = listOf(
                    Concept(
                        id = "5.1.1",
                        chapterId = 5,
                        sectionId = "5.1",
                        title = "Matrix, Bit Depth, Nyquist & MTF",
                        printedPage = 79,
                        pdfPage = 88,
                        prime = PrimeContent(
                            structuredNotes = listOf(
                                "Digital image structure: 2D matrix of pixels. Pixel size = Field of View (FOV) / Matrix Dimension.",
                                "Bit depth determines available greyscale levels: 8-bit = 256 levels; 10-bit = 1024; 12-bit = 4096; 14-bit = 16,384; 16-bit = 65,536.",
                                "Image storage size: File Size (Bytes) = Matrix Rows × Matrix Columns × (Bit Depth / 8). E.g. 512×512 CT at 12-bit = 512×512×1.5 = 393 kB; 3000×3000 DR at 14-bit = 18 MB.",
                                "Compression: Lossless (reversible, factor 2-3:1, preserves exact pixel values); Lossy (irreversible, up to 40:1, discards imperceptible data; restricted for primary diagnosis).",
                                "Fourier analysis: breaks down spatial patterns into sine waves of varying spatial frequencies (lp/mm). Sharp edges contain high spatial frequencies.",
                                "Nyquist Criterion: sampling frequency must be AT LEAST TWICE the highest frequency present in the signal (fs >= 2 × fmax) to prevent Aliasing.",
                                "Nyquist frequency: maximum accurately resolvable spatial frequency = half the sampling frequency.",
                                "Modulation Transfer Function (MTF): ratio of output modulation (contrast) to input modulation as a function of spatial frequency. MTF drops towards zero as spatial frequency rises."
                            ),
                            keyConcepts = listOf("Pixel vs Voxel", "Bit Depth & Dynamic Range", "Nyquist Criterion (fs >= 2 fmax)", "Aliasing Artefact", "Modulation Transfer Function (MTF)"),
                            definitions = listOf(
                                DefinitionItem("Bit Depth", "The number of binary bits allocated per pixel, defining the maximum number of discrete grey levels (2^n)."),
                                DefinitionItem("Nyquist Frequency", "The highest spatial frequency that can be accurately represented by a sampling system without aliasing, equal to half the sampling frequency."),
                                DefinitionItem("Modulation Transfer Function (MTF)", "The ratio of image contrast to object contrast as a function of spatial frequency, providing an objective measure of spatial resolution.")
                            ),
                            equations = listOf(
                                "Pixel Size = FOV / Matrix Size",
                                "Image Size (Bytes) = Rows × Columns × (Bit Depth / 8)",
                                "Nyquist Limit: f_Nyquist = 1 / (2 × Pixel Pitch)",
                                "MTF = Output Modulation / Input Modulation (0.0 to 1.0)"
                            ),
                            tables = listOf(
                                RevisionTable(
                                    title = "Matrix Dimensions and File Sizes across Modalities",
                                    headers = listOf("Modality", "Typical Matrix", "Bit Depth", "Uncompressed File Size"),
                                    rows = listOf(
                                        listOf("Nuclear Medicine (SPECT)", "64 × 64 or 128 × 128", "8-bit or 16-bit", "32 kB"),
                                        listOf("Computed Tomography (CT)", "512 × 512", "12-bit (stored as 16-bit)", "512 kB / slice"),
                                        listOf("Magnetic Resonance (MRI)", "256 × 256 or 512 × 512", "12-bit or 16-bit", "128 - 512 kB"),
                                        listOf("Computed Radiography (CR)", "2000 × 2500", "12-bit to 14-bit", "10 - 15 MB"),
                                        listOf("Direct Digital Radiography (DDR)", "3000 × 3000", "14-bit", "18 - 25 MB")
                                    )
                                )
                            ),
                            whatYouMustUnderstand = listOf(
                                "Sampling below the Nyquist rate creates false low frequencies (aliasing), appearing as moiré stripes in DR or wrap-around in MRI.",
                                "MTF provides a complete, objective curve describing how resolution degrades with increasing line pairs/mm, unlike single-number threshold tests."
                            ),
                            whatYouShouldMemorize = listOf(
                                "Nyquist frequency = 1 / (2 × pixel pitch). For a 0.1 mm pixel, Nyquist limit is 5 lp/mm.",
                                "A 12-bit pixel provides 4096 grey levels."
                            ),
                            highYieldFacts = listOf(
                                "Detail film-screen has higher MTF at very high spatial frequencies (>8 lp/mm) than digital radiography, but DR has far superior contrast resolution.",
                                "Lossless compression is legally required for primary radiographic diagnostic archives in many healthcare jurisdictions."
                            ),
                            chapterSummary = listOf(
                                "Digital images sample continuous X-ray signals into discrete pixels and bit depths; spatial accuracy is limited by Nyquist sampling and system MTF."
                            ),
                            learningObjectives = listOf(
                                "Calculate pixel dimensions, Nyquist frequency, and raw uncompressed digital image file sizes.",
                                "Interpret Modulation Transfer Function curves for different imaging modalities."
                            )
                        ),
                        acquire = AcquireContent(
                            simpleExplanation = "In digital images, reality is broken into square tiles (pixels) and numbered shades of grey (bit depth). If your tiles are too big to sample a striped shirt, you don't just lose the stripes—you see weird phantom zebra patterns (aliasing).",
                            stepByStepMechanism = listOf(
                                "1. Analogue X-ray beam reaches detector.",
                                "2. Detector divides continuous profile into discrete spatial samples (pixels).",
                                "3. Analogue-to-Digital Converter (ADC) quantizes continuous electric voltage into discrete binary numbers (bit depth).",
                                "4. If sampling frequency is >= 2 cycles per object width, the fine detail is preserved.",
                                "5. If sampling frequency is too coarse, high spatial frequencies wrap backwards into false low frequencies (aliasing).",
                                "6. Pixel values are mapped to a display look-up table (LUT) for viewing."
                            ),
                            whyItHappens = "Discrete Fourier sampling theorem and quantization limitations.",
                            clinicalRelevance = "Aliasing appears as moiré fringes when an antiscatter grid's lead strips interact with the periodic pixel spacing of a digital detector.",
                            commonMisconception = "Misconception: Digital radiography has higher spatial resolution than 100-speed film. Reality: High-resolution film resolves up to 10-15 lp/mm, whereas typical CR/DR resolves 3.5 to 5.5 lp/mm. Digital radiography wins because of contrast dynamic range and DQE, not raw spatial resolution.",
                            analogy = "Digital music sampling: sampling an audio wave at 44.1 kHz (CD quality) allows frequencies up to 22 kHz (Nyquist limit) to be heard without screeching distortion.",
                            imageQualityAndDoseImpact = "Digital detectors possess a wide linear response across 4 orders of magnitude, eliminating repeats from underexposure or overexposure.",
                            interactiveDiagramType = "NYQUIST_SAMPLING"
                        )
                    )
                )
            ),
            Section(
                id = "5.3",
                chapterId = 5,
                number = "5.3",
                title = "Computed Radiography (CR) & DDR",
                printedPage = 83,
                pdfPage = 92,
                concepts = listOf(
                    Concept(
                        id = "5.3.1",
                        chapterId = 5,
                        sectionId = "5.3",
                        title = "Photostimulable Phosphors, Direct vs Indirect DR & DQE",
                        printedPage = 83,
                        pdfPage = 92,
                        prime = PrimeContent(
                            structuredNotes = listOf(
                                "Computed Radiography (CR): utilizes Photostimulable Phosphor (PSP) plate made of barium fluorohalide doped with europium (BaFX:Eu, 85% Br, 15% I).",
                                "CR Readout: red laser beam (633 nm) scans the plate in raster pattern, liberating trapped electrons; electrons fall to valence band emitting blue light (~400 nm); light is channeled by optical guide to photomultiplier tubes (PMTs).",
                                "Plate erasure: bright white light floods the plate to empty residual electron traps before reuse.",
                                "CR dynamic range: wide and strictly linear over 10,000:1 (unlike film's S-shaped H&D curve). Raw gamma is ~0.4, so histogram analysis and gradation curve mapping are mandatory to achieve high contrast.",
                                "Detector Dose Indicators (DDIs): numerical indices reflecting plate exposure (analogous to optical density). Some are linear, some logarithmic, some inverse. Validated against local DRLs.",
                                "Digital Radiography (DR): fast in-situ readout (~10 s) without cassette handling.",
                                "Indirect DR: X-ray scintillator (Caesium Iodide CsI:Tl needles or Gd2O2S) converts X-rays to light; amorphous silicon (a-Si) photodiode TFT array converts light to charge.",
                                "Direct DR (DDR): Amorphous selenium (a-Se) photoconductor converts X-rays directly into electron-hole pairs; high electric field pulls positive charges to TFT capacitors without lateral light spread (superb MTF).",
                                "Detective Quantum Efficiency (DQE): ratio of output SNR^2 to input SNR^2. DQE of DR systems is up to 65%, compared with ~30% for CR and film-screen. High DQE permits significant dose reduction!",
                                "PACS & DICOM: Picture Archiving and Communication System manages workflow, short- and long-term archives. DICOM standard guarantees cross-vendor interoperability (Worklist, Storage, MPPS, GSDF calibration with SMPTE test pattern)."
                            ),
                            keyConcepts = listOf("Photostimulable Storage Phosphor (BaFX:Eu)", "CR Laser Readout & Erasure", "Detector Dose Indicators (DDI)", "Indirect DR (CsI + a-Si)", "Direct DR (a-Se Photoconductor)", "Detective Quantum Efficiency (DQE)"),
                            definitions = listOf(
                                DefinitionItem("Detective Quantum Efficiency (DQE)", "A measure of the efficiency with which an imaging system transfers the SNR of the incident X-ray beam to the output image: DQE = (SNR_out / SNR_in)^2."),
                                DefinitionItem("Photostimulated Luminescence", "The emission of visible light from a previously irradiated storage phosphor upon excitation by laser light of a longer wavelength."),
                                DefinitionItem("Direct Digital Radiography (DDR)", "A digital radiography system using a photoconductor (a-Se) to convert X-rays directly into electric charge without an intermediate light conversion stage.")
                            ),
                            equations = listOf(
                                "DQE = (SNR_out)^2 / (SNR_in)^2",
                                "CR dynamic range: 10,000 to 1 (linear)",
                                "DQE values: DR up to 65%; CR approx 30%; Film-screen approx 30%"
                            ),
                            tables = listOf(
                                RevisionTable(
                                    title = "Comparison: Film-Screen vs CR vs Indirect DR vs Direct DR",
                                    headers = listOf("Technology", "X-ray Detector", "Intermediate Step", "DQE", "Limiting Resolution"),
                                    rows = listOf(
                                        listOf("Film-Screen", "Gd2O2S screen + Film", "Light exposes AgBr", "~30%", "6 - 10 lp/mm"),
                                        listOf("Computed Radio. (CR)", "BaFX:Eu storage plate", "Trapped electrons -> Red laser -> Blue light", "~30%", "3.5 - 5.5 lp/mm"),
                                        listOf("Indirect DR (IDR)", "CsI needle scintillator", "Light detected by a-Si photodiode TFT", "50 - 65%", "3 - 4 lp/mm"),
                                        listOf("Direct DR (DDR)", "Amorphous Selenium (a-Se)", "Direct ionization, NO light step", "60 - 65%", "4 - 5 lp/mm (High MTF)")
                                    )
                                )
                            ),
                            whatYouMustUnderstand = listOf(
                                "Direct DR eliminates light diffusion entirely because charges are pulled vertically by an electric field, giving higher MTF at high spatial frequencies.",
                                "Higher DQE (65% in DR vs 30% in CR/film) means the same image SNR can be achieved with significantly lower patient radiation dose.",
                                "Because CR/DR detectors cannot be visibly overexposed (software automatically adjusts brightness), 'dose creep' must be guarded against by monitoring DDIs."
                            ),
                            whatYouShouldMemorize = listOf(
                                "CR plate: Barium fluorohalide doped with europium (BaFBr:Eu).",
                                "Indirect DR: Caesium Iodide (CsI:Tl) + amorphous silicon (a-Si).",
                                "Direct DR: Amorphous selenium (a-Se)."
                            ),
                            highYieldFacts = listOf(
                                "In Direct DR, positive charges (holes) are collected by TFT capacitors to form the image.",
                                "Display monitors for primary reporting must be calibrated to the DICOM Greyscale Standard Display Function (GSDF) and checked with the SMPTE test pattern."
                            ),
                            chapterSummary = listOf(
                                "Computed radiography uses photostimulable laser-scanned plates; digital radiography flat-panel detectors (CsI indirect or a-Se direct) achieve superior DQE (65%), lowering dose and integrating directly into PACS."
                            ),
                            learningObjectives = listOf(
                                "Compare the image capture, readout, and physical components of CR, Indirect DR, and Direct DR.",
                                "Define DQE and explain why higher DQE allows patient dose reduction."
                            )
                        ),
                        acquire = AcquireContent(
                            simpleExplanation = "In CR, X-rays trap energy in a plate like footprints in wet sand; a red laser walks over the sand, glowing blue light wherever a footprint was. In Indirect DR, X-rays flash into light which photodiodes catch. In Direct DR, X-rays directly knock electrons loose with no glowing light step, so edges stay pinpoint sharp.",
                            stepByStepMechanism = listOf(
                                "1. CR: X-ray photon promotes electrons to metastable traps in BaFX:Eu crystal.",
                                "2. Plate transported to CR reader; red laser sweeps across plate line-by-line.",
                                "3. Laser energy releases electrons; they drop to valence band, releasing blue light.",
                                "4. Light pipe directs blue light into PMT; signal is digitized by ADC.",
                                "5. Direct DR alternative: X-ray ionizes amorphous selenium layer directly.",
                                "6. High-voltage bias pulls positive charge carriers straight down into TFT capacitors without lateral blurring.",
                                "7. TFT switches read out row-by-row into computer within 5-10 seconds."
                            ),
                            whyItHappens = "Photoconductivity in amorphous selenium versus photostimulated luminescence in europium-activated barium fluorohalide.",
                            clinicalRelevance = "Dose Creep: because underexposed digital images look noisy but overexposed images look pristine, radiographers may inadvertently increase mAs. Auditing DDIs stops dose creep.",
                            commonMisconception = "Misconception: A dark digital radiograph on a monitor means the patient received too much dose. Reality: Software auto-ranging sets display brightness independently of dose; an overexposed plate looks normal on screen but gives a high DDI and excessive patient dose.",
                            analogy = "Old cameras used photographic roll film (manual development). Early digital used memory cards you put into a card reader (CR). Modern DSLRs display the shot instantly on the screen (DR).",
                            imageQualityAndDoseImpact = "High DQE of 65% in DR allows a 50% dose reduction compared to 400-speed film-screen systems for equivalent image quality.",
                            interactiveDiagramType = "DQE_COMPARISON"
                        )
                    )
                )
            )
        )
    }
}
