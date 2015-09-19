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
        GitTracker.initialize(this, "9496bba2396141bc3775d00210d57f0ca8653b60", "GitTracker");
    }
}