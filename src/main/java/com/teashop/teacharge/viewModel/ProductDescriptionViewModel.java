package com.teashop.teacharge.viewModel;

import com.teashop.teacharge.Model.SubCategoryModel;
import com.teashop.teacharge.Repository.TransactionHistoryRepository;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProductDescriptionViewModel extends ViewModel{

    public MutableLiveData<SubCategoryModel> productDescriptionModelObservable=new MutableLiveData<>();


public MutableLiveData<List<SubCategoryModel>> productList=new MutableLiveData<>();
    List<SubCategoryModel> product;

    public ProductDescriptionViewModel(List<SubCategoryModel> product)
    {
        this.product=product;
    }



    public LiveData<List<SubCategoryModel>> getProductDescription(int categoryId) {

        productList.setValue(product);

        return  productList;


    }

//    public  void fillModel()
//    {
//        productDescriptionModelObservable.setValue(mProductDescriptionRepository.getProductDescription(1).getValue().get(0));
//    }






}
