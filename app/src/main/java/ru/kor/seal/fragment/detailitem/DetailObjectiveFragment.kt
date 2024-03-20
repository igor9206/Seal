package ru.kor.seal.fragment.detailitem

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import ru.kor.seal.databinding.FragmentDetailObjectiveBinding
import ru.kor.seal.viewmodel.ObjectiveViewModel


class DetailObjectiveFragment : Fragment() {
    private val viewModel: ObjectiveViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentDetailObjectiveBinding.inflate(inflater, container, false)

        viewModel.dataOpenObjective.observe(viewLifecycleOwner) { objective ->
            if (objective != null) {
                with(binding) {
                    tvName.text = objective.name
                    tvDescription.text = objective.description
                }
            }

        }

        binding.topAppBar.setNavigationOnClickListener {
            viewModel.resetOpenObjective()
            findNavController().navigateUp()
        }

        return binding.root
    }

}