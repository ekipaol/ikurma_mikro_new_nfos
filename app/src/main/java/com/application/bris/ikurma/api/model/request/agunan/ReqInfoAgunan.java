package com.application.bris.ikurma.api.model.request.agunan;

import com.google.gson.annotations.SerializedName;

public class ReqInfoAgunan {
    @SerializedName("idApl")
    private int idApl;
    @SerializedName("idAgunan")
    private int idAgunan;
    @SerializedName("idCif")
    private int idCif;
    @SerializedName("idPengikatan")
    private int idPengikatan;

    public int getIdPengikatan() {
        return idPengikatan;
    }

    public void setIdPengikatan(int idPengikatan) {
        this.idPengikatan = idPengikatan;
    }

    public void setIdApl(int idApl) {
        this.idApl = idApl;
    }

    public void setIdAgunan(int idAgunan) {
        this.idAgunan = idAgunan;
    }

    public void setIdCif(int idCif) {
        this.idCif = idCif;
    }
}
