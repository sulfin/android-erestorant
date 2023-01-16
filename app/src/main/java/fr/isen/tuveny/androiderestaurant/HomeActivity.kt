package fr.isen.tuveny.androiderestaurant

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast

class HomeActivity : AppCompatActivity() {

    lateinit var buttonEntree: Button
    lateinit var buttonPlat: Button
    lateinit var buttonDessert: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        buttonEntree = findViewById(R.id.button_entrees)
        buttonPlat = findViewById(R.id.button_plats)
        buttonDessert = findViewById(R.id.button_deserts)

        buttonEntree.setOnClickListener {
            Toast.makeText(this, "Entrees", Toast.LENGTH_SHORT).show()
        }
        buttonPlat.setOnClickListener {
            Toast.makeText(this, "Plats", Toast.LENGTH_SHORT).show()
        }
        buttonDessert.setOnClickListener {
            Toast.makeText(this, "Desserts", Toast.LENGTH_SHORT).show()
        }
    }
}