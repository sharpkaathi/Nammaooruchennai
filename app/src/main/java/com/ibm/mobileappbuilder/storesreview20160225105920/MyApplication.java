

package com.ibm.mobileappbuilder.storesreview20160225105920;

import android.app.Application;
import ibmmobileappbuilder.injectors.ApplicationInjector;


/**
 * You can use this as a global place to keep application-level resources
 * such as singletons, services, etc.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ApplicationInjector.setApplicationContext(this);
    }
}

