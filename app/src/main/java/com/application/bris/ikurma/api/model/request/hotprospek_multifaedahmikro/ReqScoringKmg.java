package com.application.bris.ikurma.api.model.request.hotprospek_multifaedahmikro;

import com.google.gson.annotations.SerializedName;

public class ReqScoringKmg {
    @SerializedName("idAplikasi")
    private int idAplikasi;
    @SerializedName("cif")
    private int cif;

    public int getIdAplikasi() {
        return idAplikasi;
    }

    public void setIdAplikasi(int idAplikasi) {
        this.idAplikasi = idAplikasi;
    }

    public int getCif() {
        return cif;
    }

    public void setCif(int cif) {
        this.cif = cif;
    }
}
