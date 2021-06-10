package com.application.bris.ikurma.page_aom.view.approved.agunan_apr;

import android.os.Build;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.application.bris.ikurma.R;
import com.application.bris.ikurma.api.model.Error;
import com.application.bris.ikurma.api.model.ParseResponse;
import com.application.bris.ikurma.api.model.ParseResponseError;
import com.application.bris.ikurma.api.model.request.agunan.ReqAgunanData;
import com.application.bris.ikurma.api.service.ApiClientAdapter;
import com.application.bris.ikurma.page_aom.model.AgunanKendaraan;
import com.application.bris.ikurma.page_aom.view.hotprospek.datalengkap.OnNavigationBarListener;
import com.application.bris.ikurma.util.AppUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import java.lang.reflect.Type;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgunanKendaraanInputActivity_Apr extends AppCompatActivity implements StepperLayout.StepperListener, OnNavigationBarListener{

    @BindView(R.id.stepperlayout)
    StepperLayout stepperlayout;
    @BindView(R.id.progressbar_loading)
    RelativeLayout loading;
    @BindView(R.id.btn_back)
    ImageView btn_back;

    private static final String CURRENT_STEP_POSITION_KEY = "position";
    public static String cif, idAplikasi, loan_type, tp_produk, idAgunan;

    private ApiClientAdapter apiClientAdapter;
    private int startingStepPosition;
    private AgunanKendaraan dataAgunan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ao_activity_datalengkap_apr);
        ButterKnife.bind(this);
        apiClientAdapter = new ApiClientAdapter(this);
        backgroundStatusBar();
        AppUtil.toolbarRegular(AgunanKendaraanInputActivity_Apr.this, "Agunan Kendaraan");
        dataAgunan = new AgunanKendaraan();

        if (getIntent().hasExtra("cif")) {
            cif = getIntent().getStringExtra("cif");
        }

        idAplikasi = getIntent().getStringExtra("idAplikasi");
        loan_type = getIntent().getStringExtra("loan_type");
        tp_produk = getIntent().getStringExtra("tp_produk");
        idAgunan = getIntent().getStringExtra("idAgunan");

        if (!idAgunan.equalsIgnoreCase("0")) {
            getData();
        } else {
            startingStepPosition = savedInstanceState != null ? savedInstanceState.getInt(CURRENT_STEP_POSITION_KEY) : 0;
            stepperlayout.setAdapter(new StepAdapterAgunanKendaraan_Apr(getSupportFragmentManager(), AgunanKendaraanInputActivity_Apr.this, dataAgunan, idAgunan, loan_type, tp_produk), startingStepPosition );
            stepperlayout.setListener(AgunanKendaraanInputActivity_Apr.this);
        }
    }

    private void getData() {
        loading.setVisibility(View.VISIBLE);
        ReqAgunanData req = new ReqAgunanData(AppUtil.parseIntWithDefault(idAplikasi, 0), AppUtil.parseIntWithDefault(idAgunan, 0), AppUtil.parseIntWithDefault(cif, 0));
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().inquiryAgunanKendaraan(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                try {
                    loading.setVisibility(View.GONE);
                    if(response.isSuccessful()){
                        if(response.body().getStatus().equalsIgnoreCase("00")){
                            String listDataAgunan = response.body().getData().toString();
                            Gson gson = new Gson();
                            Type type = new TypeToken<AgunanKendaraan>() {}.getType();
                            dataAgunan = gson.fromJson(listDataAgunan, type);
                            stepperlayout.setAdapter(new StepAdapterAgunanKendaraan_Apr(getSupportFragmentManager(), AgunanKendaraanInputActivity_Apr.this, dataAgunan, idAgunan, loan_type, tp_produk), startingStepPosition );
                            stepperlayout.setListener(AgunanKendaraanInputActivity_Apr.this);

                        } else{
                            AppUtil.notiferror(AgunanKendaraanInputActivity_Apr.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else{
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(AgunanKendaraanInputActivity_Apr.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                AppUtil.notiferror(AgunanKendaraanInputActivity_Apr.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    private void backgroundStatusBar(){
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }


    @Override
    public void onCompleted(View view) {
        finish();
    }

    @Override
    public void onError(VerificationError verificationError) {

    }

    @Override
    public void onStepSelected(int i) {

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
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}

