package org.example.presenceapp.data.remote.api

import de.jensklingenberg.ktorfit.http.Body
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.POST
import de.jensklingenberg.ktorfit.http.Path
import org.example.presenceapp.data.remote.dto.attendance.AttendanceRequestDto
import org.example.presenceapp.data.remote.dto.attendance.AttendanceTypeDto

interface AttendanceApi {
    @POST("presence")
    suspend fun submitPresences(@Body presences: List<AttendanceRequestDto>)

    @GET("presence/presetting/{groupId}")
    suspend fun getGroupPresetting(@Path("groupId") groupId: Int): List<AttendanceRequestDto>

    @GET("presence/dictionary/attendance_type")
    suspend fun getAttendanceTypes(): List<AttendanceTypeDto>
}