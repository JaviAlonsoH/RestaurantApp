package com.example.restaurantapp.ui.restaurants

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.restaurantapp.databinding.FragmentRestaurantDetailBinding
import com.example.restaurantapp.db
import com.example.restaurantapp.network.RetrofitConfig
import com.example.restaurantapp.ui.deliveries.DeliveryAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RestaurantDetailFragment : Fragment() {

    private var _binding: FragmentRestaurantDetailBinding? = null
    private val binding get() = _binding!!

    private val args: RestaurantDetailFragmentArgs by navArgs()
    private var name: String? = null
    private var foodType: String? = null
    private var idRest: Int? = null
    private val adapter: DeliveryAdapter = DeliveryAdapter({
        var idDlv: Int = it.idDelivery
        var vehicle: String = it.vehicle
        var address: String = it.address

        val action = RestaurantDetailFragmentDirections.listToDlvDetail(
            it.idDelivery,
            it.vehicle,
            it.restaurant,
            it.address
        )
        findNavController().navigate(action)
    }, {
        //db.restDao().delDlv()
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        args.let {
            name = it.name
            foodType = it.foodType
            idRest = it.idRest
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRestaurantDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.dlvRV.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.detailRestId.text = idRest.toString()
        binding.detailRestName.text = name
        binding.detailRestFoodType.text = foodType
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
/*
    private fun delDlv() {
        RetrofitConfig.service
            .delRest()
            .enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
                        getRests()
                        adapter.notifyDataSetChanged()
                        Log.d("Network", "restaurant deleted")
                        Toast.makeText(context,"Restaurant deleted successfully", Toast.LENGTH_SHORT).show()
                    } else {
                        Log.d("Network", " network error")
                        Toast.makeText(context,"Sorry, we couldn't delete the restaurant. Try again latter",
                            Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Toast.makeText(context,"Error deleting restaurant", Toast.LENGTH_SHORT).show()
                    Log.e("Network", "Error ${t.localizedMessage}", t)
                    //delete from db
                    val rest = db.restDao().findRestById(idRest)
                    db.restDao().deleteRest(rest)
                }
            })
    }

 */
}