package com.example.dndapplication.Repositories

import androidx.lifecycle.LiveData
import com.example.dndapplication.Models.DndClass
import com.example.dndapplication.database.ClassDao
import org.jetbrains.anko.doAsync


class ClassRepository(
    private val classDao: ClassDao
) {

    fun insert(dndClass: DndClass) {
        doAsync {
            classDao.insert(dndClass)
        }
    }

    fun getAllClasses(): LiveData<List<DndClass>> {
        return classDao.getAllClasses()
    }
}
