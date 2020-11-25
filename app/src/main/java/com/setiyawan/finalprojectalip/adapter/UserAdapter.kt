package com.setiyawan.finalprojectalip.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.setiyawan.finalprojectalip.R
import com.setiyawan.finalprojectalip.model.realm.User

class UserAdapter (val context: Context): RecyclerView.Adapter<UserAdapter.UserViewHolder>(){
    private var users: MutableList<User> = mutableListOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(LayoutInflater.from(context).inflate(R.layout.item_user, parent,false))
    }

    override fun getItemCount(): Int {
        return users.size
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bindModel(users[position])
    }
    fun setUser(data: List<User>){
        users.clear()
        users.addAll(data)
        notifyDataSetChanged()
    }

    inner class UserViewHolder(i : View): RecyclerView.ViewHolder(i){
        val tvId: TextView = i.findViewById(R.id.tv_id)
        val tvNama: TextView = i.findViewById(R.id.tv_nama)
        val tvEmail: TextView = i.findViewById(R.id.tv_email)
        fun bindModel(u: User){
            tvId.text=u.getId().toString()
            tvNama.text=u.getNama()
            tvEmail.text=u.getEmail()
        }
    }

}