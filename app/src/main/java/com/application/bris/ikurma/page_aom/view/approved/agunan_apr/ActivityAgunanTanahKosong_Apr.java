package com.application.bris.ikurma.page_aom.view.approved.agunan_apr;


import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.application.bris.ikurma.R;
import com.application.bris.ikurma.api.config.UriApi;
import com.application.bris.ikurma.api.model.ParseResponseArr;
import com.application.bris.ikurma.api.model.request.agunan.ReqAgunanData;
import com.application.bris.ikurma.api.service.ApiClientAdapter;
import com.application.bris.ikurma.database.AppPreferences;
import com.application.bris.ikurma.page_aom.dialog.DialogPreviewPhoto;
import com.application.bris.ikurma.page_aom.model.AgunanTanahKosong;
import com.application.bris.ikurma.page_aom.view.hotprospek.agunan.ActivityAgunanTanahKosong;
import com.application.bris.ikurma.util.AppUtil;
import com.application.bris.ikurma.util.NumberTextWatcherCanNolForThousand;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.makeramen.roundedimageview.RoundedDrawable;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import javax.annotation.Nullable;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import studio.carbonylgroup.textfieldboxes.ExtendedEditText;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

public class ActivityAgunanTanahKosong_Apr extends AppCompatActivity implements View.OnClickListener{


    //EXTENDED EDITTEXT
    @BindView(R.id.et_collateral_id_syiar)
    ExtendedEditText et_collateral_id_syiar;
    @BindView(R.id.et_tanggal_pemeriksaan_tanah_kosong)
    ExtendedEditText et_tanggal_pemeriksaan_tanah_kosong;
    @BindView(R.id.btn_set_loc)
    Button btn_set_loc;
    @BindView(R.id.tv_set_loc)
    TextView tv_set_loc;
    @BindView(R.id.et_alamat_jaminan_tanah_kosong)
    ExtendedEditText et_alamat_jaminan_tanah_kosong;
    @BindView(R.id.et_kelurahan_dan_kecamatan_tanah_kosong)
    ExtendedEditText et_kelurahan_dan_kecamatan_tanah_kosong;
    @BindView(R.id.et_wilayah_dan_kota)
    ExtendedEditText et_wilayah_dan_kota;
    @BindView(R.id.et_hub_pemegang_hak_dengan_nasabah_tanah_kosong)
    ExtendedEditText et_hub_pemegang_hak_dengan_nasabah_tanah_kosong;
    @BindView(R.id.et_nama_penggarap)
    ExtendedEditText et_nama_penggarap;
    @BindView(R.id.et_nama_pemegang_hak)
    ExtendedEditText et_nama_pemegang_hak;
    @BindView(R.id.et_status_penggarap)
    ExtendedEditText et_status_penggarap;
    @BindView(R.id.et_hub_penggarap_dengan_pemegang_hak)
    ExtendedEditText et_hub_penggarap_dengan_pemegang_hak;
    @BindView(R.id.et_jenis_dokumen_tanah_kosong)
    ExtendedEditText et_jenis_dokumen_tanah_kosong;
    @BindView(R.id.et_no_bukti_hak)
    ExtendedEditText et_no_bukti_hak;
    @BindView(R.id.et_no_gs)
    ExtendedEditText et_no_gs;
    @BindView(R.id.et_jatuh_tempo_sertifikat)
    ExtendedEditText et_jatuh_tempo_sertifikat;
    @BindView(R.id.et_luas_tanah_tanah_kosong)
    ExtendedEditText et_luas_tanah_tanah_kosong;
    @BindView(R.id.et_harga_per_meter_tanah_kosong)
    ExtendedEditText et_harga_per_meter_tanah_kosong;
    @BindView(R.id.et_nilai_market_tanah_kosong)
    ExtendedEditText et_nilai_market_tanah_kosong;
    @BindView(R.id.et_nilai_likuidasi_tanah_kosong)
    ExtendedEditText et_nilai_likuidasi_tanah_kosong;
    @BindView(R.id.et_field_rekomendasi)
    ExtendedEditText et_field_rekomendasi;
    @BindView(R.id.et_nama_pemberi_informasi)
    ExtendedEditText et_nama_pemberi_informasi;
    @BindView(R.id.et_alamat_pemberi_informasi)
    ExtendedEditText et_alamat_pemberi_informasi;
    @BindView(R.id.et_no_pemberi_informasi)
    ExtendedEditText et_no_pemberi_informasi;
    @BindView(R.id.et_check_bpn_tanah_kosong)
    ExtendedEditText et_check_bpn_tanah_kosong;
    @BindView(R.id.et_dengan_siapa)
    ExtendedEditText et_dengan_siapa;
    @BindView(R.id.et_no_telp_bpn)
    ExtendedEditText et_no_telp_bpn;
    @BindView(R.id.et_hasil_bpn)
    ExtendedEditText et_hasil_bpn;
    @BindView(R.id.et_lebar_jalan_didepan_tanah_kosong)
    ExtendedEditText et_lebar_jalan_didepan_tanah_kosong;
    @BindView(R.id.et_pendapat_pemeriksa)
    ExtendedEditText et_pendapat_pemeriksa;


