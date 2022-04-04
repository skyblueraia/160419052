package com.example.s160419052_advancenativeuts.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.s160419052_advancenativeuts.R
import com.example.s160419052_advancenativeuts.model.Food
import com.example.s160419052_advancenativeuts.util.loadImage
import kotlinx.android.synthetic.main.food_list_item.view.*

class AdapterListFood(val listFood: ArrayList<Food>) : RecyclerView.Adapter<AdapterListFood.ViewHolderFood>(){
    class ViewHolderFood(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderFood {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.food_list_item, parent, false)
        return  ViewHolderFood(view)
    }

    override fun onBindViewHolder(holder: ViewHolderFood, position: Int) {
        holder.view.foodNameTxt.text = listFood[position].name
        holder.view.imageView.loadImage(listFood[position].photo, holder.view.progressBar)
        holder.view.InfoBtn.setOnClickListener {
            val action = FragmentListFoodDirections.fragmentDetailAction(listFood[position].name.toString())
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount():Int{
        return listFood.size
    }

    fun updateFoodList(newFoodList: ArrayList<Food>){
        listFood.clear()
        listFood.addAll(newFoodList)
        notifyDataSetChanged()
    }
}