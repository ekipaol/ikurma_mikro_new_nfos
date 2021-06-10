package com.application.bris.ikurma.page_aom.view.approved.agunan_apr;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.application.bris.ikurma.R;
import com.application.bris.ikurma.page_aom.model.AgunanTanahBangunan;
import com.application.bris.ikurma.util.AppUtil;
import com.application.bris.ikurma.util.NumberTextWatcherCanNolForThousand;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

/**
 * A simple {@link Fragment} subclass.
 */

/**
 * Created by PID on 6/15/2019.
 */

@SuppressLint("ValidFragment")
public class FragmentAgunan6Hasil_Apr extends Fragment implements Step{

    @BindView(R.id.tf_tanggal_pemeriksaan)
    TextFieldBoxes tf_tanggal_pemeriksaan;
    @BindView(R.id.et_tanggal_pemeriksaan)
    EditText et_tanggal_pemeriksaan;

    @BindView(R.id.tf_data_pembanding_1)
    TextFieldBoxes tf_data_pembanding_1;
    @BindView(R.id.et_data_pembanding_1)
    EditText et_data_pembanding_1;

    @BindView(R.id.tf_data_pembanding_2)
    TextFieldBoxes tf_data_pembanding_2;
    @BindView(R.id.et_data_pembanding_2)
    EditText et_data_pembanding_2;

    @BindView(R.id.tf_luas_tanah_hasil)
    TextFieldBoxes tf_luas_tanah_hasil;
    @BindView(R.id.et_luas_tanah_hasil)
    EditText et_luas_tanah_hasil;

    @BindView(R.id.tf_harga_tanah)
    TextFieldBoxes tf_harga_tanah;
    @BindView(R.id.et_harga_tanah)
    EditText et_harga_tanah;

    @BindView(R.id.tf_luas_bangunan_imb)
    TextFieldBoxes tf_luas_bangunan_imb;
    @BindView(R.id.et_luas_bangunan_imb)
    EditText et_luas_bangunan_imb;

    @BindView(R.id.tf_luas_bangunan_tidak_imb)
    TextFieldBoxes tf_luas_bangunan_tidak_imb;
    @BindView(R.id.et_luas_bangunan_tidak_imb)
    EditText et_luas_bangunan_tidak_imb;

    @BindView(R.id.tf_harga_bangunan_imb)
    TextFieldBoxes tf_harga_bangunan_imb;
    @BindView(R.id.et_harga_bangunan)
    EditText et_harga_bangunan;

    @BindView(R.id.tf_nilai_likuidasi)
    TextFieldBoxes tf_nilai_likuidasi;
    @BindView(R.id.et_nilai_likudasi)
    EditText et_nilai_likudasi;

    @BindView(R.id.tf_luas_total)
    TextFieldBoxes tf_luas_total;
    @BindView(R.id.et_luas_total)
    EditText et_luas_total;

    @BindView(R.id.tf_npw_tanah)
    TextFieldBoxes tf_npw_tanah;
    @BindView(R.id.et_npw_tanah)
    EditText et_npw_tanah;

    @BindView(R.id.tf_npw_bangunan)
    TextFieldBoxes tf_npw_bangunan;
    @BindView(R.id.et_npw_bangunan)
    EditText et_npw_bangunan;

    @BindView(R.id.tf_npw_tanah_dan_bangunan)
    TextFieldBoxes tf_npw_tanah_dan_bangunan;
    @BindView(R.id.et_npw_tanah_dan_bangunan)
    EditText et_npw_tanah_dan_bangunan;

    @BindView(R.id.tf_npw_bangunan_diasuransikan)
    TextFieldBoxes tf_npw_bangunan_diasuransikan;
    @BindView(R.id.et_npw_bangunan_diasuransikan)
    EditText et_npw_bangunan_diasuransikan;

    public static SimpleDateFormat dateClient = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

    private AgunanTanahBangunan dataAgunan;
    private String idAgunan;
    private String loan_type, tp_produk;

    public FragmentAgunan6Hasil_Apr() {
    }

