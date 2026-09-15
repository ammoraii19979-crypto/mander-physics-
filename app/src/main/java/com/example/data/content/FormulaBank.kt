package com.example.data.content

import com.example.data.model.FormulaItem
import com.example.data.model.FormulaVariable

object FormulaBank {
    val formulas: List<FormulaItem> by lazy {
        listOf(
            FormulaItem(
                id = "F1_INVERSE_SQUARE",
                chapterId = 1,
                title = "Inverse Square Law",
                formulaLatex = "I_2 = I_1 \\times (d_1 / d_2)^2",
                formulaDisplay = "I2 = I1 × (d1 / d2)²",
                variables = listOf(
                    FormulaVariable("I1", "Initial Intensity", "uGy/h", 100.0, 1.0, 10000.0, 10.0),
                    FormulaVariable("d1", "Initial Distance", "meters", 1.0, 0.1, 20.0, 0.5),
                    FormulaVariable("d2", "New Distance", "meters", 2.0, 0.1, 20.0, 0.5)
                ),
                unitOfResult = "uGy/h",
                whenToUse = "Calculating scatter or radiation dose reduction when stepping back from patient in fluoroscopy or mobile X-ray.",
                clinicalExample = "Stepping back from 1 m to 2 m reduces dose rate from 100 uGy/h down to 25 uGy/h (a 75% reduction).",
                sourceSection = "1.2 Electromagnetic Radiation",
                printedPage = 5,
                pdfPage = 14,
                defaultInputs = mapOf("I1" to 100.0, "d1" to 1.0, "d2" to 2.0)
            ),
            FormulaItem(
                id = "F1_ATTENUATION_HVL",
                chapterId = 1,
                title = "Exponential Attenuation & HVL",
                formulaLatex = "I = I_0 \\times (0.5)^{(d / \\text{HVL})}",
                formulaDisplay = "I = I0 × (0.5)^(d / HVL)",
                variables = listOf(
                    FormulaVariable("I0", "Incident Intensity", "%", 100.0, 1.0, 100.0, 1.0),
                    FormulaVariable("d", "Absorber Thickness", "mm", 60.0, 0.0, 300.0, 5.0),
                    FormulaVariable("HVL", "Half-Value Layer", "mm", 30.0, 0.1, 100.0, 1.0)
                ),
                unitOfResult = "% transmitted",
                whenToUse = "Calculating beam transmission through tissue, lead shielding, or aluminium filters.",
                clinicalExample = "60 mm of soft tissue with HVL of 30 mm represents 2 HVLs, transmitting (0.5)^2 = 25% of the primary beam.",
                sourceSection = "1.4 Interaction of X-rays with Matter",
                printedPage = 9,
                pdfPage = 18,
                defaultInputs = mapOf("I0" to 100.0, "d" to 60.0, "HVL" to 30.0)
            ),
            FormulaItem(
                id = "F2_EFFECTIVE_DOSE",
                chapterId = 2,
                title = "Effective Dose Calculation",
                formulaLatex = "E = \\sum (w_T \\times H_T)",
                formulaDisplay = "E = Σ (wT × HT)",
                variables = listOf(
                    FormulaVariable("HT", "Organ Equivalent Dose", "mSv", 10.0, 0.1, 100.0, 1.0),
                    FormulaVariable("wT", "Tissue Weighting Factor", "weight", 0.12, 0.01, 1.0, 0.01)
                ),
                unitOfResult = "mSv",
                whenToUse = "Converting organ equivalent doses (e.g. lung, stomach) into whole-body effective dose to assess stochastic cancer risk.",
                clinicalExample = "10 mSv equivalent dose to the lungs (wT = 0.12) contributes 1.2 mSv to total effective dose.",
                sourceSection = "2.1 Interactions with Tissue & Dosimetry",
                printedPage = 25,
                pdfPage = 34,
                defaultInputs = mapOf("HT" to 10.0, "wT" to 0.12)
            ),
            FormulaItem(
                id = "F3_GEOMETRIC_UNSHARPNESS",
                chapterId = 3,
                title = "Geometric Unsharpness (Ug)",
                formulaLatex = "U_g = f \\times \\frac{h}{F - h}",
                formulaDisplay = "Ug = f × (OFD / FOD)",
                variables = listOf(
                    FormulaVariable("f", "Focal Spot Size", "mm", 1.0, 0.1, 2.0, 0.1),
                    FormulaVariable("OFD", "Object-to-Film Distance", "cm", 20.0, 0.0, 100.0, 2.0),
                    FormulaVariable("FOD", "Focus-to-Object Distance", "cm", 80.0, 10.0, 200.0, 5.0)
                ),
                unitOfResult = "mm edge blur",
                whenToUse = "Determining penumbral edge blurring caused by non-zero focal spot dimensions.",
                clinicalExample = "A 1.0 mm focal spot with OFD = 20 cm and FOD = 80 cm produces Ug = 1.0 × (20/80) = 0.25 mm penumbra.",
                sourceSection = "3.6 Unsharpness & X-ray Tube Physics",
                printedPage = 58,
                pdfPage = 67,
                defaultInputs = mapOf("f" to 1.0, "OFD" to 20.0, "FOD" to 80.0)
            ),
            FormulaItem(
                id = "F6_II_BRIGHTNESS_GAIN",
                chapterId = 6,
                title = "Image Intensifier Brightness Gain",
                formulaLatex = "G = \\text{Flux Gain} \\times (D_{\\text{in}} / D_{\\text{out}})^2",
                formulaDisplay = "Gain = Flux Gain × (D_in / D_out)²",
                variables = listOf(
                    FormulaVariable("FluxGain", "Flux Gain (Accelerating Voltage)", "ratio", 50.0, 10.0, 100.0, 5.0),
                    FormulaVariable("Din", "Input Screen Diameter", "cm", 30.0, 10.0, 40.0, 1.0),
                    FormulaVariable("Dout", "Output Screen Diameter", "cm", 3.0, 1.0, 5.0, 0.5)
                ),
                unitOfResult = "brightness gain multiplier",
                whenToUse = "Quantifying luminance amplification inside an electrostatic image intensifier tube.",
                clinicalExample = "Din = 30 cm and Dout = 3 cm gives minification gain = (30/3)^2 = 100. With flux gain 50, total gain = 5,000.",
                sourceSection = "6.1 The Image Intensifier",
                printedPage = 92,
                pdfPage = 101,
                defaultInputs = mapOf("FluxGain" to 50.0, "Din" to 30.0, "Dout" to 3.0)
            ),
            FormulaItem(
                id = "F7_CT_NUMBER",
                chapterId = 7,
                title = "Hounsfield Unit (CT Number)",
                formulaLatex = "\\text{CTn} = 1000 \\times \\frac{\\mu_{\\text{tissue}} - \\mu_{\\text{water}}}{\\mu_{\\text{water}}}",
                formulaDisplay = "CTn = 1000 × (μ_tissue - μ_water) / μ_water",
                variables = listOf(
                    FormulaVariable("u_tissue", "Tissue Attenuation Coeff", "cm^-1", 0.21, 0.0, 1.0, 0.01),
                    FormulaVariable("u_water", "Water Attenuation Coeff", "cm^-1", 0.20, 0.1, 0.5, 0.01)
                ),
                unitOfResult = "Hounsfield Units (HU)",
                whenToUse = "Calculating CT numbers from linear attenuation coefficients measured at ~70 keV effective energy.",
                clinicalExample = "If u_tissue = 0.21 cm^-1 and u_water = 0.20 cm^-1, CTn = 1000 × (0.21 - 0.20) / 0.20 = +50 HU (typical muscle).",
                sourceSection = "7.1 CT Numbers, Display & Equipment",
                printedPage = 104,
                pdfPage = 113,
                defaultInputs = mapOf("u_tissue" to 0.21, "u_water" to 0.20)
            ),
            FormulaItem(
                id = "F7_CTDI_DLP",
                chapterId = 7,
                title = "CT Dose-Length Product (DLP)",
                formulaLatex = "\\text{DLP} = (\\text{CTDI}_w / \\text{Pitch}) \\times L",
                formulaDisplay = "DLP = (CTDIw / Pitch) × Scan Length",
                variables = listOf(
                    FormulaVariable("CTDIw", "Weighted CTDI", "mGy", 16.0, 1.0, 100.0, 1.0),
                    FormulaVariable("Pitch", "Beam Pitch", "ratio", 1.0, 0.5, 2.0, 0.1),
                    FormulaVariable("Length", "Scan Length L", "cm", 30.0, 5.0, 150.0, 5.0)
                ),
                unitOfResult = "mGy·cm",
                whenToUse = "Determining total radiation burden for a helical CT scan and deriving patient effective dose.",
                clinicalExample = "CTDIw = 16 mGy, pitch = 1.0, scan length = 30 cm -> DLP = 16 × 30 = 480 mGy·cm. Effective dose = 480 × 0.017 = 8.16 mSv.",
                sourceSection = "7.6 CT Artefacts & Dosimetry (CTDI/DLP)",
                printedPage = 119,
                pdfPage = 128,
                defaultInputs = mapOf("CTDIw" to 16.0, "Pitch" to 1.0, "Length" to 30.0)
            ),
            FormulaItem(
                id = "F8_DOPPLER_SHIFT",
                chapterId = 8,
                title = "Ultrasound Doppler Shift",
                formulaLatex = "f_D = \\frac{2 \\times v \\times f_0 \\times \\cos\\theta}{c}",
                formulaDisplay = "fD = (2 × v × f0 × cos θ) / c",
                variables = listOf(
                    FormulaVariable("v", "Blood Velocity", "m/s", 1.0, 0.05, 5.0, 0.1),
                    FormulaVariable("f0", "Transmitted Frequency", "MHz", 5.0, 1.0, 15.0, 0.5),
                    FormulaVariable("theta", "Doppler Angle", "degrees", 60.0, 0.0, 90.0, 5.0),
                    FormulaVariable("c", "Speed of Sound in Tissue", "m/s", 1540.0, 1400.0, 1600.0, 10.0)
                ),
                unitOfResult = "kHz frequency shift",
                whenToUse = "Calculating received Doppler frequency shift or computing blood flow velocity in spectral Doppler.",
                clinicalExample = "Blood velocity 1.0 m/s with 5.0 MHz probe at 60 deg angle produces fD = (2 × 1.0 × 5,000,000 × cos(60)) / 1540 = 3.25 kHz shift.",
                sourceSection = "8.4 Transducers, Resolution & Doppler",
                printedPage = 138,
                pdfPage = 147,
                defaultInputs = mapOf("v" to 1.0, "f0" to 5.0, "theta" to 60.0, "c" to 1540.0)
            ),
            FormulaItem(
                id = "F9_LARMOR_EQUATION",
                chapterId = 9,
                title = "MRI Larmor Precession Frequency",
                formulaLatex = "f_0 = \\frac{\\gamma}{2\\pi} \\times B_0",
                formulaDisplay = "f0 = (γ / 2π) × B0",
                variables = listOf(
                    FormulaVariable("gamma", "Gyromagnetic Ratio (1H)", "MHz/T", 42.6, 1.0, 100.0, 0.1),
                    FormulaVariable("B0", "Magnetic Field Strength", "Tesla", 1.5, 0.2, 7.0, 0.5)
                ),
                unitOfResult = "MHz resonance frequency",
                whenToUse = "Determining RF excitation transmitter frequency for hydrogen protons in clinical MRI scanners.",
                clinicalExample = "At 1.5 Tesla, f0 = 42.6 × 1.5 = 63.9 MHz; at 3.0 Tesla, f0 = 42.6 × 3.0 = 127.8 MHz.",
                sourceSection = "9.1 Principles of NMR & Relaxation",
                printedPage = 147,
                pdfPage = 156,
                defaultInputs = mapOf("gamma" to 42.6, "B0" to 1.5)
            ),
            FormulaItem(
                id = "F10_EFFECTIVE_HALF_LIFE",
                chapterId = 10,
                title = "Radionuclide Effective Half-Life",
                formulaLatex = "T_{\\text{eff}} = \\frac{T_p \\times T_b}{T_p + T_b}",
                formulaDisplay = "T_eff = (Tp × Tb) / (Tp + Tb)",
                variables = listOf(
                    FormulaVariable("Tp", "Physical Half-Life", "hours", 6.0, 0.1, 720.0, 1.0),
                    FormulaVariable("Tb", "Biological Elimination Half-Life", "hours", 24.0, 0.1, 720.0, 1.0)
                ),
                unitOfResult = "hours",
                whenToUse = "Calculating internal dosimetry and clearance time of radiopharmaceuticals from organs.",
                clinicalExample = "For Tc-99m (Tp = 6.0 h) cleared by kidneys with biological half-life Tb = 24.0 h, Teff = (6 × 24) / (6 + 24) = 144 / 30 = 4.8 hours.",
                sourceSection = "10.1 Radioactive Decay & Generators",
                printedPage = 168,
                pdfPage = 177,
                defaultInputs = mapOf("Tp" to 6.0, "Tb" to 24.0)
            )
        )
    }

