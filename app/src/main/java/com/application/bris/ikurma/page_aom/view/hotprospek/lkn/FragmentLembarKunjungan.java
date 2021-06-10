package com.application.bris.ikurma.page_aom.view.hotprospek.lkn;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
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
import androidx.fragment.app.Fragment;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.application.bris.ikurma.BuildConfig;
import com.application.bris.ikurma.R;
import com.application.bris.ikurma.api.config.UriApi;
import com.application.bris.ikurma.api.model.Error;
import com.application.bris.ikurma.api.model.ParseResponse;
import com.application.bris.ikurma.api.model.ParseResponseError;
import com.application.bris.ikurma.api.service.ApiClientAdapter;
import com.application.bris.ikurma.database.AppPreferences;
import com.application.bris.ikurma.database.pojo.LknPojo;
import com.application.bris.ikurma.page_aom.dialog.BSBottomCamera;
import com.application.bris.ikurma.page_aom.dialog.DialogKeyValue;
import com.application.bris.ikurma.page_aom.dialog.DialogPreviewPhoto;
import com.application.bris.ikurma.page_aom.listener.CameraListener;
import com.application.bris.ikurma.page_aom.listener.KeyValueListener;
import com.application.bris.ikurma.page_aom.model.DataLkn;
import com.application.bris.ikurma.page_aom.model.keyvalue;
import com.application.bris.ikurma.util.AppUtil;
import com.application.bris.ikurma.util.ImageHandler;
import com.application.bris.ikurma.util.KeyValue;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.makeramen.roundedimageview.RoundedDrawable;
import com.makeramen.roundedimageview.RoundedImageView;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import studio.carbonylgroup.textfieldboxes.ExtendedEditText;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

/**
 * Created by PID on 6/15/2019.
 */

@SuppressLint("ValidFragment")
public class FragmentLembarKunjungan extends Fragment implements Step, KeyValueListener, View.OnClickListener, CameraListener{

    @BindView(R.id.tf_tanggalkunjungan)
    TextFieldBoxes tf_tanggalkunjungan;
    @BindView(R.id.et_tanggalkunjungan)
    EditText et_tanggalkunjungan;
    @BindView(R.id.tf_statuspermohonan)
    TextFieldBoxes tf_statuspermohonan;
    @BindView(R.id.et_statuspermohonan)
    EditText et_statuspermohonan;
    @BindView(R.id.tf_namaorangyangditemui)
    TextFieldBoxes tf_namaorangyangditemui;
    @BindView(R.id.et_namaorangyangditemui)
    EditText et_namaorangyangditemui;
    @BindView(R.id.tf_hubungan)
    TextFieldBoxes tf_hubungan;
    @BindView(R.id.et_hubungan)
    EditText et_hubungan;


    @BindView(R.id.tf_bidangusaha)
    TextFieldBoxes tf_bidangusaha;
    @BindView(R.id.et_bidangusaha)
    EditText et_bidangusaha;
    @BindView(R.id.tf_namausaha)
    TextFieldBoxes tf_namausaha;
    @BindView(R.id.et_namausaha)
    EditText et_namausaha;
    @BindView(R.id.tf_lamausaha)
    TextFieldBoxes tf_lamausaha;
    @BindView(R.id.et_lamausaha)
    ExtendedEditText et_lamausaha;
    @BindView(R.id.tf_nomortelponusaha)
    TextFieldBoxes tf_nomortelponusaha;
    @BindView(R.id.et_nomortelponusaha)
    EditText et_nomortelponusaha;

    @BindView(R.id.tf_alamatusaha)
    TextFieldBoxes tf_alamatusaha;
    @BindView(R.id.et_alamatusaha)
    EditText et_alamatusaha;
    @BindView(R.id.tf_lokasiusaha)
    TextFieldBoxes tf_lokasiusaha;
    @BindView(R.id.et_lokasiusaha)
    EditText et_lokasiusaha;
    @BindView(R.id.tf_statustempatusaha)
    TextFieldBoxes tf_statustempatusaha;
    @BindView(R.id.et_statustempatusaha)
    EditText et_statustempatusaha;
    @BindView(R.id.tf_jenistempatusaha)
    TextFieldBoxes tf_jenistempatusaha;
    @BindView(R.id.et_jenistempatusaha)
    EditText et_jenistempatusaha;
    @BindView(R.id.tf_aspekpemasaran)
    TextFieldBoxes tf_aspekpemasaran;
    @BindView(R.id.et_aspekpemasaran)
    EditText et_aspekpemasaran;
    @BindView(R.id.tf_jenisusaha)
    TextFieldBoxes tf_jenisusaha;
    @BindView(R.id.et_jenisusaha)
    EditText et_jenisusaha;
    @BindView(R.id.tf_jaraklokasiusahakeums)
    TextFieldBoxes tf_jaraklokasiusahakeums;
    @BindView(R.id.et_jaraklokasiusahakeums)
    EditText et_jaraklokasiusahakeums;

