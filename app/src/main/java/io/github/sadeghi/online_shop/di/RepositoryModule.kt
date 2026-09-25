package io.github.sadeghi.online_shop.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import io.github.sadeghi.online_shop.data.local.datastore.UserPreferences
import io.github.sadeghi.online_shop.data.repository.AuthRepository
import io.github.sadeghi.online_shop.domain.repository.IAuthRepository
import io.github.sadeghi.online_shop.domain.repository.IProductRepository
import io.github.sadeghi.online_shop.domain.repository.IProductReviewRepository
import io.github.sadeghi.online_shop.domain.repository.IProfileRepository
import io.github.sadeghi.online_shop.domain.repository.ISubCategoryRepository
import io.github.sadeghi.online_shop.data.repository.ProductRepository
import io.github.sadeghi.online_shop.data.repository.ProductReviewRepository
import io.github.sadeghi.online_shop.data.repository.ProfileRepository
import io.github.sadeghi.online_shop.data.repository.SubCategoryRepository
import io.github.sadeghi.online_shop.domain.repository.INotificationsRepository
import io.github.sadeghi.online_shop.data.repository.NotificationsRepositoryImpl
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
    fun provideNotificationsRepository(
        supabaseClient: SupabaseClient
    ): INotificationsRepository {
        return NotificationsRepositoryImpl(supabaseClient)
    }

    @Provides
    @Singleton
    fun provideProductRepository(
        productRepository: ProductRepository
    ): IProductRepository {
        return productRepository
    }

    @Provides
    @Singleton
    fun provideSubCategoryRepository(
        subCategoryRepository: SubCategoryRepository
    ): ISubCategoryRepository {
        return subCategoryRepository
    }

    @Provides
    @Singleton
    fun provideProductReviewRepository(
        productReviewRepository: ProductReviewRepository
    ): IProductReviewRepository {
        return productReviewRepository
    }
}
