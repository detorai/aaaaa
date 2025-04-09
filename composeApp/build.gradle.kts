import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.ksp)
    alias(libs.plugins.ktorfit)
    id("app.cash.sqldelight") version "2.0.1"

}

sqldelight {
    databases {
        create("ScheduleDatabase") {
            packageName = "org.example.presenceapp.data.local.db"
        }
    }
}

compose.resources {
    publicResClass = false
    packageOfResClass = "com.presenceapp.composeapp.resources"
    generateResClass = auto
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }

        iosTarget.compilations.getByName("main") {
            cinterops {
                val uikit by creating {
                    defFile(file("src/iosMain/c_interop/Uikit.def"))
                }
            }
        }
    }

    sourceSets {
        val sqldelightVersion = "2.0.1"
        val androidMain by getting {
            dependencies {
                implementation(compose.preview)
                implementation(libs.androidx.activity.compose)
                implementation(libs.androidx.lifecycle.viewmodel)
                implementation(libs.androidx.lifecycle.runtime.compose)
                implementation(libs.androidx.datastore.preferences)
                implementation(libs.androidx.datastore.preferences.core)
                implementation(libs.androidx.startup.runtime)

                // ktor
                implementation(libs.ktor.client.okhttp)

                // koin
                implementation(libs.koin.android)
                implementation(libs.koin.androidx.compose)
                implementation(libs.koin.ktor)

                implementation("app.cash.sqldelight:android-driver:$sqldelightVersion")
            }
        }

        val commonMain by getting {
            resources.srcDirs("src/commonMain/composeResources")
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.ui)
                implementation(compose.components.resources)
                implementation(compose.components.uiToolingPreview)
                implementation(libs.kotlinx.serialization.json)
                implementation(libs.kotlinx.coroutines.core)
                implementation(libs.kotlinx.datetime)
//                implementation(libs.resources)

                // voyager
                implementation(libs.voyager.navigator)
                implementation(libs.voyager.screenmodel)
                implementation(libs.voyager.koin)

                // ktor
                implementation(libs.ktor.client.core)
                implementation(libs.ktor.client.websockets)
                implementation(libs.ktor.client.content.negotiation)
                implementation(libs.ktor.client.logging)
                implementation(libs.ktor.client.cio)
                implementation(libs.ktor.serialization.kotlinx.json)

                // ktorfit
                implementation(libs.ktorfit)

                // koin
                implementation(libs.koin.core)

                implementation("app.cash.sqldelight:primitive-adapters:$sqldelightVersion")
                implementation("app.cash.sqldelight:runtime:$sqldelightVersion")
                implementation("app.cash.sqldelight:coroutines-extensions:$sqldelightVersion")
            }
        }

        iosMain.dependencies {
            // ktor
            implementation(libs.ktor.client.darwin)

            // koin
            implementation(libs.koin.core.native)

            //sqldelight
            implementation("app.cash.sqldelight:native-driver:$sqldelightVersion")

            implementation(libs.kotlinx.coroutines.core.v173nativemt)
            implementation(libs.kermit)
        }
    }
}

android {
    namespace = "org.example.presenceapp"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "org.example.presenceapp"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
//    implementation(libs.androidx.material3.android)
    implementation(libs.androidx.ui.android)
    implementation(libs.androidx.annotation.jvm)
    implementation(libs.androidx.ui.text.android)
    implementation(libs.androidx.material3.android)
    debugImplementation(compose.uiTooling)

    add("kspCommonMainMetadata", libs.ktorfit.ksp)
    add("kspAndroid", libs.ktorfit.ksp)
    add("kspIosX64", libs.ktorfit.ksp)
    add("kspIosArm64", libs.ktorfit.ksp)
}