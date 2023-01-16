package fr.isen.tuveny.androiderestaurant

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import fr.isen.tuveny.androiderestaurant.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

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

        buttonEntree.setOnClickListener {
            val intent = Intent(this, CategoryActivity::class.java)
            intent.putExtra("category", "Entrées")
            startActivity(intent)
        }
        buttonPlat.setOnClickListener {
            val intent = Intent(this, CategoryActivity::class.java)
            intent.putExtra("category", "Plats")
            startActivity(intent)
        }
        buttonDessert.setOnClickListener {
            val intent = Intent(this, CategoryActivity::class.java)
            intent.putExtra("category", "Desserts")
            Log.i("HomeActivity", "Dessert button clicked")
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("HomeActivity", "Fin de vie")
    }
}