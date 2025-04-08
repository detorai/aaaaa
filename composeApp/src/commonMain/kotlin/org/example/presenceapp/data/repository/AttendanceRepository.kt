package org.example.presenceapp.data.repository

import kotlinx.coroutines.flow.Flow
import org.example.presenceapp.data.local.LocalDataSource
import org.example.presenceapp.data.remote.AttendanceApiImpl
import org.example.presenceapp.domain.models.Attendance

class AttendanceRepository(
    private val localDataSource: LocalDataSource,
    private val attendanceApiImpl: AttendanceApiImpl
) {
    suspend fun saveAttendanceLocally(map: Map<Int, String>) {
        localDataSource.saveAttendance(map)
    }

    fun observeLocalAttendance(): Flow<Map<Int, String>> {
        return localDataSource.observeAttendance()
    }

//    suspend fun getWeeklyAttendance(groupId: Int): Map<String, List<Attendance>> {
//        return attendanceApiImpl.getWeeklyAttendance(groupId)
//    }

    suspend fun syncWithServer() {

    }
}