import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    var kotlin_version: String by extra
    kotlin_version = "1.2.21"

    repositories {
        mavenCentral()
    }
    dependencies {
        classpath(kotlin("gradle-plugin", kotlin_version))
    }
}

group = "com.maciekjanusz"
version = "1.0-SNAPSHOT"

apply {
    plugin("kotlin")
    plugin("application")
}

plugins {
    application
    id("com.github.johnrengelman.shadow") version "1.2.3"
}

val kotlin_version: String by extra

application {
    mainClassName = "io.vertx.core.Launcher"
}

repositories {
    mavenCentral()
}

dependencies {
    compile(kotlin("stdlib-jdk8", kotlin_version))
    compile("io.vertx:vertx-core:3.5.1")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.withType<ShadowJar> {
    classifier = "fat"
    manifest {
        attributes["Main-Verticle"] = "com.maciekjanusz.vertxplayground.MainVerticle"
    }
    mergeServiceFiles {
        include("META-INF/services/io.vertx.core.spi.VerticleFactory")
    }
}