package org.example.presenceapp.network

import de.jensklingenberg.ktorfit.Ktorfit
import org.example.presenceapp.data.remote.api.AttendanceApi
import org.example.presenceapp.data.remote.api.AuthApi
import org.koin.dsl.module

val networkModule = module {
    single { KtorfitClient.instance }
    single<AuthApi> { get<Ktorfit>().create() }
    single<AttendanceApi> { get<Ktorfit>().create() }
}