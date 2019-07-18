package com.example.dndapplication


import android.app.Application
import com.example.dndapplication.injection.component.DaggerDatabaseComponent
import com.example.dndapplication.injection.component.DatabaseComponent
import com.example.dndapplication.injection.module.DatabaseModule

class App : Application() {
    companion object {
        lateinit var component: DatabaseComponent
    }

    override fun onCreate() {
        super.onCreate()

        component = DaggerDatabaseComponent
                .builder()
                .databaseModule(DatabaseModule(this))
                .build()
    }
}
