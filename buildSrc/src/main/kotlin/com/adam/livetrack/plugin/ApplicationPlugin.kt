package com.adam.livetrack.plugin

import org.gradle.api.Project

internal class ApplicationPlugin : BaseAndroidPlugin() {

    override fun apply(project: Project) {
        project.pluginManager.apply("com.android.application")
        super.apply(project)
    }
}
