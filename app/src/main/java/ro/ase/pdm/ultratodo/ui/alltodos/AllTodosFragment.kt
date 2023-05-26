package ro.ase.pdm.ultratodo.ui.alltodos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ro.ase.pdm.ultratodo.databinding.FragmentAllTodosBinding

class AllTodosFragment : Fragment() {

    private var _binding: FragmentAllTodosBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val allTodosViewModel =
            ViewModelProvider(this).get(AllTodosViewModel::class.java)

        _binding = FragmentAllTodosBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textGallery
        allTodosViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}