package org.example.presenceapp.data.remote

import org.example.presenceapp.data.remote.api.GroupApi
import org.example.presenceapp.data.remote.dto.group.StudentResponseDto
import org.example.presenceapp.data.remote.dto.schedule.ScheduleResponseDto

class ScheduleApiImpl(private val groupApi: GroupApi) {
    suspend fun getSchedule(groupId: Int): List<ScheduleResponseDto> = groupApi.getSchedule(groupId)
    suspend fun getStudent(groupId: Int): List<StudentResponseDto> = groupApi.getStudents(groupId)
}