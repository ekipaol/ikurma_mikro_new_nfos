package com.application.bris.ikurma.api.model.request.agunan;

import com.google.gson.annotations.SerializedName;

public class ReqAgunan {
    @SerializedName("idCif")
    private String idCif;
    @SerializedName("idApl")
    private String idApl;

    public String getIdCif() {
        return idCif;
    }

    public void setIdCif(String idCif) {
        this.idCif = idCif;
    }

    public String getIdApl() {
        return idApl;
    }

    public void setIdApl(String idApl) {
        this.idApl = idApl;
    }
}
