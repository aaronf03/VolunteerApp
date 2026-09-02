plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.volunteerapp"
    compileSdk = 37

    defaultConfig {
        applicationId = "com.example.volunteerapp"
        minSdk = 27
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"
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
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    // Glide: used to load the volunteer/community photography referenced by
    // the Figma design directly from URLs (self-taught - not covered in
    // lecture). See comments in each Activity's onCreate().
    implementation(libs.glide)
}
