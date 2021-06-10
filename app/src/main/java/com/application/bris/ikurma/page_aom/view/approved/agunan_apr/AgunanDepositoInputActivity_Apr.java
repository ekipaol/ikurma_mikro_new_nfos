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
import com.application.bris.ikurma.R;
import com.application.bris.ikurma.api.config.UriApi;
import com.application.bris.ikurma.api.model.Error;
import com.application.bris.ikurma.api.model.ParseResponse;
import com.application.bris.ikurma.api.model.ParseResponseError;
import com.application.bris.ikurma.api.model.request.agunan.ReqAgunanData;
import com.application.bris.ikurma.api.service.ApiClientAdapter;
import com.application.bris.ikurma.database.AppPreferences;
import com.application.bris.ikurma.page_aom.dialog.DialogPreviewPhoto;
import com.application.bris.ikurma.page_aom.model.AgunanDeposito;
import com.application.bris.ikurma.page_aom.view.hotprospek.agunan.AgunanDepositoInputActivity;
import com.application.bris.ikurma.util.AppUtil;
import com.application.bris.ikurma.util.NumberTextWatcherCanNolForThousand;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.gson.Gson;
import com.makeramen.roundedimageview.RoundedDrawable;
import com.makeramen.roundedimageview.RoundedImageView;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

public class AgunanDepositoInputActivity_Apr extends AppCompatActivity{

    @BindView(R.id.ll_content)
    LinearLayout ll_content;
    @BindView(R.id.tf_tanggalpemeriksaan)
    TextFieldBoxes tf_tanggalpemeriksaan;
    @BindView(R.id.et_tanggalpemeriksaan)
    EditText et_tanggalpemeriksaan;
    @BindView(R.id.tf_jenisdeposito)
    TextFieldBoxes tf_jenisdeposito;
    @BindView(R.id.et_jenisdeposito)
    EditText et_jenisdeposito;
    @BindView(R.id.tf_namapemilik)
    TextFieldBoxes tf_namapemilik;
    @BindView(R.id.et_namapemilik)
    EditText et_namapemilik;
    @BindView(R.id.tf_alamatpemilik)
    TextFieldBoxes tf_alamatpemilik;
    @BindView(R.id.et_alamatpemilik)
    EditText et_alamatpemilik;

