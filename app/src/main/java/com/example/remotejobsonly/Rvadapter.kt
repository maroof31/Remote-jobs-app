package com.example.remotejobsonly

import android.content.Context
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlin.text.split


class Rvadapter(private val listner: MainActivity): RecyclerView.Adapter<JobViewholder>() {
    private val items: ArrayList<jobdetails> = ArrayList<jobdetails>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobViewholder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.item_job,parent,false)
        val viewholder=JobViewholder(view)
        view.setOnClickListener{
            listner.itemClick(items[viewholder.adapterPosition])
        }
        return viewholder
    }

    override fun onBindViewHolder(holder: JobViewholder, position: Int) {
        val currentitem=items[position]
            holder.companyName.text=currentitem.companyname.toString()
        holder.jobType.text= currentitem.jobtype.toString()
        if(currentitem.jobtitle.contains('(')){
            val delim="("
            val title=currentitem.jobtitle.split(delim)
                holder.jobtitle.text= title[0].toString()

        }else{
            holder.jobtitle.text= currentitem.jobtitle.toString()
        }

        holder.region.text="region:"+currentitem.region.toString()
     Glide.with(holder.logo.context).load(currentitem.imageurl).into(holder.logo)
    }


    override fun getItemCount(): Int
    {
        return items.size
    }
    fun updatejob(updatedejob:ArrayList<jobdetails>)
    {
        items.clear()
        items.addAll(updatedejob)
        notifyDataSetChanged()
    }
}
class JobViewholder(itemView: View) : RecyclerView.ViewHolder(itemView)
{
    val jobtitle: TextView =itemView.findViewById(R.id.tvjobtitle)
    val companyName:TextView=itemView.findViewById(R.id.tvcompanyname)
    val region:TextView=itemView.findViewById(R.id.tvregion)
    val jobType:TextView=itemView.findViewById(R.id.tvjobtype)
   val logo:ImageView=itemView.findViewById(R.id.ivlogo)
}
interface  OnItemClick
{
    fun itemClick(item: jobdetails)
}