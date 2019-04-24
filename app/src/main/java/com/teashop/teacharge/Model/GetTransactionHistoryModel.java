package com.teashop.teacharge.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class GetTransactionHistoryModel implements Serializable {
    @SerializedName("order_id")
    @Expose
    private String orderId;
    @SerializedName("bill_id")
    @Expose
    private String billId;
    @SerializedName("total_quantity")
    @Expose
    private String totalQuantity;
    @SerializedName("total_price")
    @Expose
    private String totalPrice;
    @SerializedName("sale_date")
    @Expose
    private String saleDate;
    @SerializedName("order_items")
    @Expose
    private List<OrderItem> orderItems = null;
    private final static long serialVersionUID = -3045659151066236468L;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(String totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(String saleDate) {
        this.saleDate = saleDate;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;

    }
}


