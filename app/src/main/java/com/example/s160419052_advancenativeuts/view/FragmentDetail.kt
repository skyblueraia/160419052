package com.example.s160419052_advancenativeuts.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.s160419052_advancenativeuts.R
import com.example.s160419052_advancenativeuts.util.loadImage
import com.example.s160419052_advancenativeuts.viewmodel.FoodDetailViewModel
import com.example.s160419052_advancenativeuts.viewmodel.FoodListViewModel
import kotlinx.android.synthetic.main.fragment_detail.*

class FragmentDetail : Fragment() {
    private lateinit var  viewModel: FoodDetailViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.let {
            val name = FragmentDetailArgs.fromBundle(requireArguments()).foodName
            viewModel = ViewModelProvider(this).get(FoodDetailViewModel::class.java)
            viewModel.fetch(name)

            observeViewModel()
        }
    }

    private fun observeViewModel(){
        viewModel.foodLiveDta.observe(viewLifecycleOwner){
            val food = viewModel.foodLiveDta.value
            food?.let {
                TitleTxt.text = food.name
                imageView2.loadImage(food.photo, progressBar2)
                priceTxt.text = food.price
                infoTxt.text = food.description
            }
        }
    }
}