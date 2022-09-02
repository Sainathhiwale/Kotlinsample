package com.examen.kotlinpresmissionn.adapter

import androidx.recyclerview.widget.DiffUtil
import com.examen.kotlinpresmissionn.model.Users


class UserDiffUtilCallback(private val oldList:List<Users>,private val newList:List<Users>):DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when{
            oldList[oldItemPosition].id == newList[newItemPosition].id ->true
            newList[newItemPosition].username == newList[newItemPosition].username ->true
            else -> false
        }
    }
    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }
}