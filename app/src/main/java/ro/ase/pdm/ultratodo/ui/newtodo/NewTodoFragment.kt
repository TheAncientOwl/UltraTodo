package ro.ase.pdm.ultratodo.ui.newtodo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import ro.ase.pdm.ultratodo.databinding.FragmentNewTodoBinding

class NewTodoFragment : Fragment() {
    private var _binding: FragmentNewTodoBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        val newTodoViewModel = ViewModelProvider(this).get(NewTodoViewModel::class.java)

        _binding = FragmentNewTodoBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val nameInput: TextInputEditText = binding.newTodoName
        newTodoViewModel.todo.observe(viewLifecycleOwner) {
            nameInput.setText(it.name)
        }

        return root
    }
}