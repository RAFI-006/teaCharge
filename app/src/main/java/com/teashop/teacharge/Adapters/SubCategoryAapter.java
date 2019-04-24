package com.teashop.teacharge.Adapters;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.teashop.teacharge.R;

import java.util.List;

public class SubCategoryAapter extends RecyclerView.Adapter<SubCategoryAapter.ListHolder> {

    private List<String> list;
   public Context ctx;
    private int rowLayout;


    public SubCategoryAapter(List<String> list,Context ctx)
    {
        this.list=list;

        this.ctx=ctx;
    }


    @NonNull
    @Override
    public ListHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.category_horizontal_list, null);
        return new SubCategoryAapter.ListHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull ListHolder listHolder, int i) {



        listHolder.recyclerView.setHasFixedSize(true);
      listHolder.recyclerView.setLayoutManager((new LinearLayoutManager(ctx, LinearLayoutManager.HORIZONTAL, false)));

    }

    @Override
    public int getItemCount() {
       return list.size();
    }

    public static class ListHolder extends RecyclerView.ViewHolder {

        RecyclerView recyclerView;

        public ListHolder(@NonNull View itemView) {
            super(itemView);
            recyclerView=(RecyclerView)itemView.findViewById(R.id.category_horizontal_list);


        }

    }
}
