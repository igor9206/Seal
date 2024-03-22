package ru.kor.seal.fragment.detailitem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import ru.kor.seal.databinding.CardStageBinding
import ru.kor.seal.databinding.FragmentDetailObjectiveBinding
import ru.kor.seal.fragment.newitem.NewStageDialogFragment
import ru.kor.seal.fragment.newitem.NewStageDialogListener
import ru.kor.seal.viewmodel.ObjectiveViewModel


class DetailObjectiveFragment : Fragment() {
    private val viewModel: ObjectiveViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentDetailObjectiveBinding.inflate(inflater, container, false)

        viewModel.data.observe(viewLifecycleOwner) { objectives ->
            val objective =
                objectives.find { it.objective.id == viewModel.dataOpenObjective.value?.objective?.id }

            if (objective != null) {
                with(binding) {
                    tvName.text = objective.objective.name
                    tvDescription.text = objective.objective.description

                    binding.taskContainer.removeAllViews()
                    binding.cardStage.isVisible = objective.stages.isNotEmpty()
                    binding.stageTitle.isVisible = objective.stages.isNotEmpty()
                    binding.stageEmpty.isVisible = objective.stages.isEmpty()

                    objective.stages.forEach { stage ->
                        CardStageBinding.inflate(inflater, binding.taskContainer, true).apply {
                            radioButton.isChecked = stage.finished
                            content.text = stage.text
                        }.root
                    }
                }
            }
        }

        binding.topAppBar.setNavigationOnClickListener {
            viewModel.resetOpenObjective()
            findNavController().navigateUp()
        }

        binding.fab.setOnClickListener {
            showNewStageDialog()
        }

        return binding.root
    }

    private fun showNewStageDialog() {
        val dialog = NewStageDialogFragment(object : NewStageDialogListener {
            override fun onDialogPositiveClick(text: String) {
                viewModel.saveStage(text)
            }
        })
        dialog.show(childFragmentManager, "dialog")
    }

}