package ro.ase.pdm.ultratodo.ui.AboutTodo

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
        val aboutTextView: TextView = rootView.findViewById(R.id.text_about)

        appNameTextView.text = getString(R.string.app_name)
        versionTextView.text = getString(R.string.app_version)
        authorsTextView.text = getString(R.string.app_authors)
        aboutTextView.text = "Bine ați venit pe UltraTodo, cea mai bună aplicație de productivitate pentru a vă gestiona fără efort sarcinile și listele de sarcini.\n" +
                "Scopul nostru este de a oferi o experiență intuitivă și fără cusur, ajutându-vă să vă organizați și să vă concentrați cu ușurință asupra sarcinilor zilnice.\n" +
                "Construită cu cele mai recente tehnologii, UltraTodo oferă o interfață elegantă și modernă, care se adaptează la nevoile dvs. pe toate dispozitivele și platformele.\n" +
                "Cu o abordare de design centrată pe utilizator, dezvoltatorii noștri au creat o aplicație care combină simplitatea cu caracteristici puternice, asigurând un flux de lucru productiv pentru toți utilizatorii.\n" +
                "Dar UltraTodo este mai mult decât un simplu manager de sarcini. Este un hub pentru dezvoltare și realizare personală. Alăturați-vă comunității noastre de persoane motivate pentru a împărtăși sfaturi, strategii și inspirație pentru maximizarea productivității.\n" +
                "Fie că sunteți un profesionist ocupat, un student cu un program încărcat sau pur și simplu o persoană care dorește să rămână organizată, UltraTodo este aplicația dvs. de bază pentru a vă menține în fruntea sarcinilor și pentru a vă atinge obiectivele în mod eficient."

        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AboutTodoViewModel::class.java)
        // TODO: Use the ViewModel
    }
}
