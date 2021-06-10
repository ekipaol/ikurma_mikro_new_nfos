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
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.application.bris.ikurma.R;
import com.application.bris.ikurma.api.config.UriApi;
import com.application.bris.ikurma.database.AppPreferences;
import com.application.bris.ikurma.page_aom.dialog.DialogPreviewPhoto;
import com.application.bris.ikurma.page_aom.model.AgunanKendaraan;
import com.bumptech.glide.Glide;
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
import studio.carbonylgroup.textfieldboxes.ExtendedEditText;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

/**
 * A simple {@link Fragment} subclass.
 */

/**
 * Created by PID on 6/15/2019.
 */

@SuppressLint("ValidFragment")
public class FragmentAgunanKendaraan2_Apr extends Fragment implements Step{
    //VIEW BINDING
    @BindView(R.id.tf_hub_pemilik_dengan_nasabah)
    TextFieldBoxes tf_hub_pemilik_dengan_nasabah;
    @BindView(R.id.et_hub_pemilik_dengan_nasabah)
    ExtendedEditText et_hub_pemilik_dengan_nasabah;
    @BindView(R.id.tf_no_faktur)
    TextFieldBoxes tf_no_faktur;
    @BindView(R.id.et_no_faktur)
    ExtendedEditText et_no_faktur;
    @BindView(R.id.tf_no_mesin)
    TextFieldBoxes tf_no_mesin;
    @BindView(R.id.et_no_mesin)
    ExtendedEditText et_no_mesin;
    @BindView(R.id.tf_bukti_gesek_mesin)
    TextFieldBoxes tf_bukti_gesek_mesin;
    @BindView(R.id.et_bukti_gesek_mesin)
    ExtendedEditText et_bukti_gesek_mesin;
    @BindView(R.id.tf_no_rangka)
    TextFieldBoxes tf_no_rangka;
    @BindView(R.id.et_no_rangka)
    ExtendedEditText et_no_rangka;
    @BindView(R.id.tf_bukti_gesek_rangka)
    TextFieldBoxes tf_bukti_gesek_rangka;
    @BindView(R.id.et_bukti_gesek_rangka)
    ExtendedEditText et_bukti_gesek_rangka;
    @BindView(R.id.tf_no_polisi)
    TextFieldBoxes tf_no_polisi;
    @BindView(R.id.et_no_polisi)
    ExtendedEditText et_no_polisi;
    @BindView(R.id.tf_plat_kuning)
    TextFieldBoxes tf_plat_kuning;
    @BindView(R.id.et_plat_kuning)
    ExtendedEditText et_plat_kuning;
    @BindView(R.id.tf_no_bpkb)
    TextFieldBoxes tf_no_bpkb;
    @BindView(R.id.et_no_bpkb)
    ExtendedEditText et_no_bpkb;
    @BindView(R.id.tf_no_stnk)
    TextFieldBoxes tf_no_stnk;
    @BindView(R.id.et_no_stnk)
    ExtendedEditText et_no_stnk;
    @BindView(R.id.tf_warna)
    TextFieldBoxes tf_warna;
    @BindView(R.id.et_warna)
    ExtendedEditText et_warna;
    @BindView(R.id.tf_thn_pembuatan)
    TextFieldBoxes tf_thn_pembuatan;
    @BindView(R.id.et_thn_pembuatan)
    ExtendedEditText et_thn_pembuatan;
    @BindView(R.id.img_bpkb)
    RoundedImageView img_bpkb;
    @BindView(R.id.btn_upload_bpkb)
    ImageView btn_upload_bpkb;
    @BindView(R.id.progressbar_loading)
    RelativeLayout loading;
    //END OF VIEW BINDING
    private String idAgunan;
    private AgunanKendaraan dataAgunan;

    AppPreferences appPreferences;

    public FragmentAgunanKendaraan2_Apr() {
    }
    public FragmentAgunanKendaraan2_Apr(String midAgunan, AgunanKendaraan magunanData) {
        dataAgunan = magunanData;
        idAgunan = midAgunan;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.agunan_kendaraan_2, container, false);
        ButterKnife.bind(this,view);
        if(!String.valueOf(idAgunan).equalsIgnoreCase("0")) {
            setDisable();
            setData();
        }

        appPreferences=new AppPreferences(getContext());
        return view;
    }

    private void setDisable(){
        img_bpkb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogPreviewPhoto.display(getFragmentManager(), "Preview Foto", ((RoundedDrawable)img_bpkb.getDrawable()).getSourceBitmap());
            }
        });
        et_hub_pemilik_dengan_nasabah.setEnabled(false);
        et_no_faktur.setEnabled(false);
        et_no_mesin.setEnabled(false);
        et_bukti_gesek_mesin.setEnabled(false);
        et_no_rangka.setEnabled(false);
        et_bukti_gesek_rangka.setEnabled(false);
        et_no_polisi.setEnabled(false);
        et_plat_kuning.setEnabled(false);
        et_no_bpkb.setEnabled(false);
        et_no_stnk.setEnabled(false);
        et_warna.setEnabled(false);
        et_thn_pembuatan.setEnabled(false);
        btn_upload_bpkb.setVisibility(View.GONE);
    }

    private void setData(){
        try {
            et_hub_pemilik_dengan_nasabah.setText(dataAgunan.getHubungan());
            et_no_faktur.setText(dataAgunan.getNoFaktur());
            et_no_mesin.setText(dataAgunan.getNoMesin());
            et_bukti_gesek_mesin.setText(dataAgunan.getBuktiGesekMesin());
            et_no_rangka.setText(dataAgunan.getNoRangka());
            et_bukti_gesek_rangka.setText(dataAgunan.getBuktiGesekRangka());
            et_no_polisi.setText(dataAgunan.getNoPolisi());
            et_plat_kuning.setText(dataAgunan.getJenisPlat());
            et_no_bpkb.setText(dataAgunan.getNoBKPB());
            et_no_stnk.setText(dataAgunan.getNoSTNK());
            et_warna.setText(dataAgunan.getWarna());
            et_thn_pembuatan.setText(dataAgunan.getTahunPembuatan());
            final int id_bpn = dataAgunan.getIdPhotoKDBPKB();

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
                            img_bpkb.setImageBitmap(resource);
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
        return null;
    }

    @Override
    public void onSelected() {
    }

    @Override
    public void onError(@NonNull VerificationError verificationError) {

    }
}

