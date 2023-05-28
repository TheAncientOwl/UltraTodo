package ro.ase.pdm.ultratodo.services


import android.annotation.SuppressLint
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.Looper
import android.util.Log
import com.google.android.gms.location.*
import ro.ase.pdm.ultratodo.data.Location
import java.util.concurrent.TimeUnit

class GeolocationService : Service() {
    private var running = false
    private lateinit var locationProviderClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: LocationCallback

    private var userLocation = Location(0.0, 0.0)

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("GeolocationService", "onCreate()")
        locationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        locationRequest = LocationRequest.Builder(
            Priority.PRIORITY_HIGH_ACCURACY,
            TimeUnit.SECONDS.toMillis(20)
        ).build()

        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                super.onLocationResult(locationResult)

                val lastLocation = locationResult.lastLocation
                if (lastLocation != null) {
                    userLocation = Location(
                        locationResult.lastLocation!!.latitude,
                        locationResult.lastLocation!!.longitude
                    )
                }

                val geolocationIntent = Intent("ACTION_GEOLOCATION")
                geolocationIntent.putExtra("location", userLocation as java.io.Serializable)
                sendBroadcast(geolocationIntent)

                Log.d("GeolocationService", "${userLocation.latitude} - ${userLocation.longitude}")
            }
        }

    }

    @SuppressLint("MissingPermission")
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Log.d("GeolocationService", "onStartCommand()")

        if (!running) {
            locationProviderClient.requestLocationUpdates(
                locationRequest,
                locationCallback,
                Looper.getMainLooper()
            )

            running = true
        }

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("GeolocationService", "onDestroy()")
    }
}