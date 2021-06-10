package com.application.bris.ikurma.page_aom.view.approved.kelengkapandokumen_apr;

import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.application.bris.ikurma.R;
import com.application.bris.ikurma.api.config.UriApi;
import com.application.bris.ikurma.api.model.Error;
import com.application.bris.ikurma.api.model.ParseResponse;
import com.application.bris.ikurma.api.model.ParseResponseError;
import com.application.bris.ikurma.api.model.request.hotprospek.inquiryKelengkapanDokumen;
import com.application.bris.ikurma.api.service.ApiClientAdapter;
import com.application.bris.ikurma.database.AppPreferences;
import com.application.bris.ikurma.page_aom.adapter.hotprospek.AgunanKelengkapanDokumenAdapter;
import com.application.bris.ikurma.page_aom.dialog.DialogPreviewPhoto;
import com.application.bris.ikurma.page_aom.model.KelengkapanDokumen;
import com.application.bris.ikurma.page_aom.model.ListAgunanKelengkapanDokumen;
import com.application.bris.ikurma.util.AppUtil;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.makeramen.roundedimageview.RoundedDrawable;
import com.makeramen.roundedimageview.RoundedImageView;
import java.lang.reflect.Type;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KelengkapanDokumenActivity_Apr extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.ll_content)
    LinearLayout ll_content;
    @BindView(R.id.sm_placeholder)
    ShimmerFrameLayout sm_placeholder;
    @BindView(R.id.progressbar_loading)
    RelativeLayout loading;
    @BindView(R.id.btn_send)
    Button btn_send;
    @BindView(R.id.cb_ktpnasabahpasangan)
    CheckBox cb_ktpnasabahpasangan;
    @BindView(R.id.cb_kartukeluarga)
    CheckBox cb_kartukeluarga;
    @BindView(R.id.cb_suratnikah)
    CheckBox cb_suratnikah;
    @BindView(R.id.cb_pasphoto)
    CheckBox cb_pasphoto;
    @BindView(R.id.cb_npwp)
    CheckBox cb_npwp;
    @BindView(R.id.cb_formuliraplikasi)
    CheckBox cb_formuliraplikasi;
    @BindView(R.id.cb_suratijinusaha)
    CheckBox cb_suratijinusaha;
    @BindView(R.id.cb_catatankeuangan)
    CheckBox cb_catatankeuangan;
    @BindView(R.id.cb_daftarrencanapembiayaan)
    CheckBox cb_daftarrencanapembiayaan;
    @BindView(R.id.ll_iskur)
    LinearLayout ll_iskur;
    @BindView(R.id.cb_suratpernyataankur)
    CheckBox cb_suratpernyataankur;
    @BindView(R.id.cb_suratketeranganlunas)
    CheckBox cb_suratketeranganlunas;


    @BindView(R.id.iv_ktpnasabahpasangan)
    RoundedImageView iv_ktpnasabahpasangan;
    @BindView(R.id.iv_kartukeluarga)
    RoundedImageView iv_kartukeluarga;
    @BindView(R.id.iv_suratnikah)
    RoundedImageView iv_suratnikah;
    @BindView(R.id.iv_pasphoto)
    RoundedImageView iv_pasphoto;
    @BindView(R.id.iv_npwp)
    RoundedImageView iv_npwp;
    @BindView(R.id.iv_formuliraplikasi)
    RoundedImageView iv_formuliraplikasi;
    @BindView(R.id.iv_suratijinusaha)
    RoundedImageView iv_suratijinusaha;
    @BindView(R.id.iv_catatankeuangan)
    RoundedImageView iv_catatankeuangan;
    @BindView(R.id.iv_daftarrencanapembiayaan)
    RoundedImageView iv_daftarrencanapembiayaan;
    @BindView(R.id.iv_suratpernyataankur)
    RoundedImageView iv_suratpernyataankur;
    @BindView(R.id.iv_suratketeranganlunas)
    RoundedImageView iv_suratketeranganlunas;
    @BindView(R.id.btn_ktpnasabahpasangan)
    ImageView btn_ktpnasabahpasangan;
    @BindView(R.id.btn_kartukeluarga)
    ImageView btn_kartukeluarga;
    @BindView(R.id.btn_suratnikah)
    ImageView btn_suratnikah;
    @BindView(R.id.btn_pasphoto)
    ImageView btn_pasphoto;
    @BindView(R.id.btn_npwp)
    ImageView btn_npwp;
    @BindView(R.id.btn_formuliraplikasi)
    ImageView btn_formuliraplikasi;
    @BindView(R.id.btn_suratijinusaha)
    ImageView btn_suratijinusaha;
    @BindView(R.id.btn_catatankeuangan)
    ImageView btn_catatankeuangan;
    @BindView(R.id.btn_daftarrencanapembiayaan)
    ImageView btn_daftarrencanapembiayaan;
    @BindView(R.id.btn_suratpernyataankur)
    ImageView btn_suratpernyataankur;
    @BindView(R.id.btn_suratketeranganlunas)
    ImageView btn_suratketeranganlunas;
    @BindView(R.id.et_nomorsiup)
    EditText et_nomorsiup;

    @BindView(R.id.rv_listagunan)
    RecyclerView rv_listagunan;
    @BindView(R.id.ll_emptydata)
    LinearLayout ll_emptydata;

    private int idAplikasi;
    private String kodeProduct;
    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    private String dataString;
    private KelengkapanDokumen data;


    private Bitmap loadedPicture;

    private Bitmap bitmap_ktpnasabahpasangan, bitmap_kartukeluarga, bitmap_suratnikah, bitmap_pasphoto,
            bitmap_npwp, bitmap_formuliraplikasi, bitmap_suratijinusaha, bitmap_catatankeuangan,
            bitmap_daftarrencanapembiayaan, bitmap_suratpernyataankur, bitmap_suratketeranganlunas;

    private int plafond;

    private List<ListAgunanKelengkapanDokumen> dataAgunan;
    private AgunanKelengkapanDokumenAdapter adapterAgunan;
    private LinearLayoutManager layoutAgunan;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ao_activity_hotprospek_kelengkapandokumen);
        ButterKnife.bind(this);
        apiClientAdapter = new ApiClientAdapter(this);
        idAplikasi = getIntent().getIntExtra("idAplikasi", 0);
        kodeProduct = getIntent().getStringExtra("kodeProduct");
        plafond = getIntent().getIntExtra("plafond", 0);
        backgroundStatusBar();
        AppUtil.toolbarRegular(this, getString(R.string.submenu_hotprospek_kelengkapandokumen));
        setDisable();
        loadKelengkapanDokumen();
        if (kodeProduct.equalsIgnoreCase("127") || kodeProduct.equalsIgnoreCase("128")|| kodeProduct.equalsIgnoreCase("318")|| kodeProduct.equalsIgnoreCase("319")|| kodeProduct.equalsIgnoreCase("320")|| kodeProduct.equalsIgnoreCase("841")){
            ll_iskur.setVisibility(View.VISIBLE);
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

    }

    @Override
    protected void onResume() {
        super.onResume();
        sm_placeholder.startShimmer();
    }

    @Override
    protected void onPause() {
        super.onPause();
        sm_placeholder.stopShimmer();
    }

    private void backgroundStatusBar(){
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    private void setDisable(){
        iv_ktpnasabahpasangan.setOnClickListener(this);
        iv_kartukeluarga.setOnClickListener(this);
        iv_suratnikah.setOnClickListener(this);
        iv_pasphoto.setOnClickListener(this);
        iv_npwp.setOnClickListener(this);
        iv_formuliraplikasi.setOnClickListener(this);
        iv_suratijinusaha.setOnClickListener(this);
        iv_catatankeuangan.setOnClickListener(this);
        iv_daftarrencanapembiayaan.setOnClickListener(this);
        iv_suratpernyataankur.setOnClickListener(this);
        iv_suratketeranganlunas.setOnClickListener(this);

        cb_ktpnasabahpasangan.setEnabled(false);
        cb_kartukeluarga.setEnabled(false);
        cb_suratnikah.setEnabled(false);
        cb_pasphoto.setEnabled(false);
        cb_npwp.setEnabled(false);
        cb_formuliraplikasi.setEnabled(false);
        cb_suratijinusaha.setEnabled(false);
        cb_catatankeuangan.setEnabled(false);
        cb_daftarrencanapembiayaan.setEnabled(false);
        cb_suratpernyataankur.setEnabled(false);
        cb_suratketeranganlunas.setEnabled(false);
        btn_ktpnasabahpasangan.setVisibility(View.GONE);
        btn_kartukeluarga.setVisibility(View.GONE);
        btn_suratnikah.setVisibility(View.GONE);
        btn_pasphoto.setVisibility(View.GONE);
        btn_npwp.setVisibility(View.GONE);
        btn_formuliraplikasi.setVisibility(View.GONE);
        btn_suratijinusaha.setVisibility(View.GONE);
        btn_catatankeuangan.setVisibility(View.GONE);
        btn_daftarrencanapembiayaan.setVisibility(View.GONE);
        btn_suratpernyataankur.setVisibility(View.GONE);
        btn_suratketeranganlunas.setVisibility(View.GONE);
        btn_send.setVisibility(View.GONE);
        et_nomorsiup.setEnabled(false);
    }

    private void loadKelengkapanDokumen() {
        inquiryKelengkapanDokumen req = new inquiryKelengkapanDokumen(idAplikasi);
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().inquiryKelengkapanDokumen(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                sm_placeholder.stopShimmer();
                sm_placeholder.setVisibility(View.GONE);
                ll_content.setVisibility(View.VISIBLE);
                try {
                    if (response.isSuccessful()){
                        if(response.body().getStatus().equalsIgnoreCase("00")){
                            Gson gson = new Gson();
                            Type typeAgunan = new TypeToken<List<ListAgunanKelengkapanDokumen>>() {
                            }.getType();

                            dataString = response.body().getData().get("kelDokumen").toString();

                            if (!dataString.equalsIgnoreCase("{}")){
                                data = gson.fromJson(dataString, KelengkapanDokumen.class);
                                dataAgunan = gson.fromJson(response.body().getData().get("kelDokumen").getAsJsonObject().get("ID_DOKUMEN_AGUNAN").toString(), typeAgunan);

                                setCheckbox();

                                if (dataAgunan.size() > 0) {
                                    ll_emptydata.setVisibility(View.GONE);
                                    initializeAgunan();
                                } else {
                                    ll_emptydata.setVisibility(View.VISIBLE);
                                }
                            }
                        }
                    }
                    else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(KelengkapanDokumenActivity_Apr.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                AppUtil.notiferror(KelengkapanDokumenActivity_Apr.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }


    private void setCheckbox(){

        if(data != null){
            if (data.getfCKTP())
                cb_ktpnasabahpasangan.setChecked(true);

            if(data.getfCKK())
                cb_kartukeluarga.setChecked(true);

            if (data.getfCSURATNIKAH())
                cb_suratnikah.setChecked(true);

            if (data.getpASPHOTO())
                cb_pasphoto.setChecked(true);

            if (data.getfCNPWPPRIBADI())
                cb_npwp.setChecked(true);

            if (data.getaPLIKASIPERMOHONAN())
                cb_formuliraplikasi.setChecked(true);

            if (data.getfCSIUP())
                cb_suratijinusaha.setChecked(true);

            if (data.getlAPORANKEUANGAN())
                cb_catatankeuangan.setChecked(true);

            if (data.getsURATPERNYATAANNASABAH())
                cb_daftarrencanapembiayaan.setChecked(true);

            if (data.getsURATPERNYATAANKUR())
                cb_suratpernyataankur.setChecked(true);

            if (data.getsURATKETERANGANLUNASKUR())
                cb_suratketeranganlunas.setChecked(true);

            et_nomorsiup.setText(data.getnOSKU());

            bitmap_ktpnasabahpasangan = setLoadImage(iv_ktpnasabahpasangan, data.getiDDOKUMENKTP());
            bitmap_kartukeluarga = setLoadImage(iv_kartukeluarga, data.getiDDOKUMENKK());
            bitmap_suratnikah = setLoadImage(iv_suratnikah, data.getiDDOKUMENSURATNIKAH());
            bitmap_pasphoto = setLoadImage(iv_pasphoto, data.getiDDOKUMENPASPHOTO());
            bitmap_npwp = setLoadImage(iv_npwp, data.getiDDOKUMENNPWPPRIBADI());
            bitmap_formuliraplikasi = setLoadImage(iv_formuliraplikasi, data.getiDDOKUMENAPLIKASI());
            bitmap_suratijinusaha = setLoadImage(iv_suratijinusaha, data.getiDDOKUMENSIUP());
            bitmap_catatankeuangan = setLoadImage(iv_catatankeuangan, data.getiDDOKUMENLAPORANKEUANGAN());
            bitmap_daftarrencanapembiayaan = setLoadImage(iv_daftarrencanapembiayaan, data.getiDDOKUMENSURATPERNYATAANNASABAH());
            bitmap_suratpernyataankur = setLoadImage(iv_suratpernyataankur, data.getiDDOKUMENSURATPERNYATAANKUR());
            bitmap_suratketeranganlunas = setLoadImage(iv_suratketeranganlunas, data.getiDDOKUMENSURATKETERANGANLUNASKUR());
        }
    }

    public void initializeAgunan(){
        adapterAgunan = new AgunanKelengkapanDokumenAdapter(KelengkapanDokumenActivity_Apr.this, dataAgunan);
        layoutAgunan = new LinearLayoutManager(KelengkapanDokumenActivity_Apr.this);
        rv_listagunan.setLayoutManager(layoutAgunan);
        rv_listagunan.setAdapter(adapterAgunan);

        if (dataAgunan == null){
            ll_emptydata.setVisibility(View.VISIBLE);
        }
    }

    public Bitmap setLoadImage(final ImageView iv, int idFoto){
        String url_photo = UriApi.Baseurl.URL + UriApi.foto.urlPhoto + idFoto;

        GlideUrl glideUrl = new GlideUrl(url_photo, new LazyHeaders.Builder()
                .addHeader("Authorization", "Bearer "+appPreferences.getToken())
                .build());

        Glide
                .with(KelengkapanDokumenActivity_Apr.this)
                .asBitmap()
                .load(glideUrl)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        iv.setImageBitmap(resource);
                        loadedPicture = resource;
                    }
                });
        return loadedPicture;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_ktpnasabahpasangan:
                DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Foto", ((RoundedDrawable)iv_ktpnasabahpasangan.getDrawable()).getSourceBitmap());
                break;
            case R.id.iv_kartukeluarga:
                DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Foto", ((RoundedDrawable)iv_kartukeluarga.getDrawable()).getSourceBitmap());
                break;
            case R.id.iv_suratnikah:
                DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Foto", ((RoundedDrawable)iv_suratnikah.getDrawable()).getSourceBitmap());
                break;
            case R.id.iv_pasphoto:
                DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Foto", ((RoundedDrawable)iv_pasphoto.getDrawable()).getSourceBitmap());
                break;
            case R.id.iv_npwp:
                DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Foto", ((RoundedDrawable)iv_npwp.getDrawable()).getSourceBitmap());
                break;
            case R.id.iv_formuliraplikasi:
                DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Foto", ((RoundedDrawable)iv_formuliraplikasi.getDrawable()).getSourceBitmap());
                break;
            case R.id.iv_suratijinusaha:
                DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Foto", ((RoundedDrawable)iv_suratijinusaha.getDrawable()).getSourceBitmap());
                break;
            case R.id.iv_catatankeuangan:
                DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Foto", ((RoundedDrawable)iv_catatankeuangan.getDrawable()).getSourceBitmap());
                break;
            case R.id.iv_daftarrencanapembiayaan:
                DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Foto", ((RoundedDrawable)iv_daftarrencanapembiayaan.getDrawable()).getSourceBitmap());
                break;
            case R.id.iv_suratpernyataankur:
                DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Foto", ((RoundedDrawable)iv_suratpernyataankur.getDrawable()).getSourceBitmap());
                break;
            case R.id.iv_suratketeranganlunas:
                DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Foto", ((RoundedDrawable)iv_suratketeranganlunas.getDrawable()).getSourceBitmap());
                break;

        }
    }
}
