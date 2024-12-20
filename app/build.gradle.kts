plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)

    //id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.mahin.sholpe"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.mahin.sholpe"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        viewBinding = true
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation (libs.retrofit)
    implementation (libs.converter.gson)
    implementation (libs.androidx.datastore.preferences)
    implementation (libs.kotlinx.coroutines.core)
    implementation (libs.kotlinx.coroutines.android)
    implementation (libs.androidx.recyclerview)
    implementation (libs.coil)
    implementation (libs.lottie)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.logging.interceptor)

    implementation(libs.firebase.bom)
    implementation(libs.firebase.analytics)

    implementation (libs.imageslideshow)

}