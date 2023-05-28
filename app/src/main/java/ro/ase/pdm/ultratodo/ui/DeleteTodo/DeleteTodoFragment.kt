package ro.ase.pdm.ultratodo.ui.DeleteTodo

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import ro.ase.pdm.ultratodo.R

class DeleteTodoFragment : Fragment() {

    companion object {
        fun newInstance() = DeleteTodoFragment()
    }

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
        val rootView = inflater.inflate(R.layout.fragment_delete_todo, container, false)

        todoNameTextView = rootView.findViewById(R.id.text_todo_name)
        todoStateTextView = rootView.findViewById(R.id.text_todo_state)
        todoPriorityTextView = rootView.findViewById(R.id.text_todo_priority)
        checkQuestionTextView = rootView.findViewById(R.id.text_check_question)
        deleteButton = rootView.findViewById(R.id.btn_delete)

        // Set the text values for the TextViews
        todoNameTextView.text = "Todo name"
        todoStateTextView.text = "Todo state"
        todoPriorityTextView.text = "Todo priority"
        checkQuestionTextView.text = "Are you sure you want to delete this todo?"

        // Set an onClickListener for the delete button
        deleteButton.setOnClickListener {
            // Perform the delete operation
            // Add your logic here
        }

        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DeleteTodoViewModel::class.java)
        // TODO: Use the ViewModel
    }
}
