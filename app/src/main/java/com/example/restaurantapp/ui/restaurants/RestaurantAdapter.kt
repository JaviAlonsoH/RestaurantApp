package com.example.restaurantapp.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurantapp.R
import com.example.restaurantapp.databinding.RestItemBinding
import com.example.restaurantapp.model.RestaurantObject
import com.example.restaurantapp.network.RetrofitConfig
import com.example.restaurantapp.network.response.RestResponse
import com.example.restaurantapp.network.response.Restaurant
import com.example.restaurantapp.network.response.toMap
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RestaurantAdapter(
    private val onRestClick: (RestaurantObject) -> Unit,
    private val onDelClickListener: (RestaurantObject) -> Unit
) :
    ListAdapter<RestaurantObject, RestaurantAdapter.ViewHolder>(DiffUtilCallback) {

    inner class ViewHolder(var binding: RestItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: RestItemBinding = RestItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val restaurant = getItem(position)
        holder.binding.restId.text = restaurant.idRest.toString()
        holder.binding.restName.text = restaurant.name
        holder.binding.restFoodtype.text = restaurant.foodType
        holder.binding.delButton.setOnClickListener {
            onDelClickListener(restaurant)
        }

        holder.binding.root.setOnClickListener {
            onRestClick(restaurant)
        }
    }

}

private object DiffUtilCallback : DiffUtil.ItemCallback<RestaurantObject>() {

    override fun areItemsTheSame(
        oldItem: RestaurantObject,
        newItem: RestaurantObject
    ): Boolean {
        return oldItem.idRest == newItem.idRest
    }

    override fun areContentsTheSame(
        oldItem: RestaurantObject,
        newItem: RestaurantObject
    ): Boolean {
        return oldItem.idRest == newItem.idRest
    }
}