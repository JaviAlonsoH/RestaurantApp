package com.example.restaurantapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurantapp.R
import com.example.restaurantapp.databinding.FragmentRestaurantListBinding
import com.example.restaurantapp.network.RetrofitConfig
import com.example.restaurantapp.network.response.RestResponse
import com.example.restaurantapp.network.response.Restaurant
import com.example.restaurantapp.network.response.toMap
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.CollationElementIterator

class RestaurantListFragment : Fragment() {

    private var _binding: FragmentRestaurantListBinding? = null
    private val binding get() = _binding!!
    private var idRest: Int = 0
    private val adapter: RestaurantAdapter = RestaurantAdapter {
        var name: String = it.name
        var foodType: String = it.foodType
        idRest = it.idRest
        val goToDetail = RestaurantListFragmentDirections.listToDetail(
            it.name,
            it.foodType,
            it.idRest
        )

        findNavController().navigate(goToDetail)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRestaurantListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.restRV.layoutManager = GridLayoutManager(context, 1)
        binding.restRV.adapter = this.adapter
        getRests()
        val goToAdd = RestaurantListFragmentDirections.listToAdd()
        binding.floatingPlus.setOnClickListener {
            findNavController().navigate(goToAdd)
        }
    }

    private fun getRests() {
        RetrofitConfig.service.getRests().enqueue(object : Callback<RestResponse> {
            override fun onResponse(call: Call<RestResponse>, response: Response<RestResponse>) {
                if (response.isSuccessful) {
                    val rest = response.body()
                    adapter.submitList(rest?.data.toMap())
                    adapter.notifyDataSetChanged()
                } else {
                    Log.e("Network", "error en la conexion")
                }
            }
            override fun onFailure(call: Call<RestResponse>, t: Throwable) {
                Log.e("Network", "error en la conexion", t)
                Toast.makeText(context, "error de conexion", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun delRest() {
        RetrofitConfig.service
            .delRest(idRest)
            .enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
                        Toast.makeText(context,"Deleted message successfully",Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(context,"Sorry, we couldn't delete the restaurant. Try again latter",Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Toast.makeText(context,"Error deleting message",Toast.LENGTH_SHORT).show()
                    Log.e("Network", "Error ${t.localizedMessage}", t)
                }
            })
    }

}