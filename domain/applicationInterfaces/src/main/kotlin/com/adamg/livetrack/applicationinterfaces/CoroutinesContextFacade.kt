package com.adamg.livetrack.applicationinterfaces

import kotlin.coroutines.CoroutineContext

interface CoroutinesContextFacade {
    val io: CoroutineContext
    val main: CoroutineContext
    val default: CoroutineContext
}
