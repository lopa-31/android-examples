package com.example.roomdatabasedemo.data

import android.content.Context
import com.example.roomdatabasedemo.data.model.User

class UserRepository(context: Context) {

    private val userDatabase = UserDatabase.getDatabase(context)

    val readAllData = userDatabase.userDao().getAll()

    suspend fun addUser(user: User) {
        userDatabase.userDao().add(user)
    }

    suspend fun updateUser(user: User) {
        userDatabase.userDao().update(user)
    }

    suspend fun deleteUser(user: User) {
        userDatabase.userDao().delete(user)
    }

    suspend fun deleteAllUser() {
        userDatabase.userDao().deleteAll()
    }

    suspend fun addUsers(userList: List<User>){
        userDatabase.userDao().addUsers(userList)
    }

}