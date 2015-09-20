package com.nitishkasturia.gittracker;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.parse.FunctionCallback;
import com.parse.Parse;
import com.parse.ParseCloud;
import com.parse.ParseException;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 * Created by Nitish on 15-09-19.
 */
public class GitTracker {

    private static GitTracker mTracker = null;

    Context mContext = null;
    String mAccessToken = null;
    String mRepoName = null;

    public static GitTracker initialize(Context context, String accessToken, String repoName) {
        mTracker = new GitTracker(context, accessToken, repoName);
        return mTracker;
    }

    public static synchronized GitTracker getTracker() {
        return mTracker;
    }

    private GitTracker(Context context, String accessToken, String repoName) {
        this.mContext = context;
        this.mAccessToken = accessToken;
        this.mRepoName = repoName;

        Parse.enableLocalDatastore(mContext);
        Parse.initialize(mContext, "60WFWQec13XQz1Reupzu6cSahFiDeg8Hykkcl1iV", "hV3DqExT1CnZ8ORMP1O3vBU5Bz8yf6nMDIlHVCXP");

        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, Throwable e) {
                handleUncaughtException(thread, e);
            }
        });
    }

    public void handleUncaughtException(Thread thread, Throwable e) {
        e.printStackTrace();

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);

        String exception = sw.toString();
        try {
            String encodedException = URLEncoder.encode(exception, "UTF-8");
            Log.d("EXCEPTION_TEST", encodedException);

            HashMap<String, Object> params = new HashMap<>();
            params.put("access_token", mAccessToken);
            params.put("repo_name", mRepoName);
            params.put("name", "NAME");
            params.put("description", "DESCRIPTION");
            ParseCloud.callFunctionInBackground("issues", params, new FunctionCallback<String>() {
                @Override
                public void done(String s, ParseException e) {
                    Toast.makeText(mContext, s, Toast.LENGTH_LONG).show();
                }
            });

        } catch (UnsupportedEncodingException e1) {
            e1.printStackTrace();
        }

        //TODO: SEND LOG ASYNC AND CLOSE APP
        System.exit(1); // kill off the crashed app
    }
}

/*
HashMap<String, Object> params = new HashMap<>();
        params.put("access_token", mAccessToken);
        params.put("repo_name", mRepoName);
        params.put("name", "NAME");
        params.put("description", "DESCRIPTION");
        ParseCloud.callFunctionInBackground("issues", params, new FunctionCallback<String>() {
            @Override
            public void done(String s, ParseException e) {
                Toast.makeText(mContext, s, Toast.LENGTH_LONG).show();
            }
        });
 */