package ro.ase.pdm.ultratodo.ui.about

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ro.ase.pdm.ultratodo.R

class AboutTodoFragment : Fragment() {

    companion object {
        fun newInstance() = AboutTodoFragment()
    }

    private lateinit var viewModel: AboutTodoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_about_todo, container, false)

        val appNameTextView: TextView = rootView.findViewById(R.id.text_app_name)
        val versionTextView: TextView = rootView.findViewById(R.id.text_version)
        val authorsTextView: TextView = rootView.findViewById(R.id.text_authors)

        appNameTextView.text = getString(R.string.app_name)
        versionTextView.text = getString(R.string.app_version)
        authorsTextView.text = getString(R.string.app_authors)

        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AboutTodoViewModel::class.java)
        // TODO: Use the ViewModel
    }
}
