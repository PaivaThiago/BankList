package paiva.thiago.banklist.interactor

import paiva.thiago.banklist.model.Bank

interface BankInteractor {
    fun getBankList()
    fun onBankSelected(bank: Bank)
}