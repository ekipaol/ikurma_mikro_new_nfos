package com.application.bris.ikurma.page_aom.view.approved;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.application.bris.ikurma.R;
import com.application.bris.ikurma.adapter.menu.SubmenuApprovedAdapter;
import com.application.bris.ikurma.api.config.UriApi;
import com.application.bris.ikurma.api.model.Error;
import com.application.bris.ikurma.api.model.ParseResponse;
import com.application.bris.ikurma.api.model.ParseResponseError;
import com.application.bris.ikurma.api.model.request.hotprospek.inquiryHotprospek;
import com.application.bris.ikurma.api.service.ApiClientAdapter;
import com.application.bris.ikurma.config.menu.Menu;
import com.application.bris.ikurma.database.AppPreferences;
import com.application.bris.ikurma.listener.menu.MenuClickListener;
import com.application.bris.ikurma.model.menu.ListViewSubmenuHotprospek;
import com.application.bris.ikurma.page_aom.dialog.DialogPreviewPhoto;
import com.application.bris.ikurma.page_aom.model.hotprospek;
import com.application.bris.ikurma.page_aom.view.approved.agunan_apr.AgunanTerikatActivity_Apr;
import com.application.bris.ikurma.page_aom.view.approved.datalengkap_apr.DataLengkapActivity_Apr;
import com.application.bris.ikurma.page_aom.view.approved.kelengkapandokumen_apr.KelengkapanDokumenActivity_Apr;
import com.application.bris.ikurma.page_aom.view.approved.lkn_apr.LknActivity_Apr;
import com.application.bris.ikurma.page_aom.view.approved.prescreening_apr.PrescreeningActivity_Apr;
import com.application.bris.ikurma.page_aom.view.approved.rpc_apr.RpcActivity_Apr;
import com.application.bris.ikurma.page_aom.view.approved.scoring_apr.ScoringActivity_Apr;
import com.application.bris.ikurma.page_aom.view.approved.sektorekonomi_apr.SektorEkonomiActivity_Apr;
import com.application.bris.ikurma.page_aom.view.hotprospek.history.HistoryActivity;
import com.application.bris.ikurma.util.AppUtil;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApprovedDetailActivity extends AppCompatActivity implements MenuClickListener{

    @BindView(R.id.iv_photo)
    ImageView iv_photo;
    @BindView(R.id.tv_nama)
    TextView tv_nama;
    @BindView(R.id.tv_tenor)
    TextView tv_tenor;
    @BindView(R.id.tv_produk)
    TextView tv_produk;
    @BindView(R.id.tv_plafond)
    TextView tv_plafond;
    @BindView(R.id.tv_status)
    TextView tv_status;
    @BindView(R.id.tv_idaplikasi)
    TextView tv_idaplikasi;
    @BindView(R.id.tv_cifsyiar)
    TextView tv_cifsyiar;
    @BindView(R.id.tv_tujuanpenggunaan)
    TextView tv_tujuanpenggunaan;
    @BindView(R.id.sm_placeholder)
    ShimmerFrameLayout sm_placeholder;
    @BindView(R.id.ll_content)
    LinearLayout ll_content;
    @BindView(R.id.rv_submenu_approved)
    RecyclerView rv_submenu_approved;

    @BindView(R.id.btn_back)
    ImageView btn_back;
    @BindView(R.id.tv_page_title)
    TextView tv_page_title;
    @BindView(R.id.tv_noakad)
    TextView tv_noakad;
    @BindView(R.id.ll_qardh)
    LinearLayout ll_qardh;
    @BindView(R.id.tv_noakad_qardh)
    TextView tv_noakad_qardh;
    @BindView(R.id.tv_jatuhtempo_qardh)
    TextView tv_jatuhtempo_qardh;

    @BindView(R.id.progressbar_loading)
    RelativeLayout loading;

    public int idAplikasi, cif;
    private hotprospek data;
    private hotprospek dataHp;
    private String dataString;
    private Bitmap bitmapPhoto;
    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    private List<ListViewSubmenuHotprospek> dataMenu;
    private SubmenuApprovedAdapter submenuHotprospekAdapter;
    private GridLayoutManager layoutMenu;
    private int coloumMenu = 3;

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ao_activity_approved_detail);
        ButterKnife.bind(this);
        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);
        idAplikasi = getIntent().getIntExtra("idAplikasi", 0);
        cif = getIntent().getIntExtra("cif", 0);
        backgroundStatusBar();
        AppUtil.toolbarRegular(this, "Detail Approved");
    }

    @Override
    public void onResume() {
        super.onResume();
        loadDataHotprospek();
        sm_placeholder.startShimmer();
    }

    @Override
    protected void onPause() {
        super.onPause();
        sm_placeholder.stopShimmer();
    }

    private void loadDataHotprospek() {
        inquiryHotprospek req = new inquiryHotprospek(idAplikasi);
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().inquiryHotprospek(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                sm_placeholder.stopShimmer();
                sm_placeholder.setVisibility(View.GONE);
                ll_content.setVisibility(View.VISIBLE);
                try {
                    if (response.isSuccessful()){
                        if(response.body().getStatus().equalsIgnoreCase("00"))
                        {
                            Gson gson = new Gson();
                            dataString = response.body().getData().get("aplikasi").toString();
                            data = gson.fromJson(dataString, hotprospek.class);
                            String url_photo = UriApi.Baseurl.URL + UriApi.foto.urlPhoto + data.getFid_photo();

                            GlideUrl glideUrl = new GlideUrl(url_photo, new LazyHeaders.Builder()
                                    .addHeader("Authorization", "Bearer "+appPreferences.getToken())
                                    .build());

                            Glide
                                    .with(ApprovedDetailActivity.this)
                                    .asBitmap()
                                    .load(glideUrl)
                                    .into(new SimpleTarget<Bitmap>() {
                                        @Override
                                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                            iv_photo.setImageBitmap(resource);
                                            bitmapPhoto = resource;
                                        }
                                    });

                            iv_photo.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Foto", bitmapPhoto);
                                }
                            });

                            tv_nama.setText(data.getNama_debitur_1());
                            tv_tenor.setText(Integer.toString(data.getJw())+" Bulan");
                            tv_produk.setText(data.getNama_produk());
                            tv_plafond.setText(AppUtil.parseRupiah(Integer.toString(data.getPlafond_induk())));
                            tv_status.setText(data.getStatus_aplikasi());
                            tv_idaplikasi.setText(Integer.toString(data.getId_aplikasi()));
                            tv_cifsyiar.setText(Integer.toString(data.getFid_cif_las()));
                            tv_tujuanpenggunaan.setText(data.getNama_tujuan());
                            tv_noakad.setText(data.getnOAKAD());
                            layoutQard(data.getId_tujuan());
                            initializeMenu();

                            //set loan type ke dalam preference
                            appPreferences.setLoanType(data.getKode_produk());
                        }
                        else {
                            AppUtil.notiferror(ApprovedDetailActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    finish();
                                }
                            }, 2000);
                        }
                    }
                    else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(ApprovedDetailActivity.this, findViewById(android.R.id.content), error.getMessage());
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                finish();
                            }
                        }, 2000);
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            finish();
                        }
                    }, 2000);
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                AppUtil.notiferror(ApprovedDetailActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                }, 2000);
            }
        });
    }

    public void initializeMenu(){
        rv_submenu_approved.setHasFixedSize(true);
        dataHp = data;
        dataMenu = getListMenu();
        submenuHotprospekAdapter = new SubmenuApprovedAdapter(this, dataMenu, dataHp,this);
        layoutMenu = new GridLayoutManager(this, coloumMenu);
        rv_submenu_approved.setLayoutManager(layoutMenu);
        rv_submenu_approved.setAdapter(submenuHotprospekAdapter);
    }

    private List<ListViewSubmenuHotprospek> getListMenu() {
        List<ListViewSubmenuHotprospek> menu = new ArrayList<>();
        Menu.SubmenuHotprospek(this, menu);
        return menu;
    }

    private void backgroundStatusBar(){
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    public void layoutQard(int tujuanPenggunaan)
    {
        ll_qardh.setVisibility(View.GONE);
        switch (tujuanPenggunaan){
            case 40 :
                break;
            case 41 :
                break;
            case 45 :
                break;
            case 46 :
                break;
            case 47 :
                break;
            case 48 :
                break;
            case 49 :
                break;
            case 50 :
                ll_qardh.setVisibility(View.VISIBLE);
                tv_noakad_qardh.setText(data.getNo_akad_qard());
                tv_jatuhtempo_qardh.setText(AppUtil.parseTanggalGeneral(data.getJt_qardh(), "ddMMyyyy", "dd-MM-yyyy"));
                break;
            case 51 :
                ll_qardh.setVisibility(View.VISIBLE);
                tv_noakad_qardh.setText(data.getNo_akad_qard());
                tv_jatuhtempo_qardh.setText(AppUtil.parseTanggalGeneral(data.getJt_qardh(), "ddMMyyyy", "dd-MM-yyyy"));
                break;
            case 52 :
                ll_qardh.setVisibility(View.VISIBLE);
                tv_noakad_qardh.setText(data.getNo_akad_qard());
                tv_jatuhtempo_qardh.setText(AppUtil.parseTanggalGeneral(data.getJt_qardh(), "ddMMyyyy", "dd-MM-yyyy"));
                break;
            case 53 :
                ll_qardh.setVisibility(View.VISIBLE);
                tv_noakad_qardh.setText(data.getNo_akad_qard());
                tv_jatuhtempo_qardh.setText(AppUtil.parseTanggalGeneral(data.getJt_qardh(), "ddMMyyyy", "dd-MM-yyyy"));
                break;
            case 54 :
                ll_qardh.setVisibility(View.VISIBLE);
                tv_noakad_qardh.setText(data.getNo_akad_qard());
                tv_jatuhtempo_qardh.setText(AppUtil.parseTanggalGeneral(data.getJt_qardh(), "ddMMyyyy", "dd-MM-yyyy"));
                break;
            case 55 :
                ll_qardh.setVisibility(View.VISIBLE);
                tv_noakad_qardh.setText(data.getNo_akad_qard());
                tv_jatuhtempo_qardh.setText(AppUtil.parseTanggalGeneral(data.getJt_qardh(), "ddMMyyyy", "dd-MM-yyyy"));
                break;
            case 56 :
                ll_qardh.setVisibility(View.VISIBLE);
                tv_noakad_qardh.setText(data.getNo_akad_qard());
                tv_jatuhtempo_qardh.setText(AppUtil.parseTanggalGeneral(data.getJt_qardh(), "ddMMyyyy", "dd-MM-yyyy"));
                break;
            case 57 :
                ll_qardh.setVisibility(View.VISIBLE);
                tv_noakad_qardh.setText(data.getNo_akad_qard());
                tv_jatuhtempo_qardh.setText(AppUtil.parseTanggalGeneral(data.getJt_qardh(), "ddMMyyyy", "dd-MM-yyyy"));
                break;
        }
    }

    @Override
    public void onMenuClick(String menu) {
        if (menu.equalsIgnoreCase(getString(R.string.submenu_hotprospek_datalengkap))){
            Intent it = new Intent(this, DataLengkapActivity_Apr.class);
            it.putExtra("cif", String.valueOf(data.getFid_cif_las()));
            it.putExtra("idAplikasi", data.getId_aplikasi());
            it.putExtra("plafond", data.getPlafond_induk());
            startActivity(it);
        }
        else if (menu.equalsIgnoreCase(getString(R.string.submenu_hotprospek_prescreening))){
            Intent it = new Intent(this, PrescreeningActivity_Apr.class);
            it.putExtra("idAplikasi", data.getId_aplikasi());
            it.putExtra("kodeProduct", data.getKode_produk());
            startActivity(it);
        }
        else if (menu.equalsIgnoreCase(getString(R.string.submenu_hotprospek_sektorekonomi))){
            Intent it = new Intent(this, SektorEkonomiActivity_Apr.class);
            it.putExtra("idTujuan", data.getId_tujuan());
            it.putExtra("namaTujuan", data.getNama_tujuan());
            it.putExtra("cifLas", data.getFid_cif_las());
            it.putExtra("idAplikasi", data.getId_aplikasi());
            startActivity(it);
        }
        else if (menu.equalsIgnoreCase(getString(R.string.submenu_hotprospek_lkn))){
            Intent it = new Intent(this, LknActivity_Apr.class);
            it.putExtra("cif", String.valueOf(data.getFid_cif_las()));
            it.putExtra("idAplikasi", data.getId_aplikasi());
            it.putExtra("kodeProduct", data.getKode_produk());
            it.putExtra("namaProduct", data.getNama_produk());
            it.putExtra("idTujuanPembiayaan", data.getId_tujuan());
            it.putExtra("tujuanPembiayaan", data.getNama_tujuan());
            it.putExtra("plafond",  data.getPlafond_induk());
            it.putExtra("jw", data.getJw());
            startActivity(it);
        }
        else if (menu.equalsIgnoreCase(getString(R.string.submenu_hotprospek_rpc))){
            Intent it = new Intent(this, RpcActivity_Apr.class);
            it.putExtra("idAplikasi", data.getId_aplikasi());
            startActivity(it);
        }
        else if (menu.equalsIgnoreCase(getString(R.string.submenu_hotprospek_agunan))){
            Intent it = new Intent(this, AgunanTerikatActivity_Apr.class);
            it.putExtra("cif", String.valueOf(data.getFid_cif_las()));
            it.putExtra("idAplikasi", String.valueOf(data.getId_aplikasi()));
            startActivity(it);
        }
        else if (menu.equalsIgnoreCase(getString(R.string.submenu_hotprospek_kelengkapandokumen))){
            Intent it = new Intent(this, KelengkapanDokumenActivity_Apr.class);
            it.putExtra("idAplikasi", data.getId_aplikasi());
            it.putExtra("kodeProduct", data.getKode_produk());
            it.putExtra("plafond", data.getPlafond_induk());
            startActivity(it);
        }
        else if (menu.equalsIgnoreCase(getString(R.string.submenu_hotprospek_scoring))){
            Intent it = new Intent(this, ScoringActivity_Apr.class);
            it.putExtra("idAplikasi", data.getId_aplikasi());
            it.putExtra("cif", data.getFid_cif_las());
            it.putExtra("kodeProduct", data.getKode_produk());
            startActivity(it);
        }
        else if (menu.equalsIgnoreCase(getString(R.string.submenu_hotprospek_history))){
            Intent it = new Intent(this, HistoryActivity.class);
            it.putExtra("cif", String.valueOf(data.getFid_cif_las()));
            it.putExtra("idAplikasi", data.getId_aplikasi());
            it.putExtra("mikro", "mikrofaedah");
            startActivity(it);
        }
    }
}
