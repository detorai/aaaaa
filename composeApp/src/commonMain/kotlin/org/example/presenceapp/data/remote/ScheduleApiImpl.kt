package org.example.presenceapp.data.remote

import org.example.presenceapp.data.remote.api.ScheduleApi
import org.example.presenceapp.data.remote.dto.schedule.ScheduleResponseDto

class ScheduleApiImpl(private val scheduleApi: ScheduleApi) {
    suspend fun getSchedule(groupId: Int): ScheduleResponseDto = scheduleApi.getSchedule(groupId)
}