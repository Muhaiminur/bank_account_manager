package com.bankaccount.bankaccountmanager.view.viewmodel

import androidx.lifecycle.*
import com.bankaccount.bankaccountmanager.database.AccountRepository
import com.bankaccount.bankaccountmanager.database.BankAccount
import kotlinx.coroutines.launch

class AddcardviewModel (private val repository: AccountRepository) : ViewModel() {

    // Using LiveData and caching what allWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allaccount: LiveData<List<BankAccount>> = repository.allaccount.asLiveData()

    /**
     * Launching a new coroutine to insert the data in a non-blocking way
     */
    fun insert(word: BankAccount) = viewModelScope.launch {
        repository.insertaccount(word)
    }
}
class addcardViewModelFactory(private val repository: AccountRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddcardviewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AddcardviewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}