    @BindView(R.id.iv_fotokunjungan1)
    RoundedImageView iv_fotokunjungan1;
    @BindView(R.id.btn_fotokunjungan1)
    ImageView btn_fotokunjungan1;
    @BindView(R.id.iv_fotokunjungan2)
    RoundedImageView iv_fotokunjungan2;
    @BindView(R.id.btn_fotokunjungan2)
    ImageView btn_fotokunjungan2;
    @BindView(R.id.iv_fotokunjungan3)
    RoundedImageView iv_fotokunjungan3;
    @BindView(R.id.btn_fotokunjungan3)
    ImageView btn_fotokunjungan3;

    @BindView(R.id.progressbar_loading)
    RelativeLayout loading;

    private Realm realm;
    private Calendar cal;

    private DatePickerDialog dpTanggal;

    public static SimpleDateFormat dateClient = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
    public static SimpleDateFormat dateServer = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
    private DataLkn data;

    private String labelLamausaha = "";

    private String isSelectPhoto = "";
    private final int TAKE_PICTURE_FOTOKUNJUNGAN1 = 1;
    private final int TAKE_PICTURE_FOTOKUNJUNGAN2 = 2;
    private final int TAKE_PICTURE_FOTOKUNJUNGAN3 = 3;
    private final int PICK_PICTURE_FOTOKUNJUNGAN1 = 11;
    private final int PICK_PICTURE_FOTOKUNJUNGAN2 = 22;
    private final int PICK_PICTURE_FOTOKUNJUNGAN3 = 33;
    private Uri uri_fotokunjungan1, uri_fotokunjungan2, uri_fotokunjungan3;
    private Bitmap bitmap_fotokunjungan1, bitmap_fotokunjungan2, bitmap_fotokunjungan3, loadedPicture;


    public static String val_tanggalKunjungan ="";
    public static String val_statusPermohonan ="";
    public static String val_namaOrangyangditemui ="";
    public static String val_hubungan ="";
    public static String val_lokasiUsaha ="";
    public static String val_statusTempatUsaha = "";
    public static String val_jenisTempatUsaha ="";
    public static String val_aspekPemasaran ="";
    public static String val_jenisUsaha ="";
    public static String val_jarakLokasiUsahakeUms ="";

    public static int val_fotokunjungan1 = 0;
    public static int val_fotokunjungan2 = 0;
    public static int val_fotokunjungan3 = 0;

    private AppPreferences appPreferences;
    private ApiClientAdapter apiClientAdapter;

    public FragmentLembarKunjungan() {
    }

    public FragmentLembarKunjungan(DataLkn mdata) {
        data = mdata;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ao_fragment_lembarkunjungan, container, false);
        ButterKnife.bind(this, view);
        realm = Realm.getDefaultInstance();
        appPreferences = new AppPreferences(getContext());
        apiClientAdapter = new ApiClientAdapter(getContext());
        onSelectDialog();
        setData();

