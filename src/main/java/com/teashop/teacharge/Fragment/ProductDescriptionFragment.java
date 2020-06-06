package com.teashop.teacharge.Fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.teashop.teacharge.Model.BillingModel;
import com.teashop.teacharge.Model.SubCategoryModel;
import com.teashop.teacharge.View.ProductsCategory;
import com.teashop.teacharge.handlers.ItemCountUpdatePrice;
import com.teashop.teacharge.Model.ProductDescriptionModel;
import com.teashop.teacharge.R;
import com.teashop.teacharge.databinding.ActivityItemlistBinding;
import com.teashop.teacharge.databinding.ProductDescriptionBinding;
import com.teashop.teacharge.viewModel.ProductDescriptionViewModel;

import java.util.ArrayList;
import java.util.List;

public class ProductDescriptionFragment extends DialogFragment implements ItemCountUpdatePrice {
    public MutableLiveData<List<SubCategoryModel>> productDescriptionList=new MutableLiveData<>();

    ProductDescriptionBinding binding;
    ActivityItemlistBinding mBinding;
    BillingModel billingModel;
    ProductDescriptionViewModel mViewModel;
    int categoryID;
     int itemPosition=0;
    public ProductDescriptionFragment() {
        // Required empty public constructor

    }



    public static ProductDescriptionFragment newInstance() {
        return new ProductDescriptionFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.product_description, container, false);
        binding.setPresenter(this);

        return binding.getRoot();

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onResume() {
        super.onResume();
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(getDialog().getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        getDialog().getWindow().setAttributes(lp);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel=new ProductDescriptionViewModel( ((ProductsCategory)getActivity()).products);
        billingModel=new BillingModel();

        final int position = getArguments().getInt("position");
         itemPosition=getArguments().getInt("itemPosition");
        mViewModel.getProductDescription(position).observe((getActivity()), new Observer<List<SubCategoryModel>>() {
    @Override
    public void onChanged(List<SubCategoryModel> productDescriptionModels) {

        productDescriptionList.setValue(productDescriptionModels);
        Log.d("pokemon", "onChanged: "+productDescriptionModels);

        binding.tvItemName.setText(productDescriptionModels.get(itemPosition).getUnitPrice());


         String imageUrl= productDescriptionModels.get(itemPosition).getImage();
         binding.setImageUrl(imageUrl);
       billingModel.setProduct(productDescriptionModels.get(itemPosition).getDescription());
       billingModel.setPrice(Integer.parseInt(productDescriptionModels.get(itemPosition).getUnitPrice()));



        InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(binding.tvdisplayNumber, InputMethodManager.SHOW_IMPLICIT);
        }
        });

    }



    @Override
    public void ItemCountUpdatePrice() {

        String value=binding.tvdisplayNumber.getText().toString();
     if(value.matches(""))
     {
        int total=Integer.parseInt(productDescriptionList.getValue().get(itemPosition).getUnitPrice());
        billingModel.setQuantity(1);
        billingModel.setTotal(total);
       billingModel.setProduct(productDescriptionList.getValue().get(itemPosition).getTitle());
        billingModel.setID(Integer.parseInt(productDescriptionList.getValue().get(itemPosition).getProductId()));

         ((ProductsCategory)getActivity()).setPriceText(total);
        ((ProductsCategory)getActivity()).billingModelList.add(billingModel);


    }
    else
    {


        int itemCount=Integer.parseInt(binding.tvdisplayNumber.getText().toString());
        billingModel.setQuantity(itemCount);
        int price=Integer.parseInt(productDescriptionList.getValue().get(itemPosition).getUnitPrice());
        billingModel.setProduct(productDescriptionList.getValue().get(itemPosition).getTitle());
        int total=itemCount*price;
        billingModel.setID(Integer.parseInt(productDescriptionList.getValue().get(itemPosition).getProductId()));
        billingModel.setTotal(total);


        // mBinding.itemPrice.setText(totalInString);
        ((ProductsCategory)getActivity()).setPriceText(total);

        ((ProductsCategory)getActivity()).billingModelList.add(billingModel);



    }


        getDialog().dismiss();


    }
}