package com.github.unscientificjszhai.unscientificlivedata.demo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

/**
 * 首页展示整型列表的RecyclerView的适配器。
 *
 * @author UnscientificJsZhai
 * @param onRemove 当长按移除一个元素时调用的方法。
 */
class IntListAdapter(private val onRemove: (Int) -> Unit) :
    ListAdapter<Int, IntListAdapter.ViewHolder>(DiffCallback) {

    private object DiffCallback : DiffUtil.ItemCallback<Int>() {

        override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean {
            return oldItem == newItem
        }
    }

    inner class ViewHolder(rootView: View) : RecyclerView.ViewHolder(rootView) {
        val textView: TextView = rootView.findViewById(R.id.IntRecycler_TextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.recycler_int,
            parent,
            false
        )
        val viewHolder = ViewHolder(view)

        viewHolder.textView.setOnLongClickListener {
            onRemove(viewHolder.bindingAdapterPosition)
            true
        }

        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = currentList[position].toString()
    }
}