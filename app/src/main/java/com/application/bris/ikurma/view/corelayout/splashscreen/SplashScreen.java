package com.application.bris.ikurma.view.corelayout.splashscreen;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.application.bris.ikurma.R;
import com.application.bris.ikurma.api.model.Error;
import com.application.bris.ikurma.api.model.ParseResponse;
import com.application.bris.ikurma.api.model.ParseResponseError;
import com.application.bris.ikurma.api.model.request.general.Checkupdate;
import com.application.bris.ikurma.api.model.request.general.ReqFirebase;
import com.application.bris.ikurma.api.model.request.general.ReqRegisterBrisnotif;
import com.application.bris.ikurma.api.service.ApiClientAdapter;
import com.application.bris.ikurma.baseapp.RouteApp;
import com.application.bris.ikurma.database.AppPreferences;
import com.application.bris.ikurma.page_aom.dialog.CustomDialog;
import com.application.bris.ikurma.page_aom.listener.ConfirmListener;
import com.application.bris.ikurma.util.AppUtil;
import com.application.bris.ikurma.util.Constants;
import com.application.bris.ikurma.view.corelayout.activation.WelcomeActivity;
import com.application.bris.ikurma.view.corelayout.login.LoginActivity;
import com.application.bris.ikurma.view.corelayout.login.LoginActivity2;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashScreen extends AppCompatActivity implements ConfirmListener{

    @BindView(R.id.tv_version)
    TextView tv_version;

    public static final int REQUEST_PERMISSION = 1;
    private AppPreferences appPreferences;
    private ApiClientAdapter apiClientAdapter;
    private String installedVersionName = "";
    private PackageInfo packageInfo;
    private String firebaseToken="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        appPreferences = new AppPreferences(this);
        apiClientAdapter = new ApiClientAdapter(this, 0, 30, TimeUnit.SECONDS);
        ButterKnife.bind(this);
        Log.d("firebaseid",   "device id : "+getDeviceId());

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w("firebaseid", "Fetching FCM registration token failed", task.getException());

                            //comment ini jika naik prod
//                            registerBrisnotif();
                            return;

                        }

                        // Get new FCM registration token
                        String token = task.getResult();
                        firebaseToken=token;

                        if (firebaseToken != null&&!firebaseToken.isEmpty()) {
                            Toast.makeText(SplashScreen.this, "Firebase initilization on hold", Toast.LENGTH_SHORT).show();
//                            updateFirebaseId();
//                            registerBrisnotif();
                        }

                        // Log and toast
                        Log.d("firebaseid", token);
