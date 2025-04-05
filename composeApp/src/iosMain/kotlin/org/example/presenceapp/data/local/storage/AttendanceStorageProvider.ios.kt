package org.example.presenceapp.data.local.storage

import org.example.presenceapp.PlatformContext
import org.example.presenceapp.data.local.storage.AttendanceStorage

actual class AttendanceStorageProvider actual constructor(context: PlatformContext) {
    actual fun provide(): AttendanceStorage {
        return AttendanceStorageIos()
    }
}