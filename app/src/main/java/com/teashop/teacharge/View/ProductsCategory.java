package com.teashop.teacharge.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.teashop.teacharge.Model.BillingModel;
import com.teashop.teacharge.Model.CategoryModel;
import com.teashop.teacharge.Model.SubCategoryModel;
import com.teashop.teacharge.R;
import com.teashop.teacharge.databinding.ActivityItemlistBinding;
import com.teashop.teacharge.databinding.SubcategoryItemListBinding;
import com.teashop.teacharge.viewModel.ProductsCategoryViewModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class ProductsCategory extends AppCompatActivity {

    public static String itemPrice;
    public List<BillingModel> billingModelList;
    public List<SubCategoryModel> products;
    RecyclerView recyclerView, horizontalRecyclerView;
    private ActivityItemlistBinding mBinding;
    private SubcategoryItemListBinding subcategoryItemListBindingbinding;
    private ProductsCategoryViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itemlist);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_itemlist);
        mViewModel = new ProductsCategoryViewModel(this);

        billingModelList = new ArrayList<>();
        InitRecyclerView();
        mBinding.clearBil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                billingModelList = new ArrayList<>();
                mBinding.itemPrice.setText("0");
                Toast.makeText(ProductsCategory.this, "Order Canceled", Toast.LENGTH_SHORT).show();
            }
        });

        mBinding.history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(ProductsCategory.this, TransactionHistory.class));
            }
        });

        mBinding.genBill.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    String total = mBinding.itemPrice.getText().toString();

                                                    if (billingModelList.size() > 0) {
                                                        Intent intent = new Intent(ProductsCategory.this, BluetoothPrinterMain.class);
                                                        intent.putExtra("bill", (Serializable) billingModelList);
                                                        intent.putExtra("total",total);
                                                        startActivity(intent);
                                                    } else {
                                                        Toast.makeText(ProductsCategory.this, "No data", Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            }

        );

//        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
//        imm.showSoftInput(mBinding., InputMethodManager.SHOW_IMPLICIT);

    }

    void InitRecyclerView() {

        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));


        mBinding.recyclerView.setAdapter(mViewModel.getAdapter());
        mViewModel.getCategoryList().observe(this, new Observer<List<CategoryModel>>() {
            @Override
            public void onChanged(List<CategoryModel> categoryModels) {

                mViewModel.setCategoryList(categoryModels);


            }
        });
        mBinding.horizontalRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mBinding.horizontalRecyclerView.setAdapter(mViewModel.getSubCateoryAdapter(this));
        mViewModel.getSubCategoryList().observe(this, new Observer<List<SubCategoryModel>>() {
            @Override
            public void onChanged(List<SubCategoryModel> subCategoryModels) {
                mViewModel.setSubCategoryList(subCategoryModels);

                products = subCategoryModels;


//           for(int i=0;i<subCategoryModels.size();i++)
//           {
//               String ImageUrl=subCategoryModels.get(i).getImage();
//               if(ImageUrl!="")
//                   subcategoryItemListBindingbinding.setImageUrl(ImageUrl);
//           }
//

            }
        });


        // horizontalRecyclerView=(RecyclerView)findViewById(R.id.horizontal_recyclerView);
//
//          recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }


    public void setPriceText(int totalPrice) {
        int previousprice = Integer.parseInt(mBinding.itemPrice.getText().toString());

        String totalPriceInString = Integer.toString(totalPrice + previousprice);
        mBinding.itemPrice.setText(totalPriceInString);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}




