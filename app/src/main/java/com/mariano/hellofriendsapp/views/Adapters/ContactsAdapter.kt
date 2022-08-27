package com.mariano.hellofriendsapp.views.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mariano.hellofriendsapp.R
import com.mariano.hellofriendsapp.utils.models.Contacts
import com.mariano.hellofriendsapp.utils.models.searchUsers
import com.squareup.picasso.Picasso

class ContactsAdapter(
    private var list: List<Contacts>,
    private var listener: OnContactClickListener
) : RecyclerView.Adapter<ContactsAdapter.ViewHolder>(){

    interface OnContactClickListener {
        fun onContactClikListener(user: Contacts)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val profileImg = itemView.findViewById<ImageView>(R.id.profile_img)
        val name_user = itemView.findViewById<TextView>(R.id.name_user)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.item_search, parent, false)
        return ViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val users = list[position]

        holder.name_user.text = users.UserContacto.username
        Picasso.get().load(users.UserContacto.image_profile).into(holder.profileImg)

        holder.itemView.setOnClickListener{
            listener.onContactClikListener(users)
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

}