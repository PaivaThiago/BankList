package paiva.thiago.banklist.interactor

import paiva.thiago.banklist.model.Bank
import paiva.thiago.banklist.presenter.BankPresenter
import paiva.thiago.banklist.repository.BankRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class BankInteractorImpl(private val repository: BankRepository, private val presenter: BankPresenter<List<Bank>, String>) : BankInteractor {

    override fun getBankList() {
        repository.bankList().list().enqueue(object : Callback<List<Bank>> {
            override fun onResponse(call: Call<List<Bank>>, response: Response<List<Bank>>) {
                response.body()?.let { list ->
                    presenter.onBankList(list)
                } ?: presenter.onError("Null List Error")
            }

            override fun onFailure(call: Call<List<Bank>>, t: Throwable) {
                Timber.d(t)
                presenter.onError("Service Error")
            }
        })
    }

    override fun onBankSelected(bank: Bank) {
        presenter.onBankSelected(bank)
    }
}