package com.ST.speertechnologiesandroidassessment.di

import com.ST.speertechnologiesandroidassessment.network.RetrofitService
import com.ST.speertechnologiesandroidassessment.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {


    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(RetrofitService.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor { chain ->
            val newRequest: Request = chain.request().newBuilder()
                .addHeader("Accept", "application/vnd.github+json")
                .addHeader("Authorization", "Bearer ${Constants.GITUB_API_KEY}")
                .addHeader("X-GitHub-Api-Version", "2022-11-28")
                .build()
            chain.proceed(newRequest)
        }.build()
    }
}