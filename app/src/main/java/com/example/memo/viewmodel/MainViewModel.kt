package com.example.memo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.memo.model.dto.Dto
import com.example.memo.repository.Repository
import com.example.memo.repository.RepositoryImpl
import com.example.memo.usecase.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
        private val repositoryModule: RepositoryImpl
    ) : ViewModel() {
        private val _movieRepository = MutableLiveData<List<Dto>>()
        val movieRepository = _movieRepository

        fun requestRepositories(UseCase: UseCase) : LiveData{
            CoroutineScope(Dispatchers.IO).launch {
                val response = UseCase.getMovie("0a248ab8367333fba08f7bfade19fce4","targetDt")
            }
        }
    }
