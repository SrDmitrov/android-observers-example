package es.rudo.connectivitymanagerexample.flow

import kotlinx.coroutines.flow.Flow

/**
 * This facilitates abstraction
 */
interface FlowConnectivityObserver {
    /**
     * Emits a value every time the status changes
     */
    fun observe(): Flow<Status>

    enum class Status {
        Available, Unavailable, Losing, Lost
    }
}