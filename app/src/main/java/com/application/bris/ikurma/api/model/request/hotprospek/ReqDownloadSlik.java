package com.application.bris.ikurma.api.model.request.hotprospek;

import com.google.gson.annotations.SerializedName;

public class ReqDownloadSlik {
    @SerializedName("id_aplikasi")
    private String idAplikasi;

    public String getIdAplikasi() {
        return idAplikasi;
    }

    public void setIdAplikasi(String idAplikasi) {
        this.idAplikasi = idAplikasi;
    }
}
