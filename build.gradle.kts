plugins {
    kotlin("plugin.serialization") version "2.0.0"
    id("com.android.library") version "8.8.0"
    id("org.jetbrains.kotlin.android") version "1.9.0"
    id("maven-publish")
    id("org.jetbrains.dokka") version "1.8.20"
}

val ktorVersion = "1.6.3"
val libraryVersion = "1.0.1"


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

tasks.register<Jar>("javadocJar") {
    archiveClassifier.set("javadoc")
    from(tasks.dokkaJavadoc.get().outputDirectory)
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.example"
            artifactId = "zarinpal"
            version = libraryVersion

            afterEvaluate {
                from(components["release"])
            }
            artifact(tasks["javadocJar"]) {
                classifier = "javadoc"
            }

            pom {
                dependencies {
                    implementation("io.ktor:ktor-client-core:$ktorVersion")
                    implementation("io.ktor:ktor-client-android:$ktorVersion")
                    implementation("io.ktor:ktor-client-serialization:$ktorVersion")
                    implementation("io.ktor:ktor-client-logging:$ktorVersion")
                    implementation("ch.qos.logback:logback-classic:1.2.3")
                    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0")
                }

            }
        }
    }

    repositories {
        maven {
            url = uri("https://maven.pkg.github.com/alirezabashi98/zarinpal-sdk")
            credentials {
                credentials.username = System.getenv("GITHUB_USER")
                credentials.password = System.getenv("GITHUB_TOKEN")
            }
        }
    }
}
dependencies {
    implementation("io.ktor:ktor-client-core:$ktorVersion")
    implementation("io.ktor:ktor-client-android:$ktorVersion")
    implementation("io.ktor:ktor-client-serialization:$ktorVersion")
    implementation("io.ktor:ktor-client-logging:$ktorVersion")
    implementation("ch.qos.logback:logback-classic:1.2.3")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.0")
    implementation("androidx.core:core-ktx:1.13.0")
}

tasks.register<Copy>("copyLibs") {
    from(configurations.getByName("implementation"))
    into("libs")
}