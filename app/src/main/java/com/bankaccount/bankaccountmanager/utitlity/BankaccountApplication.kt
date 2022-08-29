package com.bankaccount.bankaccountmanager.utitlity

import android.app.Application
import com.bankaccount.bankaccountmanager.database.AccountDatabase
import com.bankaccount.bankaccountmanager.database.AccountRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class BankaccountApplication : Application() {
    // No need to cancel this scope as it'll be torn down with the process
    val applicationScope = CoroutineScope(SupervisorJob())
    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { AccountDatabase.getDatabase(this,applicationScope) }
    val repository by lazy { AccountRepository(database.accountdao()) }
}