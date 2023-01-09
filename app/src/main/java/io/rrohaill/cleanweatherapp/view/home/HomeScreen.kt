package io.rrohaill.cleanweatherapp.view.home

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.location.LocationManager
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.location.LocationManagerCompat
import androidx.core.location.LocationRequestCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import io.rrohaill.cleanweatherapp.BuildConfig
import io.rrohaill.cleanweatherapp.R
import io.rrohaill.cleanweatherapp.domain.usecase.model.WeatherUIData
import io.rrohaill.cleanweatherapp.domain.usecase.model.WeatherUIResult

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HomeWithPermission(homeViewModel: HomeViewModel = viewModel()) {

    //permission state
    val locationPermissionState = rememberPermissionState(
        Manifest.permission.ACCESS_COARSE_LOCATION,
    )

    //ask permission
    SideEffect {
        locationPermissionState.launchPermissionRequest()
    }

    //check state
    if (locationPermissionState.status.isGranted)
        HomeScreen(homeViewModel)
    else
        ShowError(message = "Permission not granted")
}

@SuppressLint("MissingPermission")
@Composable
fun HomeScreen(homeViewModel: HomeViewModel) {
    val context = LocalContext.current

    //fetch location and weather
    val lm = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager

    LocationManagerCompat.requestLocationUpdates(
        lm,
        LocationManager.GPS_PROVIDER,
        LocationRequestCompat.Builder(0).build(),
        { location ->
            homeViewModel.fetchWeather(lat = location.latitude, lon = location.longitude)
        },
        context.mainLooper
    )

    //check state
    val result by homeViewModel.getResult().collectAsState(initial = WeatherUIResult.Loading)
    when (result) {
        is WeatherUIResult.Loading -> ShowLoading()
        is WeatherUIResult.Error -> ShowError((result as WeatherUIResult.Error).errorMessage)
        is WeatherUIResult.Success -> HomeContent(currentWeather = (result as WeatherUIResult.Success).data)
    }

}

@Composable
fun ShowError(message: String) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = message, modifier = Modifier.align(Alignment.Center))
    }
}

@Composable
fun ShowLoading() {
    Box(modifier = Modifier.fillMaxSize()) {
        CircularProgressIndicator(
            modifier = Modifier
                .align(Alignment.Center)
                .testTag("myProgressIndicator")
        )
    }
}

@Composable
fun HomeContent(
    currentWeather: WeatherUIData
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(vertical = 20.dp)
            .verticalScroll(rememberScrollState())
            .testTag("myContent")
    ) {
        NowWeather(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            currentWeather = currentWeather,
        )
    }
}

@Composable
fun NowWeather(
    modifier: Modifier,
    currentWeather: WeatherUIData,
) {
    Row(modifier = modifier) {
        Image(
            painter = rememberImagePainter(
                BuildConfig.IMAGE_URL.replace(
                    "{icon}",
                    currentWeather.icon
                )
            ),
            contentDescription = "weather_image",
            modifier = Modifier
                .fillMaxHeight()
                .padding(top = 50.dp)
                .weight(1f),
            alignment = Alignment.CenterEnd,
            contentScale = ContentScale.Fit,
        )

        Column(
            modifier = Modifier
                .padding(end = 15.dp)
                .fillMaxHeight(),
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = stringResource(
                    id = R.string.home_text_celsius_high_low,
                    currentWeather.max,
                    currentWeather.min,
                ),
            )

            Text(
                text = currentWeather.city,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                style = MaterialTheme.typography.labelMedium.copy(fontSize = 22.sp),
            )

            Degrees(
                currentWeather = currentWeather.description,
                currentTemp = currentWeather.temperature,
            )

            DetailWeather(
                iconId = R.drawable.ic_sunrise,
                title = stringResource(id = R.string.sun_rise),
                description = currentWeather.sunRise,
            )

            DetailWeather(
                iconId = R.drawable.ic_sunset,
                title = stringResource(id = R.string.sun_set),
                description = currentWeather.sunSet,
            )

            DetailWeather(
                iconId = R.drawable.ic_wind,
                title = stringResource(id = R.string.wind),
                description = stringResource(
                    id = R.string.home_text_meter_per_second,
                    currentWeather.windSpeed
                ),
            )

            DetailWeather(
                iconId = R.drawable.ic_humidity,
                title = stringResource(id = R.string.humidity),
                description = stringResource(
                    id = R.string.home_text_humidity,
                    currentWeather.humidity
                ),
            )
        }
    }
}

@Composable
fun DetailWeather(
    modifier: Modifier = Modifier,
    @DrawableRes iconId: Int,
    title: String,
    description: String,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.End,
    ) {
        Image(
            modifier = Modifier.size(30.dp),
            painter = painterResource(id = iconId),
            contentDescription = null,
        )

        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.padding(top = 5.dp),
        )

        Text(
            text = description,
            style = MaterialTheme.typography.headlineSmall,
        )
    }
}

@Composable
fun Degrees(
    modifier: Modifier = Modifier,
    currentTemp: String,
    currentWeather: String,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.End,
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = currentTemp,
                style = MaterialTheme.typography.displayLarge,
                modifier = Modifier.alignBy(LastBaseline),
            )

            Column(modifier = Modifier.alignBy(LastBaseline)) {
                Text(text = "o", modifier = Modifier.padding(bottom = 10.dp))

                Text(text = "c")
            }
        }

        Text(
            text = currentWeather,
            style = MaterialTheme.typography.labelMedium.copy(fontSize = 22.sp),
        )
    }
}