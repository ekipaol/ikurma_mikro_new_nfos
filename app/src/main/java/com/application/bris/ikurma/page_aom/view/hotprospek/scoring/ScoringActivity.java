package com.application.bris.ikurma.page_aom.view.hotprospek.scoring;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.application.bris.ikurma.R;
import com.application.bris.ikurma.api.model.Error;
import com.application.bris.ikurma.api.model.ParseResponse;
import com.application.bris.ikurma.api.model.ParseResponseError;
import com.application.bris.ikurma.api.model.request.hotprospek.inputScoring;
import com.application.bris.ikurma.api.model.request.hotprospek.inquiryScoring;
import com.application.bris.ikurma.api.service.ApiClientAdapter;
import com.application.bris.ikurma.database.AppPreferences;
import com.application.bris.ikurma.page_aom.dialog.CustomDialog;
import com.application.bris.ikurma.page_aom.dialog.DialogGenericDataFromService;
import com.application.bris.ikurma.page_aom.dialog.DialogKeyValue;
import com.application.bris.ikurma.page_aom.listener.ConfirmListener;
import com.application.bris.ikurma.page_aom.listener.GenericListenerOnSelect;
import com.application.bris.ikurma.page_aom.listener.KeyValueListener;
import com.application.bris.ikurma.page_aom.model.DataScoring;
import com.application.bris.ikurma.page_aom.model.MDataPembiayaan;
import com.application.bris.ikurma.page_aom.model.MGenericModel;
import com.application.bris.ikurma.page_aom.model.keyvalue;
import com.application.bris.ikurma.page_aom.view.hotprospek.HotprospekActivity;
import com.application.bris.ikurma.util.AppUtil;
import com.application.bris.ikurma.util.KeyValue;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

public class ScoringActivity extends AppCompatActivity implements KeyValueListener, ConfirmListener, GenericListenerOnSelect {

    @BindView(R.id.ll_content)
    LinearLayout ll_content;
    @BindView(R.id.sm_placeholder)
    ShimmerFrameLayout sm_placeholder;
    @BindView(R.id.tf_rpcratio)
    TextFieldBoxes tf_rpcratio;
    @BindView(R.id.et_rpcratio)
    EditText et_rpcratio;
    @BindView(R.id.tf_ratioagunan)
    TextFieldBoxes tf_ratioagunan;
    @BindView(R.id.et_ratioagunan)
    EditText et_ratioagunan;
    @BindView(R.id.tf_currentratio)
    TextFieldBoxes tf_currentratio;
    @BindView(R.id.et_currentratio)
    EditText et_currentratio;
    @BindView(R.id.tf_profitability)
    TextFieldBoxes tf_profitability;
    @BindView(R.id.et_profitability)
    EditText et_profitability;
    @BindView(R.id.tf_reputasiusaha)
    TextFieldBoxes tf_reputasiusaha;
    @BindView(R.id.et_reputasiusaha)
    EditText et_reputasiusaha;
    @BindView(R.id.tf_riwayathubunganbank)
    TextFieldBoxes tf_riwayathubunganbank;
    @BindView(R.id.et_riwayathubunganbank)
    EditText et_riwayathubunganbank;
    @BindView(R.id.tf_lamausaha)
    TextFieldBoxes tf_lamausaha;
    @BindView(R.id.et_lamausaha)
    EditText et_lamausaha;
    @BindView(R.id.tf_prospekusaha)
    TextFieldBoxes tf_prospekusaha;
    @BindView(R.id.et_prospekusaha)
    EditText et_prospekusaha;
    @BindView(R.id.tf_ketergantunganthdsupplier)
    TextFieldBoxes tf_ketergantunganthdsupplier;
    @BindView(R.id.et_ketergantunganthdsupplier)
    EditText et_ketergantunganthdsupplier;
    @BindView(R.id.tf_ketergantunganthdpelanggan)
    TextFieldBoxes tf_ketergantunganthdpelanggan;
    @BindView(R.id.et_ketergantunganthdpelanggan)
    EditText et_ketergantunganthdpelanggan;
    @BindView(R.id.tf_wilayahpemasaran)
    TextFieldBoxes tf_wilayahpemasaran;
    @BindView(R.id.et_wilayahpemasaran)
    EditText et_wilayahpemasaran;
    @BindView(R.id.tf_jenisproduk)
    TextFieldBoxes tf_jenisproduk;
    @BindView(R.id.et_jenisproduk)
    EditText et_jenisproduk;
    @BindView(R.id.tf_jangkawaktu)
    TextFieldBoxes tf_jangkawaktu;
    @BindView(R.id.et_jangkawaktu)
    EditText et_jangkawaktu;
    @BindView(R.id.tf_jenispembiayaan)
    TextFieldBoxes tf_jenispembiayaan;
    @BindView(R.id.et_jenispembiayaan)
    EditText et_jenispembiayaan;

