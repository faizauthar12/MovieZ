package io.faizauthar12.moviez.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.faizauthar12.moviez.data.entities.ShowEntity
import io.faizauthar12.moviez.data.source.MovieZRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class DetailShowViewModel(private val movieZRepository: MovieZRepository) : ViewModel(){

    fun insertFavorite(data: ShowEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            movieZRepository.insertFavorite(data)
        }
    }

    fun deleteFavorite(data: ShowEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            movieZRepository.deleteFavorite(data)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}