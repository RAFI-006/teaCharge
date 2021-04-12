package com.teashop.teacharge.Repository;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.teashop.teacharge.DataUtils.BaseApi;
import com.teashop.teacharge.Model.GetTransactionHistoryModel;
import com.teashop.teacharge.Model.GetTransactionHIstoryParams;

import java.lang.reflect.Type;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransactionHistoryRepository {

    ApiRepository repo;
    public  MutableLiveData<List<GetTransactionHistoryModel>> mHistory = new MutableLiveData<>();



  public  LiveData<List<GetTransactionHistoryModel>>  TransactionHistory(GetTransactionHIstoryParams params)
    {
        repo=BaseApi.getClient().create(ApiRepository.class);

      Call<JsonObject> call= repo.History(params);
      call.enqueue(new Callback<JsonObject>() {
          @Override
          public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

              JsonObject jsonObject=response.body();
              Type dataType = new TypeToken<List<GetTransactionHistoryModel>>() {
              }.getType();

              Gson gson=new GsonBuilder().create();
              List<GetTransactionHistoryModel> productDescriptionList=gson.fromJson(jsonObject.getAsJsonArray("data"),dataType);
              Log.d("dorami", "Productdescription: "+productDescriptionList);
              mHistory.setValue(productDescriptionList);

          }

          @Override
          public void onFailure(Call<JsonObject> call, Throwable t) {

              Log.d("dorami", "Productdescription: "+t);

          }
      });


        return mHistory;


    }
    public LiveData<List<GetTransactionHistoryModel>> getTransactionHistory(GetTransactionHIstoryParams params){

        Log.d("History", "getTransactionHistory: "+ mHistory);
        return TransactionHistory(params);
    }

}
