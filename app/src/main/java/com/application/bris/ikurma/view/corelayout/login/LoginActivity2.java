package com.application.bris.ikurma.view.corelayout.login;

import android.os.Build;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.application.bris.ikurma.BuildConfig;
import com.application.bris.ikurma.R;
import com.application.bris.ikurma.api.model.Error;
import com.application.bris.ikurma.api.model.ParseResponse;
import com.application.bris.ikurma.api.model.ParseResponseError;
import com.application.bris.ikurma.api.model.request.general.login;
import com.application.bris.ikurma.api.service.ApiClientAdapter;
import com.application.bris.ikurma.baseapp.RouteApp;
import com.application.bris.ikurma.database.AppPreferences;
import com.application.bris.ikurma.model.general.DataLoginBsi;
import com.application.bris.ikurma.model.general.dataLogin;
import com.application.bris.ikurma.page_aom.dialog.CustomDialog;
import com.application.bris.ikurma.page_aom.listener.ConfirmListener;
import com.application.bris.ikurma.util.AppUtil;
import com.application.bris.ikurma.util.Constants;
import com.application.bris.ikurma.util.service_encrypt.MagicCryptHelper;
import com.application.bris.ikurma.view.corelayout.CoreLayoutActivity;
import com.application.bris.ikurma.view.corelayout.activation.WelcomeActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.gson.Gson;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;
import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity2 extends AppCompatActivity implements View.OnClickListener, ConfirmListener{
    @BindView(R.id.iv_avatarlogin)
    ImageView iv_avatarlogin;
    @BindView(R.id.tv_titlelogin)
    TextView tv_titlelogin;
    @BindView(R.id.tv_subtitlelogin)
    TextView tv_subtitlelogin;
    @BindView(R.id.et_username)
    EditText et_username;
    @BindView(R.id.et_password)
    EditText et_password;
    @BindView(R.id.btn_login)
    Button btn_login;
    @BindView(R.id.progressbar_loading)
    RelativeLayout loading;

    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    private boolean expiredToken=false;

    public static int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        apiClientAdapter = new ApiClientAdapter(this, 0);
        appPreferences = new AppPreferences(this);
        appPreferences.setNama(AppUtil.encrypt("Developer"));
        backgroundStatusBar();
        Bundle extras = getIntent().getExtras();
        if (extras != null){
            if (extras.getString("type").equalsIgnoreCase("bdwelcome")){
                loadProfilWelcome();
            }
            else{
                loadProfilLogin();
            }
        }
        btn_login.setOnClickListener(this);
        iv_avatarlogin.setOnClickListener(this);

        expiredToken=getIntent().getBooleanExtra("expiredToken",false);

        if(expiredToken){
            final SweetAlertDialog dialog=new SweetAlertDialog(LoginActivity2.this, SweetAlertDialog.WARNING_TYPE);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setTitle("Sesi habis");
            dialog.setContentText("Silahkan lakukan login ulang untuk kembali dapat mengakses aplikasi i-Kurma");
            dialog.setConfirmText("OK");

            dialog.show();
            dialog.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sweetAlertDialog) {
                    dialog.dismissWithAnimation();
                    et_password.requestFocus();
                }
            });
        }
    }

    private void backgroundStatusBar(){
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorPrimary));
        }
    }

    private void loadProfilWelcome(){
        try {
            if (appPreferences.getImageProfilBase64().equalsIgnoreCase("")){
                Glide.
                        with(this)
                        .load(R.drawable.ic_generalusericon)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true)
                        .into(iv_avatarlogin);
            }
            else{
                Glide
                        .with(this)
                        .asBitmap()
                        .load(AppUtil.decodeImageTobase64(appPreferences.getImageProfilBase64()))
                        .centerCrop()
                        .placeholder(R.drawable.banner_placeholder)
                        .into(iv_avatarlogin);
            }
            String title = "Welcome <b> Developer</b>";
            String subtitle = "Login to <b> Continue </b>";
            tv_titlelogin.setText(Html.fromHtml(title));
            tv_subtitlelogin.setText(Html.fromHtml(subtitle));
        }
        catch (Exception e){
            e.printStackTrace();
        }


    }


    private void loadProfilLogin(){
        try {
            if (appPreferences.getImageProfilBase64().equalsIgnoreCase("")){
                Glide.
                        with(this)
                        .load(R.drawable.ic_generalusericon)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true)
                        .into(iv_avatarlogin);
            }
            else{
                Glide
                        .with(this)
                        .asBitmap()
                        .load(AppUtil.decodeImageTobase64(appPreferences.getImageProfilBase64()))
                        .centerCrop()
                        .placeholder(R.drawable.banner_placeholder)
                        .into(iv_avatarlogin);
            }

            String title = "Welcome <b>"+appPreferences.getNama()+"</b>,";
            String subtitle = "Login to <b> Continue </b>";
            tv_titlelogin.setText(Html.fromHtml(title));
            tv_subtitlelogin.setText(Html.fromHtml(subtitle));
            String username = appPreferences.getUsername();
            et_username.setText(username);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                doLogin();
//                Toast.makeText(LoginActivity2.this, "Login using dummy", Toast.LENGTH_SHORT).show();
//                doLoginDummy();
                break;
            case R.id.iv_avatarlogin:
            {
                ++counter;
                if (counter > 8){

                }
            }
        }
    }

    private void doLogin() {
        loading.setVisibility(View.VISIBLE);
        login req = new login(et_username.getText().toString().trim(), "", getDeviceId(), "BRISI");
        MagicCryptHelper encryptor =new MagicCryptHelper();
        req.setPassword(encryptor.encrypt(et_password.getText().toString()));
        AppUtil.logSecure("hasildekripsi",encryptor.decrypt("HAd4SfZQEiGGLuOvGDXs1A=="));

//        if(BuildConfig.IS_PRODUCTION==false){
//            req.setPassword((et_password.getText().toString()).toUpperCase());
//        }

        //LOGIN BARU
        apiClientAdapter = new ApiClientAdapter(this,0, "http://10.0.116.37:8054/");
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().login(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                loading.setVisibility(View.GONE);
                try {
                    if(response.isSuccessful()){
                        if (response.body().getStatus().equalsIgnoreCase("00")){
                            Gson gson = new Gson();
                             DataLoginBsi data = gson.fromJson(response.body().getData().toString(), DataLoginBsi.class);
                            setPrefLoginBsi(data);
                            RouteApp router = new RouteApp(LoginActivity2.this);
                            router.openActivityAndClearAllPrevious(CoreLayoutActivity.class);
                        }
                        else if (response.body().getStatus().equalsIgnoreCase("21")){
                            CustomDialog.DialogError(LoginActivity2.this, "Upss Sorry", "Akun anda telah digunakan di perangkat lain, Silahkan aktivasi ulang jika ingin menggunakan di perangkat ini.", LoginActivity2.this);
                        }
                        else if (response.body().getStatus().equalsIgnoreCase("01")){
                            AppUtil.notiferror(LoginActivity2.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                        else {
                            AppUtil.notiferror(LoginActivity2.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(LoginActivity2.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                AppUtil.notiferror(LoginActivity2.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    private void doLoginDummy() {
        String kodecabang="";
        if(et_password.getText().toString().isEmpty()){
            kodecabang="253";
        }
        else{
            kodecabang=et_password.getText().toString();
        }

        //ini simulasi login dengan mekanisme baru, nanti yang login realnya silakan sesuaikan seperti ini ya, termasuk setPref-nya juga
        String jsonText="{\n" +
                "        \"id\": "+et_username.getText().toString()+",\n" +
                "        \"username\": \"Septifan\",\n" +
                "        \"email\": \"Septifan@gmail.com\",\n" +
                "        \"officer_code\": \"123456789\",\n" +
                "        \"name\": \"Dummy  c79a5d48\",\n" +
                "        \"limit\": 10.0,\n" +
                "        \"branch\": {\n" +
                "            \"id\": "+kodecabang+",\n" +
                "            \"branch_code\": \"ID0010607\",\n" +
                "            \"branch_name\": \"KCP KALIMALANG\",\n" +
                "            \"address\": \"JL. INSPEKSI KALIMALANG NO 9\",\n" +
                "            \"phone\": null,\n" +
                "            \"branch_level\": \"B\",\n" +
                "            \"mappingAreaBranches\": []\n" +
                "        },\n" +
                "        \"role\": {\n" +
                "            \"id\": 8,\n" +
                "            \"name\": \"AOM\"\n" +
                "        }\n" +
                "}";
        Gson gson=new Gson();
        DataLoginBsi loginCred=gson.fromJson(jsonText, DataLoginBsi.class);
        setPrefLoginDummy(loginCred);

        RouteApp router = new RouteApp(LoginActivity2.this);
        router.openActivityAndClearAllPrevious(CoreLayoutActivity.class);
    }

    public boolean validateForm(){
        if (et_password.getText().toString().trim().isEmpty() || et_password.getText().toString().trim().equalsIgnoreCase("")){
            AppUtil.notifwarning(this, findViewById(android.R.id.content), "Password tidak boleh kosong");
            return false;
        }
        return true;
    }

    public void setPrefLogin(dataLogin data){

        if(BuildConfig.IS_PRODUCTION){
            appPreferences.setNama(AppUtil.encrypt(data.getNama()));
        }
        else{
            appPreferences.setNama(AppUtil.encrypt("Developer"));
        }
        appPreferences.setRoleType(AppUtil.encrypt(String.valueOf(data.getRole_type())));
        appPreferences.setJabatan(AppUtil.encrypt(data.getJabatan()));
        appPreferences.setNamaKantor(AppUtil.encrypt(data.getNama_kantor()));
        appPreferences.setKodeKantor(AppUtil.encrypt(data.getKode_skk()));
        appPreferences.setNamaKanwil(AppUtil.encrypt(data.getNama_kanwil()));
        appPreferences.setKodeKanwil(AppUtil.encrypt(data.getKode_kanwil()));
        appPreferences.setFidRole(AppUtil.encrypt(String.valueOf(data.getFid_role())));
        appPreferences.setToken(data.getToken());
        appPreferences.setUid(AppUtil.encrypt(String.valueOf(data.getUid())));
        appPreferences.setNik(AppUtil.encrypt(data.getNik()));
        appPreferences.setKodeAo(AppUtil.encrypt(data.getKode_ao()));
        appPreferences.setKodeCabang(AppUtil.encrypt(data.getKode_cabang()));

        if(data.getUker_skk()!=null){
            appPreferences.setUkerSkk(data.getUker_skk());
        }
        else{
            appPreferences.setUkerSkk("");
        }

        if(data.getCb_amanah()!=null){
            appPreferences.setCbAmanah(data.getCb_amanah());
        }
        else{
            appPreferences.setCbAmanah("false");
        }
    }

    public void setPrefLoginDummy(DataLoginBsi dataLoginBsi){

        appPreferences.setNama(AppUtil.encrypt(dataLoginBsi.getName()));
        appPreferences.setJabatan(AppUtil.encrypt(dataLoginBsi.getRole().getRoleName()));
        appPreferences.setNamaKantor(AppUtil.encrypt(dataLoginBsi.getBranch().getBranch_name()));
        appPreferences.setKodeKantor(AppUtil.encrypt(String.valueOf(dataLoginBsi.getBranch().getId())));
        appPreferences.setFidRole(AppUtil.encrypt(String.valueOf(dataLoginBsi.getRole().getId())));
        appPreferences.setUid(AppUtil.encrypt(String.valueOf(dataLoginBsi.getId())));
        //appPreferences.setNik(AppUtil.encrypt(dataLoginBsi.getOfficer_code()));
        appPreferences.setKodeAo(AppUtil.encrypt(dataLoginBsi.getOfficer_code()));
        appPreferences.setKodeCabang(AppUtil.encrypt(String.valueOf(dataLoginBsi.getBranch().getId())));
        appPreferences.setKodeKanwil(AppUtil.encrypt(String.valueOf(dataLoginBsi.getBranch().getId())));
        appPreferences.setToken("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJyb2xlX25hbWUiOiJBT00iLCJzdWIiOiJhZGFtLnNhbmRsZXIiLCJyb2xlX2lkIjo4LCJicmFuY2hfaWQiOjI1MywiYnJhbmNoX25hbWUiOiJLQ1AgU1VOVEVSIiwiaWQiOjM4MjEsImV4cCI6MTYyMzIwNTY1Nn0.XgFvEHaKWGOshlzKixuaIAeod8fz8r5vWf48O425SCPlsgQRj90bn7LQv00aCl9dn6QPB8hgxqT9MwCfQhAf0g");

    }

    public void setPrefLoginBsi(DataLoginBsi dataLoginBsi){


        if(BuildConfig.IS_PRODUCTION==false){
            appPreferences.setNama(AppUtil.encrypt("Developer"));
        }
        else{
            appPreferences.setNama(AppUtil.encrypt(dataLoginBsi.getName()));
        }
        appPreferences.setJabatan(AppUtil.encrypt(dataLoginBsi.getRole().getRoleName()));
        appPreferences.setNamaKantor(AppUtil.encrypt(dataLoginBsi.getBranch().getBranch_name()));
        appPreferences.setKodeKantor(AppUtil.encrypt(String.valueOf(dataLoginBsi.getBranch().getId())));
        appPreferences.setFidRole(AppUtil.encrypt(String.valueOf(dataLoginBsi.getRole().getId())));
        appPreferences.setUid(AppUtil.encrypt(String.valueOf(dataLoginBsi.getId())));
        //appPreferences.setNik(AppUtil.encrypt(dataLoginBsi.getOfficer_code()));
        appPreferences.setKodeAo(AppUtil.encrypt(dataLoginBsi.getOfficer_code()));
        appPreferences.setKodeCabang(AppUtil.encrypt(String.valueOf(dataLoginBsi.getBranch().getId())));
        appPreferences.setKodeKanwil(AppUtil.encrypt(String.valueOf(dataLoginBsi.getBranch().getId())));
        appPreferences.setToken(dataLoginBsi.getToken());

    }

    private String getDeviceId() {
        HashMap<String, String> deviceInfo = AppUtil.getDeviceInfo(this);
        String deviceId = deviceInfo.get(Constants.DEVICE_ID);
        return deviceId;
    }

    @Override
    public void success(boolean val) {
        if (val){
            appPreferences.clearAll(this);
            final Realm realm = Realm.getDefaultInstance();
            try {
                realm.close();
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        realm.deleteAll();
                    }
                });
            }
            catch (Exception e){
                e.printStackTrace();
            }

            RouteApp routeApp = new RouteApp(this);
            routeApp.openActivityAndClearAllPrevious(WelcomeActivity.class);
        }
    }

    @Override
    public void confirm(boolean val) {
        if (val){
            RouteApp router = new RouteApp(LoginActivity2.this);
            router.openActivityAndClearAllPrevious(LoginActivity2.class);
        }
    }
}
