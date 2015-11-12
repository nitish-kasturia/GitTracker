package com.nitishkasturia.gittrackersample;

import android.app.Application;

import com.nitishkasturia.gittracker.GitTracker;

/**
 * Created by Nitish on 15-09-19.
 */
public class GitTrackerSample extends Application {

    private final String GITHUB_ACCESS_KEY = "YOUR GITHUB ACCESS KEY HERE";

    @Override
    public void onCreate() {
        super.onCreate();
        GitTracker.initialize(this, "GITHUB_ACCESS_KEY", "GitTracker");
    }
}