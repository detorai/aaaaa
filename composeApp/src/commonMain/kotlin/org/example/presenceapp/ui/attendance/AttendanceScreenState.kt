package org.example.presenceapp.ui.attendance

import org.example.presenceapp.someData.Student

data class AttendanceScreenState(
    var error: String? = null,
    var success: Boolean = false,
    var groupList: List<Student> = emptyList()
)
