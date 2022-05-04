package com.example.fit

class BodyRepository(private val bodyDao:BodyDao) {

    val getLast = bodyDao.get().lastOrNull()
    val get = bodyDao.get()
    fun previous():Body?{
        if (bodyDao.get().size > 1){
            return bodyDao.get()[bodyDao.get().size-2]
        }else{
            return null
        }
    }

    fun insert(body:Body){
        bodyDao.insert(body)
    }


    fun delete(){
        bodyDao.delete()
    }
}