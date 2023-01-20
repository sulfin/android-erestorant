package fr.isen.tuveny.androiderestaurant.activity

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import fr.isen.tuveny.androiderestaurant.R
import fr.isen.tuveny.androiderestaurant.databinding.ActivityCartBinding
import fr.isen.tuveny.androiderestaurant.model.CartItemAdapter
import fr.isen.tuveny.androiderestaurant.model.Preferences
import fr.isen.tuveny.androiderestaurant.model.data.Cart

class CartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCartBinding

    private lateinit var adapter: CartItemAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.cartToolbar)
        supportActionBar?.title = getString(R.string.label_action_bar_cart)

        val cart = Cart.get(this)
        updatePrice(cart)
        adapter = CartItemAdapter(cart.items) {
            cart.save()
            updatePrice(cart)
            val cartSharedPref = getSharedPreferences(Preferences.FILE_CART.key, MODE_PRIVATE)
            with(cartSharedPref.edit()) {
                putInt(Preferences.CART_QUANTITY.key, cart.totalItem)
                apply()
            }
        }
        binding.cartRecycler.layoutManager = LinearLayoutManager(this)
        binding.cartRecycler.adapter = adapter
        binding.cartRecycler.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        binding.button.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle(getString(R.string.label_dialog_confirm_order))
                .setMessage("${getString(R.string.label_dialog_confirm_order_message)} ${cart.totalPrice}€")
                .setPositiveButton(getString(R.string.label_dialog_confirm_order_confirm)) { _, _ ->
                    checkout()
                }
                .setNegativeButton(getString(R.string.label_dialog_confirm_order_cancel)) { _, _ -> }
                .show()
        }

    }

    fun checkout(){
        val cart = Cart.get(this)
        adapter.updateItems(mutableListOf())
        cart.items.clear()
        updatePrice(cart)
        val cartSharedPref = getSharedPreferences(Preferences.FILE_CART.key, MODE_PRIVATE)
        with(cartSharedPref.edit()) {
            putInt(Preferences.CART_QUANTITY.key, cart.totalItem)
            apply()
        }
        cart.save()
    }

    override fun onResume() {
        super.onResume()
        val cart = Cart.get(this)
        adapter.updateItems(cart.items)
        updatePrice(cart)
    }

    fun updatePrice(cart: Cart) {
        val priceText = "${getString(R.string.cart_total_label)} ${cart.totalPrice}€"
        binding.cartTotalLabel.text = priceText
    }
}