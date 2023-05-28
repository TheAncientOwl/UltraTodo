package ro.ase.pdm.ultratodo.ui.newtodo

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.PopupWindow
import android.widget.RadioGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import ro.ase.pdm.ultratodo.databinding.FragmentNewTodoBinding
import ro.ase.pdm.ultratodo.R
import ro.ase.pdm.ultratodo.data.TodoPriority
import ro.ase.pdm.ultratodo.data.TodoType

class NewTodoFragment : Fragment(), OnMapReadyCallback {
    private var _binding: FragmentNewTodoBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var viewModel: NewTodoViewModel

    private lateinit var titleEditText: EditText
    private lateinit var priorityRadioGroup: RadioGroup
    private lateinit var typeRadioGroup: RadioGroup
    private lateinit var descriptionEditText: EditText
    private lateinit var addButton: Button
    private lateinit var popupWindow: PopupWindow

    private lateinit var mapView: MapView
    private lateinit var googleMap: GoogleMap

    private var priority: TodoPriority? = null
    private var type: TodoType? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewTodoBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this).get(NewTodoViewModel::class.java)

        findViews(savedInstanceState)
        createPopupWindow()

        handlePriorityChange()
        handleTypeChange()

        handleAddTodoClick()

        return binding.root
    }

    private fun findViews(savedInstanceState: Bundle?) {
        titleEditText = binding.root.findViewById(R.id.titleEditText)
        priorityRadioGroup = binding.root.findViewById(R.id.priorityRadioGroup)
        typeRadioGroup = binding.root.findViewById(R.id.typeRadioGroup)
        descriptionEditText = binding.root.findViewById(R.id.descriptionEditText)
        addButton = binding.root.findViewById(R.id.addButton)

        mapView = binding.root.findViewById(R.id.mapView)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)

        val layoutParams = mapView.layoutParams
        layoutParams.height = resources.displayMetrics.heightPixels / 2 // Set the desired height here
        mapView.layoutParams = layoutParams
    }

    private fun createPopupWindow() {
        val popupView =
            LayoutInflater.from(context).inflate(R.layout.todo_form_missing_fields_popup, null)
        popupWindow = PopupWindow(
            popupView,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
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

    private fun handleTypeChange() {
        typeRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radioButtonNormal -> {
                    type = TodoType.Normal
                }
                R.id.radioButtonLocation -> {
                    type = TodoType.Location
                }
            }
        }
    }

    private fun handleAddTodoClick() {
        addButton.setOnClickListener {
            val title = titleEditText.text.toString()
            val description = descriptionEditText.text.toString()

            if (priority == null || type == null || title.isEmpty()) {
                popupWindow.showAsDropDown(binding.root.findViewById(R.id.popup_anchor))
            } else {
                viewModel.saveTodo(title, description, priority!!, type!!, null)

                titleEditText.text.clear()
                priorityRadioGroup.clearCheck()
                typeRadioGroup.clearCheck()
                descriptionEditText.text.clear()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onMapReady(gMap: GoogleMap) {
        googleMap = gMap
        googleMap.uiSettings.isZoomControlsEnabled = true

        // Add a marker and move the camera
        val location = LatLng(37.7749, -122.4194) // Example location (San Francisco)
        googleMap.addMarker(MarkerOptions().position(location).title("Marker"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(location))
    }
}