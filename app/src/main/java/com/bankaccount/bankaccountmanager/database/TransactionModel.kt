package com.bankaccount.bankaccountmanager.database

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transaction_table")
data class TransactionModel(
    val description: String,
    val amount: Long,
    val type: String,
    @Embedded val bankAccount: BankAccount?
) {
    @PrimaryKey(autoGenerate = true)
    var tranid: Int = 0
}
