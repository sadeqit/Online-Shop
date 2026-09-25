import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.dagger.plugin)
}

android {
    namespace = "io.github.sadeghi.online_shop"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "io.github.sadeghi.online_shop"
        minSdk = 27
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        val localProperties = Properties()
        val localPropertiesFile = rootProject.file("local.properties")

        if (localPropertiesFile.exists()) {
            localPropertiesFile.inputStream().use {
                localProperties.load(it)
            }
        }

        val supabaseUrl = localProperties.getProperty("supabase.url")
            ?: error("supabase.url is missing in local.properties")

        val supabaseKey = localProperties.getProperty("supabase.key")
            ?: error("supabase.key is missing in local.properties")

        buildConfigField(
            "String",
            "SUPABASE_URL",
            "\"$supabaseUrl\""
        )

        buildConfigField(
            "String",
            "SUPABASE_KEY",
            "\"$supabaseKey\""
        )
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }

    //noinspection WrongGradleMethod
    kotlin {
        jvmToolchain(21)
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }


}


dependencies {
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)

    implementation(libs.coroutines)
    implementation(libs.coroutine)
    implementation(libs.identity)
    implementation(libs.retrofit)
    implementation(libs.gson)
    implementation(libs.gsons)
    implementation(libs.lifecycle)
    implementation(libs.hilt.android)
    implementation(libs.data)
    implementation(libs.navigation)
    implementation(libs.hilt.navigation.compose)

    implementation(libs.okhttp)

    implementation(libs.androidx.compose.material.icons.extended)

    implementation(libs.coil.compose)
    implementation(libs.coil.network.okhttp)
    implementation(libs.androidx.compose.ui.text)
    implementation(libs.androidx.benchmark.traceprocessor)


    ksp(libs.hilt.compiler)

    implementation(libs.androidx.constraintlayout)
    // To use constraintlayout in compose
    implementation(libs.androidx.constraintlayout.compose)
    implementation(libs.ucrop)
    implementation(libs.androidx.transition)
    implementation(libs.shamsi.picker)

    configurations.all {
        resolutionStrategy {
            force("androidx.browser:browser:1.9.0")
        }
    }

    implementation(libs.androidx.compose.animation)

    implementation(platform(libs.supabase.bom))


    // Supabase
    implementation(libs.supabase.auth)
    implementation(libs.supabase.postgrest)
    implementation(libs.supabase.storage)
    implementation(libs.supabase.realtime)

    // Ktor
    implementation(libs.ktor.client.android)

    implementation(libs.kotlinx.serialization.json)
    implementation(libs.retrofit.kotlinx.serialization)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(libs.androidx.compose.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.test.manifest)
}