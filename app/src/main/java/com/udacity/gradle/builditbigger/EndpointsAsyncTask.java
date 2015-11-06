package com.udacity.gradle.builditbigger;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.niren.myapplication.backend.myApi.MyApi;
import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import java.io.IOException;

/**
 * Created by niren on 10/25/15.
 */
class EndpointsAsyncTask extends AsyncTask<Context, Void, String> {
    public static final String JOKE_ERROR = "";
    private static MyApi myApiService = null;
    private Context context;
    private ResponseHandler responseHandler;

    public EndpointsAsyncTask(ResponseHandler handler){
        this.responseHandler = handler;
    }

    @Override
    protected String doInBackground(Context... params) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl("https://and-build-it-bigger.appspot.com/_ah/api/");
            // end options for devappserver

            myApiService = builder.build();
        }

        context = params[0];

        try {
            return myApiService.getJoke().execute().getData();
        } catch (IOException e) {
            Log.d("EndpointsAsyncTask", e.getMessage());
            return JOKE_ERROR;
        }
    }

    @Override
    protected void onPostExecute(String result) {
        responseHandler.handleResponse(result);
    }
}