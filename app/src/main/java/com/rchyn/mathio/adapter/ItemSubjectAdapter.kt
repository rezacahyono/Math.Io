package com.rchyn.mathio.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rchyn.mathio.databinding.ItemCalcBinding
import com.rchyn.mathio.model.Subject

class ItemSubjectAdapter(
    private val dataSet: List<Subject>
) : RecyclerView.Adapter<ItemSubjectAdapter.ItemSubjectViewHolder>() {

    class ItemSubjectViewHolder(
        private val binding: ItemCalcBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(subject: Subject) {
            binding.apply {
                imageCalc.setImageResource(subject.imageSubject)
                titleSubject.text = itemView.context.getString(subject.title)
            }
        }
    }

    private lateinit var onItemCallbackListener: OnItemCallbackListener
    fun setOnItemCallbackAdapter(onItemCallbackListener: OnItemCallbackListener) {
        this.onItemCallbackListener = onItemCallbackListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemSubjectViewHolder {
        val bindingLayout =
            ItemCalcBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemSubjectViewHolder(bindingLayout)
    }

    override fun onBindViewHolder(holder: ItemSubjectViewHolder, position: Int) {
        holder.bind(dataSet[position])
        holder.itemView.setOnClickListener {
            onItemCallbackListener.onClicked(holder.adapterPosition)
        }
    }

    override fun getItemCount(): Int = dataSet.size


    interface OnItemCallbackListener {
        fun onClicked(position: Int)
    }
}