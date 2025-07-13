import org.jreleaser.model.Active

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    id("org.jreleaser") version "1.19.0"
    id("maven-publish")
    id("signing")
}

android {
    namespace = "com.maskaravivek.android.library.example"
    compileSdk = 35

    defaultConfig {
        aarMetadata {
            minCompileSdk = 35
        }
        minSdk = 24
        proguardFiles(
            "consumer-proguard-rules.pro"
        )
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    lint {
        textReport = true
    }
}

dependencies {
    // Test dependencies
    testImplementation(libs.festandroid)
    testImplementation(libs.festassert)
    testImplementation(libs.junit)
    testImplementation(libs.robolectric)
}

// Deploy

android {
    publishing {
        singleVariant("release") {
            withSourcesJar()
            withJavadocJar()
        }
    }
}

// Use environment variable for version, fallback to gradle.properties
version = System.getenv("LIBRARY_VERSION") ?: properties["VERSION_NAME"].toString()
description = properties["POM_DESCRIPTION"].toString()

publishing {
    publications {
        create<MavenPublication>("release") {
            groupId = properties["GROUP"].toString()
            artifactId = properties["POM_ARTIFACT_ID"].toString()

            pom {
                name.set(project.properties["POM_NAME"].toString())
                description.set(project.properties["POM_DESCRIPTION"].toString())
                url.set("https://github.com/CIRCLECI-GWP/android-maven-library-circleci-example")
                issueManagement {
                    url.set("https://github.com/CIRCLECI-GWP/android-maven-library-circleci-example/issues")
                }

                scm {
                    url.set("https://github.com/CIRCLECI-GWP/android-maven-library-circleci-example")
                    connection.set("scm:git@github.com:CIRCLECI-GWP/android-maven-library-circleci-example.git")
                    developerConnection.set("scm:git@github.com:CIRCLECI-GWP/android-maven-library-circleci-example.git")
                }

                licenses {
                    license {
                        name.set("The Apache Software License, Version 2.0")
                        url.set("http://www.apache.org/licenses/LICENSE-2.0.txt")
                        distribution.set("repo")
                    }
                }

                developers {
                    developer {
                        id.set("maskaravivek")
                        name.set("Vivek Maskara")
                        email.set("maskaravivek")
                        url.set("https://maskaravivek.com/")
                    }
                }

                afterEvaluate {
                    from(components["release"])
                }
            }
        }
    }
    repositories {
        maven {
            setUrl(layout.buildDirectory.dir("staging-deploy"))
        }
    }
}

jreleaser {
    project {
        inceptionYear = "2024"
        author("@maskaravivek")
    }
    gitRootSearch = true
    signing {
        active = Active.ALWAYS
        armored = true
        verify = true
        secretKey = System.getenv("JRELEASER_GPG_SECRET_KEY")
        password = System.getenv("JRELEASER_GPG_PASSPHRASE")
    }

    release {
        github {
            skipTag = true
            sign = true
            branch = "main"
            branchPush = "main"
            overwrite = true
        }
    }
    deploy {
        maven {
            mavenCentral.create("sonatype") {
                active = Active.ALWAYS
                url = "https://central.sonatype.com/api/v1/publisher"
                stagingRepository(layout.buildDirectory.dir("staging-deploy").get().toString())
                setAuthorization("Basic")
                applyMavenCentralRules = false // Wait for fix: https://github.com/kordamp/pomchecker/issues/21
                sign = true
                checksums = true
                sourceJar = true
                javadocJar = true
                retryDelay = 60
            }
        }
    }
}
