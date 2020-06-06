package com.teashop.teacharge.Model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ProductDescriptionGenericModel implements Serializable {



        @SerializedName("data")
        @Expose
        private List<ProductDescriptionModel> data = null;
        @SerializedName("product_image_path")
        @Expose
        private String productImagePath;
        @SerializedName("error_message")
        @Expose
        private String errorMessage;
        @SerializedName("error_code")
        @Expose
        private String errorCode;
        private final static long serialVersionUID = -801569852467823177L;

        public List<ProductDescriptionModel> getData() {
        return data;
    }

        public void setData(List<ProductDescriptionModel> data) {
        this.data = data;
    }

        public String getProductImagePath() {
        return productImagePath;
    }

        public void setProductImagePath(String productImagePath) {
        this.productImagePath = productImagePath;
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
