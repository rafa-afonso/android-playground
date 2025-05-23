package com.example.androidplayground.db.news

import androidx.room.TypeConverter
import com.example.androidplayground.model.news.Source

class Converters {

    @TypeConverter
    fun fromSource(source: Source): String {
        return source.name
    }

    @TypeConverter
    fun toSource(name: String): Source {
        return Source(name, name)
    }
}