package com.bankaccount.bankaccountmanager.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {

    @Query("SELECT * FROM transaction_table")
    fun gettransactionlist(): Flow<List<TransactionModel>>

    @Query("SELECT * FROM transaction_table ORDER BY tranid ASC")
    fun gettranlist(): Flow<List<TransactionModel>>


    @Query("SELECT * FROM transaction_table WHERE id LIKE :name")
    fun findByName(name: String): TransactionModel

    @Query("SELECT * FROM transaction_table WHERE number LIKE :account")
    fun findByaccount(account: String): TransactionModel

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun inserttransaction(word: TransactionModel)

    @Query("DELETE FROM transaction_table")
    fun deletealltransaction()

    @Query("DELETE FROM transaction_table WHERE tranid LIKE :id")
    fun deletetransaction(id: String)
}