package com.teashop.teacharge.View;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.teashop.teacharge.Fragment.SelectDateDialogFragment;
import com.teashop.teacharge.Model.GetTransactionHIstoryParams;
import com.teashop.teacharge.Model.GetTransactionHistoryModel;
import com.teashop.teacharge.R;
import com.teashop.teacharge.Repository.ApiRepository;
import com.teashop.teacharge.databinding.ActivityTransactionHistoryBinding;
import com.teashop.teacharge.viewModel.TransactionHistoryViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TransactionHistory extends AppCompatActivity {
    ApiRepository repo;
    TransactionHistoryViewModel viewModel;
    GetTransactionHIstoryParams transaction;
    ActivityTransactionHistoryBinding mBinding;
    int textSize = 0, smallTextSize = 0, mediumTextSize = 0;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String currentDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        sp = getSharedPreferences("your_shared_pref_name", MODE_PRIVATE);
        textSize = (int) getResources().getDimension(R.dimen.font_size_verysmall);
        smallTextSize = (int) getResources().getDimension(R.dimen.font_size_small);
        mediumTextSize = (int) getResources().getDimension(R.dimen.font_size_medium);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_transaction_history);
        transaction = new GetTransactionHIstoryParams();

        viewModel = new TransactionHistoryViewModel();


        mBinding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager = getSupportFragmentManager();
                SelectDateDialogFragment fragment = SelectDateDialogFragment.newInstance();
                fragment.show(manager, "sxssx");
            }
        });

        InitRecyclerView(currentDate, currentDate);


    }

    public void InitRecyclerView(String fromDate, String toDate) {
        mBinding.pBar.setVisibility(View.VISIBLE);
        transaction.SetFromDate(fromDate);
        transaction.SetToDate(toDate);
        transaction.setUserId(sp.getInt("userId", 0));


        mBinding.transactionRecyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        mBinding.transactionRecyclerView.setAdapter(viewModel.getAdapter(textSize, smallTextSize, mediumTextSize, this));

        viewModel.getTransactionHistory(transaction).observe(this, new Observer<List<GetTransactionHistoryModel>>() {
            @Override
            public void onChanged(List<GetTransactionHistoryModel> transactionHistoryModels) {
                mBinding.pBar.setVisibility(View.GONE);
                viewModel.SetHistoryList(transactionHistoryModels);
                double total = 0.0;
                if (transactionHistoryModels.size() > 0) {
                    mBinding.tvNodata.setVisibility(View.GONE);
                    for (int i = 0; i < transactionHistoryModels.size(); i++) {
                        total = total + Double.parseDouble(transactionHistoryModels.get(i).getTotalPrice());
                    }
                    mBinding.tvTotal.setText(String.valueOf(total));
                } else {
                    mBinding.tvNodata.setVisibility(View.VISIBLE);
                }


                Log.d("history", "onChanged: " + transactionHistoryModels);
            }
        });


    }


}