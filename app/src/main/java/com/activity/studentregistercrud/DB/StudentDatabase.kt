package com.activity.studentregistercrud.DB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Student::class], version = 1, exportSchema = false)
abstract class StudentDatabase : RoomDatabase(){
    //define abstract function which return an instance of student Dao interface
    abstract fun studentDao():StudentDao

    //create an instance of a database with application context
    //function adding context as an argument
    //companion object is singleton like static in java
    companion object{
        //represent constant singleton
        /*annotate with Volatile, marks the jvm backing field of the property Volatile
        writes the fields will immediately visible to other threads*/
        @Volatile
        private var INSTANCE : StudentDatabase? = null
        //function adding context as parameter
        fun getInstance(context : Context): StudentDatabase{
            /*add synchronize block, it will execute the given function while
            holding the monitor of the given object block */
            synchronized(this){
                var instance = INSTANCE
                //detect if the database has not created it
                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        StudentDatabase::class.java,
                                "student_data_database"
                    ).build()
                }
                return instance
            }
        }
    }
}