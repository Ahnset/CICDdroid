package com.icgen.retirementcalculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.icgen.retirementcalculator.Constants.APP_SECRET
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        appCenterSetup()

        onCalculateButtonClicked()
    }

    private fun onCalculateButtonClicked() {
        calculateButton.setOnClickListener {
//            throw Exception("Something wrong happened")
//            Crashes.generateTestCrash()

            trackInterestRateAnalytics()
            trackRetirementAgeAnalytics()
            updateResultTextView()
        }
    }

    private fun updateResultTextView() {
        val interestRate = interestEditText.text.toString().toFloat()
        resultTextView.text = "At the current rate of $interestRate ..."
    }

    private fun trackInterestRateAnalytics() {
        val interestRate = interestEditText.text.toString().toFloat()
        if (interestRate <= 0) Analytics.trackEvent("wrong_interest_rate")
    }

    private fun trackRetirementAgeAnalytics() {
        val currentAge = getCurrentAge()
        val retirementAge = getRetirementAge()
        if (retirementAge <= currentAge) Analytics.trackEvent("wrong_age")
    }

    private fun getCurrentAge() = ageEditText.text.toString().toInt()
    private fun getRetirementAge() = retirementEditText.text.toString().toInt()

    private fun appCenterSetup() {
        AppCenter.start(
            application, APP_SECRET,
            Analytics::class.java, Crashes::class.java
        )
    }

}
