package com.example.dndapplication.base

import androidx.lifecycle.ViewModel
import com.example.dndapplication.ViewModels.SheetAddViewModel
import com.example.dndapplication.ViewModels.SheetViewModel
import com.example.dndapplication.injection.component.DaggerViewModelComponent
import com.example.dndapplication.injection.component.ViewModelComponent
import com.example.dndapplication.injection.module.NetworkModule


abstract class BaseViewModel : ViewModel() {


   private val injector: ViewModelComponent = DaggerViewModelComponent
        .builder()
        .networkModule(NetworkModule)
        .build()

    init {
        inject()
    }

    private fun inject() {
        when (this) {
            is SheetViewModel -> injector.inject(this)
          //  is SheetAddViewModel  -> injector.inject(this)

        }
    /*    when (this) {
            is SheetAddViewModel  -> injector.inject(this)
        }*/
    }

}