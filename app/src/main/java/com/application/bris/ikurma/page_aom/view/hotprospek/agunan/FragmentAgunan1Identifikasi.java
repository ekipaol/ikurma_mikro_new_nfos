package com.application.bris.ikurma.page_aom.view.hotprospek.agunan;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
import android.annotation.SuppressLint;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.application.bris.ikurma.R;
import com.application.bris.ikurma.database.pojo.AgunanTanahBangunanPojo;
import com.application.bris.ikurma.page_aom.dialog.DialogAddress;
import com.application.bris.ikurma.page_aom.listener.AddressListener;
import com.application.bris.ikurma.page_aom.model.AgunanTanahBangunan;
import com.application.bris.ikurma.page_aom.model.address;
import com.application.bris.ikurma.util.AppUtil;
import com.application.bris.ikurma.util.Permission;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import studio.carbonylgroup.textfieldboxes.ExtendedEditText;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by PID on 6/15/2019.
 */

@SuppressLint("ValidFragment")
public class FragmentAgunan1Identifikasi extends Fragment implements Step, AddressListener, View.OnClickListener {

//VIEW BINDING
    @BindView(R.id.tf_lokasi_tanah)
    TextFieldBoxes tf_lokasi_tanah;
    @BindView(R.id.et_lokasi_tanah)
    ExtendedEditText et_lokasi_tanah;

    @BindView(R.id.tf_kodepos_agunan)
    TextFieldBoxes tf_kodepos_agunan;
    @BindView(R.id.et_kodepos_agunan)
    ExtendedEditText et_kodepos_agunan;

    @BindView(R.id.tf_kelurahan_agunan)
    TextFieldBoxes tf_kelurahan_agunan;
    @BindView(R.id.et_kelurahan_agunan)
    ExtendedEditText et_kelurahan_agunan;

    @BindView(R.id.tf_kecamatan_agunan)
    TextFieldBoxes tf_kecamatan_agunan;
    @BindView(R.id.et_kecamatan_agunan)
    ExtendedEditText et_kecamatan_agunan;

    @BindView(R.id.tf_kota_agunan)
    TextFieldBoxes tf_kota_agunan;
    @BindView(R.id.et_kota_agunan)
    ExtendedEditText et_kota_agunan;

    @BindView(R.id.tf_propinsi_agunan)
    TextFieldBoxes tf_propinsi_agunan;
    @BindView(R.id.et_propinsi_agunan)
    ExtendedEditText et_propinsi_agunan;

    @BindView(R.id.tf_batas_utara)
    TextFieldBoxes tf_batas_utara;
    @BindView(R.id.et_batas_utara)
    ExtendedEditText et_batas_utara;

    @BindView(R.id.tf_batas_selatan)
    TextFieldBoxes tf_batas_selatan;
    @BindView(R.id.et_batas_selatan)
    ExtendedEditText et_batas_selatan;

    @BindView(R.id.tf_batas_barat)
    TextFieldBoxes tf_batas_barat;
    @BindView(R.id.et_batas_barat)
    ExtendedEditText et_batas_barat;

    @BindView(R.id.tf_batas_timur)
    TextFieldBoxes tf_batas_timur;
    @BindView(R.id.et_batas_timur)
    ExtendedEditText et_batas_timur;

    @BindView(R.id.tf_kav_blok)
    TextFieldBoxes tf_kav_blok;
    @BindView(R.id.et_kavblok)
    ExtendedEditText et_kavblok;

    @BindView(R.id.tf_rt_agunan)
    TextFieldBoxes tf_rt_agunan;
    @BindView(R.id.et_rt_agunan)
    ExtendedEditText et_rt_agunan;

    @BindView(R.id.tf_rw_agunan)
    TextFieldBoxes tf_rw_agunan;
    @BindView(R.id.et_rw_agunan)
    ExtendedEditText et_rw_agunan;

    @BindView(R.id.btn_dom)
    Button btn_dom;

    @BindView(R.id.btn_set_loc)
    Button btn_set_loc;

    @BindView(R.id.tv_set_loc)
    TextView tvSetLoc;

    //END OF VIEW BINDING

    private String idAgunan="";
    private AgunanTanahBangunan dataAgunan;

    public static String val_LokasiTanah ="";
    public static String val_KodeposAgunan ="";
    public static String val_KelurahanAgunan ="";
    public static String val_KecamatanAgunan ="";
    public static String val_KotaAgunan ="";
    public static String val_PropinsiAgunan ="";
    public static String val_BatasUtara ="";
    public static String val_BatasSelatan ="";
    public static String val_BatasBarat ="";
    public static String val_BatasTimur ="";
    public static String val_KavBlok ="";
    public static String val_RtAgunan ="";
    public static String val_RwAgunan ="";
    public static String val_Koordinat="Lokasi Belum Diset";

    //Start Empty String
    public static String val_BentukBidangTanah = "";
    public static String val_PermukaanTanah = "";
    // END Empty String

    public static int val_IdAgunan = 0;

