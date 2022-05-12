package UI

import android.app.AlertDialog
import android.os.AsyncTask
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.navigation.Navigation
import com.example.lab4.R
import database.ToDo
import database.ToDoDB
import kotlinx.android.synthetic.main.fragment_add_todo.*
import kotlinx.coroutines.launch

class AddTodoFrag : BaseFrag() {

    private var todo: ToDo? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_todo, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        arguments?.let {
            todo = AddTodoFragArgs.fromBundle(it).toDo
            title.setText(todo?.title)
            description.setText(todo?.description)
            category.setText(todo?.category)
            duration.setText(todo?.duration)
        }

        saveBtn.setOnClickListener { view ->
            val todoTitle = title.text.toString().trim()
            val todoDescription= description.text.toString().trim()
            val todoCategory = category.text.toString().trim()
            val todoDuration = duration.text.toString().trim()

            if (todoTitle.isEmpty()) {
                title.error = "title required"
                title.requestFocus()
                return@setOnClickListener
            }
            if (todoDescription.isEmpty()) {
                description.error = "description required"
                description.requestFocus()
                return@setOnClickListener
            }
            if (todoCategory.isEmpty()) {
                category.error = "category required"
                category.requestFocus()
                return@setOnClickListener
            }
            if (todoDuration.isEmpty()) {
                duration.error = "duration required"
                duration.requestFocus()
                return@setOnClickListener
            }

            launch {
                context?.let {
                    val mTodo = ToDo(todoTitle, todoDescription, true, todoCategory, todoDuration)

                    if (todo == null) {
                        ToDoDB(it).getToDoDao().addToDo(mTodo)
                        it.toast("Task Saved")
                    }
                    else {
                        mTodo.id = todo!!.id
                        ToDoDB(it).getToDoDao().updateToDo(mTodo)
                        it.toast("Task Updated")
                    }

                    val action = AddTodoFragDirections.actionSave()
                    Navigation.findNavController(view).navigate(action)
                }
            }


        }
    }

    private fun deleteToDo() {
        AlertDialog.Builder(context).apply {
            setTitle("Are you sure?")
            setMessage("You can't undo this")
            setPositiveButton("Yes") { _, _ ->
                launch {
                    ToDoDB(context).getToDoDao().deleteToDo(todo!!)
                    val action = AddTodoFragDirections.actionSave()
                    Navigation.findNavController(requireView()).navigate(action)
                }
            }
            setNegativeButton("No"){_, _ ->

            }
        }.create().show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId)
        {
            R.id.delete -> if(todo != null) deleteToDo() else context?.toast("Cannot delete")
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }
}