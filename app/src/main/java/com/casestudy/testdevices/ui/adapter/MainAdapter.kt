package com.casestudy.testdevices.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.casestudy.data.model.MyDevice
import com.casestudy.testdevices.R
import kotlinx.android.synthetic.main.item_layout.view.*

class MainAdapter(
    private val myDevices: ArrayList<MyDevice>, private val delegate: OnItemClickListener
) : RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(myDevice: MyDevice,delegate: OnItemClickListener) {
            itemView.textViewName.text = myDevice.title
            itemView.textViewDesc.text = myDevice.description
            itemView.textViewCost.text = "${myDevice.currency} ${myDevice.price}"
            Glide.with(itemView.imageViewAvatar.context)
                .load(myDevice.imageUrl)
                .into(itemView.imageViewAvatar)

            itemView.setOnClickListener {
                delegate.onMyDeviceClicked(myDevice)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_layout, parent,
                false
            )
        )

    override fun getItemCount(): Int = myDevices.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(myDevices[position],delegate)

    fun addData(list: List<MyDevice>) {
        myDevices.clear()
        myDevices.addAll(list)
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onMyDeviceClicked(data: MyDevice)
    }
}