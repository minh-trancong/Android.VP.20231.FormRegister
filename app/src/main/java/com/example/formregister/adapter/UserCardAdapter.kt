package com.mtu.formregister.adapter

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.formregister.R
import com.mtu.formregister.model.User
import java.text.SimpleDateFormat

class UserCardAdapter(private val userList: List<User>) : RecyclerView.Adapter<UserCardAdapter.UserCardViewHolder>() {
    val dateFormat = SimpleDateFormat("dd/MM/yyyy")

    inner class UserCardViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        private val textView_name: TextView = itemView.findViewById(R.id.tv_name)
        private val textView_email: TextView = itemView.findViewById(R.id.tv_email)
        private val textView_sex: TextView = itemView.findViewById(R.id.tv_sex)
        private val textView_dob: TextView = itemView.findViewById(R.id.tv_dob)

        fun bind(user: User){
            val combinedName = user.firstName + " " + user.lastName
            textView_name.text = combinedName
            textView_email.text = user.email
            textView_sex.text = if (user.sex) "Man" else "Woman"
            textView_dob.text = dateFormat.format(user.dob)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserCardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.user_card, parent, false)
        return UserCardViewHolder(view)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    override fun onBindViewHolder(holder: UserCardViewHolder, position: Int) {
        val user: User = userList[position]
        holder.bind(user)
    }

}