package com.dverg.checkthistunev2a.tuner.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dverg.checkthistunev2a.tuner.model.TunerRepository


class TunerViewModel(private val tunerRepository : TunerRepository) : ViewModel() {

    val currentNote : MutableLiveData<String> = MutableLiveData()
    val percentageChanged : MutableLiveData<Double> = MutableLiveData()

    init {
        currentNote.postValue(tunerRepository.getNoteName())
        percentageChanged.postValue(tunerRepository.getPercentageChanged())
    }

    fun communicateNewNote(newNote : Double) {
        tunerRepository.setCurrentNote(newNote)
    }

}