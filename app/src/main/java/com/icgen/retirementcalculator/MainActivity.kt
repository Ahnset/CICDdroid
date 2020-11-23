package com.icgen.retirementcalculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        appCenterSetup()
    }

    private fun appCenterSetup() {
        AppCenter.start(
            application, "d71638f5-3230-4490-89cf-189bed7a88e4",
            Analytics::class.java, Crashes::class.java
        )
    }

}
