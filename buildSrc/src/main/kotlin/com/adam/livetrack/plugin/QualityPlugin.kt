package com.adam.livetrack.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jmailen.gradle.kotlinter.KotlinterExtension
import org.jmailen.gradle.kotlinter.KotlinterPlugin

class QualityPlugin : Plugin<Project> {

    override fun apply(project: Project) = with(project) {
        configureKtlint()
    }
}

internal fun Project.configureKtlint() {
    afterEvaluate {
        pluginManager.apply(KotlinterPlugin::class.java)
        extensions.configure(KotlinterExtension::class.java) {
            it.indentSize = 4
            it.continuationIndentSize = 4
            it.reporters = arrayOf("plain")
            it.experimentalRules = false // TODO enable?
            it.disabledRules = arrayOf("import-ordering") // TODO enable?
        }
    }
}
