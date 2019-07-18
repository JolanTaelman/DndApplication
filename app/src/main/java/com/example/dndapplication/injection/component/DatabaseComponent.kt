package com.example.dndapplication.injection.component

import com.example.dndapplication.App
import com.example.dndapplication.ViewModels.SheetAddViewModel
import com.example.dndapplication.injection.module.DatabaseModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [DatabaseModule::class])
interface DatabaseComponent {
    fun inject(app: App)
    fun inject(sheetAddViewModel: SheetAddViewModel)

}