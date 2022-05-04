package com.example.fit

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Database(entities = [Body::class], version = 1,exportSchema = false)

abstract class BodyDatabase: RoomDatabase() {

    abstract fun bodyDao(): BodyDao

    companion object{
        @Volatile
        private var INSTANCEEXERCISE: BodyDatabase? = null
        fun getDatabase(context: Context, scope: CoroutineScope):BodyDatabase{
            return INSTANCEEXERCISE?: synchronized(this){
                val instanceBody = Room.databaseBuilder(
                    context.applicationContext,
                    BodyDatabase::class.java,
                    "body_database"
                ).fallbackToDestructiveMigration().allowMainThreadQueries().addCallback(BodyDatabaseCallback(scope)).build()
                INSTANCEEXERCISE = instanceBody
                return instanceBody
            }
        }

        private class BodyDatabaseCallback(private val scope: CoroutineScope): RoomDatabase.Callback(){
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                INSTANCEEXERCISE?.let {
                        database -> scope.launch(Dispatchers.IO) {
                    populateDatabase(database.bodyDao())
                }
                }
            }
        }

        suspend fun populateDatabase(bodyDao:BodyDao){
            bodyDao.insert(Body(0,"179.5-180","118","96","35","87","28.5","17","62","37","77.5","02.09.2021"))
            bodyDao.insert(Body(1,"179.5","119","104","36.5","89","31","17.5","63.5","39","81.5","08.10.2021"))
        }

    }

}