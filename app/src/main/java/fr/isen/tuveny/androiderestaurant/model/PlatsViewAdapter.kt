package fr.isen.tuveny.androiderestaurant.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.isen.tuveny.androiderestaurant.R

class PlatsViewAdapter(private val plats: List<PlatsData>) : RecyclerView.Adapter<PlatsViewAdapter.PlatsViewHolder>() {

    inner class PlatsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameView = itemView.findViewById<TextView>(R.id.textItemName)
        //val priceView = itemView.findViewById<TextView>(R.id.textItemPrice)
        //val descriptionView = itemView.findViewById<TextView>(R.id.textItemDescription)
        //val imageView = itemView.findViewById<ImageView>(R.id.imageItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlatsViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val platView = inflater.inflate(R.layout.plat_item, parent, false)
        return PlatsViewHolder(platView)
    }

    override fun getItemCount(): Int {
        return plats.size
    }

    override fun onBindViewHolder(holder: PlatsViewHolder, position: Int) {
        holder.nameView.text = plats[position].name
    }

}