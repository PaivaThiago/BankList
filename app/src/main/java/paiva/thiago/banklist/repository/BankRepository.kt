package paiva.thiago.banklist.repository

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class BankRepository {

    companion object {
        private const val BASE_URL: String = "https://private-anon-aa6548d108-tqiandroid.apiary-mock.com/"
        val retrofit: Retrofit = Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(
            MoshiConverterFactory.create()).build()
    }

    fun bankList() : BankList = retrofit.create(BankList::class.java)
}