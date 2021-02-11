package com.example.dogpetapp.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dogpetapp.R
import com.example.dogpetapp.model.DogBreed
import com.example.dogpetapp.util.getProgressDrawable
import com.example.dogpetapp.util.loadImage
import kotlinx.android.synthetic.main.dog_eachitem.view.*
import java.util.zip.Inflater

class DogListAdapter(var dogList:List<DogBreed>, val dogListener: DogListener):RecyclerView.Adapter<DogListViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogListViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view:View = inflater.inflate(R.layout.dog_eachitem,parent, false)
        return DogListViewHolder(view,dogListener)

    }

    override fun onBindViewHolder(holder: DogListViewHolder, position: Int) {
       val dogitem = dogList[position]
        holder.dog_name.text = dogitem.dogBreed
        holder.lifespan.text = dogitem.life_span
        holder.image.loadImage(dogitem.imageUrl, getProgressDrawable(holder.image.context))
    }

    override fun getItemCount() = dogList.size

    fun setDogData(dogListItem:List<DogBreed>){
        dogList = dogListItem
        notifyDataSetChanged()

    }
}


class DogListViewHolder(view: View, dogListener: DogListener):RecyclerView.ViewHolder(view){
    val image:ImageView = view.imageItem
    val dog_name:TextView = view.name
    val lifespan:TextView = view.lifespan
    private val dogitemListener:DogListener = dogListener

    init {
        itemView.setOnClickListener {
            dogitemListener.onDogItemClick(adapterPosition)
        }
    }
}


interface DogListener{
    fun onDogItemClick(position:Int)
}
