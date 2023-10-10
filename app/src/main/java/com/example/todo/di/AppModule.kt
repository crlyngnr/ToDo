package com.example.todo.di

import android.content.Context
import androidx.room.Room
import com.example.todo.data.datasource.GorevlerDataSource
import com.example.todo.data.repo.GorevlerRepository
import com.example.todo.room.GorevlerDao
import com.example.todo.room.Veritabani
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent ::class)
class AppModule {
    @Provides
    @Singleton
    fun provideGorevlerDataSource(gDao : GorevlerDao) : GorevlerDataSource {
        return GorevlerDataSource(gDao)
    }
    @Provides
    @Singleton
    fun provideGorevlerRepository(gds : GorevlerDataSource) : GorevlerRepository {
        return GorevlerRepository(gds)
    }
    @Provides
    @Singleton
    fun provideGorevlerDao(@ApplicationContext context: Context):GorevlerDao{
        val vt = Room.databaseBuilder(context, Veritabani::class.java,"gorevler.sqlite")
            .createFromAsset("gorevler.sqlite").build()
        return vt.getGorevlerDao()
    }
}