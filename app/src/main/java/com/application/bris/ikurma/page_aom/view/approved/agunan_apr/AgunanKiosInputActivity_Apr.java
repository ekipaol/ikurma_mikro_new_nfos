package com.application.bris.ikurma.page_aom.view.approved.agunan_apr;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.application.bris.ikurma.R;
import com.application.bris.ikurma.api.config.UriApi;
import com.application.bris.ikurma.api.model.Error;
import com.application.bris.ikurma.api.model.ParseResponseArr;
import com.application.bris.ikurma.api.model.ParseResponseError;
import com.application.bris.ikurma.api.model.request.agunan.ReqAgunanData;
import com.application.bris.ikurma.api.service.ApiClientAdapter;
import com.application.bris.ikurma.database.AppPreferences;
import com.application.bris.ikurma.page_aom.dialog.DialogPreviewPhoto;
import com.application.bris.ikurma.page_aom.model.AgunanKios;
import com.application.bris.ikurma.util.AppUtil;
import com.application.bris.ikurma.util.NumberTextWatcherCanNolForThousand;
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
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

public class AgunanKiosInputActivity_Apr extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.ll_content)
    LinearLayout ll_content;
    @BindView(R.id.tf_tanggalpemeriksaan)
    TextFieldBoxes tf_tanggalpemeriksaan;
    @BindView(R.id.et_tanggalpemeriksaan)
    EditText et_tanggalpemeriksaan;
    @BindView(R.id.tf_jenisagunan)
    TextFieldBoxes tf_jenisagunan;
    @BindView(R.id.et_jenisagunan)
    EditText et_jenisagunan;
    @BindView(R.id.tf_dokumenbuktihakkios)
    TextFieldBoxes tf_dokumenbuktihakkios;
    @BindView(R.id.et_dokumenbuktihakkios)
    EditText et_dokumenbuktihakkios;
    @BindView(R.id.tf_nomordokumen)
    TextFieldBoxes tf_nomordokumen;
    @BindView(R.id.et_nomordokumen)
    EditText et_nomordokumen;
    @BindView(R.id.tf_namapemeganghak)
    TextFieldBoxes tf_namapemeganghak;
    @BindView(R.id.et_namapemeganghak)
    EditText et_namapemeganghak;
    @BindView(R.id.tf_hubunganpemeganghakdengannasabah)
    TextFieldBoxes tf_hubunganpemeganghakdengannasabah;
    @BindView(R.id.et_hubunganpemeganghakdengannasabah)
    EditText et_hubunganpemeganghakdengannasabah;
    @BindView(R.id.tf_masaberlakuijinkios)
    TextFieldBoxes tf_masaberlakuijinkios;
    @BindView(R.id.et_masaberlakuijinkios)
    EditText et_masaberlakuijinkios;
    @BindView(R.id.tf_luaskios)
    TextFieldBoxes tf_luaskios;
    @BindView(R.id.et_luaskios)
    EditText et_luaskios;
    @BindView(R.id.tf_namapasar)
    TextFieldBoxes tf_namapasar;
    @BindView(R.id.et_namapasar)
    EditText et_namapasar;
    @BindView(R.id.tf_blok)
    TextFieldBoxes tf_blok;
    @BindView(R.id.et_blok)
    EditText et_blok;
    @BindView(R.id.tf_nomorkios)
    TextFieldBoxes tf_nomorkios;
    @BindView(R.id.et_nomorkios)
    EditText et_nomorkios;
    @BindView(R.id.btn_set_loc)
    Button btn_set_loc;
    @BindView(R.id.tv_set_loc)
    TextView tv_set_loc;
    @BindView(R.id.tf_wilayahkota)
    TextFieldBoxes tf_wilayahkota;
    @BindView(R.id.et_wilayahkota)
    EditText et_wilayahkota;
    @BindView(R.id.tf_lokasijaminan)
    TextFieldBoxes tf_lokasijaminan;
    @BindView(R.id.et_lokasijaminan)
    EditText et_lokasijaminan;
    @BindView(R.id.tf_tahundibangunrenovasi)
    TextFieldBoxes tf_tahundibangunrenovasi;
    @BindView(R.id.et_tahundibangunrenovasi)
    EditText et_tahundibangunrenovasi;
    @BindView(R.id.tf_retribusi)
    TextFieldBoxes tf_retribusi;
    @BindView(R.id.et_retribusi)
    EditText et_retribusi;
    @BindView(R.id.tf_listrik)
    TextFieldBoxes tf_listrik;
    @BindView(R.id.et_listrik)
    EditText et_listrik;
    @BindView(R.id.tf_dayalistrik)
    TextFieldBoxes tf_dayalistrik;
    @BindView(R.id.et_dayalistrik)
    EditText et_dayalistrik;
    @BindView(R.id.tf_telepon)
    TextFieldBoxes tf_telepon;
    @BindView(R.id.et_telepon)
    EditText et_telepon;
    @BindView(R.id.tf_nomortelepon)
    TextFieldBoxes tf_nomortelepon;
    @BindView(R.id.et_nomortelepon)
    EditText et_nomortelepon;
    @BindView(R.id.tf_namapemberiinformasi)
    TextFieldBoxes tf_namapemberiinformasi;
    @BindView(R.id.et_namapemberiinformasi)
    EditText et_namapemberiinformasi;
    @BindView(R.id.tf_alamatpemberiinformasi)
    TextFieldBoxes tf_alamatpemberiinformasi;
    @BindView(R.id.et_alamatpemberiinformasi)
    EditText et_alamatpemberiinformasi;
    @BindView(R.id.tf_nohppemberiinformasi)
    TextFieldBoxes tf_nohppemberiinformasi;
    @BindView(R.id.et_nohppemberiinformasi)
    EditText et_nohppemberiinformasi;
    @BindView(R.id.tf_nilaimarket)
    TextFieldBoxes tf_nilaimarket;
    @BindView(R.id.et_nilaimarket)
    EditText et_nilaimarket;
    @BindView(R.id.tf_nilailikuidasi)
    TextFieldBoxes tf_nilailikuidasi;
    @BindView(R.id.et_nilailikuidasi)
    EditText et_nilailikuidasi;
    @BindView(R.id.iv_fotobpn)
    RoundedImageView iv_fotobpn;
    @BindView(R.id.btn_fotobpn)
    ImageView btn_fotobpn;
    @BindView(R.id.iv_fotokios)
    RoundedImageView iv_fotokios;
    @BindView(R.id.btn_fotokios)
    ImageView btn_fotokios;
    @BindView(R.id.iv_batasutara)
    RoundedImageView iv_batasutara;
    @BindView(R.id.btn_batasutara)
    ImageView btn_batasutara;
    @BindView(R.id.iv_batasselatan)
    RoundedImageView iv_batasselatan;
    @BindView(R.id.btn_batasselatan)
    ImageView btn_batasselatan;
    @BindView(R.id.iv_batastimur)
    RoundedImageView iv_batastimur;
    @BindView(R.id.btn_batastimur)
    ImageView btn_batastimur;
    @BindView(R.id.iv_batasbarat)
    RoundedImageView iv_batasbarat;
    @BindView(R.id.btn_batasbarat)
    ImageView btn_batasbarat;
    @BindView(R.id.et_pendapatkondisijaminan)
    EditText et_pendapatkondisijaminan;
    @BindView(R.id.btn_send)
    Button btn_send;
    @BindView(R.id.sm_placeholder)
    ShimmerFrameLayout sm_placeholder;
    @BindView(R.id.progressbar_loading)
    RelativeLayout loading;

    public static SimpleDateFormat dateClient = new SimpleDateFormat("dd-MM-yyyy", Locale.US);


    private String idAgunan;
    private String idApl;
    private String idCif;
    private String typeProduk;
    private String loanType;

    private AgunanKios dataAgunan;
    private List<AgunanKios> listDataAgunan;

    private Bitmap bitmap_fotobpn, bitmap_fotokios, bitmap_batasutara, bitmap_batasselatan, bitmap_batastimur, bitmap_batasbarat, loadedPicture;

    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ao_activity_agunankios_input);
        ButterKnife.bind(this);
        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);
        AppUtil.toolbarRegular(this, "Agunan Kios");
        idAgunan = getIntent().getStringExtra("idAgunan");
        idApl = getIntent().getStringExtra("idAplikasi");
        idCif = getIntent().getStringExtra("cif");
        typeProduk = getIntent().getStringExtra("tp_produk");
        loanType = getIntent().getStringExtra("loan_type");
        backgroundStatusBar();
        if (!idAgunan.equalsIgnoreCase("0")){
            setDisable();
            loadData();
        }
        else {
            sm_placeholder.setVisibility(View.GONE);
            ll_content.setVisibility(View.VISIBLE);
        }

    }

    private void backgroundStatusBar(){
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    private void setDisable(){
        iv_fotobpn.setOnClickListener(this);
        iv_fotokios.setOnClickListener(this);
        iv_batasutara.setOnClickListener(this);
        iv_batasselatan.setOnClickListener(this);
        iv_batastimur.setOnClickListener(this);
        iv_batasbarat.setOnClickListener(this);

        et_nilaimarket.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_nilaimarket));
        et_nilailikuidasi.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_nilailikuidasi));
        et_retribusi.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_retribusi));

        et_tanggalpemeriksaan.setEnabled(false);
        et_jenisagunan.setEnabled(false);
        et_dokumenbuktihakkios.setEnabled(false);
        et_nomordokumen.setEnabled(false);
        et_namapemeganghak.setEnabled(false);
        et_hubunganpemeganghakdengannasabah.setEnabled(false);
        et_masaberlakuijinkios.setEnabled(false);
        et_luaskios.setEnabled(false);
        et_namapasar.setEnabled(false);
        et_blok.setEnabled(false);
        et_nomorkios.setEnabled(false);
        et_wilayahkota.setEnabled(false);
        et_lokasijaminan.setEnabled(false);
        et_tahundibangunrenovasi.setEnabled(false);
        et_retribusi.setEnabled(false);
        et_listrik.setEnabled(false);
        et_dayalistrik.setEnabled(false);
        et_telepon.setEnabled(false);
        et_nomortelepon.setEnabled(false);
        et_namapemberiinformasi.setEnabled(false);
        et_alamatpemberiinformasi.setEnabled(false);
        et_nohppemberiinformasi.setEnabled(false);
        et_nilaimarket.setEnabled(false);
        et_nilailikuidasi.setEnabled(false);
        btn_set_loc.setVisibility(View.GONE);
        btn_send.setVisibility(View.GONE);
        btn_fotobpn.setVisibility(View.GONE);
        btn_fotokios.setVisibility(View.GONE);
        btn_batasutara.setVisibility(View.GONE);
        btn_batasselatan.setVisibility(View.GONE);
        btn_batastimur.setVisibility(View.GONE);
        btn_batasbarat.setVisibility(View.GONE);
        et_pendapatkondisijaminan.setEnabled(false);
    }

    private void loadData() {
        sm_placeholder.startShimmer();
        ReqAgunanData req = new ReqAgunanData(AppUtil.parseIntWithDefault(idApl, 0), AppUtil.parseIntWithDefault(idAgunan, 0), AppUtil.parseIntWithDefault(idCif, 0));
        Call<ParseResponseArr> call = apiClientAdapter.getApiInterface().inquiryAgunanKios(req);
        call.enqueue(new Callback<ParseResponseArr>() {
            @Override
            public void onResponse(Call<ParseResponseArr> call, Response<ParseResponseArr> response) {
                sm_placeholder.stopShimmer();
                sm_placeholder.setVisibility(View.GONE);
                ll_content.setVisibility(View.VISIBLE);
                try {
                    if (response.isSuccessful()){
                        if (response.body().getStatus().equalsIgnoreCase("00")){
                            Gson gson = new Gson();
                            Type type = new TypeToken<List<AgunanKios>>() {}.getType();

                            String listDataString = response.body().getData().toString();
                            listDataAgunan = gson.fromJson(listDataString, type);
                            dataAgunan=listDataAgunan.get(0);

                            et_tanggalpemeriksaan.setText(AppUtil.parseTanggalGeneral(dataAgunan.gettANGGALPEMERIKSAAN(), "ddMMyyyy", "dd-MM-yyyy"));
                            et_jenisagunan.setText(dataAgunan.getjENISJAMINAN());
                            et_dokumenbuktihakkios.setText(dataAgunan.getdOKUMENBUKTI());
                            et_nomordokumen.setText(dataAgunan.getnODOKUMEN());
                            et_namapemeganghak.setText(dataAgunan.getnAMAPEMEGANGHAK());
                            et_hubunganpemeganghakdengannasabah.setText(dataAgunan.gethUBUNGANPEMEGANGHAK());
                            et_masaberlakuijinkios.setText(AppUtil.parseTanggalGeneral(dataAgunan.getmASABERLAKUIJIN(), "ddMMyyyy", "dd-MM-yyyy"));
                            et_luaskios.setText(String.valueOf(dataAgunan.getlUASKIOS()));
                            et_namapasar.setText(dataAgunan.getnAMAPASAR());
                            et_blok.setText(dataAgunan.getlOSKIOS());
                            et_nomorkios.setText(dataAgunan.getnOKIOS());
                            tv_set_loc.setText(dataAgunan.getKoordinat());
                            et_wilayahkota.setText(dataAgunan.getwILAYAHKOTA());
                            et_lokasijaminan.setText(dataAgunan.getlOKASIJAMINAN());
                            et_tahundibangunrenovasi.setText(dataAgunan.gettAHUNRENOVASI());
                            et_retribusi.setText(dataAgunan.getrETRIBUSI());
                            et_listrik.setText(dataAgunan.getlISTRIK());
                            et_dayalistrik.setText(String.valueOf(dataAgunan.getbESARANDAYA()));
                            et_telepon.setText(dataAgunan.gettELEPON());
                            et_nomortelepon.setText(dataAgunan.getnOTLP());
                            et_namapemberiinformasi.setText(dataAgunan.getnAMAPEMBERIINFO1());
                            et_alamatpemberiinformasi.setText(dataAgunan.getaLAMATPEMBERIINFO1());
                            et_nohppemberiinformasi.setText(dataAgunan.getnOTELPEMBERIINFO1());
                            et_nilaimarket.setText(dataAgunan.getnILAIMARKET());
                            et_nilailikuidasi.setText(dataAgunan.getnILAITAKSASI());
                            et_pendapatkondisijaminan.setText(dataAgunan.getpENDAPATPEMERIKSA());

                            bitmap_fotobpn = setLoadImage(iv_fotobpn, dataAgunan.getIdPhotoKbpn());
                            bitmap_fotokios = setLoadImage(iv_fotokios, dataAgunan.getIdPhotoKutama());
                            bitmap_batasutara = setLoadImage(iv_batasutara, dataAgunan.getIdPhotoKutara());
                            bitmap_batasselatan = setLoadImage(iv_batasselatan, dataAgunan.getIdPhotoKselatan());
                            bitmap_batastimur = setLoadImage(iv_batastimur, dataAgunan.idPhotoKtimur);
                            bitmap_batasbarat = setLoadImage(iv_batasbarat, dataAgunan.getIdPhotoKbarat());
                        }
                        else{
                            AppUtil.notiferror(AgunanKiosInputActivity_Apr.this, findViewById(android.R.id.content), response.body().getMessage());
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
                        AppUtil.notiferror(AgunanKiosInputActivity_Apr.this, findViewById(android.R.id.content), error.getMessage());
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
            public void onFailure(Call<ParseResponseArr> call, Throwable t) {
                AppUtil.notiferror(AgunanKiosInputActivity_Apr.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                }, 2000);
            }
        });
    }

    public Bitmap setLoadImage(final ImageView iv, int idFoto){
        String url_photo = UriApi.Baseurl.URL + UriApi.foto.urlPhoto + idFoto;

        GlideUrl glideUrl = new GlideUrl(url_photo, new LazyHeaders.Builder()
                .addHeader("Authorization", "Bearer "+appPreferences.getToken())
                .build());

        Glide
                .with(AgunanKiosInputActivity_Apr.this)
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
            case R.id.iv_fotobpn:
                DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Foto", ((RoundedDrawable)iv_fotobpn.getDrawable()).getSourceBitmap());
                break;
            case R.id.iv_fotokios:
                DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Foto", ((RoundedDrawable)iv_fotokios.getDrawable()).getSourceBitmap());
                break;
            case R.id.iv_batasutara:
                DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Foto", ((RoundedDrawable)iv_batasutara.getDrawable()).getSourceBitmap());
                break;
            case R.id.iv_batasselatan:
                DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Foto", ((RoundedDrawable)iv_batasselatan.getDrawable()).getSourceBitmap());
                break;
            case R.id.iv_batastimur:
                DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Foto", ((RoundedDrawable)iv_batastimur.getDrawable()).getSourceBitmap());
                break;
            case R.id.iv_batasbarat :
                DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Foto", ((RoundedDrawable)iv_batasbarat.getDrawable()).getSourceBitmap());
                break;
        }
    }
}
