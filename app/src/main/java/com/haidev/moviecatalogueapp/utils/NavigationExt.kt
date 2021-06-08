package com.haidev.moviecatalogueapp.utils

import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.haidev.moviecatalogueapp.R

fun Fragment.mainNavController() = requireActivity().findNavController(R.id.nav_host_fragment_main)