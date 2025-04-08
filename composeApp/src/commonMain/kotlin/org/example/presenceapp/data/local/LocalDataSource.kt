package org.example.presenceapp.data.local

import kotlinx.coroutines.flow.Flow
import org.example.presenceapp.data.local.storage.attendance.AttendanceStorage

class LocalDataSource(private val attendanceStorage: AttendanceStorage) {

    suspend fun saveAttendance(map: Map<Int, String>) {
        attendanceStorage.saveAttendanceMap(map)
    }

    fun observeAttendance(): Flow<Map<Int, String>> {
        return attendanceStorage.attendanceMapFlow()
    }
}