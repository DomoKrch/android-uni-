package UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.lab4.R
import database.ToDoDB
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.launch

class HomeFrag : BaseFrag() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = StaggeredGridLayoutManager(6, StaggeredGridLayoutManager.VERTICAL)

        launch {
            context?.let {
                val todos = ToDoDB(it).getToDoDao().getAllToDos()
                recyclerView.adapter = ToDosAdapter(todos)
            }
        }

        addBtn.setOnClickListener {
            val action = HomeFragDirections.actionAddToDo()
            Navigation.findNavController(it).navigate(action)
        }
    }
}