        return view;
    }

    private void setData(){
        try {
            if (data.getiDLKN2() != null) {
                et_tanggalkunjungan.setText(AppUtil.parseTanggalGeneral(data.gettANGGALPENILAIAN(), "ddMMyyyy", "dd-MM-yyyy"));
                et_statuspermohonan.setText(data.getsTATUSPERMOHONAN());
                et_namaorangyangditemui.setText(data.getnAMAORANGDITEMUI());
                et_hubungan.setText(data.gethUBUNGAN());
                et_lokasiusaha.setText(data.getlOKASIUSAHA());
                et_statustempatusaha.setText(data.getsTATUSTEMPATUSAHA());
                et_jenistempatusaha.setText(data.getjENISTEMPATUSAHA());
                et_aspekpemasaran.setText(data.getaSPEKPEMASARAN());
                et_jenisusaha.setText(data.getjENISUSAHA());
                et_jaraklokasiusahakeums.setText(String.valueOf(data.getjARAKLOKASI()));

                bitmap_fotokunjungan1 = setLoadImage(iv_fotokunjungan1, data.getfIDPHOTODEPAN());
                bitmap_fotokunjungan2 = setLoadImage(iv_fotokunjungan2, data.getfIDPHOTODALAM());
                bitmap_fotokunjungan3 = setLoadImage(iv_fotokunjungan3, data.getfIDPHOTOLINGKUNGAN());

                val_fotokunjungan1 = data.getfIDPHOTODEPAN();
                val_fotokunjungan2 = data.getfIDPHOTODALAM();
                val_fotokunjungan3 = data.getfIDPHOTOLINGKUNGAN();
            }

            et_bidangusaha.setText(KeyValue.getKeyUsahaorJob(data.getbIDANGUSAHA()));


            et_namausaha.setText(data.nAMAUSAHA);
            et_lamausaha.setText(String.valueOf(parseLamaUsaha()));
            et_lamausaha.setSuffix(labelLamausaha);
            et_nomortelponusaha.setText(data.gettELPKANTOR());
            et_alamatusaha.setText(data.getaLAMATTEMPATKERJA1());
//
//            if(BuildConfig.IS_PRODUCTION==false){
//                et_bidangusaha.setText(KeyValue.getKeyUsahaorJob("1110"));
//                et_namausaha.setText("usaha1");
//                et_lamausaha.setText(String.valueOf(parseLamaUsahaDummy()));
//                et_lamausaha.setSuffix(labelLamausaha);
//                et_nomortelponusaha.setText("080989999");
//                et_alamatusaha.setText("jalan kenangan");
//                Toast.makeText(getContext(), "ada pantekan data usaha", Toast.LENGTH_SHORT).show();
//            }

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    private void dpTanggal(){
        cal = Calendar.getInstance();
        Calendar calMin = Calendar.getInstance();
        calMin.add(Calendar.YEAR, -1);
        DatePickerDialog.OnDateSetListener ls_tanggalLahir = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                cal.set(Calendar.YEAR, year);
                cal.set(Calendar.MONTH, month);
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String calTanggalKunjunganString = dateClient.format(cal.getTime());
                et_tanggalkunjungan.setText(calTanggalKunjunganString);
            }
        };

        dpTanggal = new DatePickerDialog(getContext(), R.style.AppTheme_TimePickerTheme, ls_tanggalLahir, cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
        dpTanggal.getDatePicker().setMinDate(calMin.getTimeInMillis());
        dpTanggal.getDatePicker().setMaxDate(cal.getTimeInMillis());
        dpTanggal.show();
    }

    private void onSelectDialog(){

        iv_fotokunjungan1.setOnClickListener(this);
        iv_fotokunjungan2.setOnClickListener(this);
        iv_fotokunjungan3.setOnClickListener(this);

        btn_fotokunjungan1.setOnClickListener(this);
        btn_fotokunjungan2.setOnClickListener(this);
        btn_fotokunjungan3.setOnClickListener(this);

//      TANGGAL KUNJUNGAN
        et_tanggalkunjungan.setFocusable(false);
        et_tanggalkunjungan.setInputType(InputType.TYPE_NULL);
        et_tanggalkunjungan.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                dpTanggal();
            }
        });
        tf_tanggalkunjungan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dpTanggal();
            }
        });
        tf_tanggalkunjungan.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dpTanggal();
            }
        });

        //STATUS PERMOHONAN
        et_statuspermohonan.setFocusable(false);
        et_statuspermohonan.setInputType(InputType.TYPE_NULL);
        et_statuspermohonan.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                openKeyValueDialog(tf_statuspermohonan.getLabelText().toString().trim());
            }
        });
        tf_statuspermohonan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKeyValueDialog(tf_statuspermohonan.getLabelText().toString().trim());
            }
        });
        tf_statuspermohonan.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKeyValueDialog(tf_statuspermohonan.getLabelText().toString().trim());
            }
        });


        //HUBUNGAN
        et_hubungan.setFocusable(false);
        et_hubungan.setInputType(InputType.TYPE_NULL);
        et_hubungan.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                openKeyValueDialog(tf_hubungan.getLabelText().toString().trim());
            }
        });
        tf_hubungan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKeyValueDialog(tf_hubungan.getLabelText().toString().trim());
            }
        });
        tf_hubungan.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKeyValueDialog(tf_hubungan.getLabelText().toString().trim());
            }
        });


        //LOKASI USAHA
        et_lokasiusaha.setFocusable(false);
        et_lokasiusaha.setInputType(InputType.TYPE_NULL);
        et_lokasiusaha.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                openKeyValueDialog(tf_lokasiusaha.getLabelText().toString().trim());
            }
        });
        tf_lokasiusaha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKeyValueDialog(tf_lokasiusaha.getLabelText().toString().trim());
            }
        });
        tf_lokasiusaha.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKeyValueDialog(tf_lokasiusaha.getLabelText().toString().trim());
            }
        });

        //STATUS TEMPAT USAHA
        et_statustempatusaha.setFocusable(false);
        et_statustempatusaha.setInputType(InputType.TYPE_NULL);
        et_statustempatusaha.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                openKeyValueDialog(tf_statustempatusaha.getLabelText().toString().trim());
            }
        });
        tf_statustempatusaha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKeyValueDialog(tf_statustempatusaha.getLabelText().toString().trim());
            }
        });
        tf_statustempatusaha.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKeyValueDialog(tf_statustempatusaha.getLabelText().toString().trim());
            }
        });

        //JENIS TEMPAT USAHA
        et_jenistempatusaha.setFocusable(false);
        et_jenistempatusaha.setInputType(InputType.TYPE_NULL);
        et_jenistempatusaha.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                openKeyValueDialog(tf_jenistempatusaha.getLabelText().toString().trim());
            }
        });
        tf_jenistempatusaha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKeyValueDialog(tf_jenistempatusaha.getLabelText().toString().trim());
            }
        });
        tf_jenistempatusaha.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKeyValueDialog(tf_jenistempatusaha.getLabelText().toString().trim());
            }
        });



        //ASPEK PEMASARAN
        et_aspekpemasaran.setFocusable(false);
        et_aspekpemasaran.setInputType(InputType.TYPE_NULL);
        et_aspekpemasaran.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                openKeyValueDialog(tf_aspekpemasaran.getLabelText().toString().trim());
            }
        });
        tf_aspekpemasaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKeyValueDialog(tf_aspekpemasaran.getLabelText().toString().trim());
            }
        });
        tf_aspekpemasaran.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKeyValueDialog(tf_aspekpemasaran.getLabelText().toString().trim());
            }
        });

        //JENIS USAHA
        et_jenisusaha.setFocusable(false);
        et_jenisusaha.setInputType(InputType.TYPE_NULL);
        et_jenisusaha.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                openKeyValueDialog(tf_jenisusaha.getLabelText().toString().trim());
            }
        });
        tf_jenisusaha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKeyValueDialog(tf_jenisusaha.getLabelText().toString().trim());
            }
        });
        tf_jenisusaha.getEndIconImageButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openKeyValueDialog(tf_jenisusaha.getLabelText().toString().trim());
            }
        });

        et_bidangusaha.setInputType(InputType.TYPE_NULL);
        et_bidangusaha.setFocusable(false);
        et_namausaha.setInputType(InputType.TYPE_NULL);
        et_namausaha.setFocusable(false);
        et_lamausaha.setInputType(InputType.TYPE_NULL);
        et_lamausaha.setFocusable(false);
        et_nomortelponusaha.setInputType(InputType.TYPE_NULL);
        et_nomortelponusaha.setFocusable(false);
        et_alamatusaha.setInputType(InputType.TYPE_NULL);
        et_alamatusaha.setFocusable(false);
    }

    public Bitmap setLoadImage(final ImageView iv, int idFoto){
        String url_photo = UriApi.Baseurl.URL + UriApi.foto.urlPhoto + idFoto;

        GlideUrl glideUrl = new GlideUrl(url_photo, new LazyHeaders.Builder()
                .addHeader("Authorization", "Bearer "+appPreferences.getToken())
                .build());

        Glide
                .with(getContext())
                .asBitmap()
                .load(glideUrl)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        iv.setImageBitmap(resource);
                        loadedPicture = resource;
                    }
                });
        return loadedPicture;
    }

    private void openKeyValueDialog(String title){
        DialogKeyValue.display(getFragmentManager(), title, this);
    }

    private VerificationError validateForm(){
        if (et_tanggalkunjungan.getText().toString().isEmpty() || et_tanggalkunjungan.getText().toString().equalsIgnoreCase("")){
            tf_tanggalkunjungan.setError("Format "+ tf_tanggalkunjungan.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_tanggalkunjungan.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_statuspermohonan.getText().toString().isEmpty() || et_statuspermohonan.getText().toString().equalsIgnoreCase("")){
            tf_statuspermohonan.setError("Format "+ tf_statuspermohonan.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_statuspermohonan.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_namaorangyangditemui.getText().toString().isEmpty() || et_namaorangyangditemui.getText().toString().equalsIgnoreCase("")){
            tf_namaorangyangditemui.setError("Format "+ tf_namaorangyangditemui.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_namaorangyangditemui.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_hubungan.getText().toString().isEmpty() || et_hubungan.getText().toString().equalsIgnoreCase("")){
            tf_hubungan.setError("Format "+ tf_hubungan.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_hubungan.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_lokasiusaha.getText().toString().isEmpty() || et_lokasiusaha.getText().toString().equalsIgnoreCase("")){
            tf_lokasiusaha.setError("Format "+ tf_lokasiusaha.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_lokasiusaha.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_statustempatusaha.getText().toString().isEmpty() || et_statustempatusaha.getText().toString().equalsIgnoreCase("")){
            tf_statustempatusaha.setError("Format "+ tf_statustempatusaha.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_statustempatusaha.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_jenistempatusaha.getText().toString().isEmpty() || et_jenistempatusaha.getText().toString().equalsIgnoreCase("")){
            tf_jenistempatusaha.setError("Format "+ tf_jenistempatusaha.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_jenistempatusaha.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_aspekpemasaran.getText().toString().isEmpty() || et_aspekpemasaran.getText().toString().equalsIgnoreCase("")){
            tf_aspekpemasaran.setError("Format "+ tf_aspekpemasaran.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_aspekpemasaran.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_jenisusaha.getText().toString().isEmpty() || et_jenisusaha.getText().toString().equalsIgnoreCase("")){
            tf_jenisusaha.setError("Format "+ tf_jenisusaha.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_jenisusaha.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_jaraklokasiusahakeums.getText().toString().isEmpty() || et_jaraklokasiusahakeums.getText().toString().equalsIgnoreCase("")){
            tf_jaraklokasiusahakeums.setError("Format "+ tf_jaraklokasiusahakeums.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_jaraklokasiusahakeums.getLabelText()+" "+getString(R.string.title_validate_field));
        }
        else if(val_fotokunjungan1 == 0){
            return new VerificationError("Silahkan pilih / ambil Foto Kunjungan");
        }
        else if(val_fotokunjungan2 == 0){
            return new VerificationError("Silahkan pilih / ambil Foto Kunjungan");
        }
        else if(val_fotokunjungan3 == 0){
            return new VerificationError("Silahkan pilih / ambil Foto Kunjungan");
        }

        else {
            setDataOnListerner();
            return null;
        }
    }

    public void setDataOnListerner(){
        try {
            val_tanggalKunjungan = (AppUtil.parseTanggalGeneral(et_tanggalkunjungan.getText().toString().trim(), "dd-MM-yyyy", "ddMMyyyy"));
            val_statusPermohonan = (et_statuspermohonan.getText().toString().trim());
            val_namaOrangyangditemui = (et_namaorangyangditemui.getText().toString().trim());
            val_hubungan = (et_hubungan.getText().toString().trim());
            val_lokasiUsaha = (et_lokasiusaha.getText().toString().trim());
            val_statusTempatUsaha = (et_statustempatusaha.getText().toString().trim());
            val_jenisTempatUsaha = (et_jenistempatusaha.getText().toString().trim());
            val_aspekPemasaran = (et_aspekpemasaran.getText().toString().trim());
            val_jenisUsaha = (et_jenisusaha.getText().toString().trim());
            val_jarakLokasiUsahakeUms = (et_jaraklokasiusahakeums.getText().toString().trim());


            final LknPojo d = new LknPojo();
            d.setUuid(LknActivity.UUIDR);
            d.setfIDAPLIKASI(String.valueOf(LknActivity.idAplikasi));
            d.setfIDCIFLAS(LknActivity.cif);
            d.settANGGALPENILAIAN(val_tanggalKunjungan);
            d.setsTATUSPERMOHONAN(val_statusPermohonan);
            d.setnAMAORANGDITEMUI(val_namaOrangyangditemui);
            d.sethUBUNGAN(val_hubungan);
            d.setbIDANGUSAHA(data.getbIDANGUSAHA());
            d.setnAMAUSAHA(data.getnAMAUSAHA());
            d.setlAMAUSAHA(parseLamaUsaha());
            d.settELEPON(data.gettELPKANTOR());
            d.setaLAMATUSAHA(data.getaLAMATTEMPATKERJA1());
            d.setlOKASIUSAHA(val_lokasiUsaha);
            d.setsTATUSTEMPATUSAHA(val_statusTempatUsaha);
            d.setjENISTEMPATUSAHA(val_jenisTempatUsaha);
            d.setaSPEKPEMASARAN(val_aspekPemasaran);
            d.setjENISUSAHA(val_jenisUsaha);
            d.setjARAKLOKASI(AppUtil.parseLongWithDefault(val_jarakLokasiUsahakeUms, 0));
            d.setfIDPHOTODEPAN(val_fotokunjungan1);
            d.setfIDPHOTODALAM(val_fotokunjungan2);
            d.setfIDPHOTOLINGKUNGAN(val_fotokunjungan3);

            realm.executeTransaction (new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.insertOrUpdate(d);
                }
            });

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }


    public long parseLamaUsaha(){
        try {
            String val = (data.getlAMABEKERJA());

            String d1 = val.substring(0, 2);
            String d2 = val.substring(2,4);


            if (!d2.equalsIgnoreCase("00")){
                labelLamausaha = " Tahun";
                return AppUtil.parseLongWithDefault(d2, 0);
            }
            else if (d2.equalsIgnoreCase("00") && !d1.equalsIgnoreCase("00")){
                labelLamausaha = " Bulan";
                return AppUtil.parseLongWithDefault(d1, 0);
            }
            else {
                return 0;
            }
        }
        catch (Exception e){
            return 0;
        }
    }

    public long parseLamaUsahaDummy(){
        try {
            String val = ("0106");
            String d1 = val.substring(0, 2);
            String d2 = val.substring(2,4);

            if (!d2.equalsIgnoreCase("00")){
                labelLamausaha = " Tahun";
                return AppUtil.parseLongWithDefault(d2, 0);
            }
            else if (d2.equalsIgnoreCase("00") && !d1.equalsIgnoreCase("00")){
                labelLamausaha = " Bulan";
                return AppUtil.parseLongWithDefault(d1, 0);
            }
            else {
                return 0;
            }
        }
        catch (Exception e){
            return 0;
        }
    }


    @Nullable
    @Override
    public VerificationError verifyStep() {
        return  validateForm();
    }

    @Override
    public void onSelected() {
        LknPojo dataRealm = realm.where(LknPojo.class).equalTo("uuid", LknActivity.UUIDR).findFirst();
        if (dataRealm != null)
        {
            bitmap_fotokunjungan1 = setLoadImage(iv_fotokunjungan1, dataRealm.getfIDPHOTODEPAN());
            bitmap_fotokunjungan2 = setLoadImage(iv_fotokunjungan2, dataRealm.getfIDPHOTODALAM());
            bitmap_fotokunjungan3 = setLoadImage(iv_fotokunjungan3, dataRealm.getfIDPHOTOLINGKUNGAN());

            val_fotokunjungan1 = dataRealm.getfIDPHOTODEPAN();
            val_fotokunjungan2 = dataRealm.getfIDPHOTODALAM();
            val_fotokunjungan3 = dataRealm.getfIDPHOTOLINGKUNGAN();
        }
    }

    @Override
    public void onError(@NonNull VerificationError verificationError) {
        AppUtil.notiferror(getContext(), getActivity().findViewById(android.R.id.content), verificationError.getErrorMessage());
    }

    @Override
    public void onKeyValueSelect(String title, keyvalue data) {
        if (title.equalsIgnoreCase(tf_statuspermohonan.getLabelText().toString().trim())){
            et_statuspermohonan.setText(data.getName());
        }
        else if (title.equalsIgnoreCase(tf_hubungan.getLabelText().toString().trim())){
            et_hubungan.setText(data.getName());
        }
        else if (title.equalsIgnoreCase(tf_lokasiusaha.getLabelText().toString().trim())){
            et_lokasiusaha.setText(data.getName());
        }
        else if (title.equalsIgnoreCase(tf_statustempatusaha.getLabelText().toString().trim())){
            et_statustempatusaha.setText(data.getName());
        }
        else if (title.equalsIgnoreCase(tf_jenistempatusaha.getLabelText().toString().trim())){
            et_jenistempatusaha.setText(data.getName());
        }
        else if (title.equalsIgnoreCase(tf_aspekpemasaran.getLabelText().toString().trim())){
            et_aspekpemasaran.setText(data.getName());
        }
        else if (title.equalsIgnoreCase(tf_jenisusaha.getLabelText().toString().trim())){
            et_jenisusaha.setText(data.getName());
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btn_fotokunjungan1 :
                isSelectPhoto = "fotokunjungan1";
                BSBottomCamera.displayWithTitle(getFragmentManager(), this, "Foto Kunjungan1");
                break;
            case R.id.btn_fotokunjungan2 :
                isSelectPhoto = "fotokunjungan2";
                BSBottomCamera.displayWithTitle(getFragmentManager(), this, "Foto Kunjungan2");
                break;
            case R.id.btn_fotokunjungan3 :
                isSelectPhoto = "fotokunjungan3";
                BSBottomCamera.displayWithTitle(getFragmentManager(), this, "Foto Kunjungan3");
                break;

            case R.id.iv_fotokunjungan1:
                DialogPreviewPhoto.display(getFragmentManager(), "Preview Foto", ((RoundedDrawable)iv_fotokunjungan1.getDrawable()).getSourceBitmap());
                break;
            case R.id.iv_fotokunjungan2:
                DialogPreviewPhoto.display(getFragmentManager(), "Preview Foto", ((RoundedDrawable)iv_fotokunjungan2.getDrawable()).getSourceBitmap());
                break;
            case R.id.iv_fotokunjungan3:
                DialogPreviewPhoto.display(getFragmentManager(), "Preview Foto", ((RoundedDrawable)iv_fotokunjungan3.getDrawable()).getSourceBitmap());
                break;
        }
    }

    @Override
    public void onSelectMenuCamera(String idMenu) {
        if (isSelectPhoto.equalsIgnoreCase("fotokunjungan1")){
            switch (idMenu) {
                case "Take Photo":
                    openCamera(TAKE_PICTURE_FOTOKUNJUNGAN1);
                    break;
                case "Pick Photo":
                    openGalery(PICK_PICTURE_FOTOKUNJUNGAN1);
                    break;
            }
        }
        else if (isSelectPhoto.equalsIgnoreCase("fotokunjungan2")){
            switch (idMenu) {
                case "Take Photo":
                    openCamera(TAKE_PICTURE_FOTOKUNJUNGAN2);
                    break;
                case "Pick Photo":
                    openGalery(PICK_PICTURE_FOTOKUNJUNGAN2);
                    break;
            }
        }
        else if (isSelectPhoto.equalsIgnoreCase("fotokunjungan3")){
            switch (idMenu) {
                case "Take Photo":
                    openCamera(TAKE_PICTURE_FOTOKUNJUNGAN3);
                    break;
                case "Pick Photo":
                    openGalery(PICK_PICTURE_FOTOKUNJUNGAN3);
                    break;
            }
        }
    }


    /* ============= CAMERA ============ */

    private void openCamera(int cameraCode) {
        checkCameraPermission(cameraCode);
    }

    private static final int MY_CAMERA_REQUEST_CODE = 100;
    private static int CAMERA_CODE_FORE_PERMISSION = 0;
    public void checkCameraPermission(int cameraCode)
    {
        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            CAMERA_CODE_FORE_PERMISSION = cameraCode;
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA},
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
                AppUtil.showToastLong(getContext(), "Camera Permission Granted");
                directOpenCamera(CAMERA_CODE_FORE_PERMISSION);
            }
            else {
                AppUtil.showToastLong(getContext(), "Camera Permission Denied");
            }
        }
    }

    private void directOpenCamera(int cameraCode){
        Uri outputFileUri = getCaptureImageOutputUri();
        Intent captureIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        captureIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        captureIntent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri);
        startActivityForResult(captureIntent, cameraCode);
    }

    private Uri getCaptureImageOutputUri() {
        Uri outputFileUri = null;
        File getImage = getActivity().getExternalCacheDir();
        if (getImage != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                outputFileUri = FileProvider.getUriForFile(getContext(), BuildConfig.APPLICATION_ID + ".provider", new File(getImage.getPath(), "fotoagunan.png"));
            } else {
                outputFileUri = Uri.fromFile(new File(getImage.getPath(), "fotoagunan.png"));
            }
        }
        return outputFileUri;
    }


    /* =============== GALERY ============= */

    public void openGalery(int requestCode){
        Intent it = new Intent(Intent.ACTION_GET_CONTENT);
        it.setType("image/*");
        startActivityForResult(Intent.createChooser(it, "Select File"), requestCode);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {

            switch (requestCode) {

                case TAKE_PICTURE_FOTOKUNJUNGAN1:
                case PICK_PICTURE_FOTOKUNJUNGAN1:
                    setDataImage(uri_fotokunjungan1, iv_fotokunjungan1, data);
                    break;
                case TAKE_PICTURE_FOTOKUNJUNGAN2:
                case PICK_PICTURE_FOTOKUNJUNGAN2:
                    setDataImage(uri_fotokunjungan2, iv_fotokunjungan2, data);
                    break;
                case TAKE_PICTURE_FOTOKUNJUNGAN3:
                case PICK_PICTURE_FOTOKUNJUNGAN3:
                    setDataImage(uri_fotokunjungan3, iv_fotokunjungan3, data);
                    break;
            }
        }
    }

    private void initImageFileName() {
        String fileName = new SimpleDateFormat("ddMMyyyy_HHmmss").format(new Date()) + "_" + System.currentTimeMillis() + ".jpg";
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

    private void setDataImage(Uri uri, final ImageView iv, Intent data) {
        initImageFileName();
        if (getPickImageResultUri(data) != null) {
            uri = getPickImageResultUri(data);
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContext().getContentResolver(), getPickImageResultUri(data));
                bitmap = AppUtil.getResizedBitmap(bitmap, 1024);
                bitmap = AppUtil.rotateImageIfRequired(getContext(), bitmap, uri);

                if(iv == iv_fotokunjungan1){
                    bitmap_fotokunjungan1 = bitmap;
                }
                if(iv == iv_fotokunjungan2){
                    bitmap_fotokunjungan2 = bitmap;
                }
                if(iv == iv_fotokunjungan3){
                    bitmap_fotokunjungan3 = bitmap;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            String filename = appPreferences.getFotoAgunan();
            ImageHandler.saveImageToCache(getContext(), bitmap, filename);
            uploadFoto(filename);
            iv.setImageBitmap(bitmap);
        }
    }

    public void uploadFoto(String filename) {
        loading.setVisibility(View.VISIBLE);
        File imageFile = new File(getActivity().getApplicationContext().getCacheDir(), filename);
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), imageFile);
        MultipartBody.Part fileBody = MultipartBody.Part.createFormData("file", imageFile.getName(), requestBody);
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().uploadFoto(fileBody);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                loading.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()) {
                        if (response.body().getStatus().equalsIgnoreCase("00")) {
                            AppUtil.showToastShort(getContext(), "Foto berhasil diupload");
                            int idFoto = response.body().getData().get("id").getAsInt();
                            switch (isSelectPhoto){
                                case "fotokunjungan1":
                                    val_fotokunjungan1 = idFoto;
                                    break;
                                case "fotokunjungan2":
                                    val_fotokunjungan2 = idFoto;
                                    break;
                                case "fotokunjungan3":
                                    val_fotokunjungan3 = idFoto;
                                    break;
                            }
                        } else {
                            loading.setVisibility(View.GONE);
                            AppUtil.notiferror(getContext(), getActivity().findViewById(android.R.id.content), response.body().getMessage());
                        }
                    } else {
                        loading.setVisibility(View.GONE);
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(getContext(), getActivity().findViewById(android.R.id.content), error.getMessage());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                AppUtil.notiferror(getContext(), getActivity().findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }

        });
    }

}
