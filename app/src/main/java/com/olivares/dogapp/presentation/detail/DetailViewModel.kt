package com.olivares.dogapp.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.olivares.dogapp.R
import com.olivares.dogapp.domain.DefaultUseCaseExecutor
import com.olivares.dogapp.domain.GetImagesByBreedUseCase
import com.olivares.dogapp.domain.UseCaseExecutor
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val useCase: GetImagesByBreedUseCase
) : ViewModel(), UseCaseExecutor by DefaultUseCaseExecutor() {
    private val _detailStateView = MutableLiveData<DetailStateView>()
    val detailStateView: LiveData<DetailStateView> = _detailStateView

    fun loadImages(name: String) {
        useCase(viewModelScope, name) {
            it.onSuccess { images ->
                _detailStateView.value = DetailStateView(images = images)
            }.onFailure {
                _detailStateView.value = DetailStateView(error = R.string.message_error)
            }
        }
    }
}