package com.example.retrofit;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ApiInterface apiInterface;
    ApiInterface apiInterfacePost;
    RecyclerView recyclerView;
    EditText etName,etEmail,etUserId,etRating,etComment,etProductId;
    TextView tvStatus,tvMsg;
    String name,email,userId,rating,comment,productId;
    Button btnSubmit;
    List<InfoModel> infoModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();



        apiInterface = RetrofitInstance.getRetrofit().create(ApiInterface.class);
        apiInterface.getPosts().enqueue(new Callback<GetModel>(){
                    @Override
                    public void onResponse(Call<GetModel> call, Response<GetModel> response) {

                        infoModels =response.body().getInfo();
                        RvAdapter rvAdapter=new RvAdapter(getApplicationContext(), infoModels);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
                        recyclerView.setAdapter(rvAdapter);
                    }

                    @Override
                    public void onFailure(Call<GetModel> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Failed to get Data", Toast.LENGTH_SHORT).show();
                     }
             });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name=etName.getText().toString();
                email=etEmail.getText().toString();
                userId=etUserId.getText().toString();
                rating=etRating.getText().toString();
                comment=etComment.getText().toString();
                productId=etProductId.getText().toString();
                if(name.isEmpty() || email.isEmpty() || userId.isEmpty() || rating.isEmpty() || comment.isEmpty() || productId.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please Fill the Fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                postData(name,email,userId,rating,comment,productId);
            }
        });


        /*StringRequest stringRequest=new StringRequest(Request.Method.GET, JSON_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject obj = jsonArray.getJSONObject(i);
                        infoModels.add(i, new InfoModel(Float.parseFloat(obj.getString("userId")),
                                Float.parseFloat(obj.getString("id")), obj.getString("title"), obj.getString("body")));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                RvAdapter rvAdapter=new RvAdapter(getApplicationContext(),infoModels);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
                recyclerView.setAdapter(rvAdapter);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //adding the string request to request queue
        requestQueue.add(stringRequest);*/
    }
    private void init() {
        recyclerView = findViewById(R.id.recyclerView);
        etName=findViewById(R.id.etName);
        etEmail=findViewById(R.id.etEmail);
        etUserId=findViewById(R.id.etUserId);
        etRating=findViewById(R.id.etRating);
        etComment=findViewById(R.id.etComment);
        etProductId=findViewById(R.id.etProductId);
        btnSubmit=findViewById(R.id.btnSubmit);
        tvMsg=findViewById(R.id.tvMsg);
        tvStatus=findViewById(R.id.tvStatus);
    }

    private void postData(String name,String email,String userId,String rating,String comment,String productId){
        RequestBody rb;
        LinkedHashMap<String, RequestBody> mp= new LinkedHashMap<>();

        rb = RequestBody.create(MediaType.parse("text/plain"), name );
        mp.put("name", rb);
        rb = RequestBody.create(MediaType.parse("text/plain"), email );
        mp.put("email", rb);
        rb = RequestBody.create(MediaType.parse("text/plain"), userId );
        mp.put("userId", rb);
        rb = RequestBody.create(MediaType.parse("text/plain"), rating );
        mp.put("rating", rb);
        rb = RequestBody.create(MediaType.parse("text/plain"), comment );
        mp.put("comment", rb);
        rb = RequestBody.create(MediaType.parse("text/plain"), productId );
        mp.put("productId", rb);


        apiInterfacePost= RetrofitInstance.getRetrofit().create(ApiInterface.class);
        Call<PostResponseModel> call = apiInterfacePost.createPost(mp);

        call.enqueue(new Callback<PostResponseModel>() {
            @Override
            public void onResponse(Call<PostResponseModel> call, Response<PostResponseModel> response) {
                PostResponseModel rs = response.body();
                tvMsg.setText(rs.getMsg());
                tvStatus.setText(rs.getStatus());
                if(Integer.parseInt(rs.getStatus())==1){
                    Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PostResponseModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failed to Upload data", Toast.LENGTH_SHORT).show();
            }
        });

        /*apiInterfacePost= RetrofitInstance.getRetrofit().create(ApiInterface.class);
        Call<PostResponseModel> call=apiInterfacePost.createPost(postModel);
        call.enqueue(new Callback<PostResponseModel>() {
            @Override
            public void onResponse(Call<PostResponseModel> call, Response<PostResponseModel> response) {
                Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();
                PostResponseModel rs= response.body();
                tvMsg.setText(rs.getMsg());
                tvStatus.setText(rs.getStatus());
            }

            @Override
            public void onFailure(Call<PostResponseModel> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failed to Upload data", Toast.LENGTH_SHORT).show();
            }
        });*/

    }
}