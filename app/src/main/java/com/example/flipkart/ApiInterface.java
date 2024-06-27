package com.example.flipkart;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("products")
    Call<List<ResponseProductItem>> getImage();
    @GET("products")
    Call<List<ResponseProductItem>> getProducts();
}
