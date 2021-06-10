package com.application.bris.ikurma.page_aom.view.pipeline;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.application.bris.ikurma.BuildConfig;
import com.application.bris.ikurma.R;
import com.application.bris.ikurma.api.config.UriApi;
import com.application.bris.ikurma.api.model.Error;
import com.application.bris.ikurma.api.model.ParseResponse;
import com.application.bris.ikurma.api.model.ParseResponseError;
import com.application.bris.ikurma.api.model.request.pipeline.inputPipeline;
import com.application.bris.ikurma.api.service.ApiClientAdapter;
import com.application.bris.ikurma.database.AppPreferences;
import com.application.bris.ikurma.database.pojo.ProductPojo;
import com.application.bris.ikurma.page_aom.adapter.tindaklanjut.TindaklanjutAdapater;
import com.application.bris.ikurma.page_aom.dialog.BSBottomCamera;
import com.application.bris.ikurma.page_aom.dialog.CustomDialog;
import com.application.bris.ikurma.page_aom.dialog.DialogAddress;
import com.application.bris.ikurma.page_aom.dialog.DialogKeyValue;
import com.application.bris.ikurma.page_aom.dialog.DialogPreviewPhoto;
import com.application.bris.ikurma.page_aom.dialog.DialogSelect;
import com.application.bris.ikurma.page_aom.listener.AddressListener;
import com.application.bris.ikurma.page_aom.listener.CameraListener;
import com.application.bris.ikurma.page_aom.listener.ConfirmListener;
import com.application.bris.ikurma.page_aom.listener.KeyValueListener;
import com.application.bris.ikurma.page_aom.listener.ProductListener;
import com.application.bris.ikurma.page_aom.model.address;
import com.application.bris.ikurma.page_aom.model.keyvalue;
import com.application.bris.ikurma.page_aom.model.pipeline;
import com.application.bris.ikurma.page_aom.model.tindaklanjut;
import com.application.bris.ikurma.util.AppBarStateChangedListener;
import com.application.bris.ikurma.util.AppUtil;
import com.application.bris.ikurma.util.ImageHandler;
import com.application.bris.ikurma.util.KeyValue;
import com.application.bris.ikurma.util.NumberTextWatcherForThousand;
import com.application.bris.ikurma.util.Validator;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

public class PipelineEditActivity extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener, ProductListener, AddressListener, KeyValueListener, CameraListener, ConfirmListener, TextWatcher {

