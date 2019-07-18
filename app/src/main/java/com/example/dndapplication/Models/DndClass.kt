package com.example.dndapplication.Models

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(tableName = "class_table", indices = [(Index(value = ["id"], unique = true))])
data class DndClass(@PrimaryKey @NonNull var id: Int, var name: String?, var hit_die: Int, var AC: Int)
