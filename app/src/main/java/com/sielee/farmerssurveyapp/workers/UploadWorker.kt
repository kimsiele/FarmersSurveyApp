package com.sielee.farmerssurveyapp.workers

import android.content.Context
import android.util.Log
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.sielee.farmerssurveyapp.data.database.SurveyDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class UploadWorker(appContext: Context, workerParameters: WorkerParameters):Worker(appContext, workerParameters) {
    val surveyDatabase = SurveyDatabase.getInstance(applicationContext)
    override fun doWork(): Result {
        uploadResponse()
        return Result.success()
    }

    private fun uploadResponse() {
        GlobalScope.launch {
            val responses = surveyDatabase.responseDao.getResponse()
            Log.d("WorkerClass", "Uploaded size::${responses.size} ")
        }

    }
}