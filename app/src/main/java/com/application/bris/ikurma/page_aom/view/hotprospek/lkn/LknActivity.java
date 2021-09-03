package com.application.bris.ikurma.page_aom.view.hotprospek.lkn;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.application.bris.ikurma.BuildConfig;
import com.application.bris.ikurma.R;
import com.application.bris.ikurma.api.model.Error;
import com.application.bris.ikurma.api.model.ParseResponse;
import com.application.bris.ikurma.api.model.ParseResponseError;
import com.application.bris.ikurma.api.model.request.hotprospek.inquiryLkn;
import com.application.bris.ikurma.api.service.ApiClientAdapter;
import com.application.bris.ikurma.database.AppPreferences;
import com.application.bris.ikurma.database.pojo.LknPojo;
import com.application.bris.ikurma.page_aom.dialog.CustomDialog;
import com.application.bris.ikurma.page_aom.listener.ConfirmListener;
import com.application.bris.ikurma.page_aom.model.DataLkn;
import com.application.bris.ikurma.page_aom.view.hotprospek.datalengkap.DataLengkapActivity;
import com.application.bris.ikurma.page_aom.view.hotprospek.datalengkap.OnNavigationBarListener;
import com.application.bris.ikurma.util.AppUtil;
import com.google.gson.Gson;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LknActivity extends AppCompatActivity implements StepperLayout.StepperListener, OnNavigationBarListener, ConfirmListener{
    @BindView(R.id.stepperlayout)
    StepperLayout stepperlayout;
    @BindView(R.id.progressbar_loading)
    RelativeLayout loading;
    @BindView(R.id.btn_back)
    ImageView btn_back;
    private static final String CURRENT_STEP_POSITION_KEY = "position";

    private Realm realm;
    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    private String dataLknString;
    private DataLkn data;
    private int startingStepPosition;

    //DATA PRIBADI
    public static String val_NamaAlias ="";
    public static String val_NoKtpPasangan ="";
    public static String val_StatusNikah ="";
    public static String val_NoHp ="";
    public static String val_NamaNasabah ="";
    public static String val_Npwp ="";
    public static String val_PendTerakhir ="";
    public static String val_KetGelar ="";
    public static String val_KetAgama ="";
    public static String val_Agama ="";
    public static String val_NamaIbu ="";
    public static String val_NamaPasangan ="";
    public static String val_Email ="";
    public static String val_TelpKeluarga ="";
    public static String val_ExpId ="";
    public static String val_TglLahirPasangan ="";
    public static String val_NoKtp ="";
    public static int val_JlhTanggungan = 0;
    public static String val_TglLahir ="";
    public static String val_Keluarga ="";
    public static String val_TptLahir ="";
    public static String val_TipePendapatan ="";
    public static String val_Jenkel ="";

    //DATA ALAMAT
    public static String val_AlamatId ="";
    public static String val_RtId ="";
    public static String val_RwId ="";
    public static String val_ProvId ="";
    public static String val_KotaId ="";
    public static String val_KecId ="";
    public static String val_KelId ="";
    public static String val_KodePosId ="";
    public static String val_StatusTptTinggal ="";
    public static int val_LamaMenetap = 0;
    public static String val_AlamatDom ="";
    public static String val_RtDom ="";
    public static String val_RwDom ="";
    public static String val_ProvDom ="";
    public static String val_KotaDom ="";
    public static String val_KecDom ="";
    public static String val_KelDom ="";
    public static String val_KodePosDom ="";

    public static String cif;
    public static int idAplikasi;
    public static String kodeProduct;
    public static String namaProduct;
    public static int idTujuanPembiayaan;
    public static String tujuanPembiayaan;
    public static int plafond;
    public static int jw;

    public static String UUIDR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ao_activity_lkn);
        ButterKnife.bind(this);
        realm = Realm.getDefaultInstance();
        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);
        UUIDR = UUID.randomUUID().toString();

        cif = getIntent().getStringExtra("cif");
        if(cif.equalsIgnoreCase("0")){
            cif="";
        }

        idAplikasi = getIntent().getIntExtra("idAplikasi", 0);
        kodeProduct  = getIntent().getStringExtra("kodeProduct");
        namaProduct = getIntent().getStringExtra("namaProduct");
        idTujuanPembiayaan = getIntent().getIntExtra("idTujuanPembiayaan", 0);
        tujuanPembiayaan = getIntent().getStringExtra("tujuanPembiayaan");
        plafond = getIntent().getIntExtra("plafond", 0);
        jw = getIntent().getIntExtra("jw", 0);
        backgroundStatusBar();
        AppUtil.toolbarRegular(this, "LKN");
        startingStepPosition = savedInstanceState != null ? savedInstanceState.getInt(CURRENT_STEP_POSITION_KEY) : 0;
        loadData();

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialog.DialogBackpress(LknActivity.this);
            }
        });

        Log.d("ID TUJUAN LKN", String.valueOf(idTujuanPembiayaan));
    }

    private void backgroundStatusBar(){
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    private void loadData() {
        loading.setVisibility(View.VISIBLE);
        inquiryLkn req = new inquiryLkn(String.valueOf(idAplikasi), cif);
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().inquiryLKN(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                loading.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()){
                        if(response.body().getStatus().equalsIgnoreCase("00")){
                            Gson gson = new Gson();
                            dataLknString = response.body().getData().toString();
                            data = gson.fromJson(dataLknString, DataLkn.class);

                            stepperlayout.setAdapter(new LknStepAdapter(getSupportFragmentManager(), LknActivity.this, data), startingStepPosition );
                            stepperlayout.setListener(LknActivity.this);
                        }
                        else{
                            AppUtil.notiferror(LknActivity.this, findViewById(android.R.id.content), response.body().getMessage());
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
                        AppUtil.notiferror(LknActivity.this, findViewById(android.R.id.content), error.getMessage());
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
                loading.setVisibility(View.GONE);
                AppUtil.notiferror(LknActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                }, 2000);
            }
        });
    }

    public void sendData()
    {
        loading.setVisibility(View.VISIBLE);

        final LknPojo dataR = realm.where(LknPojo.class).equalTo("uuid", LknActivity.UUIDR).findFirst();
        LknPojo datafix = realm.copyFromRealm(dataR);

        Call<ParseResponse> call = apiClientAdapter.getApiInterface().sendLkn(datafix);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                loading.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()){
                        if(response.body().getStatus().equalsIgnoreCase("00")){
                            realm.beginTransaction();
                            dataR.deleteFromRealm();
                            realm.commitTransaction();
                            CustomDialog.DialogSuccess(LknActivity.this, "Success!", "Update Data LKN sukses", LknActivity.this);
                        }
                        else{
                            AppUtil.notiferror(LknActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(LknActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                AppUtil.notiferror(LknActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    public long parseLamaUsaha(){
        try {
            String val = data.getlAMABEKERJA();
            String d1 = val.substring(0, 2);
            String d2 = val.substring(2, 4);

            if (!d2.equalsIgnoreCase("00")) {
                return AppUtil.parseLongWithDefault(d2, 0);
            } else if (d2.equalsIgnoreCase("00") && !d1.equalsIgnoreCase("00")) {
                return AppUtil.parseLongWithDefault(d1, 0);
            } else {
                return 0;
            }
        }
        catch (Exception e){
            return 0;
        }
    }

    @Override
    public void onBackPressed() {
        CustomDialog.DialogBackpress(LknActivity.this);
    }

    @Override
    public void onCompleted(View completeButton) {

        if (FragmentRekomendasiPembiayaan.flagCekRekomendasi)
            sendData();
    }

    @Override
    public void onError(VerificationError verificationError) {
    }

    @Override
    public void onStepSelected(int newStepPosition) {
    }

    @Override
    public void onReturn() {
        finish();
    }

    @Override
    public void onChangeEndButtonsEnabled(boolean enabled) {
        stepperlayout.setNextButtonVerificationFailed(!enabled);
        stepperlayout.setCompleteButtonVerificationFailed(!enabled);
    }

    @Override
    public void success(boolean val) {
        if(val){
            finish();
        }
    }

    @Override
    public void confirm(boolean val) {

    }

    private void autoInputForTesting(){


        //BELOM DIPAKE karena auto inputnya ditaruh di fragmentnya bukan di activitynya

        Toast.makeText(LknActivity.this, "Isi data otomatis untuk testing", Toast.LENGTH_SHORT).show();
        data.setpERPUTARANPIUTANG(1l);
        data.setgPM("90.0");
        data.sethARGASEWA("0");
        data.setjENISTEMPATUSAHA("Los/Lapak/Dasaran");
        data.setpENGHASILANLAINNYA("200000");
        data.setbIAYAPENDIDIKAN("300000");
        data.setfIDPHOTODALAM(100);
        data.setpENGELUARANUSAHA("17300000");
        data.setbIAYATELEPONRT("100000");
        data.setfIDPHOTODEPAN(100);
        data.settOTALPENGELUARANRT("3000000");
        data.settOTALPENGHASILAN("82900000");
        data.settOTALPEMBIAYAAN("25000000");
        data.setiNVENTORY("10000000");
        data.setsTATUSPERMOHONAN("Baru");
        data.setsTATUSTEMPATUSAHA("Milik Keluarga");
        data.setpENDAPATANUSAHA("100000000");
        data.setsISAPENGHASILAN("79800000");
        data.setjENISANGSURAN("1");
        data.setjWKONSUMTIF("0");
        data.setbIAYATRANSPORTASI("200000");
        data.setjARAKLOKASI(4l);
        data.setpERPUTARANPERSEDIAAN(30l);
        data.setpEMBIAYAANKONSUMTIF("0");
        data.setbIAYATELEPONLISTRIK("100000");
        data.setkEUNTUNGANUSAHA("82700000");
        data.setjWPRODUKTIF2("0");
        data.setjENISUSAHA("Pakaian/Alas Kaki");
        data.setjWTAKEOVER("0");
        data.setuTANGDAGANG("2000000");
        data.setpERPUTARANUTANG(6l);
        data.setlOKASIUSAHA("Pasar Utama}");
        data.setdOHINVENTORY(6l);
        data.setpERPUTARANUTANG(6l);



    }
}
