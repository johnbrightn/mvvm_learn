package com.jbntech.mvvmlearn.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.jbntech.mvvmlearn.R
import com.jbntech.mvvmlearn.source.repository.RemoteRepository
import com.jbntech.mvvmlearn.ui.viewmodel.PostsViewModel
import com.jbntech.mvvmlearn.utils.PostsViewModelFactory


class CreatePostFragment : Fragment() {
    private lateinit var layout_txtTitle: TextInputLayout
    private lateinit var layout_txtBody: TextInputLayout
    private lateinit var txtTitle: TextInputEditText
    private lateinit var txtBody: TextInputEditText
    private lateinit var btnCratePost: MaterialButton

    lateinit var viewModel: PostsViewModel
    lateinit var repository: RemoteRepository

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_create_post, container, false)

        repository = RemoteRepository()
        val viewModelFactory = PostsViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(PostsViewModel::class.java)

        layout_txtTitle = view.findViewById(R.id.layout_txtTitle)
        layout_txtBody = view.findViewById(R.id.layout_txtBody)
        txtTitle = view.findViewById(R.id.txtTitle)
        txtBody = view.findViewById(R.id.txtBody)
        btnCratePost = view.findViewById(R.id.btnCreatePost)

        btnCratePost.setOnClickListener { view ->

            createPost()
        }

        return view;
    }



    private fun createPost(){

        val title = txtTitle.text.toString()
        val body = txtBody.text.toString()
        if(validatefields(title, body))
            return


        viewModel.createPost(title, body, 30)
        viewModel.createPostResponse.observe(viewLifecycleOwner, { response ->

            Log.d("CREATE POST", response.body().toString())
            if(response.isSuccessful){
                findNavController().navigate(R.id.action_createPostFragment_to_postListFragment)
            }
        })
    }


    private fun validatefields(title:String, body:String): Boolean{
        layout_txtTitle.isErrorEnabled =  false;
        layout_txtBody.isErrorEnabled = false;

        if(title.isEmpty()){
            layout_txtTitle.isErrorEnabled =  true
            layout_txtTitle.error = "Title Required"
            return true
        }

        if(body.isEmpty()){
            layout_txtBody.isErrorEnabled = true;
            layout_txtTitle.error = "Body Required"
            return true
        }

        return false
    }

}