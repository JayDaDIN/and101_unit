package com.driuft.random_pets_starter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide


class PetAdapter(private val petList: List<String>) : RecyclerView.Adapter<PetAdapter.ViewHolder>() {    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val petImage: ImageView = view.findViewById(R.id.pet_image)
}
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.codepath_demo, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(petList[position])
            .centerCrop()
            .into(holder.petImage)
    }

    override fun getItemCount(): Int {
        return petList.size
    }
}
