package fr.isen.tuveny.androiderestaurant.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import fr.isen.tuveny.androiderestaurant.R
import fr.isen.tuveny.androiderestaurant.databinding.ActivityHomeBinding
import fr.isen.tuveny.androiderestaurant.model.CategoryEnum

class HomeActivity : MenuActivity() {

    private lateinit var buttonEntree: Button
    private lateinit var buttonPlat: Button
    private lateinit var buttonDessert: Button

    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        buttonEntree = binding.buttonHomeEntrees
        buttonPlat = binding.buttonHomePlats
        buttonDessert = binding.buttonHomeDesserts

        setSupportActionBar(binding.homeToolbar)
        supportActionBar?.title = getString(R.string.label_action_bar_home)

        buttonEntree.setOnClickListener {
            val intent = Intent(this, CategoryActivity::class.java)
            intent.putExtra("category", CategoryEnum.ENTREE.cat)
            startActivity(intent)
        }
        buttonPlat.setOnClickListener {
            val intent = Intent(this, CategoryActivity::class.java)
            intent.putExtra("category", CategoryEnum.PLAT.cat)
            startActivity(intent)
        }
        buttonDessert.setOnClickListener {
            val intent = Intent(this, CategoryActivity::class.java)
            intent.putExtra("category", CategoryEnum.DESSERT.cat)
            Log.i("HomeActivity", "Dessert button clicked")
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("HomeActivity", "Fin de vie")
    }

}