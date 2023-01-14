/** This file contains versions of all the dependencies used in the module  */

object Kotlin {
    const val KOTLIN_VERSION = "1.6.0"
    const val KTX_CORE_VERSION = "1.9.0"
    const val KT_STD = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${KOTLIN_VERSION}"
    private const val KTX_CORE = "androidx.core:core-ktx:${KTX_CORE_VERSION}"
    private const val DATE_TIME = "org.jetbrains.kotlinx:kotlinx-datetime:0.3.2"

    const val COROUTINES = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${KOTLIN_VERSION}"
    private const val COROUTINES_ANDROID =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${KOTLIN_VERSION}"

    val list = listOf(KT_STD, KTX_CORE, DATE_TIME, COROUTINES, COROUTINES_ANDROID)
}

object Google {
    const val MATERIAL_DESIGN = "com.google.android.material:material:1.4.0"

    val list = listOf(
        MATERIAL_DESIGN,
    )

}

object Androidx {
    // Compose
    const val composeVersion = "1.3.2"
    const val lifecycle = "2.5.1"
    const val lifecycleViewModel = "2.6.0-alpha03"
    const val material = "1.3.1"
    const val material3 = "1.1.0-alpha03"
    const val activityComposeVersion = "1.6.1"

}


object Accompanist {
    const val version = "0.28.0"
}

object Coil {
    const val version = "1.4.0"
}

object Di {
    const val hiltVersion = "2.44.2"
}

object Networking {
    const val retrofit = "2.9.0"
    const val gson = "2.9.0"
    const val logging = "4.10.0"
}

object UnitTesting {
    const val junit = "4.13.2"
    const val junit_Ext = "1.1.5"
    const val espresso = "3.5.1"
    const val MOCKITO_CORE = "4.6.1"
    const val MOCKITO_INLINE = "4.6.1"
    const val MOCKITO_ANDROID = "4.6.1"
    const val MOCKITO_KOTLIN = "2.2.0"
    const val MOCK_RETROFIT = "2.9.0"
    const val ARCH_CORE = "2.1.0"
    const val COMPOSE_TEST_VERSION = "1.3.2"
}