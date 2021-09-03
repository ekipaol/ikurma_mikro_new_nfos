package com.application.bris.ikurma.page_aom.view.hotprospek;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.application.bris.ikurma.BuildConfig;
import com.application.bris.ikurma.R;
import com.application.bris.ikurma.api.config.UriApi;
import com.application.bris.ikurma.api.model.Error;
import com.application.bris.ikurma.api.model.ParseResponse;
import com.application.bris.ikurma.api.model.ParseResponseError;
import com.application.bris.ikurma.api.model.request.hotprospek.inquiryHotprospek;
import com.application.bris.ikurma.api.service.ApiClientAdapter;
import com.application.bris.ikurma.config.menu.Menu;
import com.application.bris.ikurma.adapter.menu.SubmenuHotprospekAdapter;
import com.application.bris.ikurma.database.AppPreferences;
import com.application.bris.ikurma.listener.menu.MenuClickListener;
import com.application.bris.ikurma.model.menu.ListViewSubmenuHotprospek;
import com.application.bris.ikurma.page_aom.dialog.BSKirimPutusan;
import com.application.bris.ikurma.page_aom.dialog.DialogPreviewPhoto;
import com.application.bris.ikurma.page_aom.listener.ConfirmListener;
import com.application.bris.ikurma.page_aom.model.hotprospek;
import com.application.bris.ikurma.page_aom.view.hotprospek.agunan.AgunanTerikatActivity;
import com.application.bris.ikurma.page_aom.view.hotprospek.datalengkap.DataLengkapActivity;
import com.application.bris.ikurma.page_aom.view.hotprospek.history.HistoryActivity;
import com.application.bris.ikurma.page_aom.view.hotprospek.kelengkapandokumen.KelengkapanDokumenActivity;
import com.application.bris.ikurma.page_aom.view.hotprospek.lkn.LknActivity;
import com.application.bris.ikurma.page_aom.view.hotprospek.prescreening.PrescreeningActivity;
import com.application.bris.ikurma.page_aom.view.hotprospek.rpc.RpcActivity;
import com.application.bris.ikurma.page_aom.view.hotprospek.scoring.ScoringActivity;
import com.application.bris.ikurma.page_aom.view.hotprospek.scoring.ScoringActivityNos;
import com.application.bris.ikurma.page_aom.view.hotprospek.sektorekonomi.SektorEkonomiActivity;
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

public class HotprospekDetailActivity extends AppCompatActivity implements MenuClickListener, ConfirmListener{

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
    @BindView(R.id.rv_submenu_hotprospek)
    RecyclerView rv_submenu_hotprospek;
    @BindView(R.id.sm_placeholder)
    ShimmerFrameLayout sm_placeholder;
    @BindView(R.id.ll_content)
    LinearLayout ll_content;

    @BindView(R.id.btn_back)
    ImageView btn_back;
    @BindView(R.id.tv_page_title)
    TextView tv_page_title;
    @BindView(R.id.btn_right)
    ImageView btn_right;

    @BindView(R.id.progressbar_loading)
    RelativeLayout loading;
    @BindView(R.id.btn_send)
    Button btn_send;

