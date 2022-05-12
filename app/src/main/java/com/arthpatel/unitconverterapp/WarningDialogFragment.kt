package com.arthpatel.unitconverterapp

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment


class WarningDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?)
            : Dialog {
        val builder = AlertDialog.Builder(requireActivity())
        builder.setTitle("Important Note")
        builder.setMessage("All Converted Values Are Rounded To One/Two Decimal Places.")
        builder.setPositiveButton("OK", null)
        return builder.create()
    }
}