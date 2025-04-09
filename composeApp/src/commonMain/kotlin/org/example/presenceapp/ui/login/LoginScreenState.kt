package org.example.project.ui.login

import org.example.presenceapp.domain.models.Attendance
import org.example.presenceapp.domain.someData.Schedule
import org.example.presenceapp.domain.someData.Student

data class LoginScreenState(
    var login: String = "",
    var password: String = "",
    var error: String? = null,
    var success: Boolean = false,
    var getAllData: Boolean = false,
    var check: Boolean = false,
    var groupId: Int = 0,
    var lessonsList: List<Schedule> = emptyList(),
    var groupList: List<Student> = emptyList(),
    var groupPresence: List<Attendance> = emptyList()
)
