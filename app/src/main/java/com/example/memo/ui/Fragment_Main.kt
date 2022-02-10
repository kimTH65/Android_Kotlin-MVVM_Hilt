package com.example.memo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.memo.RecyclerViewAdapter
import com.example.memo.databinding.FragmentMainBinding
import com.example.memo.model.RtrofitApi
import com.example.memo.model.dto.MovieResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Fragment_Main : Fragment() {
    lateinit var home_activity: MainActivity
    private var mBinding: FragmentMainBinding? = null
    private val binding get() = mBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentMainBinding.inflate(inflater, container, false)
        home_activity = context as MainActivity

        var mAdapter : RecyclerViewAdapter
        RtrofitApi.api
            .getBoxOffice("0a248ab8367333fba08f7bfade19fce4","targetDt" )
                .enqueue(object : Callback<MovieResponse> {
                    override fun onFailure(call: Call<MovieResponse>, t: Throwable){

                    }

                    override fun onResponse(
                        call: Call<MovieResponse>,
                        response: Response<MovieResponse>
                    ) {
                        val movieList = response.body()
                        mAdapter = RecyclerViewAdapter(home_activity, movieList)
                    }
                })


        val LinearManager = LinearLayoutManager(home_activity)
        LinearManager.reverseLayout = true
        LinearManager.stackFromEnd = true

        binding.recyclerview.apply {
            adapter = mAdapter
            layoutManager = LinearManager

        }
        /*viewModel.allUsers.observe(this, Observer { users ->
            // Update the cached copy of the users in the adapter.
            users?.let { mAdapter.setUsers(it) }
        })*/

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}