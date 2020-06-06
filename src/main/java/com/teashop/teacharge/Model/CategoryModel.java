package com.teashop.teacharge.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class CategoryModel implements Serializable{

    @SerializedName("category_id")
    @Expose
    private String categoryId;
    @SerializedName("category_title")
    @Expose
    private String categoryTitle;
    @SerializedName("category_image")
    @Expose
    private String categoryImage;
    @SerializedName("category_description")
    @Expose
    private String categoryDescription;
    @SerializedName("products")
    @Expose
    private List<SubCategoryModel> products = null;
    private final static long serialVersionUID = 780034855958528384L;

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public String getCategoryImage() {
        return categoryImage;
    }

    public void setCategoryImage(String categoryImage) {
        this.categoryImage = categoryImage;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public List<SubCategoryModel> getProducts() {
        return products;
    }

    public void setProducts(List<SubCategoryModel> products) {
        this.products = products;
    }
}
