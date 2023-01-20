package fr.isen.tuveny.androiderestaurant.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import fr.isen.tuveny.androiderestaurant.R
import fr.isen.tuveny.androiderestaurant.databinding.ActivityPlatDetailBinding
import fr.isen.tuveny.androiderestaurant.model.ImagePagerAdapter
import fr.isen.tuveny.androiderestaurant.model.data.Cart
import fr.isen.tuveny.androiderestaurant.model.data.CartLine
import fr.isen.tuveny.androiderestaurant.model.data.Plat
import java.io.File

class PlatDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlatDetailBinding

    private var plat: Plat? = null

    private var quantity: Int = 1

    private lateinit var cart: Cart
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlatDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        plat = intent.getParcelableExtra("plat")
        supportActionBar?.title = "Détail du plat"

        cart = Cart.get(this)

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

        binding.platDetailButtonAddToCart.setOnClickListener {
            addToCart()
        }

        if (plat?.images != null) {
            binding.platDetailImages.adapter = ImagePagerAdapter(plat?.images!!)
        } else {
            Log.d("PlatDetailActivity", "No images for this plat")
        }
    }

    private fun updateQuantity() {
        binding.platDetailLabelQuantity.text = quantity.toString()
        val price = (plat?.getPrice()?.toDoubleOrNull() ?: 0.0) * quantity
        val priceText = "${getString(R.string.button_total_price)} ${price}€"
        binding.platDetailButtonAddToCart.text = priceText
    }

    private fun addToCart() {
        cart.items.add(
            CartLine(
                plat!!,
                quantity
            )
        )
        cart.save()
        Snackbar.make(binding.root, "Plat ajouté au panier", Snackbar.LENGTH_SHORT).show()
        quantity = 1
        updateQuantity()
    }
}