package com.example.fit.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.fit.data.Exercise
import com.example.fit.data.Repeat
import com.example.fit.converters.Converter
import com.example.fit.dao.ExerciseDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


@Database(entities = [Exercise::class], version = 2,exportSchema = false)
@TypeConverters(Converter::class)
abstract class ExerciseDatabase: RoomDatabase() {

    abstract fun exerciseDao(): ExerciseDao

    companion object{
        @Volatile
        private var INSTANCEEXERCISE: ExerciseDatabase? = null
        fun getDatabase(context: Context, scope:CoroutineScope): ExerciseDatabase {
            val migration1_2 = object:Migration(1,2){
                override fun migrate(database: SupportSQLiteDatabase) {
                }
            }

            val migration2_3 = object:Migration(2,3){
                override fun migrate(database: SupportSQLiteDatabase) {
                    database.execSQL("ALTER TABLE Repeat ADD COLUMN weight INTEGER")
                }
            }

            return INSTANCEEXERCISE ?: synchronized(this){
                val instanceExercise = Room.databaseBuilder(
                    context.applicationContext,
                    ExerciseDatabase::class.java,
                    "exercise_database"
                ).addMigrations(migration1_2,migration2_3).allowMainThreadQueries().addCallback(ExerciseDatabaseCallback(scope)).build()
                INSTANCEEXERCISE = instanceExercise
                return instanceExercise
            }
        }

        private class ExerciseDatabaseCallback(private val scope: CoroutineScope): RoomDatabase.Callback(){
            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                INSTANCEEXERCISE?.let {
                    database -> scope.launch(Dispatchers.IO) {
                        populateDatabase(database.exerciseDao())
                }
                }
            }
        }

        suspend fun populateDatabase(exerciseDao: ExerciseDao){
            //https://cross.expert/wp-content/uploads/2017/03/podtyagivaniya-na-kolcah-myshcy.jpeg
            exerciseDao.insert(Exercise(0,"back","Подтягивания поперечным хватом","", arrayListOf<Repeat>()))
            exerciseDao.insert(Exercise(1,"back","Подьем согнутых ног к торсу","", arrayListOf<Repeat>()))
            exerciseDao.insert(Exercise(2,"back","Махи ногами в стороны","",arrayListOf<Repeat>()))
            exerciseDao.insert(Exercise(3,"back","Отжимание на брусьях","", arrayListOf<Repeat>()))
            exerciseDao.insert(Exercise(4,"back","Вис на турнике(на время)","", arrayListOf<Repeat>()))
            exerciseDao.insert(Exercise(5,"back","Подтягивая средним хватом","", arrayListOf<Repeat>()))
            exerciseDao.insert(Exercise(6,"back","Подьем согнутых ног к торсу с весом","", arrayListOf<Repeat>()))
            exerciseDao.insert(Exercise(7,"back","Подтягивания обратным хватом","", arrayListOf<Repeat>()))
            exerciseDao.insert(Exercise(8,"back","Преседание с весом","", arrayListOf<Repeat>()))
            exerciseDao.insert(Exercise(9,"back","Подьем на носочки с весом","", arrayListOf<Repeat>()))
            exerciseDao.insert(Exercise(10,"back","Подтягивания широким хватом","", arrayListOf<Repeat>()))
        }

    }

}






