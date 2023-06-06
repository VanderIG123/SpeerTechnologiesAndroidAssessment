package com.ST.speertechnologiesandroidassessment.network

import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitService {
     const val baseUrl = "https://api.github.com/"

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .client(getGlobalHeaderConfig())
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    private fun getGlobalHeaderConfig(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor { chain ->
            val newRequest: Request = chain.request().newBuilder()
                .addHeader("Accept", "application/vnd.github+json")
                .addHeader("Authorization", "Bearer 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")
                .addHeader("X-GitHub-Api-Version", "2022-11-28")
                .build()
            chain.proceed(newRequest)
        }.build()
    }
}