    @BindView(R.id.tf_hubungandengannasabah)
    TextFieldBoxes tf_hubungandengannasabah;
    @BindView(R.id.et_hubungandengannasabah)
    EditText et_hubungandengannasabah;
    @BindView(R.id.tf_nomorbilyet)
    TextFieldBoxes tf_nomorbilyet;
    @BindView(R.id.et_nomorbilyet)
    EditText et_nomorbilyet;
    @BindView(R.id.tf_bankpenerbit)
    TextFieldBoxes tf_bankpenerbit;
    @BindView(R.id.et_bankpenerbit)
    EditText et_bankpenerbit;
    @BindView(R.id.tf_tanggalpenerbitan)
    TextFieldBoxes tf_tanggalpenerbitan;
    @BindView(R.id.et_tanggalpenerbitan)
    EditText et_tanggalpenerbitan;
    @BindView(R.id.tf_tanggaljatuhtempo)
    TextFieldBoxes tf_tanggaljatuhtempo;
    @BindView(R.id.et_tanggaljatuhtempo)
    EditText et_tanggaljatuhtempo;
    @BindView(R.id.tf_aro)
    TextFieldBoxes tf_aro;
    @BindView(R.id.et_aro)
    EditText et_aro;
    @BindView(R.id.tf_nilainominal)
    TextFieldBoxes tf_nilainominal;
    @BindView(R.id.et_nilainominal)
    EditText et_nilainominal;
    @BindView(R.id.tf_nilailikuidasi)
    TextFieldBoxes tf_nilailikuidasi;
    @BindView(R.id.et_nilailikuidasi)
    EditText et_nilailikuidasi;
    @BindView(R.id.et_keterangan)
    EditText et_keterangan;
    @BindView(R.id.iv_foto)
    RoundedImageView iv_foto;
    @BindView(R.id.btn_foto)
    ImageView btn_foto;
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
    private AgunanDeposito dataAgunan;
    private List<AgunanDeposito> listDataAgunan;
    private Bitmap bitmap_foto;
    private Bitmap loadedPicture;
    private ApiClientAdapter apiClientAdapter;
    AppPreferences appPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ao_activity_agunandeposito_input);
        ButterKnife.bind(this);
        apiClientAdapter = new ApiClientAdapter(this);
        AppUtil.toolbarRegular(this, "Agunan Deposito");
        idAgunan = getIntent().getStringExtra("idAgunan");
        idApl = getIntent().getStringExtra("idAplikasi");
        idCif = getIntent().getStringExtra("cif");
        typeProduk = getIntent().getStringExtra("tp_produk");
        loanType = getIntent().getStringExtra("loan_type");
        appPreferences=new AppPreferences(AgunanDepositoInputActivity_Apr.this);
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

    private void setDisable(){
        iv_foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Foto", ((RoundedDrawable)iv_foto.getDrawable()).getSourceBitmap());
            }
        });
        et_nilainominal.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_nilainominal));
        et_nilailikuidasi.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_nilailikuidasi));
        et_tanggalpemeriksaan.setEnabled(false);
        et_jenisdeposito.setEnabled(false);
        et_namapemilik.setEnabled(false);
        et_alamatpemilik.setEnabled(false);
        et_hubungandengannasabah.setEnabled(false);
        et_nomorbilyet.setEnabled(false);
        et_bankpenerbit.setEnabled(false);
        et_tanggalpenerbitan.setEnabled(false);
        et_tanggaljatuhtempo.setEnabled(false);
        et_aro.setEnabled(false);
        et_nilainominal.setEnabled(false);
        et_nilailikuidasi.setEnabled(false);
        et_keterangan.setEnabled(false);
        btn_send.setVisibility(View.GONE);
        btn_foto.setVisibility(View.GONE);
    }

    private void loadData() {
        sm_placeholder.startShimmer();
        ReqAgunanData req = new ReqAgunanData(AppUtil.parseIntWithDefault(idApl, 0), AppUtil.parseIntWithDefault(idAgunan, 0), AppUtil.parseIntWithDefault(idCif, 0));

        Call<ParseResponse> call = apiClientAdapter.getApiInterface().inquiryAgunanDeposito(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                sm_placeholder.stopShimmer();
                sm_placeholder.setVisibility(View.GONE);
                ll_content.setVisibility(View.VISIBLE);
                try {
                    if (response.isSuccessful()){
                        if (response.body().getStatus().equalsIgnoreCase("00")){
                            Gson gson = new Gson();
                            String listDataString = response.body().getData().toString();
                            dataAgunan= gson.fromJson(listDataString, AgunanDeposito.class);

                            et_tanggalpemeriksaan.setText(AppUtil.parseTanggalGeneral(dataAgunan.getTglPemeriksaan(), "ddMMyyyy", "dd-MM-yyyy"));
                            et_jenisdeposito.setText(dataAgunan.getJenisDeposito());
                            et_namapemilik.setText(dataAgunan.getNamaPemilik());
                            et_alamatpemilik.setText(dataAgunan.getAlamatPemilik());
                            et_hubungandengannasabah.setText(dataAgunan.getHubungan());
                            et_nomorbilyet.setText(dataAgunan.getNoBilyet());
                            et_bankpenerbit.setText(dataAgunan.getBankPenerbit());
                            et_tanggalpenerbitan.setText(AppUtil.parseTanggalGeneral(dataAgunan.getTanggalPenerbitan(), "ddMMyyyy", "dd-MM-yyyy"));
                            et_tanggaljatuhtempo.setText(AppUtil.parseTanggalGeneral(dataAgunan.getTanggalJatuhTempo(), "ddMMyyyy", "dd-MM-yyyy"));
                            et_aro.setText(dataAgunan.getIsAro());
                            et_nilainominal.setText(dataAgunan.getNilaiNominal());
                            et_nilailikuidasi.setText(dataAgunan.getNilaiTaksasi());
                            et_keterangan.setText(dataAgunan.getKeterangan());
                            bitmap_foto = setLoadImage(iv_foto, dataAgunan.getIdPhoto());
                        }
                        else{
                            AppUtil.notiferror(AgunanDepositoInputActivity_Apr.this, findViewById(android.R.id.content), response.body().getMessage());
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
                        AppUtil.notiferror(AgunanDepositoInputActivity_Apr.this, findViewById(android.R.id.content), error.getMessage());
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
                AppUtil.notiferror(AgunanDepositoInputActivity_Apr.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
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

    public Bitmap setLoadImage(final ImageView iv, int idFoto){
        String url_photo = UriApi.Baseurl.URL + UriApi.foto.urlPhoto + idFoto;

        GlideUrl glideUrl = new GlideUrl(url_photo, new LazyHeaders.Builder()
                .addHeader("Authorization", "Bearer "+appPreferences.getToken())
                .build());


        Glide
                .with(AgunanDepositoInputActivity_Apr.this)
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
}
