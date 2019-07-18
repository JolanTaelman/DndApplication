package com.example.dndapplication.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dndapplication.Models.DndClass
import com.example.dndapplication.Models.Sheet
import java.util.*

@Dao
interface ClassDao {

    @Query("SELECT * from class_table")
    fun getAllClasses(): LiveData<List<DndClass>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(dndClass: DndClass)

    @Query("SELECT * FROM class_table WHERE id=:classId")
    fun getClass(classId: String): LiveData<DndClass>

    @Query("DELETE FROM class_table")
    fun deleteAll()
}