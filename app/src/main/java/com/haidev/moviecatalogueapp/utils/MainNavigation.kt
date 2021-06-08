package com.haidev.moviecatalogueapp.utils

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.navigation.NavController
import androidx.navigation.navOptions
import com.haidev.moviecatalogueapp.R

fun NavController.go(
    @IdRes destinationId: Int,
    bundle: Bundle? = null,
    @IdRes popUpToIdRes: Int? = null,
    isInclusive: Boolean = false,
    isLaunchSingleTop: Boolean = false
) {
    navigate(destinationId, bundle, navOptions {
        popUpToIdRes?.let {
            popUpTo(it) {
                inclusive = isInclusive
            }
        }
        launchSingleTop = isLaunchSingleTop
        anim {
            enter = R.anim.nav_default_enter_anim
            exit = R.anim.nav_default_exit_anim
            popEnter = R.anim.nav_default_pop_enter_anim
            popExit = R.anim.nav_default_pop_exit_anim
        }
    })
}

fun NavController.goToHome(
    bundle: Bundle? = null,
    @IdRes popUpToIdRes: Int? = null,
    isInclusive: Boolean = false,
    isLaunchSingleTop: Boolean = false
) {
    go(
        R.id.action_to_homeFragment,
        bundle,
        popUpToIdRes,
        isInclusive,
        isLaunchSingleTop
    )
}