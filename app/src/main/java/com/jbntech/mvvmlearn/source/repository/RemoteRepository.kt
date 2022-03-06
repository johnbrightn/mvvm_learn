package com.jbntech.mvvmlearn.source.repository

import com.jbntech.mvvmlearn.api.RetrofitInstance
import com.jbntech.mvvmlearn.model.PostsList
import retrofit2.Response

class RemoteRepository {

    //in repository, you can check and return data from a local store or remote store
    //all those logics are done here
    //create a post
    suspend fun createPost(title: String, body:String, userId: Int): Response<Any>{
        return RetrofitInstance.api.createPost(title, body, userId)
    }

    //get posts
    suspend fun getPosts(): Response<List<PostsList>>{
        return RetrofitInstance.api.getPosts()
    }


}