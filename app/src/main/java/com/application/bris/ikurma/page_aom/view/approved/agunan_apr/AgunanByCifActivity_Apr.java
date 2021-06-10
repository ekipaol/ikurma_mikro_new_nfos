package com.application.bris.ikurma.page_aom.view.approved.agunan_apr;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.ViewCompat;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

import com.application.bris.ikurma.R;
import com.application.bris.ikurma.api.model.Error;
import com.application.bris.ikurma.api.model.ParseResponseArrAgunanByCif;
import com.application.bris.ikurma.api.model.ParseResponseError;
import com.application.bris.ikurma.api.model.request.agunan.ReqAgunanByCif;
import com.application.bris.ikurma.api.service.ApiClientAdapter;
import com.application.bris.ikurma.database.AppPreferences;
import com.application.bris.ikurma.page_aom.adapter.agunan.FrontAgunanByCifAdapater;
import com.application.bris.ikurma.page_aom.adapter.agunan.FrontAgunanDepositoByCifAdapater;
import com.application.bris.ikurma.page_aom.adapter.agunan.FrontAgunanKendaraanByCifAdapater;
import com.application.bris.ikurma.page_aom.adapter.agunan.FrontAgunanKiosByCifAdapater;
import com.application.bris.ikurma.page_aom.adapter.agunan.FrontAgunanTanahKosongByCifAdapater;
import com.application.bris.ikurma.page_aom.dialog.DialogBottomAgunan;
import com.application.bris.ikurma.page_aom.listener.AgunanByCifListener;
import com.application.bris.ikurma.page_aom.listener.AgunanListener;
import com.application.bris.ikurma.page_aom.model.AgunanDepositoFront;
import com.application.bris.ikurma.page_aom.model.AgunanKendaraanFront;
import com.application.bris.ikurma.page_aom.model.AgunanKiosFront;
import com.application.bris.ikurma.page_aom.model.AgunanTanahBangunanFront;
import com.application.bris.ikurma.page_aom.model.AgunanTanahKosongFront;
import com.application.bris.ikurma.page_aom.model.ListInfo;
import com.application.bris.ikurma.util.AppUtil;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AgunanByCifActivity_Apr extends AppCompatActivity implements AgunanListener, AgunanByCifListener {

    @BindView(R.id.tb_regular)
    Toolbar tb_regular;

    @BindView(R.id.sm_placeholder_tanah_bangunan)
    ShimmerFrameLayout sm_placeholder_tanah_bangunan;
    @BindView(R.id.sm_placeholder_kendaraan)
    ShimmerFrameLayout sm_placeholder_kendaraan;
    @BindView(R.id.sm_placeholder_tanah_kosong)
    ShimmerFrameLayout sm_placeholder_tanah_kosong;
    @BindView(R.id.sm_placeholder_kios)
    ShimmerFrameLayout sm_placeholder_kios;
    @BindView(R.id.sm_placeholder_deposito)
    ShimmerFrameLayout sm_placeholder_deposito;

    @BindView(R.id.ll_emptydata_tanah_bangunan)
    LinearLayout ll_emptydata_tanah_bangunan;
    @BindView(R.id.ll_emptydata_kendaraan)
    LinearLayout ll_emptydata_kendaraan;
    @BindView(R.id.ll_emptydata_tanah_kosong)
    LinearLayout ll_emptydata_tanah_kosong;
    @BindView(R.id.ll_emptydata_kios)
    LinearLayout ll_emptydata_kios;
    @BindView(R.id.ll_emptydata_deposito)
    LinearLayout ll_emptydata_deposito;

    @BindView(R.id.rv_tanah_bangunan)
    RecyclerView rv_tanah_bangunan;
    @BindView(R.id.rv_kendaraan)
    RecyclerView rv_kendaraan;
    @BindView(R.id.rv_tanah_kosong)
    RecyclerView rv_tanah_kosong;
    @BindView(R.id.rv_kios)
    RecyclerView rv_kios;
    @BindView(R.id.rv_deposito)
    RecyclerView rv_deposito;

    @BindView(R.id.floatAdd)
    FloatingActionButton btFloat;

    private ListInfo dataInfo;
    private List<AgunanTanahBangunanFront> dataTanahBangunan;
    private List<AgunanKendaraanFront> dataKendaraan;
    private List<AgunanTanahKosongFront> dataTanahKosong;
    private List<AgunanKiosFront> dataKios;
    private List<AgunanDepositoFront> dataDeposito;
    private FrontAgunanByCifAdapater adapterListTanahBangunan;
    private FrontAgunanKendaraanByCifAdapater adapterListKendaraan;
    private FrontAgunanTanahKosongByCifAdapater adapterListTanahKosong;
    private FrontAgunanKiosByCifAdapater adapterListKios;
    private FrontAgunanDepositoByCifAdapater adapterListDeposito;
    private LinearLayoutManager layoutTanahBangunan;
    private LinearLayoutManager layoutKendaraan;
    private LinearLayoutManager layoutTanahKosong;
    private LinearLayoutManager layoutKios;
    private LinearLayoutManager layoutDeposito;

    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    private String idAplikasi, idCif;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agunan_bycif);

        idAplikasi = getIntent().getStringExtra("idAplikasi");
        idCif = getIntent().getStringExtra("cif");

        main();
        backgroundStatusBar();
    }

    @Override
    protected void onResume() {
        super.onResume();
        sm_placeholder_tanah_bangunan.startShimmer();
        sm_placeholder_tanah_bangunan.setVisibility(View.VISIBLE);
        sm_placeholder_kendaraan.startShimmer();
        sm_placeholder_kendaraan.setVisibility(View.VISIBLE);
        sm_placeholder_tanah_kosong.startShimmer();
        sm_placeholder_tanah_kosong.setVisibility(View.VISIBLE);
        sm_placeholder_kios.startShimmer();
        sm_placeholder_kios.setVisibility(View.VISIBLE);
        sm_placeholder_deposito.startShimmer();
        sm_placeholder_deposito.setVisibility(View.VISIBLE);
        loadData();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    private void backgroundStatusBar(){
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    public void main(){
        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);
        ButterKnife.bind(this);
        setSupportActionBar(tb_regular);
        AppUtil.toolbarRegular(this, "Daftar Agunan Nasabah");

        btFloat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAgunanMenu();
            }
        });
    }

    private void openAgunanMenu(){
        DialogBottomAgunan.display(getSupportFragmentManager(), AgunanByCifActivity_Apr.this);
    }

    public void loadData(){
        sm_placeholder_tanah_bangunan.startShimmer();
        sm_placeholder_kendaraan.startShimmer();
        sm_placeholder_tanah_kosong.startShimmer();
        sm_placeholder_kios.startShimmer();

        ReqAgunanByCif req = new ReqAgunanByCif();
        req.setIdCif(getIntent().getStringExtra("cif"));
        req.setIdApl(getIntent().getStringExtra("idAplikasi"));

        Call<ParseResponseArrAgunanByCif> call = apiClientAdapter.getApiInterface().listAgunanAll(req);
        call.enqueue(new Callback<ParseResponseArrAgunanByCif>() {
            @Override
            public void onResponse(Call<ParseResponseArrAgunanByCif> call, Response<ParseResponseArrAgunanByCif> response) {
                sm_placeholder_tanah_bangunan.stopShimmer();
                sm_placeholder_tanah_bangunan.setVisibility(View.GONE);
                sm_placeholder_kendaraan.stopShimmer();
                sm_placeholder_kendaraan.setVisibility(View.GONE);
                sm_placeholder_tanah_kosong.stopShimmer();
                sm_placeholder_tanah_kosong.setVisibility(View.GONE);
                sm_placeholder_kios.stopShimmer();
                sm_placeholder_kios.setVisibility(View.GONE);
                sm_placeholder_deposito.stopShimmer();
                sm_placeholder_deposito.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()) {
                        if (response.body().getStatus().equalsIgnoreCase("00")) {
                            Gson gson = new Gson();
                            Type typeInfo = new TypeToken<List<ListInfo>>() {
                            }.getType();
                            Type typeTanahBangunan = new TypeToken<List<AgunanTanahBangunanFront>>() {
                            }.getType();
                            Type typeKendaraan = new TypeToken<List<AgunanKendaraanFront>>() {
                            }.getType();
                            Type typeTanahKosong = new TypeToken<List<AgunanTanahKosongFront>>() {
                            }.getType();
                            Type typeKios = new TypeToken<List<AgunanKiosFront>>() {
                            }.getType();
                            Type typeDeposito = new TypeToken<List<AgunanDepositoFront>>() {
                            }.getType();

                            List<ListInfo> listInfo = gson.fromJson(response.body().getInfo().toString(), typeInfo);
                            dataInfo = listInfo.get(0);

                            dataTanahBangunan = gson.fromJson(response.body().getData().get("List_Agunan_TanahBangunan").toString(), typeTanahBangunan);
                            dataKendaraan = gson.fromJson(response.body().getData().get("List_Agunan_Kendaraan").toString(), typeKendaraan);
                            dataTanahKosong = gson.fromJson(response.body().getData().get("List_Agunan_TanahKosong").toString(), typeTanahKosong);
                            dataKios = gson.fromJson(response.body().getData().get("List_Agunan_Kios").toString(), typeKios);
                            dataDeposito = gson.fromJson(response.body().getData().get("List_Agunan_Deposito").toString(), typeDeposito);

                            if (dataTanahBangunan.size() > 0) {
                                ll_emptydata_tanah_bangunan.setVisibility(View.GONE);
                                initializeTanahBangunan();
                            } else {
                                ll_emptydata_tanah_bangunan.setVisibility(View.VISIBLE);
                            }

                            if (dataKendaraan.size() > 0) {
                                ll_emptydata_kendaraan.setVisibility(View.GONE);
                                initializeKendaraan();
                            } else {
                                ll_emptydata_kendaraan.setVisibility(View.VISIBLE);
                            }

                            if (dataTanahKosong.size() > 0) {
                                ll_emptydata_tanah_kosong.setVisibility(View.GONE);
                                initializeTanahKosong();
                            } else {
                                ll_emptydata_tanah_kosong.setVisibility(View.VISIBLE);
                            }

                            if (dataKios.size() > 0) {
                                ll_emptydata_kios.setVisibility(View.GONE);
                                initializeKios();
                            } else {
                                ll_emptydata_kios.setVisibility(View.VISIBLE);
                            }

                            if (dataDeposito.size() > 0) {
                                ll_emptydata_deposito.setVisibility(View.GONE);
                                initializeDeposito();
                            } else {
                                ll_emptydata_deposito.setVisibility(View.VISIBLE);
                            }

                        } else {
                            AppUtil.notiferror(AgunanByCifActivity_Apr.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    } else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(AgunanByCifActivity_Apr.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponseArrAgunanByCif> call, Throwable t) {
                AppUtil.notiferror(AgunanByCifActivity_Apr.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    public void initializeTanahBangunan(){
        adapterListTanahBangunan = new FrontAgunanByCifAdapater(AgunanByCifActivity_Apr.this, dataTanahBangunan, AgunanByCifActivity_Apr.this, idAplikasi, idCif);
        layoutTanahBangunan = new LinearLayoutManager(AgunanByCifActivity_Apr.this, LinearLayoutManager.HORIZONTAL, false);
        rv_tanah_bangunan.setLayoutManager(layoutTanahBangunan);
        rv_tanah_bangunan.setAdapter(adapterListTanahBangunan);
        ViewCompat.setNestedScrollingEnabled(rv_tanah_bangunan, false);

        if (dataTanahBangunan == null){
            ll_emptydata_tanah_bangunan.setVisibility(View.VISIBLE);
        }
    }

    public void initializeKendaraan(){
        adapterListKendaraan = new FrontAgunanKendaraanByCifAdapater(AgunanByCifActivity_Apr.this, dataKendaraan, AgunanByCifActivity_Apr.this, idAplikasi, idCif);
        layoutKendaraan = new LinearLayoutManager(AgunanByCifActivity_Apr.this, LinearLayoutManager.HORIZONTAL, false);
        rv_kendaraan.setLayoutManager(layoutKendaraan);
        rv_kendaraan.setAdapter(adapterListKendaraan);
        ViewCompat.setNestedScrollingEnabled(rv_kendaraan, false);

        if (dataKendaraan == null){
            ll_emptydata_kendaraan.setVisibility(View.VISIBLE);
        }
    }

    public void initializeTanahKosong(){
        adapterListTanahKosong = new FrontAgunanTanahKosongByCifAdapater(AgunanByCifActivity_Apr.this, dataTanahKosong, AgunanByCifActivity_Apr.this, idAplikasi, idCif);
        layoutTanahKosong = new LinearLayoutManager(AgunanByCifActivity_Apr.this, LinearLayoutManager.HORIZONTAL, false);
        rv_tanah_kosong.setLayoutManager(layoutTanahKosong);
        rv_tanah_kosong.setAdapter(adapterListTanahKosong);
        ViewCompat.setNestedScrollingEnabled(rv_tanah_kosong, false);

        if (dataTanahKosong == null){
            ll_emptydata_tanah_kosong.setVisibility(View.VISIBLE);
        }
    }

    public void initializeKios(){
        adapterListKios = new FrontAgunanKiosByCifAdapater(AgunanByCifActivity_Apr.this, dataKios, AgunanByCifActivity_Apr.this, idAplikasi, idCif);
        layoutKios = new LinearLayoutManager(AgunanByCifActivity_Apr.this, LinearLayoutManager.HORIZONTAL, false);
        rv_kios.setLayoutManager(layoutKios);
        rv_kios.setAdapter(adapterListKios);
        ViewCompat.setNestedScrollingEnabled(rv_kios, false);

        if (dataKios == null){
            ll_emptydata_kios.setVisibility(View.VISIBLE);
        }
    }

    public void initializeDeposito(){
        adapterListDeposito = new FrontAgunanDepositoByCifAdapater(AgunanByCifActivity_Apr.this, dataDeposito, AgunanByCifActivity_Apr.this, idAplikasi, idCif);
        layoutDeposito = new LinearLayoutManager(AgunanByCifActivity_Apr.this, LinearLayoutManager.HORIZONTAL, false);
        rv_deposito.setLayoutManager(layoutDeposito);
        rv_deposito.setAdapter(adapterListDeposito);
        ViewCompat.setNestedScrollingEnabled(rv_deposito, false);

        if (dataDeposito == null){
            ll_emptydata_deposito.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onSelectMenuAgunan(String idMenu) {
        switch (idMenu) {
            case "AgunanTanahBangunan Tanah dan Bangunan":
                Intent intent=new Intent(AgunanByCifActivity_Apr.this, AgunanTanahBangunanInputActivity_Apr.class);
                intent.putExtra("cif", getIntent().getStringExtra("cif"));
                intent.putExtra("idAplikasi", getIntent().getStringExtra("idAplikasi"));
                intent.putExtra("loan_type", dataInfo.getLOAN_TYPE());
                intent.putExtra("tp_produk", String.valueOf(dataInfo.getFID_TP_PRODUK()));
                intent.putExtra("idAgunan", "0");
                startActivity(intent);
                break;

            case "AgunanTanahBangunan Kendaraan":
                Intent itKendaraan=new Intent(AgunanByCifActivity_Apr.this, AgunanKendaraanInputActivity_Apr.class);
                itKendaraan.putExtra("cif", getIntent().getStringExtra("cif"));
                itKendaraan.putExtra("idAplikasi", getIntent().getStringExtra("idAplikasi"));
                itKendaraan.putExtra("loan_type", dataInfo.getLOAN_TYPE());
                itKendaraan.putExtra("tp_produk", String.valueOf(dataInfo.getFID_TP_PRODUK()));
                itKendaraan.putExtra("idAgunan", "0");
                startActivity(itKendaraan);
                break;

            case "AgunanTanahBangunan Tanah Kosong":
                Intent itTanahKosong=new Intent(AgunanByCifActivity_Apr.this, ActivityAgunanTanahKosong_Apr.class);
                itTanahKosong.putExtra("cif", getIntent().getStringExtra("cif"));
                itTanahKosong.putExtra("idAplikasi", getIntent().getStringExtra("idAplikasi"));
                itTanahKosong.putExtra("loan_type", dataInfo.getLOAN_TYPE());
                itTanahKosong.putExtra("tp_produk", String.valueOf(dataInfo.getFID_TP_PRODUK()));
                itTanahKosong.putExtra("idAgunan", "0");
                startActivity(itTanahKosong);
                break;

            case "AgunanTanahBangunan Kios":
                Intent itKios=new Intent(AgunanByCifActivity_Apr.this, AgunanKiosInputActivity_Apr.class);
                itKios.putExtra("cif", getIntent().getStringExtra("cif"));
                itKios.putExtra("idAplikasi", getIntent().getStringExtra("idAplikasi"));
                itKios.putExtra("loan_type", dataInfo.getLOAN_TYPE());
                itKios.putExtra("tp_produk", String.valueOf(dataInfo.getFID_TP_PRODUK()));
                itKios.putExtra("idAgunan", "0");
                startActivity(itKios);
                break;

            case "AgunanTanahBangunan Deposito":
                Intent itDeposito=new Intent(AgunanByCifActivity_Apr.this, AgunanDepositoInputActivity_Apr.class);
                itDeposito.putExtra("cif", getIntent().getStringExtra("cif"));
                itDeposito.putExtra("idAplikasi", getIntent().getStringExtra("idAplikasi"));
                itDeposito.putExtra("loan_type", dataInfo.getLOAN_TYPE());
                itDeposito.putExtra("tp_produk", String.valueOf(dataInfo.getFID_TP_PRODUK()));
                itDeposito.putExtra("idAgunan", "0");
                startActivity(itDeposito);
                break;
        }
    }

    @Override
    public void onAgunanByCifSelect(String idAplikasi, String idCif, String idAgunan, String jenisAgunan) {
        Intent intent = new Intent(this, InfoAgunanActivity_Apr.class);
        intent.putExtra("idAplikasi", idAplikasi);
        intent.putExtra("idCif", idCif);
        intent.putExtra("idAgunan", idAgunan);
        intent.putExtra("loan_type", dataInfo.getLOAN_TYPE());
        intent.putExtra("tp_produk", String.valueOf(dataInfo.getFID_TP_PRODUK()));
        intent.putExtra("jenisAgunan", jenisAgunan);
        intent.putExtra("prev", "cif");
        startActivity(intent);
    }
}
