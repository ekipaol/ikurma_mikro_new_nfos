package com.application.bris.ikurma.api.model.request.pipeline_multifaedahmikro;

import com.google.gson.annotations.SerializedName;

public class inquiryTujuan {

    @SerializedName("loan_type")
    private String loan_type;

    public String getLoan_type() {
        return loan_type;
    }

    public void setLoan_type(String loan_type) {
        this.loan_type = loan_type;
    }
}
