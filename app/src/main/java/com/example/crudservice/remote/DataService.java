package com.example.crudservice.remote;

import com.example.crudservice.model.Data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface DataService {

    @GET("blog/")
    Call<List<Data>> getData();

    @POST("blog/create")
    Call<Data> addData(@Body Data data);

    @PUT("blog/edit/{id}")
    Call<Data> editData(@Path("id") int id, @Body Data data);

    @DELETE("blog/delete/{id}")
    Call<Data> deleteData(@Path("id") int id);

}
