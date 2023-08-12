package com.sujeet.test.data.remote

import com.sujeet.test.model.ProductListResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiClient {
    @GET("/products")
    suspend fun getProduct() : Call<ProductListResponse>
}