package com.teashop.teacharge.Adapters;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teashop.teacharge.BR;
import com.teashop.teacharge.handlers.CategoryClickHandler;
import com.teashop.teacharge.Model.CategoryModel;
import com.teashop.teacharge.R;
import com.teashop.teacharge.databinding.CategoryListItemBinding;
import com.teashop.teacharge.viewModel.ProductsCategoryViewModel;
import java.util.List;

public class ProductCategoryAdapter extends RecyclerView.Adapter<ProductCategoryAdapter.ListHolder> {

    private List<CategoryModel> list;
  private  ProductsCategoryViewModel mViewModel;
    private View.OnClickListener mOnClickListener;
    CategoryClickHandler mHandler;

    public ProductCategoryAdapter(ProductsCategoryViewModel viewModel,View.OnClickListener onClickListener,CategoryClickHandler handler)
    {
        mViewModel=viewModel;
        mOnClickListener=onClickListener;
        mHandler=handler;
          }

    public void setList(List<CategoryModel> list) {

        this.list = list;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
       public ListHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        CategoryListItemBinding mitemBinding=DataBindingUtil.inflate(inflater, R.layout.category_list_item, viewGroup, false);
        return new ListHolder(mitemBinding);


    }

    @Override
    public void onBindViewHolder(@NonNull ListHolder listHolder, int i) {
    //    listHolder.name.setText(list.get(i));
        listHolder.bind(mViewModel,i,mOnClickListener,mHandler);





    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public static class ListHolder extends RecyclerView.ViewHolder {
        CategoryListItemBinding mitemBinding;

        public ListHolder(@NonNull CategoryListItemBinding binding) {
            super(binding.getRoot());
            //name=(TextView)itemView.findViewById(R.id.textparcel);

            mitemBinding = binding;

        }


        void bind(ProductsCategoryViewModel viewModel, int position, View.OnClickListener mOnClickListener, CategoryClickHandler handler) {

            mitemBinding.setVariable(BR.viewModel, viewModel);
            mitemBinding.setVariable(BR.position, position);
            mitemBinding.setVariable(BR.clickListener, mOnClickListener);
            mitemBinding.setVariable(BR.itemClickListener,handler);
            mitemBinding.executePendingBindings();

        }


    }



    }
