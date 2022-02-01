package com.example.restaurantapp.ui.restaurants

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.restaurantapp.databinding.FragmentAddRestaurantBinding
import com.example.restaurantapp.network.RetrofitConfig
import com.example.restaurantapp.network.request.RestRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddRestaurantFragment : Fragment() {

    private var _binding: FragmentAddRestaurantBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddRestaurantBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnAdd.setOnClickListener {
            validateMsg()
            val action = AddRestaurantFragmentDirections.addToList()
            findNavController().navigate(action)
        }
    }

    private fun validateMsg() {
        val restaurantName = binding.restName.text.toString()
        if (restaurantName.isNotEmpty()) {
            addRest(restaurantName)
        }
    }


    private fun addRest(name: String) {
        RetrofitConfig.service
            .addRest(RestRequest(1, name, binding.foodType.text.toString()))
            .enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {

                        Toast.makeText(context,"Restaurant added successfully", Toast.LENGTH_SHORT).show()

                    }else{
                        Toast.makeText(context,"Sorry, we couldn't add the restaurant. Try again latter",
                            Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Toast.makeText(context,"Error adding restaurant", Toast.LENGTH_SHORT).show()
                    Log.e("Network", "Error ${t.localizedMessage}", t)
                }
            })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}