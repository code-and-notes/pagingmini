package com.android.learning.pagingmini.network.data

import android.util.Log
import com.android.learning.pagingmini.db.data.ProductDimensions
import com.android.learning.pagingmini.db.entities.ProductDTO
import com.android.learning.pagingmini.db.entities.ProductMetaDTO
import com.android.learning.pagingmini.db.entities.ReviewDTO

data class Product(
    val id: Int,
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
    val dimensions: ProductDimensions,
    val warrantyInformation: String,
    val shippingInformation: String,
    val availabilityStatus: String,
    val reviews: List<ReviewDTO>?,
    val returnPolicy: String,
    val minimumOrderQuantity: Int,
    val meta: ProductMetaDTO,
    val images : List<String>,
    val thumbnail: String
)

fun Product.toProductDTO(): ProductDTO {
    return ProductDTO(
        id = this.id,
        title = this.title,
        description = this.description,
        category = this.category,
        price = this.price,
        discountPercentage = this.discountPercentage,
        rating = this.rating,
        stock = this.stock,
        tags = this.tags,
        brand = this.brand,
        sku = this.sku,
        weight = this.weight,
        dimensions = this.dimensions,
        warrantyInformation = this.warrantyInformation,
        shippingInformation = this.shippingInformation,
        availabilityStatus = this.availabilityStatus,
        returnPolicy = this.returnPolicy,
        minimumOrderQuantity = this.minimumOrderQuantity,
        images = this.images,
        thumbnail = this.thumbnail
    )
}

fun Product.toReviewDTOList(): List<ReviewDTO> {
    return this.reviews?: emptyList()
}

fun Product.toProductMetaDTO(): ProductMetaDTO {
    return this.meta
}
