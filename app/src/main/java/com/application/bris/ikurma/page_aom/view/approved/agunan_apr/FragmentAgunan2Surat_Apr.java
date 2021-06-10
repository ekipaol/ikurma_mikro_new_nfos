package com.application.bris.ikurma.page_aom.view.approved.agunan_apr;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.application.bris.ikurma.R;
import com.application.bris.ikurma.api.config.UriApi;
import com.application.bris.ikurma.database.AppPreferences;
import com.application.bris.ikurma.page_aom.dialog.DialogPreviewPhoto;
import com.application.bris.ikurma.page_aom.model.AgunanTanahBangunan;
import com.application.bris.ikurma.util.AppUtil;
import com.bumptech.glide.Glide;
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
 * A simple {@link Fragment} subclass.
 */

/**
 * Created by PID on 6/15/2019.
 */

@SuppressLint("ValidFragment")
public class FragmentAgunan2Surat_Apr extends Fragment implements Step{

    @BindView(R.id.tf_jenis_surat)
    TextFieldBoxes tf_jenis_surat;
    @BindView(R.id.et_jenis_surat)
    ExtendedEditText et_jenis_surat;

    @BindView(R.id.tf_no_sertifikat)
    TextFieldBoxes tf_no_sertifikat;
    @BindView(R.id.et_no_sertifikat)
    ExtendedEditText et_no_sertifikat;

    @BindView(R.id.tf_atas_nama_sertifikat)
    TextFieldBoxes tf_atas_nama_sertifikat;
    @BindView(R.id.et_atas_nama_sertifikat)
    ExtendedEditText et_atas_nama_sertifikat;

    @BindView(R.id.tf_tanggal_terbit_sertifikat)
    TextFieldBoxes tf_tanggal_terbit_sertifikat;
    @BindView(R.id.et_tanggal_terbit_sertifikat)
    ExtendedEditText et_tanggal_terbit_sertifikat;

    @BindView(R.id.tf_luas_tanah_sertifikat)
    TextFieldBoxes tf_luas_tanah_sertifikat;
    @BindView(R.id.et_luas_tanah_sertifikat)
    ExtendedEditText et_luas_tanah_sertifikat;

    @BindView(R.id.progressbar_loading)
    RelativeLayout loading;

    @BindView(R.id.img_bpn)
    RoundedImageView img_bpn;
    @BindView(R.id.btn_upload_bpn)
    ImageView btn_upload_bpn;
    AppPreferences appPreferences;

    public static SimpleDateFormat dateClient = new SimpleDateFormat("dd-MM-yyyy", Locale.US);

    private AgunanTanahBangunan dataAgunan;
    private String idAgunan;
    public FragmentAgunan2Surat_Apr() {
    }

    public FragmentAgunan2Surat_Apr(String midAgunan, AgunanTanahBangunan magunanTanahBangunan) {
        dataAgunan = magunanTanahBangunan;
        idAgunan = midAgunan;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.agunan_2_identifikasi_surat, container, false);
        ButterKnife.bind(this,view);
        appPreferences=new AppPreferences(getContext());
        if(!idAgunan.equalsIgnoreCase("0")) {
            setDisable();
            setData();
        }
        return view;
    }

    private void setDisable(){
        img_bpn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogPreviewPhoto.display(getFragmentManager(), "Preview Foto", ((RoundedDrawable)img_bpn.getDrawable()).getSourceBitmap());
            }
        });
        et_jenis_surat.setEnabled(false);
        et_no_sertifikat.setEnabled(false);
        et_atas_nama_sertifikat.setEnabled(false);
        et_tanggal_terbit_sertifikat.setEnabled(false);
        et_luas_tanah_sertifikat.setEnabled(false);
        btn_upload_bpn.setVisibility(View.GONE);
    }

    private void setData(){
        try {
            et_jenis_surat.setText(dataAgunan.getJenisSuratTanah());
            et_no_sertifikat.setText(dataAgunan.getNoSertifikat());
            et_atas_nama_sertifikat.setText(dataAgunan.getAtasNamaSertifikat());
            et_tanggal_terbit_sertifikat.setText(AppUtil.parseTanggalGeneral(dataAgunan.getTanggalSertifikat(), "ddMMyyyy", "dd-MM-yyyy"));
            et_luas_tanah_sertifikat.setText(dataAgunan.getLuasTanahSertifikat());

            final int id_bpn = dataAgunan.getIdPhotoTBbpn();

            String ImgBpn = UriApi.Baseurl.URL + UriApi.foto.urlPhoto + id_bpn;

            GlideUrl glideUrl = new GlideUrl(ImgBpn, new LazyHeaders.Builder()
                    .addHeader("Authorization", "Bearer "+appPreferences.getToken())
                    .build());

            Glide
                    .with(getContext())
                    .asBitmap()
                    .load(glideUrl)
                    .into(new SimpleTarget<Bitmap>() {
                        @Override
                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                            img_bpn.setImageBitmap(resource);
                        }
                    });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public VerificationError verifyStep() {
        return  null;
    }

    @Override
    public void onSelected() {
    }

    @Override
    public void onError(@NonNull VerificationError verificationError) {

    }

    private void openPreviewPhotoBpn(){
        DialogPreviewPhoto.display(getActivity().getSupportFragmentManager(), "Preview Foto", ((BitmapDrawable)img_bpn.getDrawable()).getBitmap());
    }
}

