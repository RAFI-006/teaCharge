package com.teashop.teacharge.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class PostTransactionHistoryModel implements Serializable {
    @SerializedName("bill_id")
    @Expose
    private String billId;
    @SerializedName("total_quantity")
    @Expose
    private Integer totalQuantity;
    @SerializedName("total_price")
    @Expose
    private Integer totalPrice;
    @SerializedName("sale_date")
    @Expose
    private String saleDate;
    @SerializedName("user_id")
    @Expose
    private Integer userId;
    @SerializedName("orderitems")
    @Expose
    private List<OrderItemParams> orderitems = null;
    private final static long serialVersionUID = 9000063577643327591L;

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(String saleDate) {
        this.saleDate = saleDate;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public List<OrderItemParams> getOrderitems() {
        return orderitems;
    }

    public void setOrderitems(List<OrderItemParams> orderitems) {
        this.orderitems = orderitems;
    }
}
