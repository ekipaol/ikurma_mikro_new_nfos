package com.application.bris.ikurma.page_aom.view.pipeline;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.application.bris.ikurma.R;
import com.application.bris.ikurma.api.config.UriApi;
import com.application.bris.ikurma.api.model.Error;
import com.application.bris.ikurma.api.model.ParseResponse;
import com.application.bris.ikurma.api.model.ParseResponseError;
import com.application.bris.ikurma.api.model.request.pipeline.inquiryPipeline;
import com.application.bris.ikurma.api.model.request.pipeline.processRejectPipeline;
import com.application.bris.ikurma.api.service.ApiClientAdapter;
import com.application.bris.ikurma.database.AppPreferences;
import com.application.bris.ikurma.page_aom.adapter.tindaklanjut.TindaklanjutAdapater;
import com.application.bris.ikurma.page_aom.dialog.CustomDialog;
import com.application.bris.ikurma.page_aom.dialog.DialogPreviewPhoto;
import com.application.bris.ikurma.page_aom.listener.ConfirmListener;
import com.application.bris.ikurma.page_aom.model.pipeline;
import com.application.bris.ikurma.page_aom.model.tindaklanjut;
import com.application.bris.ikurma.util.AppBarStateChangedListener;
import com.application.bris.ikurma.util.AppUtil;
import com.application.bris.ikurma.util.KeyValue;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PipelineDetailActivity extends AppCompatActivity implements View.OnClickListener, ConfirmListener{

    @BindView(R.id.tb_custom)
    Toolbar toolbar;
    @BindView(R.id.tv_page_title)
    TextView tv_page_title;
    @BindView(R.id.btn_edit)
    ImageView btn_edit;
    @BindView(R.id.btn_back)
    ImageView btn_back;
    @BindView(R.id.header)
    ImageView header;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.appbar)
    AppBarLayout appBarLayout;
    @BindView(R.id.btnfab_edit)
    FloatingActionButton btnfab_edit;
    @BindView(R.id.btn_send)
    Button btn_send;
    @BindView(R.id.btn_reject)
    Button btn_reject;
    @BindView(R.id.progressbar_loading)
    RelativeLayout loading;
    @BindView(R.id.ll_info)
    LinearLayout ll_info;

    //TextView Data
    @BindView(R.id.tv_segmen)
    TextView tv_segmen;
    @BindView(R.id.tv_produk)
    TextView tv_produk;
    @BindView(R.id.ll_program)
    LinearLayout ll_program;
    @BindView(R.id.tv_program)
    TextView tv_program;
    @BindView(R.id.tv_plafond)
    TextView tv_plafond;
    @BindView(R.id.tv_tenor)
    TextView tv_tenor;
    @BindView(R.id.tv_nik)
    TextView tv_nik;
    @BindView(R.id.tv_nama)
    TextView tv_nama;
    @BindView(R.id.tv_tempatlahir)
    TextView tv_tempatlahir;
    @BindView(R.id.tv_tanggallahir)
    TextView tv_tanggallahir;
    @BindView(R.id.tv_nomorhp)
    TextView tv_nomorhp;
    @BindView(R.id.label_jenisusaha)
    TextView label_jenisusaha;
    @BindView(R.id.tv_jenisusaha)
    TextView tv_jenisusaha;
    @BindView(R.id.label_pendapatan)
    TextView label_pendapatan;
    @BindView(R.id.tv_pendapatan)
    TextView tv_pendapatan;
    @BindView(R.id.tv_alamat)
    TextView tv_alamat;
    @BindView(R.id.tv_kecamatan)
    TextView tv_kecamatan;
    @BindView(R.id.tv_kota)
    TextView tv_kota;
    @BindView(R.id.tv_provinsi)
    TextView tv_provinsi;

    @BindView(R.id.rv_history_tindaklanjut)
    RecyclerView rv_history_tindaklanjut;
    @BindView(R.id.ll_emptydata)
    LinearLayout ll_emptydata;

    private List<tindaklanjut> tindaklanjuts;
    public static int idPipeline;
    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    private static String val_title;
    private static String val_urlphoto;
    private static Bitmap bitmapPhoto;
    private static String dataPipeline = "";
    private static String dataTindakLanjut = "";
    private pipeline data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ao_activity_pipeline_detail);
        ButterKnife.bind(this);
        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);
        idPipeline = getIntent().getIntExtra("idPipeline", 0);
        collapsingToolbar.setStatusBarScrimColor(getResources().getColor(R.color.colorBackgroundTransparent));
        backgroundStatusBar();
        checkCollapse();
        btn_back.setOnClickListener(this);
        btn_edit.setOnClickListener(this);
        btnfab_edit.setOnClickListener(this);
        btn_send.setOnClickListener(this);
        btn_reject.setOnClickListener(this);
        header.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        ll_emptydata.setVisibility(View.GONE);
        loadDataPipeline();
    }

    private void loadDataPipeline() {
        ll_info.setVisibility(View.GONE);
        loading.setVisibility(View.VISIBLE);
        inquiryPipeline req = new inquiryPipeline(idPipeline, appPreferences.getFidRole());
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().inquiryPipeline(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                loading.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()){
                        if(response.body().getStatus().equalsIgnoreCase("00")){
                            if (response.body().getData().get("pipeline").isJsonNull()){
                                ll_info.setVisibility(View.GONE);
                            }
                            else {

                                try {
                                    if (response.body().getData().get("listCatatan").getAsJsonArray().size() == 0){
                                        ll_emptydata.setVisibility(View.VISIBLE);
                                    }
                                }
                                catch(NullPointerException e){
                                    ll_emptydata.setVisibility(View.GONE);
                                }

                                dataPipeline = response.body().getData().get("pipeline").toString();
                                dataTindakLanjut = response.body().getData().get("listCatatan").toString();
                                ll_info.setVisibility(View.VISIBLE);
                                //DATA PIPELINE
                                Gson gson = new Gson();
                                data = gson.fromJson(dataPipeline, pipeline.class);
                                val_title = data.getNama();
                                val_urlphoto = UriApi.Baseurl.URL + UriApi.foto.urlPhoto + data.getFid_photo();

                                GlideUrl glideUrl = new GlideUrl(val_urlphoto, new LazyHeaders.Builder()
                                        .addHeader("Authorization", "Bearer "+appPreferences.getToken())
                                        .build());

                                Glide
                                        .with(PipelineDetailActivity.this)
                                        .asBitmap()
                                        .load(glideUrl)
                                        .into(new SimpleTarget<Bitmap>() {
                                            @Override
                                            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                                header.setImageBitmap(resource);
                                                bitmapPhoto = resource;
                                            }
                                        });
                                tv_segmen.setText(data.getNama_segmen());
                                tv_produk.setText(data.getNama_produk());
                                if (data.getTipe_produk().equalsIgnoreCase("konsumer")) {
                                    ll_program.setVisibility(View.VISIBLE);
                                    tv_program.setText(data.getNama_gimmick());
                                    label_jenisusaha.setText("Pekerjaan");
                                    label_pendapatan.setText("Pendapatan");
                                }
                                tv_plafond.setText(AppUtil.parseRupiah(Integer.toString(data.getPlafond())));
                                tv_tenor.setText(Integer.toString(data.getJw())+ " Bulan");
                                tv_nik.setText(data.getNo_ktp());
                                tv_nama.setText(data.getNama());
                                tv_tempatlahir.setText(data.getTempat_lahir());
                                tv_tanggallahir.setText(AppUtil.parseTanggalLahir(data.getTanggal_lahir()));
                                tv_nomorhp.setText(data.getNo_hp());
                                tv_jenisusaha.setText(KeyValue.getKeyUsahaorJob(data.getBidang_usaha()));
                                tv_pendapatan.setText(AppUtil.parseRupiah(Integer.toString(data.getOmzet_per_hari())));
                                tv_alamat.setText(data.getAlamat() + ", RT " + data.getRt() + " RW " + data.getRw() + " " + data.getKelurahan() + " " + data.getKode_pos());
                                tv_kecamatan.setText(data.getKecamatan());
                                tv_kota.setText(data.getKota());
                                tv_provinsi.setText(data.getProvinsi());

                                //DATA TINDAK LANJUT
                                Type type = new TypeToken<List<tindaklanjut>>() {}.getType();
                                tindaklanjuts = gson.fromJson(dataTindakLanjut, type);
                                TindaklanjutAdapater adp = new TindaklanjutAdapater(PipelineDetailActivity.this, tindaklanjuts);
                                rv_history_tindaklanjut.setLayoutManager(new LinearLayoutManager(PipelineDetailActivity.this));
                                rv_history_tindaklanjut.setItemAnimator(new DefaultItemAnimator());
                                rv_history_tindaklanjut.setAdapter(adp);
                            }
                        }
                        else {
                            AppUtil.notiferror(PipelineDetailActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(PipelineDetailActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                AppUtil.notiferror(PipelineDetailActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    private void sendData(final String type){
        loading.setVisibility(View.VISIBLE);
        processRejectPipeline req = new processRejectPipeline(data.getId(),appPreferences.getUid());
        Call<ParseResponse> call = null;
        if (type.equalsIgnoreCase(getString(R.string.title_gotohotprospek))){
            call = apiClientAdapter.getApiInterface().pipelineToHotprospek(req);
        }
        else if(type.equalsIgnoreCase(getString(R.string.title_rejectpipeline))){
            call = apiClientAdapter.getApiInterface().rejectPipeline(req);
        }
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                loading.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()){
                        if(response.body().getStatus().equalsIgnoreCase("00")){
                            String msg = "";
                            if(type.equalsIgnoreCase(getString(R.string.title_gotohotprospek))){
                                msg = getString(R.string.title_successtohotprospek);
                            }
                            else if(type.equalsIgnoreCase(getString(R.string.title_rejectpipeline))){
                                msg = getString(R.string.title_successtorejectpipeline);
                            }
                            CustomDialog.DialogSuccess(PipelineDetailActivity.this, "Success!", msg, PipelineDetailActivity.this);
                        }
                        else {
                            AppUtil.notiferror(PipelineDetailActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(PipelineDetailActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                AppUtil.notiferror(PipelineDetailActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });

    }

    private void backgroundStatusBar(){
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorBackgroundTransparent));
        }
    }

    private void checkCollapse(){
        appBarLayout.addOnOffsetChangedListener(new AppBarStateChangedListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                if (state.name().equalsIgnoreCase("COLLAPSED")){
                    tv_page_title.setVisibility(View.VISIBLE);
                    btn_edit.setVisibility(View.VISIBLE);
                    if(val_title.length() > 16){
                        val_title = val_title.substring(0, 16)+"..";
                    }
                    tv_page_title.setText(val_title);
                }
                else {
                    tv_page_title.setVisibility(View.GONE);
                    btn_edit.setVisibility(View.GONE);
                    tv_page_title.setText("");
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_edit:
                btn_edit.setEnabled(false);
            case R.id.btnfab_edit:
                btnfab_edit.setEnabled(false);
                Intent it = new Intent(this, PipelineEditActivity.class);
                it.putExtra("dataPipeline", dataPipeline);
                it.putExtra("dataTindakLanjut", dataTindakLanjut);
                startActivity(it);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btn_edit.setEnabled(true);
                        btnfab_edit.setEnabled(true);
                    }
                }, 5000);
                break;
            case R.id.header:
                DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Foto", bitmapPhoto);
                break;
            case R.id.btn_send:
                sendData(btn_send.getText().toString().trim());
                break;
            case R.id.btn_reject:
                CustomDialog.DialogConfirmation(this, "Konfirmasi", getString(R.string.title_confirm_rejectpipeline), this);
                break;
        }
    }

    @Override
    public void success(boolean val) {
        if(val)
            onBackPressed();
    }

    @Override
    public void confirm(boolean val) {
        if(val)
            sendData(btn_reject.getText().toString().trim());
    }
}
