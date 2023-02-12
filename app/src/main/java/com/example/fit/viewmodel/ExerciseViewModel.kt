package com.example.fit.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.fit.dao.ExerciseDao
import com.example.fit.data.Exercise
import com.example.fit.data.Repeat
import com.example.fit.database.ExerciseDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ExerciseViewModel @Inject constructor(application: Application):AndroidViewModel(application) {

    val allExercise: List<Exercise>
    private val exerciseDao:ExerciseDao
    init {
        exerciseDao = ExerciseDatabase.getDatabase(application,viewModelScope).exerciseDao()
        allExercise = exerciseDao.get()
    }

    fun getLastRepeat(id:Int): Repeat? {
        val lastRepeat = exerciseDao.getId(id).exercise_list
        return if(lastRepeat.isEmpty())
                    null
                else
                    lastRepeat.last()

    }

//    fun getLastId():Int{
//        return repository.getLastId()
//    }

    fun insert(exercise: Exercise){
        exerciseDao.insert(exercise)
    }

    fun addRepeat(id:Int,set:Int,amount:Int,weight:Float,sum:Int){
        var exerciseRepeat = exerciseDao.getId(id)
        val exerciseList = exerciseDao.getId(id).exercise_list


        if(exerciseList.isEmpty() || set > exerciseList.last().set){
            exerciseRepeat.exercise_list.add(Repeat(set, arrayListOf<Int>(amount),weight,sum))
            exerciseDao.update(exerciseRepeat)

        }
        else{
            val currentSet = exerciseList.last()
            currentSet.amount.add(amount)
            currentSet.sum = sum
            exerciseRepeat.exercise_list[exerciseList.size-1] = currentSet
            exerciseDao.update(exerciseRepeat)
            exerciseRepeat = exerciseDao.getId(id)
            Log.v("MainActivity",exerciseRepeat.toString())
        }

    }

    fun maxFromAmountRepeat(id:Int,set:Int,amount:Int,sum:Int):Repeat{
        val maxList = exerciseDao.getId(id).exercise_list
        var max = 0
        var repeatIndex = Repeat(0, arrayListOf<Int>(),0f,0)
        for(repeat in maxList){

            if(repeat.amount.size-1 >= amount){
                val listAmount = repeat.amount.subList(0,amount)
                val number = listAmount.sum()
                if(max<number){
                    max = number
                    repeatIndex = repeat
                }
            }
        }

        return repeatIndex
    }



//    fun max(id:Int): Repeat {
//        val maxList = exerciseDao.getId(id).exercise_list
//        var number = 0
//        return repository.max(id)
//    }

    fun delete(){
        exerciseDao.delete()
    }


}