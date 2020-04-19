package com.adamg.livetrack.applicationimplementations

import com.adamg.livetrack.applicationinterfaces.CoroutinesContextFacade
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

internal class DefaultContextFacade @Inject constructor() : CoroutinesContextFacade {
    override val io = Dispatchers.IO
    override val main = Dispatchers.Main
    override val default = Dispatchers.Default
}
