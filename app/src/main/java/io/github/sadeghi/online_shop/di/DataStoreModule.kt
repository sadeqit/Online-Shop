package io.github.sadeghi.online_shop.di

import android.app.Application
import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.sadeghi.online_shop.database.datastore.UserPreferences
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    private val Context.dataStore by preferencesDataStore("user_prefs")

    @Provides
    @Singleton
    fun provideUserPreferences(
         context: Application
    ): UserPreferences {
        return UserPreferences(context)
    }
}