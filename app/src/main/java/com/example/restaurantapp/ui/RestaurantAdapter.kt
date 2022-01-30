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

class RestaurantAdapter(private val onRestClick: (RestaurantObject) -> Unit,
private val listener : ClickListener) :
    ListAdapter<RestaurantObject, RestaurantAdapter.ViewHolder>(DiffUtilCallback) {

    interface ClickListener {
        fun onClickListener(item: RestaurantListFragment)
    }

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
            RetrofitConfig.service
                .delRest(restaurant.idRest)
                .enqueue(object : Callback<Void> {
                    override fun onResponse(call: Call<Void>, response: Response<Void>) {
                        if (response.isSuccessful) {
                            getRests()
                            notifyDataSetChanged()
                            Log.d("Network", " restaurant deleted")
                            //Toast.makeText(context,"Deleted message successfully",Toast.LENGTH_SHORT).show()
                        }else{
                            Log.d("Network", " network error")
                            //Toast.makeText(context,"Sorry, we couldn't delete the restaurant. Try again latter",Toast.LENGTH_SHORT).show()
                        }
                    }
                    override fun onFailure(call: Call<Void>, t: Throwable) {
                        //Toast.makeText(context,"Error deleting message",Toast.LENGTH_SHORT).show()
                        Log.e("Network", "Error ${t.localizedMessage}", t)
                    }
                })
        }

        holder.binding.root.setOnClickListener{
            onRestClick(restaurant)
        }
    }

    private fun getRests() {
        RetrofitConfig.service.getRests().enqueue(object : Callback<RestResponse> {
            override fun onResponse(call: Call<RestResponse>, response: Response<RestResponse>) {
                if (response.isSuccessful) {
                    val rest = response.body()
                    submitList(rest?.data.toMap())
                    notifyDataSetChanged()
                } else {
                    Log.e("Network", "error en la conexion")
                }
            }
            override fun onFailure(call: Call<RestResponse>, t: Throwable) {
                Log.e("Network", "error en la conexion", t)
                //Toast.makeText(context, "error de conexion", Toast.LENGTH_SHORT).show()
            }
        })
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