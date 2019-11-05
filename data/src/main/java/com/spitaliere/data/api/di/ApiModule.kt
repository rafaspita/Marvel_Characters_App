package com.spitaliere.data.api.di

import com.spitaliere.data.api.MarvelApi
import com.spitaliere.domain.platform.extension.md5
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.math.BigInteger
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import java.util.concurrent.TimeUnit

/**
 * Created by Rafael Spitaliere on 02/11/2019.
 **/
object ApiModule {

    private const val BASE_URL = "https://gateway.marvel.com/v1/public/"
    private const val PUBLIC_KEY = ""
    private const val PRIVATE_KEY = ""

    fun getModule() = module {
        single { createWebService<MarvelApi>(url = BASE_URL) }
    }

    private fun providesOkHttpClient(): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(httpLoggingInterceptor)
        httpClient.addInterceptor{
            val original = it.request()
            val originalHttpUrl = original.url

            val ts = System.currentTimeMillis().toString()
            val url = originalHttpUrl.newBuilder()
                .addQueryParameter("apikey", PUBLIC_KEY)
                .addQueryParameter("ts", ts)
                .addQueryParameter("hash", "$ts$PRIVATE_KEY$PUBLIC_KEY".md5())
                .build()

            it.proceed(original.newBuilder().url(url).build())
        }
        httpClient.connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)

        return httpClient.build()
    }

    private inline fun <reified T> createWebService(url: String): T = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(url)
            .client(providesOkHttpClient())
            .build()
            .create(T::class.java)
}