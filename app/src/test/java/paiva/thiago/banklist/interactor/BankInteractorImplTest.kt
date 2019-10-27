package paiva.thiago.banklist.interactor

import io.mockk.mockk
import io.mockk.verifyAll
import org.junit.Before
import org.junit.Test
import paiva.thiago.banklist.model.Bank
import paiva.thiago.banklist.presenter.BankPresenter
import paiva.thiago.banklist.presenter.BankPresenterImpl
import paiva.thiago.banklist.repository.BankRepository


class BankInteractorImplTest {

    private lateinit var interactor: BankInteractor
    private lateinit var repository: BankRepository
    private lateinit var presenter: BankPresenter<List<Bank>, String>
    private lateinit var bank: Bank

    @Before
    fun setUp() {
        repository = mockk(relaxed = true)
        presenter = mockk<BankPresenterImpl>(relaxed = true)
        bank = mockk(relaxed = true)
        interactor = BankInteractorImpl(repository, presenter)
    }

    @Test
    fun onBankSelected() {
        interactor.onBankSelected(bank)

        verifyAll {
            presenter.onBankSelected(bank)
        }
    }
}