plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.hiltAndroid)
    alias(libs.plugins.kspAndroid)
    alias(libs.plugins.roomAndroid)
    id("com.google.gms.google-services")
}
room {
    schemaDirectory("$projectDir/schemas")
}
android {

    namespace = "dev.youdroid.basketbee"
    compileSdk = 34

    defaultConfig {
        applicationId = "dev.youdroid.basketbee"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.ui.icons)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.ui.fonts)
    implementation(libs.androidx.navigation)
    implementation(libs.androidx.ui.constrinatLayout)
    implementation(libs.androidx.splashscreen)
    implementation(libs.androidx.hilt)

    implementation(libs.swipe.ui)

    implementation(libs.androidx.room)
    implementation(libs.androidx.room.kts)
    implementation(libs.androidx.room.paging)
    implementation(libs.androidx.room.common)


    ksp(libs.androidx.room.compiler)

    ksp(libs.androidx.hilt.compiler)
    implementation(libs.androidx.hilt.fragment)
    implementation(libs.androidx.hilt.navigation)

    implementation(libs.coil.kt.core)
    implementation(libs.coil.kt.compose)
    implementation(libs.coil.kt.svg)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.firestore)
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.database)
    implementation(libs.firebase.auth)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}