package com.dverg.checkthistunev2a.tuner.model

import org.junit.Test

import org.junit.Assert.*

class TunerRepositoryTests {

    @Test
    fun testRepositoryInitialization() {
        val repository = TunerRepository()
        assertEquals(repository.getNoteName(), "A")
        assertEquals(0.0, repository.getPercentageChanged(),  10E-8)
    }

    @Test
    fun testRepositoryOnLowest() {
        val repository = TunerRepository()
        repository.setCurrentNote(220.0)
        assertEquals(repository.getNoteName(), "A")
        assertEquals(0.0, repository.getPercentageChanged(),  10E-8)
    }

    @Test
    fun testRepositoryAboveHighest() {
        val repository = TunerRepository()
        repository.setCurrentNote(440.0 + .01)
        assertEquals(repository.getNoteName(), "A")
    }

    @Test
    fun testRepositoryBelowLowest() {
        val repository = TunerRepository()
        repository.setCurrentNote(220.0 - .01)
        assertEquals(repository.getNoteName(), "A")
    }

    @Test
    fun testPercentageChangedFlat() {
        val repository = TunerRepository()

        // Value is the midpoint of Ab and A
        repository.setCurrentNote(433.825)
        assertEquals(repository.getNoteName(), "A")
        assertEquals(-0.5, repository.getPercentageChanged(),  10E-8)
    }

    @Test
    fun testPercentageChangedSharp() {
        val repository = TunerRepository()

        // Value is the midpoint between A and Bb (approximately)
        repository.setCurrentNote(223.269999999)
        assertEquals(repository.getNoteName(), "A")
        assertEquals(0.5, repository.getPercentageChanged(),  10E-8)
    }

    @Test
    fun testPercentageChangedFlatMultiple() {
        val repository = TunerRepository()

        // Value is the midpoint of Ab and A
        repository.setCurrentNote(433.825)
        assertEquals(repository.getNoteName(), "A")
        assertEquals(-0.5, repository.getPercentageChanged(),  10E-8)

        // Moved 25 percent sharp, toward the proper note.  So -.5 + .25 would be a .25% flat
        // 436.9125 is 25% flat
        repository.setCurrentNote(436.9125)
        assertEquals(repository.getNoteName(), "A")
        assertEquals(0.25, repository.getPercentageChanged(),  10E-8)
    }

    @Test
    fun testPercentageChangedSharpMultiple() {
        val repository = TunerRepository()

        // Value is the midpoint between A and Bb (approximately)
        repository.setCurrentNote(223.269999999)
        assertEquals(repository.getNoteName(), "A")
        assertEquals(0.5, repository.getPercentageChanged(),  10E-8)

        // Moved 25 percent flat, toward the proper note.  So .5 + -.25 would be a .25% sharp
        // 221.635 is 25% sharp
        repository.setCurrentNote(221.635)
        assertEquals(repository.getNoteName(), "A")
        assertEquals(-0.25, repository.getPercentageChanged(), 10E-8)
    }

}