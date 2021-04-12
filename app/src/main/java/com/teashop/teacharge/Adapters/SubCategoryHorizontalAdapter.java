package com.teashop.teacharge.Adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.teashop.teacharge.BR;
import com.teashop.teacharge.Model.CategoryModel;
import com.teashop.teacharge.Model.SubCategoryModel;
import com.teashop.teacharge.R;
import com.teashop.teacharge.databinding.SubcategoryItemListBinding;
import com.teashop.teacharge.handlers.SubCategoryClickHandler;
import com.teashop.teacharge.viewModel.ProductsCategoryViewModel;

import java.util.List;

public class SubCategoryHorizontalAdapter extends RecyclerView.Adapter<SubCategoryHorizontalAdapter.ListHolder> {


    private List<SubCategoryModel> list;
    private List<CategoryModel> mainlist;
    ProductsCategoryViewModel mViewModel;
    SubCategoryClickHandler mHandler;
    public Context context = null;


    public SubCategoryHorizontalAdapter(ProductsCategoryViewModel model, SubCategoryClickHandler hanlder, Context ctx) {

        mViewModel = model;
        mHandler = hanlder;
        context = ctx;

    }


    @NonNull
    @Override
    public ListHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        SubcategoryItemListBinding binding = DataBindingUtil.inflate(inflater, R.layout.subcategory_item_list, viewGroup, false);
        return new ListHolder(binding);
    }


    public void setList(List<SubCategoryModel> list, List<CategoryModel> mainList) {
        this.list = list;
        this.mainlist = mainList;
        notifyDataSetChanged();
    }


    @Override
    public void onBindViewHolder(@NonNull ListHolder listHolder, int i) {

        String imageUrl ="http://admin.chaiboy.xyz/uploads/product/"+ list.get(i).getImage();
        listHolder.bind(mViewModel, i, mHandler, listHolder.itemView.getRootView(), imageUrl);

        ImageView imageView = listHolder.imageView;
        if (imageUrl != "") {
            RequestOptions requestOptions = new RequestOptions();
            requestOptions.placeholder(R.drawable.defaultimage);
            requestOptions.centerCrop();
            //  requestOptions.error(R.drawable.ic_tea);
            listHolder.imageView.setImageDrawable(null);

            Glide.with(context)
                    .applyDefaultRequestOptions(requestOptions)
                    .load(imageUrl)

                    .apply(new RequestOptions().autoClone())
                    .into(imageView);
        }

    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();

    }

    public static class ListHolder extends RecyclerView.ViewHolder {

        TextView name;
        ImageView imageView;
        SubcategoryItemListBinding mbinding;

        public ListHolder(@NonNull SubcategoryItemListBinding binding) {
            super(binding.getRoot().getRootView());
            imageView = binding.subCategoryImage;
            mbinding = binding;

        }

        void bind(ProductsCategoryViewModel viewModel, int position, SubCategoryClickHandler handler, View view, String imageUrl) {

            mbinding.setVariable(BR.viewModel, viewModel);
            mbinding.setVariable(BR.imageUrl, imageUrl);
            mbinding.setVariable(BR.position, position);
            mbinding.setVariable(BR.subCategoryListener, handler);
            mbinding.setVariable(BR.view, view);
            mbinding.executePendingBindings();

        }


    }


}