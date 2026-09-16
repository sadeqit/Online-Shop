package io.github.sadeghi.online_shop.data.local.datastore


import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import io.github.sadeghi.online_shop.ui.screens.productScreen.screen.ProductReview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private val Context.productReviewDataStore: DataStore<Preferences>
        by preferencesDataStore(name = "product_review_preferences")

class ProductReviewDataStore @Inject constructor(
    @param:ApplicationContext
    private val context: Context,
    private val gson: Gson
) {

    companion object {

        private val REVIEWS =
            stringPreferencesKey("product_reviews")
    }

    val reviews: Flow<String> =
        context.productReviewDataStore.data.map { preferences ->
            preferences[REVIEWS] ?: "[]"
        }

    suspend fun saveReviews(reviews: String) {

        context.productReviewDataStore.edit { preferences ->
            preferences[REVIEWS] = reviews
        }
    }

    suspend fun clearReviews() {

        context.productReviewDataStore.edit { preferences ->
            preferences.remove(REVIEWS)
        }
    }

    fun toJson(
        reviews: List<ProductReview>
    ): String {
        return gson.toJson(reviews)
    }

    fun fromJson(
        json: String
    ): List<ProductReview> {

        if (json.isBlank()) {
            return emptyList()
        }

        val type = object :
            TypeToken<List<ProductReview>>() {}.type

        return gson.fromJson(
            json,
            type
        ) ?: emptyList()
    }
}