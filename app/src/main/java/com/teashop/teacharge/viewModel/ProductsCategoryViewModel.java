package com.teashop.teacharge.viewModel;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.teashop.teacharge.Adapters.ProductCategoryAdapter;
import com.teashop.teacharge.Adapters.SubCategoryHorizontalAdapter;
import com.teashop.teacharge.handlers.CategoryClickHandler;
import com.teashop.teacharge.DataUtils.BaseApi;
import com.teashop.teacharge.Fragment.ProductDescriptionFragment;
import com.teashop.teacharge.Model.CategoryModel;
import com.teashop.teacharge.Model.SubCategoryModel;
import com.teashop.teacharge.Repository.ApiRepository;
import com.teashop.teacharge.Repository.TransactionHistoryRepository;
import com.teashop.teacharge.handlers.SubCategoryClickHandler;

import java.lang.reflect.Type;
import java.util.List;
import javax.inject.Inject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductsCategoryViewModel extends ViewModel implements View.OnClickListener,CategoryClickHandler,SubCategoryClickHandler {
    public ProductCategoryAdapter mAdapter;
    public  SubCategoryHorizontalAdapter mSubCategoryAdaper;
    public List<CategoryModel> mDataList;
    int getPosition=0;
    public List<SubCategoryModel> mSubDataList;
 String Categoryposition;
    static ApiRepository repo;
Context ctx;
    private MutableLiveData<List<CategoryModel>> categoryList=new MutableLiveData<>();
    private MutableLiveData<List<SubCategoryModel>> subCategoryList=new MutableLiveData<>();
 TransactionHistoryRepository mProductDescriptionRepository;
    @Inject
    public ProductsCategoryViewModel(Context ctx) {
        this.ctx=ctx;
    }

    private   void   CategoryList()
    {
        repo=BaseApi.getClient().create(ApiRepository.class);
       Call<JsonObject> call=repo.GetCategories();
       call.enqueue(new Callback<JsonObject>() {
           @Override
           public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

               Log.d("dorami", "response: "+response);
               JsonObject jsonObject=response.body();
               Type dataType = new TypeToken<List<CategoryModel>>() {
               }.getType();
               Log.d("dorami", "onResponse: "+jsonObject);

               Gson gson=new GsonBuilder().create();
               List<CategoryModel> listcategory=gson.fromJson(jsonObject.getAsJsonArray("data"),dataType);
               Log.d("dorami", "onResponse: "+listcategory);


               categoryList.setValue(listcategory);

               subCategoryList.setValue(listcategory.get(0).getProducts());
           }

           @Override
           public void onFailure(Call<JsonObject> call, Throwable t) {

           }
       });


    }

    public LiveData<List<CategoryModel>> getCategoryList() {

        CategoryList();

        return categoryList;
    }

    public LiveData<List<SubCategoryModel>> getSubCategoryList() {


        return subCategoryList;
    }




    public ProductCategoryAdapter getAdapter() {

        if (mAdapter == null) {
            mAdapter = new ProductCategoryAdapter(this,this,this);
        }
        return mAdapter;
    }

    public SubCategoryHorizontalAdapter getSubCateoryAdapter(Context ctx) {

        if (mSubCategoryAdaper == null) {
            mSubCategoryAdaper = new SubCategoryHorizontalAdapter(this,this,ctx);
        }
        return mSubCategoryAdaper;
    }



    public void setSubCategoryList(List<SubCategoryModel> paymentHistorys) {
        mSubDataList = paymentHistorys;

        mSubCategoryAdaper.setList(mSubDataList,mDataList);


    }


    public void setCategoryList(List<CategoryModel> paymentHistorys) {
        mDataList = paymentHistorys;

        mAdapter.setList(mDataList);


    }

    @Override
    public void onClick(View view) {


        Log.d("dorami", "onClick: "+"working");
    }

    @Override
    public void CategoryClcikEvent(int positon) {
        subCategoryList.setValue(categoryList.getValue().get(positon).getProducts());
        getPosition=positon;

       }



    @Override
    public void SubCategoryProductDetails(View view,int itemPosition) {

        Categoryposition=categoryList.getValue().get(getPosition).getCategoryId();

        int position=Integer.parseInt(Categoryposition);
        Bundle bundle=new Bundle();
        bundle.putInt("position",position);
        bundle.putInt("itemPosition",itemPosition);
        FragmentManager manager=((AppCompatActivity)view.getContext()).getSupportFragmentManager();
        ProductDescriptionFragment fragment=ProductDescriptionFragment.newInstance();
        fragment.setArguments(bundle);
        fragment.show(manager,"sxss");



    }
}