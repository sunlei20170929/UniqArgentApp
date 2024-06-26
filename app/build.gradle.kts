plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
//    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.store.pacific.stage"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.store.pacific.stage"
        minSdk = 23
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        resourceConfigurations += arrayOf("en", "en-rGB", "fr")

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    androidResources {
        generateLocaleConfig = false
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

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2024.02.01"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material:material")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
    implementation("androidx.navigation:navigation-compose:2.7.7")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    //retrofit
    implementation ("com.google.code.gson:gson:2.10.1")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.5")

    //webview
    implementation ("com.google.accompanist:accompanist-webview:0.30.1")

    //hilt
//    implementation("com.google.dagger:hilt-android:2.44.2")
//    implementation("androidx.hilt:hilt-navigation-fragment:1.0.0")
//    kapt("com.google.dagger:hilt-android-compiler:2.44.2")
//    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")

    //workmanager
    val work_version = "2.9.0"
    implementation("androidx.work:work-runtime-ktx:$work_version")
    implementation ("androidx.work:work-multiprocess:$work_version")

    //coil
    implementation("io.coil-kt:coil:1.3.2")

    //location
    implementation ("com.google.android.gms:play-services-location:17.0.0")
    //splash
    implementation("androidx.core:core-splashscreen:1.0.0")

    //flow and livedate to state
    implementation("androidx.compose.runtime:runtime-livedata:1.6.1")
    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.6.2")
    //谷歌广告id
//    implementation ("com.google.android.gms:play-services-ads:19.7.0")
    implementation(kotlin("reflect"))

    //data class to Map
    implementation ("org.jetbrains.kotlin:kotlin-reflect")

    implementation ("io.github.vincent-series:sharp-retrofit:1.9")
//    implementation ("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.10.2")

}