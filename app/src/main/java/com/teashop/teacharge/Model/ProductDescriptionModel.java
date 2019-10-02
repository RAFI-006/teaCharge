package com.teashop.teacharge.Model;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import androidx.databinding.BindingAdapter;

public class ProductDescriptionModel implements Serializable {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("CategoryID")
    @Expose
    private String categoryID;
    @SerializedName("SubcategoryID")
    @Expose
    private String subcategoryID;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("unit_price")
    @Expose
    private String unitPrice;
    @SerializedName("unit_measure")
    @Expose
    private String unitMeasure;
    @SerializedName("quantity")
    @Expose
    private String quantity;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("createdon")
    @Expose
    private String createdon;
    @SerializedName("createdby")
    @Expose
    private String createdby;
    @SerializedName("updatedon")
    @Expose
    private String updatedon;
    @SerializedName("updatedby")
    @Expose
    private String updatedby;
    private final static long serialVersionUID = -371889057306187464L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getSubcategoryID() {
        return subcategoryID;
    }

    public void setSubcategoryID(String subcategoryID) {
        this.subcategoryID = subcategoryID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getUnitMeasure() {
        return unitMeasure;
    }

    public void setUnitMeasure(String unitMeasure) {
        this.unitMeasure = unitMeasure;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedon() {
        return createdon;
    }

    public void setCreatedon(String createdon) {
        this.createdon = createdon;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public String getUpdatedon() {
        return updatedon;
    }

    public void setUpdatedon(String updatedon) {
        this.updatedon = updatedon;
    }

    public String getUpdatedby() {
        return updatedby;
    }

    public void setUpdatedby(String updatedby) {
        this.updatedby = updatedby;
    }

    @BindingAdapter("profileImage")
    public static void loadImage(ImageView view, String imageUrl) {
        Glide.with(view.getContext())
                .load(imageUrl).apply(new RequestOptions().fitCenter())
                .into(view);
    }

}


