package io.rrohaill.cleanweatherapp.view.home

import android.annotation.SuppressLint
import android.content.Context
import android.location.LocationManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.core.location.LocationManagerCompat
import androidx.core.location.LocationRequestCompat
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import dagger.hilt.android.AndroidEntryPoint
import io.rrohaill.cleanweatherapp.common.compose.ShowError
import io.rrohaill.cleanweatherapp.common.compose.askLocationPermission
import io.rrohaill.cleanweatherapp.view.theme.CleanWeatherAppTheme


@AndroidEntryPoint
class HomeActivity : ComponentActivity() {

    private val homeViewModel: HomeViewModel by viewModels()

    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CleanWeatherAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    //permission state
                    val locationPermissionState = askLocationPermission()
                    //check state
                    if (locationPermissionState.status.isGranted) {
                        findLocationAndFetchWeather()
                        HomeScreen(homeViewModel.getResult())
                    } else
                        ShowError(message = "Permission not granted. Go to settings and grant permission.")
                }
            }
        }
    }


    /**
     * Composable permissions have already been asked
     * This method will get current location's latitude and longitude
     */
    @SuppressLint("MissingPermission")
    private fun findLocationAndFetchWeather() {
        //fetch location and weather
        val lm = getSystemService(Context.LOCATION_SERVICE) as LocationManager

        LocationManagerCompat.requestLocationUpdates(
            lm,
            LocationManager.GPS_PROVIDER,
            LocationRequestCompat.Builder(0).build(),
            { location ->
                homeViewModel.fetchWeather(lat = location.latitude, lon = location.longitude)
            },
            this.mainLooper
        )
    }

}