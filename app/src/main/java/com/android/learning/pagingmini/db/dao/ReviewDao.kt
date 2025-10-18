package com.android.learning.pagingmini.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.android.learning.pagingmini.db.entities.ReviewDTO
import kotlinx.coroutines.flow.Flow

@Dao
interface ReviewDao {

    @Insert
    suspend fun insertReviews(reviewDTOS: List<ReviewDTO>)

    @Insert
    suspend fun insertReview(reviewDTO: ReviewDTO)

    @Query("SELECT * FROM reviews WHERE productId = :productId")
    fun getReviewsByProductId(productId: Int): Flow<List<ReviewDTO>>

}