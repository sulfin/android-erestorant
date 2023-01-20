package fr.isen.tuveny.androiderestaurant.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.squareup.picasso.Picasso
import fr.isen.tuveny.androiderestaurant.R
import fr.isen.tuveny.androiderestaurant.databinding.ActivityPlatDetailBinding
import fr.isen.tuveny.androiderestaurant.model.ImagePagerAdapter
import fr.isen.tuveny.androiderestaurant.model.data.Plat

class PlatDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlatDetailBinding

    private var plat: Plat? = null

    private var quantity = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlatDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        plat = intent.getParcelableExtra("plat")

        supportActionBar?.title = "Détail du plat"

        populatePlat()
    }

    private fun populatePlat() {
        binding.platDetailNom.text = plat?.name_fr
        val ingredientText = "${getString(R.string.ingredients_label)} ${plat?.getIngredients()}"
        binding.platDetailIngredients.text = ingredientText
        val priceText = "${getString(R.string.button_total_price)} ${plat?.getPrice()}€"
        binding.platDetailButtonAddToCart.text = priceText
        updateQuantity()
        binding.platDetailButonAdd.setOnClickListener {
            quantity++
            updateQuantity()
        }
        binding.platDetailButonRemove.setOnClickListener {
            if (quantity > 1) {
                quantity--
                updateQuantity()
            }
        }

        if (plat?.images != null) {
            binding.platDetailImages.adapter = ImagePagerAdapter(plat?.images!!)
        } else {
            Log.d("PlatDetailActivity", "No images for this plat")
        }
    }

    private fun updateQuantity() {
        binding.platDetailLabelQuantity.text = quantity.toString()
        val price = (plat?.getPrice()?.toInt() ?: 0) * quantity
        val priceText = "${getString(R.string.button_total_price)} ${price}€"
        binding.platDetailButtonAddToCart.text = priceText
    }
}