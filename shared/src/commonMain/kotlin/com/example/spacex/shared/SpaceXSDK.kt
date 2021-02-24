package com.example.spacex.shared

import com.example.spacex.shared.cache.Database
import com.example.spacex.shared.cache.DatabaseDriverFactory
import com.example.spacex.shared.entity.RocketLaunch
import com.example.spacex.shared.network.SpaceXApi

/**
 *
 *Created by Atef on 24/02/21
 *
 */
class SpaceXSDK(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = Database(databaseDriverFactory)
    private val api = SpaceXApi()


    @Throws(Exception::class)
    suspend fun getLaunches(forceReload: Boolean): List<RocketLaunch> {
        val cachedLaunches = database.getAllLaunches()
        return if (cachedLaunches.isNotEmpty() && !forceReload) {
            cachedLaunches
        } else {
            api.getAllLaunches().also {
                database.clearDatabase()
                database.createLaunches(it)
            }
        }
    }
}