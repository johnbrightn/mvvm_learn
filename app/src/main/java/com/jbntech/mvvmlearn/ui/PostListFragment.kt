package com.jbntech.mvvmlearn.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.jbntech.mvvmlearn.R
import com.jbntech.mvvmlearn.adapter.PostListAdapter
import com.jbntech.mvvmlearn.source.repository.RemoteRepository
import com.jbntech.mvvmlearn.ui.viewmodel.PostsViewModel
import com.jbntech.mvvmlearn.utils.PostsViewModelFactory


class PostListFragment : Fragment() {

    lateinit var recyclerView: RecyclerView
    val postListAdapter by lazy { PostListAdapter() }
    lateinit var viewModel: PostsViewModel
    lateinit var repository: RemoteRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_post_list, container, false)

        repository = RemoteRepository()

        val viewModelFactory =  PostsViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(PostsViewModel::class.java)

        recyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = postListAdapter

        getPostList()

        return view;
    }

    private fun getPostList(){

        viewModel.getPosts()
        viewModel.getPostsResponse.observe(viewLifecycleOwner, {response ->

            if(response.isSuccessful){
                Log.d("POSTS", response.body().toString())
//                response.body()?.let { postListAdapter.setData(it) }

                response?.body()?.let { postListAdapter.setData(it) }

            }
        })

    }


}