package com.application.bris.ikurma.api.model.request.hotprospek;

import com.google.gson.annotations.SerializedName;

/**
 * Created by PID on 4/5/2019.
 */

public class listHotprospek {
    @SerializedName("uid")
    private String uid;
    @SerializedName("branchId")
    private String kodeSkk;
    @SerializedName("namaNasabah")
    private String namaNasabah;

    public void setUid(String uid) {
        this.uid = uid;
    }

    public void setKodeSkk(String kodeSkk) {
        this.kodeSkk = kodeSkk;
    }

    public void setNamaNasabah(String namaNasabah) {
        this.namaNasabah = namaNasabah;
    }
}
