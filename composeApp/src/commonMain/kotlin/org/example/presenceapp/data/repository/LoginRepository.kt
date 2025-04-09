package org.example.presenceapp.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.example.presenceapp.data.remote.impl.AuthApiImpl
import org.example.presenceapp.data.model.dto.auth.AuthRequestDto
import org.example.presenceapp.domain.models.ResponseState
import org.example.project.data.local.AuthManager

class LoginRepository(
    private val authApiImpl: AuthApiImpl,
    private val authManager: AuthManager,
) {
    fun login(authRequestDto: AuthRequestDto): Flow<ResponseState> = flow {
        return@flow try {
            val result = authApiImpl.login(authRequestDto)
            authManager.setToken(result.token)
            emit(ResponseState.Success(result.user))
        } catch (e: Exception) {
            emit(ResponseState.Error(e.message.toString()))
        }
    }
}