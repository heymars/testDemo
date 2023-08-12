package com.sujeet.test.ui.product

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.GsonBuilder
import com.sujeet.test.data.remote.ApiClient
import com.sujeet.test.model.ProductListResponse
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProductViewModel : ViewModel() {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://dummyjson.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val apiClient: ApiClient = retrofit.create(ApiClient::class.java)
    val productListMutableLiveData = MutableLiveData<ProductListResponse>()

    @OptIn(DelicateCoroutinesApi::class)
    fun getProductList() {
      GlobalScope.launch {
          try {
              val response = apiClient.getProduct().execute()
              if (response.isSuccessful) {
                  productListMutableLiveData.postValue(response.body())
              } else {
                  productListMutableLiveData.postValue(ProductListResponse(products = null, msg = "Something went wrong"))
              }
          } catch (e: Exception) {
              productListMutableLiveData.postValue(ProductListResponse(products = null, msg = e.toString()))
          }
      }
    }
}