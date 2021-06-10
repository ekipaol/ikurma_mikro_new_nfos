package com.application.bris.ikurma.page_aom.view.approved.datalengkap_apr;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.application.bris.ikurma.R;
import com.application.bris.ikurma.page_aom.model.DataLengkap;
import com.application.bris.ikurma.util.AppUtil;
import com.application.bris.ikurma.util.KeyValue;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import java.text.SimpleDateFormat;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

/**
 * Created by PID on 6/15/2019.
 */

@SuppressLint("ValidFragment")
public class FragmentDataPribadi_Apr extends Fragment implements Step{

    @BindView(R.id.tf_nik)
    TextFieldBoxes tf_nik;
    @BindView(R.id.et_nik)
    EditText et_nik;
    @BindView(R.id.tf_expirednik)
    TextFieldBoxes tf_expirednik;
    @BindView(R.id.et_expirednik)
    EditText et_expirednik;
    @BindView(R.id.tf_npwp)
    TextFieldBoxes tf_npwp;
    @BindView(R.id.et_npwp)
    EditText et_npwp;
    @BindView(R.id.tf_nama)
    TextFieldBoxes tf_nama;
    @BindView(R.id.et_nama)
    EditText et_nama;
    @BindView(R.id.tf_namaalias)
    TextFieldBoxes tf_namaalias;
    @BindView(R.id.et_namaalias)
    EditText et_namaalias;
    @BindView(R.id.tf_tempatlahir)
    TextFieldBoxes tf_tempatlahir;
    @BindView(R.id.et_tempatlahir)
    EditText et_tempatlahir;
    @BindView(R.id.tf_tanggallahir)
    TextFieldBoxes tf_tanggallahir;
    @BindView(R.id.et_tanggallahir)
    EditText et_tanggallahir;
    @BindView(R.id.tf_jeniskelamin)
    TextFieldBoxes tf_jeniskelamin;
    @BindView(R.id.et_jeniskelamin)
    EditText et_jeniskelamin;
    @BindView(R.id.tf_nomorhp)
    TextFieldBoxes tf_nomorhp;
    @BindView(R.id.et_nomorhp)
    EditText et_nomorhp;
    @BindView(R.id.tf_email)
    TextFieldBoxes tf_email;
    @BindView(R.id.et_email)
    EditText et_email;
    @BindView(R.id.tf_agama)
    TextFieldBoxes tf_agama;
    @BindView(R.id.et_agama)
    EditText et_agama;
    @BindView(R.id.tf_ketagama)
    TextFieldBoxes tf_ketagama;
    @BindView(R.id.et_ketagama)
    EditText et_ketagama;
    @BindView(R.id.tf_namaibukandung)
    TextFieldBoxes tf_namaibukandung;
    @BindView(R.id.et_namaibukandung)
    EditText et_namaibukandung;
    @BindView(R.id.tf_statusnikah)
    TextFieldBoxes tf_statusnikah;
    @BindView(R.id.et_statusnikah)
    EditText et_statusnikah;
    @BindView(R.id.btn_cek_nik_pasangan)
    Button btn_cek_nik_pasangan;
    @BindView(R.id.tf_nikpasangan)
    TextFieldBoxes tf_nikpasangan;
    @BindView(R.id.et_nikpasangan)
    EditText et_nikpasangan;
    @BindView(R.id.tf_namapasangan)
    TextFieldBoxes tf_namapasangan;
    @BindView(R.id.et_namapasangan)
    EditText et_namapasangan;
    @BindView(R.id.tf_tanggallahirpasangan)
    TextFieldBoxes tf_tanggallahirpasangan;
    @BindView(R.id.et_tanggallahirpasangan)
    EditText et_tanggallahirpasangan;
    @BindView(R.id.tf_namakeluarga)
    TextFieldBoxes tf_namakeluarga;
    @BindView(R.id.et_namakeluarga)
    EditText et_namakeluarga;
    @BindView(R.id.tf_nomorhpkeluarga)
    TextFieldBoxes tf_nomorhpkeluarga;
    @BindView(R.id.et_nomorhpkeluarga)
    EditText et_nomorhpkeluarga;
    @BindView(R.id.tf_jumlahtanggungan)
    TextFieldBoxes tf_jumlahtanggungan;
    @BindView(R.id.et_jumlahtanggungan)
    EditText et_jumlahtanggungan;
    @BindView(R.id.tf_tipependapatan)
    TextFieldBoxes tf_tipependapatan;
    @BindView(R.id.et_tipependapatan)
    EditText et_tipependapatan;
    @BindView(R.id.tf_pendidikanterakhir)
    TextFieldBoxes tf_pendidikanterakhir;
    @BindView(R.id.et_pendidikanterakhir)
    EditText et_pendidikanterakhir;
    @BindView(R.id.tf_gelar)
    TextFieldBoxes tf_gelar;
    @BindView(R.id.et_gelar)
    EditText et_gelar;

