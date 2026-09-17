package io.github.sadeghi.online_shop.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.sadeghi.online_shop.ui.screens.profilescreen.notif.FakeNotificationsRepository
import io.github.sadeghi.online_shop.ui.screens.profilescreen.notif.NotificationsRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NotificationsModule {

    @Provides
    @Singleton
    fun provideNotificationsRepository(): NotificationsRepository {
        return FakeNotificationsRepository()
    }
}