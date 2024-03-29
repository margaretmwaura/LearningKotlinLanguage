package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.test.AndroidTestCase;
import android.test.mock.MockContext;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class EndpointsAsyncTaskTest extends AndroidTestCase {

    public void testEndpointsTask() throws InterruptedException,
            ExecutionException, TimeoutException {
        MockContext mockContext = new MockContext()
        {
            @Override
            public String getPackageName() {
                return "com.udacity.gradle.builditbigger";
            }

            @Override
            public void startActivity(Intent intent) {
            }
        };

        String result = new EndpointsAsyncTask()
                .execute(mockContext)
                .get(30, TimeUnit.SECONDS);

        //Test for not null String
        assertNotNull(result);

        //Test for not empty String
        assertFalse(result.length() == 0);
    }
}