    //TEXTFIELDLAYOUT

    @BindView(R.id.tf_collateral_id_syiar)
    TextFieldBoxes tf_collateral_id_syiar;
    @BindView(R.id.tf_tanggal_pemeriksaan_tanah_kosong)
    TextFieldBoxes tf_tanggal_pemeriksaan_tanah_kosong;
    @BindView(R.id.tf_alamat_jaminan_tanah_kosong)
    TextFieldBoxes tf_alamat_jaminan_tanah_kosong;
    @BindView(R.id.tf_nama_pemegang_hak)
    TextFieldBoxes tf_nama_pemegang_hak;
    @BindView(R.id.tf_kelurahan_dan_kecamatan_tanah_kosong)
    TextFieldBoxes tf_kelurahan_dan_kecamatan_tanah_kosong;
    @BindView(R.id.tf_wilayah_dan_kota)
    TextFieldBoxes tf_wilayah_dan_kota;
    @BindView(R.id.tf_hub_pemegang_hak_dengan_nasabah_tanah_kosong)
    TextFieldBoxes tf_hub_pemegang_hak_dengan_nasabah_tanah_kosong;
    @BindView(R.id.tf_nama_penggarap)
    TextFieldBoxes tf_nama_penggarap;
    @BindView(R.id.tf_status_penggarap)
    TextFieldBoxes tf_status_penggarap;
    @BindView(R.id.tf_hub_penggarap_dengan_pemegang_hak)
    TextFieldBoxes tf_hub_penggarap_dengan_pemegang_hak;
    @BindView(R.id.tf_jenis_dokumen_tanah_kosong)
    TextFieldBoxes tf_jenis_dokumen_tanah_kosong;
    @BindView(R.id.tf_no_bukti_hak)
    TextFieldBoxes tf_no_bukti_hak;
    @BindView(R.id.tf_no_gs)
    TextFieldBoxes tf_no_gs;
    @BindView(R.id.tf_jatuh_tempo_sertifikat)
    TextFieldBoxes tf_jatuh_tempo_sertifikat;
    @BindView(R.id.tf_luas_tanah_tanah_kosong)
    TextFieldBoxes tf_luas_tanah_tanah_kosong;
    @BindView(R.id.tf_harga_per_meter_tanah_kosong)
    TextFieldBoxes tf_harga_per_meter_tanah_kosong;
    @BindView(R.id.tf_nilai_market_tanah_kosong)
    TextFieldBoxes tf_nilai_market_tanah_kosong;
    @BindView(R.id.tf_nilai_likuidasi_tanah_kosong)
    TextFieldBoxes tf_nilai_likuidasi_tanah_kosong;
    @BindView(R.id.tf_field_rekomendasi)
    TextFieldBoxes tf_field_rekomendasi;
    @BindView(R.id.tf_nama_pemberi_informasi)
    TextFieldBoxes tf_nama_pemberi_informasi;
    @BindView(R.id.tf_alamat_pemberi_informasi)
    TextFieldBoxes tf_alamat_pemberi_informasi;
    @BindView(R.id.tf_no_pemberi_informasi)
    TextFieldBoxes tf_no_pemberi_informasi;
    @BindView(R.id.tf_check_bpn_tanah_kosong)
    TextFieldBoxes tf_check_bpn_tanah_kosong;
    @BindView(R.id.tf_dengan_siapa)
    TextFieldBoxes tf_dengan_siapa;
    @BindView(R.id.tf_no_telp_bpn)
    TextFieldBoxes tf_no_telp_bpn;
    @BindView(R.id.tf_hasil_bpn)
    TextFieldBoxes tf_hasil_bpn;
    @BindView(R.id.tf_lebar_jalan_didepan_tanah_kosong)
    TextFieldBoxes tf_lebar_jalan_didepan_tanah_kosong;
    @BindView(R.id.tf_pendapat_pemeriksa)
    TextFieldBoxes tf_pendapat_pemeriksa;
    @BindView(R.id.btn_back)
    ImageView btn_back;
    @BindView(R.id.tv_page_title)
    TextView tb_regular;

