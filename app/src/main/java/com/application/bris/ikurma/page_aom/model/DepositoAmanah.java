package com.application.bris.ikurma.page_aom.model;

import com.google.gson.annotations.SerializedName;

public class DepositoAmanah {
    @SerializedName("MUDTENOR")
    private String MUDTENOR;
    @SerializedName("WORKINGBALANCE")
    private String WORKINGBALANCE;
    @SerializedName("CATEGORY")
    private String CATEGORY;
    @SerializedName("MUDBILYET")
    private String MUDBILYET;
    @SerializedName("CREATEDATE")
    private String CREATEDATE;
    @SerializedName("MATURITYDATE")
    private String MATURITYDATE;
    @SerializedName("ACCTNAME")
    private String ACCTNAME;
    @SerializedName("AVAILBAL")
    private String AVAILBAL;
    @SerializedName("AROOPTION")
    private String AROOPTION;
    @SerializedName("CATDESC")
    private String CATDESC;
    @SerializedName("MUDNISBAH")
    private String MUDNISBAH;
    @SerializedName("ACCTNO")
    private String ACCTNO;

    public String getMATURITYDATE() {
        return MATURITYDATE;
    }

    public void setMATURITYDATE(String MATURITYDATE) {
        this.MATURITYDATE = MATURITYDATE;
    }

    public String getMUDTENOR() {
        return MUDTENOR;
    }

    public void setMUDTENOR(String MUDTENOR) {
        this.MUDTENOR = MUDTENOR;
    }

    public String getWORKINGBALANCE() {
        return WORKINGBALANCE;
    }

    public void setWORKINGBALANCE(String WORKINGBALANCE) {
        this.WORKINGBALANCE = WORKINGBALANCE;
    }

    public String getCATEGORY() {
        return CATEGORY;
    }

    public void setCATEGORY(String CATEGORY) {
        this.CATEGORY = CATEGORY;
    }

    public String getMUDBILYET() {
        return MUDBILYET;
    }

    public void setMUDBILYET(String MUDBILYET) {
        this.MUDBILYET = MUDBILYET;
    }

    public String getCREATEDATE() {
        return CREATEDATE;
    }

    public void setCREATEDATE(String CREATEDATE) {
        this.CREATEDATE = CREATEDATE;
    }

    public String getACCTNAME() {
        return ACCTNAME;
    }

    public void setACCTNAME(String ACCTNAME) {
        this.ACCTNAME = ACCTNAME;
    }

    public String getAVAILBAL() {
        return AVAILBAL;
    }

    public void setAVAILBAL(String AVAILBAL) {
        this.AVAILBAL = AVAILBAL;
    }

    public String getAROOPTION() {
        return AROOPTION;
    }

    public void setAROOPTION(String AROOPTION) {
        this.AROOPTION = AROOPTION;
    }

    public String getCATDESC() {
        return CATDESC;
    }

    public void setCATDESC(String CATDESC) {
        this.CATDESC = CATDESC;
    }

    public String getMUDNISBAH() {
        return MUDNISBAH;
    }

    public void setMUDNISBAH(String MUDNISBAH) {
        this.MUDNISBAH = MUDNISBAH;
    }

    public String getACCTNO() {
        return ACCTNO;
    }

    public void setACCTNO(String ACCTNO) {
        this.ACCTNO = ACCTNO;
    }
}
