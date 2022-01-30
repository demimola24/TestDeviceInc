package com.casestudy.testdevices.ui.views.home

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.devices_fragment.*

import android.view.MenuInflater
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.casestudy.data.model.MyDevice
import com.casestudy.domain.Status
import com.casestudy.testdevices.R
import com.casestudy.testdevices.ui.adapter.MainAdapter
import java.util.*


@AndroidEntryPoint
class HomeFragment : Fragment(), MainAdapter.OnItemClickListener {

    private val mainViewModel : HomeViewModel by viewModels()
    private val deviceList = mutableListOf<MyDevice>()

    private lateinit var adapter: MainAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        setupObserver()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_devices, menu)
        val searchView = menu.findItem(R.id.search_view).actionView as SearchView
        searchView.maxWidth = Int.MAX_VALUE
    }


    override fun onPrepareOptionsMenu(menu: Menu) {
        val mSearchMenuItem = menu.findItem(R.id.search_view)
        val searchView: SearchView = mSearchMenuItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String): Boolean {
                filterList(newText)
                return false
            }

            override fun onQueryTextSubmit(query: String): Boolean {
                filterList(query)
                return false
            }

        })
    }


    private fun filterList(newText: String){
        if(newText.isNullOrEmpty()){
            renderList(deviceList)
        }else{
            val newItems  = deviceList.filter { it.title.lowercase(Locale.getDefault()).contains(newText.lowercase(Locale.getDefault())) }
            renderList(newItems)
        }

    }


    private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = MainAdapter(arrayListOf(), this@HomeFragment)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
    }

    private fun setupObserver() {
        mainViewModel.devices.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    it.data?.let{ devices ->
                        deviceList.clear()
                        deviceList.addAll(devices)
                        renderList(devices)
                    }
                    recyclerView.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                }
                Status.ERROR -> {
                    progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }
            }
        })
    }

    private fun renderList(myDevices: List<MyDevice>) {
        adapter.addData(myDevices)
    }

    override fun onMyDeviceClicked(data: MyDevice) {
        findNavController().navigate(
            R.id.deviceDetailsFragment, bundleOf(
                "myDeviceData" to data
            )
        )
    }

}