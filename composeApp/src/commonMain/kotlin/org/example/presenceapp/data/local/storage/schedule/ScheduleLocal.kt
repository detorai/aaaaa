package org.example.presenceapp.data.local.storage.schedule


import org.example.presenceapp.data.remote.dto.schedule.ScheduleResponseDto
import org.example.presenceapp.db.GetAllSchedules
import org.example.presenceapp.db.ScheduleDatabase

class ScheduleLocal(
    private val db: ScheduleDatabase
) {
    suspend fun insertSchedule(schedule: ScheduleResponseDto) {
        db.scheduleQueries.insertSchedule(
            schedule.id,
            schedule.lessonNumber,
            schedule.audience,
            schedule.dayOfWeek,
            schedule.subject.id
        )
    }

    fun getAllSchedules(): List<GetAllSchedules> {
        return db.scheduleQueries.getAllSchedules().executeAsList()
    }
}