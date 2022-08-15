package com.examen.kotlinpresmissionn.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.examen.kotlinpresmissionn.R
import com.examen.kotlinpresmissionn.model.Users

class UserAdapter(private val userList:MutableList<Users>): RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    // ascending/descending function
    fun setUserList(updatedUserList: List<Users>) {
      val diffResult = DiffUtil.calculateDiff(UserDiffUtilCallback(userList,updatedUserList))
        userList.clear()
        userList.addAll(updatedUserList)
        diffResult.dispatchUpdatesTo(this)
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_user,parent,false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val users = userList[position]
        holder.username.text= users.username
        holder.address.text = users.address
    }

    override fun getItemCount(): Int {
      return userList.size
    }


    inner class UserViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val username:AppCompatTextView = itemView.findViewById(R.id.username)
        val address : AppCompatTextView = itemView.findViewById(R.id.address)
    }
}