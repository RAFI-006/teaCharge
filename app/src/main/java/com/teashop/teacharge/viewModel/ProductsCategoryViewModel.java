package com.teashop.teacharge.viewModel;

import com.teashop.teacharge.Adapters.ProductCategoryAdapter;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.ViewModel;

public class ProductsCategoryViewModel extends ViewModel {
    public ProductCategoryAdapter mAdapter;
    public List<String> mDataList;

    @Inject
    public ProductsCategoryViewModel() {
    }


    public ProductCategoryAdapter getAdapter() {

        if (mAdapter == null) {
            mAdapter = new ProductCategoryAdapter(this);
        }
        return mAdapter;
    }

    public void setCategoryList(List<String> paymentHistorys) {
        mDataList = paymentHistorys;
        mAdapter.setList(mDataList);


    }
}