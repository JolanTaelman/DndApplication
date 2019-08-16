package com.example.dndapplication.database

import androidx.room.TypeConverter
import java.util.*

class Converters {
    @TypeConverter
    fun toString(uuid: UUID): String {
        return uuid.toString()
    }

    @TypeConverter
    fun toUuid(uuidString: String): UUID {
        return UUID.fromString(uuidString)
    }
}
