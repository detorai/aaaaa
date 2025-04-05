package org.example.presenceapp.data.repository

import kotlinx.coroutines.flow.Flow
import org.example.presenceapp.data.local.LocalDataSource
import org.example.presenceapp.data.remote.RemoteDataSource
import org.example.presenceapp.domain.models.Attendance

class AttendanceRepository(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) {
    suspend fun saveAttendanceLocally(map: Map<String, String>) {
        localDataSource.saveAttendance(map)
    }

    fun observeLocalAttendance(): Flow<Map<String, String>> {
        return localDataSource.observeAttendance()
    }

    suspend fun getWeeklyAttendance(groupId: Int): Map<String, List<Attendance>> {
        return remoteDataSource.getWeeklyAttendance(groupId)
    }

    suspend fun syncWithServer() {

    }
}