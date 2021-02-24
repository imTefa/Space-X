package com.example.spacex.shared.cache

import com.squareup.sqldelight.db.SqlDriver

/**
 *
 *Created by Atef on 24/02/21
 *
 */
expect class DatabaseDriverFactory {
    fun createDriver(): SqlDriver
}
