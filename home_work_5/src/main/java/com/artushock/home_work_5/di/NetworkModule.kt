package com.artushock.home_work_5.di

import com.artushock.home_work_5.data.retrofit.GitHubApi
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.Reusable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @Provides
    fun gson(): Gson = GsonBuilder().create()

    @Reusable
    @Provides
    fun gitHubApi(gson: Gson): GitHubApi = Retrofit.Builder()
        .baseUrl("https://api.github.com")
        .client(
            OkHttpClient.Builder()
                .addNetworkInterceptor(
                    HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    }
                )
                .build()
        )
        .addCallAdapterFactory(RxJava3CallAdapterFactory.createSynchronous())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
        .create(GitHubApi::class.java)

}