package com.example.projectakhir

import android.app.Application
import com.example.projectakhir.container.AppContainer
import com.example.projectakhir.container.KursusContainer

class KursusApplications:Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = KursusContainer()
    }
}