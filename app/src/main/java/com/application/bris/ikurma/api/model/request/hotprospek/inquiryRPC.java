package com.application.bris.ikurma.api.model.request.hotprospek;

import com.google.gson.annotations.SerializedName;

/**
 * Created by idong on 20/06/2019.
 */

public class inquiryRPC {
    @SerializedName("idAplikasi")
    private int idAplikasi;

    public inquiryRPC(int idAplikasi) {
        this.idAplikasi = idAplikasi;
    }

    public void setIdAplikasi(int idAplikasi) {
        this.idAplikasi = idAplikasi;
    }
}
