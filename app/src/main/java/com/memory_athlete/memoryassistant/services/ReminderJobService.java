package com.memory_athlete.memoryassistant.services;

import android.os.AsyncTask;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

import timber.log.Timber;

/**
 * Created by Manik on 26/08/17.
 */

public class ReminderJobService extends JobService {
    private final String LOG_TAG = getClass().getSimpleName();

    @Override
    public boolean onStartJob(final JobParameters jobParameters) {
        Timber.v("onStartJob() started");
        AsyncTask mBackgroundTask = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                Timber.v("entered doInBackground()");
                NotificationUtils.createNotification(ReminderJobService.this);
                //ReminderTask.executeTask(context, ReminderTask.);
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                Timber.v("entered onPostExecute()");
                jobFinished(jobParameters, false);
            }
        };
        mBackgroundTask.execute();
        return false;
    }

    @Override
    public boolean onStopJob(com.firebase.jobdispatcher.JobParameters job) {
        Timber.v("Entered onStopJob()");
        return false;
    }
}
