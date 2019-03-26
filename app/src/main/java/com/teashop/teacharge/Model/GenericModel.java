package com.teashop.teacharge.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GenericModel<TModel>{

    @SerializedName("data")
    @Expose
    private TModel data;
    @SerializedName("error_message")
    @Expose
    private String errorMessage;
    @SerializedName("error_code")
    @Expose
    private String errorCode;
    private final static long serialVersionUID = -2458241920704201826L;

    public TModel getData() {
        return data;
    }

    public void setData(TModel data) {
        this.data = data;
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

