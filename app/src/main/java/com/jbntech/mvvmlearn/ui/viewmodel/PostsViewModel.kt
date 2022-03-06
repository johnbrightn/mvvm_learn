package com.jbntech.mvvmlearn.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jbntech.mvvmlearn.model.PostsList
import com.jbntech.mvvmlearn.source.repository.RemoteRepository
import kotlinx.coroutines.launch
import retrofit2.Response

class PostsViewModel(private val repository: RemoteRepository): ViewModel() {

    //live data is not mutable
    //mutable life data is mutable

    private val _createPostResponse: MutableLiveData<Response<Any>> = MutableLiveData()
    val createPostResponse: LiveData<Response<Any>> = _createPostResponse;

    //create post method
    fun createPost(title: String, body: String, userId: Int){
        viewModelScope.launch {

            val response = repository.createPost(title, body, userId)
            _createPostResponse.value = response
        }
    }

    //fetch all posts
    private val _getPostsResponse: MutableLiveData<Response<List<PostsList>>> = MutableLiveData()
    val getPostsResponse: LiveData<Response<List<PostsList>>> = _getPostsResponse

    //get posts method
    fun getPosts(){
        viewModelScope.launch {
            val response = repository.getPosts()
            _getPostsResponse.value = response
        }
    }

}