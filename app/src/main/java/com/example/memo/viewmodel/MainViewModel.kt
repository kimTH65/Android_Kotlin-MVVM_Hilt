package com.example.memo.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.memo.model.dto.Dto
import com.example.memo.repository.RepositoryModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class MainViewModel(private val repositoryModule: RepositoryModule) : ViewModel() {
    private val _movieRepository = MutableLiveData<List<Dto>>()
    val movieRepository = _movieRepository
    fun requestRepositories(key: String,targetDt: String) {
        CoroutineScope(Dispatchers.IO).launch {
            repositoryModule.provideRepository(key,targetDt)?.let { response ->
                if(response.isSuccessful) {
                    
                    response.body()?.let {
                        _movieRepository.postValue(it.items)
                    }
                }
            }
        }
    }
}
