package fr.isen.tuveny.androiderestaurant.activity

import android.content.Intent
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
import fr.isen.tuveny.androiderestaurant.model.data.Plat

class CategoryActivity : AppCompatActivity() {
    companion object {
        const val URL = "http://test.api.catering.bluecodegames.com/menu"
    }

    private lateinit var category: String

    lateinit var binding: ActivityCategoryBinding

    private lateinit var adapter: PlatsViewAdapter

    private var cache: List<Plat>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        category = intent.getStringExtra("category") ?: "Entrées"
        supportActionBar?.title = category

        adapter = PlatsViewAdapter(mutableListOf(), this::changeActivityDetail)
        binding.recyclerCategory.adapter = adapter
        binding.recyclerCategory.layoutManager = LinearLayoutManager(this)

        binding.categorySwipeLayout.setOnRefreshListener {
            getPlatNetwork()
        }

        populatePlats()
    }

    private fun changeActivityDetail(plat: Plat) {
        val intent = Intent(this, PlatDetailActivity::class.java)
        intent.putExtra("plat", plat)
        Log.d("PlatsViewAdapter", "Plat $plat")
        startActivity(intent)
    }

    private fun updatePlats(plats: List<Plat>) {
        adapter.clear()
        adapter.addAll(plats)
    }

    private fun populatePlats() {
        if (cache != null) {
            Log.d("CategoryActivity", "populatePlats: cache")
            updatePlats(cache!!)
        } else {
            Log.d("CategoryActivity", "populatePlats: network")
            getPlatNetwork()
        }
    }

    private fun getPlatNetwork() {
        val queue = Volley.newRequestQueue(this)


        val stringRequest = object : StringRequest(Request.Method.POST, URL,
            { response ->
                val plats = PlatsViewAdapter.parsePlats(response, category)
                updatePlats(plats)
                binding.categorySwipeLayout.isRefreshing = false
                Log.i("CategoryActivity", "Plats récupérés")
            },
            { error ->
                Snackbar.make(
                    binding.recyclerCategory,
                    "Erreur de connexion",
                    Snackbar.LENGTH_LONG
                ).show()
                binding.categorySwipeLayout.isRefreshing = false
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