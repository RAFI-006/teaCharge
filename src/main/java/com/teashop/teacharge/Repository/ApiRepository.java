package com.teashop.teacharge.Repository;

import com.google.gson.JsonObject;
import com.teashop.teacharge.Model.GenericModel;
import com.teashop.teacharge.Model.OrderItemParams;
import com.teashop.teacharge.Model.PostTransactionHistoryModel;
import com.teashop.teacharge.Model.SignInModel;
import com.teashop.teacharge.Model.GetTransactionHIstoryParams;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiRepository {


    @POST("login/format/json")
    Call<JsonObject>UserLogin(@Body SignInModel model);
    //Call<GenericModel>UserLogin(@Field("username")String Username,@Field("password")String Password);

    @Headers({"Token: cos0og44c8s0s0wwwcoco8sw0ck0w48ooww0owow","Content-Type: application/json"})
    @POST("orders/format/json")
    Call<JsonObject>History(@Body GetTransactionHIstoryParams params);

    @Headers({"Token:cos0og44c8s0s0wwwcoco8sw0ck0w48ooww0owow","Content-Type: application/json"})
    @POST("postorders/format/json")
    Call<JsonObject>PostOrders(@Body PostTransactionHistoryModel model);





//    @GET("api/products.php")
//    Call<JsonObject>GetProductsDescripiton(@Query("SubCategoryID") int id);



    @GET("categories/format/json")
    Call<JsonObject>GetCategories();

}
