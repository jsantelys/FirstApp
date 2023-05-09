package com.utn.firstapp.database

import androidx.room.*
import com.utn.firstapp.entities.User

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun getUsers(): MutableList<User>?

    @Query("SELECT * FROM user WHERE id=:id")
    fun getUserById(id: String): User?

    @Query("SELECT * FROM user WHERE username=:username AND password=:password")
    fun getUserByLogIn(username: String, password: String) : User?

    @Query("SELECT * FROM user WHERE email=:email OR username=:username")
    fun getUserByEmailOrUsername(email: String, username: String): User?

    @Query("SELECT * FROM user WHERE email=:email")
    fun getUserByEmail(email: String) : User?

    @Query("SELECT * FROM user WHERE username=:username")
    fun getUserByUsername(username: String): User?

    @Insert
    fun insertUser(user: User)

    @Delete
    fun deleteUser(user: User)

    @Update
    fun updateUser(user: User)
}