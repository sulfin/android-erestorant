package fr.isen.tuveny.androiderestaurant

import android.app.Application
import com.google.android.material.color.DynamicColors

class AndroidERestaurantApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        DynamicColors.applyToActivitiesIfAvailable(this)
    }
}