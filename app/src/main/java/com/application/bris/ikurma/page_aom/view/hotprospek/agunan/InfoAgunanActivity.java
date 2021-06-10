package com.application.bris.ikurma.page_aom.view.hotprospek.agunan;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import android.os.Build;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.application.bris.ikurma.R;
import com.application.bris.ikurma.api.model.Error;
import com.application.bris.ikurma.api.model.ParseResponse;
import com.application.bris.ikurma.api.model.ParseResponseError;
import com.application.bris.ikurma.api.model.request.agunan.ReqDeleteAgunan;
import com.application.bris.ikurma.api.model.request.agunan.ReqInfoAgunan;
import com.application.bris.ikurma.api.service.ApiClientAdapter;
import com.application.bris.ikurma.page_aom.dialog.CustomDialog;
import com.application.bris.ikurma.page_aom.listener.ConfirmListener;
import com.application.bris.ikurma.page_aom.model.InfoAgunan;
import com.application.bris.ikurma.util.AppUtil;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.widget.Toast;

public class InfoAgunanActivity extends AppCompatActivity implements ConfirmListener {

    @BindView(R.id.tv_id_agunan_info)
    TextView tv_id_agunan_info;
    @BindView(R.id.tv_fid_jenis_agunan_info)
    TextView tv_fid_jenis_agunan_info;
    @BindView(R.id.tv_pengikatan_eksisting_info)
    TextView tv_pengikatan_eksisting_info;
    @BindView(R.id.tv_id_cif_appel_info)
    TextView tv_id_cif_appel_info;
    @BindView(R.id.tv_persen_ftv_info)
    TextView tv_persen_ftv_info;
    @BindView(R.id.tv_jenis_pengikatan)
    TextView tv_jenis_pengikatan;
    @BindView(R.id.tv_cover_plafon)
    TextView tv_cover_plafon;
    @BindView(R.id.tv_nilai_pengikatan)
    TextView tv_nilai_pengikatan;
    @BindView(R.id.btn_hapus_info_agunan)
    TextView btn_hapus_info_agunan;
    @BindView(R.id.btn_edit_info_bangunan)
    TextView btn_edit_info_bangunan;
    @BindView(R.id.btn_ikat_info_bangunan)
    TextView btn_ikat_info_bangunan;
    @BindView(R.id.progressbar_loading)
    RelativeLayout progressbar_loading;

    private String jenisAgunan,idPengikatan;
    private int idAplikasi, idCif, idAgunan, fidJenisAgunan;

