package com.application.bris.ikurma.page_aom.view.hotprospek.datalengkap;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

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
import com.application.bris.ikurma.api.model.request.hotprospek.inquiryDataLengkap;
import com.application.bris.ikurma.api.service.ApiClientAdapter;
import com.application.bris.ikurma.database.AppPreferences;
import com.application.bris.ikurma.database.pojo.DataLengkapPojo;
import com.application.bris.ikurma.page_aom.dialog.CustomDialog;
import com.application.bris.ikurma.page_aom.listener.ConfirmListener;
import com.application.bris.ikurma.page_aom.model.DataLengkap;
import com.application.bris.ikurma.util.AppUtil;
import com.google.gson.Gson;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataLengkapActivity extends AppCompatActivity implements StepperLayout.StepperListener, OnNavigationBarListener, ConfirmListener{
    @BindView(R.id.stepperlayout)
    StepperLayout stepperlayout;
    @BindView(R.id.progressbar_loading)
    RelativeLayout loading;
    @BindView(R.id.btn_back)
    ImageView btn_back;

    private Realm realm;
    private static final String CURRENT_STEP_POSITION_KEY = "position";
    public static String cif;
    public static int uid;
    public static int idAplikasi;
    public static int plafond;

    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    private String dataPribadiString;
    private DataLengkap dataLengkap;
    private int startingStepPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ao_activity_datalengkap);
        ButterKnife.bind(this);
        realm = Realm.getDefaultInstance();
        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);
        cif = getIntent().getStringExtra("cif");
        uid = appPreferences.getUid();
        idAplikasi = getIntent().getIntExtra("idAplikasi", 0);
        plafond = getIntent().getIntExtra("plafond", 0);
        backgroundStatusBar();
        AppUtil.toolbarRegular(this, "Data Lengkap");
        startingStepPosition = savedInstanceState != null ? savedInstanceState.getInt(CURRENT_STEP_POSITION_KEY) : 0;
        loadDataLengkap();

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialog.DialogBackpress(DataLengkapActivity.this);
            }
        });

//        autoInputForTesting();


    }

    private void backgroundStatusBar(){
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    private void loadDataLengkap() {
        loading.setVisibility(View.VISIBLE);
        inquiryDataLengkap req = new inquiryDataLengkap(cif, String.valueOf(idAplikasi));
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().inquiryDataLengkap(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                loading.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()){
                        if(response.body().getStatus().equalsIgnoreCase("00")){
                            Gson gson = new Gson();
                            dataPribadiString = response.body().getData().get("nasabah").toString();
                            dataLengkap = gson.fromJson(dataPribadiString, DataLengkap.class);

                            //isian data lengkap otomatis biar tester ga cape cape isi
                            if(BuildConfig.IS_PRODUCTION==false){
                                autoInputForTesting();
                            }

                            stepperlayout.setAdapter(new DatalengkapStepAdapter(getSupportFragmentManager(), DataLengkapActivity.this, dataLengkap), startingStepPosition );
                            stepperlayout.setListener(DataLengkapActivity.this);
                        }
                        else{
                            AppUtil.notiferror(DataLengkapActivity.this, findViewById(android.R.id.content), response.body().getMessage());
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
                        AppUtil.notiferror(DataLengkapActivity.this, findViewById(android.R.id.content), error.getMessage());
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
                AppUtil.notiferror(DataLengkapActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                }, 2000);
            }
        });
    }

    public void sendData(){
        apiClientAdapter=new ApiClientAdapter(this);
        final DataLengkapPojo data = realm.where(DataLengkapPojo.class).equalTo("idAplikasi", idAplikasi).findFirst();
        DataLengkapPojo datafix = realm.copyFromRealm(data);

        loading.setVisibility(View.VISIBLE);
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().sendDataLengkap(datafix);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                loading.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()){
                        if(response.body().getStatus().equalsIgnoreCase("00")){
                            realm.beginTransaction();
                            data.deleteFromRealm();
                            realm.commitTransaction();
                            CustomDialog.DialogSuccess(DataLengkapActivity.this, "Success!", "Update Data Lengkap sukses", DataLengkapActivity.this);
                        }
                        else{
                            AppUtil.notiferror(DataLengkapActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(DataLengkapActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){

                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                AppUtil.notiferror(DataLengkapActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    @Override
    public void onBackPressed() {
        CustomDialog.DialogBackpress(DataLengkapActivity.this);
    }

    @Override
    public void onCompleted(View completeButton) {
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


        //kalau agama kosong, berarti belum pernah simpan sebelumnya, jadi ambil data otomatis saja
        if(dataLengkap.getAgama()==null||dataLengkap.getAgama().isEmpty()){

            Toast.makeText(DataLengkapActivity.this, "Data lengkap sudah diisi otomatis untuk testing", Toast.LENGTH_SHORT).show();
            dataLengkap.setNpwp("000000000000000");
            dataLengkap.setNamaAlias("Nama Dummy");
            dataLengkap.setNoHp("081212911111");
            dataLengkap.setEmail("email@email.com");
            dataLengkap.setAgama("ISL");
            dataLengkap.setNoKtpPasangan("3208161912880005");
            dataLengkap.setNamaPasangan("Aleksander Barkeeper");
            dataLengkap.setTglLahirPasangan("18031998");
            dataLengkap.setTelpKeluarga("0812120999111");
            dataLengkap.setJlhTanggungan(0);
            dataLengkap.setAlamatDom("JL.PELAJAR TIMUR GG.MAWAR NO.1-A");
            dataLengkap.setAlamatId("JL.PELAJAR TIMUR GG.MAWAR NO.1-A");
            dataLengkap.setAlamatUsaha("JL.PELAJAR TIMUR GG.MAWAR NO.1-A");
            dataLengkap.setBidangUsaha("1110");
            dataLengkap.setExpId("01012100");
            dataLengkap.setKecDom("Kelapa Dua");
            dataLengkap.setKecId("Kelapa Dua");
            dataLengkap.setKecUsaha("Kelapa Dua");
            dataLengkap.setKelDom("Bencongan");
            dataLengkap.setKelId("Bencongan");
            dataLengkap.setKelUsaha("Bencongan");
            dataLengkap.setKeluarga("Nama Keluarga");
            dataLengkap.setKetGelar("S.Kom");
            dataLengkap.setKodePosDom("15810");
            dataLengkap.setKodePosId("15810");
            dataLengkap.setKodePosUsaha("15810");
            dataLengkap.setKotaDom("Kab. Tangerang");
            dataLengkap.setKotaId("Kab. Tangerang");
            dataLengkap.setKotaUsaha("Kab. Tangerang");
            dataLengkap.setLamaMenetap(10);
            dataLengkap.setNamaUsaha("Nama Usaha");
            dataLengkap.setNoTelpUsaha("081212900111");
            dataLengkap.setPendTerakhir("8");
            dataLengkap.setProvDom("BANTEN");
            dataLengkap.setProvId("BANTEN");
            dataLengkap.setProvUsaha("BANTEN");
            dataLengkap.setRtDom("01");
            dataLengkap.setRtId("01");
            dataLengkap.setRtUsaha("01");
            dataLengkap.setRwDom("01");
            dataLengkap.setRwId("01");
            dataLengkap.setRwUsaha("01");
            dataLengkap.setStatusTptTinggal("2");
            dataLengkap.setTglMulaiUsaha("10062015");
            dataLengkap.setTipePendapatan("2");
        }

        }

}
