package io.thed.applevel.storage

import android.content.Context

object StorageManager {

    fun init(context: Context) {
        val db = AppDatabase.getDatabase(context)

    }
}