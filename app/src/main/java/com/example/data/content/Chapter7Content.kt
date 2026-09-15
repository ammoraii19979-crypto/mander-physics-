package com.example.data.content

import com.example.data.model.AcquireContent
import com.example.data.model.Concept
import com.example.data.model.DefinitionItem
import com.example.data.model.PrimeContent
import com.example.data.model.RevisionTable
import com.example.data.model.Section

object Chapter7Content {
    val sections: List<Section> by lazy {
        listOf(
            Section(
                id = "7.1",
                chapterId = 7,
                number = "7.1",
                title = "CT Numbers, Display & Equipment",
                printedPage = 103,
                pdfPage = 112,
                concepts = listOf(
                    Concept(
                        id = "7.1.1",
                        chapterId = 7,
                        sectionId = "7.1",
                        title = "Hounsfield Units, Windowing & Scanner Generations",
                        printedPage = 103,
                        pdfPage = 112,
                        prime = PrimeContent(
                            structuredNotes = listOf(
                                "CT generates cross-sectional transaxial slices perpendicular to the z-axis (axis of rotation), eliminating overlying tissue superimposition.",
                                "CT Number (Hounsfield Unit, HU): CTn = 1000 × (u_tissue - u_water) / u_water. By definition: Water = 0 HU, Air = -1000 HU.",
                                "Tissue CT numbers: Cortical Bone = +500 to +1500 HU; Muscle = +40 to +60 HU; Brain grey matter = 35 to 45 HU; Brain white matter = 20 to 30 HU; Fat = -60 to -150 HU; Lung = -300 to -800 HU.",
                                "Partial volume effect: each voxel has 3 dimensions (width × height × slice thickness). If a voxel contains a mixture of tissues, the CT number is their volume-weighted average.",
                                "Windowing: human eye sees ~50 grey shades, whereas CT data spans 4000 levels (-1024 to +3071, 12-bit). Window Width (WW) controls contrast; Window Level (WL) controls center brightness.",
                                "Lung window: wide WW ~ 1500, low WL ~ -600. Mediastinum/soft tissue: narrow WW ~ 350, WL ~ +40. Bone: wide WW ~ 2000, WL ~ +500. Brain: narrow WW ~ 80, WL ~ +35.",
                                "Scanner generations: 1st (rotate-translate, single detector, 5 min/slice); 2nd (rotate-translate, ~30 detectors, 20 s/slice); 3rd (rotate-rotate, wide fan beam, curved detector arc, industry standard); 4th (rotate-stationary, stationary detector ring); 5th (electron beam scanner, 50 ms cardiac sweep).",
                                "Bow tie filter: shaped filter thin in center, thicker at edges, equalizing beam intensity across the elliptical body cross-section and evening out beam hardening."
                            ),
                            keyConcepts = listOf("CT Number Formula (HU)", "Partial Volume Effect", "Window Width & Window Level", "Scanner Generations (1st to 4th)", "Bow Tie Filter"),
                            definitions = listOf(
                                DefinitionItem("CT Number (Hounsfield Unit)", "A normalized measure of the linear attenuation coefficient of a voxel relative to water: CTn = 1000 × (ut - uw) / uw."),
                                DefinitionItem("Partial Volume Effect", "The blurring of CT numbers that occurs when a voxel contains more than one tissue type, producing an intermediate average attenuation value."),
                                DefinitionItem("Window Width (WW)", "The range of CT numbers displayed over the full greyscale from black to white, determining image contrast."),
                                DefinitionItem("Window Level (WL)", "The central CT number around which the window width is centered, determining overall display brightness.")
                            ),
                            equations = listOf(
                                "CTn = 1000 × (ut - uw) / uw",
                                "Voxel volume = Pixel area × Slice thickness",
                                "Window range: [WL - WW/2, WL + WW/2]"
                            ),
                            tables = listOf(
                                RevisionTable(
                                    title = "Table 7.1: Standard CT Numbers of Human Tissues",
                                    headers = listOf("Tissue / Material", "CT Number Range (HU)", "Typical Value"),
                                    rows = listOf(
                                        listOf("Air", "-1000", "-1000"),
                                        listOf("Lung parenchyma", "-800 to -300", "-600"),
                                        listOf("Fat", "-150 to -60", "-100"),
                                        listOf("Water", "0 (by definition)", "0"),
                                        listOf("Cerebrospinal Fluid (CSF)", "+5 to +15", "+10"),
                                        listOf("Brain White Matter", "+20 to +30", "+25"),
                                        listOf("Brain Grey Matter", "+35 to +45", "+40"),
                                        listOf("Muscle", "+40 to +60", "+50"),
                                        listOf("Acute Clotted Blood", "+60 to +80", "+70"),
                                        listOf("Trabecular / Dense Bone", "+500 to +1500", "+1000")
                                    )
                                )
                            ),
                            whatYouMustUnderstand = listOf(
                                "Narrowing the window width increases display contrast, allowing subtle differences (e.g. 10 HU difference between grey and white matter) to be detected by the human eye.",
                                "Thinner slices reduce partial volume averaging, improving z-axis resolution and visibility of small calcifications."
                            ),
                            whatYouShouldMemorize = listOf(
                                "Water = 0 HU; Air = -1000 HU; Bone = +1000 HU.",
                                "Grey matter (35-45 HU) is denser and has a HIGHER CT number than white matter (20-30 HU) due to white matter's high lipid (myelin) content.",
                                "CT X-ray tubes have immense heat capacity: 4 to 8 Megajoules (MJ) with oil-to-air heat exchangers."
                            ),
                            highYieldFacts = listOf(
                                "CT tube anode-cathode axis is oriented PARALLEL to the z-axis of rotation to minimize the anode heel effect across the transaxial slice.",
                                "Solid-state ceramic detectors (cadmium tungstate or rare earth ceramic) achieve up to 98% detection efficiency."
                            ),
                            chapterSummary = listOf(
                                "CT maps tissue linear attenuation coefficients into calibrated Hounsfield Units; third-generation rotate-rotate geometry and tailored windowing provide exceptional soft tissue discrimination."
                            ),
                            learningObjectives = listOf(
                                "Calculate CT numbers from linear attenuation coefficients.",
                                "Select appropriate window width and level settings for brain, lung, soft tissue, and bone.",
                                "Contrast the geometry and trade-offs of 1st, 2nd, 3rd, and 4th generation CT scanners."
                            )
                        ),
                        acquire = AcquireContent(
                            simpleExplanation = "In a standard chest X-ray, the ribs, heart, and lungs are squashed on top of each other. A CT scanner rotates around the patient like a bread slicer, calculating the exact density of every single cube of sugar (voxel) inside.",
                            stepByStepMechanism = listOf(
                                "1. X-ray tube and curved detector array rotate continuously around patient on a 360 deg slip ring gantry.",
                                "2. Pencil beam transmissions (ray sums) are sampled ~1000 times per rotation across 800 detectors.",
                                "3. Computer calculates linear attenuation coefficient ut for each voxel.",
                                "4. ut is normalized against water: CTn = 1000 × (ut - uw) / uw.",
                                "5. If a voxel contains both bone and soft tissue, an intermediate CT number results (partial volume effect).",
                                "6. The user adjusts Window Width and Window Level to expand a 100 HU range across all 256 screen shades."
                            ),
                            whyItHappens = "Radon transform inversion and digital greyscale mapping.",
                            clinicalRelevance = "Diagnosing acute ischemic stroke requires distinguishing subtle loss of the insular ribbon (loss of 5-10 HU grey-white contrast) using an ultra-narrow brain window (WW 80, WL 35).",
                            commonMisconception = "Misconception: A CT number of +20 means the tissue is 20 times denser than water. Reality: +20 HU means the tissue attenuation is only 2% higher than water (each HU is 0.1% of water attenuation).",
                            analogy = "Windowing is like zooming in with binoculars on a specific altitude band of a mountain: you ignore the sky above and the valley below to inspect fine trees on the slope in high contrast.",
                            imageQualityAndDoseImpact = "Thinner slices reduce partial volume blur for small pulmonary nodules, but increase quantum noise unless mA is increased.",
                            interactiveDiagramType = "CT_WINDOWING_SIM"
                        )
                    )
                )
            ),
            Section(
                id = "7.4",
                chapterId = 7,
                number = "7.4",
                title = "Helical, Multislice & Reconstruction",
                printedPage = 108,
                pdfPage = 117,
                concepts = listOf(
                    Concept(
                        id = "7.4.1",
                        chapterId = 7,
                        sectionId = "7.4",
                        title = "Reconstruction, Multislice Pitch & Cone Beam",
                        printedPage = 108,
                        pdfPage = 117,
                        prime = PrimeContent(
                            structuredNotes = listOf(
                                "Reconstruction: Filtered Back-Projection (FBP) is the mathematical standard. Back-projecting un-filtered ray sums creates a 1/r star blur; applying a spatial frequency reconstruction filter (kernel) cancels the 1/r blur.",
                                "Reconstruction filters: Soft tissue kernels smooth data to reduce noise; Bone/sharp kernels enhance high frequencies for edge sharpness at the cost of higher noise.",
                                "Helical (spiral) CT: continuous gantry rotation via slip rings while patient table moves continuously along z-axis. Trajectory traces a helix around patient.",
                                "Single-slice Pitch = Tabletop movement per 360 deg rotation / Slice thickness. Pitch = 1: contiguous slices; Pitch > 1: gaps between helix ribbons, lower dose; Pitch < 1: overlapping ribbons, higher dose.",
                                "Multislice CT (MSCT): uses multiple parallel rows of solid-state detectors (16, 64, 128+ rows).",
                                "Beam Pitch = Tabletop movement per rotation / Nominal collimation length.",
                                "Slice Pitch = Tabletop movement per rotation / Single slice width = Beam Pitch × Number of slices.",
                                "Cone beam effect: in 64+ slice scanners, the wide z-axis fan becomes a 3D cone; oblique rays cross multiple detector rows, requiring 3D cone beam reconstruction (e.g. Feldkamp algorithm).",
                                "Isotropy: when reconstructed voxel dimensions are equal in all 3 planes (x = y = z ~ 0.5 mm), allowing multiplanar reformatting (MPR) in coronal, sagittal, or oblique planes without stairstep blur."
                            ),
                            keyConcepts = listOf("Filtered Back-Projection (FBP)", "Helical Scanning & Slip Rings", "Pitch (Beam Pitch vs Slice Pitch)", "Multislice Detector Configuration", "Cone Beam Effect & Isotropy"),
                            definitions = listOf(
                                DefinitionItem("Filtered Back-Projection", "An analytic reconstruction algorithm where measured projection ray sums are mathematically convolved with a filter kernel before back-projection to eliminate the 1/r blurring artifact."),
                                DefinitionItem("Pitch (Beam Pitch)", "In multislice CT, the distance the patient table travels during one complete 360 degree gantry rotation divided by the total collimated beam width."),
                                DefinitionItem("Isotropic Voxel", "A voxel having identical dimensions in all three axes (x, y, and z), ensuring uniform spatial resolution in any reconstruction plane.")
                            ),
                            equations = listOf(
                                "Pitch = Table movement per rotation / Total collimator width",
                                "Slice Pitch = Beam Pitch × Number of active detector rows",
                                "Scan Time = (Total scan length × Rotation time) / (Pitch × Collimator width)"
                            ),
                            tables = listOf(
                                RevisionTable(
                                    title = "Multislice Detector Pitch Terminology",
                                    headers = listOf("Parameter", "Definition", "Example (64-slice, 0.625 mm slice = 40 mm beam)", "Effect of Increasing"),
                                    rows = listOf(
                                        listOf("Beam Pitch", "Table movement per rotation / Total beam width", "40 mm table feed / 40 mm beam = 1.0", "Reduces patient dose, shortens scan time"),
                                        listOf("Slice Pitch", "Table movement per rotation / Single slice width", "40 mm table feed / 0.625 mm = 64.0", "Used by some manufacturers for display"),
                                        listOf("Over-beaming", "Penumbra extending beyond active detector rows", "~1-2 mm extra beam width", "Causes wasted dose at outer rows in multislice"),
                                        listOf("Over-ranging", "Extra helical rotations before and after volume", "1 to 2 extra rotations", "Increases dose on short scan lengths")
                                    )
                                )
                            ),
                            whatYouMustUnderstand = listOf(
                                "Increasing pitch above 1 reduces patient dose inversely (Pitch 1.5 reduces dose by 33%), but widens effective slice sensitivity profile and increases interpolation noise.",
                                "Pitch in clinical practice is rarely increased above 1.5 (maximum 2.0) to prevent unacceptable image degradation."
                            ),
                            whatYouShouldMemorize = listOf(
                                "Beam pitch = Table movement / Collimator length.",
                                "Over-ranging adds 1 to 2 part-rotations at each end of a helical scan for interpolation data."
                            ),
                            highYieldFacts = listOf(
                                "In multislice scanners, slice width is determined by DETECTOR ROW SELECTION, not by pre-patient collimation.",
                                "In single-slice CT, pitch does not affect noise because interpolation uses the same photon count; in multislice CT, noise INCREASES with pitch due to 3D cone-beam weighting."
                            ),
                            chapterSummary = listOf(
                                "Slip-ring technology enables continuous helical volume imaging; multislice arrays provide submillimeter isotropic voxels, reconstructed via filtered back-projection or iterative algorithms."
                            ),
                            learningObjectives = listOf(
                                "Calculate pitch and explain its effect on patient dose and slice thickness profile.",
                                "Describe how multislice detector rows combine to form various slice thicknesses."
                            )
                        ),
                        acquire = AcquireContent(
                            simpleExplanation = "In helical scanning, the table glides through the spinning doughnut like a candy cane spinning out stripes. If you pull the table faster (higher pitch), the stripes stretch out, covering the chest in one 5-second breath-hold.",
                            stepByStepMechanism = listOf(
                                "1. Patient moves through gantry while tube spins continuously.",
                                "2. Detectors collect a continuous spiral ribbon of projection data.",
                                "3. Computer interpolates data between adjacent rotations to estimate projections for a flat slice plane.",
                                "4. Filtered back-projection or iterative reconstruction computes voxel values.",
                                "5. In 64-slice scanners, cone beam geometry angles rays across adjacent rows.",
                                "6. True isotropic voxels (0.5 × 0.5 × 0.5 mm) allow seamless 3D volume rendering and virtual endoscopy."
                            ),
                            whyItHappens = "Continuous table translation during gantry revolution governed by helical interpolation geometry.",
                            clinicalRelevance = "Rapid scan speed allows whole-chest trauma imaging in under 5 seconds, eliminating respiratory motion blur and slice misregistration near the diaphragm.",
                            commonMisconception = "Misconception: Increasing pitch in single-slice CT makes the image noisier. Reality: In single-slice CT, pitch does not increase noise; it broadens the effective slice width. In multislice CT, pitch DOES increase noise because of cone-beam interpolation.",
                            analogy = "Wrapping an Ace bandage around a leg: at pitch=1 the edges touch perfectly. At pitch=1.5 you stretch the wrap with gaps, covering the leg faster with less bandage.",
                            imageQualityAndDoseImpact = "High pitch (1.5) lowers dose by 33% and freezes cardiac/breath motion, but slightly degrades z-axis spatial resolution.",
                            interactiveDiagramType = "HELICAL_PITCH_SIM"
                        )
                    )
                )
            ),
            Section(
                id = "7.6",
                chapterId = 7,
                number = "7.6",
                title = "CT Artefacts & Dosimetry (CTDI/DLP)",
                printedPage = 115,
                pdfPage = 124,
                concepts = listOf(
                    Concept(
                        id = "7.6.1",
                        chapterId = 7,
                        sectionId = "7.6",
                        title = "Artefacts, CTDI, DLP & Dose Optimization",
                        printedPage = 115,
                        pdfPage = 124,
                        prime = PrimeContent(
                            structuredNotes = listOf(
                                "CT Artefacts: 1. Motion (streaks & ghosting from cardiac/patient motion); 2. Metal / High-attenuation (dark and bright streaks from dental amalgam/prostheses); 3. Photon starvation (horizontal black streaks where beam cannot penetrate, e.g. through bilateral hips); 4. Beam hardening & cupping (low-energy photons filtered in center, lowering central CT numbers); 5. Ring artefact (calibration drift in a single detector row tracing circular rings).",
                                "CT Dose Index (CTDI): measure of dose from a single gantry rotation, measured with a 100 mm pencil ionization chamber in 16 cm (head) and 32 cm (body) perspex phantoms.",
                                "Weighted CTDI (CTDIw): combines center and peripheral doses: CTDIw = 1/3 CTDI_center + 2/3 CTDI_periphery.",
                                "Volume CTDI (CTDIvol): average absorbed dose within the scanned volume: CTDIvol = CTDIw / pitch.",
                                "Dose-Length Product (DLP): total radiation output across the examination: DLP = CTDIvol × scan length L (units mGy·cm).",
                                "Effective Dose (E): derived from DLP using region-specific conversion coefficients (E/DLP): E = DLP × (E/DLP).",
                                "E/DLP conversion factors: Head = 0.0023 mSv/(mGy·cm); Chest = 0.018; Liver = 0.015; Abdomen & Pelvis = 0.017 (pelvis is highest due to gonads wT=0.20).",
                                "Typical CT doses: Head CT = 1.5 mSv (CTDIw 60 mGy, DLP 700); Chest CT = 7 mSv (CTDIw 14 mGy, DLP 400); Abdomen/Pelvis CT = 8.5-10 mSv (CTDIw 16 mGy, DLP 500).",
                                "Automated tube current modulation (mA modulation): varies mA with patient cross-section and angle, reducing dose by 10-40% while maintaining target noise."
                            ),
                            keyConcepts = listOf("Beam Hardening & Cupping", "Ring Artefact", "Photon Starvation", "CTDIw & CTDIvol", "Dose-Length Product (DLP)", "mA Modulation"),
                            definitions = listOf(
                                DefinitionItem("CTDIw", "The weighted CT dose index representing average absorbed dose across the scan plane: CTDIw = 1/3 CTDI_center + 2/3 CTDI_periphery."),
                                DefinitionItem("CTDIvol", "The volume CT dose index, representing average dose in the scanned volume taking pitch into account: CTDIvol = CTDIw / Pitch."),
                                DefinitionItem("Dose-Length Product (DLP)", "The product of CTDIvol and total scan length L, quantifying the total radiation energy imparted during a CT examination (mGy·cm).")
                            ),
                            equations = listOf(
                                "CTDIw = (1/3) × CTDI_center + (2/3) × CTDI_periphery",
                                "CTDIvol = CTDIw / Pitch",
                                "DLP = CTDIvol × L (mGy·cm)",
                                "Effective Dose E = DLP × (E/DLP) mSv"
                            ),
                            tables = listOf(
                                RevisionTable(
                                    title = "Table 7.3: Typical CT Doses and Conversion Factors",
                                    headers = listOf("Examination", "CTDIw (mGy)", "DLP (mGy·cm)", "E/DLP factor", "Effective Dose E (mSv)"),
                                    rows = listOf(
                                        listOf("Head CT", "60", "700", "0.0023", "1.5 mSv"),
                                        listOf("Chest CT", "14", "400", "0.018", "7.0 mSv"),
                                        listOf("Liver CT", "16", "350", "0.015", "5.5 mSv"),
                                        listOf("Abdomen & Pelvis CT", "16", "500", "0.017", "8.5 mSv")
                                    )
                                )
                            ),
                            whatYouMustUnderstand = listOf(
                                "CTDIw is heavily weighted to the periphery (2/3) because the skin and outer body absorb much more radiation than the center.",
                                "Halving pixel dimensions (to improve resolution) requires an 8-FOLD dose increase to keep SNR constant!",
                                "Doubling SNR requires quadrupling the dose (4x)!"
                            ),
                            whatYouShouldMemorize = listOf(
                                "E/DLP factor is lowest for Head (0.0023) and highest for Chest/Pelvis (~0.017-0.018) due to tissue radiosensitivity.",
                                "Ring artefacts appear as concentric circles caused by a miscalibrated or malfunctioning detector element.",
                                "CT accounts for ~40% of all medical radiation dose in the UK, despite comprising only 4% of imaging procedures!"
                            ),
                            highYieldFacts = listOf(
                                "Cupping artefact occurs because low-energy photons are preferentially filtered out in the thick center of the patient, causing central CT numbers to appear lower than true values.",
                                "mA modulation reduces mA in AP projections and boosts mA in thicker lateral projections, saving 10-40% total patient dose."
                            ),
                            chapterSummary = listOf(
                                "CT artefacts (streaks, hardening, rings) require software correction; CTDIvol and DLP quantify radiation output, convertible to effective dose via regional factors."
                            ),
                            learningObjectives = listOf(
                                "Identify common CT artefacts and describe methods to correct them.",
                                "Calculate CTDIw, CTDIvol, DLP, and effective dose from scanning parameters."
                            )
                        ),
                        acquire = AcquireContent(
                            simpleExplanation = "CTDIvol is the dose inside one slice. DLP is the total dose along the whole length of the body scanned. A CT scan of the abdomen delivers ~8 mSv, which is equal to 400 chest X-rays or 3 years of natural background radiation.",
                            stepByStepMechanism = listOf(
                                "1. 100 mm pencil chamber inside a 32 cm perspex cylinder measures dose profile.",
                                "2. Dose is integrated over length and divided by nominal slice thickness = CTDI.",
                                "3. Weighted average (1/3 center + 2/3 edge) computes CTDIw.",
                                "4. Dividing by pitch accounts for helical spacing = CTDIvol.",
                                "5. Multiplying by total scanned length gives DLP (mGy·cm).",
                                "6. Multiplying DLP by region coefficient (0.017 for abdomen) yields patient Effective Dose (mSv)."
                            ),
                            whyItHappens = "Dosimetry geometry established by the International Electrotechnical Commission (IEC).",
                            clinicalRelevance = "Because CT contributes 40% of medical population dose, every CT request must be strictly justified under IRMER, and scanning lengths must not exceed clinical indications.",
                            commonMisconception = "Misconception: CTDI measures the exact radiation dose delivered to a specific patient. Reality: CTDI is a standardized scanner output metric measured in a plastic phantom; actual patient dose varies with individual body habitus and tissue composition.",
                            analogy = "Fuel consumption: CTDIvol is liters per 100 km (efficiency of the vehicle/protocol). DLP is total liters consumed for the entire journey (length of trip).",
                            imageQualityAndDoseImpact = "Tube current modulation keeps image noise constant across varying anatomy while dropping dose by up to 40%.",
                            interactiveDiagramType = "CT_DOSIMETRY_CALC"
                        )
                    )
                )
            )
        )
    }
}
