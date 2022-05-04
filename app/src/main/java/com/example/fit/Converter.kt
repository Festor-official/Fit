package com.example.fit

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converter {

    @TypeConverter
    fun fromArrayToGson(productList: ArrayList<Repeat>?): String?{
        val type = object: TypeToken<ArrayList<Repeat>>() {}.type
        return Gson().toJson(productList,type)
    }

    @TypeConverter
    fun fromGsonToArray(productString: String?): ArrayList<Repeat>?{
        val type = object: TypeToken<ArrayList<Repeat>>() {}.type
        return Gson().fromJson<ArrayList<Repeat>>(productString, type)
    }

    @TypeConverter
    fun fromArrayIntToGson(productList: ArrayList<Int>?): String?{
        val type = object: TypeToken<ArrayList<Int>>() {}.type
        return Gson().toJson(productList,type)
    }
    @TypeConverter
    fun fromGsonToArrayInt(productString: String?): ArrayList<Int>?{
        val type = object: TypeToken<ArrayList<Int>>() {}.type
        return Gson().fromJson<ArrayList<Int>>(productString, type)
    }

}