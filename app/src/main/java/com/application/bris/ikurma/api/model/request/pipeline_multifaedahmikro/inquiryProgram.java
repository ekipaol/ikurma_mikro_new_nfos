package com.application.bris.ikurma.api.model.request.pipeline_multifaedahmikro;

import com.google.gson.annotations.SerializedName;

/**
 * Created by idong on 20/06/2019.
 */

public class inquiryProgram {
    @SerializedName("uid")
    private Long uid;

    public void setUid(Long uid) {
        this.uid = uid;
    }
}