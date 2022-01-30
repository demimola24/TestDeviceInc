package com.casestudy.testdevices.ui.views.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.casestudy.testdevices.R
import kotlinx.android.synthetic.main.fragment_device_details.*
import kotlinx.android.synthetic.main.item_layout.view.*


class DeviceDetailsFragment : Fragment() {

    private val args by navArgs<DeviceDetailsFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_device_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textViewCost.text = "${args.myDeviceData.currency} ${args.myDeviceData.price}"
        textViewName.text = args.myDeviceData.title
        textViewDesc.text = args.myDeviceData.description


        Glide.with(requireActivity())
            .load(args.myDeviceData.imageUrl)
            .into(imageViewAvatar)

    }

}