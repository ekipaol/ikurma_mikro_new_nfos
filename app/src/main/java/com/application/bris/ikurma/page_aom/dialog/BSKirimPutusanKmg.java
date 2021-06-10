package com.application.bris.ikurma.page_aom.dialog;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;

import com.application.bris.ikurma.R;
import com.application.bris.ikurma.api.config.UriApi;
import com.application.bris.ikurma.api.model.Error;
import com.application.bris.ikurma.api.model.ParseResponse;
import com.application.bris.ikurma.api.model.ParseResponseError;
import com.application.bris.ikurma.api.model.request.general.ListDeviasi;
import com.application.bris.ikurma.api.model.request.hotprospek.inquiryKelengkapanDokumen;
import com.application.bris.ikurma.api.model.request.hotprospek.kirimPutusanMikro;
import com.application.bris.ikurma.api.model.request.hotprospek.kirimPutusanMikroDeviasi;
import com.application.bris.ikurma.api.service.ApiClientAdapter;
import com.application.bris.ikurma.database.AppPreferences;
import com.application.bris.ikurma.page_aom.listener.ConfirmListener;
import com.application.bris.ikurma.page_aom.model.DataDeviasi;
import com.application.bris.ikurma.page_aom.model.KelengkapanDokumen;
import com.application.bris.ikurma.page_aom.model.ListAgunanKelengkapanDokumen;
import com.application.bris.ikurma.page_aom.model.hotprospekKmg;
import com.application.bris.ikurma.page_aom.view.hotprospek_multifaedahmikro.KmgHotprospekDetailActivity;
import com.application.bris.ikurma.util.AppUtil;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.makeramen.roundedimageview.RoundedDrawable;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by PID on 5/19/2019.
 */

public class BSKirimPutusanKmg extends BottomSheetDialogFragment implements View.OnClickListener, ConfirmListener{

    @BindView(R.id.progressbar_loading)
    RelativeLayout loading;
    @BindView(R.id.sw_isdeviasi)
    Switch sw_isdeviasi;
    @BindView(R.id.tv_isdeviasi)
    TextView tv_isdeviasi;
    @BindView(R.id.ll_infopemutus)
    LinearLayout ll_infopemutus;
    @BindView(R.id.ll_uh)
    LinearLayout ll_uh;
    @BindView(R.id.cb_uh)
    CheckBox cb_uh;
    @BindView(R.id.tv_uh)
    TextView tv_uh;
    @BindView(R.id.ll_m3)
    LinearLayout ll_m3;
    @BindView(R.id.cb_m3)
    CheckBox cb_m3;
    @BindView(R.id.sp_m3)
    Spinner sp_m3;
    @BindView(R.id.ll_pincapem)
    LinearLayout ll_pincapem;
    @BindView(R.id.cb_pincapem)
    CheckBox cb_pincapem;
    @BindView(R.id.tv_pincapem)
    TextView tv_pincapem;
    @BindView(R.id.ll_pinca)
    LinearLayout ll_pinca;
    @BindView(R.id.cb_pinca)
    CheckBox cb_pinca;
    @BindView(R.id.tv_pinca)
    TextView tv_pinca;
    @BindView(R.id.et_catatandeviasi)
    EditText et_catatandeviasi;
    @BindView(R.id.btn_sendnow)
    Button btn_sendnow;

    @BindView(R.id.ll_pemutus_1)
    LinearLayout ll_pemutus_1;
    @BindView(R.id.cb_pemutus_1)
    CheckBox cb_pemutus_1;
    @BindView(R.id.sp_pemutus_1)
    Spinner sp_pemutus_1;

    @BindView(R.id.ll_pemutus_2)
    LinearLayout ll_pemutus_2;
    @BindView(R.id.cb_pemutus_2)
    CheckBox cb_pemutus_2;
    @BindView(R.id.sp_pemutus_2)
    Spinner sp_pemutus_2;

