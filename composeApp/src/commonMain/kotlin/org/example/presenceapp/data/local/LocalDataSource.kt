package org.example.presenceapp.data.local

import kotlinx.coroutines.flow.Flow
import org.example.presenceapp.data.local.storage.AttendanceStorage

class LocalDataSource(private val attendanceStorage: AttendanceStorage) {

    suspend fun saveAttendance(map: Map<String, String>) {
        attendanceStorage.saveAttendanceMap(map)
    }

    fun observeAttendance(): Flow<Map<String, String>> {
        return attendanceStorage.attendanceMapFlow()
    }
}