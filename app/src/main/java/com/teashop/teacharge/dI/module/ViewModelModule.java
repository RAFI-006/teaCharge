package com.teashop.teacharge.dI.module;
import com.teashop.teacharge.dI.scope.UserScope;
import com.teashop.teacharge.dI.scope.ViewModelScope;
import com.teashop.teacharge.viewModel.ProductsCategoryViewModel;
import androidx.lifecycle.ViewModel;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;

@Module
public abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelScope(ProductsCategoryViewModel.class)
    abstract ViewModel bindProductsCategoryViewModel(ProductsCategoryViewModel viewModel);

}