    @BindView(R.id.ll_pemutus_3)
    LinearLayout ll_pemutus_3;
    @BindView(R.id.cb_pemutus_3)
    CheckBox cb_pemutus_3;
    @BindView(R.id.sp_pemutus_3)
    Spinner sp_pemutus_3;

    @BindView(R.id.ll_pemutus_4)
    LinearLayout ll_pemutus_4;
    @BindView(R.id.cb_pemutus_4)
    CheckBox cb_pemutus_4;
    @BindView(R.id.sp_pemutus_4)
    Spinner sp_pemutus_4;

    @BindView(R.id.ll_pemutus_5)
    LinearLayout ll_pemutus_5;
    @BindView(R.id.cb_pemutus_5)
    CheckBox cb_pemutus_5;
    @BindView(R.id.sp_pemutus_5)
    Spinner sp_pemutus_5;

    @BindView(R.id.header_rincianpembiayaan)
    LinearLayout header_rincianpembiayaan;
    @BindView(R.id.content_rincianpembiayaan)
    LinearLayout content_rincianpembiayaan;
    @BindView(R.id.iv_arrow_bottom)
    ImageView iv_arrow_bottom;
    @BindView(R.id.tv_produk)
    TextView tv_produk;
    @BindView(R.id.tv_plafond)
    TextView tv_plafond;
    @BindView(R.id.tv_tenor)
    TextView tv_tenor;
    @BindView(R.id.tv_tujuanpenggunaan)
    TextView tv_tujuanpenggunaan;
    @BindView(R.id.iv_foto)
    ImageView iv_foto;

    private int val_isDeviasi = 0;
    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;

    private String dataUhString, dataM3String, dataPincapemString, dataPincaString;
    private List<DataDeviasi> dataUh, dataM3, dataPincapem, dataPinca, dataPemutus1,dataPemutus2,dataPemutus3,dataPemutus4,dataPemutus5;

    private boolean isKcp=false;

    private List listM3 = new ArrayList<>();

    private List listPemutus1 = new ArrayList<>();
    private List listPemutus2 = new ArrayList<>();
    private List listPemutus3 = new ArrayList<>();
    private List listPemutus4 = new ArrayList<>();
    private List listPemutus5 = new ArrayList<>();

    private ArrayAdapter adapterM3;
    private int itemPositionM3,itemPositionPemutus1,itemPositionPemutus2,itemPositionPemutus3,itemPositionPemutus4,itemPositionPemutus5;

    private int uidUh, uidM3, uidPincapem, uidPinca,uidPemutus1,uidPemutus2,uidPemutus3,uidPemutus4,uidPemutus5;
    private static Context context;
    private static hotprospekKmg data;
    static SweetAlertDialog dialog;

    public static BSKirimPutusanKmg display(FragmentManager fragmentManager, Context mcontex, hotprospekKmg mdata){
        context = mcontex;
        data = mdata;
        BSKirimPutusanKmg BSCekNasabah = new BSKirimPutusanKmg();
        BSCekNasabah.show(fragmentManager, "Open Kirim Putusan");
        return BSCekNasabah;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        loadKelengkapanDokumen();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.bottomsheet_kirimputusan, container, false);

        try {
            ButterKnife.bind(this, v);
            apiClientAdapter = new ApiClientAdapter(getContext());
            appPreferences = new AppPreferences(getContext());
            loadDeviasi();
            sw_isdeviasi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked){
                        ll_infopemutus.setVisibility(View.VISIBLE);
                        tv_isdeviasi.setTextColor(getResources().getColor(R.color.colorTextGrey));
                        val_isDeviasi = 1;
                        et_catatandeviasi.setHint("Catatan Deviasi");
                    }
                    else {
                        ll_infopemutus.setVisibility(View.GONE);
                        tv_isdeviasi.setTextColor(getResources().getColor(R.color.placholderBg));
                        val_isDeviasi = 0;
                        et_catatandeviasi.setHint("Catatan");
                    }
                }
            });

