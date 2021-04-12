package com.teashop.teacharge.viewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.teashop.teacharge.DataUtils.BaseApi;
import com.teashop.teacharge.Model.GenericModel;
import com.teashop.teacharge.Model.LoginResponse;
import com.teashop.teacharge.Model.SignInModel;
import com.teashop.teacharge.Repository.ApiRepository;

import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel {

    public static ApiRepository repo;
    private MutableLiveData<LoginResponse> loginResponse = new MutableLiveData<>();

    public void DoApiLogin(SignInModel model) {
        SignInModel signInModel = new SignInModel();
        boolean Isresponse = false;
        final GenericModel[] responseModel = {new GenericModel()};
        repo = BaseApi.getClient().create(ApiRepository.class);

        Call<LoginResponse> call = repo.UserLogin(model);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

           /*     JsonObject jsonObject = response.body();
                Type dataType = new TypeToken<GenericModel>() {
                }.getType();

                Gson gson = new GsonBuilder().create();
                GenericModel model = gson.fromJson(jsonObject, dataType);*/

           LoginResponse model = response.body();
                loginResponse.setValue(model);
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e("error", t.toString());
            }
        });


    }

    public LiveData<LoginResponse> GetLoginResponse(SignInModel model) {
        DoApiLogin(model);
        return loginResponse;
    }

}