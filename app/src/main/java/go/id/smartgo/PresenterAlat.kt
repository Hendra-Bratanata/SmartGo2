package go.id.smartgo

import android.util.Log.println
import com.google.gson.Gson

import go.id.smartgo.ApiRepository.ApiReposirtory
import go.id.smartgo.ApiRepository.DataApi
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class PresenterAlat(val view: AlatView, val gson: Gson, val apiReposirtory: ApiReposirtory){
    fun getAlat(){
        doAsync {
            val data = gson.fromJson(apiReposirtory.doRequest(DataApi.getAlatHash()),AlatResponse::class.java)
            uiThread {
                view.showData(data.Data)
                println(1,"tag","Dalam presenter"+data.Data)
            }
        }
    }
}


