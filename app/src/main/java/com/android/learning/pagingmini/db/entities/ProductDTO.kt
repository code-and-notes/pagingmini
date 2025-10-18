package com.android.learning.pagingmini.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Embedded
import com.android.learning.pagingmini.db.data.ProductDimensions


@Entity(tableName = "products")
data class ProductDTO(
    @PrimaryKey
    val id: Int = 0,
    val title: String,
    val description: String,
    val category: String,
    val price: Double,
    val discountPercentage: Double,
    val rating: Double,
    val stock: Int,
    val tags : List<String>,
    val brand: String?,
    val sku: String,
    val weight: Int,
    @Embedded val dimensions: ProductDimensions,
    val warrantyInformation: String,
    val shippingInformation: String,
    val availabilityStatus: String,
    val returnPolicy: String,
    val minimumOrderQuantity: Int,
    val images : List<String>,
    val thumbnail: String
)