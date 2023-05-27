package ro.ase.pdm.ultratodo.ui.todolist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.ViewModelFactoryDsl
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import ro.ase.pdm.ultratodo.R
import ro.ase.pdm.ultratodo.data.Todo
import ro.ase.pdm.ultratodo.data.TodoDatabase
import ro.ase.pdm.ultratodo.data.TodoRepository
import ro.ase.pdm.ultratodo.databinding.FragmentTodoListBinding

class TodoListFragment : Fragment() {

    private var _binding: FragmentTodoListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var viewModel: TodoListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(TodoListViewModel::class.java)

        _binding = FragmentTodoListBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val todoDao = TodoDatabase.getInstance(requireContext()).getTodoDao()
        val todoRepository = TodoRepository(todoDao)
        viewModel.setTodoRepository(todoRepository)

        val todoListRecyclerView: RecyclerView = root.findViewById(R.id.todo_recycler_view)
        todoListRecyclerView.layoutManager = LinearLayoutManager(context)
        if (viewModel.allTodos.value != null)
            todoListRecyclerView.adapter = TodoListAdapter(viewModel.allTodos.value!!)

        viewModel.allTodos.observe(viewLifecycleOwner) { todos ->
            todoListRecyclerView.adapter = TodoListAdapter(todos)
        }

        return root
    }
}