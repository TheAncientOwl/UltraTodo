package ro.ase.pdm.ultratodo.ui.alltodos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AllTodosViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is all todos Fragment"
    }
    val text: LiveData<String> = _text
}