package org.example.presenceapp.di

import org.example.presenceapp.data.local.login.AndroidTokenStorage
import org.example.project.data.local.AuthManager
import org.example.project.data.local.TokenStorage
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val androidModule = module {
    single<TokenStorage> { AndroidTokenStorage(androidContext()) }
    single { AuthManager(get()) }

//    single<DatabaseHelper> { DatabaseHelper(androidContext()) }
//    single<ScheduleDatabase> { get<DatabaseHelper>().database }

}