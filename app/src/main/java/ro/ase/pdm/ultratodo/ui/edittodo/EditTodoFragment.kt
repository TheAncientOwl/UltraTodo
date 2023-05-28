package ro.ase.pdm.ultratodo.ui.edittodo

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.PopupWindow
import android.widget.RadioGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ro.ase.pdm.ultratodo.R
import ro.ase.pdm.ultratodo.data.*
import ro.ase.pdm.ultratodo.databinding.FragmentEditTodoBinding

class EditTodoFragment : Fragment() {
    private var _binding: FragmentEditTodoBinding? = null
    private val binding get() = _binding!!
    private val args by navArgs<EditTodoFragmentArgs>()

    private lateinit var viewModel: EditTodoViewModel

    private lateinit var priorityRadioGroup: RadioGroup
    private lateinit var stateRadioGroup: RadioGroup
    private lateinit var titleEditText: EditText
    private lateinit var descriptionEditText: EditText

    private lateinit var popupWindow: PopupWindow

    private var priority: TodoPriority? = null
    private var state: TodoState? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        viewModel = EditTodoViewModel(
            requireActivity().application,
            TodoRepository(TodoDatabase.getInstance(requireContext()).getTodoDao())
        )

        _binding = FragmentEditTodoBinding.inflate(inflater, container, false)
        val root: View = binding.root

        findViewsById()
        setDefaults()

        handlePriorityChange()
        handleStateChange()

        createPopupWindow()

        handleSaveButtonClick()

        return root
    }

    private fun setDefaults() {
        val todo = args.argTodo

        titleEditText.setText(todo.title)
        descriptionEditText.setText(todo.description)

        priority = todo.priority
        when (todo.priority) {
            TodoPriority.Low -> {
                priorityRadioGroup.check(R.id.radioButtonLow)
            }
            TodoPriority.Medium -> {
                priorityRadioGroup.check(R.id.radioButtonMedium)
            }
            TodoPriority.High -> {
                priorityRadioGroup.check(R.id.radioButtonHigh)

            }
            TodoPriority.Urgent -> {
                priorityRadioGroup.check(R.id.radioButtonUrgent)
            }
        }

        state = todo.state
        when (todo.state) {
            TodoState.Done -> {
                stateRadioGroup.check(R.id.radioButtonDone)
            }
            TodoState.Pending -> {
                stateRadioGroup.check(R.id.radioButtonPending)
            }
        }
    }

    private fun findViewsById() {
        priorityRadioGroup = binding.root.findViewById(R.id.priorityRadioGroup)
        stateRadioGroup = binding.root.findViewById(R.id.stateRadioGroup)
        titleEditText = binding.root.findViewById(R.id.titleEditText)
        descriptionEditText = binding.root.findViewById(R.id.descriptionEditText)
    }

    private fun createPopupWindow() {
        val popupView =
            LayoutInflater.from(context).inflate(R.layout.todo_form_missing_fields_popup, null)
        popupWindow = PopupWindow(
            popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        )

        popupWindow.isFocusable = true
        popupWindow.setBackgroundDrawable(ColorDrawable(Color.RED))

        val closeButton = popupView.findViewById<Button>(R.id.close_newtodo_popup_button)
        closeButton.setOnClickListener {
            popupWindow.dismiss()
        }
    }

    private fun handlePriorityChange() {
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
    }

    private fun handleStateChange() {
        stateRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radioButtonDone -> {
                    state = TodoState.Done
                }
                R.id.radioButtonPending -> {
                    state = TodoState.Pending
                }
            }
        }
    }

    private fun handleSaveButtonClick() {
        binding.saveButton.setOnClickListener {
            val title = binding.titleEditText.text.toString()

            if (priority == null || state == null || title.isEmpty()) {
                popupWindow.showAsDropDown(binding.root.findViewById(R.id.popup_anchor))
            } else {
                val todo = args.argTodo

                val updatedTodo = Todo(
                    title = title,
                    priority = priority!!,
                    state = state!!,
                    description = binding.descriptionEditText.text.toString(),
                    location = todo.location,
                    type = todo.type
                )
                updatedTodo.id = todo.id
                updatedTodo.creationDate = todo.creationDate

                viewModel.updateTodoItem(updatedTodo)

                findNavController().navigateUp()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
