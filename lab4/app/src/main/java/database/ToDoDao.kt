package database

import androidx.room.*

@Dao
interface ToDoDao {
    @Insert
    suspend fun addToDo(todo: ToDo)

    @Query("SELECT * FROM todo ORDER BY id DESC")
    suspend fun getAllToDos(): List<ToDo>

    @Update
    suspend fun updateToDo(todo: ToDo)

    @Delete
    suspend fun deleteToDo(todo: ToDo)
}