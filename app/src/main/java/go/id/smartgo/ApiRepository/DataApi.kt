package go.id.smartgo.ApiRepository

import android.util.Log
import go.id.smartgo.BuildConfig

object DataApi {
    fun setDataR1(r1:String):String{
        val url = BuildConfig.BASE_URL +"setData.php?R1=$r1"
        Log.d("data", url)
        return url
    }
    fun setDataR2(r1:String):String{
        val url = BuildConfig.BASE_URL +"setData.php?R2=$r1"
        Log.d("data", url)
        return url
    }
    fun getDataLokasi():String{
        val url = BuildConfig.BASE_URL +"getDataLatLong.php"
        Log.d("data", url)
        return url
    }
    fun getDataLokasiId(id:String):String{
        val url = BuildConfig.BASE_URL +"getLatLongId.php?id=$id"
        Log.d("data", url)
        return url
    }
    fun getData():String{
        val url = BuildConfig.BASE_URL +"getData.php"
        Log.d("data", url)
        return url
    }

    fun getAlatHash():String{
        val url = BuildConfig.BASE_URL +"getDataHash.php"
        Log.d("data", url)
        return url
    }

}