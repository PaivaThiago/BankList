package paiva.thiago.banklist.repository

import paiva.thiago.banklist.model.Bank
import retrofit2.Call
import retrofit2.http.GET

interface BankList {
    @GET("list")
    fun list(): Call<List<Bank>>
}