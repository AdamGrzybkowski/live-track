package com.adamg.livetrack.applicationimplementations.usecase

import kotlinx.coroutines.flow.Flow

internal interface UseCase {

    interface Action<in TParam> {

        suspend fun execute(param: TParam)
    }

    interface Query<in TParam, TResult> : UseCase {

        fun execute(): Flow<TResult>
    }
}
