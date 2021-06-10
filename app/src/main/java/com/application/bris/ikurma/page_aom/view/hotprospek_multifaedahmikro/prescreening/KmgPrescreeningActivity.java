package com.application.bris.ikurma.page_aom.view.hotprospek_multifaedahmikro.prescreening;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.application.bris.ikurma.R;
import com.application.bris.ikurma.api.model.Error;
import com.application.bris.ikurma.api.model.ParseResponse;
import com.application.bris.ikurma.api.model.ParseResponseError;
import com.application.bris.ikurma.api.model.request.hotprospek.KomponenPrescreening;
import com.application.bris.ikurma.api.model.request.hotprospek.Prescreening;
import com.application.bris.ikurma.api.model.request.hotprospek.PrescreeningSikp;
import com.application.bris.ikurma.api.service.ApiClientAdapter;
import com.application.bris.ikurma.baseapp.RouteApp;
import com.application.bris.ikurma.database.AppPreferences;
import com.application.bris.ikurma.page_aom.dialog.CustomDialog;
import com.application.bris.ikurma.page_aom.listener.ConfirmListener;
import com.application.bris.ikurma.page_aom.listener.DownloadListener;
import com.application.bris.ikurma.page_aom.model.DataPrescreeningKmg;
import com.application.bris.ikurma.page_aom.view.hotprospek_multifaedahmikro.KmgHotprospekActivity;
import com.application.bris.ikurma.page_aom.view.hotprospek_multifaedahmikro.prescreening.remaksslik.KmgRemaksSlikActivity;
import com.application.bris.ikurma.util.AppUtil;
import com.application.bris.ikurma.util.DownloadTask;
import com.application.bris.ikurma.util.Stringinfo;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.grantland.widget.AutofitTextView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KmgPrescreeningActivity extends AppCompatActivity implements View.OnClickListener, ConfirmListener, DownloadListener {

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

    @BindView(R.id.tv_no_tiket_slik)
    AutofitTextView tv_no_tiket_slik;
    @BindView(R.id.tv_no_tiket_slik_pasangan)
    AutofitTextView tv_no_tiket_slik_pasangan;

    @BindView(R.id.ll_tiket_slik)
    LinearLayout ll_tiket_slik;

    @BindView(R.id.ll_tiket_slik_pasangan)
    LinearLayout ll_tiket_slik_pasangan;

    private int idAplikasi;
    private String kodeProduct,approved, idTujuan;
    private ApiClientAdapter apiClientAdapter;

    private DataPrescreeningKmg data;
    private boolean flagCheckDhn = false;
    private boolean flagCheckDukcapil = false;
    private boolean flagCheckSlik = false;
    private boolean flagCheckSikp = false;
    private boolean isKUR = false;
    private boolean isKawin = false;
    private int flagMemoSales;
    private int statusBtnSend =0;
    private int statusCekSlik=0;
    AppPreferences appPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ao_activity_hotprospek_prescreening_kmg);
        ButterKnife.bind(this);
        apiClientAdapter = new ApiClientAdapter(this);
        idAplikasi = getIntent().getIntExtra("idAplikasi", 0);
        kodeProduct = getIntent().getStringExtra("kodeProduct");
        idTujuan = getIntent().getStringExtra("idTujuan");
        approved = getIntent().getStringExtra("approved");
        flagMemoSales=getIntent().getIntExtra("flagMemoSales",99);
        backgroundStatusBar();
        AppUtil.toolbarRegular(this, "Prescreening");
        appPreferences=new AppPreferences(this);
        if (approved.equalsIgnoreCase("yes")){
            btn_send.setVisibility(View.GONE);
        }

        iv_btndhn.setOnClickListener(this);
        iv_btndukcapil.setOnClickListener(this);
        iv_btnslik.setOnClickListener(this);
        iv_btnsikp.setOnClickListener(this);
        btn_send.setOnClickListener(this);
        btn_download.setOnClickListener(this);
        btn_remaks.setOnClickListener(this);
        btn_refresh.setOnClickListener(this);

