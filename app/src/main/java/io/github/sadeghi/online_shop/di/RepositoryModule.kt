package io.github.sadeghi.online_shop.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import io.github.sadeghi.online_shop.data.local.datastore.ProductReviewDataStore
import io.github.sadeghi.online_shop.data.repository.AuthRepository
import io.github.sadeghi.online_shop.data.repository.IAuthRepository
import io.github.sadeghi.online_shop.data.local.datastore.UserPreferences
import io.github.sadeghi.online_shop.data.repository.IProductReviewRepository
import io.github.sadeghi.online_shop.data.repository.IProfileRepository
import io.github.sadeghi.online_shop.data.repository.ProductReviewRepository
import io.github.sadeghi.online_shop.data.repository.ProfileRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideAuthRepository(
        userPreferences: UserPreferences,
        supabaseClient: SupabaseClient
    ): IAuthRepository {
        return AuthRepository(
            userPreferences,
            supabaseClient
        )
    }

    @Provides
    @Singleton
    fun provideProfileRepository(
        profileRepository: ProfileRepository
    ): IProfileRepository {
        return profileRepository
    }
    @Provides
    @Singleton
    fun provideProductReviewRepository(
        dataStore: ProductReviewDataStore
    ): IProductReviewRepository {
        return ProductReviewRepository(dataStore)
    }
}
