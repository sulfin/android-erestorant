package fr.isen.tuveny.androiderestaurant.model.data

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.lang.reflect.Type

class Cart(
    val items: MutableList<CartLine>,
    val context: Context
) {
    companion object {

        const val CART_FILENAME = "cart.json"
        fun get(ctx: Context): Cart {
            val cartFile = File(ctx.filesDir, CART_FILENAME)
            return if (cartFile.exists()) {
                val cartJson = cartFile.readText()
                val type: Type = object : TypeToken<List<CartLine>>() {}.type
                val items: List<CartLine> = Gson().fromJson(cartJson, type)
                Cart(
                    items.toMutableList(),
                    ctx
                )
            } else {
                Cart(mutableListOf(), ctx)
            }
        }
    }

    fun add(item: CartLine) {
        val index = items.indexOfFirst { it.plat.id == item.plat.id }
        if (index == -1) {
            items.add(item)
        } else {
            items[index].quantity += item.quantity
        }
        save()
    }

    fun save() {
        val cartFile = File(context.filesDir, CART_FILENAME)
        val cartJson = Gson().toJson(items)
        cartFile.writeText(cartJson)
    }
    fun toJson(): String {
        return Gson().toJson(this)
    }

    val totalPrice: Double
        get() {
            var total = 0.0
            items.forEach {
                total += (it.plat.getPrice().toDoubleOrNull() ?: 0.0) * it.quantity
            }
            return total
        }
    val totalItem: Int
        get() {
            var total = 0
            items.forEach {
                total += it.quantity
            }
            return total
        }
}