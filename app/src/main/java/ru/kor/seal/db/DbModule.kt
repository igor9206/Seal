package ru.kor.seal.db

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import ru.kor.seal.dao.ObjectiveDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    @Provides
    @Singleton
    fun provideDb(
        @ApplicationContext
        context: Context
    ): AppDb = Room.databaseBuilder(
        context,
        AppDb::class.java,
        "AppDb"
    ).build()

    @Provides
    fun provideObjectiveDao(
        appDb: AppDb
    ): ObjectiveDao = appDb.objectiveDao()
}