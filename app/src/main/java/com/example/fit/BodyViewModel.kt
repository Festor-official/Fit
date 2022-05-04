package com.example.fit

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope

class BodyViewModel(application: Application):AndroidViewModel(application) {

    private val repository:BodyRepository
    val lastMeasurement:Body?
    val allMesurments:List<Body>

    init {
        val bodyDao = BodyDatabase.getDatabase(application,viewModelScope).bodyDao()
        repository = BodyRepository(bodyDao)
        lastMeasurement = repository.getLast
        allMesurments = repository.get

    }

    fun insert(body:Body){
        repository.insert(body)
    }

    fun previous():Body?{
        return repository.previous()
    }

    fun delete(){
        repository.delete()
    }

}