//        Log.d("pirmen",String.valueOf(flagMemoSales));

        //unused karena perubahan warna tombol ada di method loadprescreening

//        if(flagMemoSales!=99){
//            if(flagMemoSales==0){
//                btn_send.setEnabled(false);
//                btn_send.getBackground().setColorFilter(getResources().getColor(R.color.colorBgBottomsheet), PorterDuff.Mode.SRC_ATOP);
//            }
//            else{
//                btn_send.setEnabled(true);
//            }
//        }

        tv_info.setText(Html.fromHtml(Stringinfo.info_prescreening));


    }

    @Override
    protected void onResume() {
        super.onResume();

        loadPrescreening();
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
        statusBtnSend =0;
        loading.setVisibility(View.VISIBLE);
        Prescreening req = new Prescreening(idAplikasi);
//        Prescreening req = new Prescreening(102155);
//        Toast.makeText(this, "ada pantekan id aplikasi di load prescreening", Toast.LENGTH_SHORT).show();
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().inquiryPrescreeningKmg(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                loading.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()){
                        if(response.body().getStatus().equalsIgnoreCase("00"))
                        {
                            Gson gson = new Gson();
                            String dataPrescreeningString = response.body().getData().get("prescreening").toString();
                            data = gson.fromJson(dataPrescreeningString, DataPrescreeningKmg.class);

                            if(data.getNoPermin()!=null&&!data.getNoPermin().isEmpty()){
                                ll_tiket_slik.setVisibility(View.VISIBLE);
                                tv_no_tiket_slik.setText(data.getNoPermin());
                            }
                            if(data.getNoPerminPasangan()!=null&&!data.getNoPerminPasangan().isEmpty()){
                                ll_tiket_slik_pasangan.setVisibility(View.VISIBLE);
                                tv_no_tiket_slik_pasangan.setText(data.getNoPerminPasangan());
                            }

                            if (data.getsTATUSKAWIN().equalsIgnoreCase("2")){
                                isKawin = true;
                            }

                            if (data.getdHN() != null && (data.getdHN() || data.getdHN() == false)){
                                flagCheckDhn = true;
                                setCustomView(data.getdHN(), iv_completedhn, tv_dhn,data.getDhn_ket(),"DHN");
                            }

                            if (data.getdUKCAPIL() != null && (data.getdUKCAPIL() || data.getdUKCAPIL() == false)){
                                flagCheckDukcapil = true;
                                setCustomView(data.getdUKCAPIL(), iv_completedukcapil, tv_dukcapil,data.getDukcapil_ket(),"DUKCAPIL");
                            }

                            if (data.getsLIK() != null && (data.getsLIK() || data.getsLIK() == false)){
                                btn_download.setVisibility(View.VISIBLE);
                                btn_remaks.setVisibility(View.VISIBLE);
                                flagCheckSlik = true;
                                setCustomView(data.getsLIK(), iv_completeslik, tv_slik,data.getSlik_ket(),"SLIK");
                            }

                            //KALAU SLIK NULL, BERARTI MASI DALAM ANTRIAN
                            else if(data.getsLIK()==null){
                                if(data.getStatusPermintaanSlik()!=null&&data.getStatusPermintaanSlik().equalsIgnoreCase("0")){
                                    tv_slik.setVisibility(View.VISIBLE);
                                    tv_slik.setText("DALAM ANTRIAN");
                                    tv_slik.setTextColor(getResources().getColor(R.color.colorPrimary));
                                    btn_refresh.setVisibility(View.VISIBLE);
                                }
                            }

                            if (data.getsIKP() != null && (data.getsIKP() || data.getsIKP() == false)){
                                flagCheckSikp = true;
                                setCustomView(data.getsIKP(), iv_completesikp, tv_sikp,data.getSikp_ket(),"SIKP");
                            }

                            //kode produk kmg prod khusus takeover , 91,110,111
//                            Log.d("pimenproduk",kodeProduct);
//                            Log.d("pimenproduk",Integer.toString(data.getFlagMemoSales()));
//                            if (kodeProduct.equalsIgnoreCase("110") || kodeProduct.equalsIgnoreCase("91")|| kodeProduct.equalsIgnoreCase("111")){
//                                if(data.getFlagMemoSales()==0){
////                                    btn_send.setEnabled(false);
//                                    btn_send.getBackground().setColorFilter(getResources().getColor(R.color.colorBgBottomsheet), PorterDuff.Mode.SRC_ATOP);
//                                    statusBtnSend=1;
//                                }
//                                else  if(data.getFlagMemoSales()==1){
//                                    btn_send.setEnabled(true);
//                                    btn_send.getBackground().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
//                                }
//                            }

                            if (idTujuan.equalsIgnoreCase("99") || idTujuan.equalsIgnoreCase("112")|| idTujuan.equalsIgnoreCase("113")){
                                if(data.getFlagMemoSales() == 0){
//                                    btn_send.setEnabled(false);
                                    btn_send.getBackground().setColorFilter(getResources().getColor(R.color.colorBgBottomsheet), PorterDuff.Mode.SRC_ATOP);
                                    Log.d("set statusBtnSend", "here");
                                    statusBtnSend = 1;
                                }
                                else if(data.getFlagMemoSales() == 1){
                                    Log.d("default statusBtnSend", "here");
                                    btn_send.setEnabled(true);
                                    btn_send.getBackground().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
                                }
                            }


                            if (data.getrESULT() != null)
                            {
                                ll_info.setVisibility(View.GONE);
                                ll_hasilprescreening.setVisibility(View.VISIBLE);
                                String resultString = data.getrESULT();
                                tv_hasilprescreening.setText(resultString);
                                if (resultString.equalsIgnoreCase("merah")){
                                    tv_hasilprescreening.setBackgroundResource(R.drawable.shapered);
                                }
                                else if (resultString.equalsIgnoreCase("kuning")){
                                    tv_hasilprescreening.setBackgroundResource(R.drawable.shapeyellow);
                                }
                                else{
                                    tv_hasilprescreening.setBackgroundResource(R.drawable.shapegreen);
                                }
                                scrollPageAutomatic();
                            }
                        }
                        else {
                            AppUtil.notiferror(KmgPrescreeningActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(KmgPrescreeningActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                AppUtil.notiferror(KmgPrescreeningActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
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
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().cekDHNKmg(req);
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

                            }
                            else {
                                msgStatus = "TIDAK LOLOS";
                                tv_info_gagal.setText(tv_info_gagal.getText()+"Keterangan Gagal DHN : "+messageDhn+"\n");
                                ll_info_gagal.setVisibility(View.VISIBLE);

                                tv_dhn.setVisibility(View.VISIBLE);
                                tv_dhn.setText(msgStatus);
                                tv_dhn.setTextColor(getResources().getColor(R.color.colorError));
                            }
                            AppUtil.notifsuccess(KmgPrescreeningActivity.this, findViewById(android.R.id.content), msgStatus);
                        }
                        else {
                            AppUtil.notiferror(KmgPrescreeningActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(KmgPrescreeningActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                AppUtil.notiferror(KmgPrescreeningActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
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
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().cekDukcapilKmg(req);
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
                            }
                            else {
                                msgStatus = "TIDAK LOLOS";
                                tv_info_gagal.setText(tv_info_gagal.getText()+"Keterangan Gagal DUKCAPIL : "+messageDukcapil+"\n");
                                ll_info_gagal.setVisibility(View.VISIBLE);

                                tv_dukcapil.setVisibility(View.VISIBLE);
                                tv_dukcapil.setText(msgStatus);
                                tv_dukcapil.setTextColor(getResources().getColor(R.color.colorError));
                            }
                            AppUtil.notifsuccess(KmgPrescreeningActivity.this, findViewById(android.R.id.content), msgStatus);
                        }
                        else {
                            AppUtil.notiferror(KmgPrescreeningActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(KmgPrescreeningActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                AppUtil.notiferror(KmgPrescreeningActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }


    //CEK SLIK
    private void cekSlik() {
        loading.setVisibility(View.VISIBLE);
        tv_loading.setVisibility(View.VISIBLE);
        if(statusCekSlik==1){
            tv_loading.setText(("...hasil SLIK belum didapatkan, mencoba kembali, mohon ditunggu ya"));
        }
        else if (statusCekSlik==2){
            tv_loading.setText(("...hasil SLIK masih belum didapatkan, sepertinya antrian SLIK lagi ramai nih, ditunggu ya"));
        }
        else if (statusCekSlik==3){
            tv_loading.setText(("...hasil SLIK masih belum dapat juga nih, ditunggu sekali lagi ya, kalau masih belum bisa, boleh isi pipeline yang lain dulu, pencarian hasil SLIK akan tetap berjalan di sistem, ketika kembali kesini semoga hasil SLIK-nya sudah didapatkan"));
        }
        else{
            tv_loading.setText(getString(R.string.title_pleasewaitslik));
        }

        KomponenPrescreening req = new KomponenPrescreening();
        req.setIdAplikasi(String.valueOf(idAplikasi));
        req.setKodeCabang(appPreferences.getKodeKantor());
        req.setUid(String.valueOf(appPreferences.getUid()));
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().cekSlikKmg(req);
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
                            int statusPermintaanSlik = response.body().getData().get("statusPermintaanSlik").getAsInt();
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

                            AppUtil.notifinfo(KmgPrescreeningActivity.this, findViewById(android.R.id.content), msgStatus);
                        }
                        else {
                            AppUtil.notiferror(KmgPrescreeningActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(KmgPrescreeningActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {


                if(statusCekSlik<3){
                    //retry check slik
                    statusCekSlik=statusCekSlik+1;
                    cekSlik();

                }
                else{
                    loading.setVisibility(View.GONE);
                    tv_loading.setVisibility(View.GONE);
                    AppUtil.notiferror(KmgPrescreeningActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
                }

            }
        });
    }


    //CEK SIKP
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
                            String messageSikp=response.body().getData().get("sikp").getAsString();
                            String msgStatus = "";

                            if (resultSikp){
                                msgStatus = "LOLOS";
                                tv_sikp.setVisibility(View.VISIBLE);
                                tv_sikp.setText(msgStatus);
                                tv_sikp.setTextColor(getResources().getColor(R.color.colorGreenSuccess));
                            }
                            else {
                                msgStatus = "TIDAK LOLOS";
                                tv_info_gagal.setText(tv_info_gagal.getText()+"Keterangan Gagal SIKP : "+messageSikp+"\n");
                                ll_info_gagal.setVisibility(View.VISIBLE);

                                tv_sikp.setVisibility(View.VISIBLE);
                                tv_sikp.setText(msgStatus);
                                tv_sikp.setTextColor(getResources().getColor(R.color.colorError));
                            }

                            AppUtil.notifsuccess(KmgPrescreeningActivity.this, findViewById(android.R.id.content), msgStatus);
                        }
                        else {
                            AppUtil.notiferror(KmgPrescreeningActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(KmgPrescreeningActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                AppUtil.notiferror(KmgPrescreeningActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }


    //SEND PRESCREENING
    private void sendPrescreening() {
        loading.setVisibility(View.VISIBLE);
        Prescreening req = new Prescreening(idAplikasi);
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().sendPrescreeningKmg(req);
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
                                CustomDialog.DialogError(KmgPrescreeningActivity.this, "Upss Sorry", "Hasil Prescreening Nasabah "+resultString+". Anda tidak diijinkan untuk melanjutkan pengisian aplikasi ini!", KmgPrescreeningActivity.this);
                            }
                            else if (resultString.equalsIgnoreCase("kuning")){
                                tv_hasilprescreening.setBackgroundResource(R.drawable.shapeyellow);
                                AppUtil.notifsuccess(KmgPrescreeningActivity.this, findViewById(android.R.id.content), "Hasil Prescreening Nasabah "+resultString);
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        finish();
                                    }
                                }, 2000);
                            }
                            else{
                                tv_hasilprescreening.setBackgroundResource(R.drawable.shapegreen);
                                AppUtil.notifsuccess(KmgPrescreeningActivity.this, findViewById(android.R.id.content), "Hasil Prescreening Nasabah "+resultString);
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        finish();
                                    }
                                }, 2000);
                            }
                        }
                        else {
                            AppUtil.notiferror(KmgPrescreeningActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(KmgPrescreeningActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                AppUtil.notiferror(KmgPrescreeningActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    public boolean validateChecking(){
        if (!flagCheckDhn){
            AppUtil.notifwarning(KmgPrescreeningActivity.this, findViewById(android.R.id.content), "Silahkan Cek DHN terlebih dahulu");
            return false;
        }
        else if (!flagCheckDukcapil){
            AppUtil.notifwarning(KmgPrescreeningActivity.this, findViewById(android.R.id.content), "Silahkan Cek E-KTP terlebih dahulu");
            return false;
        }
        else if (!flagCheckSlik){
            AppUtil.notifwarning(KmgPrescreeningActivity.this, findViewById(android.R.id.content), "Harap selesaikan proses SLIK terlebih dahulu");
            return false;
        }
        else if (!flagCheckSikp){
            if (isKUR){
                AppUtil.notifwarning(KmgPrescreeningActivity.this, findViewById(android.R.id.content), "Silahkan Cek SIKP terlebih dahulu");
                return false;
            }
            else {
                return true;
            }
        }
        return true;
    }

    public void setCustomView(Boolean bol, ImageView iv, TextView tv,String message, String sumberCek)
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
        Prescreening req = new Prescreening(idAplikasi);
        Call<ParseResponse> call = null;
        if (id == 1){
            call = apiClientAdapter.getApiInterface().downloadSlikKmg(req);
        }
        else{
            call = apiClientAdapter.getApiInterface().downloadSlikPasanganKmg(req);
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
                            String fileName = response.body().getData().get("fileName").getAsString();
                            String dataString = response.body().getData().get("file").getAsString();
                            byte[] data = Base64.decode(dataString, Base64.DEFAULT);
                            new DownloadTask(KmgPrescreeningActivity.this, data, fileName, "hotprospek");
                        }
                        else {
                            AppUtil.notiferror(KmgPrescreeningActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(KmgPrescreeningActivity.this, findViewById(android.R.id.content), error.getMessage());
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
                AppUtil.notiferror(KmgPrescreeningActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
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
            case R.id.btn_refresh:
                recreate();
                break;
            case R.id.btn_send:
                if (validateChecking())
                    if(statusBtnSend==1){
//                        Toast.makeText(this, "Silahkan lakukan remarks untuk pembiayaan takeover", Toast.LENGTH_SHORT).show();
                        AppUtil.notifinfoLong(this,findViewById(android.R.id.content),"Silahkan lakukan remarks untuk pembiayaan takeover");
                    }
                    else{
                        sendPrescreening();
                    }

                break;
            case R.id.btn_download:
                CustomDialog.DialogDownloadSlik(this, isKawin, "Download Hasil SLIK", "Silahkan download hasil SLIK", this);
                break;
            case R.id.btn_remaks:
                Bundle bundle = new Bundle();
                bundle.putInt("idAplikasi", idAplikasi);
                bundle.putString("approved",approved);
                bundle.putInt("statusKawin", AppUtil.parseIntWithDefault(data.getsTATUSKAWIN(), 0));
                RouteApp routeApp = new RouteApp(this);
                routeApp.openActivity(KmgRemaksSlikActivity.class, bundle);
        }
    }

    @Override
    public void success(boolean val) {
        if(val){
            Intent intent = new Intent(this, KmgHotprospekActivity.class);
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
