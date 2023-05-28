package ro.ase.pdm.ultratodo.ui.newtodo

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.PopupWindow
import android.widget.RadioGroup
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContracts
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
import ro.ase.pdm.ultratodo.data.Location
import ro.ase.pdm.ultratodo.data.TodoPriority
import ro.ase.pdm.ultratodo.data.TodoType
import ro.ase.pdm.ultratodo.services.GeolocationService

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

    val launcher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions(),
        ActivityResultCallback {
            // it[Manifest.permission.ACCESS_FINE_LOCATION] -> true (acordata), false (respinsa)
        })

    val geolocationReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent) {
            val userLocation: Location = intent.getSerializableExtra("location") as Location

            val location = LatLng(userLocation!!.latitude, userLocation!!.longitude)
            googleMap.addMarker(MarkerOptions().position(location).title("You're Here!"))
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(location))

            Log.d(
                "NEW-TODO Location received",
                "${userLocation.latitude} - ${userLocation.longitude}"
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNewTodoBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this).get(NewTodoViewModel::class.java)

        launcher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )
        val intent = Intent(requireContext(), GeolocationService::class.java)
        requireContext().startService(intent)

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
        mapView.visibility = View.GONE

        val layoutParams = mapView.layoutParams
        layoutParams.height =
            resources.displayMetrics.heightPixels / 2 // Set the desired height here
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
                    mapView.visibility = View.GONE
                }
                R.id.radioButtonLocation -> {
                    type = TodoType.Location
                    mapView.visibility = View.VISIBLE
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
                mapView.visibility = View.GONE
            }
        }
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
        context?.registerReceiver(geolocationReceiver, IntentFilter("ACTION_GEOLOCATION"))
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
        context?.unregisterReceiver(geolocationReceiver)
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
    }
}