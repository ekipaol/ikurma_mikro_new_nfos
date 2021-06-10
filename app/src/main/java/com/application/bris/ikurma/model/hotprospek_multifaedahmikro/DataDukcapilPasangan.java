package com.application.bris.ikurma.model.hotprospek_multifaedahmikro;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataDukcapilPasangan {

    @Expose
    @SerializedName("nama")
    private String nama;

    @Expose
    @SerializedName("tgl_lahir")
    private String tgl_lahir;

    public String getNama() {
        return nama;
    }

    public String getTgl_lahir() {
        return tgl_lahir;
    }
}
