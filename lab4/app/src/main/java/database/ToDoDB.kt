package database

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database (
    entities = [ToDo::class],
    version = 2
)

abstract  class ToDoDB : RoomDatabase(){
    abstract  fun getToDoDao(): ToDoDao

    companion object {
        @Volatile private var instance : ToDoDB? =null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDB(context) .also {
                instance = it
            }
        }

        private fun buildDB(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            ToDoDB::class.java,
            "tododb"
        ).build()
    }
}