package paiva.thiago.banklist.presenter

import paiva.thiago.banklist.model.Bank
import paiva.thiago.banklist.view.BankView

class BankPresenterImpl(private val view: BankView<List<Bank>, String>): BankPresenter<List<Bank>, String> {

    override fun onBankList(bankList: List<Bank>) {
        view.onShowBankList(bankList)
    }

    override fun onError(error: String) {
        view.onError(error)
    }

    override fun onBankSelected(bank: Bank) {
        view.setSelectedBank(bank)
    }
}