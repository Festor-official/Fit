package com.example.fit.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey



@Entity(tableName = "body_table")
data class Body(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id:Int,

    @ColumnInfo(name = "height")
    var height:String,

    @ColumnInfo(name = "shoulders")
    var shoulders:String,

    @ColumnInfo(name = "chest")
    var chest:String,

    @ColumnInfo(name = "biceps")
    var biceps:String,

    @ColumnInfo(name = "talia")
    var talia:String,

    @ColumnInfo(name = "forearm")
    var forearm:String,

    @ColumnInfo(name = "wrest")
    var wrest:String,

    @ColumnInfo(name = "legs")
    var legs:String,

    @ColumnInfo(name = "caviar")
    var caviar:String,

    @ColumnInfo(name = "weight")
    var weight:String,

    @ColumnInfo(name = "date")
    var date:String
    )