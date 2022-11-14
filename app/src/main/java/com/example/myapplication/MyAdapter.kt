package com.example.myapplication

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MyAdapter(var context: Context, var modelData: ArrayList<Data> ) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>(),Filterable {

    var filterArrayList:ArrayList<Data> = modelData
    companion object var view_TypeOne: Int = Constant.LISTCOUNTONE

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view: View
        if (view_TypeOne == Constant.LISTCOUNTONE) {
            view = LayoutInflater.from(context).inflate(R.layout.list_view, parent, false)
        } else {
            view = LayoutInflater.from(context).inflate(R.layout.item_small, parent, false)
        }
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        val modelItem = modelData[position]
        holder.setData(modelItem, position)
    }

    override fun getItemCount(): Int {
        return modelData.size
    }


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        var currModel: Data? = null
        var curepos: Int = 0

        fun setData(modelItem: Data, position: Int) {

            var textview: TextView
            var date: TextView
            var img: ImageView

            if (view_TypeOne == Constant.LISTCOUNTONE) {
                textview = itemView.findViewById(R.id.title_tv)
                textview.text = modelItem!!.title

                img=itemView.findViewById(R.id.imag_v)
                Picasso.with(context).load(modelItem.img).into(img);

                date=itemView.findViewById(R.id.date_tvv)
                date.text=dateTime()

            } else {
                textview = itemView!!.findViewById(R.id.title_small)
                textview.text = modelItem!!.title

                img=itemView.findViewById(R.id.image_small)
                Picasso.with(context).load(modelItem.img).into(img);

                date=itemView.findViewById(R.id.date_tv)
                date.text=dateTime()
            }

            curepos = position
            currModel = modelItem

        }

    }

    override fun getFilter(): Filter {

        var filter = object :Filter() {
            override fun performFiltering(p0: CharSequence?): FilterResults {
                var filterResults = FilterResults()

                if (p0!!.isEmpty()) {
                    filterResults.values = filterArrayList
                    filterResults.count = filterArrayList.size

                } else {

                    var searchcharater = p0.toString().lowercase()
                    var filteredResult = ArrayList<Data>()

                    for (data in filterArrayList) {

                        if (data.title?.lowercase()!!.contains(searchcharater)) {

                            filteredResult.add(data)
                        }

                    }
                    filterResults.values = filteredResult
                    filterResults.count = filteredResult.size

                }

                return filterResults

            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {

                modelData = p1!!.values as  ArrayList<Data>
                notifyDataSetChanged()
            }

        }

        return filter
    }

    fun dateTime():String{

        val dateFormat = SimpleDateFormat("dd-MMM-yy hh.mm aa")
        val formattedDate: String = dateFormat.format(Date()).toString()
        //println("date->"+formattedDate)
        return formattedDate
    }


}


