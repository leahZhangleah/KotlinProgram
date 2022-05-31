package com.leahhumlelu.kotlinprogram.sleep

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.leahhumlelu.kotlinprogram.R
import com.leahhumlelu.kotlinprogram.databinding.SleepDataRvItemBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

private val ITEM_VIEW_TYPE_HEADER = 0
private val ITEM_VIEW_TYPE_ITEM = 1

class SleepDataAdapter(val listener:SleepNightClickListener):
    ListAdapter<DataItem, RecyclerView.ViewHolder>(SleepNightDiffCallback()) {
    class ViewHolder private constructor(val binding:SleepDataRvItemBinding):RecyclerView.ViewHolder(binding.root){
        companion object{
            fun from(parent: ViewGroup):ViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = SleepDataRvItemBinding.inflate(layoutInflater,parent,false)
                return ViewHolder(binding)
            }
        }

        fun bind(item:SleepNight,listener: SleepNightClickListener){
            binding.sleepNight = item
            binding.sleepListener = listener
            binding.executePendingBindings()
        }
    }

    class TextViewHolder (view:View):RecyclerView.ViewHolder(view){
        companion object{
            fun from(parent:ViewGroup):TextViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater.inflate(R.layout.header,parent,false)
                return TextViewHolder(view)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            ITEM_VIEW_TYPE_HEADER->TextViewHolder.from(parent)
            ITEM_VIEW_TYPE_ITEM->ViewHolder.from(parent)
            else-> throw ClassCastException("Unknown viewType ${viewType}")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is ViewHolder->{
                val item = getItem(position) as DataItem.SleepNightItem
                holder.bind(item.night,listener)
            }
        }

    }

    override fun getItemViewType(position: Int): Int {
        return when(getItem(position)){
            is DataItem.Header-> ITEM_VIEW_TYPE_HEADER
            is DataItem.SleepNightItem-> ITEM_VIEW_TYPE_ITEM
        }
    }

    private val adapterScope = CoroutineScope(Dispatchers.Default)
    fun addHeaderAndSubmitList(list:List<SleepNight>?){
        adapterScope.launch {
            val items = when(list){
                null-> listOf(DataItem.Header)
                else-> listOf(DataItem.Header) + list.map{
                    DataItem.SleepNightItem(it)
                }
            }
            withContext(Dispatchers.Main){
                submitList(items)
            }
        }
    }

}

class SleepNightDiffCallback:DiffUtil.ItemCallback<DataItem>(){
    override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        return oldItem.id==newItem.id
    }

    override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
        return oldItem==newItem
    }

}

class SleepNightClickListener(val click:(nightId:Long)->Unit){
    fun onClick(night:SleepNight) = click(night.nightId)
}

sealed class DataItem{
    abstract val id:Long
    data class SleepNightItem(val night: SleepNight):DataItem(){
        override val id: Long
            get() = night.nightId

    }

    object Header:DataItem(){
        override val id: Long
            get() = Long.MIN_VALUE

    }
}
























