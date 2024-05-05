package com.jyun_yi.kotlin_demo.ui.fragments.map

import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.jyun_yi.kotlin_demo.R
import com.jyun_yi.kotlin_demo.databinding.FragmentMapBinding
import com.jyun_yi.kotlin_demo.ui.BaseFragment
import com.jyun_yi.kotlin_demo.ui.activitys.main.MainActivity
import com.jyun_yi.kotlin_demo.ui.adapers.MapInfoAdapter
import com.jyun_yi.kotlin_demo.ui.adapers.ParkingSearchAdapter
import com.jyun_yi.kotlin_demo.ui.fragments.search.SearchFragment

class MapFragment : BaseFragment<FragmentMapBinding, MapViewModel>(), OnMapReadyCallback {
    override val layoutId = R.layout.fragment_map

    private lateinit var mapAdapter: MapInfoAdapter
    private lateinit var parkingSearchAdapter: ParkingSearchAdapter

    private var googleMap: GoogleMap? = null
    private val markers = mutableMapOf<String, Marker>()

    override fun initView() {
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        mapAdapter = MapInfoAdapter(requireContext())

        parkingSearchAdapter = ParkingSearchAdapter {
            viewModel.updateSearching()
            moveCamera(it.lat, it.lng)
            markers[it.id]?.showInfoWindow()
        }
        binding.mapSearchList.adapter = parkingSearchAdapter

        binding.mapBtnRefresh.setOnClickListener {
            refreshMap()
        }
        binding.mapSearchEt.addTextChangedListener {
            val text = it.toString()
            if( text.isEmpty() ){
                parkingSearchAdapter.submitList(viewModel.parkingLocations)
            }else{
                val items = viewModel.parkingLocations.filter {
                    it.name.contains(text, true) || it.address.contains(text, true)
                }
                parkingSearchAdapter.submitList(items)
            }
        }
    }

    override fun initViewModel(): MapViewModel {
        val viewModel by viewModels<MapViewModel>()
        binding.viewModel = viewModel
        return viewModel
    }

    override fun initData() {
        refreshMap()
    }

    override fun receiveData(action: String, data: Any?) {

    }

    override fun onMapReady(googleMap: GoogleMap) {
        this.googleMap = googleMap
        moveCamera(25.03648987, 121.5621068)

        googleMap.setInfoWindowAdapter(mapAdapter)
        googleMap.setOnInfoWindowClickListener {
            passData(MainActivity.TAB_CHANGE, null, 1)
            passData(SearchFragment.SHOW_INFO, it.title, 1)
        }
        initMap()
    }

    private fun initMap(){
        if(googleMap == null){
            return
        }
        val items = viewModel.getParkingItems() ?: return
        mapAdapter.items = items
        passData(SearchFragment.UPDATE_PARKING_ITEMS, items, 1)

        parkingSearchAdapter.submitList(viewModel.parkingLocations)
        viewModel.parkingLocations.forEach{
            val location = LatLng(it.lat, it.lng)
            markers[it.id] = googleMap?.addMarker(
                MarkerOptions()
                    .position(location)
                    .title(it.id)
                    .snippet(it.id)
            )!!
        }
    }

    private fun refreshMap(){
        viewModel.clearData()
        markers.clear()
        if(googleMap != null){
            googleMap!!.clear()
        }
        getParkingLot()
        getParkingSpace()
    }

    private fun moveCamera(lat: Double, lng: Double){
        val location = LatLng(lat, lng)
        googleMap?.animateCamera(CameraUpdateFactory.newLatLngZoom(location,18.0f))
    }
    // ------------------------------
    private fun getParkingLot(){
        showLoading()
        viewModel.getParkingLot {
            if(it != null){
                initMap()
            }
            hideLoading()
        }
    }

    private fun getParkingSpace(){
        showLoading()
        viewModel.getParkingSpace {
            if(it != null){
                initMap()
            }
            hideLoading()
        }
    }
}