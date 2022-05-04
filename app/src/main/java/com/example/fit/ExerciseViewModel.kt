package com.example.fit

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope

class ExerciseViewModel(application: Application):AndroidViewModel(application) {
    private val repository:ExerciseRepository
    val allExercise: List<Exercise>

    init {
        val categoryDao = ExerciseDatabase.getDatabase(application, viewModelScope).exerciseDao()
        repository = ExerciseRepository(categoryDao)
        allExercise = repository.get
    }

    fun getLastRepeat(id:Int):Repeat{
        return repository.getLastRepeat(id)
    }

    fun insert(exercise: Exercise){
        repository.insert(exercise)
    }

    fun addRepeat(id:Int,set:Int,amount:Int,sum:Int,weight:Int){
        repository.addRepeat(id,set,amount,sum,weight)
    }

    fun maxFromAmountRepeat(id:Int,amount:Int):Repeat{
       return repository.maxFromAmountRepeat(id,amount)
    }

    fun max(id:Int):Repeat{
        return repository.max(id)
    }

    fun delete(){
        repository.delete()
    }


}