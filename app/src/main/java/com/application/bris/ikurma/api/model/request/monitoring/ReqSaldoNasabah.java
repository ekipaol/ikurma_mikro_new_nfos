package com.application.bris.ikurma.api.model.request.monitoring;

import com.google.gson.annotations.SerializedName;

public class ReqSaldoNasabah {

    @SerializedName("noRekening")
    private String noRekening;

    public String getNoRekening() {
        return noRekening;
    }

    public void setNoRekening(String noRekening) {
        this.noRekening = noRekening;
    }
}