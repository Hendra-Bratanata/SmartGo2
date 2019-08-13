package go.id.smartgo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.jetbrains.anko.find

class AdapterLog(val listMAPS: List<MAPS>,val detail:(MAPS)-> Unit) : RecyclerView.Adapter<AdapterLog.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_log, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = listMAPS.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listMAPS[position],detail)
    }


    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val id: TextView = v.find(R.id.logId)
        val waktu: TextView = v.find(R.id.waktu)

        fun bind(Maps:MAPS,detail: (MAPS) -> Unit){
            id.setText("Log id : ${Maps.id}").toString()
            waktu.setText("Waktu : ${Maps.waktu}").toString()
            itemView.setOnClickListener {
                detail(Maps)
            }

        }

    }

}