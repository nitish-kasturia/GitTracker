package com.nitishkasturia.gittracker;

import android.content.Context;
import android.widget.Toast;

import com.parse.FunctionCallback;
import com.parse.Parse;
import com.parse.ParseCloud;
import com.parse.ParseException;

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

    public static GitTracker getTracker() {
        return mTracker;
    }

    private GitTracker(Context context, String accessToken, String repoName) {
        this.mContext = context;
        this.mAccessToken = accessToken;
        this.mRepoName = repoName;

        Parse.enableLocalDatastore(mContext);
        Parse.initialize(mContext, "60WFWQec13XQz1Reupzu6cSahFiDeg8Hykkcl1iV", "hV3DqExT1CnZ8ORMP1O3vBU5Bz8yf6nMDIlHVCXP");

        HashMap<String, Object> params = new HashMap<String, Object>();
        params.put("access_token", mAccessToken);
        ParseCloud.callFunctionInBackground("user", params, new FunctionCallback<String>() {
            @Override
            public void done(String s, ParseException e) {
                Toast.makeText(mContext, s, Toast.LENGTH_LONG).show();
            }
        });
    }
}