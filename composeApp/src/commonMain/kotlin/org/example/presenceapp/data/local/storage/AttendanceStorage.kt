package org.example.presenceapp.data.local.storage

import kotlinx.coroutines.flow.Flow

interface AttendanceStorage {
    suspend fun saveAttendanceMap(map: Map<String, String>)
    fun attendanceMapFlow(): Flow<Map<String, String>>
}