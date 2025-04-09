package org.example.presenceapp.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.example.presenceapp.data.remote.impl.ScheduleApiImpl
import org.example.presenceapp.domain.models.Attendance
import org.example.presenceapp.domain.models.ResponseState
import org.example.presenceapp.domain.someData.Schedule
import org.example.presenceapp.domain.someData.Student
import org.example.presenceapp.domain.someData.Subject

class ScheduleRepository(
    private val scheduleApiImpl: ScheduleApiImpl,
) {
    fun getSchedule(groupId: Int):Flow<ResponseState> = flow {
        return@flow try {
            val result = scheduleApiImpl.getSchedule(groupId).map {
                Schedule(
                    id = it.id,
                    dayOfWeek = it.dayOfWeek,
                    lessonNumber = it.lessonNumber,
                    subject = Subject(
                        it.subject.id,
                        it.subject.name,
                    ),
                    audience = it.audience
                )
            }
            emit(ResponseState.Success(result))
        } catch (e: Exception) {
            emit(ResponseState.Error(e.message.toString()))
        }
    }
    fun getStudents(groupId: Int):Flow<ResponseState> = flow {
        return@flow try {
            val result = scheduleApiImpl.getStudent(groupId).map {
                Student(
                    id = it.studentId,
                    name = it.fio
                )
            }
            emit(ResponseState.Success(result))
        } catch (e: Exception) {
            emit(ResponseState.Error(e.message.toString()))
        }
    }
    fun getGroupPresence(groupId: Int): Flow<ResponseState> = flow {
        return@flow try {
            val result = scheduleApiImpl.getGroupPresence(groupId).map {
                Attendance(
                    scheduleId = it.scheduleId,
                    studentId = it.scheduleId,
                    presenceId = it.presenceId,
                    presenceDate = it.presenceDate,
                    attendanceType = it.attendanceTypeId
                )
            }
            emit(ResponseState.Success(result))
        } catch (e: Exception) {
            emit(ResponseState.Error(e.message.toString()))
        }
    }
}