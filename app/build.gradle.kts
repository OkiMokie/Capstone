plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.elevateretailapp"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.elevateretailapp"
        minSdk = 27
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {

    // Replace old support libraries with AndroidX equivalents
    implementation(libs.appcompat) // This references androidx.appcompat
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation ("androidx.work:work-runtime:2.9.0")
    implementation ("androidx.core:core:1.12.0")
    implementation ("com.facebook.shimmer:shimmer:0.5.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("com.github.bumptech.glide:glide:4.16.0")
    annotationProcessor("com.github.bumptech.glide:compiler:4.16.0")

    // Remove old support library
    // implementation("com.android.support:appcompat-v7:28.0.0")  <-- Removed this line

    implementation(libs.volley)

    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
