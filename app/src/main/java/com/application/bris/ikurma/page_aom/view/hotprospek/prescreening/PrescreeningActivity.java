package com.application.bris.ikurma.page_aom.view.hotprospek.prescreening;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.text.Html;
import android.util.Base64;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.application.bris.ikurma.R;
import com.application.bris.ikurma.api.model.Error;
import com.application.bris.ikurma.api.model.ParseResponse;
import com.application.bris.ikurma.api.model.ParseResponseError;
import com.application.bris.ikurma.api.model.request.hotprospek.KomponenPrescreening;
import com.application.bris.ikurma.api.model.request.hotprospek.Prescreening;
import com.application.bris.ikurma.api.model.request.hotprospek.PrescreeningSikp;
import com.application.bris.ikurma.api.model.request.hotprospek.ReqDownloadSlik;
import com.application.bris.ikurma.api.model.request.hotprospek.inquiryHotprospek;
import com.application.bris.ikurma.api.model.request.hotprospek.inquiryRPC;
import com.application.bris.ikurma.api.service.ApiClientAdapter;
import com.application.bris.ikurma.baseapp.RouteApp;
import com.application.bris.ikurma.database.AppPreferences;
import com.application.bris.ikurma.page_aom.dialog.CustomDialog;
import com.application.bris.ikurma.page_aom.listener.ConfirmListener;
import com.application.bris.ikurma.page_aom.listener.DownloadListener;
import com.application.bris.ikurma.page_aom.model.DataPrescreening;
import com.application.bris.ikurma.page_aom.model.hotprospek;
import com.application.bris.ikurma.page_aom.view.hotprospek.HotprospekActivity;
import com.application.bris.ikurma.page_aom.view.hotprospek.prescreening.remaksslik.RemaksSlikActivity;
import com.application.bris.ikurma.util.AppUtil;
import com.application.bris.ikurma.util.DownloadTask;
import com.application.bris.ikurma.util.Stringinfo;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrescreeningActivity extends AppCompatActivity implements View.OnClickListener, ConfirmListener, DownloadListener{

    @BindView(R.id.ll_content)
    LinearLayout ll_content;
    @BindView(R.id.progressbar_loading)
    public RelativeLayout loading;
    @BindView(R.id.tv_loading)
    TextView tv_loading;
    @BindView(R.id.iv_completedhn)
    ImageView iv_completedhn;
    @BindView(R.id.tv_dhn)
    TextView tv_dhn;
    @BindView(R.id.iv_btndhn)
    ImageView iv_btndhn;
    @BindView(R.id.iv_completedukcapil)
    ImageView iv_completedukcapil;
    @BindView(R.id.tv_dukcapil)
    TextView tv_dukcapil;
    @BindView(R.id.iv_btndukcapil)
    ImageView iv_btndukcapil;
    @BindView(R.id.iv_completeslik)
    ImageView iv_completeslik;
    @BindView(R.id.tv_slik)
    TextView tv_slik;
    @BindView(R.id.iv_btnslik)
    ImageView iv_btnslik;
    @BindView(R.id.ll_sikp)
    LinearLayout ll_sikp;
    @BindView(R.id.iv_completesikp)
    ImageView iv_completesikp;
    @BindView(R.id.tv_sikp)
    TextView tv_sikp;
    @BindView(R.id.iv_btnsikp)
    ImageView iv_btnsikp;
    @BindView(R.id.ll_info)
    LinearLayout ll_info;
    @BindView(R.id.tv_info)
    TextView tv_info;
    @BindView(R.id.ll_info_gagal)
    LinearLayout ll_info_gagal;
    @BindView(R.id.tv_info_gagal)
    TextView tv_info_gagal;
    @BindView(R.id.ll_hasilprescreening)
    LinearLayout ll_hasilprescreening;
    @BindView(R.id.tv_hasilprescreening)
    TextView tv_hasilprescreening;
    @BindView(R.id.btn_send)
    Button btn_send;
    @BindView(R.id.btn_download)
    Button btn_download;
    @BindView(R.id.btn_remaks)
    Button btn_remaks;
    @BindView(R.id.btn_refresh)
    Button btn_refresh;
    @BindView(R.id.sv_prescreening)
    public ScrollView sv_prescreening;

    private int idAplikasi;
    private String kodeProduct;
    private int tujuanPenggunaan;
    private ApiClientAdapter apiClientAdapter;

    private DataPrescreening data;
    private hotprospek dataHotprospek;
    private boolean flagCheckDhn = false;
    private boolean flagCheckDukcapil = false;
    private boolean flagCheckSlik = false;
    private boolean flagCheckSikp = false;
    private boolean isKUR = false;
    private boolean isKawin = false;
    AppPreferences appPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ao_activity_hotprospek_prescreening);
        ButterKnife.bind(this);
        apiClientAdapter = new ApiClientAdapter(this, 240, TimeUnit.SECONDS);
        idAplikasi = getIntent().getIntExtra("idAplikasi", 0);
        kodeProduct = getIntent().getStringExtra("kodeProduct");
        tujuanPenggunaan = getIntent().getIntExtra("tujuanPenggunaan", 0);
        backgroundStatusBar();
        AppUtil.toolbarRegular(this, "Prescreening");
        appPreferences=new AppPreferences(this);

        iv_btndhn.setOnClickListener(this);
        iv_btndukcapil.setOnClickListener(this);
        iv_btnslik.setOnClickListener(this);
        iv_btnsikp.setOnClickListener(this);
        btn_send.setOnClickListener(this);
        btn_download.setOnClickListener(this);
        btn_remaks.setOnClickListener(this);
        btn_refresh.setOnClickListener(this);

        if (kodeProduct.equalsIgnoreCase("127") || kodeProduct.equalsIgnoreCase("128") || kodeProduct.equalsIgnoreCase("840")|| kodeProduct.equalsIgnoreCase("318")|| kodeProduct.equalsIgnoreCase("319")|| kodeProduct.equalsIgnoreCase("320")|| kodeProduct.equalsIgnoreCase("841")){
            ll_sikp.setVisibility(View.VISIBLE);
            isKUR = true;
        }

        tv_info.setText(Html.fromHtml(Stringinfo.info_prescreening));
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadPrescreening();
        loadDataHotprospek();
    }

    private void backgroundStatusBar(){
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    private void scrollPageAutomatic(){
        sv_prescreening.post(new Runnable() {
            @Override
            public void run() {
                sv_prescreening.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });
    }

    private void loadPrescreening() {
        apiClientAdapter=new ApiClientAdapter(this);
        loading.setVisibility(View.VISIBLE);

        //pakai inquiryrpc, karena sama sama request id APlikasi
        Prescreening req = new Prescreening(idAplikasi);
//        Prescreening req = new Prescreening(102155);
//        Toast.makeText(this, "ada pantekan id aplikasi di load prescreening", Toast.LENGTH_SHORT).show();
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().inquiryPrescreening(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                loading.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()) {
                        if (response.body().getStatus().equalsIgnoreCase("00")) {
                            Gson gson = new Gson();
                            String dataPrescreeningString = response.body().getData().get("prescreening").toString();
                            data = gson.fromJson(dataPrescreeningString, DataPrescreening.class);

                            if (data.getsTATUSKAWIN().equalsIgnoreCase("2")) {
                                isKawin = true;
                            }

                            if (!data.getdHN().trim().isEmpty()) {
                                flagCheckDhn = true;
                                setCustomView(Boolean.parseBoolean(data.getdHN()), iv_completedhn, tv_dhn,data.getDhn_ket(),"DHN");
                            }

                            if (!data.getdUKCAPIL().trim().isEmpty() ) {
                                flagCheckDukcapil = true;
                                setCustomView(Boolean.parseBoolean(data.getdUKCAPIL()), iv_completedukcapil, tv_dukcapil,data.getDukcapil_ket(),"DUKCAPIL");
                            }

                            if (!data.getsLIK().trim().isEmpty() ) {
                                btn_download.setVisibility(View.VISIBLE);
                                btn_remaks.setVisibility(View.VISIBLE);
                                flagCheckSlik = true;
                                setCustomView(Boolean.parseBoolean(data.getsLIK()), iv_completeslik, tv_slik,data.getSlik_ket(),"SLIK");
                            }

                            //KALAU SLIK NULL, BERARTI MASI DALAM ANTRIAN
                            else if(data.getsLIK().trim().isEmpty()){
                                if(data.getStatusPermintaanSlik()!=null&&data.getStatusPermintaanSlik().equalsIgnoreCase("0")){
                                    tv_slik.setVisibility(View.VISIBLE);
                                    tv_slik.setText("DALAM ANTRIAN");
                                    tv_slik.setTextColor(getResources().getColor(R.color.colorPrimary));
                                    btn_refresh.setVisibility(View.VISIBLE);
                                }
                            }

                            if (!data.getsIKP().trim().isEmpty()) {
                                flagCheckSikp = true;
                                setCustomView(Boolean.parseBoolean(data.getsIKP()), iv_completesikp, tv_sikp,data.getSikp_ket(),"SIKP");
                            }


                            if (!data.getrESULT().isEmpty()) {
                                ll_info.setVisibility(View.GONE);
                                ll_hasilprescreening.setVisibility(View.VISIBLE);
                                String resultString = data.getrESULT();
                                tv_hasilprescreening.setText(resultString);
                                if (resultString.equalsIgnoreCase("merah")) {
                                    tv_hasilprescreening.setBackgroundResource(R.drawable.shapered);
                                } else if (resultString.equalsIgnoreCase("kuning")) {
                                    tv_hasilprescreening.setBackgroundResource(R.drawable.shapeyellow);
                                } else {
                                    tv_hasilprescreening.setBackgroundResource(R.drawable.shapegreen);
                                }
                                scrollPageAutomatic();
                            }
                        } else {
                            AppUtil.notiferror(PrescreeningActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    } else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(PrescreeningActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }



            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                AppUtil.notiferror(PrescreeningActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }


    //CEK DHN
    private void cekDHN() {
        loading.setVisibility(View.VISIBLE);
        KomponenPrescreening req = new KomponenPrescreening();
        req.setIdAplikasi(String.valueOf(idAplikasi));
        req.setKodeCabang(appPreferences.getKodeKantor());
        req.setUid(String.valueOf(appPreferences.getUid()));
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().cekDHN(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                loading.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()){
                        if(response.body().getStatus().equalsIgnoreCase("00"))
                        {
                            flagCheckDhn = true;
                            iv_completedhn.setImageResource(R.drawable.ic_checklist);
                            boolean resultDHN = response.body().getData().get("dhn").getAsBoolean();
                            String messageDhn=response.body().getData().get("dhnMessage").getAsString();
                            String msgStatus = "";

                            if (resultDHN){
                                msgStatus = "LOLOS";
                                tv_dhn.setVisibility(View.VISIBLE);
                                tv_dhn.setText(msgStatus);
                                tv_dhn.setTextColor(getResources().getColor(R.color.colorGreenSuccess));
                                AppUtil.notifsuccess(PrescreeningActivity.this, findViewById(android.R.id.content), msgStatus);
                            }
                            else {
                                msgStatus = "TIDAK LOLOS";
                                tv_info_gagal.setText(tv_info_gagal.getText()+"Keterangan Gagal DHN : "+messageDhn+"\n");
                                ll_info_gagal.setVisibility(View.VISIBLE);

                                tv_dhn.setVisibility(View.VISIBLE);
                                tv_dhn.setText(msgStatus);
                                tv_dhn.setTextColor(getResources().getColor(R.color.colorError));
                                AppUtil.notiferror(PrescreeningActivity.this, findViewById(android.R.id.content), msgStatus);
                            }

                        }
                        else {
                            AppUtil.notiferror(PrescreeningActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(PrescreeningActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                AppUtil.notiferror(PrescreeningActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    //CEK DUKCAPIL
    private void cekDukcapil() {
        loading.setVisibility(View.VISIBLE);
        KomponenPrescreening req = new KomponenPrescreening();
        req.setIdAplikasi(String.valueOf(idAplikasi));
        req.setKodeCabang(appPreferences.getKodeKantor());
        req.setUid(String.valueOf(appPreferences.getUid()));
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().cekDukcapil(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                loading.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()){
                        if(response.body().getStatus().equalsIgnoreCase("00"))
                        {
                            flagCheckDukcapil = true;
                            iv_completedukcapil.setImageResource(R.drawable.ic_checklist);
                            boolean resultDukcapil = response.body().getData().get("dukcapil").getAsBoolean();
                            String messageDukcapil=response.body().getData().get("dukcapilMessage").getAsString();
                            String msgStatus = "";

                            if (resultDukcapil){
                                msgStatus = "LOLOS";
                                tv_dukcapil.setVisibility(View.VISIBLE);
                                tv_dukcapil.setText(msgStatus);
                                tv_dukcapil.setTextColor(getResources().getColor(R.color.colorGreenSuccess));
                                AppUtil.notifsuccess(PrescreeningActivity.this, findViewById(android.R.id.content), msgStatus);
                            }
                            else {
                                msgStatus = "TIDAK LOLOS";

                                tv_info_gagal.setText(tv_info_gagal.getText()+"Keterangan Gagal DUKCAPIL : "+messageDukcapil+"\n");
                                ll_info_gagal.setVisibility(View.VISIBLE);

                                tv_dukcapil.setVisibility(View.VISIBLE);
                                tv_dukcapil.setText(msgStatus);
                                tv_dukcapil.setTextColor(getResources().getColor(R.color.colorError));
                                AppUtil.notiferror(PrescreeningActivity.this, findViewById(android.R.id.content), msgStatus);
                            }
                        }
                        else {
                            AppUtil.notiferror(PrescreeningActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(PrescreeningActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                AppUtil.notiferror(PrescreeningActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }


    //CEK SLIK
    private void cekSlik() {
        loading.setVisibility(View.VISIBLE);
        tv_loading.setVisibility(View.VISIBLE);
        tv_loading.setText(getString(R.string.title_pleasewaitslik));

        KomponenPrescreening req = new KomponenPrescreening();
        req.setIdAplikasi(String.valueOf(idAplikasi));
        req.setKodeCabang(appPreferences.getKodeKantor());
        req.setUid(String.valueOf(appPreferences.getUid()));

        Call<ParseResponse> call = apiClientAdapter.getApiInterface().cekSlik(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                loading.setVisibility(View.GONE);
                tv_loading.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()){
                        if(response.body().getStatus().equalsIgnoreCase("00"))
                        {

                            //flag dibikin false, karna hasil slik harus masuk antrian dlu
                            flagCheckSlik = false;

                            //tidak dipakai lagi, karena sekarang sistem antrian
//                            iv_completeslik.setImageResource(R.drawable.ic_checklist);
//                            btn_download.setVisibility(View.VISIBLE);
//                            btn_remaks.setVisibility(View.VISIBLE);
//                            boolean resultSlik = response.body().getData().get("slik").getAsBoolean();

                            String messageSlik = response.body().getData().get("slikMessage").getAsString();
                            int statusPermintaanSlik = response.body().getData().get("StatusPermintaanSlik").getAsInt();
                            String msgStatus = "";

                            if (statusPermintaanSlik==0){
                                msgStatus = "SEDANG DIPROSES";
                                tv_slik.setVisibility(View.VISIBLE);
                                tv_slik.setText(msgStatus);
                                tv_slik.setTextColor(getResources().getColor(R.color.colorPrimary));

                                iv_completeslik.setImageResource(R.drawable.ic_checklistgrey);
                                btn_download.setVisibility(View.GONE);
                                btn_remaks.setVisibility(View.GONE);
                                btn_refresh.setVisibility(View.VISIBLE);
                            }
                            else {
                                msgStatus = "SELESAI DIPROSES";
//                                msgStatus = messageSlik;
//                                tv_info_gagal.setText(tv_info_gagal.getText()+"Keterangan Gagal SLIK : "+messageSlik+"\n");
//                                ll_info_gagal.setVisibility(View.VISIBLE);

                                tv_slik.setVisibility(View.VISIBLE);
                                tv_slik.setText(msgStatus);
                                tv_slik.setTextColor(getResources().getColor(R.color.colorError));
                            }

                            AppUtil.notifinfo(PrescreeningActivity.this, findViewById(android.R.id.content), msgStatus);
                        }
                        else {
                            AppUtil.notiferror(PrescreeningActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(PrescreeningActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                tv_loading.setVisibility(View.GONE);
                AppUtil.notiferror(PrescreeningActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }


    //CEK SLIK
    private void cekSikp() {
        loading.setVisibility(View.VISIBLE);
        PrescreeningSikp req = new PrescreeningSikp();
        req.setIdAplikasi(idAplikasi);
        req.setUid(appPreferences.getUid());
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().cekSikp(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                loading.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()){
                        if(response.body().getStatus().equalsIgnoreCase("00"))
                        {
                            flagCheckSikp = true;
                            iv_completesikp.setImageResource(R.drawable.ic_checklist);
                            boolean resultSikp = response.body().getData().get("sikp").getAsBoolean();
                            String messageSikp=response.body().getData().get("sikpMessage").getAsString();
                            String msgStatus = "";

                            if (resultSikp){
                                msgStatus = "LOLOS";
                                tv_sikp.setVisibility(View.VISIBLE);
                                tv_sikp.setText(msgStatus);
                                tv_sikp.setTextColor(getResources().getColor(R.color.colorGreenSuccess));
                                AppUtil.notifsuccess(PrescreeningActivity.this, findViewById(android.R.id.content), msgStatus);
                            }
                            else {
                                msgStatus = "TIDAK LOLOS";

                                tv_info_gagal.setText(tv_info_gagal.getText()+"Keterangan Gagal SIKP : "+messageSikp+"\n");
                                ll_info_gagal.setVisibility(View.VISIBLE);

                                tv_sikp.setVisibility(View.VISIBLE);
                                tv_sikp.setText(msgStatus);
                                tv_sikp.setTextColor(getResources().getColor(R.color.colorError));
                                AppUtil.notiferror(PrescreeningActivity.this, findViewById(android.R.id.content), msgStatus);
                            }

//                            AppUtil.notifsuccess(PrescreeningActivity.this, findViewById(android.R.id.content), msgStatus);
                            isEnableButtonPresceening(0);
                        }
                        else {
                            AppUtil.notiferror(PrescreeningActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(PrescreeningActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                AppUtil.notiferror(PrescreeningActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }


    //SEND PRESCREENING
    private void sendPrescreening() {
        loading.setVisibility(View.VISIBLE);
        Prescreening req = new Prescreening(idAplikasi);
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().sendPrescreening(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                loading.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()){
                        if(response.body().getStatus().equalsIgnoreCase("00"))
                        {
                            scrollPageAutomatic();
                            ll_info.setVisibility(View.GONE);
                            ll_hasilprescreening.setVisibility(View.VISIBLE);
                            String resultString = response.body().getData().get("result").getAsString();
                            tv_hasilprescreening.setText(resultString);
                            if (resultString.equalsIgnoreCase("merah")){
                                tv_hasilprescreening.setBackgroundResource(R.drawable.shapered);
                                CustomDialog.DialogError(PrescreeningActivity.this, "Upss Sorry", "Hasil Prescreening Nasabah "+resultString+". Anda tidak diijinkan untuk melanjutkan pengisian aplikasi ini!", PrescreeningActivity.this);
                            }
                            else if (resultString.equalsIgnoreCase("kuning")){
                                tv_hasilprescreening.setBackgroundResource(R.drawable.shapeyellow);
                                AppUtil.notifsuccess(PrescreeningActivity.this, findViewById(android.R.id.content), "Hasil Prescreening Nasabah "+resultString);
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        finish();
                                    }
                                }, 2000);
                            }
                            else{
                                tv_hasilprescreening.setBackgroundResource(R.drawable.shapegreen);
                                AppUtil.notifsuccess(PrescreeningActivity.this, findViewById(android.R.id.content), "Hasil Prescreening Nasabah "+resultString);
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        finish();
                                    }
                                }, 2000);
                            }
                        }
                        else {
                            AppUtil.notiferror(PrescreeningActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(PrescreeningActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                AppUtil.notiferror(PrescreeningActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    public boolean validateChecking(){
        if (!flagCheckDhn){
            AppUtil.notifwarning(PrescreeningActivity.this, findViewById(android.R.id.content), "Silahkan Cek DHN terlebih dahulu");
            return false;
        }
        else if (!flagCheckDukcapil){
            AppUtil.notifwarning(PrescreeningActivity.this, findViewById(android.R.id.content), "Silahkan Cek E-KTP terlebih dahulu");
            return false;
        }
        else if (!flagCheckSlik){
            AppUtil.notifwarning(PrescreeningActivity.this, findViewById(android.R.id.content), "Harap selesaikan proses SLIK terlebih dahulu");
            return false;
        }
        else if (!flagCheckSikp){
            if (isKUR){
                AppUtil.notifwarning(PrescreeningActivity.this, findViewById(android.R.id.content), "Silahkan Cek SIKP terlebih dahulu");
                return false;
            }
            else {
                return true;
            }
        }
        return true;
    }

    public void setCustomView(Boolean bol, ImageView iv, TextView tv,String message,String sumberCek)
    {
        iv.setImageResource(R.drawable.ic_checklist);
        tv.setVisibility(View.VISIBLE);
        if (bol){
            tv.setText("LOLOS");
            tv.setTextColor(getResources().getColor(R.color.colorGreenSuccess));
        }
        else{
            tv.setText("TIDAK LOLOS");
            tv.setTextColor(getResources().getColor(R.color.colorError));

            tv_info_gagal.setText(tv_info_gagal.getText()+"Keterangan Gagal "+sumberCek+" : "+message+"\n");
            ll_info_gagal.setVisibility(View.VISIBLE);
        }
    }

    private void downloadSlik(int id){
        loading.setVisibility(View.VISIBLE);
        tv_loading.setVisibility(View.VISIBLE);
        tv_loading.setText("Downloading...");
        ReqDownloadSlik req = new ReqDownloadSlik();
        req.setIdAplikasi(String.valueOf(idAplikasi));
        Call<ParseResponse> call = null;
        if (id == 1){
            call = apiClientAdapter.getApiInterface().downloadSlik(req);
        }
        else{
            call = apiClientAdapter.getApiInterface().downloadSlikPasangan(req);
        }
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                loading.setVisibility(View.GONE);
                tv_loading.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()){
                        if(response.body().getStatus().equalsIgnoreCase("00"))
                        {
                            String fileName = "Hasil SLIK ID APLIKASI : "+String.valueOf(idAplikasi)+"-"+new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime())+".pdf";

                            String dataString="";
                            try {
                                 dataString = response.body().getData().get("SLIKPDFDebitur").getAsString();
                            }
                            catch(Exception e){
                                 dataString = response.body().getData().get("mup").getAsString();
                                AppUtil.logSecure("dataStringnull",e.getMessage());
                            }

                            byte[] data = Base64.decode(dataString, Base64.DEFAULT);
                            new DownloadTask(PrescreeningActivity.this, data, fileName, "hotprospek");
                        }
                        else {
                            AppUtil.notiferror(PrescreeningActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(PrescreeningActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                tv_loading.setVisibility(View.GONE);
                AppUtil.notiferror(PrescreeningActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    public void isEnableButtonPresceening(int flagMemosales)
    {
        btn_send.setEnabled(false);
        switch (tujuanPenggunaan){
            case 40 :
                btn_send.setEnabled(true);
                btn_send.getBackground().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
                break;
            case 41 :
                btn_send.setEnabled(true);
                btn_send.getBackground().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
                break;
            case 45 :
                btn_send.setEnabled(true);
                btn_send.getBackground().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
                break;
            case 46 :
                btn_send.setEnabled(true);
                btn_send.getBackground().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
                break;
            case 47 :
                btn_send.setEnabled(true);
                btn_send.getBackground().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
                break;
            case 48 :
                btn_send.setEnabled(true);
                btn_send.getBackground().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
                break;
            case 49 :
                btn_send.setEnabled(true);
                btn_send.getBackground().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
                break;
            case 50 :
                if (flagMemosales == 1){
                    btn_send.setEnabled(true);
                    btn_send.getBackground().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
                }
                break;
            case 51 :
                if (flagMemosales == 1)
                {
                    btn_send.setEnabled(true);
                    btn_send.getBackground().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
                }
                break;
            case 52 :
                if (flagMemosales == 1)
                {
                    btn_send.setEnabled(true);
                    btn_send.getBackground().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
                }
                break;
            case 53 :
                if (flagMemosales == 1)
                {
                    btn_send.setEnabled(true);
                    btn_send.getBackground().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
                }
                break;
            case 54 :
                if (flagMemosales == 1)
                {
                    btn_send.setEnabled(true);
                    btn_send.getBackground().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
                }
                break;
            case 55 :
                if (flagMemosales == 1)
                {
                    btn_send.setEnabled(true);
                    btn_send.getBackground().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
                }
                break;
            case 56 :
                if (flagMemosales == 1)
                {
                    btn_send.setEnabled(true);
                    btn_send.getBackground().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
                }
                break;
            case 57 :
                if (flagMemosales == 1)
                {
                    btn_send.setEnabled(true);
                    btn_send.getBackground().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
                }
                break;
        }
    }

    private void loadDataHotprospek() {
        apiClientAdapter=new ApiClientAdapter(this);
        inquiryHotprospek req = new inquiryHotprospek(idAplikasi);
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().inquiryHotprospek(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                try {
                    if (response.isSuccessful()){
                        if(response.body().getStatus().equalsIgnoreCase("00"))
                        {
                            Gson gson = new Gson();
                            String dataString = response.body().getData().get("aplikasi").toString();
                            dataHotprospek = gson.fromJson(dataString, hotprospek.class);
                            isEnableButtonPresceening(dataHotprospek.getFlag_memo_sales());
                        }
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });

        //return the client adapter to default state
        apiClientAdapter=new ApiClientAdapter(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_btndhn:
                cekDHN();
                break;
            case R.id.iv_btndukcapil:
                cekDukcapil();
                break;
            case R.id.iv_btnslik:
                cekSlik();
                break;
            case R.id.iv_btnsikp:
                cekSikp();
                break;
            case R.id.btn_send:
                if (validateChecking())
                    sendPrescreening();
                break;
            case R.id.btn_refresh:
              recreate();
                break;
            case R.id.btn_download:
                CustomDialog.DialogDownloadSlik(this, isKawin, "Download Hasil SLIK", "Silahkan download hasil SLIK", this);
                break;
            case R.id.btn_remaks:
                Bundle bundle = new Bundle();
                bundle.putInt("idAplikasi", idAplikasi);
                bundle.putInt("statusKawin", AppUtil.parseIntWithDefault(data.getsTATUSKAWIN(), 0));
                RouteApp routeApp = new RouteApp(this);
                routeApp.openActivity(RemaksSlikActivity.class, bundle);
        }
    }

    @Override
    public void success(boolean val) {
        if(val){
            Intent intent = new Intent(this, HotprospekActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        }

    }

    @Override
    public void confirm(boolean val) {

    }

    @Override
    public void downloadSLik(int id) {
        downloadSlik(id);
    }
}