    @BindView(R.id.bt_simpan_agunan_tanah_kosong)
    Button bt_simpan_agunan_tanah_kosong;

    @BindView(R.id.iv_foto1)
    ImageView iv_foto1;

    @BindView(R.id.img_bpn_tanah_kosong)
    ImageView img_bpn_tanah_kosong;

    @BindView(R.id.btn_upload_bpn_tanah_kosong)
    ImageView btn_upload_bpn_tanah_kosong;

    //button foto
    @BindView(R.id.btn_upload_agunan)
    ImageView btn_upload_agunan;
    @BindView(R.id.btn_upload_barat)
    ImageView btn_upload_barat;
    @BindView(R.id.btn_upload_selatan)
    ImageView btn_upload_selatan;
    @BindView(R.id.btn_upload_timur)
    ImageView btn_upload_timur;
    @BindView(R.id.btn_upload_utara)
    ImageView btn_upload_utara;

    @BindView(R.id.img_agunan)
    ImageView img_agunan;
    @BindView(R.id.img_barat)
    ImageView img_barat;
    @BindView(R.id.img_selatan)
    ImageView img_selatan;
    @BindView(R.id.img_timur)
    ImageView img_timur;
    @BindView(R.id.img_utara)
    ImageView img_utara;
    @BindView(R.id.progressbar_loading)
    RelativeLayout loading;
    @BindView(R.id.view_content_tanah_kosong)
    ScrollView content;
    @BindView(R.id.btn_close)
    ImageView btn_close;

    AppPreferences appPreferences;

