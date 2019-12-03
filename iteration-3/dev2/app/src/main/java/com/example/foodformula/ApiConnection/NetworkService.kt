package com.example.foodformula.ApiConnection

import okhttp3.Interceptor
import okhttp3.Interceptor.Companion.invoke
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object NetworkService {

    private const val BASE_URL = ApiParametrs.BASE_URL

    private val loggingInterceptor by lazy {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        }
    }

    private val baseInterceptor: Interceptor by lazy {
        invoke { chain ->
        val newUrl = chain
            .request()
            .url
            .newBuilder()
            .addQueryParameter(ApiParametrs.BASE_API_KEY, ApiParametrs.BASE_API_VALUE)
            .build()

        val request = chain
            .request()
            .newBuilder()
            .url(newUrl)
            .build()

        return@invoke chain.proceed(request)
    }
    }

    private val client: OkHttpClient by lazy {
        OkHttpClient
        .Builder()
        .addInterceptor(loggingInterceptor)
        .addInterceptor(baseInterceptor)
        .build()
    }

    fun retrofitService(): ApiFunctions = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()
        .create(ApiFunctions::class.java)

}