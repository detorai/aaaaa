package org.example.presenceapp.data.remote.api

import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Path
import org.example.presenceapp.data.remote.dto.schedule.ScheduleResponseDto

interface ScheduleApi {
    @GET("api/v1/group/{id}/schedule")
    suspend fun getSchedule(@Path id: Int): ScheduleResponseDto
}