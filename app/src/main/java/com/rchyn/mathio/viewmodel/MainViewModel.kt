package com.rchyn.mathio.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlin.math.pow

class MainViewModel : ViewModel() {

    private val _areaOfRectangle = MutableLiveData<Double?>(null)
    val areaOfRectangle: LiveData<Double?> = _areaOfRectangle

    private val _areaOfSquare = MutableLiveData<Double?>(null)
    val areaOfSquare: LiveData<Double?> = _areaOfSquare

    private val _areaOfTriangle = MutableLiveData<Double?>(null)
    val areaOfTriangle: LiveData<Double?> = _areaOfTriangle

    private val _areaOfCircle = MutableLiveData<Double?>(null)
    val areaOfCircle: LiveData<Double?> = _areaOfCircle

    private val _areaOfParallelogram = MutableLiveData<Double?>(null)
    val areaOfParallelogram: LiveData<Double?> = _areaOfParallelogram

    private val _areaOfRhombus = MutableLiveData<Double?>(null)
    val areaOfRhombus: LiveData<Double?> = _areaOfRhombus


    fun calculateRectangle(length: String, width: String) {
        val l = length.toDouble()
        val w = width.toDouble()
        _areaOfRectangle.value = l * w
    }

    fun calculateSquare(side: String) {
        val s = side.toDouble()
        _areaOfSquare.value = s.pow(2)
    }

    fun calculateTriangle(base: String, height: String) {
        val b = base.toDouble()
        val h = height.toDouble()
        _areaOfTriangle.value = (b * h).div(2)
    }

    fun calculateCircle(radius: String) {
        val r = radius.toDouble()
        _areaOfCircle.value = Math.PI * r.pow(2)
    }

    fun calculateParallelogram(base: String, height: String) {
        val b = base.toDouble()
        val h = height.toDouble()
        _areaOfParallelogram.value = b * h
    }

    fun calculateRhombus(diagonalOne: String, diagonalTwo: String) {
        val d1 = diagonalOne.toDouble()
        val d2 = diagonalTwo.toDouble()
        _areaOfRhombus.value = (d1 * d2).div(2)
    }

    fun resetCalculate() {
        _areaOfRectangle.value = null
        _areaOfSquare.value = null
        _areaOfTriangle.value = null
        _areaOfCircle.value = null
        _areaOfParallelogram.value = null
        _areaOfRhombus.value = null
    }

}