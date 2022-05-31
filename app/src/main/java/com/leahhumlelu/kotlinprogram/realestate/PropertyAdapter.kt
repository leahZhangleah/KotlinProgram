package com.leahhumlelu.kotlinprogram.realestate

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.leahhumlelu.kotlinprogram.R
import com.leahhumlelu.kotlinprogram.databinding.RealEstateGridItemViewBinding
import com.leahhumlelu.kotlinprogram.generated.callback.OnClickListener

class PropertyAdapter(private val listener: PropertyClickListener):ListAdapter<MarsProperty, PropertyAdapter.PropertyViewHolder>(PropertyDiffCallback) {
    companion object PropertyDiffCallback: DiffUtil.ItemCallback<MarsProperty>() {
        override fun areItemsTheSame(oldItem: MarsProperty, newItem: MarsProperty): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: MarsProperty, newItem: MarsProperty): Boolean {
            return oldItem.id==newItem.id
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PropertyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding:RealEstateGridItemViewBinding = RealEstateGridItemViewBinding.inflate(layoutInflater)
        return PropertyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PropertyViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item,listener)
    }

    class PropertyViewHolder(val binding: RealEstateGridItemViewBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(item: MarsProperty,listener: PropertyClickListener) {
            binding.marsProperty = item
            binding.listener = listener
            binding.executePendingBindings()
        }
    }
}

class PropertyClickListener(private val click:(MarsProperty)->Unit) {
    fun onClick(marsProperty: MarsProperty) = click(marsProperty)

}



