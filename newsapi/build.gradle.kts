plugins {
    id("java-library")
    alias(libs.plugins.jetbrainsKotlinJvm)
    alias(libs.plugins.kotlinSerialization)
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation(libs.retrofit)
    implementation(libs.koltinx.coroutines.core)
    implementation(libs.koltinx.serialization.json)
    implementation(libs.androidx.annotation)
    implementation(libs.retrofit.converter.kotlinx.serialization)
    implementation (libs.retrofit.adapters.result)

    implementation (libs.okhttp.logging.interceptor)
    api(libs.okhttp)
}