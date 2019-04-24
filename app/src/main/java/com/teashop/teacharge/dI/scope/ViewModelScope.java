package com.teashop.teacharge.dI.scope;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import androidx.lifecycle.ViewModel;
import dagger.MapKey;


public interface ViewModelScope {
    Class<? extends ViewModel> value();
}
