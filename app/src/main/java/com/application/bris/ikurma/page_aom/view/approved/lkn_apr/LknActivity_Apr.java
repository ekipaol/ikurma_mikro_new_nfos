package com.application.bris.ikurma.page_aom.view.approved.lkn_apr;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.application.bris.ikurma.R;
import com.application.bris.ikurma.api.model.Error;
import com.application.bris.ikurma.api.model.ParseResponse;
import com.application.bris.ikurma.api.model.ParseResponseError;
import com.application.bris.ikurma.api.model.request.hotprospek.inquiryLkn;
import com.application.bris.ikurma.api.service.ApiClientAdapter;
import com.application.bris.ikurma.database.AppPreferences;
import com.application.bris.ikurma.page_aom.model.DataLkn;
import com.application.bris.ikurma.page_aom.view.hotprospek.datalengkap.OnNavigationBarListener;
import com.application.bris.ikurma.util.AppUtil;
import com.google.gson.Gson;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LknActivity_Apr extends AppCompatActivity implements StepperLayout.StepperListener, OnNavigationBarListener{
    @BindView(R.id.stepperlayout)
    StepperLayout stepperlayout;
    @BindView(R.id.progressbar_loading)
    RelativeLayout loading;
    @BindView(R.id.btn_back)
    ImageView btn_back;
    private static final String CURRENT_STEP_POSITION_KEY = "position";

    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    private String dataLknString;
    private DataLkn data;
    private int startingStepPosition;
    public static String cif;
    public static int idAplikasi;
    public static String kodeProduct;
    public static String namaProduct;
    public static int idTujuanPembiayaan;
    public static String tujuanPembiayaan;
    public static int plafond;
    public static int jw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ao_activity_lkn_apr);
        ButterKnife.bind(this);
        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);
        cif = getIntent().getStringExtra("cif");
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
        loadDataLengkap();
    }

    private void backgroundStatusBar(){
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    private void loadDataLengkap() {
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
                            stepperlayout.setAdapter(new LknStepAdapter_Apr(getSupportFragmentManager(), LknActivity_Apr.this, data), startingStepPosition );
                            stepperlayout.setListener(LknActivity_Apr.this);
                        }
                        else{
                            AppUtil.notiferror(LknActivity_Apr.this, findViewById(android.R.id.content), response.body().getMessage());
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
                        AppUtil.notiferror(LknActivity_Apr.this, findViewById(android.R.id.content), error.getMessage());
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
                AppUtil.notiferror(LknActivity_Apr.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                }, 2000);
            }
        });
    }

    @Override
    public void onCompleted(View completeButton) {
        finish();
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

}
