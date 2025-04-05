package org.example.presenceapp.someData

data class Student(
    val id: String,
    val name: String
)

data class Lesson(
    val time: String,
    val subject: String,
    val teacher: String,
    val room: String
)

class SomeStudents {
    val students = listOf(
        Student(id = "1", name = "Васильев Кирилл"),
        Student(id = "2", name = "Игнатова Вероника"),
        Student(id = "3", name = "Латышева Екатерина"),
        Student(id = "4", name = "Ермолаев Егор"),
        Student(id = "5", name = "Фролов Владимир"),
        Student(id = "6", name = "Чеботарева Анастасия"),
        Student(id = "7", name = "Попова Виктория"),
        Student(id = "8", name = "Соловьева Лейла"),
        Student(id = "9", name = "Орлова Анжелика"),
        Student(id = "10", name = "Осипова Татьяна"),
        Student(id = "11", name = "Николаева Ева"),
        Student(id = "12", name = "Федосеева Майя")
    )
}

object SampleData {
    val lessons = listOf(
        Lesson("09:00", "Subject 1", "Teacher 1", "101"),
        Lesson("09:55", "Subject 2", "Teacher 2", "202"),
        Lesson("10:50", "Subject 3", "Teacher 3", "303")
    )
}

object SelectedLessonHolder {
    var selectedLesson: Lesson? = null
}