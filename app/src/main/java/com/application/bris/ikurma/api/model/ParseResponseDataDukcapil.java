package com.application.bris.ikurma.api.model;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

/**
 * Created by PID on 4/5/2019.
 */

public class ParseResponseDataDukcapil {
    @SerializedName("status")
    private String status;
    @SerializedName("statusMsg")
    private String message;
    @SerializedName("dukcapil")
    private JsonObject dukcapil;

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public JsonObject getDukcapil() {
        return dukcapil;
    }
}