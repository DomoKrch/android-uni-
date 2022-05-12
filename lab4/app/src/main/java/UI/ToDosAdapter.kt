package UI

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.lab4.R
import database.ToDo
import kotlinx.android.synthetic.main.todo_layout.view.*

class ToDosAdapter (private val todos : List<ToDo>) : RecyclerView.Adapter<ToDosAdapter.ToDoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        return ToDoViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.todo_layout, parent, false)
        )
    }

    override fun getItemCount() = todos.size

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        holder.view.titleTV.text = todos[position].title
        holder.view.statusTV.text = todos[position].status.toString()
        holder.view.descriptionTV.text = todos[position].description

        holder.view.setOnClickListener {
            val action = HomeFragDirections.actionAddToDo()
            action.toDo = todos[position]
            Navigation.findNavController(it).navigate(action)
        }
    }

    class ToDoViewHolder(val view: View) : RecyclerView.ViewHolder(view)
}