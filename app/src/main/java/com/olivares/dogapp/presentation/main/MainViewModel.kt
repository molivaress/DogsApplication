package com.olivares.dogapp.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.olivares.dogapp.R
import com.olivares.dogapp.domain.DefaultUseCaseExecutor
import com.olivares.dogapp.domain.GetDogsUseCase
import com.olivares.dogapp.domain.UseCaseExecutor
import javax.inject.Inject

class MainViewModel @Inject constructor(
    useCase: GetDogsUseCase
) : ViewModel(), UseCaseExecutor by DefaultUseCaseExecutor() {
    private val _mainStateView = MutableLiveData<MainStateView>()
    val mainStateView: LiveData<MainStateView> = _mainStateView

    init {
        useCase(viewModelScope, Unit) {
            it.onSuccess { dogs ->
                _mainStateView.value = MainStateView(dogs = dogs)
            }.onFailure {
                _mainStateView.value = MainStateView(error = R.string.message_error)
            }
        }
    }
}