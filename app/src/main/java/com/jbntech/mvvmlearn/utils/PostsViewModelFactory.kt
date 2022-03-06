package com.jbntech.mvvmlearn.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jbntech.mvvmlearn.source.repository.RemoteRepository
import com.jbntech.mvvmlearn.ui.viewmodel.PostsViewModel

class PostsViewModelFactory(private val repository: RemoteRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        return PostsViewModel(repository) as T
    }
}