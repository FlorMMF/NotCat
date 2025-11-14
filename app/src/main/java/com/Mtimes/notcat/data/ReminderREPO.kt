package com.Mtimes.notcat.data

import com.Mtimes.notcat.model.Reminder
import com.Mtimes.notcat.model.UserVM

class ReminderRepository(private val dbHelper: UserDB) {

    fun getUserById(id: Int): UserVM? {
        return dbHelper.getUserById(id)
    }

    fun insertReminder(reminder: Reminder): Long {
        return dbHelper.addRemind( getUserById(reminder.userId).toString(),reminder.title, reminder.description, reminder.date, reminder.time, reminder.repeat)
    }

}