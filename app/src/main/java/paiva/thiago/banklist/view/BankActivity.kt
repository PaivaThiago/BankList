package paiva.thiago.banklist.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_bank.*
import paiva.thiago.banklist.R
import paiva.thiago.banklist.interactor.BankInteractor
import paiva.thiago.banklist.interactor.BankInteractorImpl
import paiva.thiago.banklist.model.Bank
import paiva.thiago.banklist.presenter.BankPresenterImpl
import paiva.thiago.banklist.repository.BankRepository
import paiva.thiago.banklist.view.adapter.BankAdapter
import timber.log.Timber

class BankActivity : AppCompatActivity(), BankView<List<Bank>, String>, BankAdapter.OnBankClickListener {

    private lateinit var interactor: BankInteractor
    private lateinit var bankAdapter: BankAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bank)
        startEvents()
    }

    private fun startEvents() {
        interactor = BankInteractorImpl(BankRepository(), BankPresenterImpl(this))
        interactor.getBankList()
    }

    override fun onShowBankList(bankList: List<Bank>) {
        bankAdapter = BankAdapter(bankList, this)

        setSearchView(bankAdapter, null)

        bank_list_recycler_view.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(this.context, LinearLayoutManager.VERTICAL))
            adapter = bankAdapter
        }
    }

    private fun setSearchView(bankAdapter: BankAdapter, selectedBank: Bank?) {
        bank_list_search_view.apply {
            setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    bankAdapter.filter.filter(query)
                    bank_list_recycler_view.visibility = View.VISIBLE
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    bankAdapter.filter.filter(query)
                    bank_list_recycler_view.visibility = View.VISIBLE
                    return true
                }

            })

            if(selectedBank != null){
                setQuery(getString(R.string.bank_name, selectedBank.code, selectedBank.name), true)
                bank_list_recycler_view.visibility = View.GONE
            }
        }
    }

    override fun onBankSelected(bank: Bank) {
        interactor.onBankSelected(bank)
    }

    override fun setSelectedBank(bank: Bank) {
        setSearchView(bankAdapter, bank)
    }

    override fun onError(error: String) {
        Timber.tag(localClassName).d(error)
    }

}
