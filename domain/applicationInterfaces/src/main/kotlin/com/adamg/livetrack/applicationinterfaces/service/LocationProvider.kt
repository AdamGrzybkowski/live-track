package com.adamg.livetrack.applicationinterfaces.service

import com.adamg.livetrack.business.entities.Location
import kotlinx.coroutines.flow.Flow

interface LocationProvider {

    fun observeLocationChanges(): Flow<Location>
}
