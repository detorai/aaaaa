package org.example.presenceapp.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.example.presenceapp.data.local.storage.schedule.ScheduleLocal
import org.example.presenceapp.data.remote.ScheduleApiImpl
import org.example.presenceapp.data.remote.dto.schedule.ScheduleResponseDto
import org.example.presenceapp.data.remote.dto.schedule.SubjectResponse
import org.example.presenceapp.domain.models.ResponseState

class ScheduleRepository(
    private val scheduleApiImpl: ScheduleApiImpl,
    private val local: ScheduleLocal
) {
    fun getSchedule(groupId: Int):Flow<ResponseState> = flow {
        return@flow try {
            val result = scheduleApiImpl.getSchedule(groupId)
            local.insertSchedule(
                ScheduleResponseDto(
                    id = result.id,
                    subject = SubjectResponse(result.subject.id, result.subject.name),
                    lessonNumber = result.lessonNumber,
                    audience = result.audience,
                    dayOfWeek = result.dayOfWeek
                )
            )
            emit( ResponseState.Success(result))
        } catch (e: Exception) {
            emit(ResponseState.Error(e.message.toString()))
        }
    }
}