    private List<ListViewSubmenuHotprospek> dataMenu;
    private SubmenuHotprospekAdapter submenuHotprospekAdapter;
    private GridLayoutManager layoutMenu;
    private int coloumMenu = 3;
    public static int idAplikasi;
    private hotprospek data;
    private hotprospek dataHp;
    private String dataString;
    private Bitmap bitmapPhoto;
    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ao_activity_hotprospek_detail);
        ButterKnife.bind(this);
        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);
        idAplikasi = getIntent().getIntExtra("idAplikasi", 0);
        customToolbar();
        backgroundStatusBar();

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_send.setEnabled(false);
                BSKirimPutusan.display(getSupportFragmentManager(), HotprospekDetailActivity.this, data);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btn_send.setEnabled(true);
                    }
                }, 5000);
            }
        });


    }

    @Override
    public void onResume() {
        super.onResume();
        btn_right.setEnabled(false);
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
//        Toast.makeText(this, "id aplikasi is panteked", Toast.LENGTH_SHORT).show();
//        req.setIdAplikasi(123123);
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

//                            Toast.makeText(HotprospekDetailActivity.this, "Status aplikasi, flag  di pantek 1 semua", Toast.LENGTH_SHORT).show();
//                            data.setId_st_aplikasi(1);
//                            data.setFlag_data_lengkap(1);
//                            data.setFlag_prescreening(1);
//                            data.setFlag_data_pembiayaan(1);
//                            data.setFlag_lkn(1);
//                            data.setFlag_rpc(1);
//                            data.setFlag_agunan(1);
//                            data.setFlag_dokumen(1);
//                            data.setFlag_scoring(1);

                            //set loan type ke dalam preference
                            appPreferences.setLoanType(data.getKode_produk());


                            String url_photo = UriApi.Baseurl.URL + UriApi.foto.urlPhoto + data.getFid_photo();


                            GlideUrl glideUrl = new GlideUrl(url_photo, new LazyHeaders.Builder()
                                    .addHeader("Authorization", "Bearer "+appPreferences.getToken())
                                    .build());

                            Glide
                                    .with(HotprospekDetailActivity.this)
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
                            initializeMenu();
                            enableButtonSend();

                        }
                        else {
                            AppUtil.notiferror(HotprospekDetailActivity.this, findViewById(android.R.id.content), response.body().getMessage());
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
                        AppUtil.notiferror(HotprospekDetailActivity.this, findViewById(android.R.id.content), error.getMessage());
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
                AppUtil.notiferror(HotprospekDetailActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                }, 2000);
            }
        });
    }


    private void backgroundStatusBar(){
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    public void initializeMenu(){
        rv_submenu_hotprospek.setHasFixedSize(true);
        dataHp = data;
        dataMenu = getListMenu();
        submenuHotprospekAdapter = new SubmenuHotprospekAdapter(this, dataMenu, dataHp,this);
        layoutMenu = new GridLayoutManager(this, coloumMenu);
        rv_submenu_hotprospek.setLayoutManager(layoutMenu);
        rv_submenu_hotprospek.setAdapter(submenuHotprospekAdapter);
    }

    public void enableButtonSend(){
        if (data.getId_st_aplikasi() == 1 || data.getId_st_aplikasi() == -14){
            btn_right.setEnabled(true);
        }
        else{
            btn_right.setEnabled(false);
        }
        int flagPrescreening = data.getFlag_prescreening();
        int flagDataLengkap = data.getFlag_data_lengkap();
        int flagSektorEkonomi = data.getFlag_data_pembiayaan();
        int flagLKN = data.getFlag_lkn();
        int flagRPC = data.getFlag_rpc();
        int flagAgunan = data.getFlag_agunan();
        int flagKelengkapanData = data.getFlag_dokumen();
        int flagScoring = data.getFlag_scoring();

        if (data.getId_st_aplikasi() == 1 || data.getId_st_aplikasi() == -14){
            if (flagPrescreening == 1 && flagDataLengkap == 1 && flagSektorEkonomi == 1 && flagLKN == 1 &&
                    flagRPC == 1 && flagAgunan == 1 && flagKelengkapanData == 1 && flagScoring == 1){
                btn_send.getBackground().setColorFilter(getResources().getColor(R.color.colorPrimary), PorterDuff.Mode.SRC_ATOP);
                btn_send.setEnabled(true);
            }
            else{
                if(BuildConfig.IS_PRODUCTION==false){
                    btn_send.setEnabled(true);
                }
                else{
                    btn_send.setEnabled(false);
                }

            }
        }
        else{
            if(BuildConfig.IS_PRODUCTION==false){
                btn_send.setEnabled(true);
            }
            else{
                btn_send.setEnabled(false);
            }
        }
    }

    public void customToolbar(){
        btn_right.setVisibility(View.VISIBLE);
        tv_page_title.setText("Detail Hotprospek");
        btn_right.setImageResource(R.drawable.ic_edit_24dp);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_right.setEnabled(false);
                Intent it = new Intent(HotprospekDetailActivity.this, HotprospekEditActivity.class);
                it.putExtra("dataHotprospek", dataString);
                startActivity(it);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        btn_right.setEnabled(true);
                    }
                }, 5000);
            }
        });
    }


    private List<ListViewSubmenuHotprospek> getListMenu() {
        List<ListViewSubmenuHotprospek> menu = new ArrayList<>();
        Menu.SubmenuHotprospek(this, menu);
        return menu;
    }

    @Override
    public void onMenuClick(String menu) {
        if (menu.equalsIgnoreCase(getString(R.string.submenu_hotprospek_prescreening))){
            Intent it = new Intent(this, PrescreeningActivity.class);
            it.putExtra("idAplikasi", data.getId_aplikasi());
            it.putExtra("kodeProduct", data.getKode_produk());
            it.putExtra("tujuanPenggunaan", data.getId_tujuan());
            startActivity(it);
        }
        else if (menu.equalsIgnoreCase(getString(R.string.submenu_hotprospek_datalengkap))){
            Intent it = new Intent(this, DataLengkapActivity.class);
            it.putExtra("cif", String.valueOf(data.getFid_cif_las()));
            it.putExtra("idAplikasi", data.getId_aplikasi());
            it.putExtra("plafond", data.getPlafond_induk());
            startActivity(it);
        }

        else if (menu.equalsIgnoreCase(getString(R.string.submenu_hotprospek_history))){
            Intent it = new Intent(this, HistoryActivity.class);
            it.putExtra("cif", String.valueOf(data.getFid_cif_las()));
            it.putExtra("idAplikasi", data.getId_aplikasi());
            it.putExtra("mikro", "mikrofaedah");
            startActivity(it);
        }

        else if (menu.equalsIgnoreCase(getString(R.string.submenu_hotprospek_sektorekonomi))){
            Intent it = new Intent(this, SektorEkonomiActivity.class);
            it.putExtra("idTujuan", data.getId_tujuan());
            it.putExtra("namaTujuan", data.getNama_tujuan());
            it.putExtra("cifLas", data.getFid_cif_las());
            it.putExtra("idAplikasi", data.getId_aplikasi());
            startActivity(it);
        }
        else if (menu.equalsIgnoreCase(getString(R.string.submenu_hotprospek_lkn))){
            Intent it = new Intent(this, LknActivity.class);
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
            Intent it = new Intent(this, RpcActivity.class);
            it.putExtra("idAplikasi", data.getId_aplikasi());
            startActivity(it);
        }


        else if (menu.equalsIgnoreCase(getString(R.string.submenu_hotprospek_kelengkapandokumen))){
            Intent it = new Intent(this, KelengkapanDokumenActivity.class);
            it.putExtra("idAplikasi", data.getId_aplikasi());
            it.putExtra("kodeProduct", data.getKode_produk());
            it.putExtra("plafond", data.getPlafond_induk());
            startActivity(it);
        }

        else if (menu.equalsIgnoreCase(getString(R.string.submenu_hotprospek_scoring))){
            Intent it = new Intent(this, ScoringActivityNos.class);
            it.putExtra("idAplikasi", data.getId_aplikasi());
            it.putExtra("cif", data.getFid_cif_las());
            startActivity(it);
        }

        else if (menu.equalsIgnoreCase(getString(R.string.submenu_hotprospek_agunan))){
            Intent it = new Intent(this, AgunanTerikatActivity.class);
            it.putExtra("cif", String.valueOf(data.getFid_cif_las()));
            it.putExtra("idAplikasi", String.valueOf(data.getId_aplikasi()));
            startActivity(it);
        }
    }

    @Override
    public void success(boolean val) {
        if(val)
            finish();
    }

    @Override
    public void confirm(boolean val) {
    }
}
