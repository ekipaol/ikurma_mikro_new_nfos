package com.application.bris.ikurma.page_aom.listener;

import com.application.bris.ikurma.page_aom.model.MKategoriNasabahPensiun;
import com.application.bris.ikurma.page_aom.model.MListInstansi;
import com.application.bris.ikurma.page_aom.model.MProgramKmg;
import com.application.bris.ikurma.page_aom.model.MRekananDM;
import com.application.bris.ikurma.page_aom.model.MTujuanPenggunaan;

/**
 * Created by PID on 05/05/19.
 */

public interface LoadDataInputPipelineKmgListener {

    void onSelectProgram(String title, MProgramKmg data);

    void onSelectInstitusi(String title, MKategoriNasabahPensiun data);

    void onSelectRekananDM(String title, MRekananDM data);

    void onSelectTujuan(String title, MTujuanPenggunaan data);

    void onSelectInstansi(String title, MListInstansi data);

    void onSelectKategNasabah(String title, MKategoriNasabahPensiun data);
}

