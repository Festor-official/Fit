package com.example.fit

import android.util.Log

class ExerciseRepository(private val exerciseDao: ExerciseDao) {

    val get = exerciseDao.get()

    fun insert(exercise: Exercise){
        exerciseDao.insert(exercise)
    }

    fun getLastRepeat(id:Int):Repeat{
        val lastRepeat = exerciseDao.getId(id).exercise_list
        Log.v("MainActivity","$lastRepeat")
        return lastRepeat.last()
    }

    fun max(id:Int):Repeat{
        val maxList = exerciseDao.getId(id).exercise_list
        var number = 0
        var weight = 0
        var repeatMaximum = Repeat(0, arrayListOf<Int>(),0,0)
        for(i in maxList){
            if(i.sum >number && i.weight >= weight){
                weight = i.weight
                number = i.sum
                repeatMaximum = i
            }
        }
        return repeatMaximum

    }

    fun maxFromAmountRepeat(id:Int,amount:Int):Repeat{
        val maxList = exerciseDao.getId(id).exercise_list
        var  max= 0
        var repeatIndex = Repeat(0, arrayListOf<Int>(),0,0)
        for(i in maxList){
            if (i.amount.size-1 >= amount){
                val listAmount =  i.amount.subList(0,amount)
                val  number= listAmount.sum()
                if(max<number){
                    max = number
                    repeatIndex = i
                }
            }

        }
        return repeatIndex
    }

    fun addRepeat(id:Int,set:Int,amount:Int,sum:Int,weight:Int){
        var exerciseRepeat = exerciseDao.getId(id)
        val exerciseList = exerciseDao.getId(id).exercise_list


            if(exerciseList.size == 0 || set > exerciseList.last().set){
                exerciseRepeat.exercise_list.add(Repeat(set, arrayListOf<Int>(amount),sum,weight))
                exerciseDao.update(exerciseRepeat)
                Log.v("MainActivity",exerciseRepeat.toString())
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

    fun delete(){
        exerciseDao.delete()
    }

}