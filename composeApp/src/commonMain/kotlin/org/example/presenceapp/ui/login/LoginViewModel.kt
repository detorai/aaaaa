package org.example.project.ui.login

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.example.presenceapp.data.remote.dto.auth.AuthRequestDto
import org.example.presenceapp.data.remote.dto.auth.UserResponse
import org.example.presenceapp.data.repository.LoginRepository
import org.example.presenceapp.data.repository.ScheduleRepository
import org.example.presenceapp.domain.models.ResponseState
import org.example.presenceapp.someData.Schedule
import org.example.presenceapp.someData.Student


class LoginViewModel(private val authRepository: LoginRepository, private val scheduleRepository: ScheduleRepository): ScreenModel {
    val state = MutableStateFlow(LoginScreenState())


    fun resetError(){
        state.update{
            it.copy(
                error = null
            )
        }
    }
    fun onLogin(login: String){
        state.update {
            it.copy(
                login = login
            )
        }
    }
    fun onPassword(password: String){
        state.update {
            it.copy(
                password = password
            )
        }
    }
    fun onCheck(){
        state.update {
            it.copy(
                check = !it.check
            )
        }
    }

    fun login(login: String, password: String) {
        val loginRequest = AuthRequestDto(login, password)
        screenModelScope.launch {
            val result = authRepository.login(loginRequest)
            result.collect{response ->
                when (response) {
                    is ResponseState.Success<*> -> {
                        val userResponse = (response.data as UserResponse)
                        val groupId = userResponse.responsible.first().group.id
                        getSchedule(groupId)
                        getStudents(groupId)
                        state.update {
                            it.copy(
                                groupId = groupId
                            )
                        }
                    }
                    is ResponseState.Error -> {
                        state.update {
                            it.copy(
                                error = response.error
                            )
                        }
                    }
                }
            }
        }
    }
    fun getSchedule(groupId: Int){
        screenModelScope.launch {
            val result = scheduleRepository.getSchedule(groupId)
            result.collect{response ->
                when (response) {
                    is ResponseState.Success<*> -> {
                        state.update{
                            it.copy(
                                success = true,
                                lessonsList = response.data as List<Schedule>
                            )
                        }
                    }
                    is ResponseState.Error -> {
                        state.update{
                            it.copy(

                                error = response.error
                            )
                        }
                    }
                }
            }
        }
    }
    fun getStudents(groupId: Int){
        screenModelScope.launch {
            val result = scheduleRepository.getStudents(groupId)
            result.collect{response ->
                when (response) {
                    is ResponseState.Success<*> -> {
                        state.update{
                            it.copy(
                                groupList = response.data as List<Student>
                            )
                        }
                    }
                    is ResponseState.Error -> {
                        state.update{
                            it.copy(
                                success = true,
                                error = response.error
                            )
                        }
                    }
                }
            }
        }
    }
}