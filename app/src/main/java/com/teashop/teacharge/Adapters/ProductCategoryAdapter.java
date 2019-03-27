package com.teashop.teacharge.Adapters;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teashop.teacharge.BR;
import com.teashop.teacharge.R;
import com.teashop.teacharge.databinding.CategoryListItemBinding;
import com.teashop.teacharge.viewModel.ProductsCategoryViewModel;
import java.util.List;

public class ProductCategoryAdapter extends RecyclerView.Adapter<ProductCategoryAdapter.ListHolder> {

    private List<String> list;
  private  ProductsCategoryViewModel mViewModel;

    public ProductCategoryAdapter(ProductsCategoryViewModel viewModel)
    {
        mViewModel=viewModel;

          }

    public void setList(List<String> list) {

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
        listHolder.bind(mViewModel, i);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ListHolder extends RecyclerView.ViewHolder {
   CategoryListItemBinding mitemBinding;

        public ListHolder(@NonNull CategoryListItemBinding binding ) {
            super(binding.getRoot());
            //name=(TextView)itemView.findViewById(R.id.textparcel);

              mitemBinding=binding;

        }



        void bind(ProductsCategoryViewModel viewModel, int position) {

            mitemBinding.setVariable(BR.viewModel, viewModel);
            mitemBinding.setVariable(BR.position, position);
            mitemBinding.executePendingBindings();

        }



    }



}
