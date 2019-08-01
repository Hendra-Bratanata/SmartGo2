package go.id.smartgo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.gson.Gson
import go.id.smartgo.ApiRepository.ApiReposirtory
import kotlinx.android.synthetic.main.activity_maps.*

class MapsActivity : AppCompatActivity(), OnMapReadyCallback,MainView {
    override fun showDataMap(listMap: List<MAPS>) {
        list.clear()
        mMap.clear()
        list.addAll(listMap)
        val markerOptions = MarkerOptions()

        if(riwayat == 1){

            for (i in list.indices){
                val map = list[i]
                lat = map.lat!!.toDouble()
                lang = map.lang!!.toDouble()
                latLang = LatLng(lat,lang)


                markerOptions.position(latLang)
                markerOptions.title(map.id)
                markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))

                if (i == listMap.size-1){
                    markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED))
                }

                print(map.id)

                marker = mMap.addMarker(markerOptions)
                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLang))
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLang,13F))
            }

        }
        if(riwayat == 0){

            val maps = listMap[listMap.size-1]
            lat = maps.lat!!.toDouble()
            lang = maps.lang!!.toDouble()
            latLang = LatLng(lat,lang)
            markerOptions.position(latLang)
            markerOptions.title("Lokasi Terakhir")
            marker = mMap.addMarker(markerOptions)
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLang))
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLang,18F))
        }




    }
    var marker : Marker?= null
    lateinit var apiReposirtory: ApiReposirtory
    lateinit var list:MutableList<MAPS>
    lateinit var gson: Gson
    lateinit var presenter: Presenter
    lateinit var latLang:LatLng
    var lat = 0.0
    var lang = 0.0
    var riwayat = 0

    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        apiReposirtory = ApiReposirtory()
        gson = Gson()
        list = mutableListOf()
        presenter = Presenter(this,gson,apiReposirtory)




        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = this.supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        btnLokasi.setOnClickListener {
            riwayat = 0
           presenter.getMaps()
        }
        btnLokasiR.setOnClickListener {
            riwayat = 1
            presenter.getMaps()


        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        presenter.getMaps()
        Log.d("Tag","onMapReady")
        mMap = googleMap
    }

}
