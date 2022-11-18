package com.example.retrofit;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PartMap;

public interface ApiInterface {

    @GET("new-arrival.php")
    Call<GetModel> getPosts();

    //using form data
    @Multipart
    @POST("submit-review.php")
    Call<PostResponseModel> createPost(@PartMap Map<String, RequestBody> params);

    /*@POST("submit-review.php")
    Call<PostModel> createPost(@Body PostModel postModel);*/
}
