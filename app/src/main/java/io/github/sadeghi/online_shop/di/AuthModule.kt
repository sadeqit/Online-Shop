package io.github.sadeghi.online_shop.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.sadeghi.online_shop.data.repository.AuthRepository
import io.github.sadeghi.online_shop.data.repository.IAuthRepository
import io.github.sadeghi.online_shop.data.local.database.datastore.UserPreferences
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun provideAuthRepository(
        userPreferences: UserPreferences
    ): IAuthRepository {
        return AuthRepository(userPreferences)
    }
}
