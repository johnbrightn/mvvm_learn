package com.jbntech.mvvmlearn.api

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Interceptor to intercept requests to help add headers before sending requests
 */
class RetrofitInterceptor: Interceptor {

    //this
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
            .newBuilder()
//            .addHeader()
            .build();


        return chain.proceed(request)
    }
}