package com.application.bris.ikurma.page_aom.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by idong on 25/06/2019.
 */

public class DataPrescreening {

    @SerializedName("ID_PRESCREENING")
    private Integer iDPRESCREENING;
    @SerializedName("DUKCAPIL")
    private String dUKCAPIL;
    @SerializedName("DHN")
    private String dHN;
    @SerializedName("SLIK")
    private String sLIK;
    @SerializedName("SIKP")
    private String sIKP;
    @SerializedName("RESULT")
    private String rESULT;

    @SerializedName("SLIK_KET")
    private String slik_ket;
    @SerializedName("DUCKAPIL_KET")
    private String dukcapil_ket;
    @SerializedName("SIKP_KET")
    private String sikp_ket;
    @SerializedName("DHN_KET")
    private String dhn_ket;

    @SerializedName("FID_APLIKASI")
    private Integer fIDAPLIKASI;
    @SerializedName("STATUS_KAWIN")
    private String sTATUSKAWIN;
    @SerializedName("STATUS_PERMINTAAN_SLIK")
    private String statusPermintaanSlik;

    public DataPrescreening(Integer iDPRESCREENING, String dUKCAPIL, String dHN, String sLIK, String sIKP, String rESULT, Integer fIDAPLIKASI, String sTATUSKAWIN) {
        this.iDPRESCREENING = iDPRESCREENING;
        this.dUKCAPIL = dUKCAPIL;
        this.dHN = dHN;
        this.sLIK = sLIK;
        this.sIKP = sIKP;
        this.rESULT = rESULT;
        this.fIDAPLIKASI = fIDAPLIKASI;
        this.sTATUSKAWIN = sTATUSKAWIN;
    }

    public String getStatusPermintaanSlik() {
        return statusPermintaanSlik;
    }

    public void setStatusPermintaanSlik(String statusPermintaanSlik) {
        this.statusPermintaanSlik = statusPermintaanSlik;
    }

    public String getSlik_ket() {
        return slik_ket;
    }

    public void setSlik_ket(String slik_ket) {
        this.slik_ket = slik_ket;
    }

    public String getDukcapil_ket() {
        return dukcapil_ket;
    }

    public void setDukcapil_ket(String dukcapil_ket) {
        this.dukcapil_ket = dukcapil_ket;
    }

    public String getSikp_ket() {
        return sikp_ket;
    }

    public void setSikp_ket(String sikp_ket) {
        this.sikp_ket = sikp_ket;
    }

    public String getDhn_ket() {
        return dhn_ket;
    }

    public void setDhn_ket(String dhn_ket) {
        this.dhn_ket = dhn_ket;
    }

    public Integer getiDPRESCREENING() {
        return iDPRESCREENING;
    }

    public String getdUKCAPIL() {
        return dUKCAPIL;
    }

    public String getdHN() {
        return dHN;
    }

    public String getsLIK() {
        return sLIK;
    }

    public String getsIKP() {
        return sIKP;
    }

    public String getrESULT() {
        return rESULT;
    }

    public Integer getfIDAPLIKASI() {
        return fIDAPLIKASI;
    }

    public String getsTATUSKAWIN(){
        return  sTATUSKAWIN;
    }
}
