package fr.isen.tuveny.androiderestaurant

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import fr.isen.tuveny.androiderestaurant.model.PlatsData
import fr.isen.tuveny.androiderestaurant.model.PlatsViewAdapter

class CategoryActivity : AppCompatActivity() {

    val plats = listOf(
        PlatsData("Salade César", "8€", "Salade verte, tomates, oeufs, croûtons, parmesan", ""),
        PlatsData("Salade Niçoise", "9€", "Salade verte, tomates, oeufs, anchois, olives, thon", ""),
        PlatsData("Salade de fruits", "6€", "Fruits frais", ""),
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)
        val title: String = intent.getStringExtra("category") ?: "Category"
        val titleView = findViewById<TextView>(R.id.text_category_title)
        titleView.text = title

        Snackbar.make(titleView, "Category: $title", Snackbar.LENGTH_LONG).show()

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerCategory)
        val adapter = PlatsViewAdapter(plats)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

    }
}