//            sp_m3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                @Override
//                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                    itemPositionM3 = position;
//                    if (cb_m3.isChecked()){
//                        uidM3 = dataM3.get(itemPositionM3).getUid();
//                    }
//                    else{
//                        uidM3 = 0;
//                    }
//
//                }
//
//                @Override
//                public void onNothingSelected(AdapterView<?> parent) {
//
//                }
//            });

            spinnerOnClick();

            setDataPembiayaan();
            btn_sendnow.setOnClickListener(this);
            cb_uh.setOnClickListener(this);
            cb_m3.setOnClickListener(this);
            cb_pincapem.setOnClickListener(this);
            cb_pinca.setOnClickListener(this);
            cb_pemutus_1.setOnClickListener(this);
            cb_pemutus_2.setOnClickListener(this);
            cb_pemutus_3.setOnClickListener(this);
            cb_pemutus_4.setOnClickListener(this);
            header_rincianpembiayaan.setOnClickListener(this);
            iv_foto.setOnClickListener(this);
        }
        catch (Exception e){
            e.printStackTrace();
            AppUtil.showToastShort(context, e.getMessage());
        }
        return v;
    }

    private void setDataPembiayaan(){
        try {
            tv_produk.setText(data.getNama_produk());
            tv_plafond.setText(AppUtil.parseRupiah(Integer.toString(data.getPlafond_induk())));
            tv_tenor.setText(Integer.toString(data.getJw())+" Bulan");
            tv_tujuanpenggunaan.setText(data.getNama_tujuan());
        }
        catch (Exception e){
            e.printStackTrace();
            AppUtil.showToastShort(context, e.getMessage());
        }
    }

    private void loadDeviasi() {
        loading.setVisibility(View.VISIBLE);
        ListDeviasi req = new ListDeviasi(appPreferences.getUid(), appPreferences.getKodeKanwil(), appPreferences.getKodeCabang(), appPreferences.getKodeKantor());
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().listDeviasiKmg(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                loading.setVisibility(View.GONE);
                try {
                    if(response.isSuccessful()){
                        if (response.body().getStatus().equalsIgnoreCase("00"))
                        {
                            Gson gson = new Gson();
                            Type type = new TypeToken< List<DataDeviasi>>() {}.getType();

                            JsonObject jObj = response.body().getData().get("deviasi").getAsJsonObject();

                            //ambil seluruh keys dari service
                            Set<String> keys = jObj.keySet();
                            List<String> listKeys=new ArrayList<>();

                            for (String temp : keys) {
                                listKeys.add(temp);
                            }

                            //assign masing masing key ke slot pemutus
                            //ubah labe sesuai dengan nama keysnya

                            //BELUM DIPAKE DLU YA

//                            dataUhString = jObj.get(listKeys.get(0)).toString();
//                            cb_uh.setText(listKeys.get(0));
//
//                            dataM3String = jObj.get(listKeys.get(3)).toString();
//                            cb_m3.setText(listKeys.get(3));
//
//                            dataPincapemString = jObj.get(listKeys.get(1)).toString();
//                            cb_pincapem.setText(listKeys.get(1));
//
//                            dataPincaString = jObj.get(listKeys.get(2)).toString();
//                            cb_pinca.setText(listKeys.get(2));
//
//                            String dataPemutus1String= jObj.get(listKeys.get(4)).toString();
//                            cb_pemutus_1.setText(listKeys.get(4));
//
//                            String dataPemutus2String= jObj.get(listKeys.get(5)).toString();
//                            cb_pemutus_2.setText(listKeys.get(5));
//
//                            String dataPemutus3String= jObj.get(listKeys.get(6)).toString();
//                            cb_pemutus_3.setText(listKeys.get(6));
//
//                            String dataPemutus4String= jObj.get(listKeys.get(7)).toString();
//                            cb_pemutus_4.setText(listKeys.get(7));

//                            String dataPemutus5String= jObj.get(listKeys.get(8)).toString();
//                            cb_pemutus_5.setText(listKeys.get(8));





                            //REMOVED
                            dataUhString = jObj.get("UH").toString();
//                            dataPincapemString = jObj.get("PINCAPEM").toString();

                            if(!appPreferences.getUkerSkk().isEmpty()&&appPreferences.getUkerSkk().equalsIgnoreCase("K")){
                                dataPincaString = jObj.get("PINCAPEM").toString();
                                isKcp=true;
                            }
                            else{
                                dataPincaString = jObj.get("PINCA").toString();
                                isKcp=false;
                            }

                            dataM3String = jObj.get("MMM").toString();
                            String dataAMString = jObj.get("AM").toString();
                            String dataAMPMString = jObj.get("AMPM").toString();
                            String dataMBDGHString = jObj.get("MBD_GH").toString();
                            String dataMBDDHString = jObj.get("MBD_DH").toString();

                            //REMOVED
                            dataUh = gson.fromJson(dataUhString, type);
//                            dataPincapem = gson.fromJson(dataPincapemString, type);

                            dataM3 = gson.fromJson(dataM3String, type);
                            dataPinca = gson.fromJson(dataPincaString, type);
                            dataPemutus1 = gson.fromJson(dataAMString, type);
                            dataPemutus2 = gson.fromJson(dataAMPMString, type);
                            dataPemutus3 = gson.fromJson(dataMBDGHString, type);
                            dataPemutus4 = gson.fromJson(dataMBDDHString, type);
//                            dataPemutus5 = gson.fromJson(dataPemutus5String, type);


                            //REMOVED

                            if (dataUh.size() > 0){
                                cb_uh.setEnabled(true);
                                tv_uh.setText(dataUh.get(0).getNama_pegawai());
                            }
                            else {
                                cb_uh.setEnabled(false);
                                uidUh = 0;
                            }

//                            if (dataPincapem.size() > 0){
//                                cb_pincapem.setEnabled(true);
//                                tv_pincapem.setText(dataPincapem.get(0).getNama_pegawai());
//                            }
//                            else {
//                                cb_pincapem.setEnabled(false);
//                                uidPincapem = 0;
//                            }
//
                            if (dataPinca.size() > 0){
                                cb_pinca.setEnabled(true);
                                tv_pinca.setText(dataPinca.get(0).getNama_pegawai());
                            }
                            else {
                                cb_pinca.setEnabled(false);
                                uidPinca = 0;
                            }

                            //SETTING PEMUTUS M3
                            if (dataM3.size() > 0){
                                cb_m3.setEnabled(true);
                                for (int i =0; i<dataM3.size(); i++){
                                    listM3.add(dataM3.get(i).getNama_pegawai());
                                }
                                adapterM3 = new ArrayAdapter<String>(getContext(), R.layout.spinner_style, listM3);
                                adapterM3.setDropDownViewResource(android.R.layout.simple_list_item_1);
                                sp_m3.setAdapter(adapterM3);
                                itemPositionM3 = sp_m3.getSelectedItemPosition();
                            }
                            else {
                                cb_m3.setEnabled(false);
                                uidM3 = 0;
                            }

                            //SETTING PEMUTUS PEMUTUS 1 (AM)
                            if (dataPemutus1.size() > 0){
                                cb_pemutus_1.setEnabled(true);
                                for (int i =0; i<dataPemutus1.size(); i++){
                                    listPemutus1.add(dataPemutus1.get(i).getNama_pegawai());
                                }
                                adapterM3 = new ArrayAdapter<String>(getContext(), R.layout.spinner_style, listPemutus1);
                                adapterM3.setDropDownViewResource(android.R.layout.simple_list_item_1);
                                sp_pemutus_1.setAdapter(adapterM3);
                                itemPositionPemutus1 = sp_pemutus_1.getSelectedItemPosition();
                            }
                            else {
                                cb_pemutus_1.setEnabled(false);
                                uidPemutus1 = 0;
                            }

                            //SETTING PEMUTUS PEMUTUS 2 (AMPM)
                            if (dataPemutus2.size() > 0){
                                cb_pemutus_2.setEnabled(true);
                                for (int i =0; i<dataPemutus2.size(); i++){
                                    listPemutus2.add(dataPemutus2.get(i).getNama_pegawai());
                                }
                                adapterM3 = new ArrayAdapter<String>(getContext(), R.layout.spinner_style, listPemutus2);
                                adapterM3.setDropDownViewResource(android.R.layout.simple_list_item_1);
                                sp_pemutus_2.setAdapter(adapterM3);
                                itemPositionPemutus2 = sp_pemutus_2.getSelectedItemPosition();
                            }
                            else {
                                cb_pemutus_2.setEnabled(false);
                                uidPemutus2 = 0;
                            }

                            //SETTING PEMUTUS PEMUTUS 3 (mbd gh)
                            if (dataPemutus3.size() > 0){
                                cb_pemutus_3.setEnabled(true);
                                for (int i =0; i<dataPemutus3.size(); i++){
                                    listPemutus3.add(dataPemutus3.get(i).getNama_pegawai());
                                }
                                adapterM3 = new ArrayAdapter<String>(getContext(), R.layout.spinner_style, listPemutus3);
                                adapterM3.setDropDownViewResource(android.R.layout.simple_list_item_1);
                                sp_pemutus_3.setAdapter(adapterM3);
                                itemPositionPemutus3 = sp_pemutus_3.getSelectedItemPosition();
                            }
                            else {
                                cb_pemutus_3.setEnabled(false);
                                ll_pemutus_3.setVisibility(View.GONE);
                                uidPemutus3 = 0;
                            }
//
//                            //SETTING PEMUTUS PEMUTUS 4 (mbd dh)
                            if (dataPemutus4.size() > 0){
                                cb_pemutus_4.setEnabled(true);
                                for (int i =0; i<dataPemutus4.size(); i++){
                                    listPemutus4.add(dataPemutus4.get(i).getNama_pegawai());
                                }
                                adapterM3 = new ArrayAdapter<String>(getContext(), R.layout.spinner_style, listPemutus4);
                                adapterM3.setDropDownViewResource(android.R.layout.simple_list_item_1);
                                sp_pemutus_4.setAdapter(adapterM3);
                                itemPositionPemutus4 = sp_pemutus_4.getSelectedItemPosition();
                            }
                            else {
                                cb_pemutus_4.setEnabled(false);
                                ll_pemutus_4.setVisibility(View.GONE);
                                uidPemutus4 = 0;
                            }

                            //SETTING PEMUTUS PEMUTUS 5
//                            if (dataPemutus5.size() > 0){
//                                cb_pemutus_5.setEnabled(true);
//                                for (int i =0; i<dataPemutus5.size(); i++){
//                                    listPemutus5.add(dataPemutus5.get(i).getNama_pegawai());
//                                }
//                                adapterM3 = new ArrayAdapter<String>(getContext(), R.layout.spinner_style, listPemutus5);
//                                adapterM3.setDropDownViewResource(android.R.layout.simple_list_item_1);
//                                sp_pemutus_5.setAdapter(adapterM3);
//                                itemPositionPemutus5 = sp_pemutus_5.getSelectedItemPosition();
//                            }
//                            else {
//                                cb_pemutus_5.setEnabled(false);
//                                uidPemutus5 = 0;
//                            }
                        }
                        else {
                            AppUtil.notiferror(getContext(), getDialog().findViewById(android.R.id.content), response.body().getMessage());
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    dismiss();
                                }
                            }, 2000);
                        }
                    }
                    else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(getContext(), getDialog().findViewById(android.R.id.content), error.getMessage());
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                dismiss();
                            }
                        }, 2000);
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            dismiss();
                        }
                    }, 2000);
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                AppUtil.notiferror(getContext(), getDialog().findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        dismiss();
                    }
                }, 2000);
            }
        });
    }

    private void loadKelengkapanDokumen() {
        inquiryKelengkapanDokumen req = new inquiryKelengkapanDokumen(data.getId_aplikasi());
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().inquiryKelengkapanDokumenKmg(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                try {
                    if (response.isSuccessful()){
                        if(response.body().getStatus().equalsIgnoreCase("00")){
                            Gson gson = new Gson();
                            Type typeAgunan = new TypeToken<List<ListAgunanKelengkapanDokumen>>() {
                            }.getType();

                            String dataString = response.body().getData().get("kelDokumen").toString();

                            if (!dataString.equalsIgnoreCase("{}")){
                                KelengkapanDokumen dataDoc = gson.fromJson(dataString, KelengkapanDokumen.class);
                                String url_photo = UriApi.Baseurl.URL + UriApi.foto.urlPhoto + dataDoc.getiDDOKUMENAPLIKASI();
                                Glide
                                    .with(context)
                                    .asBitmap()
                                    .load(url_photo)
                                    .into(new SimpleTarget<Bitmap>() {
                                        @Override
                                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                            iv_foto.setImageBitmap(resource);
                                        }
                                    });
                            }
                        }
                    }
                    else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.showToastShort(context, error.getMessage());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                AppUtil.showToastShort(context, t.getMessage());
            }
        });
    }

    public void sendPutusanMikro(){
        loading.setVisibility(View.VISIBLE);
        Call<ParseResponse> call = null;
//        kirimPutusanMikro req = null;
        kirimPutusanMikroDeviasi reqDeviasi = null;
        if (val_isDeviasi == 1){
            reqDeviasi = new kirimPutusanMikroDeviasi(appPreferences.getUid(), KmgHotprospekDetailActivity.idAplikasi, uidUh, uidPincapem, uidM3, uidPinca, appPreferences.getNama(), et_catatandeviasi.getText().toString().trim());

            if(isKcp){
                reqDeviasi.setUidPINCAPEM(uidPinca);
                reqDeviasi.setUidPINCA(0);
            }
            else{
                reqDeviasi.setUidPINCA(uidPinca);
                reqDeviasi.setUidPINCAPEM(0);
            }

            reqDeviasi.setUidAM(uidPemutus1);
            reqDeviasi.setUidAMPM(uidPemutus2);
            reqDeviasi.setUidMBDGH(uidPemutus3);
            reqDeviasi.setUidMBDDH(uidPemutus4);
            call = apiClientAdapter.getApiInterface().sendPutusanDeviasiKmg(reqDeviasi);
        }
        else {
        kirimPutusanMikro req = new kirimPutusanMikro(KmgHotprospekDetailActivity.idAplikasi, String.valueOf(appPreferences.getUid()), et_catatandeviasi.getText().toString().trim());
            call = apiClientAdapter.getApiInterface().sendPutusanMikroKmg(req);
        }
//        Call<ParseResponse> call = apiClientAdapter.getApiInterface().sendPutusanMikroKmg(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                loading.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()){
                        if (response.body().getStatus().equalsIgnoreCase("00")){
                            CustomDialog.DialogSuccess(getContext(), "Success!", "ID Aplikasi "+String.valueOf(KmgHotprospekDetailActivity.idAplikasi)+" Sukses dikirim ke Pemutus", BSKirimPutusanKmg.this);
                        }
                        else{
                            AppUtil.notiferror(getContext(), getDialog().findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(getContext(), getDialog().findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                AppUtil.notiferror(getContext(), getDialog().findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_sendnow :
                if (val_isDeviasi == 1){
                    if (validateFormDeviasi()){
                        dialog=new SweetAlertDialog(context,SweetAlertDialog.NORMAL_TYPE);
                    dialog.setTitle("Konfirmasi Prinsip Syariah");
                    dialog.setContentText("Dengan menekan tombol setuju, maka anda sudah memastikan bahwa pembiayaan yang akan diajukan sudah sesuai dengan prinsip-prinsip syariah\n");
                    dialog.setCancelText("Batal");
                    dialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            dialog.dismissWithAnimation();
                        }
                    });
                    dialog.setConfirmText("Setuju");
                    dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                        @Override
                        public void onClick(SweetAlertDialog sweetAlertDialog) {
                            sendPutusanMikro();
                            dialog.dismissWithAnimation();
                        }
                    });
                    dialog.show();
                }

                }
                else{
                    if (validateForm()){
                        dialog=new SweetAlertDialog(context,SweetAlertDialog.NORMAL_TYPE);
                        dialog.setTitle("Konfirmasi Prinsip Syariah");
                        dialog.setContentText("Dengan menekan tombol setuju, maka anda sudah memastikan bahwa pembiayaan yang akan diajukan sudah sesuai dengan prinsip-prinsip syariah\n");
                        dialog.setCancelText("Batal");
                        dialog.setCancelClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                dialog.dismissWithAnimation();
                            }
                        });
                        dialog.setConfirmText("Setuju");
                        dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sweetAlertDialog) {
                                sendPutusanMikro();
                                dialog.dismissWithAnimation();
                            }
                        });
                        dialog.show();
                    }


                }
                break;
            case R.id.cb_uh:
                setCheckedValue(cb_uh, 1);
                break;
            case R.id.cb_m3:
                setCheckedValue(cb_m3, 2);
                break;
            case R.id.cb_pincapem:
                setCheckedValue(cb_pincapem, 3);
                break;
            case R.id.cb_pinca:
                setCheckedValue(cb_pinca, 4);
                break;
            case R.id.cb_pemutus_1:
                setCheckedValue(cb_pemutus_1, 5);
                break;
            case R.id.cb_pemutus_2:
                setCheckedValue(cb_pemutus_2, 6);
                break;
            case R.id.cb_pemutus_3:
                setCheckedValue(cb_pemutus_3, 7);
                break;
            case R.id.cb_pemutus_4:
                setCheckedValue(cb_pemutus_4, 8);
                break;
            case R.id.header_rincianpembiayaan:
                if (content_rincianpembiayaan.getVisibility() == View.GONE){
                    expand(content_rincianpembiayaan);
                    iv_arrow_bottom.setImageResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
                }
                else{
                    collapse(content_rincianpembiayaan);
                    iv_arrow_bottom.setImageResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                }
                break;
            case R.id.iv_foto:
                DialogPreviewPhoto.display(getFragmentManager(), "Preview Foto", ((RoundedDrawable)iv_foto.getDrawable()).getSourceBitmap());
                break;
        }
    }

    public boolean validateForm(){
        if(et_catatandeviasi.getText().toString().trim().isEmpty() || et_catatandeviasi.getText().toString().trim().equalsIgnoreCase("")){
            AppUtil.notifwarning(getContext(), getDialog().findViewById(android.R.id.content), "Catatan harus diisi");
            return false;
        }
        return true;
    }

    public boolean validateFormDeviasi(){
        if (!cb_uh.isChecked() && !cb_m3.isChecked() && !cb_pincapem.isChecked() && !cb_pinca.isChecked()&& !cb_pemutus_1.isChecked()&& !cb_pemutus_2.isChecked()&& !cb_pemutus_3.isChecked()&& !cb_pemutus_4.isChecked()){
            AppUtil.notifwarning(getContext(), getDialog().findViewById(android.R.id.content), "Pilih minimal 1 Pemutus Deviasi, atau ubah jenis putusan tanpa Deviasi");
            return false;
        }
        else if(et_catatandeviasi.getText().toString().trim().isEmpty() || et_catatandeviasi.getText().toString().trim().equalsIgnoreCase("")){
            AppUtil.notifwarning(getContext(), getDialog().findViewById(android.R.id.content), "Catatan Deviasi harus diisi");
            return false;
        }
        return true;
    }

    public void setCheckedValue(CheckBox cb, int id){
        if (id == 1){
            uidUh = (cb.isChecked()) ? dataUh.get(0).getUid() : 0;
        }
        if (id == 2){
            uidM3 = (cb.isChecked()) ? dataM3.get(itemPositionM3).getUid() : 0;
        }
        if (id == 3){
            uidPincapem = (cb.isChecked()) ? dataPincapem.get(0).getUid() : 0;
        }
        if (id == 4){
            uidPinca = (cb.isChecked()) ? dataPinca.get(0).getUid() : 0;
        }
        if (id == 5){
            uidPemutus1 = (cb.isChecked()) ? dataPemutus1.get(itemPositionPemutus1).getUid() : 0;
        }
        if (id == 6){
            uidPemutus2 = (cb.isChecked()) ? dataPemutus2.get(itemPositionPemutus2).getUid() : 0;
        }
        if (id == 7){
            uidPemutus3 = (cb.isChecked()) ? dataPemutus3.get(itemPositionPemutus3).getUid() : 0;
        }
        if (id == 8){
            uidPemutus4 = (cb.isChecked()) ? dataPemutus4.get(itemPositionPemutus4).getUid() : 0;
        }
    }

    private static void expand(LinearLayout mLinearLayout) {
        //set Visible
        mLinearLayout.setVisibility(View.VISIBLE);
        final int widthSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        final int heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        mLinearLayout.measure(widthSpec, heightSpec);
        ValueAnimator mAnimator = slideAnimator(mLinearLayout, 0, mLinearLayout.getMeasuredHeight());
        mAnimator.start();
    }

    private static void collapse(final LinearLayout mLinearLayout) {
        int finalHeight = mLinearLayout.getHeight();
        ValueAnimator mAnimator = slideAnimator(mLinearLayout, finalHeight, 0);
        mAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }
            @Override
            public void onAnimationEnd(Animator animator) {
                //Height=0, but it set visibility to GONE
                mLinearLayout.setVisibility(View.GONE);
            }
            @Override
            public void onAnimationCancel(Animator animation) {
            }
            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
        mAnimator.start();
    }

    private static ValueAnimator slideAnimator(final LinearLayout mLinearLayout, int start, int end) {
        ValueAnimator animator = ValueAnimator.ofInt(start, end);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                //Update Height
                int value = (Integer) valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = mLinearLayout.getLayoutParams();
                layoutParams.height = value;
                mLinearLayout.setLayoutParams(layoutParams);
            }
        });
        return animator;
    }

    @Override
    public void success(boolean val) {
        if (val)
            dismiss();
            ((KmgHotprospekDetailActivity) context).recreate();
    }

    @Override
    public void confirm(boolean val) {

    }

    private void spinnerOnClick(){

        //spinner m3
        sp_m3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                itemPositionM3 = position;
                if (cb_m3.isChecked()){
                    uidM3 = dataM3.get(itemPositionM3).getUid();
                }
                else{
                    uidM3 = 0;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //spinner pemutus 1
        sp_pemutus_1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                itemPositionPemutus1 = position;
                if (cb_pemutus_1.isChecked()){
                    uidPemutus1 = dataPemutus1.get(itemPositionPemutus1).getUid();
                }
                else{
                    uidPemutus1 = 0;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //spinner pemutus 2
        sp_pemutus_2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                itemPositionPemutus2 = position;
                if (cb_pemutus_2.isChecked()){
                    uidPemutus2 = dataPemutus2.get(itemPositionPemutus2).getUid();
                }
                else{
                    uidPemutus2 = 0;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //spinner pemutus 3
        sp_pemutus_3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                itemPositionPemutus3 = position;
                if (cb_pemutus_3.isChecked()){
                    uidPemutus3 = dataPemutus3.get(itemPositionPemutus3).getUid();
                }
                else{
                    uidPemutus3 = 0;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
//
//        //spinner pemutus 4
        sp_pemutus_4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                itemPositionPemutus4 = position;
                if (cb_pemutus_4.isChecked()){
                    uidPemutus4 = dataPemutus4.get(itemPositionPemutus4).getUid();
                }
                else{
                    uidPemutus4 = 0;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
//
//        //spinner pemutus 1
//        sp_pemutus_5.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                itemPositionPemutus5 = position;
//                if (cb_pemutus_5.isChecked()){
//                    uidPemutus5 = dataPemutus5.get(itemPositionPemutus5).getUid();
//                }
//                else{
//                    uidPemutus5 = 0;
//                }
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });

    }
}
