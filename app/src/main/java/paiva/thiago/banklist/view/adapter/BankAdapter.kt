package paiva.thiago.banklist.view.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.layout_bank_item.view.*
import paiva.thiago.banklist.R
import paiva.thiago.banklist.model.Bank

class BankAdapter(private val bankList: List<Bank>, private val listener: OnBankClickListener): RecyclerView.Adapter<BankAdapter.BankViewHolder>(), Filterable {

    private var bankListFiltered: MutableList<Bank> = bankList.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BankViewHolder {
        return BankViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_bank_item, parent, false))
    }

    override fun getItemCount(): Int {
        return bankListFiltered.size
    }

    override fun onBindViewHolder(holder: BankViewHolder, position: Int) {
        holder.bind(bankListFiltered[position])
        holder.itemView.setOnClickListener {
            listener.onBankSelected(bankListFiltered[position])
        }
    }

    class BankViewHolder(private val holder: View): RecyclerView.ViewHolder(holder) {

        private val image: AppCompatImageView = holder.bank_item_image
        private val name: AppCompatTextView = holder.bank_item_name

        fun bind(bank: Bank) {
            Picasso.get()
                .load(Uri.parse(bank.image))
                .placeholder(R.drawable.ic_bank_placeholder)
                .error(R.drawable.ic_bank_placeholder)
                .into(image)

            name.text = holder.resources.getString(R.string.bank_name, bank.code, bank.name)
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(query: CharSequence): FilterResults {
                val tempValues = bankList.filter { bank ->  bank.name.contains(query, true) || bank.code.contains(query, true)}
                val results = FilterResults()
                results.values = tempValues.toMutableList()
                return results
            }

            override fun publishResults(query: CharSequence, results: FilterResults) {
                bankListFiltered = results.values as MutableList<Bank>
                notifyDataSetChanged()
            }

        }
    }

    interface OnBankClickListener {
        fun onBankSelected(bank: Bank)
    }
}