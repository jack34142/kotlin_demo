package com.jyun_yi.kotlin_demo.http

import android.util.Log
import com.google.gson.Gson
import com.jyun_yi.kotlin_demo.configs.MyConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

abstract class BaseHttp<T>(baseUrl: String, serviceClass: Class<T>) {
    protected val service: T

    init {
        val clientBuilder = OkHttpClient.Builder()
        if(MyConfig.DEBUG){
            val interceptor = HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.HEADERS)
            clientBuilder.addInterceptor(interceptor)
        }

        clientBuilder.connectTimeout(60, TimeUnit.SECONDS)
        val retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(clientBuilder.build())
            .build()
        service = retrofit.create(serviceClass)
    }
}

abstract class MyCallback<T>: Callback<T> {

    abstract fun onSuccess(data: T)
    abstract fun onFailed(e: String)

    companion object {
        const val TAG = "MyCallback"
    }

    override fun onResponse(call: Call<T>, response: Response<T>) {
        val body = response.body()
        if(body != null){
            if(response.code() == 200){
                Log.d(TAG, Gson().toJson(body))
                onSuccess(body)
            }else{
                onFailed(body.toString())
            }
        }else{
            onFailed(response.message())
        }
    }

    override fun onFailure(call: Call<T>, e: Throwable) {
        onFailed(e.toString())
    }
}