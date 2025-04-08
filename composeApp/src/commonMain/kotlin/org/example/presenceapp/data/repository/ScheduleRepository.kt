package org.example.presenceapp.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.example.presenceapp.data.remote.ScheduleApiImpl
import org.example.presenceapp.domain.models.ResponseState
import org.example.presenceapp.someData.Schedule
import org.example.presenceapp.someData.Student
import org.example.presenceapp.someData.Subject

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
}