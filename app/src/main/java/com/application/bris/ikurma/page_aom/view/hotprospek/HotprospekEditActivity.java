package com.application.bris.ikurma.page_aom.view.hotprospek;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.appcompat.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.application.bris.ikurma.BuildConfig;
import com.application.bris.ikurma.R;
import com.application.bris.ikurma.api.config.UriApi;
import com.application.bris.ikurma.api.model.Error;
import com.application.bris.ikurma.api.model.ParseResponse;
import com.application.bris.ikurma.api.model.ParseResponseError;
import com.application.bris.ikurma.api.model.request.hotprospek.inputHotprospek;
import com.application.bris.ikurma.api.service.ApiClientAdapter;
import com.application.bris.ikurma.database.AppPreferences;
import com.application.bris.ikurma.database.pojo.ProductPojo;
import com.application.bris.ikurma.page_aom.dialog.BSBottomCamera;
import com.application.bris.ikurma.page_aom.dialog.BSRejectHotprospek;
import com.application.bris.ikurma.page_aom.dialog.CustomDialog;
import com.application.bris.ikurma.page_aom.dialog.DialogKeyValue;
import com.application.bris.ikurma.page_aom.dialog.DialogPreviewPhoto;
import com.application.bris.ikurma.page_aom.dialog.DialogSelect;
import com.application.bris.ikurma.page_aom.listener.CameraListener;
import com.application.bris.ikurma.page_aom.listener.ConfirmListener;
import com.application.bris.ikurma.page_aom.listener.KeyValueListener;
import com.application.bris.ikurma.page_aom.listener.ProductListener;
import com.application.bris.ikurma.page_aom.model.hotprospek;
import com.application.bris.ikurma.page_aom.model.keyvalue;
import com.application.bris.ikurma.util.AppUtil;
import com.application.bris.ikurma.util.ImageHandler;
import com.application.bris.ikurma.util.NumberTextWatcherForThousand;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.gson.Gson;
import com.makeramen.roundedimageview.RoundedDrawable;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

public class HotprospekEditActivity extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener, ProductListener, KeyValueListener, TextWatcher, ConfirmListener, CameraListener {
    @BindView(R.id.tf_produk)
    TextFieldBoxes tf_produk;
    @BindView(R.id.et_produk)
    EditText et_produk;
    @BindView(R.id.tf_plafond)
    TextFieldBoxes tf_plafond;
    @BindView(R.id.et_plafond)
    EditText et_plafond;
    @BindView(R.id.tf_tenor)
    TextFieldBoxes tf_tenor;
    @BindView(R.id.et_tenor)
    EditText et_tenor;
    @BindView(R.id.tf_tujuanpenggunaan)
    TextFieldBoxes tf_tujuanpenggunaan;
    @BindView(R.id.et_tujuanpenggunaan)
    EditText et_tujuanpenggunaan;
    @BindView(R.id.iv_foto)
    ImageView iv_foto;
    @BindView(R.id.btn_foto)
    ImageView btn_foto;
    @BindView(R.id.progressbar_loading)
    RelativeLayout loading;
    @BindView(R.id.btn_send)
    Button btn_send;
    @BindView(R.id.btn_reject)
    Button btn_reject;

    private int val_idAplikasi;
    private String val_uid;
    private String val_produk;
    private String val_plafond;
    private String val_tenor;
    private int val_tujuanpenggunaan;

    private String dataString;
    private hotprospek data;

    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    private String isSelectPhoto = "";
    private final int TAKE_PICTURE= 1;
    private final int PICT_PICTURE = 0;
    private Bitmap bitmap_foto;
    private Bitmap loadedPicture;
    private int val_idPhoto = 0;
    private Uri uri_foto;

