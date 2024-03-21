package ru.kor.seal.fragment.newitem

import android.app.Dialog
import android.os.Bundle
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import ru.kor.seal.R

interface NewStageDialogListener {
    fun onDialogPositiveClick(text: String)
}

class NewStageDialogFragment(
    private val listener: NewStageDialogListener
) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val layout = requireActivity().layoutInflater.inflate(R.layout.dialog_new_stage, null)
        val t = layout.findViewById<EditText>(R.id.nameStage)

        val dialog = MaterialAlertDialogBuilder(requireContext())
            .setTitle("New Stage")
            .setView(layout)
            .setPositiveButton("Ok") { _, _ ->
                listener.onDialogPositiveClick(t.text.toString().trim())
                dismiss()
            }
            .setNegativeButton("Cancel") { _, _ ->
                dismiss()
            }
            .create()

        return dialog
    }

}