//                        Toast.makeText(SplashScreen.this, token, Toast.LENGTH_SHORT).show();
                    }
                });

        try {
            packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        tv_version.setText("Version "+packageInfo.versionName);

        if(checkPermission()) {
            Toast.makeText(this, "Check version off", Toast.LENGTH_SHORT).show();
            gotoNextActivity();
//            checkAvailableUpdate();
        }


    }

    private void updateFirebaseId() {
        ReqFirebase req = new ReqFirebase();
        req.setAppID("BRISI");
        req.setDeviceID(getDeviceId());
        req.setFirebaseMessagingID(firebaseToken);
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().updateFirebase(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                try {
                    if (response.isSuccessful()) {

                        if (response.body().getStatus().equalsIgnoreCase("00")) {
                            Log.d("firebaseid",   FirebaseMessaging.getInstance().getToken().toString());


                        }
                    } else {
//                        Error error = ParseResponseError.confirmEror(response.errorBody());
//                        AppUtil.showToastShort(SplashScreen.this, "Terjadi Kesalahan");

                    }
                } catch (Exception e) {
                    AppUtil.showToastShort(SplashScreen.this, e.getMessage());
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            gotoNextActivity();
                        }
                    }, 1000);
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                AppUtil.showToastShort(SplashScreen.this, getString(R.string.txt_connection_failure));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        gotoNextActivity();
                    }
                }, 1000);
            }
        });
    }

    private void registerBrisnotif() {
        ReqRegisterBrisnotif req = new ReqRegisterBrisnotif();
        req.setAppclient("ikurma");

        //pantekan device id
//        req.setIdentifier("8609269d342fd10dikurmapemrakarsa");

        //real device id
        req.setIdentifier(getDeviceId()+"ikurmapemrakarsa");

        //pantekan token brisnotif
//        req.setToken("cHehuZwIR5CE91cVvDSSFa:APA91bGmlGVMIxqKXpG4hb-SdZRsh3mQShp4vti_YYRXnP2a44pZBwyiMz8Uu7aWNGb7awxk8qG1_CWtc-jdl7GgZDgiNf90J6pqLx0z4z_FZ6wU4bpdLouiTqcCHWgLkMHXDihRIV32");

//        Toast.makeText(this, "ada pantekan token notifkasi di splash screen", Toast.LENGTH_SHORT).show();

        //real token firebase
        req.setToken(firebaseToken);


        //contoh agent
        //Dalvik/2.1.0 (Linux; U; Android 6.0; Redmi Note 4X MIUI/V10.2.1.0.MBFMIXM), v3.0.7
        req.setAgent("i-Kurma/" + packageInfo.versionName + " (Android " + getAndroidVersion() + ";" + getDeviceName() + ")");
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().brisnotifRegister(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                try {
                    if (response.isSuccessful()) {

                        if (response.body().getStatus().equalsIgnoreCase("00")) {
                            Log.d("firebaseid", FirebaseInstanceId.getInstance().getToken());
                            Log.d("firebaseid", "device id: "+ getDeviceId());
                            Log.d("firebaseid", "sukses register brisnotif");

                        } else {
                            Log.d("firebaseid", FirebaseInstanceId.getInstance().getToken());
                            Log.d("firebaseid", "gagal brisnotif register");
                        }
                    } else {
//                        Error error = ParseResponseError.confirmEror(response.errorBody());
//                        AppUtil.showToastShort(SplashScreen.this, "Terjadi Kesalahan");

                    }
                } catch (Exception e) {
                    AppUtil.showToastShort(SplashScreen.this, e.getMessage());
//                    new Handler().postDelayed(new Runnable() {
//                        @Override
//                        public void run() {
//                            gotoNextActivity();
//                        }
//                    }, 1000);
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
//                AppUtil.showToastShort(SplashScreen.this, getString(R.string.txt_connection_failure));
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        gotoNextActivity();
//                    }
//                }, 1000);
            }
        });
    }

    private String getDeviceId() {
        HashMap<String, String> deviceInfo = AppUtil.getDeviceInfo(this);
        String deviceId = deviceInfo.get(Constants.DEVICE_ID);
        return deviceId;
    }

    private void checkAvailableUpdate() {
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().checkUpdate(new Checkupdate("BRISI"));
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                try {
                    if (response.isSuccessful()){
                        if(response.body().getStatus().equalsIgnoreCase("00")){
                            installedVersionName = packageInfo.versionName;

                            //Checker versi agar aplikasi yang sudah diupdate (versi terbaru) tidak akan keluar popup update,
                            // walaupun versi APP di DB belum diupdate
                            int installedVersionNameInt=Integer.parseInt(packageInfo.versionName.replace(".",""));
                            int responseVersionInt=Integer.parseInt(response.body().getData().get("versionName").getAsString().replace(".",""));
                            if(installedVersionNameInt>=responseVersionInt){
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        gotoNextActivity();
                                    }
                                }, 1000);
                            }
                            else{
                                CustomDialog.DialogUpdate(SplashScreen.this, getString(R.string.notif_update), SplashScreen.this);
                            }


                            //checker lama dicomment dulu
//                            if (response.body().getData().get("versionName").getAsString().equalsIgnoreCase(installedVersionName)){
//                                new Handler().postDelayed(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        gotoNextActivity();
//                                    }
//                                }, 1000);
//                            }
//                            else{
//                                CustomDialog.DialogUpdate(SplashScreen.this, getString(R.string.notif_update), SplashScreen.this);
//                            }
                        }
                        else{
                            AppUtil.showToastShort(SplashScreen.this, response.body().getMessage());
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    gotoNextActivity();
                                }
                            }, 1000);
                        }
                    }
                    else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.showToastShort(SplashScreen.this, error.getMessage());
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                gotoNextActivity();
                            }
                        }, 1000);
                    }
                }
                catch (Exception e){
                    AppUtil.showToastShort(SplashScreen.this, e.getMessage());
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            gotoNextActivity();
                        }
                    }, 1000);
                }
            }
            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                AppUtil.showToastShort(SplashScreen.this, getString(R.string.txt_connection_failure));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        gotoNextActivity();
                    }
                }, 1000);
            }
        });
    }

    private boolean checkPermission() {
        int readCameraPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
        int readLocationPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        int readPhoneStatePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);
        int writeExternalStoragePerm = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        List<String> listPermissionNeeded = new ArrayList<>();


        if(readCameraPermission != PackageManager.PERMISSION_GRANTED){
            listPermissionNeeded.add(Manifest.permission.CAMERA);
        }

        if(readLocationPermission != PackageManager.PERMISSION_GRANTED){
            listPermissionNeeded.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }

        if(readPhoneStatePermission != PackageManager.PERMISSION_GRANTED){
            listPermissionNeeded.add(Manifest.permission.READ_PHONE_STATE);
        }
        if(writeExternalStoragePerm != PackageManager.PERMISSION_GRANTED){
            listPermissionNeeded.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!listPermissionNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionNeeded.toArray(new String[listPermissionNeeded.size()]), REQUEST_PERMISSION);
            return false;
        }
        return true;
    }

    private void gotoNextActivity() {
        RouteApp router = new RouteApp(SplashScreen.this);
        if (appPreferences.getIsActivated().equalsIgnoreCase("1")){
            router.openActivityAndClearAllPrevious(LoginActivity.class);
        }
        else {
//            router.openActivityAndClearAllPrevious(WelcomeActivity.class);
            router.openActivityAndClearAllPrevious(LoginActivity2.class);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case REQUEST_PERMISSION :{
                Map<String,Integer> perms = new HashMap<>();
                perms.put(Manifest.permission.CAMERA, PackageManager.PERMISSION_GRANTED);
                perms.put(Manifest.permission.ACCESS_FINE_LOCATION, PackageManager.PERMISSION_GRANTED);
                perms.put(Manifest.permission.READ_PHONE_STATE, PackageManager.PERMISSION_GRANTED);
                perms.put(Manifest.permission.WRITE_EXTERNAL_STORAGE, PackageManager.PERMISSION_GRANTED);
                if(grantResults.length > 0){
                    for (int i = 0; i < permissions.length; i++){
                        perms.put(permissions[i], grantResults[i]);
                        if(perms.get(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED && perms.get(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && perms.get(Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED &&
                                perms.get(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED){

                            gotoNextActivity();
                        }
                        else{
                            if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA) || ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION) || ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_PHONE_STATE) ||
                                ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)){
                            showDialogOK(getResources().getString(R.string.dialog_perms_msg),
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        switch (which) {
                                            case DialogInterface.BUTTON_POSITIVE:
                                                checkPermission();
                                                break;
                                            case DialogInterface.BUTTON_NEGATIVE:
                                                finish();
                                                break;
                                        }
                                    }
                                });
                            }
                            else {
                                confirmAgain(getResources().getString(R.string.dialog_need_permission));
                            }
                        }
                    }
                }
            }
        }
    }

    private void showDialogOK(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Keluar", okListener)
                .create()
                .show();
    }

    private void confirmAgain(String msg){
        final androidx.appcompat.app.AlertDialog.Builder dialog = new androidx.appcompat.app.AlertDialog.Builder(this);
        dialog.setMessage(msg)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        startActivity(new Intent(android.provider.Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:com.application.bris.brisi")));
                    }
                })
                .setNegativeButton("Keluar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface paramDialogInterface, int paramInt) {
                        finish();
                    }
                });
        dialog.show();
    }

    @Override
    public void success(boolean val) {

    }

    @Override
    public void confirm(boolean val) {
        if (val){
            String appPackageName = getPackageName();
            finish();
            try {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
            } catch (android.content.ActivityNotFoundException anfe) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
            }
        }

    }

    private String getAndroidVersion() {
        Field[] fields = Build.VERSION_CODES.class.getFields();
        String codeName = "";
        for (Field field : fields) {
            try {
                if (field.getInt(Build.VERSION_CODES.class) == Build.VERSION.SDK_INT) {
                    codeName = field.getName();
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return codeName;
    }

    public String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.toLowerCase().startsWith(manufacturer.toLowerCase())) {
            return capitalize(model);
        } else {
            return capitalize(manufacturer) + " " + model;
        }
    }

    private String capitalize(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        char first = s.charAt(0);
        if (Character.isUpperCase(first)) {
            return s;
        } else {
            return Character.toUpperCase(first) + s.substring(1);
        }


    }
}
