package go.id.smartgo


import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class ALAT(

    @SerializedName("ID")
    var id: String?= null,
    @SerializedName("Hash")
    var hash: String?= null,
    @SerializedName("Type")
    var type: String? = null

):Serializable