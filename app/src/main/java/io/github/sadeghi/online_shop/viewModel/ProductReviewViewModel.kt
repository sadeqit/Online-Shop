package io.github.sadeghi.online_shop.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.postgrest.from
import io.github.sadeghi.online_shop.data.remote.model.UserRoleDto
import io.github.sadeghi.online_shop.data.repository.IProductReviewRepository
import io.github.sadeghi.online_shop.data.repository.IProfileRepository
import io.github.sadeghi.online_shop.ui.screens.productScreen.screen.ProductReview
import io.github.sadeghi.online_shop.ui.screens.productScreen.screen.ProductReviewUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductReviewViewModel @Inject constructor(
    private val reviewRepository: IProductReviewRepository,
    private val profileRepository: IProfileRepository,
    private val supabaseClient: SupabaseClient
) : ViewModel()
{

    private val _isAdmin =
        MutableStateFlow(false)

    val isAdmin: StateFlow<Boolean> =
        _isAdmin.asStateFlow()

    private val _currentUserId =
        MutableStateFlow<String?>(null)

    val currentUserId: StateFlow<String?> =
        _currentUserId.asStateFlow()

    private val _reviews =
        MutableStateFlow<List<ProductReviewUi>>(emptyList())

    val reviews: StateFlow<List<ProductReviewUi>> =
        _reviews.asStateFlow()
/*
    fun loadCurrentUser() {

        viewModelScope.launch {

            val userId =
                supabaseClient.auth.currentUserOrNull()?.id

            _currentUserId.value = userId

            if (userId.isNullOrBlank()) {
                _isAdmin.value = false
                return@launch
            }

            try {

                val result =
                    supabaseClient
                        .from("user_roles")
                        .select {
                            filter {
                                eq("user_id", userId)
                            }
                        }
                        .decodeList<UserRoleDto>()

                _isAdmin.value =
                    result.any { it.role == "admin" }

            } catch (e: Exception) {

                _isAdmin.value = false

                e.printStackTrace()
            }
        }
    }
*/

    fun loadCurrentUser() {

        viewModelScope.launch {

            val userId =
                supabaseClient.auth.currentUserOrNull()?.id

            println("CURRENT USER ID = $userId")

            _currentUserId.value = userId

            if (userId.isNullOrBlank()) {
                _isAdmin.value = false
                return@launch
            }

            try {

                val result =
                    supabaseClient
                        .from("user_roles")
                        .select {
                            filter {
                                eq("user_id", userId)
                            }
                        }
                        .decodeList<UserRoleDto>()

                println("USER ROLE RESULT = $result")

                _isAdmin.value =
                    result.any { it.role == "admin" }

            } catch (e: Exception) {

                _isAdmin.value = false

                e.printStackTrace()
            }
        }
    }

    fun loadReviewsForProducts(productIds: List<Int>) {
        viewModelScope.launch {

            val allReviews = mutableListOf<ProductReviewUi>()

            productIds.distinct().forEach { productId ->

                val reviews = reviewRepository
                    .getReviews(productId)
                    .first()

                allReviews += reviews.map { review ->

                    val userName =
                        profileRepository
                            .getFullNameByUserId(review.userId)

                    ProductReviewUi(
                        id = review.id,
                        productId = review.productId,
                        userName = userName,
                        userId = review.userId,
                        rating = review.rating,
                        comment = review.comment,
                        createdAt = review.createdAt,
                        adminReply = review.adminReply,
                        adminReplyAt = review.adminReplyAt
                    )
                }
            }

            _reviews.value = allReviews
        }
    }

    fun loadReviews(productId: Int) {

        viewModelScope.launch {

            reviewRepository
                .getReviews(productId)
                .collect { reviews ->

                    _reviews.value =
                        reviews.map { review ->

                            val userName =
                                profileRepository
                                    .getFullNameByUserId(review.userId)

                            ProductReviewUi(
                                id = review.id,
                                productId = review.productId,
                                userName = userName,
                                userId = review.userId,
                                rating = review.rating,
                                comment = review.comment,
                                createdAt = review.createdAt,
                                adminReply = review.adminReply,
                                adminReplyAt = review.adminReplyAt
                            )
                        }
                }
        }
    }


    fun addReview(
        productId: Int,
        rating: Int,
        comment: String,
        onSuccess: () -> Unit = {}
    ) {
        if (rating !in 1..5) return
        if (comment.isBlank()) return

        viewModelScope.launch {

            val userId =
                supabaseClient.auth.currentUserOrNull()?.id

            if (userId.isNullOrBlank()) return@launch

            val review = ProductReview(
                id = 0L,
                productId = productId,
                userId = userId,
                rating = rating,
                comment = comment.trim(),
                createdAt = 0L
            )

            reviewRepository.addReview(review)

            val reviews =
                reviewRepository
                    .getReviews(productId)
                    .first()

            _reviews.value =
                reviews.map { savedReview ->

                    val userName =
                        profileRepository
                            .getFullNameByUserId(savedReview.userId)

                    ProductReviewUi(
                        id = savedReview.id,
                        productId = savedReview.productId,
                        userName = userName,
                        userId = savedReview.userId,
                        rating = savedReview.rating,
                        comment = savedReview.comment,
                        createdAt = savedReview.createdAt,
                        adminReply = savedReview.adminReply,
                        adminReplyAt = savedReview.adminReplyAt
                    )
                }

            onSuccess()
        }
    }
    fun updateReview(
        reviewId: Long,
        productId: Int,
        rating: Int,
        comment: String
    ) {
        if (rating !in 1..5) return
        if (comment.isBlank()) return

        viewModelScope.launch {

            reviewRepository.updateReview(
                reviewId = reviewId,
                rating = rating,
                comment = comment.trim()
            )

            refreshReviews(productId)
        }
    }

    fun deleteReview(
        reviewId: Long,
        productId: Int
    ) {
        viewModelScope.launch {

            reviewRepository.deleteReview(reviewId)

            refreshReviews(productId)
        }
    }
    private suspend fun refreshReviews(
        productId: Int
    ) {
        val reviews =
            reviewRepository
                .getReviews(productId)
                .first()

        _reviews.value =
            reviews.map { review ->

                val userName =
                    profileRepository
                        .getFullNameByUserId(review.userId)

                ProductReviewUi(
                    id = review.id,
                    productId = review.productId,
                    userName = userName,
                    userId = review.userId,
                    rating = review.rating,
                    comment = review.comment,
                    createdAt = review.createdAt,
                    adminReply = review.adminReply,
                    adminReplyAt = review.adminReplyAt
                )
            }
    }
    fun replyToReview(
        reviewId: Long,
        productId: Int,
        reply: String
    ) {
        if (reply.isBlank()) return

        viewModelScope.launch {

            reviewRepository.replyToReview(
                reviewId = reviewId,
                reply = reply.trim()
            )

            refreshReviews(productId)
        }
    }
}
