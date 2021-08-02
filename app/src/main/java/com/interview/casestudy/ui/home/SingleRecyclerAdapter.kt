package com.interview.casestudy.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.interview.casestudy.BR

class SingleRecyclerAdapter<LISTENER, DATA>(
    private val singleLayout: Int,
    private val clickListener: LISTENER?= null
) : RecyclerView.Adapter<SingleRecyclerAdapter<LISTENER, DATA>.ViewHolder>() {

    private var list: MutableList<DATA> = mutableListOf()
    private var data: DATA? = null
    private lateinit var listener: (DATA) -> Unit

    @NonNull
    override fun onCreateViewHolder(@NonNull viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(viewGroup.context)
        return ViewHolder(
            DataBindingUtil.inflate(
                layoutInflater,
                singleLayout,
                viewGroup,
                false
            ), clickListener
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (data == null) {
            holder.setData(list[position])
        } else {
            holder.setData(data!!)
        }
    }

    override fun getItemCount(): Int = if (data == null) {
        list.size
    } else {
        1
    }

    fun setData(dataList: MutableList<DATA>?) {
        if (dataList == null) return
        list.clear()
        list.addAll(dataList)
        notifyDataSetChanged()
    }

    fun setData(data: DATA) {
        this.data = data
        //notifyItemInserted(0)
    }

    fun itemClickListener(listener: (DATA) -> Unit) {
        this.listener = listener
    }

    inner class ViewHolder(val binding: ViewDataBinding, private val clickListener: LISTENER?) :
        RecyclerView.ViewHolder(binding.root) {

        fun setData(itemData: DATA) {
            binding.setVariable(BR.item, itemData)
            //if (clickListener != null) binding.setVariable(BR.clickListener, clickListener)
            if (::listener.isInitialized) {
                binding.root.setOnClickListener {
                    listener.invoke(itemData)
                }
            }

        }
    }
}