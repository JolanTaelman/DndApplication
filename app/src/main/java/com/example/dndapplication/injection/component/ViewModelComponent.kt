package com.example.dndapplication.injection.component

import com.example.dndapplication.Fragments.SheetAddFragment
import com.example.dndapplication.ViewModels.SheetAddViewModel
import com.example.dndapplication.injection.module.NetworkModule
import com.example.dndapplication.ViewModels.SheetViewModel
import com.example.dndapplication.base.BaseViewModel
import dagger.Component
import javax.inject.Singleton


@Singleton
@Component(modules = [NetworkModule::class])
interface ViewModelComponent {
    fun inject(sheetViewModel: SheetViewModel)

}