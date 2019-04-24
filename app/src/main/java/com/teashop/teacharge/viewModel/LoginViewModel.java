package com.teashop.teacharge.viewModel;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.teashop.teacharge.DataUtils.BaseApi;
import com.teashop.teacharge.Model.GenericModel;
import com.teashop.teacharge.Model.GetTransactionHistoryModel;
import com.teashop.teacharge.Model.SignInModel;
import com.teashop.teacharge.Repository.ApiRepository;

import java.lang.reflect.Type;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel {

    public static ApiRepository repo;
    private MutableLiveData<GenericModel> loginResponse = new MutableLiveData<>();

    public  void DoApiLogin(SignInModel model) {
        SignInModel signInModel = new SignInModel();
        boolean Isresponse = false;
        final GenericModel[] responseModel = {new GenericModel()};
        repo = BaseApi.getClient().create(ApiRepository.class);

        Call<JsonObject> call = repo.UserLogin(model);

        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {

                JsonObject jsonObject = response.body();
                Type dataType = new TypeToken<GenericModel>() {
                }.getType();

                Gson gson = new GsonBuilder().create();
                GenericModel model = gson.fromJson(jsonObject, dataType);
               loginResponse.setValue(model);
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {

            }
        });



    }

    public LiveData<GenericModel> GetLoginResponse(SignInModel model)
    {
        DoApiLogin(model);
      return loginResponse;
    }

}