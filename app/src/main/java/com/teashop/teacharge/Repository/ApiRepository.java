package com.teashop.teacharge.Repository;

import com.teashop.teacharge.Model.CategoryResponseModel;
import com.teashop.teacharge.Model.GenericModel;
import com.teashop.teacharge.Model.SignInModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiRepository {

    @FormUrlEncoded
    @POST("api/login.php")
    Call<GenericModel>UserLogin(@Field("username")String Username,@Field("password")String Password);

   @GET("api/categores.php")
    Call<CategoryResponseModel>GetCategories();

}
