package com.teashop.teacharge.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BillingModel implements Serializable {


    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("product")
    @Expose
    private String product;
    @SerializedName("quantity")
    @Expose
    private int quantity;
    @SerializedName("price")
    @Expose
    private int price;
    @SerializedName("total")
    @Expose
    private int total;

    public void setID(int id)
    {
        this.id=id;
    }

  public int getID()
  {
    return id;
  }

    public void setProduct(String Product)
    {
        this.product=Product;
    }

    public String getProduct()
    {
        return product ;
    }

    public void setQuantity(int quantity)
    {
        this.quantity=quantity;
    }

    public int getQuantity()
    {
        return quantity;
    }

    public void setPrice(int price)
    {
        this.price=price;
    }

    public int getPrice()
    {
        return price;
    }
    public void setTotal(int total)
    {
        this.total=total;
    }

    public int getTotal()
    {
        return total;
    }



}
