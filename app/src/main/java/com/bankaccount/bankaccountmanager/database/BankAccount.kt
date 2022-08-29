package com.bankaccount.bankaccountmanager.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "account_table")
data class BankAccount(
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "number") val number: String?,
    @ColumnInfo(name = "amount") val amount: String?,){
    @PrimaryKey(autoGenerate = true) var id: Int=0
}
