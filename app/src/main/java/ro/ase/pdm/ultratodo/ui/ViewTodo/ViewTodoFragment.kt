package ro.ase.pdm.ultratodo.ui.viewtodo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import ro.ase.pdm.ultratodo.R
import ro.ase.pdm.ultratodo.databinding.FragmentViewTodoBinding
import ro.ase.pdm.ultratodo.data.Todo
import ro.ase.pdm.ultratodo.ui.DeleteTodo.DeleteTodoFragment

class ViewTodoFragment : Fragment() {
    private var _binding: FragmentViewTodoBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<ViewTodoFragmentArgs>()

    private lateinit var viewModel: ViewTodoViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentViewTodoBinding.inflate(inflater, container, false)
        val root: View = binding.root

        viewModel = ViewModelProvider(this).get(ViewTodoViewModel::class.java)

        val todo = args.argTodo
        todo?.let {
            viewModel.loadTodoDetails(it.id.toInt())
        }

        viewModel.todo.observe(viewLifecycleOwner) { loadedTodo ->
            loadedTodo?.let { displayTodoDetails(it) }
        }

        return root
    }

    private fun displayTodoDetails(todo: Todo) {
        // Set the values of UI elements with the details of the todo item
        binding.titleTextView.text = "Title: ${todo.title}"
        binding.priorityTextView.text = "Priority: ${todo.priority}"
        binding.stateTextView.text = "State: ${todo.state}"
        binding.descriptionTextView.text = "Description: ${todo.description}"
        binding.creationDateTextView.text = "Created on: ${todo.creationDate}"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ARG_TODO = "todo"

        fun newInstance(todo: Todo): ViewTodoFragment {
            val fragment = ViewTodoFragment()
            val args = Bundle()
            args.putParcelable(ARG_TODO, todo)
            fragment.arguments = args
            return fragment
        }
    }

}
