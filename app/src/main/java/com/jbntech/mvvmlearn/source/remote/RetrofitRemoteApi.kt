package com.jbntech.mvvmlearn.source.remote

import com.jbntech.mvvmlearn.model.DataResponse
import com.jbntech.mvvmlearn.model.PostsList
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface RetrofitRemoteApi {

    @FormUrlEncoded
    @POST("posts")
    suspend fun createPost(
        @Field("title") title: String,
        @Field("body") body: String,
        @Field("userId") userId: Int
    ): Response<Any>

    @GET("posts")
    suspend fun getPosts(): Response<List<PostsList>>
}