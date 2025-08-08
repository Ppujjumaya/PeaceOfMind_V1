plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.google.gms) // Firebase Google Services 플러그인
    // alias(libs.plugins.firebase.perf) // Performance Monitoring 플러그인. 추가했다면 여기도 추가
    // kotlinx.serialization 플러그인 추가
    alias(libs.plugins.kotlin.serialization)
    //id("kotlin-kapt")
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.hilt)

}

android {
    namespace = "com.pjmy.project.peaceofmind"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.pjmy.project.peaceofmind"
        minSdk = 21
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
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
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)


    // Firebase BoM (Bill of Materials) 추가
    implementation(platform(libs.firebase.bom))

    // Firebase SDKs
    implementation(libs.firebase.firestore.ktx) // REQ-005
    implementation(libs.firebase.storage.ktx)   // REQ-005
    implementation(libs.firebase.analytics.ktx) // REQ-001

    // 선택 사항 (필요할 때 주석 해제)
    // implementation(libs.firebase.auth.ktx)
    // implementation(libs.firebase.perf.ktx)
    // implementation(libs.firebase.crashlytics.ktx)
    // implementation(libs.play.services.ads)
    // Kotlinx Serialization (JSON 파싱용)
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3")

    implementation("io.coil-kt:coil-compose:2.6.0")
    // navigation 라이브러리에 대한 추가
    implementation(libs.androidx.navigation.compose)

    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)
    implementation(libs.hilt.navigation.compose)
}