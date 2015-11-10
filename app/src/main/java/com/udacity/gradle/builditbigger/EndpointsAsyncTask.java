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
class EndpointsAsyncTask extends AsyncTask<String, Void, String> {
    public static final String JOKE_ERROR = "";
    private static MyApi myApiService = null;
    private Context context;
    private ResponseHandler responseHandler;

    public EndpointsAsyncTask(Context context, ResponseHandler handler){
        this.responseHandler = handler;
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {
        if(myApiService == null) {  // Only do this once
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(), new AndroidJsonFactory(), null)
                    .setRootUrl(context.getString(R.string.backend_url));
            // end options for devappserver

            myApiService = builder.build();
        }

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