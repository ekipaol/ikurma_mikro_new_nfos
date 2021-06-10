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
import com.application.bris.ikurma.page_aom.dialog.DialogPreviewPhoto;
import com.application.bris.ikurma.page_aom.model.AgunanKendaraan;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
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
public class FragmentAgunanKendaraan4_Apr extends Fragment implements Step, View.OnClickListener{

    @BindView(R.id.tf_nama_informan)
    TextFieldBoxes tf_nama_informan;
    @BindView(R.id.et_nama_informan)
    EditText et_nama_informan;

    @BindView(R.id.tf_alamat_informan)
    TextFieldBoxes tf_alamat_informan;
    @BindView(R.id.et_alamat_informan)
    EditText et_alamat_informan;

    @BindView(R.id.tf_telp_informan)
    TextFieldBoxes tf_telp_informan;
    @BindView(R.id.et_telp_informan)
    EditText et_telp_informan;

    @BindView(R.id.tf_keterangan)
    TextFieldBoxes tf_keterangan;
    @BindView(R.id.et_keterangan)
    EditText et_keterangan;

    @BindView(R.id.img_agunan_1)
    RoundedImageView img_agunan_1;
    @BindView(R.id.btn_upload_agunan_1)
    ImageView btn_upload_agunan_1;

    @BindView(R.id.img_agunan_2)
    RoundedImageView img_agunan_2;
    @BindView(R.id.btn_upload_agunan_2)
    ImageView btn_upload_agunan_2;

    @BindView(R.id.img_agunan_3)
    RoundedImageView img_agunan_3;
    @BindView(R.id.btn_upload_agunan_3)
    ImageView btn_upload_agunan_3;

    @BindView(R.id.progressbar_loading)
    RelativeLayout loading;

    private AgunanKendaraan dataAgunan;
    private String idAgunan;


    private Bitmap bitmapPhotoAgunan1, bitmapPhotoAgunan2, bitmapPhotoAgunan3, loadedPicture;
    public FragmentAgunanKendaraan4_Apr() {
    }

    public FragmentAgunanKendaraan4_Apr(String midAgunan, AgunanKendaraan magunanData) {
        dataAgunan = magunanData;
        idAgunan = midAgunan;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.agunan_kendaraan_4, container, false);
        ButterKnife.bind(this, view);
        if (!idAgunan.equalsIgnoreCase("0")) {
            setDisable();
            setData();
        }
        return view;
    }

    private void setDisable(){
        img_agunan_1.setOnClickListener(this);
        img_agunan_2.setOnClickListener(this);
        img_agunan_3.setOnClickListener(this);
        et_nama_informan.setEnabled(false);
        et_alamat_informan.setEnabled(false);
        et_telp_informan.setEnabled(false);
        et_keterangan.setEnabled(false);
        btn_upload_agunan_1.setVisibility(View.GONE);
        btn_upload_agunan_2.setVisibility(View.GONE);
        btn_upload_agunan_3.setVisibility(View.GONE);
    }


    private void setData() {
        try {
            et_nama_informan.setText(dataAgunan.getNamaPemberiInfo1());
            et_alamat_informan.setText(dataAgunan.getAlamatPemberiInfo1());
            et_telp_informan.setText(dataAgunan.getNoTelpPemberiInfo1());
            et_keterangan.setText(dataAgunan.getKeterangan());

            bitmapPhotoAgunan1 = setLoadImage(img_agunan_1, dataAgunan.getIdPhotoKDUtama());
            bitmapPhotoAgunan2 = setLoadImage(img_agunan_2, dataAgunan.getIdPhotoKDInterior());
            bitmapPhotoAgunan3 = setLoadImage(img_agunan_3, dataAgunan.getIdPhotoKDMesin());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public Bitmap setLoadImage(final ImageView iv, int idFoto){
        String url_photo = UriApi.Baseurl.URL + UriApi.foto.urlPhoto + idFoto;
        Glide
                .with(getContext())
                .asBitmap()
                .load(url_photo)
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
            case R.id.img_agunan_1:
                DialogPreviewPhoto.display(getFragmentManager(), "Preview Foto", ((RoundedDrawable)img_agunan_1.getDrawable()).getSourceBitmap());
                break;
            case R.id.img_agunan_2:
                DialogPreviewPhoto.display(getFragmentManager(), "Preview Foto", ((RoundedDrawable)img_agunan_2.getDrawable()).getSourceBitmap());
                break;
            case R.id.img_agunan_3:
                DialogPreviewPhoto.display(getFragmentManager(), "Preview Foto", ((RoundedDrawable)img_agunan_3.getDrawable()).getSourceBitmap());
                break;
        }
    }
}




