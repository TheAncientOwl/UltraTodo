package ro.ase.pdm.ultratodo.ui.todolist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
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

//        val textView: TextView = binding.textTodoList
//        todoListViewModel.text.observe(viewLifecycleOwner) {
//            textView.text = it
//        }

        return root
    }
}