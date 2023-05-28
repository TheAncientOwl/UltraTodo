package ro.ase.pdm.ultratodo.ui.info

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import ro.ase.pdm.ultratodo.R

class InfoTodoFragment : Fragment() {

    companion object {
        fun newInstance() = InfoTodoFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_info_todo, container, false)

        val appNameTextView1: TextView = rootView.findViewById(R.id.text_more_info1)
        val appNameTextView2: TextView = rootView.findViewById(R.id.text_more_info2)
        val appNameTextView3: TextView = rootView.findViewById(R.id.text_more_info3)

        appNameTextView1.text = getString(R.string.info_fragment1)
        appNameTextView2.text = getString(R.string.info_fragment2)
        appNameTextView3.text = getString(R.string.info_fragment3)


        return rootView
    }
}
