package fr.isen.tuveny.androiderestaurant.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.isen.tuveny.androiderestaurant.R
import fr.isen.tuveny.androiderestaurant.databinding.CartCellBinding
import fr.isen.tuveny.androiderestaurant.model.data.CartLine

class CartItemAdapter(val items: MutableList<CartLine>, val onItemDelete: (Int) -> Unit) :
    RecyclerView.Adapter<CartItemAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = CartCellBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val ctx = parent.context
        val inflater = LayoutInflater.from(ctx)
        val itemview = inflater.inflate(R.layout.cart_cell, parent, false)
        return ViewHolder(itemview)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.binding.cellCartName.text = item.plat.name_fr
        val price = (item.plat.getPrice().toDoubleOrNull() ?: 0.0) * item.quantity
        val priceText = "Total : ${price}€"
        holder.binding.cellCartTotalPrice.text = priceText
        val quantityText = "Quantité : ${item.quantity}"
        holder.binding.cellCartQuantity.text = quantityText

        holder.binding.cellCartDelete.setOnClickListener {
            val index = items.indexOf(item)
            items.removeAt(index)
            notifyItemRemoved(index)
            onItemDelete(index)
        }

        if ((item.plat.getImage() ?: "") != "") {
            Picasso.get().load(item.plat.getImage())
                .placeholder(R.drawable.ic_image_placeholder)
                .error(R.drawable.ic_image_placeholder)
                .into(holder.binding.cellCartImage)
        }
    }

    override fun getItemCount() = items.size
    fun updateItems(newItems: MutableList<CartLine>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }
}