    private String btnAddressString = "";
    String latitude = "";
    String longitude = "";

    private Realm realm;
    private Permission permission;

    public FragmentAgunan1Identifikasi() {
    }

    public FragmentAgunan1Identifikasi(String midAgunan, AgunanTanahBangunan magunanTanahBangunan) {
        dataAgunan = magunanTanahBangunan;
        idAgunan = midAgunan;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.agunan_1_identifikasi, container, false);
        ButterKnife.bind(this,view);
        realm = Realm.getDefaultInstance();
        permission = new Permission(getContext());
        btn_dom.setOnClickListener(this);
        btn_set_loc.setOnClickListener(this);

        et_kodepos_agunan.setFocusable(false);
        et_kelurahan_agunan.setFocusable(false);
        et_kecamatan_agunan.setFocusable(false);
        et_kota_agunan.setFocusable(false);
        et_propinsi_agunan.setFocusable(false);
        if(!String.valueOf(idAgunan).equalsIgnoreCase("0")) {
            setData();
        }

        return view;
    }

    private void setData(){
        try {
            val_Koordinat = dataAgunan.getKoordinat();
            et_lokasi_tanah.setText(dataAgunan.getLokasiTanah());
            et_kodepos_agunan.setText(dataAgunan.getKodePos());
            et_kelurahan_agunan.setText(dataAgunan.getKelurahan());
            et_kecamatan_agunan.setText(dataAgunan.getKecamatan());
            et_kota_agunan.setText(dataAgunan.getKota());
            et_propinsi_agunan.setText(dataAgunan.getPropinsi());
            tvSetLoc.setText(val_Koordinat);
            et_batas_utara.setText(dataAgunan.getBatasUtaraTanah());
            et_batas_selatan.setText(dataAgunan.getBatasSelatanTanah());
            et_batas_barat.setText(dataAgunan.getBatasBaratTanah());
            et_batas_timur.setText(dataAgunan.getBatasTimurTanah());
            et_kavblok.setText(dataAgunan.getKavBlok());
            et_rt_agunan.setText(dataAgunan.getrT());
            et_rw_agunan.setText(dataAgunan.getrW());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_dom:
                btnAddressString = "dom";
                openAddressDialog();
                break;
            case R.id.btn_set_loc:
                if(permission.Location())
                {
                    Intent intent = new Intent(getActivity(), MapsActivity.class);
                    startActivityForResult(intent, 1);
                }
                break;
        }
    }

    private void openAddressDialog(){
        DialogAddress.display(getFragmentManager(), this);
    }

    @Override
    public void onAddressSelect(address data) {
        if (btnAddressString.equalsIgnoreCase("dom")){
            et_propinsi_agunan.setText(data.getProvinsi());
            et_kota_agunan.setText(data.getKota());
            et_kecamatan_agunan.setText(data.getKecamatan());
            et_kelurahan_agunan.setText(data.getKelurahan());
            et_kodepos_agunan.setText(data.getKodepos());
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (data != null) {
            if (data.hasExtra("latitude")) {
                latitude = data.getStringExtra("latitude");
            }
            if (data.hasExtra("longitude")) {
                longitude = data.getStringExtra("longitude");
            }

            Toast.makeText(getContext(), "latitude : "+latitude+"\nlongitude : "+longitude, Toast.LENGTH_SHORT).show();
            if (!(latitude.isEmpty() || latitude.equalsIgnoreCase("") || latitude.equalsIgnoreCase("0"))) {
                tvSetLoc.setText(latitude + "," + longitude);
                val_Koordinat = latitude + "," + longitude;
            }
        }
    }

    @Nullable
    @Override
    public VerificationError verifyStep() {
        return  validateForm();
    }

    @Override
    public void onSelected() {
        tvSetLoc.setText(val_Koordinat);
    }

    @Override
    public void onError(@NonNull VerificationError verificationError) {
        AppUtil.notiferror(getContext(), getActivity().findViewById(android.R.id.content), verificationError.getErrorMessage());
    }

    private VerificationError validateForm(){
//        Log.d("KOORDINAT", val_Koordinat);
        if (et_lokasi_tanah.getText().toString().isEmpty() || et_lokasi_tanah.getText().toString().equalsIgnoreCase("")){
            tf_lokasi_tanah.setError("Format "+ tf_lokasi_tanah.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_lokasi_tanah.getLabelText()+" "+getString(R.string.title_validate_field));
        }
        else if (et_kodepos_agunan.getText().toString().isEmpty() || et_kodepos_agunan.getText().toString().equalsIgnoreCase("")){
            tf_kodepos_agunan.setError("Format "+ tf_kodepos_agunan.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_kodepos_agunan.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_kelurahan_agunan.getText().toString().isEmpty() || et_kelurahan_agunan.getText().toString().equalsIgnoreCase("")){
            tf_kelurahan_agunan.setError("Format "+ tf_kelurahan_agunan.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_kelurahan_agunan.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_kecamatan_agunan.getText().toString().isEmpty() || et_kecamatan_agunan.getText().toString().equalsIgnoreCase("")){
            tf_kecamatan_agunan.setError("Format "+ tf_kecamatan_agunan.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_kecamatan_agunan.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_kota_agunan.getText().toString().isEmpty() || et_kota_agunan.getText().toString().equalsIgnoreCase("")){
            tf_kota_agunan.setError("Format "+ tf_kota_agunan.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_kota_agunan.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_propinsi_agunan.getText().toString().isEmpty() || et_propinsi_agunan.getText().toString().equalsIgnoreCase("")){
            tf_propinsi_agunan.setError("Format "+ tf_propinsi_agunan.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_propinsi_agunan.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_batas_utara.getText().toString().isEmpty() || et_batas_utara.getText().toString().equalsIgnoreCase("")){
            tf_batas_utara.setError("Format "+ tf_batas_utara.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_batas_utara.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_batas_selatan.getText().toString().isEmpty() || et_batas_selatan.getText().toString().equalsIgnoreCase("")){
            tf_batas_selatan.setError("Format "+ tf_batas_selatan.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_batas_selatan.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_batas_barat.getText().toString().isEmpty() || et_batas_barat.getText().toString().equalsIgnoreCase("")){
            tf_batas_barat.setError("Format "+ tf_batas_barat.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_batas_barat.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_batas_timur.getText().toString().isEmpty() || et_batas_timur.getText().toString().equalsIgnoreCase("")){
            tf_batas_timur.setError("Format "+ tf_batas_timur.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_batas_timur.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_kavblok.getText().toString().isEmpty() || et_kavblok.getText().toString().equalsIgnoreCase("")){
            tf_kav_blok.setError("Format "+ tf_kav_blok.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_kav_blok.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_rt_agunan.getText().toString().isEmpty() || et_rt_agunan.getText().toString().equalsIgnoreCase("")){
            tf_rt_agunan.setError("Format "+ tf_rt_agunan.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_rt_agunan.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_rw_agunan.getText().toString().isEmpty() || et_rw_agunan.getText().toString().equalsIgnoreCase("")){
            tf_rw_agunan.setError("Format "+ tf_rw_agunan.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_rw_agunan.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (val_Koordinat==null||val_Koordinat.equalsIgnoreCase("Lokasi Belum Diset") || val_Koordinat.equalsIgnoreCase("")) {
            return new VerificationError("Lokasi Harus Diset");
        }

        else {
            setDataOnListerner();
            return null;
        }
    }

    public void setDataOnListerner(){
        try{
            val_LokasiTanah = (et_lokasi_tanah.getText().toString().trim());
            val_KodeposAgunan = (et_kodepos_agunan.getText().toString().trim());
            val_KelurahanAgunan = (et_kelurahan_agunan.getText().toString().trim());
            val_KecamatanAgunan = (et_kecamatan_agunan.getText().toString().trim());
            val_KotaAgunan = (et_kota_agunan.getText().toString().trim());
            val_PropinsiAgunan = (et_propinsi_agunan.getText().toString().trim());
            val_BatasUtara = (et_batas_utara.getText().toString().trim());
            val_BatasSelatan = (et_batas_selatan.getText().toString().trim());
            val_BatasBarat = (et_batas_barat.getText().toString().trim());
            val_BatasTimur = (et_batas_timur.getText().toString().trim());
            val_KavBlok = (et_kavblok.getText().toString().trim());
            val_RtAgunan = (et_rt_agunan.getText().toString().trim());
            val_RwAgunan = (et_rw_agunan.getText().toString().trim());

            final AgunanTanahBangunanPojo d = new AgunanTanahBangunanPojo();
            d.setUuid(AgunanTanahBangunanInputActivity.UUIDR);
            d.setIdAgunan(AppUtil.parseIntWithDefault(AgunanTanahBangunanInputActivity.idAgunan, 0));
            d.setIdApl(AppUtil.parseIntWithDefault(AgunanTanahBangunanInputActivity.idAplikasi, 0));
            d.setCif(AgunanTanahBangunanInputActivity.cif);

            d.setLokasiTanah(val_LokasiTanah);
            d.setKodePos(val_KodeposAgunan);
            d.setKelurahan(val_KelurahanAgunan);
            d.setKecamatan(val_KecamatanAgunan);
            d.setKota(val_KotaAgunan);
            d.setPropinsi(val_PropinsiAgunan);
            d.setBatasUtaraTanah(val_BatasUtara);
            d.setBatasSelatanTanah(val_BatasSelatan);
            d.setBatasBaratTanah(val_BatasBarat);
            d.setBatasTimurTanah(val_BatasTimur);
            d.setKavBlok(val_KavBlok);
            d.setRT(val_RtAgunan);
            d.setRW(val_RwAgunan);
            d.setKoordinat(val_Koordinat);
            d.setBentukTanah(val_BentukBidangTanah);
            d.setPermukaanTanah(val_PermukaanTanah);

            realm.executeTransaction (new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.insertOrUpdate(d);
                }
            });
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}

