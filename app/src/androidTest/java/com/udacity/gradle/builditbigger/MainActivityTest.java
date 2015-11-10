package com.udacity.gradle.builditbigger;

import android.app.Activity;
import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.MediumTest;
import android.util.Log;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by niren on 10/25/15.
 */
public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> implements ResponseHandler{
    private boolean mRecievedJoke = false;

    // create  a signal to let us know when our task is done.
    final CountDownLatch mSignal = new CountDownLatch(1);

    public MainActivityTest() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        setActivityInitialTouchMode(true);
    }

    @MediumTest
    public void testEndpointsAsyncTask () {
        final Activity context = this.getActivity();
        final ResponseHandler responseHandler = this;

        try {
            runTestOnUiThread(new Runnable() {

                @Override
                public void run() {
                    new EndpointsAsyncTask(context, responseHandler).execute();
                }
            });
        } catch(Throwable t){
            assertTrue(mRecievedJoke);
        }

        try {
            mSignal.await(10, TimeUnit.SECONDS);
            assertTrue(mRecievedJoke);
        } catch(InterruptedException e) {
            assertTrue(mRecievedJoke);
        }
    }

    @Override
    public void handleResponse(String s) {
        if(!EndpointsAsyncTask.JOKE_ERROR.equals(s)){
            mRecievedJoke = true;
        }

        mSignal.countDown();
    }
}
