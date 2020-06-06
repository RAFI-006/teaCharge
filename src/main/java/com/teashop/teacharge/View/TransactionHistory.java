package com.teashop.teacharge.View;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.gson.JsonObject;
import com.teashop.teacharge.DataUtils.BaseApi;
import com.teashop.teacharge.Fragment.ProductDescriptionFragment;
import com.teashop.teacharge.Fragment.SelectDateDialogFragment;
import com.teashop.teacharge.Model.GetTransactionHIstoryParams;
import com.teashop.teacharge.Model.GetTransactionHistoryModel;
import com.teashop.teacharge.Model.OrderItem;
import com.teashop.teacharge.Model.OrderItemParams;
import com.teashop.teacharge.Model.PostTransactionHistoryModel;
import com.teashop.teacharge.R;
import com.teashop.teacharge.Repository.ApiRepository;
import com.teashop.teacharge.databinding.ActivityTransactionHistoryBinding;
import com.teashop.teacharge.databinding.TransactionHistoryItemlistBinding;
import com.teashop.teacharge.viewModel.TransactionHistoryViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static java.security.AccessController.getContext;

public class TransactionHistory extends AppCompatActivity {
    ApiRepository repo;
    TransactionHistoryViewModel viewModel;
    GetTransactionHIstoryParams transaction;
    ActivityTransactionHistoryBinding mBinding;
    int textSize = 0, smallTextSize = 0, mediumTextSize = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        textSize = (int) getResources().getDimension(R.dimen.font_size_verysmall);
        smallTextSize = (int) getResources().getDimension(R.dimen.font_size_small);
        mediumTextSize = (int) getResources().getDimension(R.dimen.font_size_medium);

        setContentView(R.layout.activity_transaction_history);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_transaction_history);
        transaction = new GetTransactionHIstoryParams();

        viewModel = new TransactionHistoryViewModel();


mBinding.fab.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        FragmentManager manager=getSupportFragmentManager();
        SelectDateDialogFragment fragment=SelectDateDialogFragment.newInstance();
        fragment.show(manager,"sxssx");
    }
});

        InitRecyclerView(currentDate,currentDate);


    }

   public void InitRecyclerView(String fromDate,String toDate) {
        transaction.SetFromDate(fromDate);
        transaction.SetToDate(toDate);
        transaction.setUserId(1);


        mBinding.transactionRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        mBinding.transactionRecyclerView.setAdapter(viewModel.getAdapter( textSize,  smallTextSize, mediumTextSize, this));

        viewModel.getTransactionHistory(transaction).observe(this, new Observer<List<GetTransactionHistoryModel>>() {
            @Override
            public void onChanged(List<GetTransactionHistoryModel> transactionHistoryModels) {

                viewModel.SetHistoryList(transactionHistoryModels);
                Log.d("history", "onChanged: " + transactionHistoryModels);
            }
        });


    }


}