package com.adam.livetrack.plugin

import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


internal class ConfigurationPlugin : Plugin<Project> {

    override fun apply(project: Project) = with(project) {
        // Run `./gradlew dependencyUpdates` for a report of used dependencies vs most recent versions
        if (!rootProject.pluginManager.hasPlugin("com.github.ben-manes.versions")) {
            rootProject.pluginManager.apply("com.github.ben-manes.versions")
        }

        configureKapt()
        configureRepositories()

        tasks.withType(KotlinCompile::class.java).all {
            it.kotlinOptions.jvmTarget = JavaVersion.VERSION_1_8.toString()
        }
    }
}

internal fun Project.configureKapt() {
    pluginManager.apply("kotlin-kapt")
    extensions.configure(org.jetbrains.kotlin.gradle.plugin.KaptExtension::class.java) {
        it.correctErrorTypes = true
        it.useBuildCache = true
        it.arguments {
            arg("dagger.gradle.incremental")
        }
    }
}

internal fun Project.configureRepositories() = with(repositories) {
    google()
    jcenter()
}
