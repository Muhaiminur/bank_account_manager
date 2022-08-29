package com.bankaccount.bankaccountmanager.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountDao {

    @Query("SELECT * FROM account_table")
    fun getAllaccountlist(): Flow<List<BankAccount>>

    @Query("SELECT * FROM account_table ORDER BY number ASC")
    fun getaccountlist(): Flow<List<BankAccount>>


    @Query("SELECT * FROM account_table WHERE name LIKE :name")
    fun findByName(name: String): BankAccount

    @Query("SELECT * FROM account_table WHERE number LIKE :account")
    fun findByaccount(account: String): BankAccount

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertaccount(word: BankAccount)

    @Query("DELETE FROM account_table")
    fun deleteAllaccount()

    @Query("DELETE FROM account_table WHERE number LIKE :account")
    fun deleteaccount(account: String)
}