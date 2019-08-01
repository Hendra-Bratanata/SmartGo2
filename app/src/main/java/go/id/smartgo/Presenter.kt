package go.id.smartgo

import com.google.gson.Gson
import go.id.smartgo.ApiRepository.ApiReposirtory
import go.id.smartgo.ApiRepository.PromoApi
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class Presenter(val view: MainView,val gson: Gson,val apiReposirtory: ApiReposirtory){
    fun getMaps(){
        doAsync {
            val data = gson.fromJson(apiReposirtory.doRequest(PromoApi.getDataLokasi()),MAPSResponse::class.java)
            uiThread {
                view.showDataMap(data.Data)
                println("Dalam presenter"+data.Data)
            }
        }
    }
}