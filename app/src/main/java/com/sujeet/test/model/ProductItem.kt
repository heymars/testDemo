package com.sujeet.test.model

data class ProductItem(
    val id: String,
    val thumbnail: String,
    val title: String,
    val price: Double,
    val rating: Double,
)

data class ProductListResponse (
    val products : List<ProductItem>?,
    val msg: String?
)
