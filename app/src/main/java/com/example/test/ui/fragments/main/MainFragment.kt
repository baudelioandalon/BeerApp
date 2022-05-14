package com.example.test.ui.fragments.main

import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import com.example.test.R
import com.example.test.databinding.ItemBeerBinding
import com.example.test.databinding.MainFragmentBinding
import com.example.test.domain.entity.BeerResponseItem
import com.example.test.extensions.setOnSingleClickListener
import com.example.test.networking.core.StatusRequestEnum
import com.example.test.ui.activities.main.MainViewModel
import com.example.test.utils.GAdapter
import com.example.test.utils.base.BaseFragment
import com.example.test.utils.getRandomColor
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MainFragment : BaseFragment<MainFragmentBinding>() {

    val viewModel: MainViewModel by sharedViewModel()

    val adapterRecyclerBeers by lazy {
        GAdapter<ItemBeerBinding, BeerResponseItem>(
            R.layout.item_beer,
            AsyncDifferConfig.Builder(object : DiffUtil.ItemCallback<BeerResponseItem>() {
                override fun areItemsTheSame(
                    oldItem: BeerResponseItem,
                    newItem: BeerResponseItem
                ) = oldItem == newItem

                override fun areContentsTheSame(
                    oldItem: BeerResponseItem,
                    newItem: BeerResponseItem
                ) = oldItem == newItem

            }).build(),
            holderCallback = { binding, model, list, adapter ->
                binding.apply {
                    customModel = model
                    containerBeer.apply {
                        backgroundColor = ContextCompat.getColor(requireContext(), getRandomColor())
                        setOnSingleClickListener {
                            viewModel.beerResponseItem = model
                            findNavController().navigate(R.id.action_mainFragment_to_detailFragment)
                        }
                    }
                }
            }
        )
    }

    override fun getLayout() = R.layout.main_fragment

    override fun initObservers() {
        viewModel.beerList.observe(viewLifecycleOwner) {
            it?.let {
                when (it.statusRequest) {
                    StatusRequestEnum.FAILURE -> {
                        hideProgressBarCustom()
                    }
                    StatusRequestEnum.LOADING -> {
                        showProgressBarCustom()
                    }
                    StatusRequestEnum.SUCCESS -> {
                        hideProgressBarCustom()
                        it.successData?.let { result ->
                            adapterRecyclerBeers.submitList(result)
                        }
                    }
                }
            }
        }
    }

    override fun initView() {
        initElements()
    }

}