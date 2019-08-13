package go.id.smartgo

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import com.google.zxing.Result
import go.id.smartgo.ApiRepository.ApiReposirtory
import me.dm7.barcodescanner.zxing.ZXingScannerView
import org.jetbrains.anko.startActivity

class ScanActivity : AppCompatActivity(), ZXingScannerView.ResultHandler, AlatView {
    lateinit var gson: Gson
    lateinit var apiReposirtory: ApiReposirtory
    lateinit var presenter: PresenterAlat
    lateinit var list: MutableList<ALAT>
    lateinit var alat: ALAT
    lateinit var pref : SharedPreference
    var kode = ""


    override fun showData(listAlat: List<ALAT>) {
        for (i in listAlat.indices) {
            alat = listAlat[i]

            if (alat.hash.equals(kode)) {
                startActivity<MainActivity>()
                finish()
            } else {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Scan Result")
                builder.setMessage("Maaf Barcode Salah /Tidak Terdaftar")
                val alert1 = builder.create()
                alert1.show()
            }
        }
    }

    override fun handleResult(p0: Result?) {

        Log.v("TAG", p0?.getText())
        Log.v("TAG", p0?.getBarcodeFormat().toString())
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Scan Result")
        builder.setMessage(p0?.text.toString())
        val alert1 = builder.create()
//        alert1.show()
        kode = p0?.text.toString()
        pref.save("hash",p0?.text.toString())
        presenter.getAlat()
        mScannerView.resumeCameraPreview(this)
    }

    lateinit var mScannerView: ZXingScannerView




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pref = SharedPreference(this)
        if(!pref.getValueString("hash").isNullOrEmpty()){
            startActivity<MainActivity>()
        }
        mScannerView = ZXingScannerView(this)
        setContentView(mScannerView)

        gson = Gson()
        apiReposirtory = ApiReposirtory()
        list = mutableListOf()
        presenter = PresenterAlat(this, gson, apiReposirtory)


    }

    override fun onResume() {
        super.onResume()
        mScannerView.setResultHandler(this)
        mScannerView.startCamera()
    }

    override fun onPause() {
        super.onPause()
        mScannerView.stopCamera()
    }
}
