package org.example.presenceapp.data.local.storage.attendance

import kotlinx.coroutines.flow.Flow

interface AttendanceStorage {
    suspend fun saveAttendanceMap(map: Map<Int, String>)
    fun attendanceMapFlow(): Flow<Map<Int, String>>
}