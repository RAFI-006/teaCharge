package com.teashop.teacharge.viewModel;

import android.content.Context;

import com.teashop.teacharge.Adapters.ProductCategoryAdapter;
import com.teashop.teacharge.Adapters.TransactionHistoryAdapter;
import com.teashop.teacharge.Model.GetTransactionHIstoryParams;
import com.teashop.teacharge.Model.GetTransactionHistoryModel;
import com.teashop.teacharge.Repository.TransactionHistoryRepository;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class TransactionHistoryViewModel {

    TransactionHistoryAdapter mAdapter;
   // public MutableLiveData<List<GetTransactionHistoryModel>> getTransactionHistoryList=new MutableLiveData<>();
    public List<GetTransactionHistoryModel> getTransactionHistoryList;
    TransactionHistoryRepository repository;
    public TransactionHistoryViewModel()
    {
        repository=new TransactionHistoryRepository();

    }


   public LiveData<List<GetTransactionHistoryModel>> getTransactionHistory(GetTransactionHIstoryParams params)
    {
        return repository.getTransactionHistory(params);
         }

public void SetHistoryList(List<GetTransactionHistoryModel> model)
{
    getTransactionHistoryList=model;
    mAdapter.SetHistoryList(model);
}

    public TransactionHistoryAdapter getAdapter(int textSize, int smallTextSize, int mediumTextSize, Context ctx) {

        if (mAdapter == null) {
            mAdapter = new TransactionHistoryAdapter( textSize ,smallTextSize,  mediumTextSize , ctx);
        }
        return mAdapter;
    }






}
