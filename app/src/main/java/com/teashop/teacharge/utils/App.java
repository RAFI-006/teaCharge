package com.teashop.teacharge.utils;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.teashop.teacharge.dI.component.AppComponent;
import com.teashop.teacharge.dI.component.DaggerObjComponent;
import com.teashop.teacharge.dI.component.ObjComponent;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DaggerActivity;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

public class App extends Application implements HasActivityInjector {

    private static App app;
    private static AppComponent appComponent;
    private static ObjComponent objComponent;

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;
    @Override
    public void onCreate() {
        super.onCreate();

        app = this;
        appComponent=DaggerAppComponent
                .builder()
                .application(this)
                .build();
        appComponent.inject(this);

        // Use this to inject plain old objects if needed
        objComponent = DaggerObjComponent.builder().appComponent(appComponent).build();


    }
    public static Context getContext() {
        return app.getBaseContext();
    }


    @Override
    public AndroidInjector<Activity> activityInjector() {
        return null;
    }

    public static ObjComponent getObjComponent() {
        return objComponent;
    }

}
