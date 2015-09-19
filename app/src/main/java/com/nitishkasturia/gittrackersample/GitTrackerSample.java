package com.nitishkasturia.gittrackersample;

import android.app.Application;

import com.nitishkasturia.gittracker.GitTracker;

/**
 * Created by Nitish on 15-09-19.
 */
public class GitTrackerSample extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        GitTracker.initialize(this, "05abc9eab69e3b38dede24e0c148626d0ce09cab", "GitTracker");
    }
}