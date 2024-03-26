package ru.kor.seal.fragment.newitem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
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

        viewModel.dataEditObjective.observe(viewLifecycleOwner) { objective ->
            if (objective.id != 0L) {
                binding.name.editText?.setText(objective.name)
                binding.description.editText?.setText(objective.description)
                binding.topAppBar.title = getString(R.string.edit_objective)
            } else {
                binding.topAppBar.title = getString(R.string.new_objective)
            }
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

    override fun onDestroy() {
        viewModel.resetEditObjective()
        super.onDestroy()
    }

}