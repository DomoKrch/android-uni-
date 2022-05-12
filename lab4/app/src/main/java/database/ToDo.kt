package database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class ToDo (
    val title: String,
    val description: String,
    val status: Boolean,
    val category: String,
    val duration: String
): Serializable{
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}