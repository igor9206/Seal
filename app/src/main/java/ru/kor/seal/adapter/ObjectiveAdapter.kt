package ru.kor.seal.adapter

import android.view.Gravity
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.kor.seal.R
import ru.kor.seal.databinding.CardObjectiveBinding
import ru.kor.seal.model.ObjectiveModel


interface OnInteractionClickListener {
    fun removeObjective(objective: ObjectiveModel)
    fun openCard(objective: ObjectiveModel)
}

class ObjectiveAdapter(
    private val onInteractionClickListener: OnInteractionClickListener
) : ListAdapter<ObjectiveModel, ObjectiveVH>(ItemCB()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ObjectiveVH {
        val binding =
            CardObjectiveBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ObjectiveVH(binding, onInteractionClickListener)
    }

    override fun onBindViewHolder(holder: ObjectiveVH, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

}

class ObjectiveVH(
    private val binding: CardObjectiveBinding,
    private val onInteractionClickListener: OnInteractionClickListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(objective: ObjectiveModel) {
        with(binding) {
            name.text = objective.objective.name
            description.text = objective.objective.description

            cardObjective.setOnClickListener {
                onInteractionClickListener.openCard(objective)
            }

            buttonOption.setOnClickListener {
                PopupMenu(it.context, it).apply {
                    inflate(R.menu.card_objective_menu)
                    setOnMenuItemClickListener { menu ->
                        when (menu.itemId) {
                            R.id.delete -> {
                                onInteractionClickListener.removeObjective(objective)
                                true
                            }

                            else -> false
                        }
                    }
                    gravity = Gravity.END
                }.show()
            }
        }
    }
}


class ItemCB : DiffUtil.ItemCallback<ObjectiveModel>() {
    override fun areItemsTheSame(oldItem: ObjectiveModel, newItem: ObjectiveModel): Boolean {
        return oldItem.objective.id == newItem.objective.id
    }

    override fun areContentsTheSame(oldItem: ObjectiveModel, newItem: ObjectiveModel): Boolean {
        return oldItem == newItem
    }

}