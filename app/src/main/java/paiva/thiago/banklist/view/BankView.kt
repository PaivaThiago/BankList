package paiva.thiago.banklist.view

import paiva.thiago.banklist.model.Bank

interface BankView<TResponse,TError> {
    fun onShowBankList(bankList: TResponse)
    fun onError(error: TError)
    fun setSelectedBank(bank: Bank)
}