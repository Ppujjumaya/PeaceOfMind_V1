// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false

    // Firebase 관련 플러그인 추가 (아래 2줄 추가)
    alias(libs.plugins.google.gms) apply false
    // alias(libs.plugins.firebase.perf) apply false // Performance Monitoring 플러그인. 나중에 필요할 때 추가하자.
}