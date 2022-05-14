package com.example.test.ui.fragments.details

import androidx.core.content.ContextCompat
import com.example.test.extensions.setOnSingleClickListener
import com.example.test.extensions.showToast
import com.example.test.utils.getRandomColor

fun DetailFragment.initElements() {
    binding.apply {
        if (viewModel.beerResponseItem != null) {
            val color = ContextCompat.getColor(requireContext(), getRandomColor())
            containerBeer.backgroundColor = color

            containerBackground.setBackgroundColor(color)
            viewModel.beerResponseItem?.apply {
                beerImage = image_url
                txtTitle.text = name
                txtSince.text = first_brewed
                txtTagline.text = tagline
                txtLevel.text = attenuation_level.toString()
                txtIngredientOne.text = ingredients.yeast
                txtDescription.text = description
            }
            btnBack.setOnSingleClickListener {
                requireActivity().onBackPressed()
            }
        } else {
            showToast("Faltán datos por enviar")
            requireActivity().onBackPressed()
        }
    }
}