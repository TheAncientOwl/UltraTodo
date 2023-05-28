package ro.ase.pdm.ultratodo.ui.about

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import ro.ase.pdm.ultratodo.R

class AboutTodoFragment : Fragment() {

    companion object {
        fun newInstance() = AboutTodoFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_about_todo, container, false)
        val buttonMore: Button = rootView.findViewById(R.id.navToInfoButton)
        buttonMore.setOnClickListener{
            findNavController().navigate(R.id.InfoTodoFragment)
        }
        val appNameTextView: TextView = rootView.findViewById(R.id.text_app_name)
        val versionTextView: TextView = rootView.findViewById(R.id.text_version)
        val authorsTextView: TextView = rootView.findViewById(R.id.text_authors)

        appNameTextView.text = getString(R.string.app_name)
        versionTextView.text = getString(R.string.app_version)
        authorsTextView.text = getString(R.string.app_authors)

        return rootView
    }
}
