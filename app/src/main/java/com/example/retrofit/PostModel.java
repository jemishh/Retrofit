package com.example.retrofit;

import java.util.List;

public class PostModel {
    private String name,email,userId,comment,productId;
    private Integer rating;
    private List<PostResponseModel> info=null;

    public PostModel(String name, String email, String userId,  Integer rating,String comment, String productId) {
        this.name = name;
        this.email = email;
        this.userId = userId;
        this.comment = comment;
        this.productId = productId;
        this.rating = rating;
    }

    public List<PostResponseModel> getInfo() {
        return info;
    }

    public void setInfo(List<PostResponseModel> info) {
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
