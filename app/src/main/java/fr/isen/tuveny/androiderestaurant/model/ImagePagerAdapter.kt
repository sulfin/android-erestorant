package fr.isen.tuveny.androiderestaurant.model

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import fr.isen.tuveny.androiderestaurant.R

class ImagePagerAdapter(private val images: List<String>) : RecyclerView.Adapter<ImagePagerAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.platDetailPagerImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val imageLayout = inflater.inflate(R.layout.plat_image_item, parent, false)
        return ViewHolder(imageLayout)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val image = images[position]
        if (image != "") {
            Picasso.get().load(image)
                .placeholder(R.drawable.ic_image_placeholder)
                .error(R.drawable.ic_image_placeholder)
                .into(holder.image)
        }
    }

    override fun getItemCount() = images.size
}