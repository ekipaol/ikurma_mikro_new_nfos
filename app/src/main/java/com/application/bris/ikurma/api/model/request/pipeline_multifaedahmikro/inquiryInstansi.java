package com.application.bris.ikurma.api.model.request.pipeline_multifaedahmikro;

import com.google.gson.annotations.SerializedName;

/**
 * Created by idong on 20/06/2019.
 */

public class inquiryInstansi {
    @SerializedName("kode_skk")
    private String kode_skk;

    public inquiryInstansi(String kode_skk) {
        this.kode_skk = kode_skk;
    }

    public void setKode_skk(String kode_skk) {
        this.kode_skk = kode_skk;
    }
}