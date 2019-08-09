package com.dverg.checkthistunev2a.tuner.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dverg.checkthistunev2a.tuner.model.TunerRepository


class TunerViewModelFactory(private val repository: TunerRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>) = TunerViewModel(repository) as T

}