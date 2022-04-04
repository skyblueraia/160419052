package com.example.s160419052_advancenativeuts.viewmodel

import android.app.Application
import android.util.Log
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.s160419052_advancenativeuts.model.Food
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlin.math.log

class FoodListViewModel(application: Application):AndroidViewModel(application) {
    val foodLiveDta = MutableLiveData<ArrayList<Food>>()
    val foodErrorLoad = MutableLiveData<Boolean>()
    val loadingFoodData = MutableLiveData<Boolean>()

    private val TAG = "FoodVolleyTag"
    private var queue: RequestQueue?=null


    fun refresh(){
        foodErrorLoad.value = false
        loadingFoodData.value = true
        queue = Volley.newRequestQueue(getApplication())
        var url = "http://192.168.122.5/FoodList/Food.php"
        val request = StringRequest(Request.Method.GET, url,
            {response->
                val objectType = object : TypeToken<List<Food>>() {}.type
                val gsonResult = Gson().fromJson<ArrayList<Food>>(response, objectType)
                Log.d("ResultGSON", gsonResult.toString())
                foodLiveDta.value = gsonResult

                loadingFoodData.value = false

            },
            {
                loadingFoodData.value= false
                foodErrorLoad.value = true
                Log.d("ErrorGson", it.toString())
            })
            .apply { 
                tag = "TAG"
            }
        queue?.add(request)
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}