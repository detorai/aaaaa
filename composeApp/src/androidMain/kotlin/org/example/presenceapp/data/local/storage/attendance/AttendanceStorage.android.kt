package org.example.presenceapp.data.local.storage.attendance

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json

private val Context.attendanceDataStore by preferencesDataStore(name = "attendance_prefs")

class AttendanceStorageAndroid(private val context: Context): AttendanceStorage {

    private val ATTENDANCE_KEY = stringPreferencesKey("attendance_map")

    override suspend fun saveAttendanceMap(map: Map<Int, String>) {
        val json = Json.encodeToString(map)
        context.attendanceDataStore.edit { prefs ->
            prefs[ATTENDANCE_KEY] = json
        }
    }

    override fun attendanceMapFlow(): Flow<Map<Int, String>> {
        return context.attendanceDataStore.data.map { prefs ->
            prefs[ATTENDANCE_KEY]?.let {
                Json.decodeFromString<Map<Int, String>>(it)
            } ?: emptyMap()
        }
    }
}