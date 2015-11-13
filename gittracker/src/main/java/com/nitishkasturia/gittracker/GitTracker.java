package com.nitishkasturia.gittracker;

import android.content.Context;
import android.util.Log;

import com.parse.Parse;
import com.parse.ParseCloud;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 * Created by Nitish on 15-09-19.
 */
public class GitTracker {

    private static GitTracker mTracker = null;

    private Context mContext = null;
    private String mAccessToken = null;
    private String mRepoName = null;

    public static GitTracker initialize(Context context, String accessToken, String repoName, String parseAppId, String parseClientId) {
        mTracker = new GitTracker(context, accessToken, repoName, parseAppId, parseClientId);
        return mTracker;
    }

    public static synchronized GitTracker getTracker() {
        return mTracker;
    }

    private GitTracker(Context context, String accessToken, String repoName, String parseAppId, String parseClientId) {
        this.mContext = context;
        this.mAccessToken = accessToken;
        this.mRepoName = repoName;

        Parse.enableLocalDatastore(mContext);
        Parse.initialize(mContext, parseAppId, parseClientId);

        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, Throwable exception) {
                handleUncaughtException(exception);
            }
        });

        //Search for old stack traces and upload
        try {
            uploadStackTraces();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void uploadStackTraces() throws IOException {
        FileInputStream fileInputStream;
        String[] fileList = mContext.fileList();

        for (String fileName : fileList) {
            Log.i("GitTracker", "UPLOADING STACKTRACE FILE: " + fileName);
            fileInputStream = mContext.openFileInput(fileName);

            StringBuilder builder = new StringBuilder();
            int ch;
            while ((ch = fileInputStream.read()) != -1) {
                builder.append((char) ch);
            }

            String stackTrace = builder.toString();

            String encodedAccessToken = URLEncoder.encode(mAccessToken, "UTF-8").replace("+", "%20");
            String encodedRepoName = URLEncoder.encode(mRepoName, "UTF-8").replace("+", "%20");
            String encodedName = URLEncoder.encode(fileName, "UTF-8").replace("+", "%20");
            String encodedDescription = URLEncoder.encode(stackTrace, "UTF-8").replace("+", "%20");

            HashMap<String, Object> params = new HashMap<>();
            params.put("access_token", encodedAccessToken);
            params.put("repo_name", encodedRepoName);
            params.put("name", encodedName);
            params.put("description", encodedDescription);

            ParseCloud.callFunctionInBackground("error", params);

            mContext.deleteFile(fileName);
        }
    }

    public void handleUncaughtException(Throwable exception) {
        try {
            PrintWriter pw = new PrintWriter(new OutputStreamWriter(mContext.openFileOutput(exception.toString(), 0)));
            exception.printStackTrace(pw);
            pw.flush();
            pw.close();
        } catch (FileNotFoundException e1) {
            // do nothing
        }

        System.exit(1);
    }

    public String getRepoName() {
        return mRepoName;
    }
}