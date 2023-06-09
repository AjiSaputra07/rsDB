package com.example.pasienrumahsakit

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.pasienrumahsakit.data.User

class UserListAdapter: RecyclerView.Adapter<UserListAdapter.MyViewHolder>(){
    private var userList = emptyList<User>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.custom_row,parent,false))
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.itemView.findViewById<TextView>(R.id.tvnoID).text = currentItem.id.toString()
        holder.itemView.findViewById<TextView>(R.id.tvnoKTP).text = currentItem.noKTP.toString()
        holder.itemView.findViewById<TextView>(R.id.tvnama).text = currentItem.nama
        holder.itemView.findViewById<TextView>(R.id.tvalamat).text = currentItem.alamat
        holder.itemView.findViewById<TextView>(R.id.tvnoHP).text = currentItem.noHP.toString()


        // akasi pada list to update data atau mengubah data
        holder.itemView.findViewById<ConstraintLayout>(R.id.rowLayout).setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }


    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun setData(user: List<User>){
        this.userList = user
        notifyDataSetChanged()
    }
}