   @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ao_activity_hotprospek_edit);
        ButterKnife.bind(this);
        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);
        AppUtil.toolbarRegular(this, "Edit Hotprospek");
        dataString = getIntent().getStringExtra("dataHotprospek");
        et_plafond.addTextChangedListener(new NumberTextWatcherForThousand(et_plafond));
        backgroundStatusBar();
        onclickSelectDialog();
        setData();
        iv_foto.setOnClickListener(this);
        btn_foto.setOnClickListener(this);
        btn_send.setOnClickListener(this);
        btn_reject.setOnClickListener(this);
    }

    private void setData() {
       Gson gson = new Gson();
       data = gson.fromJson(dataString, hotprospek.class);
       et_produk.setText(data.getNama_produk());
       et_plafond.setText(Integer.toString(data.getPlafond_induk()));
       et_tenor.setText(Integer.toString(data.getJw()));
       et_tujuanpenggunaan.setText(data.getNama_tujuan());
       bitmap_foto = setLoadImage(iv_foto, data.getFid_photo());

       val_idAplikasi = data.getId_aplikasi();
       val_uid = Integer.toString(appPreferences.getUid());
       val_produk = data.getKode_produk();
       val_tujuanpenggunaan = data.getId_tujuan();
       val_idPhoto = data.getFid_photo();

    }

    public void sendData(){
        loading.setVisibility(View.VISIBLE);
        val_plafond = NumberTextWatcherForThousand.trimCommaOfString(et_plafond.getText().toString().trim());
        val_tenor = et_tenor.getText().toString().trim();
        inputHotprospek req = new inputHotprospek(val_idAplikasi, val_uid, AppUtil.parseIntWithDefault(val_plafond, 0), AppUtil.parseIntWithDefault(val_tenor, 0), val_produk, val_tujuanpenggunaan, val_idPhoto);
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().sendDataHotprospek(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                loading.setVisibility(View.GONE);
                try {
                    if(response.isSuccessful()){
                        if(response.body().getStatus().equalsIgnoreCase("00")){
                            CustomDialog.DialogSuccess(HotprospekEditActivity.this, "Success!", "Edit data Hotprospek sukses", HotprospekEditActivity.this);
                        }
                        else{
                            AppUtil.notiferror(HotprospekEditActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(HotprospekEditActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                AppUtil.notiferror(HotprospekEditActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    public Bitmap setLoadImage(final ImageView iv, int idFoto){
        String url_photo = UriApi.Baseurl.URL + UriApi.foto.urlPhoto + idFoto;
        Glide
                .with(HotprospekEditActivity.this)
                .asBitmap()
                .load(url_photo)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        iv.setImageBitmap(resource);
                        loadedPicture = resource;
                    }
                });
        return loadedPicture;
    }

    private boolean validateForm(){
        if(et_produk.getText().toString().trim().isEmpty() || et_produk.getText().toString().trim().equalsIgnoreCase("")){
            tf_produk.setError(tf_produk.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return false;
        }

        else if(et_plafond.getText().toString().trim().isEmpty() || et_plafond.getText().toString().trim().equalsIgnoreCase("")){
            tf_plafond.setError(tf_plafond.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return false;
        }
        else if(et_tenor.getText().toString().trim().isEmpty() || et_tenor.getText().toString().trim().equalsIgnoreCase("")){
            tf_tenor.setError(tf_tenor.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return false;
        }
        else if(et_tujuanpenggunaan.getText().toString().trim().isEmpty() || et_tujuanpenggunaan.getText().toString().trim().equalsIgnoreCase("")){
            tf_tujuanpenggunaan.setError(tf_tujuanpenggunaan.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return false;
        }
        else if ((val_produk.equalsIgnoreCase("127") || val_produk.equalsIgnoreCase("128")) && val_tujuanpenggunaan > 41){
            AppUtil.showToastShort(this, "Produk KUR hanya diperbolehkan untuk tujuan penggunaan Modal Kerja atau Investasi");
            return false;
        }
        return true;
    }

    private void onclickSelectDialog(){
        //PRODUCT
        et_produk.setFocusable(false);
        et_produk.setInputType(InputType.TYPE_NULL);
        et_produk.setOnFocusChangeListener(this);
        tf_produk.setOnClickListener(this);
        tf_produk.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProdukDialog(tf_produk.getLabelText().toString().trim(), "mikro", "");
            }
        });

        //TUJUAN PENGGUNAAN
        et_tujuanpenggunaan.setFocusable(false);
        et_tujuanpenggunaan.setInputType(InputType.TYPE_NULL);
        et_tujuanpenggunaan.setOnFocusChangeListener(this);
        tf_tujuanpenggunaan.setOnClickListener(this);
        tf_tujuanpenggunaan.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKeyValueDialog(tf_tujuanpenggunaan.getLabelText().toString().trim());
            }
        });


    }

    private void openProdukDialog(String title, String segmen, String product){
        DialogSelect.display(getSupportFragmentManager(), title, segmen, product, this);
    }

    private void openKeyValueDialog(String title){
        DialogKeyValue.displayWithTypeAndFlagAmanah(getSupportFragmentManager(), title, this,val_produk,appPreferences.getCbAmanah());
    }

    private void backgroundStatusBar(){
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //PRODUK
            case R.id.tf_produk:
            case R.id.et_produk:
                openProdukDialog(tf_produk.getLabelText().toString().trim(), "mikro", "");
                break;

            //TUJUAN PENGGUNAAN
            case R.id.tf_tujuanpenggunaan:
            case R.id.et_tujuanpenggunaan:
                openKeyValueDialog(tf_tujuanpenggunaan.getLabelText().toString().trim());
                break;

            //TAKE PICTURE
            case R.id.btn_foto:
                isSelectPhoto = "foto";
                BSBottomCamera.displayWithTitle(getSupportFragmentManager(), this, "Foto Nasabah");
                break;

            case R.id.iv_foto:
                DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Foto", ((RoundedDrawable)iv_foto.getDrawable()).getSourceBitmap());
                break;

            case R.id.btn_send:
                if (validateForm())
                    sendData();
                break;

            case R.id.btn_reject:
                BSRejectHotprospek.display(getSupportFragmentManager(), HotprospekEditActivity.this);
                break;
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        switch (v.getId()){
            //PRODUK
            case R.id.et_produk:
                openProdukDialog(tf_produk.getLabelText().toString().trim(), "mikro", "");
                break;

            //TUJUAN PENGGUNAAN
            case R.id.et_tujuanpenggunaan:
                openKeyValueDialog(tf_tujuanpenggunaan.getLabelText().toString().trim());
                break;
        }
    }

    @Override
    public void onProductSelect(String title, ProductPojo data) {
        if(title.equalsIgnoreCase("produk")){
            et_produk.setText(data.getNama_produk());
            val_produk = data.getKode_produk(); //set value produk
        }
    }

    @Override
    public void onKeyValueSelect(String title, keyvalue data) {
        if(title.equalsIgnoreCase("tujuan penggunaan")){
            et_tujuanpenggunaan.setText(data.getName());
            val_tujuanpenggunaan = AppUtil.parseIntWithDefault(data.getValue(), 0); //set value tujuan penggunaan
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void success(boolean val) {
        if(val)
            finish();
    }

    @Override
    public void confirm(boolean val) {

    }

    @Override
    public void onSelectMenuCamera(String idMenu) {
        if (isSelectPhoto.equalsIgnoreCase("foto")){
            switch (idMenu) {
                case "Take Photo":
                    openCamera(TAKE_PICTURE);
                    break;
                case "Pick Photo":
                    openGalery(PICT_PICTURE);
                    break;
            }
        }
    }

    //TAKE PICTURE
    public void openGalery(int requestCode){
        Intent it = new Intent(Intent.ACTION_GET_CONTENT);
        it.setType("image/*");
        startActivityForResult(Intent.createChooser(it, "Select File"), requestCode);
    }

    private void openCamera(int cameraCode) {
        checkCameraPermission(cameraCode);
    }

    private static final int MY_CAMERA_REQUEST_CODE = 100;
    private static int CAMERA_CODE_FORE_PERMISSION = 0;
    public void checkCameraPermission(int cameraCode)
    {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            CAMERA_CODE_FORE_PERMISSION = cameraCode;
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},
                    MY_CAMERA_REQUEST_CODE);
        } else {
            directOpenCamera(cameraCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == MY_CAMERA_REQUEST_CODE)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                AppUtil.showToastLong(this, "Camera Permission Granted");
                directOpenCamera(CAMERA_CODE_FORE_PERMISSION);
            }
            else {
                AppUtil.showToastLong(this, "Camera Permission Denied");
            }
        }
    }

    private void directOpenCamera(int cameraCode){
        Uri outputFileUri = getCaptureImageOutputUri();
        Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        captureIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
        startActivityForResult(captureIntent, cameraCode);
    }

    private Uri getCaptureImageOutputUri() {
        Uri outputFileUri = null;
        File getImage = getExternalCacheDir();
        if (getImage != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                outputFileUri = FileProvider.getUriForFile(HotprospekEditActivity.this, BuildConfig.APPLICATION_ID + ".provider", new File(getImage.getPath(), "fotoagunandeposito.png"));
            }
            else{
                outputFileUri = Uri.fromFile(new File(getImage.getPath(), "fotoagunandeposito.png"));
            }
        }
        return outputFileUri;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {

            switch (requestCode){
                case TAKE_PICTURE:
                case PICT_PICTURE:
                    setDataImage(uri_foto, bitmap_foto, iv_foto, data);
                    break;
            }
        }
    }

    private void setDataImage(Uri uri, Bitmap bitmap, ImageView iv, Intent data){
        initImageFileName();
        if (getPickImageResultUri(data) != null) {
            uri = getPickImageResultUri(data);
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                bitmap = AppUtil.getResizedBitmap(bitmap, 1024);
                bitmap = AppUtil.rotateImageIfRequired(this, bitmap, uri);
                iv.setImageBitmap(bitmap);
                bitmap_foto = bitmap;
            } catch (Exception e) {
                e.printStackTrace();
            }
            String filename = appPreferences.getFotoAgunan();
            ImageHandler.saveImageToCache(this, bitmap, filename);
            uploadFoto(filename);
        }
    }

    private void initImageFileName() {
        String fileName = new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date())+"_"+System.currentTimeMillis()+ ".jpg";
        appPreferences.setFotoAgunan(fileName);
    }

    public Uri getPickImageResultUri(Intent data) {
        boolean isCamera = true;
        if (data != null) {
            String action = data.getAction();
            isCamera = action != null && action.equals(MediaStore.ACTION_IMAGE_CAPTURE);
        }
        return isCamera ? getCaptureImageOutputUri() : data.getData();
    }

    public void uploadFoto(String filename){
        loading.setVisibility(View.VISIBLE);
        File imageFile = new File(getApplicationContext().getCacheDir(), filename);
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), imageFile);
        MultipartBody.Part fileBody = MultipartBody.Part.createFormData("file", imageFile.getName(), requestBody);
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().uploadFoto(fileBody);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                try {
                    loading.setVisibility(View.GONE);
                    if (response.isSuccessful()){
                        if (response.body().getStatus().equalsIgnoreCase("00")){
                            int idFoto = response.body().getData().get("id").getAsInt();
                            switch (isSelectPhoto){
                                case "foto":
                                    val_idPhoto = idFoto;
                                    break;
                                default:
                                    break;
                            }
                        }
                        else{
                            loading.setVisibility(View.GONE);
                            AppUtil.notiferror(HotprospekEditActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else {
                        loading.setVisibility(View.GONE);
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(HotprospekEditActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                AppUtil.notiferror(HotprospekEditActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }
}
