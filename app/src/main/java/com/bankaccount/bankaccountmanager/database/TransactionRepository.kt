package com.bankaccount.bankaccountmanager.database

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class TransactionRepository(private val transactionDao: TransactionDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val alltransaction: Flow<List<TransactionModel>> = transactionDao.gettransactionlist()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun inserttransaction(tran: TransactionModel) {
        transactionDao.inserttransaction(tran)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    fun findaccount(account: String) {
        transactionDao.findByaccount(account)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    fun deletetransaction(account: String) {
        transactionDao.deletetransaction(account)
    }
}