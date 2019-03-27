package com.teashop.teacharge.dI.component;

import com.teashop.teacharge.dI.scope.UserScope;

import dagger.Component;

@UserScope
@Component(dependencies = AppComponent.class)
public interface ObjComponent {
    void inject(Object obj);
}
