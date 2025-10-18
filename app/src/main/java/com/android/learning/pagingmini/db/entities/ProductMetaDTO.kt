package com.android.learning.pagingmini.db.entities

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "product_meta",
    foreignKeys = [
        ForeignKey(
            entity = ProductDTO::class,
            parentColumns = ["id"],
            childColumns = ["productId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("productId")]
)
data class ProductMetaDTO(
    @PrimaryKey
    val productId: Int,
    val createdAt: String,
    val updatedAt: String,
    val barcode: String,
    val qrCode: String
)