    fun calculate(formulaId: String, inputs: Map<String, Double>): Double {
        return when (formulaId) {
            "F1_INVERSE_SQUARE" -> {
                val i1 = inputs["I1"] ?: 100.0
                val d1 = inputs["d1"] ?: 1.0
                val d2 = inputs["d2"] ?: 2.0
                if (d2 <= 0.0) 0.0 else i1 * (d1 / d2) * (d1 / d2)
            }
            "F1_ATTENUATION_HVL" -> {
                val i0 = inputs["I0"] ?: 100.0
                val d = inputs["d"] ?: 60.0
                val hvl = inputs["HVL"] ?: 30.0
                if (hvl <= 0.0) 0.0 else i0 * Math.pow(0.5, d / hvl)
            }
            "F2_EFFECTIVE_DOSE" -> {
                val ht = inputs["HT"] ?: 10.0
                val wt = inputs["wT"] ?: 0.12
                ht * wt
            }
            "F3_GEOMETRIC_UNSHARPNESS" -> {
                val f = inputs["f"] ?: 1.0
                val ofd = inputs["OFD"] ?: 20.0
                val fod = inputs["FOD"] ?: 80.0
                if (fod <= 0.0) 0.0 else f * (ofd / fod)
            }
            "F6_II_BRIGHTNESS_GAIN" -> {
                val flux = inputs["FluxGain"] ?: 50.0
                val din = inputs["Din"] ?: 30.0
                val dout = inputs["Dout"] ?: 3.0
                if (dout <= 0.0) 0.0 else flux * (din / dout) * (din / dout)
            }
            "F7_CT_NUMBER" -> {
                val ut = inputs["u_tissue"] ?: 0.21
                val uw = inputs["u_water"] ?: 0.20
                if (uw <= 0.0) 0.0 else 1000.0 * (ut - uw) / uw
            }
            "F7_CTDI_DLP" -> {
                val ctdiw = inputs["CTDIw"] ?: 16.0
                val pitch = inputs["Pitch"] ?: 1.0
                val length = inputs["Length"] ?: 30.0
                if (pitch <= 0.0) 0.0 else (ctdiw / pitch) * length
            }
            "F8_DOPPLER_SHIFT" -> {
                val v = inputs["v"] ?: 1.0
                val f0Mhz = inputs["f0"] ?: 5.0
                val thetaDeg = inputs["theta"] ?: 60.0
                val c = inputs["c"] ?: 1540.0
                val f0Hz = f0Mhz * 1_000_000.0
                val thetaRad = Math.toRadians(thetaDeg)
                if (c <= 0.0) 0.0 else (2.0 * v * f0Hz * Math.cos(thetaRad)) / (c * 1000.0) // returns in kHz
            }
            "F9_LARMOR_EQUATION" -> {
                val gamma = inputs["gamma"] ?: 42.6
                val b0 = inputs["B0"] ?: 1.5
                gamma * b0
            }
            "F10_EFFECTIVE_HALF_LIFE" -> {
                val tp = inputs["Tp"] ?: 6.0
                val tb = inputs["Tb"] ?: 24.0
                if (tp + tb <= 0.0) 0.0 else (tp * tb) / (tp + tb)
            }
            else -> 0.0
        }
    }
}
