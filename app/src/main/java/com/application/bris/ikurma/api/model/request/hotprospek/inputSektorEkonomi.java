package com.application.bris.ikurma.api.model.request.hotprospek;

import com.google.gson.annotations.SerializedName;

/**
 * Created by idong on 21/06/2019.
 */

public class inputSektorEkonomi {

    @SerializedName("idAplikasi")
    private Integer idAplikasi;
    @SerializedName("cifLas")
    private Integer cifLas;
    @SerializedName("hubDebiturDgnBank")
    private String hubDebiturDgnBank;
    @SerializedName("bidangUsaha")
    private String bidangUsaha;
    @SerializedName("sifatKredit")
    private String sifatKredit;
    @SerializedName("jenisPenggunaan")
    private String jenisPenggunaan;
    @SerializedName("jenisPenggunaanLBU")
    private String jenisPenggunaanLBU;
    @SerializedName("jenisKreditLBU")
    private Integer jenisKreditLBU;
    @SerializedName("sifatKreditLBU")
    private Integer sifatKreditLBU;
    @SerializedName("kategoriKreditLBU")
    private Integer kategoriKreditLBU;
    @SerializedName("sektorEkonomiSID")
    private String sektorEkonomiSID;
    @SerializedName("sektorEkonomiLBU")
    private String sektorEkonomiLBU;

    public inputSektorEkonomi(Integer idAplikasi, Integer cifLas, String hubDebiturDgnBank, String bidangUsaha, String sifatKredit, String jenisPenggunaan, String jenisPenggunaanLBU, Integer jenisKreditLBU, Integer sifatKreditLBU, Integer kategoriKreditLBU, String sektorEkonomiSID, String sektorEkonomiLBU) {
        this.idAplikasi = idAplikasi;
        this.cifLas = cifLas;
        this.hubDebiturDgnBank = hubDebiturDgnBank;
        this.bidangUsaha = bidangUsaha;
        this.sifatKredit = sifatKredit;
        this.jenisPenggunaan = jenisPenggunaan;
        this.jenisPenggunaanLBU = jenisPenggunaanLBU;
        this.jenisKreditLBU = jenisKreditLBU;
        this.sifatKreditLBU = sifatKreditLBU;
        this.kategoriKreditLBU = kategoriKreditLBU;
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

    public void setSifatKredit(String sifatKredit) {
        this.sifatKredit = sifatKredit;
    }

    public void setJenisPenggunaan(String jenisPenggunaan) {
        this.jenisPenggunaan = jenisPenggunaan;
    }

    public void setJenisPenggunaanLBU(String jenisPenggunaanLBU) {
        this.jenisPenggunaanLBU = jenisPenggunaanLBU;
    }

    public void setJenisKreditLBU(Integer jenisKreditLBU) {
        this.jenisKreditLBU = jenisKreditLBU;
    }

    public void setSifatKreditLBU(Integer sifatKreditLBU) {
        this.sifatKreditLBU = sifatKreditLBU;
    }

    public void setKategoriKreditLBU(Integer kategoriKreditLBU) {
        this.kategoriKreditLBU = kategoriKreditLBU;
    }

    public void setSektorEkonomiSID(String sektorEkonomiSID) {
        this.sektorEkonomiSID = sektorEkonomiSID;
    }

    public void setSektorEkonomiLBU(String sektorEkonomiLBU) {
        this.sektorEkonomiLBU = sektorEkonomiLBU;
    }
}
