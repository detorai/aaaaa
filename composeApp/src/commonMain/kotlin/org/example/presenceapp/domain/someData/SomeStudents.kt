package org.example.presenceapp.domain.someData

import kotlinx.datetime.LocalDate

data class Student(
    val id: Int,
    val name: String
)

data class Schedule(
    val id: Int,
    val lessonNumber: Int,
    val audience: String,
    val subject: Subject,
    val dayOfWeek: Int,
)
data class Subject(
    val id: Int,
    val name: String
)

class SomeStudents {
    val students = listOf(
        Student(id = 1, name = "Васильев Кирилл"),
        Student(id = 2, name = "Игнатова Вероника"),
        Student(id = 3, name = "Латышева Екатерина"),
        Student(id = 4, name = "Ермолаев Егор"),
        Student(id = 5, name = "Фролов Владимир"),
        Student(id = 6, name = "Чеботарева Анастасия"),
        Student(id = 7, name = "Попова Виктория"),
        Student(id = 8, name = "Соловьева Лейла"),
        Student(id = 9, name = "Орлова Анжелика"),
        Student(id = 10, name = "Осипова Татьяна"),
        Student(id = 11, name = "Николаева Ева"),
        Student(id = 12, name = "Федосеева Майя")
    )
}

object SampleData {
    val lessonTimes = listOf("9:00", "9:55", "10:50", "11:55", "13:00", "14:00", "14:55", "15:45")
}

object SelectedLessonHolder {
    var selectedLesson: Schedule? = null
}

data class WeekDay(
    val date: LocalDate,
    val displayName: String
)

