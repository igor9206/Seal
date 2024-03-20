package ru.kor.seal.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.divider.MaterialDividerItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import ru.kor.seal.R
import ru.kor.seal.adapter.ObjectiveAdapter
import ru.kor.seal.databinding.FragmentMainBinding
import ru.kor.seal.viewmodel.ObjectiveViewModel

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val viewModel: ObjectiveViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentMainBinding.inflate(inflater, container, false)

        val adapter = ObjectiveAdapter()
        binding.recyclerView.apply {
            addItemDecoration(
                MaterialDividerItemDecoration(
                    requireContext(),
                    RecyclerView.VERTICAL
                ).apply {
                    isLastItemDecorated = false
                })
            this.adapter = adapter
        }


        viewModel.dataObjectives.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }

        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_newObjectiveFragment)
        }

        return binding.root
    }

}