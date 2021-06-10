package com.application.bris.ikurma.page_aom.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by idong on 28/10/2019.
 */

public class ListJenisBangunan {

    @SerializedName("KODE_OKUPASI")
    private String KODE_OKUPASI;
    @SerializedName("DESC_BRINS")
    private String DESC_BRINS;

    public String getKODE_OKUPASI() {
        return KODE_OKUPASI;
    }

    public String getDESC_BRINS() {
        return DESC_BRINS;
    }
}
