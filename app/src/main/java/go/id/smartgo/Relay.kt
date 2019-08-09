package go.id.smartgo


import com.google.gson.annotations.SerializedName


data class Relay(

    @SerializedName("ID")
    var id: String?= null,
    @SerializedName("R1")
    var R1: String?= null,
    @SerializedName("R2")
    var R2: String? = null

)