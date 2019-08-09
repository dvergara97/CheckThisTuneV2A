package com.dverg.checkthistunev2a.tuner.model

import kotlin.math.abs

class TunerRepository {


    private var currentNote : Double = 440.0
    private var previousNote : Double = 440.0

    private val noteFrequencies : Array<Double> = arrayOf(220.0, 233.08, 246.94, 261.63, 277.18, 293.66, 311.13,
                                                            329.63, 349.23, 369.99, 392.00, 415.30, 440.0)
    private val noteNamesSharp = arrayOf("A", "A♯", "B", "C", "C♯", "D", "D♯", "E", "F", "F♯", "G", "G♯", "A")
    private val noteNamesFlat = arrayOf("A", "B♭", "B", "C", "D♭", "D", "E♭", "E", "F", "G♭", "G", "A♭  ", "A")

    private var showSharps = true


    fun setCurrentNote(newNote : Double) {
        previousNote = putFrequencyInRange(currentNote)
        currentNote = putFrequencyInRange(newNote)
    }

    fun getNoteName() : String {
        return if (showSharps) {
            noteNamesSharp[getClosestIndex(currentNote)]
        } else {
            noteNamesFlat[getClosestIndex(currentNote)]
        }
    }

    fun getPercentageChanged() : Double {
        return calculatePercentage(currentNote) - calculatePercentage(previousNote)
    }

    private fun putFrequencyInRange(frequency : Double) : Double {
        var outFrequency = frequency
        if (doubleEqual(outFrequency, noteFrequencies.first()) || doubleEqual(outFrequency, noteFrequencies.last())) {
            return frequency
        }
        while (frequency < noteFrequencies.first()) {
            outFrequency *= 2
        }
        while (frequency > noteFrequencies.last()) {
            outFrequency /= 2
        }
        return outFrequency
    }

    private fun getClosestIndex(inNote: Double) : Int {
        for (i in 0 until noteFrequencies.size) {
            if (doubleEqual(inNote, noteFrequencies[i])) {
                return i
            }
        }
        var index = -1

        for (i in 1 until noteFrequencies.size) {
            if (inNote < noteFrequencies[i] && index == -1) {
                index = i
            }
        }

        return if ((noteFrequencies[index] - inNote) < (inNote - noteFrequencies[index - 1])) {
            index
        }
        else {
            index - 1
        }
    }

    private fun calculatePercentage(frequency : Double) : Double {
        val index = getClosestIndex(frequency)
        if (doubleEqual(frequency, noteFrequencies[index])) {
            return 0.0
        }
        return if (frequency < noteFrequencies[index]) {
            val totalDistance = (noteFrequencies[index] - noteFrequencies[index - 1]) / 2
            val calcDistance = (noteFrequencies[index] - frequency)
            calcDistance / totalDistance
        } else {
            val totalDistance = (noteFrequencies[index + 1] - noteFrequencies[index]) / 2
            val calcDistance = (frequency - noteFrequencies[index])
            - (calcDistance / totalDistance)
        }
    }

    private fun doubleEqual(num1 : Double, num2 : Double) : Boolean {
        return abs(num1 - num2) < PRECISION
    }

    companion object {

        private const val PRECISION = 10E-8

    }

}