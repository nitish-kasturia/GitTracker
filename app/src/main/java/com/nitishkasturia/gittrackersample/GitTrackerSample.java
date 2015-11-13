package com.nitishkasturia.gittrackersample;

import android.app.Application;

import com.nitishkasturia.gittracker.GitTracker;

/**
 * Created by Nitish on 15-09-19.
 */
public class GitTrackerSample extends Application {

    private final String GITHUB_ACCESS_KEY = "GITHUB ACCESS KEY";
    private final String GITHUB_REPO_NAME = "GITHUB REPOSITORY NAME";
    private final String PARSE_APP_ID = "PARSE APPLICATION ID";
    private final String PARSE_CLIENT_ID = "PARSE CLIENT ID";

    @Override
    public void onCreate() {
        super.onCreate();
        GitTracker.initialize(this, GITHUB_ACCESS_KEY, GITHUB_REPO_NAME, PARSE_APP_ID, PARSE_CLIENT_ID);
    }
}