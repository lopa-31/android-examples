package com.example.roomdatabasedemo.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.roomdatabasedemo.data.model.User

@Dao
interface UserDao {

    @Insert
    suspend fun addUsers(userList: List<User>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun add(user: User)

    @Update
    suspend fun update(user: User)

    @Delete
    suspend fun delete(user: User)

    @Query("DELETE FROM users")
    suspend fun deleteAll()

    @Query("SELECT * FROM users ORDER BY id ASC")
    fun getAll(): LiveData<List<User>>
}