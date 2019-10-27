package paiva.thiago.banklist.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Bank(
    val name: String,
    val code: String,
    val favorite: Boolean,
    val image: String = ""
)