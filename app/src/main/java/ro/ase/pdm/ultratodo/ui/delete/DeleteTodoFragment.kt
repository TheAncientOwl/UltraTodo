package ro.ase.pdm.ultratodo.ui.delete

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ro.ase.pdm.ultratodo.R

class DeleteTodoFragment : Fragment() {
    companion object {
        fun newInstance() = DeleteTodoFragment()
    }

    private val args by navArgs<DeleteTodoFragmentArgs>()

    private lateinit var viewModel: DeleteTodoViewModel

    private lateinit var todoNameTextView: TextView
    private lateinit var todoStateTextView: TextView
    private lateinit var todoPriorityTextView: TextView
    private lateinit var checkQuestionTextView: TextView
    private lateinit var deleteButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(DeleteTodoViewModel::class.java)

        val rootView = inflater.inflate(R.layout.fragment_delete_todo, container, false)

        todoNameTextView = rootView.findViewById(R.id.text_todo_name)
        todoStateTextView = rootView.findViewById(R.id.text_todo_state)
        todoPriorityTextView = rootView.findViewById(R.id.text_todo_priority)
        checkQuestionTextView = rootView.findViewById(R.id.text_check_question)
        deleteButton = rootView.findViewById(R.id.deleteButton)

        val todo = args.argTodo

        // Set the text values for the TextViews
        todoNameTextView.text = "Title: ${todo.title}"
        todoStateTextView.text = "State: ${todo.state.toString()}"
        todoPriorityTextView.text = "Priority: ${todo.priority.toString()}"
        checkQuestionTextView.text = "Are you sure you want to delete this todo?"

        deleteButton.setOnClickListener {
            viewModel.deleteTodo(todo)
            findNavController().navigate(R.id.nav_todo_list)
        }

        return rootView
    }
}