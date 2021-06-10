package com.application.bris.ikurma.api.model.request.general;

import com.google.gson.annotations.SerializedName;

public class ReqUid {
    @SerializedName("uid")
    private Long uid;

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }
}
