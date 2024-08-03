plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.coworking.tehhizmat"
    compileSdk = 34

    lint {
        baseline = file("lint-baseline.xml")
    }

    defaultConfig {
        applicationId = "com.coworking.tehhizmat"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        multiDexEnabled = true

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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {



    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    implementation(libs.firebase.auth)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation("mysql:mysql-connector-java:5.1.49")
    implementation("androidx.compose.ui:ui-text-google-fonts:1.6.8")
    implementation("com.github.gcacace:signature-pad:1.3.1")


}