package com.application.bris.ikurma.api.model;

import com.google.gson.JsonArray;
import com.google.gson.annotations.SerializedName;

public class ParseResponseArrAgunan {
    @SerializedName("status")
    private String status;
    @SerializedName("statusMsg")
    private String message;
    @SerializedName("data")
    private JsonArray data;
    @SerializedName("info")
    private JsonArray info;

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public JsonArray getData() {
        return data;
    }

    public JsonArray getInfo() {
        return info;
    }

}
