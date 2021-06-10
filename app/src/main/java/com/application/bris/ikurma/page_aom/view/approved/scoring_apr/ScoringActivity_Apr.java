package com.application.bris.ikurma.page_aom.view.approved.scoring_apr;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

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
import com.application.bris.ikurma.api.model.request.hotprospek.inquiryScoring;
import com.application.bris.ikurma.api.service.ApiClientAdapter;
import com.application.bris.ikurma.database.AppPreferences;
import com.application.bris.ikurma.page_aom.model.DataScoring;
import com.application.bris.ikurma.util.AppUtil;
import com.application.bris.ikurma.util.KeyValue;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

public class ScoringActivity_Apr extends AppCompatActivity{

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
    }

    @Override
    protected void onResume() {
        super.onResume();
        sm_placeholder.startShimmer();
        setDisable();
        loadData();
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
        et_rpcratio.setEnabled(false);
        et_ratioagunan.setEnabled(false);
        et_currentratio.setEnabled(false);
        et_profitability.setEnabled(false);
        et_reputasiusaha.setEnabled(false);
        et_riwayathubunganbank.setEnabled(false);
        et_lamausaha.setEnabled(false);
        et_prospekusaha.setEnabled(false);
        et_ketergantunganthdsupplier.setEnabled(false);
        et_ketergantunganthdpelanggan.setEnabled(false);
        et_wilayahpemasaran.setEnabled(false);
        et_jenisproduk.setEnabled(false);
        et_jangkawaktu.setEnabled(false);
        et_jenispembiayaan.setEnabled(false);
        btn_send.setVisibility(View.GONE);
    }

    private void loadData() {
        inquiryScoring req = new inquiryScoring(cif, idAplikasi);
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().inquiryScoring(req);
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
                            AppUtil.notiferror(ScoringActivity_Apr.this, findViewById(android.R.id.content), response.body().getMessage());
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
                        AppUtil.notiferror(ScoringActivity_Apr.this, findViewById(android.R.id.content), error.getMessage());
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
                AppUtil.notiferror(ScoringActivity_Apr.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
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
            if (kodeProduct.equalsIgnoreCase("127") || kodeProduct.equalsIgnoreCase("128")){
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
            }

            if (kodeProduct.equalsIgnoreCase("136")){
                et_ratioagunan.setText(KeyValue.getKeyAgunanRatio(String.valueOf(data.getrATIOAGUNAN())));
            }

           if (kodeProduct.equalsIgnoreCase("141") || kodeProduct.equalsIgnoreCase("128")|| kodeProduct.equalsIgnoreCase("320")|| kodeProduct.equalsIgnoreCase("319")){
                et_ratioagunan.setText(KeyValue.getKeyAgunanRatio(String.valueOf(data.getrATIOAGUNAN())));
                et_currentratio.setText(KeyValue.getKeyCurrentRatio(String.valueOf(data.getcURRENTRATIO())));
                et_profitability.setText(KeyValue.getKeyProfitability(String.valueOf(data.getpROFITABILITY())));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

}
