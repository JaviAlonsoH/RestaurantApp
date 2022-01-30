package com.example.restaurantapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.restaurantapp.R
import com.example.restaurantapp.databinding.FragmentRestaurantDetailBinding

class RestaurantDetailFragment : Fragment() {

    private var _binding: FragmentRestaurantDetailBinding? = null
    private val binding get() = _binding!!

    private val args: RestaurantDetailFragmentArgs by navArgs()
    private var name: String? = null
    private var foodType: String? = null
    private var idRest: Int? = null

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

        binding.detailRestId.text = idRest.toString()
        binding.detailRestName.text = name
        binding.detailRestFoodType.text = foodType
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}