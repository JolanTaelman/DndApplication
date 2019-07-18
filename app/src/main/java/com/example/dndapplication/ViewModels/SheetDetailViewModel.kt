package com.example.dndapplication.ViewModels

import androidx.lifecycle.ViewModel
import com.example.dndapplication.Models.Sheet

class SheetDetailViewModel : ViewModel() {
    val sheetList =  mutableListOf<Sheet>()

    fun fillsheetList(){
        if(sheetList.count() == 0) {
            for (i in 0..10) {
                val playername = "Player $i"
                val characterName = "Steven $i"
                val newSheet = Sheet(i.toString(),playername,characterName ,"Noble", "Elf", "NG", "Fighter", 8, 15, 15, 10, 10, 10, 10 , 10, 15, 30, 44)
                sheetList.add(newSheet)
            }
        }
 }

    fun getByID(id: String): Sheet?{
        var returnSheet: Sheet? = null
        sheetList.forEach {
            if(it.uid!! == id){
                returnSheet = it
            }
        }
        return returnSheet
    }
    }
