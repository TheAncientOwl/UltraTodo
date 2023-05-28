package ro.ase.pdm.ultratodo.ui.list

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
import ro.ase.pdm.ultratodo.databinding.FragmentTodoListBinding

class TodoListFragment : Fragment() {

    private var _binding: FragmentTodoListBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: TodoListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTodoListBinding.inflate(inflater, container, false)
        val root: View = binding.root

        viewModel = ViewModelProvider(this).get(TodoListViewModel::class.java)

        val todoDao = TodoDatabase.getInstance(requireContext()).getTodoDao()
        val todoRepository = TodoRepository(todoDao)
        viewModel.setTodoRepository(todoRepository)

        val todoListRecyclerView: RecyclerView = binding.todoRecyclerView
        todoListRecyclerView.layoutManager = LinearLayoutManager(context)

        val todos = viewModel.allTodos.value ?: emptyList()
        val adapter = TodoListAdapter(todos)
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
