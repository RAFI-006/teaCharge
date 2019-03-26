package com.teashop.teacharge.Adapters;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.teashop.teacharge.R;

import java.util.List;

public class ProductCategoryAdapter extends RecyclerView.Adapter<ProductCategoryAdapter.ListHolder> {

    private List<String> list;
    private int rowLayout;
    public Context context;


    public ProductCategoryAdapter(List<String> data,int layout,Context ctx)
    {

        list=data;
        rowLayout=layout;
        context=ctx;
    }


    @NonNull
    @Override
    public ListHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new ListHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ListHolder listHolder, int i) {
        listHolder.name.setText(list.get(i));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ListHolder extends RecyclerView.ViewHolder {

        TextView name;
        public ListHolder(@NonNull View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.textparcel);

        }



    }



}
