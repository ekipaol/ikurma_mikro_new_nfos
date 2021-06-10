package com.application.bris.ikurma.api.model.request.hotprospek;

import com.google.gson.annotations.SerializedName;

/**
 * Created by idong on 27/06/2019.
 */

public class kirimPutusanMikroDeviasi {

    @SerializedName("uid")
    public Integer uid;
    @SerializedName("idAplikasi")
    public Integer idAplikasi;
    @SerializedName("uidUH")
    public Integer uidUH;
    @SerializedName("uidPINCAPEM")
    public Integer uidPINCAPEM;
    @SerializedName("uidMMM")
    public Integer uidMMM;
    @SerializedName("uidAM")
    public Integer uidAM;
    @SerializedName("uidAMPM")
    public Integer uidAMPM;
    @SerializedName("uidMBDGH")
    public Integer uidMBDGH;
    @SerializedName("uidMBDDH")
    public Integer uidMBDDH;
    @SerializedName("uidPINCA")
    public Integer uidPINCA;
    @SerializedName("nama")
    public String nama;
    @SerializedName("ketDeviasi")
    public String ketDeviasi;

    public kirimPutusanMikroDeviasi(Integer uid, Integer idAplikasi, Integer uidUH, Integer uidPINCAPEM, Integer uidMMM, Integer uidPINCA, String nama, String ketDeviasi) {
        this.uid = uid;
        this.idAplikasi = idAplikasi;
        this.uidUH = uidUH;
        this.uidPINCAPEM = uidPINCAPEM;
        this.uidMMM = uidMMM;
        this.uidPINCA = uidPINCA;
        this.nama = nama;
        this.ketDeviasi = ketDeviasi;
    }

    public Integer getUidAM() {
        return uidAM;
    }

    public void setUidAM(Integer uidAM) {
        this.uidAM = uidAM;
    }

    public Integer getUidAMPM() {
        return uidAMPM;
    }

    public void setUidAMPM(Integer uidAMPM) {
        this.uidAMPM = uidAMPM;
    }

    public Integer getUidMBDGH() {
        return uidMBDGH;
    }

    public void setUidMBDGH(Integer uidMBDGH) {
        this.uidMBDGH = uidMBDGH;
    }

    public Integer getUidMBDDH() {
        return uidMBDDH;
    }

    public void setUidMBDDH(Integer uidMBDDH) {
        this.uidMBDDH = uidMBDDH;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public void setIdAplikasi(Integer idAplikasi) {
        this.idAplikasi = idAplikasi;
    }

    public void setUidUH(Integer uidUH) {
        this.uidUH = uidUH;
    }

    public void setUidPINCAPEM(Integer uidPINCAPEM) {
        this.uidPINCAPEM = uidPINCAPEM;
    }

    public void setUidMMM(Integer uidMMM) {
        this.uidMMM = uidMMM;
    }

    public void setUidPINCA(Integer uidPINCA) {
        this.uidPINCA = uidPINCA;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setKetDeviasi(String ketDeviasi) {
        this.ketDeviasi = ketDeviasi;
    }
}
