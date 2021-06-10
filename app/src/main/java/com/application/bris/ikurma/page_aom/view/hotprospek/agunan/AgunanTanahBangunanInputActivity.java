package com.application.bris.ikurma.page_aom.view.hotprospek.agunan;

import android.content.Intent;
import android.os.Build;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.application.bris.ikurma.R;
import com.application.bris.ikurma.api.model.Error;
import com.application.bris.ikurma.api.model.ParseResponse;
import com.application.bris.ikurma.api.model.ParseResponseError;
import com.application.bris.ikurma.api.model.request.agunan.InsertAgunan;
import com.application.bris.ikurma.api.model.request.agunan.ReqAgunanData;
import com.application.bris.ikurma.api.service.ApiClientAdapter;
import com.application.bris.ikurma.database.AppPreferences;
import com.application.bris.ikurma.database.pojo.AgunanTanahBangunanPojo;
import com.application.bris.ikurma.page_aom.dialog.CustomDialog;
import com.application.bris.ikurma.page_aom.listener.ConfirmListener;
import com.application.bris.ikurma.page_aom.model.AgunanTanahBangunan;
import com.application.bris.ikurma.page_aom.view.hotprospek.datalengkap.OnNavigationBarListener;
import com.application.bris.ikurma.util.AppUtil;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import java.lang.reflect.Type;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgunanTanahBangunanInputActivity extends AppCompatActivity implements StepperLayout.StepperListener, OnNavigationBarListener, ConfirmListener {

    @BindView(R.id.stepperlayout)
    StepperLayout stepperlayout;
    @BindView(R.id.progressbar_loading)
    RelativeLayout loading;
    @BindView(R.id.btn_back)
    ImageView btn_back;

    private static final String CURRENT_STEP_POSITION_KEY = "position";
    public static String cif, idAplikasi, loan_type, tp_produk, idAgunan;

    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    private InsertAgunan agunan;
    private int startingStepPosition;
    private AgunanTanahBangunan dataAgunan;
    private String id_agunan_response;

    public static String UUIDR;
    private Realm realm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ao_activity_datalengkap);
        ButterKnife.bind(this);
        realm = Realm.getDefaultInstance();
        apiClientAdapter = new ApiClientAdapter(AgunanTanahBangunanInputActivity.this);
        appPreferences = new AppPreferences(this);
        backgroundStatusBar();
        AppUtil.toolbarRegular(AgunanTanahBangunanInputActivity.this, "Agunan Tanah Bangunan");

        UUIDR = UUID.randomUUID().toString();

        agunan = new InsertAgunan();
        dataAgunan = new AgunanTanahBangunan();

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
            stepperlayout.setAdapter(new StepAdapterAgunan(getSupportFragmentManager(), AgunanTanahBangunanInputActivity.this, dataAgunan, idAgunan, loan_type, tp_produk), startingStepPosition );
            stepperlayout.setListener(AgunanTanahBangunanInputActivity.this);
        }
    }

    private void getData() {
        ReqAgunanData req = new ReqAgunanData(AppUtil.parseIntWithDefault(idAplikasi, 0), AppUtil.parseIntWithDefault(idAgunan, 0), AppUtil.parseIntWithDefault(cif, 0));
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().inquiryAgunanTanahBangunan(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                try {
                    if(response.isSuccessful()){
                        if(response.body().getStatus().equalsIgnoreCase("00")){
                            String listDataAgunan = response.body().getData().get("data_detail").toString();
                            Gson gson = new Gson();
                            Type type = new TypeToken<AgunanTanahBangunan>() {}.getType();

                            dataAgunan = gson.fromJson(listDataAgunan, type);


                            stepperlayout.setAdapter(new StepAdapterAgunan(getSupportFragmentManager(), AgunanTanahBangunanInputActivity.this, dataAgunan, idAgunan, loan_type, tp_produk), startingStepPosition );
                            stepperlayout.setListener(AgunanTanahBangunanInputActivity.this);

                        } else{
                            AppUtil.notiferror(AgunanTanahBangunanInputActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else{
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(AgunanTanahBangunanInputActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                AppUtil.notiferror(AgunanTanahBangunanInputActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
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
    public void onBackPressed() {
        CustomDialog.DialogBackpress(this);
    }

    @Override
    public void onCompleted(View view) {
        sendData();

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

    public void sendData(){
        loading.setVisibility(View.VISIBLE);
        final AgunanTanahBangunanPojo dataR = realm.where(AgunanTanahBangunanPojo.class).equalTo("uuid", UUIDR).findFirst();
        AgunanTanahBangunanPojo datafix = realm.copyFromRealm(dataR);
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().sendAgunanTanahBangunan(datafix);
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
                            CustomDialog.DialogSuccess(AgunanTanahBangunanInputActivity.this, "Success!", response.body().getMessage(), AgunanTanahBangunanInputActivity.this);
                            id_agunan_response = response.body().getData().get("idAgunan").getAsString();
                        }
                        else{
                            AppUtil.notiferror(AgunanTanahBangunanInputActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(AgunanTanahBangunanInputActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){

                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                AppUtil.notiferror(AgunanTanahBangunanInputActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    @Override
    public void success(boolean val) {
        if(val){
            if (idAgunan.equalsIgnoreCase("0")) {
                finish();
                Intent intent = new Intent(AgunanTanahBangunanInputActivity.this, InfoAgunanActivity.class);
                intent.putExtra("idAplikasi", getIntent().getStringExtra("idAplikasi"));
                intent.putExtra("idCif", cif);
                intent.putExtra("idAgunan", id_agunan_response);
                intent.putExtra("loan_type", loan_type);
                intent.putExtra("tp_produk", tp_produk);
                intent.putExtra("jenisAgunan", "tanah dan bangunan");
                startActivity(intent);
            }
            else{
                finish();
            }
        }
    }

    @Override
    public void confirm(boolean val) {

    }
}

