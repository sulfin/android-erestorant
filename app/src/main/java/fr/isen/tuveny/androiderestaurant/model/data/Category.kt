package fr.isen.tuveny.androiderestaurant.model.data

data class Category(
    val name_fr: String,
    val name_en: String,
    val items: List<Plat>
)
