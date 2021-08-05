package com.graduation.softskillsmeter.networking

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    companion object{
        fun getRetrofit() : Retrofit {

            val baseUrl = "http://207.154.220.88:5000/"

            val httpOk = OkHttpClient.Builder()

            httpOk.addInterceptor { chain ->
                val request = chain.request().newBuilder().build()

                chain.proceed(request)
            }

            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpOk.build())
                .build()
        }
    }
}