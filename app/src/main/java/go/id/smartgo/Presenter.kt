package go.id.smartgo

import android.util.Log.println
import com.google.gson.Gson

import go.id.smartgo.ApiRepository.ApiReposirtory
import go.id.smartgo.ApiRepository.PromoApi
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class Presenter(val view: MainView, val gson: Gson, val apiReposirtory: ApiReposirtory){
    fun getMaps(){
        doAsync {
            val data = gson.fromJson(apiReposirtory.doRequest(PromoApi.getDataLokasi()),MAPSResponse::class.java)
            uiThread {
                view.showDataMap(data.Data)
                println(1,"tag","Dalam presenter"+data.Data)
            }
        }
    }
    fun getData(){
        doAsync {
            val data = gson.fromJson(apiReposirtory.doRequest(PromoApi.getDataLokasi()),RelayResponse::class.java)
            uiThread {
                view.showData(data.Data)
                println(1,"tag","Dalam presenter"+data.Data)
            }
        }
    }
}


