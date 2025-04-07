package org.example.presenceapp.network

import de.jensklingenberg.ktorfit.Ktorfit
import org.example.presenceapp.data.remote.AuthApiImpl
import org.example.presenceapp.data.remote.ScheduleApiImpl
import org.example.presenceapp.data.remote.api.AttendanceApi
import org.example.presenceapp.data.remote.api.AuthApi
import org.example.presenceapp.data.remote.api.ScheduleApi
import org.example.presenceapp.data.remote.api.createAuthApi
import org.example.presenceapp.data.remote.api.createScheduleApi
import org.example.presenceapp.data.repository.LoginRepository
import org.example.presenceapp.data.repository.ScheduleRepository
import org.example.project.ui.login.LoginViewModel
import org.koin.dsl.module

val networkModule = module {
    single { KtorfitClient.instance }
    single<AuthApi> { get<Ktorfit>().createAuthApi() }
    single<ScheduleApi> { get<Ktorfit>().createScheduleApi() }
    single<AttendanceApi> { get<Ktorfit>().create() }

    single { AuthApiImpl(get()) }
    single { LoginRepository(get(), get()) }
    single { ScheduleApiImpl(get()) }
    single { ScheduleRepository (get()) }

    factory { LoginViewModel(get(), get()) }


}