package sery.vlasenko.winsport

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import sery.vlasenko.winsport.utils.CurrentUser
import sery.vlasenko.winsport.utils.ProgressCleaner
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val progressCleaner = PeriodicWorkRequestBuilder<ProgressCleaner>(1, TimeUnit.DAYS).build()
        WorkManager.getInstance(this).enqueue(progressCleaner)

        CurrentUser.progress.observe(this) {
            getSharedPreferences(getString(R.string.auth_prefs), Context.MODE_PRIVATE)
                .edit()
                .putInt(getString(R.string.auth_progress), it)
                .apply()
        }
    }
}