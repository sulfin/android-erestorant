package fr.isen.tuveny.androiderestaurant.model.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Plat(
    val id: String,
    val name_fr: String,
    val name_en: String,
    val id_category: String,
    val categ_name_fr: String,
    val categ_name_en: String,
    val images: List<String>,
    val ingredients: List<Ingredient>,
    val prices: List<Price>
) : Parcelable {
    fun getIngredients() =
        ingredients.foldRightIndexed("") { index, ingredient, acc ->
            ingredient.name_fr + (if (index != ingredients.size - 1) ", " else "") + acc
        }

    fun getPrice()  = prices.firstOrNull()?.price ?: "N/A"
    fun getImage() : String? {
        val im = images.firstOrNull()
        if(im != null){
            if(im == "") return null
        }
        return im
    }

}