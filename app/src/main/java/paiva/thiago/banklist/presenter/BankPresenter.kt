package paiva.thiago.banklist.presenter

import paiva.thiago.banklist.model.Bank

interface BankPresenter<TResponse, TError> {
    fun onBankList(bankList: TResponse)
    fun onError(error: TError)
    fun onBankSelected(bank: Bank)
}