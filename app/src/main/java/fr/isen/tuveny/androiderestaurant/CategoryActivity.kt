package fr.isen.tuveny.androiderestaurant

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.snackbar.Snackbar
import fr.isen.tuveny.androiderestaurant.databinding.ActivityCategoryBinding
import fr.isen.tuveny.androiderestaurant.model.PlatsViewAdapter

class CategoryActivity : AppCompatActivity() {
    companion object {
        const val URL = "http://test.api.catering.bluecodegames.com/menu"
    }

    private lateinit var category: String

    lateinit var binding: ActivityCategoryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        category = intent.getStringExtra("category") ?: "Entrées"
        supportActionBar?.title = category

        populatePlats()
    }

    private fun populatePlats() {
        val queue = Volley.newRequestQueue(this)

        val stringRequest = object : StringRequest(Request.Method.POST, URL,
            { response ->
                val plats = PlatsViewAdapter.parsePlats(response, category)
                binding.recyclerCategory.layoutManager = LinearLayoutManager(this)
                binding.recyclerCategory.adapter = PlatsViewAdapter(plats)
                Log.i("CategoryActivity", "Plats récupérés")
            },
            { error ->
                Snackbar.make(
                    binding.recyclerCategory,
                    "Erreur de connexion",
                    Snackbar.LENGTH_LONG
                ).show()
                Log.e("CategoryActivity", error.toString())
            }
        ) {
            override fun getBody(): ByteArray {
                return "{\"id_shop\":\"1\"}".toByteArray()
            }

            override fun getBodyContentType(): String {
                return "application/json"
            }
        }
        queue.add(stringRequest)
    }
}