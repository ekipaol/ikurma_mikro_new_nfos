package com.application.bris.ikurma.api.model.request.pipeline_multifaedahmikro;
import com.google.gson.annotations.SerializedName;

/**
 * Created by idong on 17/05/2019.
 */

public class KmgInquiryPipeline {
    @SerializedName("idPipeline")
    private int idPipeline;

    public KmgInquiryPipeline(int idPipeline) {
        this.idPipeline = idPipeline;
    }

    public void setIdPipeline(int idPipeline) {
        this.idPipeline = idPipeline;
    }
}