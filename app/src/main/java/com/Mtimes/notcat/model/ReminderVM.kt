package com.Mtimes.notcat.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.Mtimes.notcat.data.ReminderRepository
import com.Mtimes.notcat.model.UserVM


data class Reminder(
    val userId: Int,
    val title: String,
    val description: String,
    val date: String,
    val time: Int,
    val repeat: String
)

class ReminderViewModel(
    private val repository: ReminderRepository
) : ViewModel() {

    var user by mutableStateOf<UserVM?>(null)
        private set

    var reminders by mutableStateOf<List<Reminder>>(emptyList())
        private set


    fun loadUser(userId: Int) {
        user = repository.getUserById(userId)
    }


    fun saveReminder(reminder: Reminder) {
        repository.insertReminder(reminder)
    }
}


class ReminderViewModelFactory(
    private val repo: ReminderRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ReminderViewModel(repo) as T
    }
}