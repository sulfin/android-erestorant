package fr.isen.tuveny.androiderestaurant.model.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Api data :
 * "id": "150",
 * "id_shop": "1",
 * "name_fr": "laitue",
 * "name_en": "laitue",
 * "create_date": "2021-01-18 21:42:35",
 * "update_date": "2021-01-18 21:42:35",
 * "id_pizza": "127"
 */
@Parcelize
data class Ingredient(
    val id: String,
    val id_shop: String,
    val name_fr: String,
    val name_en: String,
    val create_date: String,
    val update_date: String,
    val id_pizza: String
): Parcelable
