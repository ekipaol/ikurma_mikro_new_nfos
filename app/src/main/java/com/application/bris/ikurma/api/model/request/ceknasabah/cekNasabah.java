package com.application.bris.ikurma.api.model.request.ceknasabah;

import com.google.gson.annotations.SerializedName;

/**
 * Created by idong on 14/05/2019.
 */

public class cekNasabah {
    @SerializedName("Key")
    private String key;
    @SerializedName("UID")
    private String UID;
    @SerializedName("KodeCabang")
    private String KodeCabang;

    public String getKey() {
        return key;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getKodeCabang() {
        return KodeCabang;
    }

    public void setKodeCabang(String kodeCabang) {
        KodeCabang = kodeCabang;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
