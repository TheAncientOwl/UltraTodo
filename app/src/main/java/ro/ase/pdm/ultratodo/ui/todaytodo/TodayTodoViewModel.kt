package ro.ase.pdm.ultratodo.ui.todaytodo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TodayTodoViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is today todo Fragment"
    }
    val text: LiveData<String> = _text
}