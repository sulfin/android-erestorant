package fr.isen.tuveny.androiderestaurant.model

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import fr.isen.tuveny.androiderestaurant.PlatDetailActivity
import fr.isen.tuveny.androiderestaurant.R
import fr.isen.tuveny.androiderestaurant.model.data.APIData
import fr.isen.tuveny.androiderestaurant.model.data.Plat

class PlatsViewAdapter(private val plats: List<Plat>) : RecyclerView.Adapter<PlatsViewAdapter.PlatsViewHolder>() {
    companion object{
        fun parsePlats(data: String, category: String): List<Plat> {
            val gson = Gson()
            val plats = gson.fromJson(data, APIData::class.java)
            return plats.data.first { it.name_fr == category }.items
        }
    }

    inner class PlatsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameView = itemView.findViewById<TextView>(R.id.textItemName)
        init {
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, PlatDetailActivity::class.java)
                intent.putExtra("plat", plats[adapterPosition])
                Log.d("PlatsViewAdapter", "Plat ${plats[adapterPosition]}")
                itemView.context.startActivity(intent)
            }
        }
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
        holder.nameView.text = plats[position].name_fr
    }



}