package ro.ase.pdm.ultratodo.ui.todolistall

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ro.ase.pdm.ultratodo.data.TodoDatabase
import ro.ase.pdm.ultratodo.data.TodoRepository
import ro.ase.pdm.ultratodo.databinding.FragmentTodoListAllBinding
import ro.ase.pdm.ultratodo.ui.todolist.TodoListFragmentDirections
import ro.ase.pdm.ultratodo.ui.todolist.TodoListViewModel

class TodoListAllFragment : Fragment() {

    private var _binding: FragmentTodoListAllBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: TodoListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTodoListAllBinding.inflate(inflater, container, false)
        val root: View = binding.root

        viewModel = ViewModelProvider(this).get(TodoListViewModel::class.java)

        val todoDao = TodoDatabase.getInstance(requireContext()).getTodoDao()
        val todoRepository = TodoRepository(todoDao)
        viewModel.setTodoRepository(todoRepository)

        val todoListRecyclerView: RecyclerView = binding.todoAllRecyclerView
        todoListRecyclerView.layoutManager = LinearLayoutManager(context)

        val todos = viewModel.allTodos.value ?: emptyList()
        val adapter = TodoListAllAdapter(todos)
        adapter.setOnItemClickListener { todo ->
            val action = TodoListFragmentDirections.actionTodoListFragmentToViewTodoFragment(todo)
            findNavController().navigate(action)
        }
        todoListRecyclerView.adapter = adapter

        viewModel.allTodos.observe(viewLifecycleOwner) { todos ->
            adapter.updateData(todos)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}