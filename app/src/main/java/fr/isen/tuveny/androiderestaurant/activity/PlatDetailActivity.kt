package fr.isen.tuveny.androiderestaurant.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.squareup.picasso.Picasso
import fr.isen.tuveny.androiderestaurant.R
import fr.isen.tuveny.androiderestaurant.databinding.ActivityPlatDetailBinding
import fr.isen.tuveny.androiderestaurant.model.data.Plat

class PlatDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPlatDetailBinding

    private var plat: Plat? = null
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
        val priceText = "${getString(R.string.prix_label)} ${plat?.getPrice()}€"
        binding.platDetailPrice.text = priceText

        if (plat?.getImage() != null) {
            Picasso.get().load(plat?.getImage()).into(binding.platDetailImage)
        } else {
            Log.d("PlatDetailActivity", "No image for this plat")
        }
    }
}