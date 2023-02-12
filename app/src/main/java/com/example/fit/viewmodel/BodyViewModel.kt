package com.example.fit.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.fit.dao.BodyDao
import com.example.fit.data.Body
import com.example.fit.database.BodyDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BodyViewModel @Inject constructor(application: Application):AndroidViewModel(application) {


    val lastMeasurement: Body?
    val allMeasurements:List<Body>
    private val bodyDao:BodyDao

    init {
        bodyDao = BodyDatabase.getDatabase(application,viewModelScope).bodyDao()
        lastMeasurement = bodyDao.get().lastOrNull()
        allMeasurements = bodyDao.get()

    }


    fun previous(): Body?{
        if (bodyDao.get().size > 1){
            return bodyDao.get()[bodyDao.get().size-2]
        }else{
            return null
        }
    }

    fun insert(body: Body){
        bodyDao.insert(body)
    }


    fun delete(){
        bodyDao.delete()
    }

}