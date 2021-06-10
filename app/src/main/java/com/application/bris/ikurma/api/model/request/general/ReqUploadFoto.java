package com.application.bris.ikurma.api.model.request.general;

import com.google.gson.annotations.SerializedName;

public class ReqUploadFoto {
    @SerializedName("File")
    private String file;

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
