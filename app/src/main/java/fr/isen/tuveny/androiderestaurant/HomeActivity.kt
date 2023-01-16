package fr.isen.tuveny.androiderestaurant

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class HomeActivity : AppCompatActivity() {

    private lateinit var buttonEntree: Button
    private lateinit var buttonPlat: Button
    private lateinit var buttonDessert: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonEntree = findViewById(R.id.buttonHomeEntrees)
        buttonPlat = findViewById(R.id.buttonHomePlats)
        buttonDessert = findViewById(R.id.buttonHomeDesserts)

        buttonEntree.setOnClickListener {
            val intent = Intent(this, CategoryActivity::class.java)
            intent.putExtra("category", "entrees")
            startActivity(intent)
        }
        buttonPlat.setOnClickListener {
            val intent = Intent(this, CategoryActivity::class.java)
            intent.putExtra("category", "plats")
            startActivity(intent)
        }
        buttonDessert.setOnClickListener {
            val intent = Intent(this, CategoryActivity::class.java)
            intent.putExtra("category", "deserts")
            Log.i("HomeActivity", "Dessert button clicked")
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("HomeActivity", "Fin de vie")
    }
}