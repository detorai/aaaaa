package org.example.presenceapp.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.example.presenceapp.data.local.LocalDataSource
import org.example.presenceapp.data.remote.impl.AttendanceApiImpl
import org.example.presenceapp.domain.models.AttendanceType
import org.example.presenceapp.domain.models.ResponseState

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

    fun getAttendanceType(): Flow<ResponseState> = flow {
        return@flow try {
            val result = attendanceApiImpl.getAttendanceType().map {
                AttendanceType(
                    id = it.id,
                    name = it.name
                )
            }
            emit(ResponseState.Success(result))
        } catch (e: Exception) {
            emit (ResponseState.Error(e.message.toString()))
        }
    }
}