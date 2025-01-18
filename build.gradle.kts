plugins {
    kotlin("plugin.serialization") version "2.0.0"
    id("com.android.library") version "8.8.0"
    id("org.jetbrains.kotlin.android") version "1.9.0"
    id("maven-publish")
}

val ktor_version = "1.6.3"

android {
    namespace = "com.example.zarinpal"
    compileSdk = 34

    publishing {
        singleVariant("release") {
            withSourcesJar()
        }
    }

    defaultConfig {
        aarMetadata {
            minCompileSdk = 24
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.zarinpal"
            artifactId = "zarinPal-library"
            version = "1.0.0"

            afterEvaluate {
                from(components["release"])
            }
            artifact("$buildDir/outputs/aar/zarinPal-library-release.aar")

            pom {
                dependencies {
                    implementation("io.ktor:ktor-client-core:$ktor_version")
                    implementation("io.ktor:ktor-client-android:$ktor_version")
                    implementation("io.ktor:ktor-client-serialization:$ktor_version")
                    implementation("io.ktor:ktor-client-logging:$ktor_version")
                    implementation("ch.qos.logback:logback-classic:1.2.3")
                    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0")
                }
            }
        }
    }

    repositories {
        maven {
            url = uri(layout.buildDirectory.dir("repo"))
        }
    }
}

dependencies {
    api("io.ktor:ktor-client-core:$ktor_version") {
        isTransitive = true
    }
    api("io.ktor:ktor-client-android:$ktor_version") {
        isTransitive = true
    }
    api("io.ktor:ktor-client-serialization:$ktor_version") {
        isTransitive = true
    }
    api("io.ktor:ktor-client-logging:$ktor_version") {
        isTransitive = true
    }
    api("ch.qos.logback:logback-classic:1.2.3") {
        isTransitive = true
    }
    api("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0") {
        isTransitive = true
    }
    implementation("androidx.core:core-ktx:1.13.0")
}

tasks.register<Copy>("copyLibs") {
    from(configurations.getByName("implementation"))
    into("libs")
}

