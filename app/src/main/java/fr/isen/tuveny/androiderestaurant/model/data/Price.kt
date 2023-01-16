package fr.isen.tuveny.androiderestaurant.model.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Api data :
 * "id": "263",
 * "id_pizza": "127",
 * "id_size": "1",
 * "price": "11",
 * "create_date": "2021-01-23 17:53:25",
 * "update_date": "2021-01-23 17:53:25",
 * "size": "Petite"
 */
@Parcelize
data class Price(
    val id: String,
    val id_pizza: String,
    val id_size: String,
    val price: String,
    val create_date: String,
    val update_date: String,
    val size: String
): Parcelable
