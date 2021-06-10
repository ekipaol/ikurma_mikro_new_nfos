package com.application.bris.ikurma.page_aom.view.approved.agunan_apr;

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
import android.widget.RelativeLayout;
import com.application.bris.ikurma.R;
import com.application.bris.ikurma.api.config.UriApi;
import com.application.bris.ikurma.database.AppPreferences;
import com.application.bris.ikurma.page_aom.dialog.DialogPreviewPhoto;
import com.application.bris.ikurma.page_aom.model.AgunanTanahBangunan;
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
import butterknife.BindView;
import butterknife.ButterKnife;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

/**
 * A simple {@link Fragment} subclass.
 */

/**
 * Created by PID on 6/15/2019.
 */

@SuppressLint("ValidFragment")
public class FragmentAgunan7Lain_Apr extends Fragment implements Step, View.OnClickListener{

    @BindView(R.id.tf_pendapat_kondisi_jaminan)
    TextFieldBoxes tf_pendapat_kondisi_jaminan;
    @BindView(R.id.et_pendapat_kondisi_jaminan)
    EditText et_pendapat_kondisi_jaminan;

    @BindView(R.id.img_agunan)
    RoundedImageView img_agunan;
    @BindView(R.id.btn_upload_agunan)
    ImageView btn_upload_agunan;

    @BindView(R.id.img_utara)
    RoundedImageView img_utara;
    @BindView(R.id.btn_upload_utara)
    ImageView btn_upload_utara;

    @BindView(R.id.img_selatan)
    RoundedImageView img_selatan;
    @BindView(R.id.btn_upload_selatan)
    ImageView btn_upload_selatan;

    @BindView(R.id.img_barat)
    RoundedImageView img_barat;
    @BindView(R.id.btn_upload_barat)
    ImageView btn_upload_barat;

    @BindView(R.id.img_timur)
    RoundedImageView img_timur;
    @BindView(R.id.btn_upload_timur)
    ImageView btn_upload_timur;

    @BindView(R.id.progressbar_loading)
    RelativeLayout loading;
    AppPreferences appPreferences;

    private AgunanTanahBangunan dataAgunan;
    private String idAgunan;
    private Bitmap bitmapPhotoAgunan, bitmapPhotoUtara, bitmapPhotoSelatan, bitmapPhotoBarat, bitmapPhotoTimur, loadedPicture;

    public FragmentAgunan7Lain_Apr() {
    }

    public FragmentAgunan7Lain_Apr(String midAgunan, AgunanTanahBangunan magunanTanahBangunan) {
        dataAgunan = magunanTanahBangunan;
        idAgunan = midAgunan;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.agunan_7_lain, container, false);
        ButterKnife.bind(this, view);
        if (!idAgunan.equalsIgnoreCase("0")) {
            setDisable();
            setData();
        }

        appPreferences=new AppPreferences(getContext());
        return view;
    }

    private void setDisable(){
        img_agunan.setOnClickListener(this);
        img_utara.setOnClickListener(this);
        img_selatan.setOnClickListener(this);
        img_timur.setOnClickListener(this);
        img_barat.setOnClickListener(this);
        et_pendapat_kondisi_jaminan.setEnabled(false);
        btn_upload_agunan.setVisibility(View.GONE);
        btn_upload_utara.setVisibility(View.GONE);
        btn_upload_selatan.setVisibility(View.GONE);
        btn_upload_barat.setVisibility(View.GONE);
        btn_upload_timur.setVisibility(View.GONE);
    }

    private void setData() {
        try {
            et_pendapat_kondisi_jaminan.setText(dataAgunan.getPendapatKondisiJaminan());
            bitmapPhotoAgunan = setLoadImage(img_agunan, dataAgunan.getIdPhotoTBbangunan());
            bitmapPhotoUtara = setLoadImage(img_utara, dataAgunan.getIdPhotoTButara());
            bitmapPhotoSelatan = setLoadImage(img_selatan, dataAgunan.getIdPhotoTBselatan());
            bitmapPhotoBarat = setLoadImage(img_barat, dataAgunan.getIdPhotoTBbarat());
            bitmapPhotoTimur = setLoadImage(img_timur, dataAgunan.getIdPhotoTBtimur());
        }
        catch (Exception e)
        {
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

    @Nullable
    @Override
    public VerificationError verifyStep() {
        return null;
    }

    @Override
    public void onSelected() {
    }

    @Override
    public void onError(@NonNull VerificationError verificationError) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_agunan:
                DialogPreviewPhoto.display(getFragmentManager(), "Preview Foto", ((RoundedDrawable)img_agunan.getDrawable()).getSourceBitmap());
                break;
            case R.id.img_utara:
                DialogPreviewPhoto.display(getFragmentManager(), "Preview Foto", ((RoundedDrawable)img_utara.getDrawable()).getSourceBitmap());
                break;
            case R.id.img_selatan:
                DialogPreviewPhoto.display(getFragmentManager(), "Preview Foto", ((RoundedDrawable)img_selatan.getDrawable()).getSourceBitmap());
                break;
            case R.id.img_timur:
                DialogPreviewPhoto.display(getFragmentManager(), "Preview Foto", ((RoundedDrawable)img_timur.getDrawable()).getSourceBitmap());
                break;
            case R.id.img_barat:
                DialogPreviewPhoto.display(getFragmentManager(), "Preview Foto", ((RoundedDrawable)img_barat.getDrawable()).getSourceBitmap());
                break;
        }
    }
}




