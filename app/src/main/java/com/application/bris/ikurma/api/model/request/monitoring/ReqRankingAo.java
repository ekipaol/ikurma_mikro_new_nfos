package com.application.bris.ikurma.api.model.request.monitoring;

import com.google.gson.annotations.SerializedName;

public class ReqRankingAo {

    @SerializedName("fidRole")
    private String fidRole;

    public String getFidRole() {
        return fidRole;
    }

    public void setFidRole(String fidRole) {
        this.fidRole = fidRole;
    }
}
