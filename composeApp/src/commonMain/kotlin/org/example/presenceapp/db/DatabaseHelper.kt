package org.example.presenceapp.db

import org.example.presenceapp.ScheduleDatabase

expect class DatabaseHelper {
    val database: ScheduleDatabase
}