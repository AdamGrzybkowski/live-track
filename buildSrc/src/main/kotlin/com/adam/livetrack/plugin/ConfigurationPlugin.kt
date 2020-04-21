package com.adam.livetrack.plugin

import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

internal class ConfigurationPlugin : Plugin<Project> {

    override fun apply(project: Project) = with(project) {
        pluginManager.apply(QualityPlugin::class.java)

        // Run `./gradlew dependencyUpdates` for a report of used dependencies vs most recent versions
        if (!rootProject.pluginManager.hasPlugin("com.github.ben-manes.versions")) {
            rootProject.pluginManager.apply("com.github.ben-manes.versions")
        }

        configureKapt()
        configureRepositories()

        tasks.withType(KotlinCompile::class.java).all {
            it.kotlinOptions.jvmTarget = JavaVersion.VERSION_1_8.toString()
            it.kotlinOptions.freeCompilerArgs += "-Xopt-in=kotlin.RequiresOptIn"
        }

        tasks.withType(org.gradle.api.tasks.testing.Test::class.java).all {
            it.useJUnitPlatform()
            it.dependsOn("cleanTest")
            it.testLogging { testLoggingContainer ->
                testLoggingContainer.events("passed", "skipped", "failed")
            }
        }
    }
}

internal fun Project.configureKapt() {
    pluginManager.apply("kotlin-kapt")
    extensions.configure(org.jetbrains.kotlin.gradle.plugin.KaptExtension::class.java) {
        it.correctErrorTypes = true
        it.useBuildCache = true
    }
}

internal fun Project.configureRepositories() = with(repositories) {
    google()
    jcenter()
}
