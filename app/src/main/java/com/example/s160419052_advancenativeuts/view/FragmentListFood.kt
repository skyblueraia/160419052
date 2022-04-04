package com.example.s160419052_advancenativeuts.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.s160419052_advancenativeuts.R
import com.example.s160419052_advancenativeuts.viewmodel.FoodListViewModel
import kotlinx.android.synthetic.main.fragment_list_food.*


class FragmentListFood : Fragment() {
    private lateinit var  viewModel:FoodListViewModel
    private  val adapaterFoodList = AdapterListFood(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_food, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this).get(FoodListViewModel::class.java)
        viewModel.refresh()
        recyclerViewFood.adapter = adapaterFoodList
        recyclerViewFood.layoutManager = LinearLayoutManager(context)
        refreshLayout.setOnRefreshListener { 
            recyclerViewFood.visibility = View.GONE
            errorTxt.visibility = View.GONE
            progressBar6.visibility = View.VISIBLE
            viewModel.refresh()
            refreshLayout.isRefreshing = false
        }

        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.foodLiveDta.observe(viewLifecycleOwner, Observer {
            adapaterFoodList.updateFoodList(it)
        })

        viewModel.foodErrorLoad.observe(viewLifecycleOwner, Observer {
            if(it != true){
                errorTxt.visibility = View.GONE
            }else{
                errorTxt.visibility = View.VISIBLE
            }
        })

        viewModel.loadingFoodData.observe(viewLifecycleOwner, Observer {
            if(it == true){
                progressBar6.visibility = View.VISIBLE
                recyclerViewFood.visibility = View.GONE
            }else{
                progressBar6.visibility = View.GONE
                recyclerViewFood.visibility = View.VISIBLE
            }
        })
    }
}