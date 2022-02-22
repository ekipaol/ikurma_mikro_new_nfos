package com.application.bris.ikurma.page_aom.view.hotprospek.rpc;

import android.os.Build;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.application.bris.ikurma.R;
import com.application.bris.ikurma.api.model.Error;
import com.application.bris.ikurma.api.model.ParseResponse;
import com.application.bris.ikurma.api.model.ParseResponseError;
import com.application.bris.ikurma.api.model.request.hotprospek.inquiryRPC;
import com.application.bris.ikurma.api.service.ApiClientAdapter;
import com.application.bris.ikurma.page_aom.model.Rpc;
import com.application.bris.ikurma.util.AppUtil;
import com.application.bris.ikurma.util.Stringinfo;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RpcActivity extends AppCompatActivity{

    @BindView(R.id.ll_content)
    LinearLayout ll_content;
    @BindView(R.id.sm_placeholder)
    ShimmerFrameLayout sm_placeholder;

    @BindView(R.id.tv_pendapatanusaha)
    TextView tv_pendapatanusaha;
    @BindView(R.id.tv_hargapokokpenjualan)
    TextView tv_hargapokokpenjualan;
    @BindView(R.id.tv_sewakontrak)
    TextView tv_sewakontrak;
    @BindView(R.id.tv_gajipegawai)
    TextView tv_gajipegawai;
    @BindView(R.id.tv_telponlistrikair)
    TextView tv_telponlistrikair;
    @BindView(R.id.tv_pajakretribusi)
    TextView tv_pajakretribusi;
    @BindView(R.id.tv_transportasi)
    TextView tv_transportasi;
    @BindView(R.id.tv_biayarumahtangga)
    TextView tv_biayarumahtangga;
    @BindView(R.id.tv_pengeluaranlainnya)
    TextView tv_pengeluaranlainnya;
    @BindView(R.id.tv_pengeluaranusaha)
    TextView tv_pengeluaranusaha;
    @BindView(R.id.tv_pendapatanbersih)
    TextView tv_pendapatanbersih;
    @BindView(R.id.tv_penghasilanlainnya)
    TextView tv_penghasilanlainnya;
    @BindView(R.id.tv_labarugi)
    TextView tv_labarugi;
    @BindView(R.id.tv_rpc)
    TextView tv_rpc;
    @BindView(R.id.tv_angsuranbrisexisting)
    TextView tv_angsuranbrisexisting;
    @BindView(R.id.tv_angsuransaatini)
    TextView tv_angsuransaatini;
    @BindView(R.id.tv_rpcratio)
    TextView tv_rpcratio;
    @BindView(R.id.ll_info)
    LinearLayout ll_info;
    @BindView(R.id.ll_hasil_rpc)
    LinearLayout ll_hasil_rpc;
    @BindView(R.id.tv_info)
    TextView tv_info;
    @BindView(R.id.sv_rpc)
    ScrollView sv_rpc;

    private int idAplikasi;

    private ApiClientAdapter apiClientAdapter;
    private String dataString;
    private Rpc data;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ao_activity_hotprospek_rpc);
        ButterKnife.bind(this);
        apiClientAdapter = new ApiClientAdapter(this);
        idAplikasi = getIntent().getIntExtra("idAplikasi", 0);
        backgroundStatusBar();
        AppUtil.toolbarRegular(this, "Perhitungan RPC");
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadDataRPC();
        sm_placeholder.startShimmer();
    }

    @Override
    protected void onPause() {
        super.onPause();
        sm_placeholder.stopShimmer();
    }

    private void backgroundStatusBar(){
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }


    private void loadDataRPC() {
        inquiryRPC req = new inquiryRPC(idAplikasi); //101678
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().hitungRPC(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                sm_placeholder.stopShimmer();
                sm_placeholder.setVisibility(View.GONE);
                ll_content.setVisibility(View.VISIBLE);
                try {
                    if (response.isSuccessful()){
                        if(response.body().getStatus().equalsIgnoreCase("00")){
                            Gson gson = new Gson();
                            dataString = response.body().getData().get("rpc").toString();
                            data = gson.fromJson(dataString, Rpc.class);

                            tv_pendapatanusaha.setText(AppUtil.parseRupiahLong(data.getPendapatanUsaha()));

                            tv_hargapokokpenjualan.setText(AppUtil.parseRupiahLong(data.getPengeluaranHargaPokokPenjualan()));
                            tv_sewakontrak.setText(AppUtil.parseRupiahLong(data.getPengeluaranHargaSewa()));
                            tv_gajipegawai.setText(AppUtil.parseRupiahLong(data.getPengeluaranGajiPegawai()));
                            tv_telponlistrikair.setText(AppUtil.parseRupiahLong(data.getPengeluaranTelpListrik()));
                            tv_pajakretribusi.setText(AppUtil.parseRupiahLong(data.getPengeluaranPajak()));
                            tv_transportasi.setText(AppUtil.parseRupiahLong(data.getPengeluaranTransportasi()));
                            tv_biayarumahtangga.setText(AppUtil.parseRupiahLong(data.getPengeluaranRumahTangga()));
                            tv_pengeluaranlainnya.setText(AppUtil.parseRupiahLong(data.getPengeluaranLainnya()));
                            tv_pengeluaranusaha.setText(AppUtil.parseRupiahLong(data.getPengeluaranUsaha()));
                            tv_pendapatanbersih.setText(AppUtil.parseRupiahLong(data.getPendapatanBersih()));
                            tv_penghasilanlainnya.setText(AppUtil.parseRupiahLong(data.getPenghasilanLain()));
                            tv_labarugi.setText(AppUtil.parseRupiahLong(data.getLabaRugi()));
                            tv_rpc.setText(AppUtil.parseRupiahLong(data.getRpc()));
                            tv_angsuranbrisexisting.setText(AppUtil.parseRupiahLong(data.getAngsuranBrisExisting()));
                            tv_angsuransaatini.setText(AppUtil.parseRupiahLong(data.getAngsuranAplikasi()));
                            tv_rpcratio.setText(String.valueOf(data.getRpcRatio())+"x");

                            String msginfo = response.body().getData().get("message").getAsString();

                            if (msginfo.equalsIgnoreCase("success")){
                                ll_info.setVisibility(View.GONE);
                            }
                            else{
                                ll_info.setVisibility(View.GONE);
                                tv_info.setText("-");
                            }

                            sv_rpc.post(new Runnable() {
                                @Override
                                public void run() {
                                    sv_rpc.fullScroll(ScrollView.FOCUS_DOWN);
                                }
                            });
                        }
                    }
                    else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(RpcActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                AppUtil.notiferror(RpcActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }
}
