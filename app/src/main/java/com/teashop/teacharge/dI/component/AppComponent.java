package com.teashop.teacharge.dI.component;

import android.app.Application;
import com.teashop.teacharge.dI.module.ActivityModule;
import com.teashop.teacharge.dI.module.ViewModelModule;
import com.teashop.teacharge.dI.scope.UserScope;
import com.teashop.teacharge.utils.App;
import javax.inject.Singleton;
import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;


    @Singleton
    @Component(modules={AndroidSupportInjectionModule.class, ActivityModule.class,ViewModelModule.class})
    public interface AppComponent {

        @Component.Builder
        interface Builder {
            @BindsInstance
            Builder application(Application application);
            AppComponent build();

        }
        void inject(App app);
    }

