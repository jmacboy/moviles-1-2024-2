package com.example.practicacalculadora

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {
    private val _result = MutableLiveData<String>().apply {
        value = ""
    }
    val result: LiveData<String> = _result

    private val _errorMessage = MutableLiveData<String>().apply {
        value = ""
    }
    val errorMessage: LiveData<String> = _errorMessage

    private var currentOperation: OperationType = OperationType.NONE
    private var prevNumber = 0
    private var lastOpNumber = 0
    private var repeatSameOperation = false

    fun appendNumber(number: Int) {
        _errorMessage.value = ""
        _result.value += number.toString()
    }
    fun startOperation(opType: OperationType) {
        _errorMessage.value = ""
        repeatSameOperation = false
        if (_result.value?.isEmpty() == true) {
            prevNumber = 0
        } else {
            prevNumber = _result.value?.toInt() ?: 0
        }
        currentOperation = opType
        _result.value = ""
    }

    fun performOperation() {
        var currentNumber = 0
        if (_result.value?.isNotEmpty() == true) {
            currentNumber = _result.value!!.toInt()
        }
        if (repeatSameOperation) {
            currentNumber = lastOpNumber
        }
        var operationResult = 0
        when (currentOperation) {
            OperationType.ADDITION -> operationResult = prevNumber + currentNumber
            OperationType.SUBTRACTION -> operationResult = prevNumber - currentNumber
            OperationType.MULTIPLICATION -> operationResult = prevNumber * currentNumber
            OperationType.DIVISION -> {
                if (currentNumber == 0) {
                    _errorMessage.value = "Error"
                    return
                }
                operationResult = prevNumber / currentNumber
            }

            OperationType.NONE -> {}
        }
        if (!repeatSameOperation) {
            lastOpNumber = _result.value?.toInt() ?: 0
        }
        _result.value = operationResult.toString()
        prevNumber = _result.value!!.toInt()

        repeatSameOperation = true
    }

    fun clearEverything() {
        _errorMessage.value = ""
        repeatSameOperation = false
        _result.value = ""
    }

    fun clearOne() {
        _errorMessage.value = ""
        repeatSameOperation = false
        if (_result.value?.isEmpty() == true)
            return
        _result.value = _result.value?.substring(0, (_result.value?.length ?: 0) - 1)
    }
}