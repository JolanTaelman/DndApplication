package com.example.dndapplication.ViewModels

import android.annotation.SuppressLint
import android.util.Log
import com.example.dndapplication.App
import com.example.dndapplication.Models.DndClass
import com.example.dndapplication.Models.Sheet
import com.example.dndapplication.Network.SheetApi
import com.example.dndapplication.Repositories.ClassRepository
import com.example.dndapplication.base.BaseViewModel
import javax.inject.Inject

class SheetAddViewModel : BaseViewModel() {
    @Inject
    lateinit var classRepository: ClassRepository

    @Inject
    lateinit var sheetApi: SheetApi

    init {
        App.component.inject(this)
    }

    val classList =  classRepository.getAllClasses()

    fun insert(dndClass: DndClass){
        classRepository.insert(dndClass)
    }

    @SuppressLint("CheckResult")
    fun postSheet(sheet: Sheet){

        Log.e("PostSheet in VM", sheet.toString())

        this.sheetApi.postSheet(sheet.playerName!!, sheet.characterName!!, sheet.background!!, sheet.race!!,
            sheet.alignment!!,sheet.className!!, sheet.hit_die, sheet.class_levels, sheet.strength, sheet.dexterity,
            sheet.constitution, sheet.intelligence, sheet.wisdom, sheet.charisma, sheet.armorClass, sheet.speed, sheet.hp).subscribe {
                Log.e("Completed", it.toString())
        }
    }
}
