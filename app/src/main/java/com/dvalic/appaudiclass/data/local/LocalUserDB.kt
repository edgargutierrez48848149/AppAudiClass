package com.dvalic.appaudiclass.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dvalic.appaudiclass.data.models.ModelUser

@Database(
    entities = [ModelUser::class],
    version = 2,
    exportSchema = false
)
abstract class LocalUserDB :RoomDatabase(){

    abstract fun localUserDao(): LocalUserDao

    companion object{
        @Volatile
        private var INSTANCE: LocalUserDB?=null

        fun createInstance(context: Context): LocalUserDB{
            val tempInstance = INSTANCE
            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LocalUserDB::class.java,
                    "user_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}