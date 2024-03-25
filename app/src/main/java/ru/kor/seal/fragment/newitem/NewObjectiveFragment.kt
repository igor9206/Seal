package ru.kor.seal.fragment.newitem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.hilt.android.AndroidEntryPoint
import ru.kor.seal.R
import ru.kor.seal.databinding.FragmentNewObjectiveBinding

import ru.kor.seal.viewmodel.ObjectiveViewModel

@AndroidEntryPoint
class NewObjectiveFragment : Fragment() {

    private val viewModel: ObjectiveViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentNewObjectiveBinding.inflate(inflater, container, false)

        binding.topAppBar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        binding.topAppBar.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.save -> {
                    viewModel.saveObjective(
                        binding.name.editText?.text.toString().trim(),
                        binding.description.editText?.text.toString().trim()
                    )
                    findNavController().navigateUp()
                    true
                }

                else -> false
            }
        }

        return binding.root
    }

}