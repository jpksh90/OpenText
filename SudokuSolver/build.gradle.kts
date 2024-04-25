plugins {
    kotlin("jvm") version "1.9.23"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
}

application {
    mainClass = "org.example.Main"
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}