    public FragmentAgunan6Hasil_Apr(String midAgunan, AgunanTanahBangunan magunanTanahBangunan, String mloan_type, String mtp_produk) {
        dataAgunan = magunanTanahBangunan;
        idAgunan = midAgunan;
        loan_type = mloan_type;
        tp_produk = mtp_produk;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.agunan_6_hasil_penilaian, container, false);
        ButterKnife.bind(this, view);
        if(!idAgunan.equalsIgnoreCase("0")) {
            setDisable();
            setData();
        }
        onChangeText();
        return view;
    }

    private void onChangeText(){
        et_harga_tanah.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_harga_tanah));
        et_harga_bangunan.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_harga_bangunan));
        et_nilai_likudasi.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_nilai_likudasi));
        et_npw_tanah.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_npw_tanah));
        et_npw_bangunan.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_npw_bangunan));
        et_npw_tanah_dan_bangunan.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_npw_tanah_dan_bangunan));
        et_npw_bangunan_diasuransikan.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_npw_bangunan_diasuransikan));
    }


    private void setDisable(){
        et_tanggal_pemeriksaan.setEnabled(false);
        et_data_pembanding_1.setEnabled(false);
        et_data_pembanding_2.setEnabled(false);
        et_luas_tanah_hasil.setEnabled(false);
        et_harga_tanah.setEnabled(false);
        et_luas_bangunan_imb.setEnabled(false);
        et_luas_bangunan_tidak_imb.setEnabled(false);
        et_harga_bangunan.setEnabled(false);
        et_nilai_likudasi.setEnabled(false);
        et_luas_total.setEnabled(false);
        et_npw_tanah.setEnabled(false);
        et_npw_bangunan.setEnabled(false);
        et_npw_tanah_dan_bangunan.setEnabled(false);
        et_npw_bangunan_diasuransikan.setEnabled(false);
    }

    private void setData(){
        try {
            et_tanggal_pemeriksaan.setText(AppUtil.parseTanggalGeneral(dataAgunan.getTanggalPemeriksaan(), "ddMMyyyy", "dd-MM-yyyy"));
            et_data_pembanding_1.setText(dataAgunan.getDataPembanding1());
            et_data_pembanding_2.setText(dataAgunan.getDataPembanding2());
            et_luas_tanah_hasil.setText(dataAgunan.getLuasTanahSertifikat());
            et_harga_tanah.setText(String.valueOf(dataAgunan.getHargaMeterTanah()));
            et_luas_bangunan_imb.setText(String.valueOf(dataAgunan.getLuasBangunan1()));
            et_harga_bangunan.setText(String.valueOf(dataAgunan.getHargaBangunan1()));
            et_luas_bangunan_tidak_imb.setText(String.valueOf(dataAgunan.getLuasBangunan2()));
            et_nilai_likudasi.setText(String.valueOf(dataAgunan.getnLTanahBangunan()));

            BigDecimal luas_total = new BigDecimal(String.valueOf(dataAgunan.getLuasBangunan1())).add(new BigDecimal(String.valueOf(dataAgunan.getLuasBangunan2())));
            et_luas_total.setText(String.valueOf(luas_total));

            BigDecimal npw_tanah = new BigDecimal(String.valueOf(dataAgunan.getLuasTanahSertifikat()))
                    .multiply(new BigDecimal(String.valueOf(dataAgunan.getHargaMeterTanah())))
                    .setScale(0, RoundingMode.HALF_UP);
            et_npw_tanah.setText(String.valueOf(npw_tanah));

            et_npw_bangunan.setText(String.valueOf(dataAgunan.getnPWBangunan()));
            et_npw_tanah_dan_bangunan.setText(String.valueOf(dataAgunan.getnPWTanahBangunan()));
            et_npw_bangunan_diasuransikan.setText(String.valueOf(dataAgunan.getnPWBangunanBRIS()));
        }
        catch (Exception e){
            AppUtil.showToastShort(getContext(), e.getMessage());
        }

    }


    @Nullable
    @Override
    public VerificationError verifyStep() {
        return  null;
    }

    @Override
    public void onSelected() {
    }

    @Override
    public void onError(@NonNull VerificationError verificationError) {
    }
}



