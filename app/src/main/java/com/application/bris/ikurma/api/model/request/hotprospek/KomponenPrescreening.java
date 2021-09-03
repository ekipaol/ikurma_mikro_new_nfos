package com.application.bris.ikurma.api.model.request.hotprospek;

import com.google.gson.annotations.SerializedName;

public class KomponenPrescreening {
        @SerializedName("idAplikasi")
        private String idAplikasi;

        @SerializedName("UID")
        private String uid;

        @SerializedName("KodeCabang")
        private String kodeCabang;

        public void setIdAplikasi(String idAplikasi) {
            this.idAplikasi = idAplikasi;
        }

        public String getIdAplikasi() {
            return idAplikasi;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getKodeCabang() {
            return kodeCabang;
        }

        public void setKodeCabang(String kodeCabang) {
            this.kodeCabang = kodeCabang;
        }

}