    @BindView(R.id.tb_custom)
    Toolbar toolbar;
    @BindView(R.id.tv_page_title)
    TextView tv_page_title;
    @BindView(R.id.btn_takepicture)
    ImageView btn_takepicture;
    @BindView(R.id.btn_back)
    ImageView btn_back;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.appbar)
    AppBarLayout appBarLayout;
    @BindView(R.id.btn_send)
    Button btn_send;
    @BindView(R.id.btn_pickaddress)
    Button btn_pickaddress;
    @BindView(R.id.progressbar_loading)
    RelativeLayout loading;

    //Data Pembiayaan
    @BindView(R.id.tf_segmen)
    TextFieldBoxes tf_segmen;
    @BindView(R.id.et_segmen)
    EditText et_segmen;
    @BindView(R.id.tf_produk)
    TextFieldBoxes tf_produk;
    @BindView(R.id.et_produk)
    EditText et_produk;
    @BindView(R.id.tf_program)
    TextFieldBoxes tf_program;
    @BindView(R.id.et_program)
    EditText et_program;
    @BindView(R.id.tf_plafond)
    TextFieldBoxes tf_plafond;
    @BindView(R.id.et_plafond)
    EditText et_plafond;
    @BindView(R.id.tf_tenor)
    TextFieldBoxes tf_tenor;
    @BindView(R.id.et_tenor)
    EditText et_tenor;

    //Data Pribadi
    @BindView(R.id.tf_nik)
    TextFieldBoxes tf_nik;
    @BindView(R.id.et_nik)
    EditText et_nik;
    @BindView(R.id.tf_nama)
    TextFieldBoxes tf_nama;
    @BindView(R.id.et_nama)
    EditText et_nama;
    @BindView(R.id.tf_tempatlahir)
    TextFieldBoxes tf_tempatlahir;
    @BindView(R.id.et_tempatlahir)
    EditText et_tempatlahir;
    @BindView(R.id.tf_tanggallahir)
    TextFieldBoxes tf_tanggallahir;
    @BindView(R.id.et_tanggallahir)
    EditText et_tanggallahir;
    @BindView(R.id.tf_nomorhp)
    TextFieldBoxes tf_nomorhp;
    @BindView(R.id.et_nomorhp)
    EditText et_nomorhp;
    @BindView(R.id.tf_jenisusaha)
    TextFieldBoxes tf_jenisusaha;
    @BindView(R.id.et_jenisusaha)
    EditText et_jenisusaha;
    @BindView(R.id.tf_pendapatan)
    TextFieldBoxes tf_pendapatan;
    @BindView(R.id.et_pendapatan)
    EditText et_pendapatan;

    //Data Alamat
    @BindView(R.id.et_alamat)
    EditText et_alamat;
    @BindView(R.id.et_rt)
    EditText et_rt;
    @BindView(R.id.et_rw)
    EditText et_rw;
    @BindView(R.id.et_provinsi)
    EditText et_provinsi;
    @BindView(R.id.et_kota)
    EditText et_kota;
    @BindView(R.id.et_kecamatan)
    EditText et_kecamatan;
    @BindView(R.id.et_kelurahan)
    EditText et_kelurahan;
    @BindView(R.id.et_kodepos)
    EditText et_kodepos;

    //Tindak lanjut
    @BindView(R.id.rg_typetindaklanjut)
    RadioGroup rg_typetindaklanjut;
    @BindView(R.id.rb_byvisit)
    RadioButton rb_byvisit;
    @BindView(R.id.rb_bycall)
    RadioButton rb_bycall;
    @BindView(R.id.et_tindaklanjut)
    EditText et_tindaklanjut;
    @BindView(R.id.rv_history_tindaklanjut)
    RecyclerView rv_history_tindaklanjut;
    @BindView(R.id.ll_emptydata)
    LinearLayout ll_emptydata;

    @BindView(R.id.btnfab_takepicture)
    FloatingActionButton btnfab_takepicture;

    @BindView(R.id.iv_photo)
    ImageView iv_photo;

    private DatePickerDialog dp_tanggallahir;
    private Calendar calendar;
    private Calendar calendarMinDate;

    private final int PICK_IMAGE = 1;
    private final int TAKE_PICTURE = 0;
    private int CHANGE_PICTURE = 0;
    private Uri uriPhoto;
    private Bitmap bitmapPhoto;
    private int hasFace = 0;

    //VALUE
    private static String val_segmen = "";
    private static String val_produk = "";
    private static String val_program = "";
    private static String val_plafond = "";
    private static String val_tenor = "";
    private static String val_nik = "";
    private static String val_nama = "";
    private static String val_tempatlahir = "";
    private static String val_tanggallahir = "";
    private static String val_nomorhp = "";
    private static String val_jenisusaha = "";
    private static String val_pendapatan = "";
    private static String val_alamat = "";
    private static String val_rt = "";
    private static String val_rw = "";
    private static String val_provinsi = "";
    private static String val_kota = "";
    private static String val_kecamatan = "";
    private static String val_kelurahan = "";
    private static String val_kodepos = "";
    private static String val_jenistindaklanjut = "";
    private static String val_texttindaklanjut = "";
    private static String val_gps = "";
    private static String val_urlphoto = "";

    private List<tindaklanjut> tindaklanjuts;
    private String dataPipeline, dataTindakLanjut;
    private pipeline dataPL;
    private List<tindaklanjut> dataTL;

    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ao_activity_pipeline_edit);
        ButterKnife.bind(this);
        collapsingToolbar.setStatusBarScrimColor(getResources().getColor(R.color.colorBackgroundTransparent));
        backgroundStatusBar();
        checkCollapse();
        iv_photo.setOnClickListener(this);
        btn_back.setOnClickListener(this);
        btn_takepicture.setOnClickListener(this);
        btnfab_takepicture.setOnClickListener(this);
        btn_pickaddress.setOnClickListener(this);
        btn_send.setOnClickListener(this);
        rg_typetindaklanjut.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_byvisit:
                        val_jenistindaklanjut = "VISIT";
                        break;
                    case R.id.rb_bycall:
                        val_jenistindaklanjut = "CALL";
                        break;
                }


            }
        });
        onclickSelectDialog();
        onChangeText();
        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);
        dataPipeline = getIntent().getStringExtra("dataPipeline");
        dataTindakLanjut = getIntent().getStringExtra("dataTindakLanjut");
        setData();
        calendarMinDate = Calendar.getInstance();
        calendarMinDate.add(Calendar.YEAR, -75);
    }

    private void setData() {
        Gson gson = new Gson();
        dataPL = gson.fromJson(dataPipeline, pipeline.class);

        val_urlphoto = UriApi.Baseurl.URL + UriApi.foto.urlPhoto + dataPL.getFid_photo();
        Glide
                .with(PipelineEditActivity.this)
                .asBitmap()
                .load(val_urlphoto)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        iv_photo.setImageBitmap(resource);
                        bitmapPhoto = resource;
                        hasFace = 1;
                    }
                });

        et_segmen.setText(dataPL.getNama_segmen());
        et_produk.setText(dataPL.getNama_produk());
        if (dataPL.getTipe_produk().equalsIgnoreCase("konsumer")) {
            tf_program.setVisibility(View.VISIBLE);
            et_program.setText(dataPL.getNama_gimmick());
            val_program = dataPL.getKode_gimmick();
            tf_jenisusaha.setLabelText("Pekerjaan");
            tf_pendapatan.setLabelText("Pendapatan");
        }
        et_plafond.setText(Integer.toString(dataPL.getPlafond()));
        et_tenor.setText(Integer.toString(dataPL.getJw()));
        et_nik.setText(dataPL.getNo_ktp());
        et_nama.setText(dataPL.getNama());
        et_tempatlahir.setText(dataPL.getTempat_lahir());
        et_tanggallahir.setText(AppUtil.parseTanggalLahir(dataPL.getTanggal_lahir()));
        et_nomorhp.setText(dataPL.getNo_hp());
        et_jenisusaha.setText(KeyValue.getKeyUsahaorJob(dataPL.getBidang_usaha()));
        et_pendapatan.setText(Integer.toString(dataPL.getOmzet_per_hari()));
        et_alamat.setText(dataPL.getAlamat());
        et_rt.setText(dataPL.getRt());
        et_rw.setText(dataPL.getRw());
        et_provinsi.setText(dataPL.getProvinsi());
        et_kota.setText(dataPL.getKota());
        et_kecamatan.setText(dataPL.getKecamatan());
        et_kelurahan.setText(dataPL.getKelurahan());
        et_kodepos.setText(dataPL.getKode_pos());


        Type type = new TypeToken<List<tindaklanjut>>() {}.getType();
        dataTL = gson.fromJson(dataTindakLanjut, type);

        if(dataTL==null){
            //do nothing
        }
        else if(dataTL.size() == 0){
            ll_emptydata.setVisibility(View.VISIBLE);
        }
        else{
            TindaklanjutAdapater adp = new TindaklanjutAdapater(PipelineEditActivity.this, dataTL);
            rv_history_tindaklanjut.setLayoutManager(new LinearLayoutManager(PipelineEditActivity.this));
            rv_history_tindaklanjut.setItemAnimator(new DefaultItemAnimator());
            rv_history_tindaklanjut.setAdapter(adp);
        }
        val_segmen = dataPL.getKode_segmen();
        val_produk = dataPL.getKode_produk();
        val_gps = dataPL.getLokasi_gps();
        val_jenisusaha = dataPL.getBidang_usaha();
        val_tanggallahir = dataPL.getTanggal_lahir();
    }

    private void backgroundStatusBar(){
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorBackgroundTransparent));
        }
    }

    private void checkCollapse(){
        appBarLayout.addOnOffsetChangedListener(new AppBarStateChangedListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                Log.d(getClass().getCanonicalName(), state.name());
                if (state.name().equalsIgnoreCase("COLLAPSED")){
                    tv_page_title.setVisibility(View.VISIBLE);
                    btn_takepicture.setVisibility(View.VISIBLE);
                    tv_page_title.setText("Edit Pipeline");
                }
                else {
                    tv_page_title.setVisibility(View.GONE);
                    btn_takepicture.setVisibility(View.GONE);
                    tv_page_title.setText("");
                }
            }
        });
    }

    private void openProdukDialog(String title, String segmen, String product){
        DialogSelect.display(getSupportFragmentManager(), title, segmen, product, this);
    }
    private void openKeyValueDialog(String title){
        DialogKeyValue.display(getSupportFragmentManager(), title, this);
    }
    private void openAddressDialog(){
        DialogAddress.display(getSupportFragmentManager(), this);
    }
    private void openCameraMenu(){
        BSBottomCamera.display(getSupportFragmentManager(), this);
    }


    private void onclickSelectDialog(){
        //SEGMEN
        et_segmen.setFocusable(false);
        et_segmen.setInputType(InputType.TYPE_NULL);
        et_segmen.setOnClickListener(this);
        et_segmen.setOnFocusChangeListener(this);
        tf_segmen.setOnClickListener(this);
        tf_segmen.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProdukDialog(tf_segmen.getLabelText().toString().trim(), "", "");
            }
        });

        //PRODUCT
        et_produk.setFocusable(false);
        et_produk.setInputType(InputType.TYPE_NULL);
        et_produk.setOnFocusChangeListener(this);
        tf_produk.setOnClickListener(this);
        tf_produk.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!et_segmen.getText().toString().trim().isEmpty()){
                    openProdukDialog(tf_produk.getLabelText().toString().trim(), et_segmen.getText().toString().trim(), "");
                }
                else{
                    AppUtil.notifwarning(PipelineEditActivity.this, findViewById(android.R.id.content), getString(R.string.title_pleaseselect)+" "+tf_segmen.getLabelText()+" terlebih dahulu");
                }
            }
        });

        //PROGRAM
        et_program.setFocusable(false);
        et_program.setInputType(InputType.TYPE_NULL);
        et_program.setOnFocusChangeListener(this);
        tf_program.setOnClickListener(this);
        tf_program.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!et_segmen.getText().toString().trim().isEmpty() && !et_produk.getText().toString().trim().isEmpty()){
                    openProdukDialog(tf_program.getLabelText().toString().trim(), et_segmen.getText().toString().trim(), et_produk.getText().toString().trim());
                }
                else{
                    AppUtil.notifwarning(PipelineEditActivity.this, findViewById(android.R.id.content), getString(R.string.title_pleaseselect)+" "+tf_segmen.getLabelText()+" atau "+tf_produk.getLabelText()+" terlebih dahulu");
                }
            }
        });

        //TANGGAL LAHIR
        et_tanggallahir.setFocusable(false);
        et_tanggallahir.setInputType(InputType.TYPE_NULL);
        et_tanggallahir.setOnFocusChangeListener(this);
        tf_tanggallahir.setOnClickListener(this);
        tf_tanggallahir.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePickerTanggalLahir();
            }
        });

        //JENIS USAHA
        et_jenisusaha.setFocusable(false);
        et_jenisusaha.setInputType(InputType.TYPE_NULL);
        et_jenisusaha.setOnFocusChangeListener(this);
        tf_jenisusaha.setOnClickListener(this);
        tf_jenisusaha.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!et_segmen.getText().toString().trim().isEmpty()) {
                    openKeyValueDialog(tf_jenisusaha.getLabelText().toString().trim());
                }
                else {
                    AppUtil.notifwarning(PipelineEditActivity.this, findViewById(android.R.id.content), getString(R.string.title_pleaseselect)+" "+tf_segmen.getLabelText()+" terlebih dahulu");
                }
            }
        });

        //ALAMAT
        et_provinsi.setInputType(InputType.TYPE_NULL);
        et_provinsi.setFocusable(false);
        et_kota.setInputType(InputType.TYPE_NULL);
        et_kota.setFocusable(false);
        et_kecamatan.setInputType(InputType.TYPE_NULL);
        et_kecamatan.setFocusable(false);
        et_kelurahan.setInputType(InputType.TYPE_NULL);
        et_kelurahan.setFocusable(false);
        et_kodepos.setInputType(InputType.TYPE_NULL);
        et_kodepos.setFocusable(false);
    }

    private void onChangeText(){
        et_plafond.addTextChangedListener(new NumberTextWatcherForThousand(et_plafond));
        et_pendapatan.addTextChangedListener(new NumberTextWatcherForThousand(et_pendapatan));
        et_tindaklanjut.addTextChangedListener(this);
    }


    private void datePickerTanggalLahir(){
        calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, -18);
        final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        final SimpleDateFormat formatServer = new SimpleDateFormat("yyyy-MM-dd", Locale.US);

        DatePickerDialog.OnDateSetListener ls_tanggallahir = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String tanggallahir = sdf.format(calendar.getTime());
                et_tanggallahir.setText(tanggallahir);
                if(!et_tanggallahir.getText().toString().trim().isEmpty()){
                    val_tanggallahir = formatServer.format(calendar.getTime()); //set value tanggal lahir
                }
            }
        };

        dp_tanggallahir = new DatePickerDialog(PipelineEditActivity.this, R.style.AppTheme_TimePickerTheme, ls_tanggallahir, calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        dp_tanggallahir.getDatePicker().setMaxDate(calendar.getTimeInMillis());
        dp_tanggallahir.getDatePicker().setMinDate(calendarMinDate.getTimeInMillis());
        dp_tanggallahir.show();
    }

    //TAKE PICTURE
    public void openGalery(){
        Intent it = new Intent(Intent.ACTION_GET_CONTENT);
        it.setType("image/*");
        startActivityForResult(Intent.createChooser(it, "Select File"), PICK_IMAGE);
    }

    private void openCamera() {
        checkCameraPermission();
    }

    private void openPreviewPhoto(){
        DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Foto", bitmapPhoto);
    }

    private static final int MY_CAMERA_REQUEST_CODE = 100;
    public void checkCameraPermission()
    {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},
                    MY_CAMERA_REQUEST_CODE);
        } else {
            Uri outputFileUri = getCaptureImageOutputUri();
            Intent captureIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            captureIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
            startActivityForResult(captureIntent, TAKE_PICTURE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == MY_CAMERA_REQUEST_CODE)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                AppUtil.showToastLong(this, "Camera Permission Granted");
                Uri outputFileUri = getCaptureImageOutputUri();
                Intent captureIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
                startActivityForResult(captureIntent, TAKE_PICTURE);
            }
            else {
                AppUtil.showToastLong(this, "Camera Permission Denied");
            }
        }
    }

    private Uri getCaptureImageOutputUri() {
        Uri outputFileUri = null;
        File getImage = getExternalCacheDir();
        if (getImage != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
                outputFileUri = FileProvider.getUriForFile(PipelineEditActivity.this, BuildConfig.APPLICATION_ID + ".provider", new File(getImage.getPath(), "fotonasabah.png"));
            }
            else{
                outputFileUri = Uri.fromFile(new File(getImage.getPath(), "fotonasabah.png"));
            }
        }
        return outputFileUri;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            initImageFileName();
            if (getPickImageResultUri(data) != null) {
                uriPhoto = getPickImageResultUri(data);
                try {
                    bitmapPhoto = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uriPhoto);
                    bitmapPhoto = AppUtil.getResizedBitmap(bitmapPhoto, 1024);
                    bitmapPhoto = AppUtil.rotateImageIfRequired(this, bitmapPhoto, uriPhoto);
                    iv_photo.setImageBitmap(bitmapPhoto);
                    CHANGE_PICTURE = 1; //FLAG CHANGE PICTURE
                    hasFace = AppUtil.hasFaceImage(bitmapPhoto);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public Uri getPickImageResultUri(Intent data) {
        boolean isCamera = true;
        if (data != null) {
            String action = data.getAction();
            isCamera = action != null && action.equals(MediaStore.ACTION_IMAGE_CAPTURE);
        }
        return isCamera ? getCaptureImageOutputUri() : data.getData();
    }

    private void initImageFileName() {
        String fileName = new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date())+"_"+System.currentTimeMillis()+ ".jpg";
        appPreferences.setFotoNasabahName(fileName);
    }

    public void processInputPipeline(){
        loading.setVisibility(View.VISIBLE);
        String filename = appPreferences.getFotoNasabahName();
        if (CHANGE_PICTURE == 1){
            ImageHandler.saveImageToCache(this, bitmapPhoto, filename);
            File imageFile = new File(getApplicationContext().getCacheDir(), filename);
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), imageFile);
            MultipartBody.Part fileBody = MultipartBody.Part.createFormData("file", imageFile.getName(), requestBody);
            Call<ParseResponse> call = apiClientAdapter.getApiInterface().uploadFoto(fileBody);
            call.enqueue(new Callback<ParseResponse>() {
                @Override
                public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                    try {
                        if (response.isSuccessful()){
                            if (response.body().getStatus().equalsIgnoreCase("00")){
                                sendData(response.body().getData().get("id").getAsInt());
                            }
                            else{
                                loading.setVisibility(View.GONE);
                                AppUtil.notiferror(PipelineEditActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                            }
                        }
                        else {
                            loading.setVisibility(View.GONE);
                            Error error = ParseResponseError.confirmEror(response.errorBody());
                            AppUtil.notiferror(PipelineEditActivity.this, findViewById(android.R.id.content), error.getMessage());
                        }
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<ParseResponse> call, Throwable t) {
                    loading.setVisibility(View.GONE);
                    AppUtil.notiferror(PipelineEditActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
                }
            });
        }
        else {
            sendData(dataPL.getFid_photo());
        }
    }

    private void sendData(int idPhoto){
        val_nama = et_nama.getText().toString().trim();
        val_nik = et_nik.getText().toString().trim();
        val_nomorhp = et_nomorhp.getText().toString().trim();
        val_tempatlahir = et_tempatlahir.getText().toString().trim();
        val_pendapatan = NumberTextWatcherForThousand.trimCommaOfString(et_pendapatan.getText().toString().trim());
        val_alamat = et_alamat.getText().toString().trim();
        val_provinsi = et_provinsi.getText().toString().trim();
        val_kota = et_kota.getText().toString().trim();
        val_kecamatan = et_kecamatan.getText().toString().trim();
        val_kelurahan = et_kelurahan.getText().toString().trim();
        val_kodepos = et_kodepos.getText().toString().trim();
        val_rt = et_rt.getText().toString().trim();
        val_rw = et_rw.getText().toString().trim();

        val_tenor = et_tenor.getText().toString().trim();
        val_plafond = NumberTextWatcherForThousand.trimCommaOfString(et_plafond.getText().toString().trim());
        val_texttindaklanjut = et_tindaklanjut.getText().toString().trim();
        inputPipeline req = new inputPipeline(String.valueOf(appPreferences.getUid()), dataPL.getId(), val_nama, val_nik, val_nomorhp, val_tempatlahir, val_tanggallahir,
                idPhoto, val_gps, val_jenisusaha, AppUtil.parseLongWithDefault(val_pendapatan, 0),
                val_alamat, val_provinsi, val_kota, val_kelurahan, val_kecamatan, val_kodepos, val_rt, val_rw,
                AppUtil.parseIntWithDefault(val_plafond, 0), AppUtil.parseIntWithDefault(val_tenor, 0), val_segmen, val_produk, AppUtil.parseIntWithDefault(val_program, 0), val_texttindaklanjut, val_jenistindaklanjut);

        req.setKodeCabang(appPreferences.getKodeKantor());

        Call<ParseResponse> call = apiClientAdapter.getApiInterface().sendDataPipeline(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                loading.setVisibility(View.GONE);
                try {
                    if(response.isSuccessful()){
                        if(response.body().getStatus().equalsIgnoreCase("00")){
                            CustomDialog.DialogSuccess(PipelineEditActivity.this, "Success!", "Edit data Pipeline sukses", PipelineEditActivity.this);
                        }
                        else{
                            AppUtil.notiferror(PipelineEditActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(PipelineEditActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                AppUtil.notiferror(PipelineEditActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    private boolean validateForm(){
        if(et_segmen.getText().toString().trim().isEmpty() || et_segmen.getText().toString().trim().equalsIgnoreCase("")){
            tf_segmen.setError(tf_segmen.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return false;
        }
        else if(et_produk.getText().toString().trim().isEmpty() || et_produk.getText().toString().trim().equalsIgnoreCase("")){
            tf_produk.setError(tf_produk.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return false;
        }

        else if(tf_program.getVisibility() == View.VISIBLE && et_program.getText().toString().trim().isEmpty()){
            tf_program.setError(tf_program.getLabelText()+" "+getString(R.string.title_validate_field), true);
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

        else if(Validator.validateKtp(et_nik.getText().toString().trim()) == false){
            tf_nik.setError("Format "+ tf_nik.getLabelText()+" "+getString(R.string.title_invalid_field), true);
            return false;
        }

        else if(et_nama.getText().toString().trim().isEmpty() || et_nama.getText().toString().trim().equalsIgnoreCase("")){
            tf_nama.setError(tf_nama.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return false;
        }
        else if(Validator.validateNomorHp(et_nomorhp.getText().toString().trim()) == false){
            tf_nomorhp.setError("Format "+ tf_nomorhp.getLabelText()+" "+getString(R.string.title_invalid_field), true);
            return false;
        }
        else if(et_jenisusaha.getText().toString().trim().isEmpty() || et_jenisusaha.getText().toString().trim().equalsIgnoreCase("")){
            tf_jenisusaha.setError(tf_jenisusaha.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return false;
        }
        else if(et_pendapatan.getText().toString().trim().isEmpty() || et_pendapatan.getText().toString().trim().equalsIgnoreCase("")){
            tf_pendapatan.setError(tf_pendapatan.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return false;
        }
        else if(!et_tindaklanjut.getText().toString().trim().isEmpty() && val_jenistindaklanjut.isEmpty()){
            AppUtil.notifwarning(PipelineEditActivity.this, findViewById(android.R.id.content), "Pilih jenis tindak lanjut");
            return false;
        }

        else if(!val_jenistindaklanjut.isEmpty() && et_tindaklanjut.getText().toString().trim().isEmpty()){
            AppUtil.notifwarning(PipelineEditActivity.this, findViewById(android.R.id.content), "Isi deskripsi tindak lanjut");
            return false;
        }
        else if(bitmapPhoto == null){
            AppUtil.notifwarning(PipelineEditActivity.this, findViewById(android.R.id.content), "Silahkan pilih / ambil Foto");
            return false;
        }
        else if(hasFace == 0){
            AppUtil.notifwarning(PipelineEditActivity.this, findViewById(android.R.id.content), getString(R.string.title_nohasface));
            return false;
        }
        return true;
    }

    @Override
    public void onSelectMenuCamera(String idMenu) {
        switch (idMenu) {
            case "Take Photo":
                openCamera();
                break;
            case "Pick Photo":
                openGalery();
                break;
        }
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
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_pickaddress:
                openAddressDialog();
                break;

            case R.id.btn_send:
                if(validateForm())
                    processInputPipeline();
//                    Toast.makeText(this, "Foto Tidak Diupload, dipantek idnya 0", Toast.LENGTH_SHORT).show();
//                sendData((0));
                break;

            //SEGMEN
            case R.id.et_segmen:
                openProdukDialog(tf_segmen.getLabelText().toString().trim(), "", "");
                break;
            case R.id.tf_segmen:
                openProdukDialog(tf_segmen.getLabelText().toString().trim(), "", "");
                break;

            //PRODUK
            case R.id.et_produk:
                if (!et_segmen.getText().toString().trim().isEmpty()){
                    openProdukDialog(tf_produk.getLabelText().toString().trim(), et_segmen.getText().toString().trim(), "");
                }
                else{
                    AppUtil.notifwarning(PipelineEditActivity.this, findViewById(android.R.id.content), getString(R.string.title_pleaseselect)+" "+tf_segmen.getLabelText()+" terlebih dahulu");
                }
                break;
            case R.id.tf_produk:
                if (!et_segmen.getText().toString().trim().isEmpty()){
                    openProdukDialog(tf_produk.getLabelText().toString().trim(), et_segmen.getText().toString().trim(), "");
                }
                else{
                    AppUtil.notifwarning(PipelineEditActivity.this, findViewById(android.R.id.content), getString(R.string.title_pleaseselect)+" "+tf_segmen.getLabelText()+" terlebih dahulu");
                }
                break;

            //PROGRAM
            case R.id.et_program:
                if (!et_segmen.getText().toString().trim().isEmpty() && !et_produk.getText().toString().trim().isEmpty()){
                    openProdukDialog(tf_program.getLabelText().toString().trim(), et_segmen.getText().toString().trim(), et_produk.getText().toString().trim());
                }
                else{
                    AppUtil.notifwarning(PipelineEditActivity.this, findViewById(android.R.id.content), getString(R.string.title_pleaseselect)+" "+tf_segmen.getLabelText()+" atau "+tf_produk.getLabelText()+" terlebih dahulu");
                }
                break;
            case R.id.tf_program:
                if (!et_segmen.getText().toString().trim().isEmpty() && !et_produk.getText().toString().trim().isEmpty()){
                    openProdukDialog(tf_program.getLabelText().toString().trim(), et_segmen.getText().toString().trim(), et_produk.getText().toString().trim());
                }
                else{
                    AppUtil.notifwarning(PipelineEditActivity.this, findViewById(android.R.id.content), getString(R.string.title_pleaseselect)+" "+tf_segmen.getLabelText()+" atau "+tf_produk.getLabelText()+" terlebih dahulu");
                }
                break;

            //TANGGAL LAHIR
            case R.id.et_tanggallahir:
                datePickerTanggalLahir();
                break;
            case R.id.tf_tanggallahir:
                datePickerTanggalLahir();
                break;

            //JENIS USAHA
            case R.id.et_jenisusaha:
                if (!et_segmen.getText().toString().trim().isEmpty()) {
                    openKeyValueDialog(tf_jenisusaha.getLabelText().toString().trim());
                }
                else {
                    AppUtil.notifwarning(PipelineEditActivity.this, findViewById(android.R.id.content), getString(R.string.title_pleaseselect)+" "+tf_segmen.getLabelText()+" terlebih dahulu");
                }
                break;
            case R.id.tf_jenisusaha:
                if (!et_segmen.getText().toString().trim().isEmpty()) {
                    openKeyValueDialog(tf_jenisusaha.getLabelText().toString().trim());
                }
                else {
                    AppUtil.notifwarning(PipelineEditActivity.this, findViewById(android.R.id.content), getString(R.string.title_pleaseselect)+" "+tf_segmen.getLabelText()+" terlebih dahulu");
                }
                break;

            //TAKE PICTURE
            case R.id.btn_takepicture:
            case R.id.btnfab_takepicture :
                openCameraMenu();
                break;
            case R.id.iv_photo:
                openPreviewPhoto();
                break;
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus){
            switch (v.getId()){

                //SEGMEN
                case R.id.et_segmen:
                    openProdukDialog(tf_segmen.getLabelText().toString().trim(), "", "");
                    break;

                //PRODUK
                case R.id.et_produk:
                    if (!et_segmen.getText().toString().trim().isEmpty()){
                        openProdukDialog(tf_produk.getLabelText().toString().trim(), et_segmen.getText().toString().trim(), "");
                    }
                    else{
                        AppUtil.notifwarning(PipelineEditActivity.this, findViewById(android.R.id.content), getString(R.string.title_pleaseselect)+" "+tf_segmen.getLabelText()+" terlebih dahulu");
                    }
                    break;

                //PROGRAM
                case R.id.et_program:
                    if (!et_segmen.getText().toString().trim().isEmpty() && !et_produk.getText().toString().trim().isEmpty()){
                        openProdukDialog(tf_program.getLabelText().toString().trim(), et_segmen.getText().toString().trim(), et_produk.getText().toString().trim());
                    }
                    else{
                        AppUtil.notifwarning(PipelineEditActivity.this, findViewById(android.R.id.content), getString(R.string.title_pleaseselect)+" "+tf_segmen.getLabelText()+" atau "+tf_produk.getLabelText()+" terlebih dahulu");
                    }
                    break;

                //TANGGAL LAHIR
                case R.id.et_tanggallahir:
                    datePickerTanggalLahir();
                    break;

                //JENIS USAHA
                case R.id.et_jenisusaha:
                    if (!et_segmen.getText().toString().trim().isEmpty()) {
                        openKeyValueDialog(tf_jenisusaha.getLabelText().toString().trim());
                    }
                    else {
                        AppUtil.notifwarning(PipelineEditActivity.this, findViewById(android.R.id.content), getString(R.string.title_pleaseselect)+" "+tf_segmen.getLabelText()+" terlebih dahulu");
                    }
                    break;
            }
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (et_tindaklanjut.getText().toString().trim().isEmpty() || et_tindaklanjut.getText().toString().trim().equalsIgnoreCase("")){
            rg_typetindaklanjut.clearCheck();
            val_jenistindaklanjut = "";
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onAddressSelect(address data) {
        et_provinsi.setText(data.getProvinsi());
        et_kota.setText(data.getKota());
        et_kecamatan.setText(data.getKecamatan());
        et_kelurahan.setText(data.getKelurahan());
        et_kodepos.setText(data.getKodepos());
    }

    @Override
    public void onProductSelect(String title, ProductPojo data) {
        if (title.equalsIgnoreCase("segmen")){
            et_segmen.setText(data.getNama_segmen());
            val_segmen = data.getKode_segmen(); //set value segmen
            et_produk.getText().clear();
            et_program.getText().clear();
            et_jenisusaha.getText().clear();
            if(data.getNama_segmen().equalsIgnoreCase("konsumer")){
                tf_program.setVisibility(View.VISIBLE);
                tf_jenisusaha.setLabelText("Pekerjaan");
                tf_pendapatan.setLabelText("Pendapatan");
            }
            else if (data.getNama_segmen().equalsIgnoreCase("mikro")){
                tf_program.setVisibility(View.GONE);
                tf_jenisusaha.setLabelText("Bidang Usaha");
                tf_pendapatan.setLabelText("Omset Per Bulan");
            }
        }
        else if(title.equalsIgnoreCase("produk")){
            et_produk.setText(data.getNama_produk());
            val_produk = data.getKode_produk(); //set value produk
            et_program.getText().clear();
        }

        else if(title.equalsIgnoreCase("program")){
            et_program.setText(data.getNama_gimmick());
            val_program = String.valueOf(data.getKode_gimmick()); //set value program
        }
    }

    @Override
    public void onKeyValueSelect(String title, keyvalue data) {
        if(title.equalsIgnoreCase("Bidang usaha") || title.equalsIgnoreCase("pekerjaan")){
            et_jenisusaha.setText(data.getName());
            val_jenisusaha = data.getValue(); //set value jenis usaha/pekerjaan
        }
    }
}
