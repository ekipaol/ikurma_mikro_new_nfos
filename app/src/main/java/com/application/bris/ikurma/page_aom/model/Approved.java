package com.application.bris.ikurma.page_aom.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by idong on 09/07/2019.
 */

public class Approved {
    @SerializedName("FID_CIF_LAS")
    public Integer fIDCIFLAS;
    @SerializedName("ID_ST_APLIKASI")
    public Integer iDSTAPLIKASI;
    @SerializedName("KODE_PRODUK")
    public String kODEPRODUK;
    @SerializedName("PLAFOND_INDUK")
    public Long pLAFONDINDUK;
    @SerializedName("KLASIFIKASI_KREDIT")
    public String kLASIFIKASIKREDIT;
    @SerializedName("STATUS_APLIKASI")
    public String sTATUSAPLIKASI;
    @SerializedName("NAMA_PRODUK")
    public String nAMAPRODUK;
    @SerializedName("ID_APLIKASI")
    public Integer iDAPLIKASI;
    @SerializedName("TANGGAL_ENTRY")
    public String tANGGALENTRY;
    @SerializedName("FID_PHOTO")
    public Integer fIDPHOTO;
    @SerializedName("JW")
    public Integer jW;
    @SerializedName("NAMA_DEBITUR_1")
    public String nAMADEBITUR1;
    @SerializedName("NO_AKAD")
    public String nOAKAD;
    @SerializedName("NO_AKAD_QARDH")
    private String no_akad_qard;
    @SerializedName("JT_QARDH")
    private String jt_qardh;
    @SerializedName("ID_TUJUAN")
    private int id_tujuan;
    @SerializedName("NAMA_TUJUAN")
    private String nama_tujuan;

    public Approved(Integer fIDCIFLAS, Integer iDSTAPLIKASI, String kODEPRODUK, Long pLAFONDINDUK, String kLASIFIKASIKREDIT, String sTATUSAPLIKASI, String nAMAPRODUK, Integer iDAPLIKASI, String tANGGALENTRY, Integer fIDPHOTO, Integer jW, String nAMADEBITUR1, String nOAKAD, String no_akad_qard, String jt_qardh, int id_tujuan, String nama_tujuan) {
        this.fIDCIFLAS = fIDCIFLAS;
        this.iDSTAPLIKASI = iDSTAPLIKASI;
        this.kODEPRODUK = kODEPRODUK;
        this.pLAFONDINDUK = pLAFONDINDUK;
        this.kLASIFIKASIKREDIT = kLASIFIKASIKREDIT;
        this.sTATUSAPLIKASI = sTATUSAPLIKASI;
        this.nAMAPRODUK = nAMAPRODUK;
        this.iDAPLIKASI = iDAPLIKASI;
        this.tANGGALENTRY = tANGGALENTRY;
        this.fIDPHOTO = fIDPHOTO;
        this.jW = jW;
        this.nAMADEBITUR1 = nAMADEBITUR1;
        this.nOAKAD = nOAKAD;
        this.no_akad_qard = no_akad_qard;
        this.jt_qardh = jt_qardh;
        this.id_tujuan = id_tujuan;
        this.nama_tujuan = nama_tujuan;
    }

    public Integer getfIDCIFLAS() {
        return fIDCIFLAS;
    }

    public Integer getiDSTAPLIKASI() {
        return iDSTAPLIKASI;
    }

    public String getkODEPRODUK() {
        return kODEPRODUK;
    }

    public Long getpLAFONDINDUK() {
        return pLAFONDINDUK;
    }

    public String getkLASIFIKASIKREDIT() {
        return kLASIFIKASIKREDIT;
    }

    public String getsTATUSAPLIKASI() {
        return sTATUSAPLIKASI;
    }

    public String getnAMAPRODUK() {
        return nAMAPRODUK;
    }

    public Integer getiDAPLIKASI() {
        return iDAPLIKASI;
    }

    public String gettANGGALENTRY() {
        return tANGGALENTRY;
    }

    public Integer getfIDPHOTO() {
        return fIDPHOTO;
    }

    public Integer getjW() {
        return jW;
    }

    public String getnAMADEBITUR1() {
        return nAMADEBITUR1;
    }

    public String getnOAKAD() {
        return nOAKAD;
    }

    public String getNo_akad_qard() {
        return no_akad_qard;
    }

    public String getJt_qardh() {
        return jt_qardh;
    }

    public int getId_tujuan() {
        return id_tujuan;
    }

    public String getNama_tujuan() {
        return nama_tujuan;
    }
}
