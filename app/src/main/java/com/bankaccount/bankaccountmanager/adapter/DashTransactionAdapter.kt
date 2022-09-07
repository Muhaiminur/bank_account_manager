package com.bankaccount.bankaccountmanager.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bankaccount.bankaccountmanager.database.TransactionModel
import com.bankaccount.bankaccountmanager.databinding.AdapterDashTransactionBinding
import com.bankaccount.bankaccountmanager.utitlity.Utility


class DashTransactionAdapter(
    private val dataSet: List<TransactionModel>,
    con: Context
) :
    RecyclerView.Adapter<DashTransactionAdapter.ViewHolder>() {
    val context: Context
    val utility: Utility

    init {
        context = con;
        utility = Utility(context)
    }


    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: AdapterDashTransactionBinding) : RecyclerView.ViewHolder(view.root) {
        var binding: AdapterDashTransactionBinding

        init {
            binding = view
        }

        fun bind(s: TransactionModel?) {
            //historyBinding.setHistory(deliveryModel);
            binding.executePendingBindings()
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val binding = AdapterDashTransactionBinding.inflate(
            LayoutInflater.from(viewGroup.context),
            viewGroup,
            false
        )

        return ViewHolder(binding)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        try {
            val model = dataSet[position]
            holder.bind(model)
            holder.binding.dashTransactionName.text = model.description
            holder.binding.dashTransactionAccount.text = model.bankAccount?.number ?: ""
            holder.binding.dashTransactionAmount.text = model.amount.toString()
        } catch (e: Exception) {
            Log.d("Error Line Number", Log.getStackTraceString(e))
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}