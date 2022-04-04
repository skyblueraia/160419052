package com.example.s160419052_advancenativeuts.viewmodel

import android.app.Application
import android.util.Log
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

class FoodDetailViewModel(application: Application): AndroidViewModel(application){
    val foodLiveDta = MutableLiveData<Food>()
    private var queue: RequestQueue?=null
    private val TAG = "FoodVolleyTag"

    fun fetch(foodName: String?){
        queue = Volley.newRequestQueue(getApplication())
        var name =foodName+".php"
        var url = "http://192.168.122.5/FoodList/"+name
        val request = StringRequest(
            Request.Method.GET, url,
            {response->
                val objectType = object : TypeToken<Food>() {}.type
                val gsonResult = Gson().fromJson<Food>(response, objectType)
                foodLiveDta.value = gsonResult
                Log.d("ResultGSONDetail", gsonResult.toString())

            },
            {
                Log.d("ErrorGsonDetail", it.toString())
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