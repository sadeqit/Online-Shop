package io.github.sadeghi.online_shop.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.sadeghi.online_shop.data.repository.IProductReviewRepository
import io.github.sadeghi.online_shop.data.repository.IProfileRepository
import io.github.sadeghi.online_shop.data.repository.IAuthRepository
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
    private val authRepository: IAuthRepository
) : ViewModel()
{

    private val _reviews =
        MutableStateFlow<List<ProductReviewUi>>(emptyList())

    val reviews: StateFlow<List<ProductReviewUi>> =
        _reviews.asStateFlow()

    private var currentUserId: String? = null

    fun loadReviewsForProducts(productIds: List<Int>) {
        viewModelScope.launch {

            val allReviews = mutableListOf<ProductReviewUi>()

            productIds.distinct().forEach { productId ->

                val reviews = reviewRepository
                    .getReviews(productId)
                    .first()

                val userName =
                    profileRepository
                        .getFullName()
                        .first()

                allReviews += reviews.map { review ->

                    ProductReviewUi(
                        id = review.id,
                        productId = review.productId,
                        userName = userName,
                        rating = review.rating,
                        comment = review.comment,
                        createdAt = review.createdAt
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

                    val userName =
                        profileRepository
                            .getFullName()
                            .first()

                    _reviews.value =
                        reviews.map { review ->

                            ProductReviewUi(
                                id = review.id,
                                productId = review.productId,
                                userName = userName,
                                rating = review.rating,
                                comment = review.comment,
                                createdAt = review.createdAt
                            )
                        }
                }
        }
    }

    fun loadCurrentUser() {

        viewModelScope.launch {

            currentUserId =
                authRepository
                    .getUserEmail()
                    .first()
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
                authRepository
                    .getUserEmail()
                    .first()

            if (userId.isNullOrBlank()) return@launch

            val review = ProductReview(
                id = System.currentTimeMillis(),
                productId = productId,
                userId = userId,
                rating = rating,
                comment = comment.trim(),
                createdAt = System.currentTimeMillis()
            )

            reviewRepository.addReview(review)

            onSuccess()
        }
    }
}