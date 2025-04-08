package org.example.project.ui.login

import org.example.presenceapp.someData.Schedule
import org.example.presenceapp.someData.Student

data class LoginScreenState(
    var login: String = "",
    var password: String = "",
    var error: String? = null,
    var success: Boolean = false,
    var check: Boolean = false,
    var groupId: Int = 0,
    var lessonsList: List<Schedule> = emptyList(),
    var groupList: List<Student> = emptyList()
)
