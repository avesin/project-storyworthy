plugins {
    alias(core.plugins.android.application)
    alias(core.plugins.ksp)
    alias(core.plugins.hilt)
    alias(core.plugins.compose)
}

android {
    namespace = "com.jadigaknih.storyworthy"
    compileSdk {
        version = release(36) {
            minorApiLevel = 1
        }
    }


    defaultConfig {
        applicationId = "com.jadigaknih.storyworthy"
        minSdk = 24
        targetSdk = 36
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
        viewBinding = true
    }
}

dependencies {
    implementation(core.androidx.core.ktx)
    implementation(core.androidx.appcompat)
    implementation(core.material)
    implementation(core.androidx.constraintlayout)
    implementation(core.androidx.navigation.fragment.ktx)
    implementation(core.androidx.navigation.ui.ktx)

    /** Jetpack Compose ***/
    implementation(platform(core.compose.bom))

    implementation(core.compose.ui)
    implementation(core.compose.material3)
    implementation(core.compose.activity)
    implementation(core.compose.ui.preview)
    implementation(core.compose.navigation.material)

    implementation(core.lifecycle.runtime)

    /** Coroutines ***/
    implementation(core.coroutines.android)

    /** Room ***/
    implementation(core.room.runtime)
    implementation(core.room.ktx)
    ksp(core.room.compiler)

    /** Hilt ***/
    implementation(core.hilt.android)
    ksp(core.hilt.compiler)
    implementation(core.hilt.navigation.compose)

    /** Library ***/
    implementation(libs.compose.kizitonwose.calendar)

    /** Test ***/
    testImplementation(core.junit)
    androidTestImplementation(core.androidx.junit)
    androidTestImplementation(core.espresso)
}