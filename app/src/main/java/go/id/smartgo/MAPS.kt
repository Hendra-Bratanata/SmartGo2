package go.id.smartgo


import com.google.gson.annotations.SerializedName


data class MAPS(

    @SerializedName("ID")
    var id: String?= null,
    @SerializedName("lat")
    var lat: String?= null,
    @SerializedName("long")
    var lang: String? = null,
    @SerializedName("waktu")
    var waktu: String? = null

)