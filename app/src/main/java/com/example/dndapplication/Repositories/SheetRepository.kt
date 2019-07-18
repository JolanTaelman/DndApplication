package com.example.dndapplication.Repositories

import androidx.lifecycle.LiveData
import com.example.dndapplication.Models.Sheet
import org.jetbrains.anko.doAsync

/*
class SheetRepository(
    private val sheetDao: SheetDao
) {

    fun insert(sheet: Sheet) {
        doAsync {
            sheetDao.insert(sheet)
        }
    }

    fun getSheet(sheetId: String): LiveData<Sheet> = sheetDao.getSheet(sheetId)

    fun getAllSheets(): LiveData<List<Sheet>> {
        return sheetDao.getAllSheets()
    }


}
*/