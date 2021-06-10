package com.application.bris.ikurma.api.model.request.hotprospek;

import com.google.gson.annotations.SerializedName;

public class PrescreeningSikp
{

    @SerializedName("IdAplikasi")
    private int idAplikasi;

    @SerializedName("IdUser")
    private int uid;

    public int getIdAplikasi() {
        return idAplikasi;
    }

    public void setIdAplikasi(int idAplikasi) {
        this.idAplikasi = idAplikasi;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
