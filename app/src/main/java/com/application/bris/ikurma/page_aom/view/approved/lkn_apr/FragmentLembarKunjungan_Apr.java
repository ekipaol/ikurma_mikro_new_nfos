package com.application.bris.ikurma.page_aom.view.approved.lkn_apr;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.application.bris.ikurma.R;
import com.application.bris.ikurma.api.config.UriApi;
import com.application.bris.ikurma.database.AppPreferences;
import com.application.bris.ikurma.page_aom.dialog.DialogPreviewPhoto;
import com.application.bris.ikurma.page_aom.model.DataLkn;
import com.application.bris.ikurma.util.AppUtil;
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

import java.text.SimpleDateFormat;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import studio.carbonylgroup.textfieldboxes.ExtendedEditText;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

/**
 * Created by PID on 6/15/2019.
 */

@SuppressLint("ValidFragment")
public class FragmentLembarKunjungan_Apr extends Fragment implements Step, View.OnClickListener{

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

    public static SimpleDateFormat dateClient = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
    public static SimpleDateFormat dateServer = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
    private DataLkn data;

    private String labelLamausaha = "";
    private Bitmap bitmap_fotokunjungan1, bitmap_fotokunjungan2, bitmap_fotokunjungan3, loadedPicture;
    AppPreferences  appPreferences;

    public FragmentLembarKunjungan_Apr() {
    }

    public FragmentLembarKunjungan_Apr(DataLkn mdata) {
        data = mdata;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ao_fragment_lembarkunjungan, container, false);
        ButterKnife.bind(this, view);
        setDisable();
        setData();
        appPreferences= new AppPreferences(getContext());

        return view;
    }

    private void setDisable(){
        et_tanggalkunjungan.setEnabled(false);
        et_statuspermohonan.setEnabled(false);
        et_namaorangyangditemui.setEnabled(false);
        et_hubungan.setEnabled(false);
        et_bidangusaha.setEnabled(false);
        et_namausaha.setEnabled(false);
        et_lamausaha.setEnabled(false);
        et_nomortelponusaha.setEnabled(false);
        et_alamatusaha.setEnabled(false);
        et_lokasiusaha.setEnabled(false);
        et_statustempatusaha.setEnabled(false);
        et_jenistempatusaha.setEnabled(false);
        et_aspekpemasaran.setEnabled(false);
        et_jenisusaha.setEnabled(false);
        et_jaraklokasiusahakeums.setEnabled(false);
        iv_fotokunjungan1.setOnClickListener(this);
        iv_fotokunjungan2.setOnClickListener(this);
        iv_fotokunjungan3.setOnClickListener(this);
        btn_fotokunjungan1.setVisibility(View.GONE);
        btn_fotokunjungan2.setVisibility(View.GONE);
        btn_fotokunjungan3.setVisibility(View.GONE);
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
            }

            et_bidangusaha.setText(KeyValue.getKeyUsahaorJob(data.getbIDANGUSAHA()));
            et_namausaha.setText(data.nAMAUSAHA);
            et_lamausaha.setText(String.valueOf(parseLamaUsaha()));
            et_lamausaha.setSuffix(labelLamausaha);
            et_nomortelponusaha.setText(data.gettELPKANTOR());
            et_alamatusaha.setText(data.getaLAMATTEMPATKERJA1());
        }
        catch (Exception e){
            e.printStackTrace();
        }

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
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        iv.setImageBitmap(resource);
                        loadedPicture = resource;
                    }
                });
        return loadedPicture;
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


    @Nullable
    @Override
    public VerificationError verifyStep() {
        return  null;
    }

    @Override
    public void onSelected() {
        if (data.getiDLKN2() != null)
        {
            bitmap_fotokunjungan1 = setLoadImage(iv_fotokunjungan1, data.getfIDPHOTODEPAN());
            bitmap_fotokunjungan2 = setLoadImage(iv_fotokunjungan2, data.getfIDPHOTODALAM());
            bitmap_fotokunjungan3 = setLoadImage(iv_fotokunjungan3, data.getfIDPHOTOLINGKUNGAN());
        }
    }

    @Override
    public void onError(@NonNull VerificationError verificationError) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
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
}
