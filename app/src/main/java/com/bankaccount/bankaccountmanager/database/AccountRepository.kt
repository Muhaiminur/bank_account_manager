package com.bankaccount.bankaccountmanager.database

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow

class AccountRepository(private val wordDao: AccountDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allaccount: Flow<List<BankAccount>> = wordDao.getaccountlist()

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertaccount(account: BankAccount) {
        wordDao.insertaccount(account)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    fun findnameaccount(name: String) {
        wordDao.findByName(name)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    fun findaccountaccount(account: String) {
        wordDao.findByaccount(account)
    }
}