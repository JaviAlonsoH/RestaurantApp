package com.example.restaurantapp.ui.deliveries

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.restaurantapp.databinding.DeliveryItemBinding
import com.example.restaurantapp.model.DeliveryObject

class DeliveryAdapter (
    private val onDlvClick: (DeliveryObject) -> Unit,
    private val onDelDlvClickListener: (DeliveryObject) -> Unit
) :
    ListAdapter<DeliveryObject, DeliveryAdapter.ViewHolder>(DiffUtilCallback) {

    inner class ViewHolder(var binding: DeliveryItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: DeliveryItemBinding =
            DeliveryItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val delivery = getItem(position)
        holder.binding.idDlv.text = delivery.idDelivery.toString()

        holder.binding.root.setOnClickListener {
            onDlvClick(delivery)
        }

        holder.binding.btnDelDlv.setOnClickListener {
            onDelDlvClickListener(delivery)
        }

    }


}

private object DiffUtilCallback : DiffUtil.ItemCallback<DeliveryObject>() {

    override fun areItemsTheSame(
        oldItem: DeliveryObject,
        newItem: DeliveryObject
    ): Boolean {
        return oldItem.idDelivery == newItem.idDelivery
    }

    override fun areContentsTheSame(
        oldItem: DeliveryObject,
        newItem: DeliveryObject
    ): Boolean {
        return oldItem.idDelivery == newItem.idDelivery
    }
}