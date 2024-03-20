package ru.kor.seal.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.kor.seal.databinding.CardObjectiveBinding
import ru.kor.seal.dto.Objective


class ObjectiveAdapter : ListAdapter<Objective, ObjectiveVH>(ItemCB()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ObjectiveVH {
        val binding =
            CardObjectiveBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ObjectiveVH(binding)
    }

    override fun onBindViewHolder(holder: ObjectiveVH, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

}

class ObjectiveVH(
    private val binding: CardObjectiveBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(objective: Objective) {
        with(binding) {
            name.text = objective.name
            description.text = objective.description
        }
    }
}


class ItemCB : DiffUtil.ItemCallback<Objective>() {
    override fun areItemsTheSame(oldItem: Objective, newItem: Objective): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Objective, newItem: Objective): Boolean {
        return oldItem == newItem
    }

}