package go.id.smartgo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.gson.Gson
import go.id.smartgo.ApiRepository.ApiReposirtory
import go.id.smartgo.ApiRepository.PromoApi
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.delay
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity(),MainView {
    override fun showData(listMap: List<Relay>) {
        //isi varible R dengan object dari server
         val R = listMap[0]
        //memastikan object R1 tidak kosong
        if(!R.R1.isNullOrEmpty()){
            //jika Object R1 sama dengan "OFF" bagroud berubah jadi lock
            if(R.R1!!.equals("OFF")){
                btnLock.background = ContextCompat.getDrawable(this, go.id.smartgo.R.drawable.ic_lock_blue)
                tvLock.setText("Lock")
                state = true
            }
            //jika Object R1 sama dengan "ON" bagroud berubah jadi unlock
            else if(R.R1!!.equals("ON")){
                btnLock.background = ContextCompat.getDrawable(this, go.id.smartgo.R.drawable.ic_lock_open_blue)
                tvLock.setText("Unlock")

                state = false
            }
        }



    }

    override fun showDataMap(listMap: List<MAPS>) {

    }

    lateinit var apiReposirtory: ApiReposirtory
    lateinit var gson: Gson
    lateinit var presenter: Presenter
    lateinit var listData: MutableList<Relay>
    var state = true





    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        apiReposirtory = ApiReposirtory()
        gson = Gson()
        listData = mutableListOf()
        presenter = Presenter(this,gson,apiReposirtory)

        presenter.getData()


        //saat tombol remot ditekan icon akan menjadi abu2 selama 3 detik kemudian kembali lagi menanjadi biru
        // data R2 pada database di set menjadi ON
        // dan remote tidak bisa diklik selama 3 detik
        btnRemote.setOnClickListener {
            btnRemote.background = ContextCompat.getDrawable(this,R.drawable.ic_settings_remote_gray)
            btnRemote.isClickable = false
           Thread(Runnable {
               Thread.sleep(3000)
               btnRemote.background = ContextCompat.getDrawable(this,R.drawable.ic_settings_remote_blue)
               btnRemote.isClickable = true
           }).start()
            doAsync {
                apiReposirtory.doRequest(PromoApi.setDataR2("ON"))
            }

        }

        //saat tombol lock diklik maka logo akan berubah dan text menjadi unlock

        btnLock.setOnClickListener {
            if(state){
                btnLock.background = ContextCompat.getDrawable(this,R.drawable.ic_lock_open_blue)
                tvLock.setText("Unlock")
                doAsync {
                    apiReposirtory.doRequest(PromoApi.setDataR1("ON"))
                }
                state = false
            }
            else if (!state){
                btnLock.background = ContextCompat.getDrawable(this,R.drawable.ic_lock_blue)
                tvLock.setText("Lock")
                doAsync {
                    apiReposirtory.doRequest(PromoApi.setDataR1("OFF"))
                }
                state = true
            }

        }

        //saat tombol GPS ditekan akan diahlikan ke activity map
        btnGps.setOnClickListener {
            startActivity<MapsActivity>()
        }

    }
}
