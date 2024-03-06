package com.sandbox.compose.newsapp.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.sandbox.compose.newsapp.domain.model.local.SourceEntity

@ProvidedTypeConverter
class NewsTypeConverter {

    @TypeConverter
    fun sourceToString(sourceDto: SourceEntity): String {
        return "${sourceDto.id},${sourceDto.name}"
    }

    @TypeConverter
    fun stringToSource(string: String): SourceEntity {
        return SourceEntity(
            id = string.split(",")[0],
            name = string.split(",")[1]
        )
    }
}