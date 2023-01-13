package io.rrohaill.cleanweatherapp.common.compose

import android.Manifest
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun askLocationPermission(): PermissionState {
    //permission state
    val locationPermissionState = rememberPermissionState(
        Manifest.permission.ACCESS_COARSE_LOCATION,
    )

    //ask permission
    SideEffect {
        locationPermissionState.launchPermissionRequest()
    }

    return locationPermissionState
}