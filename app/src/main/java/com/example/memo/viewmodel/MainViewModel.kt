package com.example.memo.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.*
import com.example.memo.ApiResult
import com.example.memo.model.dto.Dto
import com.example.memo.model.dto.MovieResponse
import com.example.memo.repository.Repository
import com.example.memo.repository.RepositoryImpl
import com.example.memo.ui.MainActivity
import com.example.memo.usecase.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
        private val useCase: UseCase
    ) : ViewModel() {
    val movieRepository: LiveData<ApiResult<List<Dto>>> get()= _movieRepository
    private val _movieRepository = MutableLiveData<ApiResult<List<Dto>>>()
    fun getMovie() {
        viewModelScope.launch{
            _movieRepository.postValue(ApiResult.loading())
            val result = useCase.getMovie("0a248ab8367333fba08f7bfade19fce4","targetDt")
            _movieRepository.postValue(ApiResult<result>)
        }
    }
}
