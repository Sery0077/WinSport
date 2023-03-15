package sery.vlasenko.winsport.utils

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import sery.vlasenko.winsport.R

class ProgressCleaner(private val context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {

    override fun doWork(): Result {
        return try {
            context.getSharedPreferences(context.getString(R.string.auth_prefs), Context.MODE_PRIVATE)
                .edit()
                .putInt(context.getString(R.string.auth_progress), 0)
                .apply()

            Result.success()
        } catch (e: Exception) {

            Result.retry()
        }
    }
}