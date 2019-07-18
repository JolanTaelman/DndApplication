package com.example.dndapplication.injection.module

import android.app.Application
import android.content.Context
import com.example.dndapplication.Network.SheetApi
import com.example.dndapplication.database.ClassDao
import com.example.dndapplication.Repositories.ClassRepository
import com.example.dndapplication.database.ClassDatabase
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
class DatabaseModule(private val application: Application) {
/*
    @Provides
    @Singleton
    internal fun provideSheetRepository(sheetDao: SheetDao): SheetRepository {
        return SheetRepository(sheetDao)
    }


    @Provides
    @Singleton
    internal fun provideSheetDao(sheetDatabase: SheetDatabase): SheetDao {
        return sheetDatabase.sheetDao()
    }

    @Provides
    @Singleton
    internal fun provideSheetDatabase(context: Context): SheetDatabase {
        return SheetDatabase.getDatabase(context)
    }
    */
  @Provides
  @Singleton
  internal fun provideClassRepository(classDao: ClassDao): ClassRepository {
      return ClassRepository(classDao)
  }


    @Provides
    @Singleton
    internal fun provideClassDao(classDatabase: ClassDatabase): ClassDao {
        return classDatabase.DndClassDao()
    }



    @Provides
    @Singleton
    internal fun provideClassDatabase(context: Context): ClassDatabase {
        return ClassDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun provideApplicationContext(): Context {
        return application
    }


    @Provides
    internal fun provideSheetApi(retrofit: Retrofit): SheetApi {
        return retrofit.create(SheetApi::class.java)
    }

    val baseUrl: String = "https://dndprojserver.herokuapp.com/"

    @Provides
    internal fun provideRetrofitInterface(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
    }

    @Provides
    internal fun provideOkHttpClient(): OkHttpClient {
        val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder().apply {
            addInterceptor(interceptor)
        }.build()
    }



}