package com.example.plantapp.Utils

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController

class Utils {
    companion object {
        fun navigateTo(fragment: Fragment, actionId: Int) {

            val navController: NavController = fragment.findNavController()
            navController.navigate(actionId)
        }

        fun navigateTo(fragment: Fragment, actionId: Int, bundle: Bundle? = null) {
            val navController: NavController = fragment.findNavController()
            if (bundle != null) {
                navController.navigate(actionId, bundle)
            } else {
                navController.navigate(actionId)
            }
        }

        fun navigateTo(fragment: Fragment, action: NavDirections) {
            val navController: NavController = fragment.findNavController()
            navController.navigate(action)
        }
    }
}