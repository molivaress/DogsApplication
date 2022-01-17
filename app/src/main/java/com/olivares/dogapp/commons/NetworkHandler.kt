package com.olivares.dogapp.commons

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class NetworkHandler @Inject constructor(@ApplicationContext private val context: Context) {
    val isConnected get() = context.networkInfo?.isConnected
}