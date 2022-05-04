package com.example.fit

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercise_table")
data class Exercise(
    @PrimaryKey
    @ColumnInfo(name = "exercise_id")
    val exercise_id:Int,

    @ColumnInfo(name = "exercise_category")
    var exercise_category:String,

    @ColumnInfo(name = "exercise_name")
    var exercise_name:String,

    @ColumnInfo(name = "exercise_image")
    var exercise_image:String,

    @ColumnInfo(name = "exercise_amount_repeat")
    var exercise_list:ArrayList<Repeat>

)

data class Repeat(

    @ColumnInfo(name = "set")
    var set:Int,

    @ColumnInfo(name = "amount")
    var amount:ArrayList<Int>,

    @ColumnInfo(name="sum")
    var sum:Int,

    @ColumnInfo(name = "weight")
    var weight:Int
)



//repeat(1(10,10,10)

