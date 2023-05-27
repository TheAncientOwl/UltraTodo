package ro.ase.pdm.ultratodo.ui.newtodo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ro.ase.pdm.ultratodo.databinding.FragmentNewTodoBinding
import ro.ase.pdm.ultratodo.R
import ro.ase.pdm.ultratodo.data.Todo
import ro.ase.pdm.ultratodo.data.TodoPriority
import ro.ase.pdm.ultratodo.data.TodoState
import ro.ase.pdm.ultratodo.data.TodoType

class NewTodoFragment : Fragment() {
    private var _binding: FragmentNewTodoBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var viewModel: NewTodoViewModel

    private lateinit var titleEditText: EditText
    private lateinit var priorityRadioGroup: RadioGroup
    private lateinit var typeRadioGroup: RadioGroup
    private lateinit var descriptionEditText: EditText
    private lateinit var addButton: Button

    private lateinit var priority: TodoPriority
    private lateinit var type: TodoType

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(NewTodoViewModel::class.java)

        _binding = FragmentNewTodoBinding.inflate(inflater, container, false)
        val root: View = binding.root

        titleEditText = root.findViewById(R.id.titleEditText)
        priorityRadioGroup = root.findViewById(R.id.priorityRadioGroup)
        typeRadioGroup = root.findViewById(R.id.typeRadioGroup)
        descriptionEditText = root.findViewById(R.id.descriptionEditText)
        addButton = root.findViewById(R.id.addButton)

        priorityRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radioButtonLow -> {
                    priority = TodoPriority.Low
                }
                R.id.radioButtonMedium -> {
                    priority = TodoPriority.Medium
                }
                R.id.radioButtonHigh -> {
                    priority = TodoPriority.High
                }
                R.id.radioButtonUrgent -> {
                    priority = TodoPriority.Urgent
                }
            }
        }

        typeRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radioButtonNormal -> {
                    type = TodoType.Normal
                }
                R.id.radioButtonLocation -> {
                    type = TodoType.Location
                }
            }
        }

        addButton.setOnClickListener {
            val title = titleEditText.text.toString()
            val description = descriptionEditText.text.toString()

            val todo =
                Todo(title, TodoPriority.Low, TodoState.Pending, TodoType.Normal, description, null)

            viewModel.saveTodo(todo)

            titleEditText.text.clear()
            priorityRadioGroup.clearCheck()
            typeRadioGroup.clearCheck()
            descriptionEditText.text.clear()
        }

        return root
    }

}