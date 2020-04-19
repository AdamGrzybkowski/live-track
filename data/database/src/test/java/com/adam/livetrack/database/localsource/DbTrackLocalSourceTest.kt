package com.adam.livetrack.database.localsource

import com.adam.livetrack.database.LiveTrackDb
import com.adam.livetrack.testing.test
import com.adamg.livetrack.applicationinterfaces.CoroutinesContextFacade
import com.adamg.livetrack.business.entities.Track
import com.adamg.livetrack.business.entities.TrackPoint
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.threeten.bp.Clock
import org.threeten.bp.Instant
import org.threeten.bp.ZoneOffset
import org.threeten.bp.ZonedDateTime

@OptIn(ExperimentalCoroutinesApi::class)
class DbTrackLocalSourceTest {

    private lateinit var driver: SqlDriver
    private lateinit var database: LiveTrackDb
    private lateinit var trackLocalSource: DbTrackLocalSource

    private val clock = Clock.fixed(Instant.parse("2011-12-03T10:15:30Z"), ZoneOffset.UTC)

    private val coroutinesContextFacade = object : CoroutinesContextFacade {
        override val io = Dispatchers.Unconfined
        override val main = Dispatchers.Unconfined
        override val default = Dispatchers.Unconfined
    }

    @BeforeEach
    fun setup() {
        driver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY).apply {
            LiveTrackDb.Schema.create(this)
        }

        database = LiveTrackDb(driver)

        trackLocalSource = DbTrackLocalSource(
            trackQueries = database.trackQueries,
            pointQueries = database.pointQueries,
            clock = clock,
            contextFacade = coroutinesContextFacade
        )
    }

    @AfterEach
    fun tearDown() {
        driver.close()
    }

    @Test
    fun `receives null when there is no live track`() = runBlocking {
        trackLocalSource.getLiveTrack()
            .test {
                assertEquals(null, expectItem())

                cancel()
            }
    }

    @Test
    fun `receives update with track when inserted`() = runBlocking {
        trackLocalSource.getLiveTrack()
            .test {
                assertEquals(null, expectItem())

                val track = createTrack(1)
                trackLocalSource.createTrack(track.startedAt)
                assertEquals(track, expectItem())

                cancel()
            }
    }

    @Test
    fun `emits updated track after point inserted`() = runBlocking {
        trackLocalSource.getLiveTrack()
            .test {
                assertEquals(null, expectItem())

                val track = createTrack(1)
                trackLocalSource.createTrack(track.startedAt)
                assertEquals(track, expectItem())

                val point = createTrackPoint(1)
                trackLocalSource.addTrackPoint(
                    trackId = track.id,
                    latitude = point.latitude,
                    longitude = point.longitude,
                    registeredAt = point.registeredAt
                )
                assertEquals(track.copy(points = listOf(point)), expectItem())

                cancel()
            }
    }

    private fun createTrack(identifier: Int): Track {
        return Track(
            id = identifier.toLong(),
            startedAt = ZonedDateTime.now(clock),
            finishedAt = null,
            points = emptyList()
        )
    }

    private fun createTrackPoint(identifier: Int): TrackPoint {
        return TrackPoint(
            id = identifier.toLong(),
            latitude = identifier.toFloat(),
            longitude = identifier.toFloat(),
            registeredAt = ZonedDateTime.now(clock)
        )
    }
}
