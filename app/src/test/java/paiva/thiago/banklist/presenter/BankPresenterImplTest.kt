package paiva.thiago.banklist.presenter

import io.mockk.mockk
import io.mockk.verifyAll
import org.junit.Before
import org.junit.Test
import paiva.thiago.banklist.model.Bank
import paiva.thiago.banklist.view.BankView

class BankPresenterImplTest {

    private lateinit var presenter : BankPresenter<List<Bank>, String>
    private lateinit var view: BankView<List<Bank>, String>
    private lateinit var bank: Bank
    private lateinit var bankList: List<Bank>

    @Before
    fun setUp() {
        view = mockk(relaxed = true)
        bank = mockk(relaxed = true)
        bankList = mockk(relaxed = true)
        presenter = BankPresenterImpl(view)
    }

    @Test
    fun onBankList() {
        presenter.onBankSelected(bank)

        verifyAll {
            view.setSelectedBank(bank)
        }
    }

    @Test
    fun onError() {
        presenter.onBankList(bankList)

        verifyAll {
            view.onShowBankList(bankList)
        }
    }

    @Test
    fun onBankSelected() {
        presenter.onError("Test")

        verifyAll {
            view.onError("Test")
        }
    }
}