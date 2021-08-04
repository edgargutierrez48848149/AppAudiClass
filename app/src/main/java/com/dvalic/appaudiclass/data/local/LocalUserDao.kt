package com.dvalic.appaudiclass.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.dvalic.appaudiclass.data.models.ModelUser

@Dao
interface LocalUserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(modelUser: ModelUser)

    @Update
    suspend fun updateUser(modelUser: ModelUser)

    @Query("DELETE FROM user_table")
    fun deleteUser()

    @Query("SELECT * FROM user_table")
    fun selectUser(): LiveData<ModelUser>
}