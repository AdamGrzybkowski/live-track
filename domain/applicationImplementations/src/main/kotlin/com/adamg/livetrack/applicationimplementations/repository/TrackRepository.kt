package com.adamg.livetrack.applicationimplementations.repository

import com.adamg.livetrack.business.entities.Track
import io.reactivex.Observable

interface TrackRepository {

    fun getTracks(): Observable<List<Track>>
}
