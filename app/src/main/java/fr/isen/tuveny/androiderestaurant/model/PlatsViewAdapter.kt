package fr.isen.tuveny.androiderestaurant.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import fr.isen.tuveny.androiderestaurant.R
import fr.isen.tuveny.androiderestaurant.model.data.APIData
import fr.isen.tuveny.androiderestaurant.model.data.Plat

class PlatsViewAdapter(private val plats: MutableList<Plat>, val onItemClick: ((Plat) -> Unit)) :
    RecyclerView.Adapter<PlatsViewAdapter.PlatsViewHolder>() {
    companion object {
        fun parsePlats(data: String, category: String): List<Plat> {
            val gson = Gson()
            val plats = gson.fromJson(data, APIData::class.java)
            return plats.data.first { it.name_fr == category }.items
        }
    }

    fun addAll(plats: List<Plat>) {
        this.plats.addAll(plats)
        notifyDataSetChanged()
    }

    fun clear() {
        this.plats.clear()
        notifyDataSetChanged()
    }

    inner class PlatsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemName: TextView = itemView.findViewById(R.id.platListItemName)
        val itemPrice: TextView = itemView.findViewById(R.id.platListItemPrice)
        val itemImage: ImageView = itemView.findViewById(R.id.platListItemImage)
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
        val plat = plats[position]
        holder.itemName.text = plat.name_fr
        val priceText = "${plat.getPrice()}€"
        holder.itemPrice.text = priceText
        if (plat.getImage() != null) {
            Picasso.get().load(plat.getImage())
                .placeholder(R.drawable.ic_image_placeholder)
                .error(R.drawable.ic_image_placeholder)
                .into(holder.itemImage)
        }

        holder.itemView.setOnClickListener {
            onItemClick(plat)
        }
    }


}