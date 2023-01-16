package fr.isen.tuveny.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.squareup.picasso.Picasso
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
        Log.d("PlatDetailActivity", "Avant image : ${plat?.images?.size}")
        if ((plat?.images?.size ?: 0) > 0) {
            if(plat?.images?.first() != "") {
                Picasso.get().load(plat?.images?.first()).into(binding.platDetailImage)
            }
        } else {
            Log.d("PlatDetailActivity", "No image for this plat")
        }
    }
}