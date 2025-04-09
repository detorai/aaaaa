package org.example.presenceapp.data.remote.impl

import org.example.presenceapp.data.model.dto.attendance.AttendanceRequestDto
import org.example.presenceapp.data.remote.api.GroupApi
import org.example.presenceapp.data.model.dto.group.StudentResponseDto
import org.example.presenceapp.data.model.dto.schedule.ScheduleResponseDto

class ScheduleApiImpl(private val groupApi: GroupApi) {
    suspend fun getSchedule(groupId: Int): List<ScheduleResponseDto> = groupApi.getSchedule(groupId)
    suspend fun getStudent(groupId: Int): List<StudentResponseDto> = groupApi.getStudents(groupId)
    suspend fun getGroupPresence(groupId: Int): List<AttendanceRequestDto> = groupApi.getGroupPresence(groupId)
}