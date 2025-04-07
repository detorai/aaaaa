package org.example.presenceapp.db

import android.content.Context
import app.cash.sqldelight.driver.android.AndroidSqliteDriver

actual class DatabaseHelper(private val context: Context)  {
    actual val database: ScheduleDatabase by lazy {
        val driver = AndroidSqliteDriver(ScheduleDatabase.Companion.Schema, context, "schedule.db")
        ScheduleDatabase.Companion(driver)
    }
}