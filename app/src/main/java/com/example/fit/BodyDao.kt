package com.example.fit

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BodyDao {

    @Query("SELECT * FROM body_table")
    fun get():List<Body>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(body:Body)

    @Query("DELETE FROM body_table")
    fun delete()

}










