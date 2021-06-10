package com.application.bris.ikurma.page_aom.view.hotprospek_multifaedahmikro.datalengkap.purna;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.application.bris.ikurma.R;
import com.application.bris.ikurma.api.model.Error;
import com.application.bris.ikurma.api.model.ParseResponse;
import com.application.bris.ikurma.api.model.ParseResponseError;
import com.application.bris.ikurma.api.model.request.hotprospek.inquiryDataLengkap;
import com.application.bris.ikurma.api.service.ApiClientAdapter;
import com.application.bris.ikurma.database.AppPreferences;
import com.application.bris.ikurma.database.pojo.DataGlobalHotprospekPojo;
import com.application.bris.ikurma.database.pojo.KmgDataLengkapPojo;
import com.application.bris.ikurma.page_aom.dialog.CustomDialog;
import com.application.bris.ikurma.page_aom.listener.ConfirmListener;
import com.application.bris.ikurma.page_aom.model.DataLengkapKmg;
import com.application.bris.ikurma.page_aom.view.hotprospek.datalengkap.OnNavigationBarListener;
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

public class DataLengkapActivityPurna extends AppCompatActivity implements StepperLayout.StepperListener, OnNavigationBarListener, ConfirmListener{
    @BindView(R.id.stepperlayout)
    StepperLayout stepperlayout;
    @BindView(R.id.progressbar_loading)
    RelativeLayout loading;
    @BindView(R.id.btn_back)
    ImageView btn_back;

    private Realm realm;
    private static final String CURRENT_STEP_POSITION_KEY = "position";
    public static String cif,approved, gimmick;
    public static int uid;
    public static int idAplikasi;
    public static int plafond;

    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    private String dataPribadiString;
    private DataLengkapKmg dataLengkap;
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
        approved = getIntent().getStringExtra("approved");
        uid = appPreferences.getUid();
        idAplikasi = getIntent().getIntExtra("idAplikasi", 0);
        plafond = getIntent().getIntExtra("plafond", 0);
        gimmick = getIntent().getStringExtra("gimmick");
        backgroundStatusBar();
        AppUtil.toolbarRegular(this, "Data Lengkap");
        startingStepPosition = savedInstanceState != null ? savedInstanceState.getInt(CURRENT_STEP_POSITION_KEY) : 0;
        loadDataLengkap();

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(approved.equalsIgnoreCase("no")){
                    CustomDialog.DialogBackpress(DataLengkapActivityPurna.this);
                }
                else{
                    finish();
                }

            }
        });
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


        //pantekan
//        req.setCif("81272");
//        req.setIdAplikasi("100997");

        Call<ParseResponse> call = apiClientAdapter.getApiInterface().inquiryDataLengkapKmg(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                loading.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()){
                        if(response.body().getStatus().equalsIgnoreCase("00")){
                            Gson gson = new Gson();
                            dataPribadiString = response.body().getData().get("nasabah").toString();
                            dataLengkap = gson.fromJson(dataPribadiString, DataLengkapKmg.class);
                            stepperlayout.setAdapter(new DataLengkapStepAdapterPurna(getSupportFragmentManager(), DataLengkapActivityPurna.this, dataLengkap,approved, gimmick), startingStepPosition );
                            stepperlayout.setListener(DataLengkapActivityPurna.this);
                        }
                        else{
                            AppUtil.notiferror(DataLengkapActivityPurna.this, findViewById(android.R.id.content), response.body().getMessage());
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
                        AppUtil.notiferror(DataLengkapActivityPurna.this, findViewById(android.R.id.content), error.getMessage());
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
                AppUtil.notiferror(DataLengkapActivityPurna.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
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
        final KmgDataLengkapPojo data = realm.where(KmgDataLengkapPojo.class).equalTo("idAplikasi", idAplikasi).findFirst();
        final KmgDataLengkapPojo datafix = realm.copyFromRealm(data);

        loading.setVisibility(View.VISIBLE);
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().sendDataLengkapKmg(datafix);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                loading.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()){
                        if(response.body().getStatus().equalsIgnoreCase("00")) {
                            realm.beginTransaction();
                            data.deleteFromRealm();
                            realm.commitTransaction();

                            //menyimpan status janda/duda jika berhasil disimpan
                            try {
                                DataGlobalHotprospekPojo dataGlobalHotprospekPojo = realm.where(DataGlobalHotprospekPojo.class).equalTo("idAplikasi", idAplikasi).findFirst();
                                DataGlobalHotprospekPojo copyDataGlobalHotprospekPojo = new DataGlobalHotprospekPojo();

                                if (dataGlobalHotprospekPojo != null) {
                                    copyDataGlobalHotprospekPojo = realm.copyFromRealm(dataGlobalHotprospekPojo);
                                }
                                realm.beginTransaction();
                                copyDataGlobalHotprospekPojo.setIdAplikasi(idAplikasi);
                                if (datafix.getJenisKMG().toLowerCase().contains("janda")) {
                                    copyDataGlobalHotprospekPojo.setAdalahJandaDuda(true);
                                } else {
                                    copyDataGlobalHotprospekPojo.setAdalahJandaDuda(false);
                                }


                                realm.insertOrUpdate(copyDataGlobalHotprospekPojo);
                                realm.commitTransaction();
                                realm.close();
                            }
                            catch (NullPointerException e){
                                Log.d("ada error di ","realm janda duda");
                            }

                            CustomDialog.DialogSuccess(DataLengkapActivityPurna.this, "Success!", "Update Data Lengkap sukses", DataLengkapActivityPurna.this);
                        }
                        else{
                            AppUtil.notiferror(DataLengkapActivityPurna.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(DataLengkapActivityPurna.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){

                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                AppUtil.notiferror(DataLengkapActivityPurna.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    @Override
    public void onBackPressed() {
        if(approved.equalsIgnoreCase("no")){
            CustomDialog.DialogBackpress(DataLengkapActivityPurna.this);
        }
        else{
            super.onBackPressed();
        }

    }

    @Override
    public void onCompleted(View completeButton) {

        if(approved.equalsIgnoreCase("no")){
            sendData();
        }
        else{
            finish();
        }

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
}

