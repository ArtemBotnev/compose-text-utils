plugins {
    id("com.android.library") version "8.0.1" apply true
    id("org.jetbrains.kotlin.android") version "1.8.21" apply true
    id("maven-publish")
}

android {
    namespace = "com.artembotnev.text"
    compileSdk = 33

    defaultConfig {
        minSdk = 24
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        named("release") {
            isMinifyEnabled = true
            setProguardFiles(listOf(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"))
        }
    }
    java {
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
        kotlinCompilerExtensionVersion = "1.4.7"
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.10.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation(platform("androidx.compose:compose-bom:2022.10.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.material3:material3")
}

publishing {
    publications {
        register<MavenPublication>("release") {
            groupId = "com.github.ArtemBotnev"
            artifactId = "compose-text-utils"
            version = "1.0.0"

            afterEvaluate {
                from(components["release"])
            }
        }
    }
}
