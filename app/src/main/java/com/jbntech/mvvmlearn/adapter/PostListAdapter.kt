package com.jbntech.mvvmlearn.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.jbntech.mvvmlearn.R
import com.jbntech.mvvmlearn.model.PostsList

class PostListAdapter: RecyclerView.Adapter<PostListAdapter.MyViewHolder>() {


    private var postList = emptyList<PostsList>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        //create layout inflator
        val layoutInflater = LayoutInflater.from(parent.context).inflate(R.layout.adapter_post_list, parent, false)

        return MyViewHolder(layoutInflater)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val currentItem = postList[position]

        holder.txtBody.text = currentItem.body
        holder.txtTitle.text = currentItem.title
        holder.layout_Constraint.setOnClickListener { view ->
            val itemclick = "ID: ${currentItem.id} user: ${currentItem.userId} title: ${currentItem.title}"
            Log.d("ON CLICK", itemclick)
        }

    }

    override fun getItemCount(): Int {

        return postList.size
    }

    fun setData(postsList: List<PostsList>){
            this.postList = postsList

        notifyDataSetChanged()
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        var txtTitle = itemView.findViewById<TextView>(R.id.txtTitle)
        var txtBody = itemView.findViewById<TextView>(R.id.txtBody)
        var layout_Constraint = itemView.findViewById<ConstraintLayout>(R.id.layout_Constraint)

    }
}