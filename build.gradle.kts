plugins {
    id("com.android.library") version "8.5.1" apply true
    id("org.jetbrains.kotlin.android") version "1.9.24" apply true
    `maven-publish`
}

android {
    namespace = "com.artembotnev.compose.text.utils"
    compileSdk = 34

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
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
    }
    publishing {
        singleVariant("release") {
            withSourcesJar()
        }
    }
}

dependencies {
    implementation(platform("androidx.compose:compose-bom:2022.10.00"))
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
