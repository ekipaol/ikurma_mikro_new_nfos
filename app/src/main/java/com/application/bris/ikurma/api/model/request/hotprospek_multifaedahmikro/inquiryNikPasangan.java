package com.application.bris.ikurma.api.model.request.hotprospek_multifaedahmikro;

import com.google.gson.annotations.SerializedName;

/**
 * Created by idong on 17/05/2019.
 */

public class inquiryNikPasangan {
    @SerializedName("noKtpPasangan")
    private String noKtpPasangan;

    public inquiryNikPasangan(String noKtpPasangan) {
        this.noKtpPasangan = noKtpPasangan;
    }
}
