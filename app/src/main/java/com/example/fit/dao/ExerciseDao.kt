package com.example.fit.dao

import androidx.room.*
import com.example.fit.data.Exercise

@Dao
interface ExerciseDao {

    @Query("SELECT * FROM exercise_table")
    fun get(): List<Exercise>

    @Query("SELECT * FROM exercise_table WHERE exercise_id =:id")
    fun getId(id: Int): Exercise

    @Query("SELECT * FROM exercise_table ORDER BY exercise_id DESC LIMIT 1")
    fun getLastRepeat(): Exercise


    @Update
    fun update(exercise: Exercise)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(category: Exercise)

    @Query("DELETE FROM exercise_table")
    fun delete()

}