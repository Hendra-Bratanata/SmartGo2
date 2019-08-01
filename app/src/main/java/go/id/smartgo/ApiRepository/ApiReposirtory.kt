package go.id.smartgo.ApiRepository

import android.util.Log
import java.net.URL

class ApiReposirtory{
    fun doRequest(url: String):String{
        val data = URL(url).readText()
        Log.d("Data",data)
        return data
    }
}