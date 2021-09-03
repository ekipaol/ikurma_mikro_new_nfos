package com.application.bris.ikurma.page_aom.model;

import com.google.gson.annotations.SerializedName;

public class DataScoringNos {
    @SerializedName("decision")
    public String hasilScoring;

    public String getHasilScoring() {
        return hasilScoring;
    }

    public void setHasilScoring(String hasilScoring) {
        this.hasilScoring = hasilScoring;
    }
}
