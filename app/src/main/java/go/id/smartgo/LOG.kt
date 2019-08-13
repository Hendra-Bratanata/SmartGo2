package go.id.smartgo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import go.id.smartgo.ApiRepository.ApiReposirtory
import kotlinx.android.synthetic.main.activity_log.*
import org.jetbrains.anko.startActivity

class LOG : AppCompatActivity(), MainView {
    override fun showDataMap(listMap: List<MAPS>) {
        list.clear()
        list.addAll(listMap)
        adapterLog.notifyDataSetChanged()
    }

    override fun showData(listMap: List<Relay>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    lateinit var apiReposirtory: ApiReposirtory
    lateinit var gson: Gson
    lateinit var presenter: Presenter
    lateinit var list: MutableList<MAPS>
    lateinit var adapterLog: AdapterLog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log)
        list = mutableListOf()
        adapterLog = AdapterLog(list, {
            startActivity<MapsActivity>("detail" to it, "main" to 2)
        })

        apiReposirtory = ApiReposirtory()
        gson = Gson()
        rvLog.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        rvLog.adapter = adapterLog
        presenter = Presenter(this, gson, apiReposirtory)
        presenter.getMaps()


    }
}
