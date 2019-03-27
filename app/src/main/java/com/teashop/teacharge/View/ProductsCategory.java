package com.teashop.teacharge.View;
import android.app.Activity;
import android.os.Bundle;
import com.teashop.teacharge.R;
import com.teashop.teacharge.viewModel.ProductsCategoryViewModel;
import com.teashop.teacharge.databinding.ActivityItemlistBinding;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import dagger.android.AndroidInjection;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class ProductsCategory extends AppCompatActivity implements HasActivityInjector {

  private ActivityItemlistBinding mBinding;

    @Inject
    ViewModelProvider.Factory viewModelFactory;
    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;

    private ProductsCategoryViewModel mViewModel;
    RecyclerView recyclerView,horizontalRecyclerView;

   public List<String> productCategoryList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itemlist);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_itemlist);
        mViewModel = ViewModelProviders.of(this,viewModelFactory).get(ProductsCategoryViewModel.class);
        mBinding.setViewModel(mViewModel);

        InitRecyclerView();

    }

      void InitRecyclerView()
      {
          productCategoryList = new ArrayList<String>();
          productCategoryList.add("Tea");
          productCategoryList.add("Coffee");
          productCategoryList.add("Milk Shake");
          productCategoryList.add("Juice");

     mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL, false));

     mBinding.recyclerView.setAdapter(mViewModel.getAdapter());
          mViewModel.setCategoryList(productCategoryList);

              // horizontalRecyclerView=(RecyclerView)findViewById(R.id.horizontal_recyclerView);
//
//          recyclerView.setLayoutManager(new LinearLayoutManager(this));
//          horizontalRecyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
//
//          horizontalRecyclerView.setAdapter(new SubCategoryAapter(productCategoryList,getApplicationContext()));
//          recyclerView.setAdapter(new ProductCategoryAdapter(productCategoryList, R.layout.category_list_item, getApplicationContext()));

      }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }
}
