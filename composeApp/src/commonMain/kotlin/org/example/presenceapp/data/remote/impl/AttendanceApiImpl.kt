package org.example.presenceapp.data.remote.impl

import org.example.presenceapp.data.model.dto.attendance.AttendanceTypeDto
import org.example.presenceapp.data.remote.api.AttendanceApi

class AttendanceApiImpl(private val attendance: AttendanceApi) {
    suspend fun getAttendanceType(): List<AttendanceTypeDto> = attendance.getAttendanceTypes()
}