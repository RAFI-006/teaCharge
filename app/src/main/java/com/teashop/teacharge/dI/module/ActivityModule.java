package com.teashop.teacharge.dI.module;

import com.teashop.teacharge.View.ProductsCategory;
import com.teashop.teacharge.dI.component.AppComponent;

import dagger.Module;

import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityModule {
    @ContributesAndroidInjector
    abstract ProductsCategory productsCategory();


}