    @BindView(R.id.btn_send)
    Button btn_send;
    @BindView(R.id.ll_contenthasil)
    LinearLayout ll_contenthasil;
    @BindView(R.id.ll_hasil)
    LinearLayout ll_hasil;
    @BindView(R.id.tv_titlehasil)
    TextView tv_titlehasil;
    @BindView(R.id.tv_kethasil)
    TextView tv_kethasil;
    @BindView(R.id.progressbar_loading)
    RelativeLayout loading;


    private int idAplikasi;
    private int cif;
    private String kodeProduct;
    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;

    private DataScoring data;


    private MDataPembiayaan dataPbySebelumPutusan;

    private String dtKatLBUString, dtJenisPenggunaanLbuString, dtSifatPbyLBUString, dtSifatPbyString,dtBidangUsahaString,
            dtJenisPenggunaanString, dtJenisKreditLbuString, dtHubDebiturString, dataPbySebelumPutusanString;

    private long val_ketergantunganPelanggan;
    private long val_jenisPembiayaan;
    private long val_lamaUsaha;
    private long val_deviasiMikro;
    private long val_jenisProduk;
    private long val_hubunganBank;
    private long val_jangkaWaktu;
    private long val_wilayahPemasaran;
    private long val_rpcRatio;
    private long val_ratioAgunan = 0;
    private long val_currentRatio = 0;
    private long val_profitability = 0;
    private long val_reputasiUsaha;
    private long val_ketergantunganSupplier;
    private long val_prospekUsaha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ao_activity_hotprospek_scoring);
        ButterKnife.bind(this);
        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);
        idAplikasi = getIntent().getIntExtra("idAplikasi", 0);
        cif = getIntent().getIntExtra("cif", 0);
        kodeProduct = getIntent().getStringExtra("kodeProduct");
        backgroundStatusBar();
        AppUtil.toolbarRegular(this, getString(R.string.submenu_hotprospek_scoring));

        if (kodeProduct.equalsIgnoreCase("136")){
            tf_ratioagunan.setVisibility(View.VISIBLE);
        }
        if (kodeProduct.equalsIgnoreCase("141") || kodeProduct.equalsIgnoreCase("128")||kodeProduct.equalsIgnoreCase("319") || kodeProduct.equalsIgnoreCase("320")){
            tf_ratioagunan.setVisibility(View.VISIBLE);
            tf_currentratio.setVisibility(View.VISIBLE);
            tf_profitability.setVisibility(View.VISIBLE);
        }

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateForm())
                    sendData();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        sm_placeholder.startShimmer();
        loadData();
        onSelectDialog();
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

    private void loadData() {
        inquiryScoring req = new inquiryScoring(cif, idAplikasi);
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().hitungScoring(req);
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
                            data = gson.fromJson(response.body().getData().get("dataScoring").toString(), DataScoring.class);
                            setData();
                        }
                        else{
                            AppUtil.notiferror(ScoringActivity.this, findViewById(android.R.id.content), response.body().getMessage());
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
                        AppUtil.notiferror(ScoringActivity.this, findViewById(android.R.id.content), error.getMessage());
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
                AppUtil.notiferror(ScoringActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                }, 2000);
            }
        });

    }

    public void setData()
    {
        try {
            et_rpcratio.setText(KeyValue.getKeyRpcRatio(kodeProduct.trim(), String.valueOf(data.getrPCRATIO())));
            et_reputasiusaha.setText(KeyValue.getKeyReputasiUsaha(String.valueOf(data.getrEPUTASIUSAHA())));
            et_riwayathubunganbank.setText(KeyValue.getKeyRiwayatHubdgnBank(String.valueOf(data.gethUBUNGANBANK())));
            if (kodeProduct.equalsIgnoreCase("127") || kodeProduct.equalsIgnoreCase("128")||kodeProduct.equalsIgnoreCase("318") || kodeProduct.equalsIgnoreCase("319")|| kodeProduct.equalsIgnoreCase("320")||kodeProduct.equalsIgnoreCase("840") || kodeProduct.equalsIgnoreCase("841")){
                et_lamausaha.setText(KeyValue.getKeyLamaUsahaKur(String.valueOf(data.getlAMAUSAHA())));
            }
            else {
                et_lamausaha.setText(KeyValue.getKeyLamaUsaha(String.valueOf(data.getlAMAUSAHA())));
            }
            et_prospekusaha.setText(KeyValue.getKeyProspekUsaha(String.valueOf(data.getpROSPEKUSAHA())));
            et_ketergantunganthdsupplier.setText(KeyValue.getKeyKetergantunganSupplierdanPelanggan(String.valueOf(data.getkETERGANTUNGANSUPPLIER())));
            et_ketergantunganthdpelanggan.setText(KeyValue.getKeyKetergantunganSupplierdanPelanggan(String.valueOf(data.getkETERGANTUNGANPELANGGAN())));
            et_wilayahpemasaran.setText(KeyValue.getKeyWilayahPemasaran(String.valueOf(data.getwILAYAHPEMASARAN())));
            et_jenisproduk.setText(KeyValue.getKeyJenisProduk(String.valueOf(data.getjENISPRODUK())));
            et_jangkawaktu.setText(KeyValue.getKeyJangkaWaktu(String.valueOf(data.getjANGKAWAKTU())));
            et_jenispembiayaan.setText(KeyValue.getKeyJenisPembiayaan(String.valueOf(data.getjENISPEMBIAYAAN())));

            if (!data.getHasil().toString().isEmpty() || !data.getHasil().toString().equalsIgnoreCase("")){
                ll_contenthasil.setVisibility(View.VISIBLE);
                tv_titlehasil.setText(data.getHasil());
                tv_kethasil.setText(data.getPesan());
                if (data.getHasil().equalsIgnoreCase("high risk")){
                    ll_hasil.setBackgroundResource(R.drawable.shapered);
                }
                else if (data.getHasil().equalsIgnoreCase("medium risk")){
                    ll_hasil.setBackgroundResource(R.drawable.shapeyellow);
                }
                else{
                    ll_hasil.setBackgroundResource(R.drawable.shapegreen);
                }
                val_ketergantunganPelanggan = data.getkETERGANTUNGANPELANGGAN();
                val_jenisPembiayaan = data.getjENISPEMBIAYAAN();
                val_deviasiMikro = data.getdEVIASIMIKRO();
                val_jenisProduk = data.getjENISPRODUK();
                val_hubunganBank = data.gethUBUNGANBANK();
                val_wilayahPemasaran = data.getwILAYAHPEMASARAN();
                val_reputasiUsaha = data.getrEPUTASIUSAHA();
                val_ketergantunganSupplier = data.getkETERGANTUNGANSUPPLIER();
                val_prospekUsaha = data.getpROSPEKUSAHA();
            }


            val_rpcRatio = data.getrPCRATIO();
            if (kodeProduct.equalsIgnoreCase("136")){
                et_ratioagunan.setText(KeyValue.getKeyAgunanRatio(String.valueOf(data.getrATIOAGUNAN())));
                val_ratioAgunan = data.getrATIOAGUNAN();
            }

            if (kodeProduct.equalsIgnoreCase("141") || kodeProduct.equalsIgnoreCase("128")|| kodeProduct.equalsIgnoreCase("320")|| kodeProduct.equalsIgnoreCase("319")){
                et_ratioagunan.setText(KeyValue.getKeyAgunanRatio(String.valueOf(data.getrATIOAGUNAN())));
                et_currentratio.setText(KeyValue.getKeyCurrentRatio(String.valueOf(data.getcURRENTRATIO())));
                et_profitability.setText(KeyValue.getKeyProfitability(String.valueOf(data.getpROFITABILITY())));
                val_ratioAgunan = data.getrATIOAGUNAN();
                if (data.getcURRENTRATIO() != null){
                    val_currentRatio = data.getcURRENTRATIO();
                    val_profitability = data.getpROFITABILITY();
                }
            }
            val_lamaUsaha = data.getlAMAUSAHA();
            val_jangkaWaktu = data.getjANGKAWAKTU();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void onSelectDialog(){


        et_rpcratio.setInputType(InputType.TYPE_NULL);
        et_rpcratio.setFocusable(false);

        et_lamausaha.setInputType(InputType.TYPE_NULL);
        et_lamausaha.setFocusable(false);

        et_jangkawaktu.setInputType(InputType.TYPE_NULL);
        et_jangkawaktu.setFocusable(false);


        et_ratioagunan.setFocusable(false);
        et_ratioagunan.setInputType(InputType.TYPE_NULL);

        //CURRENT RATIO
        et_currentratio.setFocusable(false);
        et_currentratio.setInputType(InputType.TYPE_NULL);
        et_currentratio.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                DialogKeyValue.display(getSupportFragmentManager(), tf_currentratio.getLabelText().toString().trim(), ScoringActivity.this);
            }
        });
        tf_currentratio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogKeyValue.display(getSupportFragmentManager(), tf_currentratio.getLabelText().toString().trim(), ScoringActivity.this);
            }
        });
        tf_currentratio.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogKeyValue.display(getSupportFragmentManager(), tf_currentratio.getLabelText().toString().trim(), ScoringActivity.this);
            }
        });


        //PROFITABILITY
        et_profitability.setFocusable(false);
        et_profitability.setInputType(InputType.TYPE_NULL);
        et_profitability.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                DialogKeyValue.display(getSupportFragmentManager(), tf_profitability.getLabelText().toString().trim(), ScoringActivity.this);
            }
        });
        tf_profitability.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogKeyValue.display(getSupportFragmentManager(), tf_profitability.getLabelText().toString().trim(), ScoringActivity.this);
            }
        });
        tf_profitability.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogKeyValue.display(getSupportFragmentManager(), tf_profitability.getLabelText().toString().trim(), ScoringActivity.this);
            }
        });



        //REPUTASI USAHA
        et_reputasiusaha.setFocusable(false);
        et_reputasiusaha.setInputType(InputType.TYPE_NULL);
        et_reputasiusaha.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                DialogKeyValue.display(getSupportFragmentManager(), tf_reputasiusaha.getLabelText().toString().trim(), ScoringActivity.this);
            }
        });
        tf_reputasiusaha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogKeyValue.display(getSupportFragmentManager(), tf_reputasiusaha.getLabelText().toString().trim(), ScoringActivity.this);
            }
        });
        tf_reputasiusaha.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogKeyValue.display(getSupportFragmentManager(), tf_reputasiusaha.getLabelText().toString().trim(), ScoringActivity.this);
            }
        });



        //RIWAYAT HUBUNGAN USAHA
        et_riwayathubunganbank.setFocusable(false);
        et_riwayathubunganbank.setInputType(InputType.TYPE_NULL);
        et_riwayathubunganbank.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                DialogKeyValue.display(getSupportFragmentManager(), tf_riwayathubunganbank.getLabelText().toString().trim(), ScoringActivity.this);
            }
        });
        tf_riwayathubunganbank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogKeyValue.display(getSupportFragmentManager(), tf_riwayathubunganbank.getLabelText().toString().trim(), ScoringActivity.this);
            }
        });
        tf_riwayathubunganbank.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogKeyValue.display(getSupportFragmentManager(), tf_riwayathubunganbank.getLabelText().toString().trim(), ScoringActivity.this);
            }
        });



        //PROSPEK USAHA
        et_prospekusaha.setFocusable(false);
        et_prospekusaha.setInputType(InputType.TYPE_NULL);
        et_prospekusaha.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                DialogKeyValue.display(getSupportFragmentManager(), tf_prospekusaha.getLabelText().toString().trim(), ScoringActivity.this);
            }
        });
        tf_prospekusaha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogKeyValue.display(getSupportFragmentManager(), tf_prospekusaha.getLabelText().toString().trim(), ScoringActivity.this);
            }
        });
        tf_prospekusaha.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogKeyValue.display(getSupportFragmentManager(), tf_prospekusaha.getLabelText().toString().trim(), ScoringActivity.this);
            }
        });



        //KETERGANTUNGAN TERHADAP SUPPLIER
        et_ketergantunganthdsupplier.setFocusable(false);
        et_ketergantunganthdsupplier.setInputType(InputType.TYPE_NULL);
        et_ketergantunganthdsupplier.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                DialogKeyValue.display(getSupportFragmentManager(), tf_ketergantunganthdsupplier.getLabelText().toString().trim(), ScoringActivity.this);
            }
        });
        tf_ketergantunganthdsupplier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogKeyValue.display(getSupportFragmentManager(), tf_ketergantunganthdsupplier.getLabelText().toString().trim(), ScoringActivity.this);
            }
        });
        tf_ketergantunganthdsupplier.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogKeyValue.display(getSupportFragmentManager(), tf_ketergantunganthdsupplier.getLabelText().toString().trim(), ScoringActivity.this);
            }
        });



        //KETERGANTUNGAN TERHADAP PELANGGAN
        et_ketergantunganthdpelanggan.setFocusable(false);
        et_ketergantunganthdpelanggan.setInputType(InputType.TYPE_NULL);
        et_ketergantunganthdpelanggan.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                DialogKeyValue.display(getSupportFragmentManager(), tf_ketergantunganthdpelanggan.getLabelText().toString().trim(), ScoringActivity.this);
            }
        });
        tf_ketergantunganthdpelanggan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogKeyValue.display(getSupportFragmentManager(), tf_ketergantunganthdpelanggan.getLabelText().toString().trim(), ScoringActivity.this);
            }
        });
        tf_ketergantunganthdpelanggan.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogKeyValue.display(getSupportFragmentManager(), tf_ketergantunganthdpelanggan.getLabelText().toString().trim(), ScoringActivity.this);
            }
        });



        //WILAYAH PEMASARAN
        et_wilayahpemasaran.setFocusable(false);
        et_wilayahpemasaran.setInputType(InputType.TYPE_NULL);
        et_wilayahpemasaran.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                DialogKeyValue.display(getSupportFragmentManager(), tf_wilayahpemasaran.getLabelText().toString().trim(), ScoringActivity.this);
            }
        });
        tf_wilayahpemasaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogKeyValue.display(getSupportFragmentManager(), tf_wilayahpemasaran.getLabelText().toString().trim(), ScoringActivity.this);
            }
        });
        tf_wilayahpemasaran.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogKeyValue.display(getSupportFragmentManager(), tf_wilayahpemasaran.getLabelText().toString().trim(), ScoringActivity.this);
            }
        });



        //JENIS PRODUK
        et_jenisproduk.setFocusable(false);
        et_jenisproduk.setInputType(InputType.TYPE_NULL);
        et_jenisproduk.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                DialogKeyValue.display(getSupportFragmentManager(), tf_jenisproduk.getLabelText().toString().trim(), ScoringActivity.this);
            }
        });
        tf_jenisproduk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogKeyValue.display(getSupportFragmentManager(), tf_jenisproduk.getLabelText().toString().trim(), ScoringActivity.this);
            }
        });
        tf_jenisproduk.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogKeyValue.display(getSupportFragmentManager(), tf_jenisproduk.getLabelText().toString().trim(), ScoringActivity.this);
            }
        });





        //khusus user amanah, hanya ambil scoring murabahah saja
        if(appPreferences.getCbAmanah().equalsIgnoreCase("true")){
            final List<MGenericModel> genericJenisPembiayaan=new ArrayList<>();
            genericJenisPembiayaan.add(new MGenericModel("3","Murabahah"));


            et_jenispembiayaan.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    DialogGenericDataFromService.display(getSupportFragmentManager(),tf_jenispembiayaan.getLabelText(),genericJenisPembiayaan,ScoringActivity.this);
                }
            });
            tf_jenispembiayaan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogGenericDataFromService.display(getSupportFragmentManager(),tf_jenispembiayaan.getLabelText(),genericJenisPembiayaan,ScoringActivity.this);
                }
            });
            tf_jenispembiayaan.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogGenericDataFromService.display(getSupportFragmentManager(),tf_jenispembiayaan.getLabelText(),genericJenisPembiayaan,ScoringActivity.this);
                }
            });
        }
        else{
            //JENIS PRODUK
            et_jenispembiayaan.setFocusable(false);
            et_jenispembiayaan.setInputType(InputType.TYPE_NULL);
            et_jenispembiayaan.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    DialogKeyValue.displayWithType(getSupportFragmentManager(), tf_jenispembiayaan.getLabelText().toString().trim(), ScoringActivity.this,kodeProduct);
                }
            });
            tf_jenispembiayaan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogKeyValue.display(getSupportFragmentManager(), tf_jenispembiayaan.getLabelText().toString().trim(), ScoringActivity.this);
                }
            });
            tf_jenispembiayaan.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogKeyValue.display(getSupportFragmentManager(), tf_jenispembiayaan.getLabelText().toString().trim(), ScoringActivity.this);
                }
            });
        }
    }


    public void sendData(){
        loading.setVisibility(View.VISIBLE);
        inputScoring req = new inputScoring(cif, idAplikasi, appPreferences.getUid(), appPreferences.getKodeCabang(), val_rpcRatio, val_ratioAgunan, val_currentRatio, val_profitability, val_reputasiUsaha, val_hubunganBank, val_lamaUsaha, val_prospekUsaha, val_ketergantunganSupplier, val_ketergantunganPelanggan, val_wilayahPemasaran, val_jenisProduk, val_jangkaWaktu, val_jenisPembiayaan);
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().sendScoring(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                loading.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()) {
                        if (response.body().getStatus().equalsIgnoreCase("00")){

                            Gson gson = new Gson();
                            DataScoring dataHasil = gson.fromJson(response.body().getData().toString(), DataScoring.class);

                            if (!dataHasil.getHasil().equalsIgnoreCase("") || !dataHasil.getHasil().isEmpty()){
                                ll_contenthasil.setVisibility(View.VISIBLE);
                                tv_titlehasil.setText(dataHasil.getHasil());
                                tv_kethasil.setText(dataHasil.getPesan());
                                if (dataHasil.getHasil().equalsIgnoreCase("high risk")){
                                    ll_hasil.setBackgroundResource(R.drawable.shapered);
                                    CustomDialog.DialogError(ScoringActivity.this, "Upss Sorry", "Hasil Scoring Nasabah "+dataHasil.getHasil()+". Anda tidak diijinkan untuk melanjutkan pengisian aplikasi ini!", ScoringActivity.this);
                                }
                                else if (dataHasil.getHasil().equalsIgnoreCase("medium risk")){
                                    ll_hasil.setBackgroundResource(R.drawable.shapeyellow);
                                    AppUtil.notifsuccess(ScoringActivity.this, findViewById(android.R.id.content), "Hasil Scoring Nasabah "+dataHasil.getHasil());
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            finish();
                                        }
                                    }, 2000);
                                }
                                else{
                                    ll_hasil.setBackgroundResource(R.drawable.shapegreen);
                                    AppUtil.notifsuccess(ScoringActivity.this, findViewById(android.R.id.content), "Hasil Scoring Nasabah "+dataHasil.getHasil());
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            finish();
                                        }
                                    }, 2000);
                                }
                            }
                        }
                        else{
                            AppUtil.notiferror(ScoringActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(ScoringActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                AppUtil.notiferror(ScoringActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    public boolean validateForm(){
        if (et_rpcratio.getText().toString().isEmpty() || et_rpcratio.getText().toString().equalsIgnoreCase("")){
            tf_rpcratio.setError(tf_rpcratio.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return false;
        }
        else if (tf_currentratio.getVisibility() == View.VISIBLE && et_currentratio.getText().toString().trim().isEmpty()){
            tf_currentratio.setError(tf_currentratio.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return false;
        }
        else if (tf_profitability.getVisibility() == View.VISIBLE && et_profitability.getText().toString().trim().isEmpty()){
            tf_profitability.setError(tf_profitability.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return false;
        }
        else if (et_reputasiusaha.getText().toString().isEmpty() || et_reputasiusaha.getText().toString().equalsIgnoreCase("")){
            tf_reputasiusaha.setError(tf_reputasiusaha.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return false;
        }
        else if (et_riwayathubunganbank.getText().toString().isEmpty() || et_riwayathubunganbank.getText().toString().equalsIgnoreCase("")){
            tf_riwayathubunganbank.setError(tf_riwayathubunganbank.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return false;
        }
        else if (et_lamausaha.getText().toString().isEmpty() || et_lamausaha.getText().toString().equalsIgnoreCase("")){
            tf_lamausaha.setError(tf_lamausaha.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return false;
        }
        else if (et_prospekusaha.getText().toString().isEmpty() || et_prospekusaha.getText().toString().equalsIgnoreCase("")){
            tf_prospekusaha.setError(tf_prospekusaha.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return false;
        }
        else if (et_ketergantunganthdsupplier.getText().toString().isEmpty() || et_ketergantunganthdsupplier.getText().toString().equalsIgnoreCase("")){
            tf_ketergantunganthdsupplier.setError(tf_ketergantunganthdsupplier.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return false;
        }
        else if (et_ketergantunganthdpelanggan.getText().toString().isEmpty() || et_ketergantunganthdpelanggan.getText().toString().equalsIgnoreCase("")){
            tf_ketergantunganthdpelanggan.setError(tf_ketergantunganthdpelanggan.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return false;
        }
        else if (et_wilayahpemasaran.getText().toString().isEmpty() || et_wilayahpemasaran.getText().toString().equalsIgnoreCase("")){
            tf_wilayahpemasaran.setError(tf_wilayahpemasaran.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return false;
        }
        else if (et_jenisproduk.getText().toString().isEmpty() || et_jenisproduk.getText().toString().equalsIgnoreCase("")){
            tf_jenisproduk.setError(tf_jenisproduk.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return false;
        }
        else if (et_jangkawaktu.getText().toString().isEmpty() || et_jangkawaktu.getText().toString().equalsIgnoreCase("")){
            tf_jangkawaktu.setError(tf_jangkawaktu.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return false;
        }
        else if (et_jenispembiayaan.getText().toString().isEmpty() || et_jenispembiayaan.getText().toString().equalsIgnoreCase("")){
            tf_jenispembiayaan.setError(tf_jenispembiayaan.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return false;
        }
        return true;
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
    public void onKeyValueSelect(String title, keyvalue data) {
        if (title.equalsIgnoreCase(tf_reputasiusaha.getLabelText().toString().trim())){
            et_reputasiusaha.setText(data.getName());
            val_reputasiUsaha = AppUtil.parseLongWithDefault(data.getValue(), 0);
        }
        else if (title.equalsIgnoreCase(tf_riwayathubunganbank.getLabelText().toString().trim())){
            et_riwayathubunganbank.setText(data.getName());
            val_hubunganBank = AppUtil.parseLongWithDefault(data.getValue(), 0);
        }

        else if (title.equalsIgnoreCase(tf_prospekusaha.getLabelText().toString().trim())){
            et_prospekusaha.setText(data.getName());
            val_prospekUsaha = AppUtil.parseLongWithDefault(data.getValue(), 0);
        }


        else if (title.equalsIgnoreCase(tf_ketergantunganthdsupplier.getLabelText().toString().trim())){
            et_ketergantunganthdsupplier.setText(data.getName());
            val_ketergantunganSupplier = AppUtil.parseLongWithDefault(data.getValue(), 0);
        }

        else if (title.equalsIgnoreCase(tf_ketergantunganthdpelanggan.getLabelText().toString().trim())){
            et_ketergantunganthdpelanggan.setText(data.getName());
            val_ketergantunganPelanggan = AppUtil.parseLongWithDefault(data.getValue(), 0);
        }

        else if (title.equalsIgnoreCase(tf_wilayahpemasaran.getLabelText().toString().trim())){
            et_wilayahpemasaran.setText(data.getName());
            val_wilayahPemasaran = AppUtil.parseLongWithDefault(data.getValue(), 0);
        }

        else if (title.equalsIgnoreCase(tf_jenisproduk.getLabelText().toString().trim())){
            et_jenisproduk.setText(data.getName());
            val_jenisProduk = AppUtil.parseLongWithDefault(data.getValue(), 0);
        }
        else if (title.equalsIgnoreCase(tf_jenispembiayaan.getLabelText().toString().trim())){
            et_jenispembiayaan.setText(data.getName());
            val_jenisPembiayaan = AppUtil.parseLongWithDefault(data.getValue(), 0);
        }

        else if (title.equalsIgnoreCase(tf_currentratio.getLabelText().toString().trim())){
            et_currentratio.setText(data.getName());
            val_currentRatio = AppUtil.parseLongWithDefault(data.getValue(), 0);
        }

        else if (title.equalsIgnoreCase(tf_profitability.getLabelText().toString().trim())){
            et_profitability.setText(data.getName());
            val_profitability = AppUtil.parseLongWithDefault(data.getValue(), 0);
        }
    }

    @Override
    public void onSelect(String title, MGenericModel data) {
        if(title.equalsIgnoreCase(tf_jenispembiayaan.getLabelText())){
            et_jenispembiayaan.setText(data.getDESC());
            val_jenisPembiayaan=Integer.parseInt(data.getID());
        }
    }
}
