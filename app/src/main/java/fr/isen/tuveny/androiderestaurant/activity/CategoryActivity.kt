package fr.isen.tuveny.androiderestaurant.activity

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.snackbar.Snackbar
import fr.isen.tuveny.androiderestaurant.R
import fr.isen.tuveny.androiderestaurant.databinding.ActivityCategoryBinding
import fr.isen.tuveny.androiderestaurant.model.PlatsViewAdapter
import fr.isen.tuveny.androiderestaurant.model.data.Plat
import org.json.JSONObject

class CategoryActivity : MenuActivity() {
    companion object {
        const val URL = "http://test.api.catering.bluecodegames.com/menu"
    }

    private lateinit var category: String

    private lateinit var binding: ActivityCategoryBinding

    private lateinit var adapter: PlatsViewAdapter

    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPref = getPreferences(MODE_PRIVATE)

        category = intent.getStringExtra("category") ?: "Entrées"

        setSupportActionBar(binding.categoryToolbar)
        supportActionBar?.title = category

        adapter = PlatsViewAdapter(mutableListOf(), this::changeActivityDetail)
        binding.recyclerCategory.adapter = adapter
        binding.recyclerCategory.layoutManager = LinearLayoutManager(this)

        binding.categorySwipeLayout.setOnRefreshListener {
            invalidateCache()
            populatePlats()
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

    private fun invalidateCache() {
        with(sharedPref.edit()) {
            putLong(getString(R.string.cache_key_timestamp), 0)
            apply()
        }
    }

    private fun populatePlats() {
        binding.categorySwipeLayout.isRefreshing = true
        val cache = sharedPref.getString(getString(R.string.cache_key), null)
        val cache_ts = sharedPref.getLong(getString(R.string.cache_key_timestamp), 0)

        var cacheValid = false

        Log.d("CategoryActivity", "CacheTS: $cache_ts Cache: $cache")
        if (cache != null && cache_ts > 0) {
            val now = System.currentTimeMillis() / 1000
            val diff = now - cache_ts
            cacheValid = diff / 60 < resources.getInteger(R.integer.cache_valid_min)
        }

        if (cacheValid) {
            Log.d("CategoryActivity", "populatePlats: cache")
            val plats = Plat.parsePlats(cache!!, category)
            updatePlats(plats)
            binding.categorySwipeLayout.isRefreshing = false
        } else {
            Log.d("CategoryActivity", "populatePlats: network")
            getPlatNetwork()
        }
    }

    private fun getPlatNetwork() {
        val queue = Volley.newRequestQueue(this.applicationContext)

        val requestBody = JSONObject()
        requestBody.put("id_shop", "1")

        val jsonRequest = JsonObjectRequest(
            Request.Method.POST,
            URL,
            requestBody,
            { response ->
                handleResponse(response)
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
        )
        queue.add(jsonRequest)
    }

    private fun handleResponse(response: JSONObject) {
        with(sharedPref.edit()) {
            putString(getString(R.string.cache_key), response.toString())
            val ts = System.currentTimeMillis() / 1000
            putLong(getString(R.string.cache_key_timestamp), ts)
            apply()
        }
        val plats = Plat.parsePlats(response.toString(), category)
        updatePlats(plats)
        binding.categorySwipeLayout.isRefreshing = false
        Log.i("CategoryActivity", "Plats récupérés")
    }
}