    @BindView(R.id.ll_pasangan)
    LinearLayout ll_pasangan;

    public static SimpleDateFormat dateClient = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
    private DataLengkap dataLengkap;

    public FragmentDataPribadi_Apr() {
    }

    public FragmentDataPribadi_Apr(DataLengkap mdataLengkap) {
        dataLengkap = mdataLengkap;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ao_fragment_datapribadi, container, false);
        ButterKnife.bind(this, view);
        setDisable();
        setData();
        return view;
    }

    private void setDisable(){
        et_nik.setEnabled(false);
        et_expirednik.setEnabled(false);
        et_npwp.setEnabled(false);
        et_nama.setEnabled(false);
        et_namaalias.setEnabled(false);
        et_tempatlahir.setEnabled(false);
        et_tanggallahir.setEnabled(false);
        et_jeniskelamin.setEnabled(false);
        et_nomorhp.setEnabled(false);
        et_email.setEnabled(false);
        et_agama.setEnabled(false);
        et_namaibukandung.setEnabled(false);
        et_statusnikah.setEnabled(false);
        btn_cek_nik_pasangan.setVisibility(View.GONE);
        et_nikpasangan.setEnabled(false);
        et_namapasangan.setEnabled(false);
        et_tanggallahirpasangan.setEnabled(false);
        et_namakeluarga.setEnabled(false);
        et_nomorhpkeluarga.setEnabled(false);
        et_jumlahtanggungan.setEnabled(false);
        et_tipependapatan.setEnabled(false);
        et_pendidikanterakhir.setEnabled(false);
        et_gelar.setEnabled(false);

    }

    private void setData(){
            et_nik.setText(dataLengkap.getNoKtp());
            et_expirednik.setText(AppUtil.parseTanggalGeneral(dataLengkap.getExpId(), "ddMMyyyy", "dd-MM-yyyy"));
//            et_npwp.setText(dataLengkap.getNpwp().replaceAll("[-.]", ""));
            et_npwp.setText(AppUtil.parseNpwp(dataLengkap.getNpwp()));
            et_nama.setText(dataLengkap.getNamaNasabah());
            et_namaalias.setText(dataLengkap.getNamaAlias());
            et_tempatlahir.setText(dataLengkap.getTptLahir());
            et_tanggallahir.setText(AppUtil.parseTanggalGeneral(dataLengkap.getTglLahir(), "ddMMyyyy", "dd-MM-yyyy"));
            et_jeniskelamin.setText(KeyValue.getKeyJenisKelamin(dataLengkap.getJenkel()));
            et_nomorhp.setText(dataLengkap.getNoHp());
            et_email.setText(dataLengkap.getEmail());
            et_agama.setText(KeyValue.getKeyAgama(dataLengkap.getAgama()));
            et_ketagama.setText(dataLengkap.getKetAgama());
            et_namaibukandung.setText(dataLengkap.getNamaIbu());
            et_statusnikah.setText(KeyValue.getKeyStatusNikah(dataLengkap.getStatusNikah()));
            et_nikpasangan.setText(dataLengkap.getNoKtpPasangan());
            et_namapasangan.setText(dataLengkap.getNamaPasangan());
            et_tanggallahirpasangan.setText(AppUtil.parseTanggalGeneral(dataLengkap.getTglLahirPasangan(), "ddMMyyyy", "dd-MM-yyyy"));
            et_namakeluarga.setText(dataLengkap.getKeluarga());
            et_nomorhpkeluarga.setText(dataLengkap.getTelpKeluarga());
            et_jumlahtanggungan.setText(String.valueOf(dataLengkap.getJlhTanggungan()));
            et_tipependapatan.setText(KeyValue.getKeyTipePendapatan(dataLengkap.getTipePendapatan()));
            et_pendidikanterakhir.setText(KeyValue.getKeyPendidikanTerakhir(dataLengkap.getPendTerakhir()));
            et_gelar.setText(dataLengkap.getKetGelar());

            if (dataLengkap.getStatusNikah().equalsIgnoreCase("2")){
                ll_pasangan.setVisibility(View.VISIBLE);
            }
            if (dataLengkap.getAgama().equalsIgnoreCase("ZZZ")){
                tf_ketagama.setVisibility(View.VISIBLE);
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
