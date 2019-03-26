package com.teashop.teacharge.Adapters;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.teashop.teacharge.R;
import java.util.List;

public class SubCategoryHorizontalAdapter extends RecyclerView.Adapter<SubCategoryHorizontalAdapter.ListHolder> {


    private List<String> list;
    private int rowLayout;
    public Context context;


    public SubCategoryHorizontalAdapter(List<String> data, Context ctx)
    {

        list=data;

        context=ctx;
    }


    @NonNull
    @Override
    public ListHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.subcategory_item_list, null);
        return new SubCategoryHorizontalAdapter.ListHolder(v);

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
        ImageView imageView;

        public ListHolder(@NonNull View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.sub_category_title);

        }

    }
}