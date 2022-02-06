import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    kotlin("multiplatform")
    id("com.android.library")
    `maven-publish`
}

kotlin {
    android()
    
    val xcf = XCFramework("GithubKMMModule")
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64() //sure all ios dependencies support this target
    ).forEach {
        it.binaries.framework {
            baseName = "GithubKMMModule"
            xcf.add(this)
        }
    }

    sourceSets {
        val commonMain by getting
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }
        val androidMain by getting
        val androidTest by getting {
            dependencies {
                implementation(kotlin("test-junit"))
                implementation("junit:junit:4.13.2")
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }

    android {
        publishLibraryVariants("release", "debug")
        publishLibraryVariantsGroupedByFlavor = true
    }
}

android {
    compileSdk = 31
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 24
        targetSdk = 31
    }
}

publishing {
    repositories {
        maven {
            url = uri("repo")
        }
    }
    publications {
        version = "0.0.1"
        group = "com.yochidros.githubkmmmodule"
        create<MavenPublication>("maven") {
            from(components["kotlin"])
            this.version = "0.0.1"
            this.groupId = "com.yochidros.githubkmmmodule"
            this.pom {
                version = "0.0.1"
                groupId = "com.yochidros"
            }
        }
    }
}