package org.example.presenceapp.di

import de.jensklingenberg.ktorfit.Ktorfit
import org.example.presenceapp.data.remote.impl.AuthApiImpl
import org.example.presenceapp.data.remote.impl.ScheduleApiImpl
import org.example.presenceapp.data.remote.api.AttendanceApi
import org.example.presenceapp.data.remote.api.AuthApi
import org.example.presenceapp.data.remote.api.GroupApi
import org.example.presenceapp.data.remote.api.createAttendanceApi
import org.example.presenceapp.data.remote.api.createAuthApi
import org.example.presenceapp.data.remote.api.createGroupApi
import org.example.presenceapp.data.remote.impl.AttendanceApiImpl
import org.example.presenceapp.data.remote.network.KtorfitClient
import org.example.presenceapp.data.repository.AttendanceRepository
import org.example.presenceapp.data.repository.LoginRepository
import org.example.presenceapp.data.repository.ScheduleRepository
import org.example.presenceapp.ui.attendance.AttendanceScreenModel
import org.example.project.ui.login.LoginViewModel
import org.koin.dsl.module

val networkModule = module {
    single { KtorfitClient.instance }
    single<AuthApi> { get<Ktorfit>().createAuthApi() }
    single<GroupApi> { get<Ktorfit>().createGroupApi() }
    single<AttendanceApi> { get<Ktorfit>().createAttendanceApi() }

    single { AuthApiImpl(get()) }
    single { LoginRepository(get(), get()) }
    single { ScheduleApiImpl(get()) }
    single { ScheduleRepository (get()) }

    factory { LoginViewModel(get(), get()) }

    single { AttendanceApiImpl(get()) }
    single { AttendanceRepository(get(), get()) }

    factory { AttendanceScreenModel(get()) }
}