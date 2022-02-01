package com.example.restaurantapp.ui.restaurants

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.restaurantapp.databinding.FragmentRestaurantListBinding
import com.example.restaurantapp.db
import com.example.restaurantapp.network.RetrofitConfig
import com.example.restaurantapp.network.response.*
import com.example.restaurantapp.ui.RestaurantAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RestaurantListFragment : Fragment() {

    private var _binding: FragmentRestaurantListBinding? = null
    private val binding get() = _binding!!
    private var idRest: Int = 0
    private val adapter: RestaurantAdapter = RestaurantAdapter({
        var name: String = it.name
        var foodType: String = it.foodType
        idRest = it.idRest
        val action = RestaurantListFragmentDirections.listToDetail(
            it.name,
            it.foodType,
            it.idRest
        )

        findNavController().navigate(action)
    }, {
        delRest()
    })

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
                    //get from api and add to db
                    db.restDao().addRest(rest?.data.toEntity())
                    //refresh all list
                    adapter.submitList(rest?.data.toMap())
                } else {
                    Log.e("Network", "error en la conexion")
                    //get from db and refresh
                    val restEntity = db.restDao().findAll()
                    adapter.submitList(restEntity.toModel())

                }
            }

            override fun onFailure(call: Call<RestResponse>, t: Throwable) {
                Log.e("Network", "error en la conexion", t)
                Toast.makeText(context, "error de conexion", Toast.LENGTH_SHORT).show()
                // get all from db and refresh list
                val restEntity = db.restDao().findAll()
                adapter.submitList(restEntity.toModel())

            }
        })
    }

    private fun delRest() {
        RetrofitConfig.service
            .delRest(idRest)
            .enqueue(object : Callback<Void> {
                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    if (response.isSuccessful) {
                        getRests()
                        adapter.notifyDataSetChanged()
                        Log.d("Network", "restaurant deleted")
                        Toast.makeText(
                            context,
                            "Restaurant deleted successfully",
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Log.d("Network", " network error")
                        Toast.makeText(
                            context,
                            "Sorry, we couldn't delete the restaurant. Try again latter",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

                override fun onFailure(call: Call<Void>, t: Throwable) {
                    Toast.makeText(context, "Error deleting restaurant", Toast.LENGTH_SHORT).show()
                    Log.e("Network", "Error ${t.localizedMessage}", t)
                    //delete from db
                    val rest = db.restDao().findRestById(idRest)
                    db.restDao().deleteRest(rest)
                }
            })
    }

}