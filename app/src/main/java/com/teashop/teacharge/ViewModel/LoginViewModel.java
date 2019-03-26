package com.teashop.teacharge.ViewModel;

import android.util.Log;

import com.teashop.teacharge.DataUtils.BaseApi;
import com.teashop.teacharge.Model.GenericModel;
import com.teashop.teacharge.Model.SignInModel;
import com.teashop.teacharge.Repository.ApiRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel {

   public static ApiRepository repo;
    public static GenericModel DoApiLogin(String username,String password)
    {
         SignInModel signInModel=new SignInModel();
        final GenericModel[] responseModel = {new GenericModel()};
        repo=BaseApi.getClient().create(ApiRepository.class);

        Call<GenericModel> call = repo.UserLogin(username,password);
        call.enqueue(new Callback<GenericModel>() {
            @Override
            public void onResponse(Call<GenericModel> call, Response<GenericModel> response) {
                Log.d("dorami", "onResponse: "+response.body().getData());

            responseModel[0] =response.body();
            }

            @Override
            public void onFailure(Call<GenericModel> call, Throwable t) {
                Log.d("dorami", "onResponse: "+t);

            }
        });

   return responseModel[0];
    }

}
