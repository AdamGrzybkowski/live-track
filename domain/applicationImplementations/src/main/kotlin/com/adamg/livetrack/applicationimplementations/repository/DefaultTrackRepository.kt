package com.adamg.livetrack.applicationimplementations.repository

import com.adamg.livetrack.business.entities.Track
import io.reactivex.Observable
import javax.inject.Inject

internal class DefaultTrackRepository @Inject constructor() : TrackRepository {

    override fun getTracks(): Observable<List<Track>> {
        return Observable.just(emptyList())
    }
}
