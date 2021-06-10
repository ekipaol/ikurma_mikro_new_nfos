package com.application.bris.ikurma.api.model.request.agunan;

import com.google.gson.annotations.SerializedName;

public class ReqCifDeposito {
    @SerializedName("cif")
    private String cif;

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }
}
