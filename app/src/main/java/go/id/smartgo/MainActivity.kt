package go.id.smartgo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import go.id.smartgo.ApiRepository.ApiReposirtory
import go.id.smartgo.ApiRepository.PromoApi
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.delay
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {
    lateinit var apiReposirtory: ApiReposirtory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        apiReposirtory = ApiReposirtory()

        btnRemote.setOnClickListener {
            btnRemote.background = ContextCompat.getDrawable(this,R.drawable.ic_settings_remote_gray)
           Thread(Runnable {
               run {
                   Thread.sleep(300)
                   btnRemote.background = ContextCompat.getDrawable(this,R.drawable.ic_settings_remote_blue)
               }
           }).start()

        }
        btnGps.setOnClickListener {
            startActivity<MapsActivity>()
        }


//        btnON.setOnClickListener {
//            doAsync {
//                apiReposirtory.doRequest(PromoApi.setData(btnON.text.toString()))
//            }
//
//        }
//        btnOFF.setOnClickListener {
//            doAsync {
//                apiReposirtory.doRequest(PromoApi.setData(btnOFF.text.toString()))
//            }
//
//        }
//        btnMap.setOnClickListener {
//            startActivity<MapsActivity>()
//        }
    }
}
