package com.bankaccount.bankaccountmanager.view.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bankaccount.bankaccountmanager.adapter.DashTransactionAdapter
import com.bankaccount.bankaccountmanager.database.BankAccount
import com.bankaccount.bankaccountmanager.database.TransactionModel
import com.bankaccount.bankaccountmanager.databinding.FragmentDashpageBinding
import com.bankaccount.bankaccountmanager.utitlity.Utility

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class DashPage : Fragment() {

    lateinit var utility: Utility
    lateinit var cont: Context
    lateinit var adapter: DashTransactionAdapter
    lateinit var list : ArrayList<TransactionModel>

    private var _binding: FragmentDashpageBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDashpageBinding.inflate(inflater, container, false)
        cont = requireContext()
        utility = Utility(cont)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try {
            recyler()

        } catch (e: Exception) {
            Log.d("Error Line Number", Log.getStackTraceString(e))
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun recyler(){
        // create  layoutManager
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(cont)

        // pass it to rvLists layoutManager
        binding.dashTransaction.setLayoutManager(layoutManager)

        // initialize the adapter,
        // and pass the required argument
        list=ArrayList()
        list.add(TransactionModel("abir",500,"Credit", BankAccount("Abir2","12301019","500")))
        adapter = DashTransactionAdapter(list,cont)
        binding.dashTransaction.adapter=adapter;

        // attach adapter to the recycler view
    }
}