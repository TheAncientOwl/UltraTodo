package ro.ase.pdm.ultratodo.ui.edittodo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ro.ase.pdm.ultratodo.data.Todo
import ro.ase.pdm.ultratodo.data.TodoDao
import ro.ase.pdm.ultratodo.data.TodoDatabase
import ro.ase.pdm.ultratodo.data.TodoRepository
import ro.ase.pdm.ultratodo.databinding.FragmentEditTodoBinding

class EditTodoFragment : Fragment() {
    private var _binding: FragmentEditTodoBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<EditTodoFragmentArgs>()

    private lateinit var viewModel: EditTodoViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditTodoBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val todo = args.argTodo
        val todoDao: TodoDao = TodoDatabase.getInstance(requireContext()).getTodoDao()
        val todoRepository = TodoRepository(todoDao)
        viewModel = EditTodoViewModel(requireActivity().application, todoRepository)

        binding.saveButton.setOnClickListener {
            val updatedTodo = Todo(
                title = binding.titleEditText.text.toString(),
                /* priority = binding.prioritySpinner.selectedItem.toString(),
                state = binding.stateSpinner.selectedItem.toString(), */
                description = binding.descriptionEditText.text.toString(),
                location = todo.location,
                priority = todo.priority,
                state = todo.state,
                type = todo.type
            )
            updatedTodo.id = todo.id
            updatedTodo.creationDate = todo.creationDate


            viewModel.updateTodoItem(updatedTodo)

            findNavController().navigateUp()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
