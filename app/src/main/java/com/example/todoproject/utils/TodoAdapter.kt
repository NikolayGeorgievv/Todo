package com.example.todoproject.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todoproject.databinding.EachTodoItemBinding

class TodoAdapter(private val list: MutableList<TodoData>) :
    RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    private var listener: TodoAdapterClickListenerInterface? = null
    fun setListener(listener: TodoAdapterClickListenerInterface){
        this.listener = listener
    }

    inner class TodoViewHolder(val binding: EachTodoItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val binding = EachTodoItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodoViewHolder(binding)
    }

    override fun getItemCount(): Int {
       return list.size
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
       with(holder){
           with(list[position]){
               binding.todoTask.text = this.task
               binding.deleteTask.setOnClickListener {
                    listener?.onDeleteTaskBtnClick(this)
               }

               binding.editTask.setOnClickListener {
                    listener?.onEditTaskBtnClick(this)
               }
           }
       }
    }

    interface TodoAdapterClickListenerInterface{
       fun onDeleteTaskBtnClick(todo: TodoData)
       fun onEditTaskBtnClick(todo: TodoData)
    }
}