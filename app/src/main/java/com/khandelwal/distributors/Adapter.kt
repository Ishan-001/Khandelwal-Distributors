package com.khandelwal.distributors

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter internal constructor(
    private val context: Context,
    private val requestList: ArrayList<Request>

): RecyclerView.Adapter<Adapter.ViewHolder>(){

    class ViewHolder constructor(itemView: View): RecyclerView.ViewHolder(itemView){
        var title: TextView
        var type: TextView
        var date: TextView
        var description: TextView

        init {
            title=itemView.findViewById(R.id.title)
            type=itemView.findViewById(R.id.type)
            date=itemView.findViewById(R.id.date)
            description=itemView.findViewById(R.id.description)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Adapter.ViewHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.request_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: Adapter.ViewHolder, position: Int) {
        val request= requestList[position]
        holder.title.text=request.title
        holder.type.text=request.type
        holder.date.text=request.date
        holder.description.text=request.description
    }

    override fun getItemCount(): Int {
        return requestList.size
    }
}