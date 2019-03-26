package com.teashop.teacharge.View;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.teashop.teacharge.Adapters.ProductCategoryAdapter;
import com.teashop.teacharge.Adapters.SubCategoryAapter;
import com.teashop.teacharge.Repository.ApiRepository;
import com.teashop.teacharge.R;

import java.util.ArrayList;
import java.util.List;


public class ItemListActivity extends AppCompatActivity {
    ApiRepository repo;
    RecyclerView recyclerView,horizontalRecyclerView;
   public List<String> productCategoryList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itemlist);

        productCategoryList = new ArrayList<String>();
        productCategoryList.add("Tea");
        productCategoryList.add("Coffee");
        productCategoryList.add("Milk Shake");
        productCategoryList.add("Juice");





        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        horizontalRecyclerView=(RecyclerView)findViewById(R.id.horizontal_recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        horizontalRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        horizontalRecyclerView.setAdapter(new SubCategoryAapter(productCategoryList,getApplicationContext()));
        recyclerView.setAdapter(new ProductCategoryAdapter(productCategoryList, R.layout.category_list_item, getApplicationContext()));


//        repo=BaseApi.getClient().create(ApiRepository.class);
//
//        repo.GetCategories().enqueue(new Callback<CategoryResponseModel>() {
//            @Override
//            public void onResponse(Call<CategoryResponseModel> call, Response<CategoryResponseModel> response) {
//
//               CategoryResponseModel model=response.body();
//
//
//
//            }
//
//            @Override
//            public void onFailure(Call<CategoryResponseModel> call, Throwable t) {
//                Log.d("dorami", "onResponse: "+t);
//
//
//            }
//        });
    }
}
