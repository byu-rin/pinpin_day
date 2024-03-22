package com.byurin.trip.ui.tasks

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.byurin.trip.data.Task
import com.byurin.trip.databinding.ItemTaskBinding

// Task 목록 받아와서 RecyclerView에 표시

class TasksAdapter(private val listener: OnItemClickListener) : ListAdapter<Task, TasksAdapter.TasksViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksViewHolder {
        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TasksViewHolder(binding)
    }
    override fun onBindViewHolder(holder: TasksViewHolder, position: Int) {
        val currentItem = getItem(position)
//        holder.bind(currentItem)
    }

    inner class TasksViewHolder(private val binding: ItemTaskBinding) :
        RecyclerView.ViewHolder(binding.root) {

            init {
                binding.apply {
                    root.setOnClickListener {
                        val position = adapterPosition
                        if (position != RecyclerView.NO_POSITION) {
                            val task = getItem(position)
                            listener.onItemClick(task)
                        }
                    }
//                    cbCompleted.setOnClickListener {
//                        val position = adapterPosition
//                        if (position != RecyclerView.NO_POSITION) {
//                            val task = getItem(position)
//                            listener.onCheckBoxClick(task, cbCompleted.isChecked)
//                        }
//                    }
                }
            }

//        fun bind(task: Task) {
//            binding.apply {
//                cbCompleted.isChecked = task.completed
//                tvName.text = task.name
//                tvName.paint.isStrikeThruText = task.completed
//                ivPriority.isVisible = task.important
//            }
//        }
    }

    interface OnItemClickListener {
        fun onItemClick(task: Task)
        fun onCheckBoxClick(task: Task, isChecked: Boolean)
    }

    // 두 Task 목록을 비교하여 변경사항을 찾아내는 콜백
    class DiffCallback : DiffUtil.ItemCallback<Task>() {
        override fun areItemsTheSame(oldItem: Task, newItem: Task) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Task, newItem: Task) =
            oldItem == newItem

    }
}