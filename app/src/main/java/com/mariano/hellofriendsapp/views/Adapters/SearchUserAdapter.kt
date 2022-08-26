package com.mariano.hellofriendsapp.views.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.mariano.hellofriendsapp.R
import com.mariano.hellofriendsapp.network.apiService.UserServices
import com.mariano.hellofriendsapp.network.viewModels.UserViewModel
import com.mariano.hellofriendsapp.utils.models.searchUsers
import com.squareup.picasso.Picasso

class SearchUserAdapter(
    private var list: List<searchUsers>,
    private var listener: OnUserClickListener
) : RecyclerView.Adapter<SearchUserAdapter.ViewHolder>() {

    interface OnUserClickListener {
        fun onUserClick(user: searchUsers)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val profileImg = itemView.findViewById<ImageView>(R.id.profile_img)
        val name_user = itemView.findViewById<TextView>(R.id.name_user)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.item_search, parent, false)
        return ViewHolder(inflater)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val users = list[position]

        Picasso.get().load(users.image_profile).into(holder.profileImg)
        holder.name_user.text = users.username

        holder.itemView.setOnClickListener{
            listener.onUserClick(users)
        }
    }


}