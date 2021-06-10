package com.application.bris.ikurma.api.model.request.hotprospek_multifaedahmikro;

import com.google.gson.annotations.SerializedName;

/**
 * Created by idong on 21/06/2019.
 */

public class inputSektorEkonomiKmg {

    @SerializedName("idAplikasi")
    private Integer idAplikasi;
    @SerializedName("cifLas")
    private Integer cifLas;
    @SerializedName("hubDebiturDgnBank")
    private String hubDebiturDgnBank;
    @SerializedName("bidangUsaha")
    private String bidangUsaha;
    @SerializedName("sifatPembiayaan")
    private String sifatPembiayaan;
    @SerializedName("jenisPenggunaan")
    private String jenisPenggunaan;
    @SerializedName("jenisPenggunaanLBU")
    private String jenisPenggunaanLBU;
    @SerializedName("jenisPembiayaanLBU")
    private Integer jenisPembiayaanLBU;
    @SerializedName("sifatPembiayaanLBU")
    private Integer sifatPembiayaanLBU;
    @SerializedName("kategoriPembiayaanLBU")
    private Integer kategoriPembiayaanLBU;
    @SerializedName("sektorEkonomiSID")
    private String sektorEkonomiSID;
    @SerializedName("sektorEkonomiLBU")
    private String sektorEkonomiLBU;

    public inputSektorEkonomiKmg(Integer idAplikasi, Integer cifLas, String hubDebiturDgnBank, String bidangUsaha, String sifatPembiayaan, String jenisPenggunaan, String jenisPenggunaanLBU, Integer jenisPembiayaanLBU, Integer sifatPembiayaanLBU, Integer kategoriPembiayaanLBU, String sektorEkonomiSID, String sektorEkonomiLBU) {
        this.idAplikasi = idAplikasi;
        this.cifLas = cifLas;
        this.hubDebiturDgnBank = hubDebiturDgnBank;
        this.bidangUsaha = bidangUsaha;
        this.sifatPembiayaan = sifatPembiayaan;
        this.jenisPenggunaan = jenisPenggunaan;
        this.jenisPenggunaanLBU = jenisPenggunaanLBU;
        this.jenisPembiayaanLBU = jenisPembiayaanLBU;
        this.sifatPembiayaanLBU = sifatPembiayaanLBU;
        this.kategoriPembiayaanLBU = kategoriPembiayaanLBU;
        this.sektorEkonomiSID = sektorEkonomiSID;
        this.sektorEkonomiLBU = sektorEkonomiLBU;
    }

    public void setIdAplikasi(Integer idAplikasi) {
        this.idAplikasi = idAplikasi;
    }

    public void setCifLas(Integer cifLas) {
        this.cifLas = cifLas;
    }

    public void setHubDebiturDgnBank(String hubDebiturDgnBank) {
        this.hubDebiturDgnBank = hubDebiturDgnBank;
    }

    public void setBidangUsaha(String bidangUsaha) {
        this.bidangUsaha = bidangUsaha;
    }

    public void setSifatPembiayaan(String sifatPembiayaan) {
        this.sifatPembiayaan = sifatPembiayaan;
    }

    public void setJenisPenggunaan(String jenisPenggunaan) {
        this.jenisPenggunaan = jenisPenggunaan;
    }

    public void setJenisPenggunaanLBU(String jenisPenggunaanLBU) {
        this.jenisPenggunaanLBU = jenisPenggunaanLBU;
    }

    public void setJenisPembiayaanLBU(Integer jenisPembiayaanLBU) {
        this.jenisPembiayaanLBU = jenisPembiayaanLBU;
    }

    public void setSifatPembiayaanLBU(Integer sifatPembiayaanLBU) {
        this.sifatPembiayaanLBU = sifatPembiayaanLBU;
    }

    public void setKategoriPembiayaanLBU(Integer kategoriPembiayaanLBU) {
        this.kategoriPembiayaanLBU = kategoriPembiayaanLBU;
    }

    public void setSektorEkonomiSID(String sektorEkonomiSID) {
        this.sektorEkonomiSID = sektorEkonomiSID;
    }

    public void setSektorEkonomiLBU(String sektorEkonomiLBU) {
        this.sektorEkonomiLBU = sektorEkonomiLBU;
    }
}
