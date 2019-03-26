package com.teashop.teacharge.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoryResponseModel<TModel> {

    @SerializedName("data")
    @Expose
    private TModel data;
    @SerializedName("category_image_path")
    @Expose
    private String categoryImagePath;
    @SerializedName("subcategory_image_path")
    @Expose
    private String subcategoryImagePath;
    @SerializedName("error_message")
    @Expose
    private String errorMessage;
    @SerializedName("error_code")
    @Expose
    private String errorCode;
    private final static long serialVersionUID = -2866868126945010879L;

    public TModel getData() {
        return data;
    }

    public void setData(TModel data) {
        this.data = data;
    }

    public String getCategoryImagePath() {
        return categoryImagePath;
    }

    public void setCategoryImagePath(String categoryImagePath) {
        this.categoryImagePath = categoryImagePath;
    }

    public String getSubcategoryImagePath() {
        return subcategoryImagePath;
    }

    public void setSubcategoryImagePath(String subcategoryImagePath) {
        this.subcategoryImagePath = subcategoryImagePath;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }


}
