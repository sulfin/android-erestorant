package fr.isen.tuveny.androiderestaurant.activity

import android.content.Intent
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import fr.isen.tuveny.androiderestaurant.R
import fr.isen.tuveny.androiderestaurant.model.Preferences

abstract class MenuActivity : AppCompatActivity() {

    private var cartNbItem: TextView? = null
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_achat, menu)
        val actionCart = menu.findItem(R.id.actionCart).actionView
        Log.d("MenuActivity", "actionCart: $actionCart")
        if (actionCart != null) {
            cartNbItem = actionCart.findViewById(R.id.actionCartItemCount)
            updateCartQuantity()
        }
        actionCart?.setOnClickListener {
            val intent = Intent(this, CartActivity::class.java)
            startActivity(intent)
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onResume() {
        super.onResume()
        updateCartQuantity()
    }

    protected fun updateCartQuantity() {
        val sp = getSharedPreferences(Preferences.FILE_CART.key,MODE_PRIVATE)
        val nbItem = sp.getInt(Preferences.CART_QUANTITY.key, 0)
        if (nbItem > 0) {
            cartNbItem?.text = nbItem.toString()
            cartNbItem?.visibility = TextView.VISIBLE
        } else {
            cartNbItem?.visibility = TextView.GONE
        }
    }
}