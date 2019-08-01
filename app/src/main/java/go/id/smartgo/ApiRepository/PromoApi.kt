package go.id.smartgo.ApiRepository

import android.util.Log
import go.id.smartgo.BuildConfig

object PromoApi {
    fun setData(r1:String):String{
        val url = BuildConfig.BASE_URL +"setData.php?R1=$r1"
        Log.d("data", url)
        return url
    }
    fun getDataLokasi():String{
        val url = BuildConfig.BASE_URL +"getDataLatLong.php"
        Log.d("data", url)
        return url
    }
}