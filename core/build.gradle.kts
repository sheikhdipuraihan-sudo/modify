plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.ksp)                     // ✅ Add KSP plugin
}

android {
    namespace = "com.modify.music"               // Your actual namespace
    compileSdk = 35

    defaultConfig {
        minSdk = 24
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    // Room (Runtime + KTX + Compiler with KSP)
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)             // ✅ Essential – enables code generation

    // Hilt (if you use it in this module)
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)              // If you use Hilt in this module

    // Other dependencies you already have
    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.okhttp)
    // ... rest
}