    AgunanTanahKosong dataTanahKosong;
    List<AgunanTanahKosong> listDataTanahKosong;
    ApiClientAdapter apiClientAdapter;
    public static SimpleDateFormat dateClient = new SimpleDateFormat("dd-MM-yyyy", Locale.US);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agunan_tanah_kosong);
        ButterKnife.bind(this);
        apiClientAdapter = new ApiClientAdapter(this);
        toolbarSettings();
        if (!getIntent().getStringExtra("idAgunan").equalsIgnoreCase("0")) {
            setDisable();
            inquiryTanahKosong();
        }

        appPreferences=new AppPreferences(ActivityAgunanTanahKosong_Apr.this);
    }

    private void toolbarSettings() {
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        tb_regular.setText("Agunan Tanah Kosong");
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    public void setDisable(){
        img_bpn_tanah_kosong.setOnClickListener(this);
        img_agunan.setOnClickListener(this);
        img_utara.setOnClickListener(this);
        img_selatan.setOnClickListener(this);
        img_timur.setOnClickListener(this);
        img_barat.setOnClickListener(this);

        et_harga_per_meter_tanah_kosong.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_harga_per_meter_tanah_kosong));
        et_nilai_market_tanah_kosong.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_nilai_market_tanah_kosong));
        et_nilai_likuidasi_tanah_kosong.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_nilai_likuidasi_tanah_kosong));

        et_collateral_id_syiar.setEnabled(false);
        et_tanggal_pemeriksaan_tanah_kosong.setEnabled(false);
        et_alamat_jaminan_tanah_kosong.setEnabled(false);
        et_kelurahan_dan_kecamatan_tanah_kosong.setEnabled(false);
        et_wilayah_dan_kota.setEnabled(false);
        et_hub_pemegang_hak_dengan_nasabah_tanah_kosong.setEnabled(false);
        et_nama_penggarap.setEnabled(false);
        et_nama_pemegang_hak.setEnabled(false);
        et_status_penggarap.setEnabled(false);
        et_hub_penggarap_dengan_pemegang_hak.setEnabled(false);
        et_jenis_dokumen_tanah_kosong.setEnabled(false);
        et_no_bukti_hak.setEnabled(false);
        et_no_gs.setEnabled(false);
        et_jatuh_tempo_sertifikat.setEnabled(false);
        et_luas_tanah_tanah_kosong.setEnabled(false);
        et_harga_per_meter_tanah_kosong.setEnabled(false);
        et_nilai_market_tanah_kosong.setEnabled(false);
        et_nilai_likuidasi_tanah_kosong.setEnabled(false);
        et_field_rekomendasi.setEnabled(false);
        et_nama_pemberi_informasi.setEnabled(false);
        et_alamat_pemberi_informasi.setEnabled(false);
        et_no_pemberi_informasi.setEnabled(false);
        et_check_bpn_tanah_kosong.setEnabled(false);
        et_dengan_siapa.setEnabled(false);
        et_no_telp_bpn.setEnabled(false);
        et_hasil_bpn.setEnabled(false);
        et_lebar_jalan_didepan_tanah_kosong.setEnabled(false);
        et_pendapat_pemeriksa.setEnabled(false);
        bt_simpan_agunan_tanah_kosong.setVisibility(View.GONE);
        btn_upload_bpn_tanah_kosong.setVisibility(View.GONE);
        btn_upload_agunan.setVisibility(View.GONE);
        btn_upload_utara.setVisibility(View.GONE);
        btn_upload_selatan.setVisibility(View.GONE);
        btn_upload_timur.setVisibility(View.GONE);
        btn_upload_barat.setVisibility(View.GONE);
        btn_set_loc.setVisibility(View.GONE);
    }

    public void inquiryTanahKosong() {
        content.setVisibility(View.GONE);
        loading.setVisibility(View.VISIBLE);
        ReqAgunanData req = new ReqAgunanData(AppUtil.parseIntWithDefault(getIntent().getStringExtra("idAplikasi"), 0), AppUtil.parseIntWithDefault(getIntent().getStringExtra("idAgunan"), 0), AppUtil.parseIntWithDefault(getIntent().getStringExtra("cif"), 0));

        //ganti jadi list pemutus deviasi jika sudah ada middletier
        Call<ParseResponseArr> call = apiClientAdapter.getApiInterface().inquiryAgunanTanahKosong(req);
        call.enqueue(new Callback<ParseResponseArr>() {
            @Override
            public void onResponse(Call<ParseResponseArr> call, Response<ParseResponseArr> response) {
                //progressbar_loading.setVisibility(View.GONE);

                if (response.isSuccessful()) {
                    if (response.body().getStatus().equalsIgnoreCase("00")) {
                        String listDataString = response.body().getData().toString();
                        Gson gson = new Gson();
                        Type type = new TypeToken<List<AgunanTanahKosong>>() {
                        }.getType();
                        content.setVisibility(View.VISIBLE);
                        loading.setVisibility(View.GONE);
                        listDataTanahKosong = gson.fromJson(listDataString, type);
                        dataTanahKosong = listDataTanahKosong.get(0);
                        String[] parseAddress = dataTanahKosong.getAlamatJaminan().split("\\|\\|");

                        et_collateral_id_syiar.setText(dataTanahKosong.getCollIdSyiar());
                        et_tanggal_pemeriksaan_tanah_kosong.setText(AppUtil.parseTanggalGeneral(dataTanahKosong.getTglPemeriksaan().trim(), "ddMMyyyy", "dd-MM-yyyy"));
                        tv_set_loc.setText(dataTanahKosong.getKoordinat());
                        et_alamat_jaminan_tanah_kosong.setText(dataTanahKosong.getAlamatJaminan());
                        et_kelurahan_dan_kecamatan_tanah_kosong.setText(dataTanahKosong.getKelKec());
                        et_wilayah_dan_kota.setText(dataTanahKosong.getWilayahKota());
                        et_hub_pemegang_hak_dengan_nasabah_tanah_kosong.setText(dataTanahKosong.getHubPemegangHak());
                        et_nama_penggarap.setText(dataTanahKosong.getNamaPenggarap());
                        et_nama_pemegang_hak.setText(dataTanahKosong.getNamaPemegangHak());
                        et_status_penggarap.setText(dataTanahKosong.getStatusPenggarap());
                        et_hub_penggarap_dengan_pemegang_hak.setText(dataTanahKosong.getHubPenggarap());
                        et_jenis_dokumen_tanah_kosong.setText(dataTanahKosong.getJenisDokumen());
                        et_no_bukti_hak.setText(dataTanahKosong.getNoBuktiHak());
                        et_no_gs.setText(dataTanahKosong.getNoGs());
                        et_jatuh_tempo_sertifikat.setText(AppUtil.parseTanggalGeneral(dataTanahKosong.getTglJatuhTempoSertifikat().trim(), "ddMMyyyy", "dd-MM-yyyy"));
                        et_luas_tanah_tanah_kosong.setText(dataTanahKosong.getLuasTanah());
                        et_harga_per_meter_tanah_kosong.setText(dataTanahKosong.getHargaTanah());
                        et_nilai_market_tanah_kosong.setText(dataTanahKosong.getNilaiMarketTanah());
                        et_nilai_likuidasi_tanah_kosong.setText(dataTanahKosong.getNilaiTaksasi());

                        et_nama_pemberi_informasi.setText(dataTanahKosong.getNamaPemberiInfo1());
                        et_alamat_pemberi_informasi.setText(dataTanahKosong.getAlamatPemberiInfo1());
                        et_no_pemberi_informasi.setText(dataTanahKosong.getNoTelpPemberiInfo1());
                        et_check_bpn_tanah_kosong.setText(dataTanahKosong.getCheckBpn());
                        et_dengan_siapa.setText(dataTanahKosong.getNamaCheckBpn());
                        et_no_telp_bpn.setText(dataTanahKosong.getNotelCheckBpn());
                        et_hasil_bpn.setText(dataTanahKosong.getHasilBpn());
                        et_lebar_jalan_didepan_tanah_kosong.setText(dataTanahKosong.getLebarJalan());
                        et_pendapat_pemeriksa.setText(dataTanahKosong.getPendapatPemeriksa());
                        et_alamat_jaminan_tanah_kosong.setText(dataTanahKosong.getAlamatJaminan());

                        //load foto

                        String baseurl_photo = UriApi.Baseurl.URL + UriApi.foto.urlPhoto;

                        RequestOptions options = new RequestOptions()
                                .centerCrop()
                                .placeholder(R.mipmap.ico_img_for_upload)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .priority(Priority.HIGH);


                        GlideUrl glideUrl1 = new GlideUrl(UriApi.Baseurl.URL + UriApi.foto.urlPhoto + dataTanahKosong.getIdPhotoTKutama(), new LazyHeaders.Builder()
                                .addHeader("Authorization", "Bearer "+appPreferences.getToken())
                                .build());

                        Glide.with(ActivityAgunanTanahKosong_Apr.this)
                                .asBitmap()
                                .load(glideUrl1)
                                .apply(options)
                                .into(new SimpleTarget<Bitmap>() {
                                    @Override
                                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                        img_agunan.setImageBitmap(resource);

                                    }
                                });

                        GlideUrl glideUrl2 = new GlideUrl(UriApi.Baseurl.URL + UriApi.foto.urlPhoto + dataTanahKosong.getIdPhotoTKutara(), new LazyHeaders.Builder()
                                .addHeader("Authorization", "Bearer "+appPreferences.getToken())
                                .build());

                        Glide.with(ActivityAgunanTanahKosong_Apr.this)
                                .asBitmap()
                                .load(glideUrl2)
                                .apply(options)
                                .into(new SimpleTarget<Bitmap>() {
                                    @Override
                                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                        img_utara.setImageBitmap(resource);

                                    }
                                });

                        GlideUrl glideUrl3 = new GlideUrl(UriApi.Baseurl.URL + UriApi.foto.urlPhoto + dataTanahKosong.getIdPhotoTKselatan(), new LazyHeaders.Builder()
                                .addHeader("Authorization", "Bearer "+appPreferences.getToken())
                                .build());

                        Glide.with(ActivityAgunanTanahKosong_Apr.this)
                                .asBitmap()
                                .load(glideUrl3)
                                .apply(options)
                                .into(new SimpleTarget<Bitmap>() {
                                    @Override
                                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                        img_selatan.setImageBitmap(resource);
                                    }
                                });

                        GlideUrl glideUrl4 = new GlideUrl(UriApi.Baseurl.URL + UriApi.foto.urlPhoto + dataTanahKosong.getIdPhotoTKbarat(), new LazyHeaders.Builder()
                                .addHeader("Authorization", "Bearer "+appPreferences.getToken())
                                .build());

                        Glide.with(ActivityAgunanTanahKosong_Apr.this)
                                .asBitmap()
                                .load(glideUrl4)
                                .apply(options)
                                .into(new SimpleTarget<Bitmap>() {
                                    @Override
                                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                        img_barat.setImageBitmap(resource);

                                    }
                                });

                        GlideUrl glideUrl5 = new GlideUrl(UriApi.Baseurl.URL + UriApi.foto.urlPhoto + dataTanahKosong.getIdPhotoTKtimur(), new LazyHeaders.Builder()
                                .addHeader("Authorization", "Bearer "+appPreferences.getToken())
                                .build());

                        Glide.with(ActivityAgunanTanahKosong_Apr.this)
                                .asBitmap()
                                .load(glideUrl5)
                                .apply(options)
                                .into(new SimpleTarget<Bitmap>() {
                                    @Override
                                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                        img_timur.setImageBitmap(resource);
                                    }
                                });

                        GlideUrl glideUrl6 = new GlideUrl(UriApi.Baseurl.URL + UriApi.foto.urlPhoto + dataTanahKosong.getIdPhotoTKbpn(), new LazyHeaders.Builder()
                                .addHeader("Authorization", "Bearer "+appPreferences.getToken())
                                .build());

                        Glide.with(ActivityAgunanTanahKosong_Apr.this)
                                .asBitmap()
                                .load(glideUrl6)
                                .apply(options)
                                .into(new SimpleTarget<Bitmap>() {
                                    @Override
                                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                        img_bpn_tanah_kosong.setImageBitmap(resource);
                                    }
                                });

                    } else {
                        content.setVisibility(View.VISIBLE);
                        loading.setVisibility(View.GONE);
                        Toast.makeText(ActivityAgunanTanahKosong_Apr.this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    content.setVisibility(View.VISIBLE);
                    loading.setVisibility(View.GONE);
                    Toast.makeText(ActivityAgunanTanahKosong_Apr.this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ParseResponseArr> call, Throwable t) {
                content.setVisibility(View.VISIBLE);
                loading.setVisibility(View.GONE);
                Toast.makeText(ActivityAgunanTanahKosong_Apr.this, "Gagal tersambung ke server", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.img_bpn_tanah_kosong:
                DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Foto", ((RoundedDrawable)img_bpn_tanah_kosong.getDrawable()).getSourceBitmap());
                break;
            case R.id.img_agunan:
                DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Foto", ((RoundedDrawable)img_agunan.getDrawable()).getSourceBitmap());
                break;
            case R.id.img_utara:
                DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Foto", ((RoundedDrawable)img_utara.getDrawable()).getSourceBitmap());
                break;
            case R.id.img_selatan:
                DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Foto", ((RoundedDrawable)img_selatan.getDrawable()).getSourceBitmap());
                break;
            case R.id.img_timur:
                DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Foto", ((RoundedDrawable)img_timur.getDrawable()).getSourceBitmap());
                break;
            case R.id.img_barat:
                DialogPreviewPhoto.display(getSupportFragmentManager(), "Preview Foto", ((RoundedDrawable)img_barat.getDrawable()).getSourceBitmap());
                break;
        }
    }
}