    private ApiClientAdapter apiClientAdapter;
    private String dataString;
    private InfoAgunan data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agunan_info_agunan);
        ButterKnife.bind(this);
        apiClientAdapter = new ApiClientAdapter(this);
        backgroundStatusBar();
        AppUtil.toolbarRegular(this, "Info Agunan");

        idAplikasi = Integer.valueOf(getIntent().getStringExtra("idAplikasi"));
        idCif = Integer.valueOf(getIntent().getStringExtra("idCif"));
        idAgunan = AppUtil.parseIntWithDefault(getIntent().getStringExtra("idAgunan"), 0);
        jenisAgunan = getIntent().getStringExtra("jenisAgunan");
        if(getIntent().hasExtra("idPengikatan")){
            idPengikatan=getIntent().getStringExtra("idPengikatan");
        }
        else{
            idPengikatan="0";
        }

        if (getIntent().hasExtra("prev")){
            if (getIntent().getStringExtra("prev").equalsIgnoreCase("cif")) {
                btn_hapus_info_agunan.setVisibility(View.GONE);
            }
        }

        loadData();

        //LISTENERS FOR BUTTONS
        btn_hapus_info_agunan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteAgunan();
            }
        });

        btn_edit_info_bangunan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                if (getIntent().getStringExtra("jenisAgunan").equalsIgnoreCase("tanah dan bangunan")) {
                    intent = new Intent(InfoAgunanActivity.this, AgunanTanahBangunanInputActivity.class);
                } else if (getIntent().getStringExtra("jenisAgunan").equalsIgnoreCase("kendaraan")) {
                    intent = new Intent(InfoAgunanActivity.this, AgunanKendaraanInputActivity.class);
                } else if (getIntent().getStringExtra("jenisAgunan").equalsIgnoreCase("tanah kosong")) {
                    intent = new Intent(InfoAgunanActivity.this, ActivityAgunanTanahKosong.class);
                } else if (getIntent().getStringExtra("jenisAgunan").equalsIgnoreCase("kios")) {
                    intent = new Intent(InfoAgunanActivity.this, AgunanKiosInputActivity.class);
                } else if (getIntent().getStringExtra("jenisAgunan").equalsIgnoreCase("deposito")) {
                    intent = new Intent(InfoAgunanActivity.this, AgunanDepositoInputActivity.class);
                }

                intent.putExtra("idAplikasi", String.valueOf(idAplikasi));
                intent.putExtra("idAgunan", String.valueOf(idAgunan));
                intent.putExtra("loan_type", getIntent().getStringExtra("loan_type"));
                intent.putExtra("tp_produk", getIntent().getStringExtra("tp_produk"));
                intent.putExtra("cif", getIntent().getStringExtra("idCif"));
                intent.putExtra("dariEdit", "ya");

                startActivity(intent);

            }
        });

        btn_ikat_info_bangunan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InfoAgunanActivity.this, SetPengikatanActivity.class);
                intent.putExtra("idAPlikasi", String.valueOf(idAplikasi));
                intent.putExtra("idAgunan", tv_id_agunan_info.getText().toString());
                intent.putExtra("idCif", tv_id_cif_appel_info.getText().toString());
                intent.putExtra("fidJenisAgunan", String.valueOf(fidJenisAgunan));
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    private void backgroundStatusBar() {
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    private void deleteAgunan() {
        new AlertDialog.Builder(InfoAgunanActivity.this)
                .setTitle("Hapus")
                .setMessage("Anda yakin ingin menghapus agunan?")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        final ProgressDialog loadingDialog = ProgressDialog.show(InfoAgunanActivity.this, "",
                                "Memproses", true);

                        ReqDeleteAgunan req = new ReqDeleteAgunan();
                        req.setIdAgunan(idAgunan);
                        req.setIdCif(idCif);
                        req.setIdApl(idAplikasi);
                        req.setFidjenisAgunan(fidJenisAgunan);
                        req.setIdPengikatan(Integer.parseInt(idPengikatan));
                        Call<ParseResponse> call = apiClientAdapter.getApiInterface().deleteAgunan(req);
                        call.enqueue(new Callback<ParseResponse>() {
                            @Override
                            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {

                                try {
                                    if (response.isSuccessful()) {
                                        if (response.body().getStatus().equalsIgnoreCase("00")) {
                                            loadingDialog.dismiss();
                                            CustomDialog.DialogSuccess(InfoAgunanActivity.this, "Success!", "Berhasil Menghapus Agunan", InfoAgunanActivity.this);
                                            Toast.makeText(InfoAgunanActivity.this, "Berhasil Menghapus Agunan", Toast.LENGTH_SHORT).show();
                                            Intent it = new Intent(InfoAgunanActivity.this, AgunanTerikatActivity.class);
                                            it.putExtra("cif", getIntent().getStringExtra("idCif"));
                                            it.putExtra("idAplikasi", getIntent().getStringExtra("idAplikasi"));
                                            startActivity(it);
                                            finish();
                                        } else {
                                            AppUtil.notiferror(InfoAgunanActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                                        }
                                    } else {
                                        Error error = ParseResponseError.confirmEror(response.errorBody());
                                        AppUtil.notiferror(InfoAgunanActivity.this, findViewById(android.R.id.content), error.getMessage());
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFailure(Call<ParseResponse> call, Throwable t) {
                                AppUtil.notiferror(InfoAgunanActivity.this, findViewById(android.R.id.content), "Terjadi Kesalahan");
                            }
                        });

                    }
                })

                // A null listener allows the button to dismiss the dialog and take no further action.
                .setNegativeButton("Batal", null)
                .show();
    }

    private void loadData() {
        progressbar_loading.setVisibility(View.VISIBLE);
        ReqInfoAgunan req = new ReqInfoAgunan();
        req.setIdApl(Integer.valueOf(getIntent().getStringExtra("idAplikasi")));
        req.setIdAgunan(Integer.valueOf(getIntent().getStringExtra("idAgunan")));
        req.setIdCif(idCif);
        req.setIdPengikatan(Integer.parseInt(idPengikatan));
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().inquiryInfoAgunan(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                progressbar_loading.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()) {
                        if (response.body().getStatus().equalsIgnoreCase("00")) {
                            Gson gson = new Gson();
                            dataString = response.body().getData().toString();
                            data = gson.fromJson(dataString, InfoAgunan.class);
                            setData();
                        }
                        else if (response.body().getStatus().equalsIgnoreCase("01")) {
                            AppUtil.notifinfoLong(InfoAgunanActivity.this,findViewById(android.R.id.content),"Agunan belum diikat");
                            switch (jenisAgunan.toLowerCase()) {
                                case "tanah dan bangunan":
                                    fidJenisAgunan = 7;
                                    setDataBelumDiikat();
                                    break;

                                case "kendaraan":
                                    fidJenisAgunan = 32;
                                    setDataBelumDiikat();
                                    break;

                                case "tanah kosong":
                                    fidJenisAgunan = 30;
                                    setDataBelumDiikat();
                                    break;

                                case "kios":
                                    fidJenisAgunan = 33;
                                    setDataBelumDiikat();
                                    break;

                                case "deposito":
                                    fidJenisAgunan = 31;
                                    setDataBelumDiikat();
                                    break;
                            }


                        }else {
                            switch (jenisAgunan.toLowerCase()) {
                                case "tanah dan bangunan":
                                    fidJenisAgunan = 7;
                                    break;

                                case "kendaraan":
                                    fidJenisAgunan = 32;
                                    break;

                                case "tanah kosong":
                                    fidJenisAgunan = 30;
                                    break;

                                case "kios":
                                    fidJenisAgunan = 33;
                                    break;

                                case "deposito":
                                    fidJenisAgunan = 31;
                                    break;
                            }

//                            btn_ikat_info_bangunan.setVisibility(View.GONE);
                            AppUtil.notiferror(InfoAgunanActivity.this, findViewById(android.R.id.content), response.body().getMessage() + " Silahkan edit agunan terlebih dahulu");
                        }
                    } else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(InfoAgunanActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                progressbar_loading.setVisibility(View.GONE);
                AppUtil.notiferror(InfoAgunanActivity.this, findViewById(android.R.id.content), "Terjadi Kesalahan");
            }
        });
    }

    public void setData() {
        tv_id_agunan_info.setText(String.valueOf(data.getIdAgunan()));

        //fid jenis agunan special treatment
        if (data.getFidjenisAgunan().equalsIgnoreCase("30")) {
            tv_fid_jenis_agunan_info.setText("Tanah Kosong / Sawah");
        } else if (data.getFidjenisAgunan().equalsIgnoreCase("31")) {
            tv_fid_jenis_agunan_info.setText("Deposito");
        } else if (data.getFidjenisAgunan().equalsIgnoreCase("32")) {
            tv_fid_jenis_agunan_info.setText("Kendaraan Bermotor");
        } else if (data.getFidjenisAgunan().equalsIgnoreCase("33")) {
            tv_fid_jenis_agunan_info.setText("Kios");
        } else if (data.getFidjenisAgunan().equalsIgnoreCase("7")) {
            tv_fid_jenis_agunan_info.setText("Tanah dan Bangunan");
        } else if (data.getFidjenisAgunan().equalsIgnoreCase("8")) {
            tv_fid_jenis_agunan_info.setText("Mesin-mesin");
        }
        //END OF FID SPECIAL TREATMENT
        fidJenisAgunan = AppUtil.parseIntWithDefault(data.getFidjenisAgunan(), 0);
        tv_pengikatan_eksisting_info.setText(data.getPengikatanEksisting());
        tv_id_cif_appel_info.setText(data.getIdCif());
        tv_persen_ftv_info.setText(data.getPersenFTV());
        tv_jenis_pengikatan.setText(data.getJenisPengikatan());
        tv_cover_plafon.setText(AppUtil.parseRupiah(data.getCoverPlafond()));
        tv_nilai_pengikatan.setText(AppUtil.parseRupiah(data.getNilaiPengikatan()));
    }

    public void setDataBelumDiikat() {
        tv_id_agunan_info.setText(Integer.toString(idAgunan));

        //fid jenis agunan special treatment
        if (fidJenisAgunan==30) {
            tv_fid_jenis_agunan_info.setText("Tanah Kosong / Sawah");
        } else if (fidJenisAgunan==31) {
            tv_fid_jenis_agunan_info.setText("Deposito");
        } else if (fidJenisAgunan==32) {
            tv_fid_jenis_agunan_info.setText("Kendaraan Bermotor");
        } else if (fidJenisAgunan==33) {
            tv_fid_jenis_agunan_info.setText("Kios");
        } else if (fidJenisAgunan==7) {
            tv_fid_jenis_agunan_info.setText("Tanah dan Bangunan");
        } else if (fidJenisAgunan==8) {
            tv_fid_jenis_agunan_info.setText("Mesin-mesin");
        }

        tv_pengikatan_eksisting_info.setText("-");
        tv_id_cif_appel_info.setText(Integer.toString(idCif));
        tv_persen_ftv_info.setText("-");
        tv_jenis_pengikatan.setText("-");
        tv_cover_plafon.setText("-");
        tv_nilai_pengikatan.setText("-");

        btn_ikat_info_bangunan.setVisibility(View.VISIBLE);
    }

    @Override
    public void success(boolean val) {
        finish();
    }

    @Override
    public void confirm(boolean val) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}