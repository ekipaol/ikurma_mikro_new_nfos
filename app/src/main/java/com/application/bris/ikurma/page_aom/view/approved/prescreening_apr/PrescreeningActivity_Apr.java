package com.application.bris.ikurma.page_aom.view.approved.prescreening_apr;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.text.Html;
import android.util.Base64;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.application.bris.ikurma.R;
import com.application.bris.ikurma.api.model.Error;
import com.application.bris.ikurma.api.model.ParseResponse;
import com.application.bris.ikurma.api.model.ParseResponseError;
import com.application.bris.ikurma.api.model.request.hotprospek.Prescreening;
import com.application.bris.ikurma.api.model.request.hotprospek.ReqDownloadSlik;
import com.application.bris.ikurma.api.model.request.hotprospek.inquiryRPC;
import com.application.bris.ikurma.api.service.ApiClientAdapter;
import com.application.bris.ikurma.baseapp.RouteApp;
import com.application.bris.ikurma.page_aom.dialog.CustomDialog;
import com.application.bris.ikurma.page_aom.listener.ConfirmListener;
import com.application.bris.ikurma.page_aom.listener.DownloadListener;
import com.application.bris.ikurma.page_aom.model.DataPrescreening;
import com.application.bris.ikurma.page_aom.view.approved.prescreening_apr.remaksslik.RemaksSlikActivity_Apr;
import com.application.bris.ikurma.page_aom.view.hotprospek.HotprospekActivity;
import com.application.bris.ikurma.util.AppUtil;
import com.application.bris.ikurma.util.DownloadTask;
import com.application.bris.ikurma.util.Stringinfo;
import com.google.gson.Gson;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrescreeningActivity_Apr extends AppCompatActivity implements View.OnClickListener, ConfirmListener, DownloadListener{

    @BindView(R.id.ll_content)
    LinearLayout ll_content;
    @BindView(R.id.progressbar_loading)
    public RelativeLayout loading;
    @BindView(R.id.tv_loading)
    TextView tv_loading;
    @BindView(R.id.iv_completedhn)
    ImageView iv_completedhn;
    @BindView(R.id.tv_dhn)
    TextView tv_dhn;
    @BindView(R.id.iv_btndhn)
    ImageView iv_btndhn;
    @BindView(R.id.iv_completedukcapil)
    ImageView iv_completedukcapil;
    @BindView(R.id.tv_dukcapil)
    TextView tv_dukcapil;
    @BindView(R.id.iv_btndukcapil)
    ImageView iv_btndukcapil;
    @BindView(R.id.iv_completeslik)
    ImageView iv_completeslik;
    @BindView(R.id.tv_slik)
    TextView tv_slik;
    @BindView(R.id.iv_btnslik)
    ImageView iv_btnslik;
    @BindView(R.id.ll_sikp)
    LinearLayout ll_sikp;
    @BindView(R.id.iv_completesikp)
    ImageView iv_completesikp;
    @BindView(R.id.tv_sikp)
    TextView tv_sikp;
    @BindView(R.id.iv_btnsikp)
    ImageView iv_btnsikp;
    @BindView(R.id.ll_info)
    LinearLayout ll_info;
    @BindView(R.id.tv_info)
    TextView tv_info;
    @BindView(R.id.ll_hasilprescreening)
    LinearLayout ll_hasilprescreening;
    @BindView(R.id.tv_hasilprescreening)
    TextView tv_hasilprescreening;
    @BindView(R.id.btn_send)
    Button btn_send;
    @BindView(R.id.btn_download)
    Button btn_download;
    @BindView(R.id.btn_remaks)
    Button btn_remaks;
    @BindView(R.id.sv_prescreening)
    public ScrollView sv_prescreening;

    private int idAplikasi;
    private String kodeProduct;
    private ApiClientAdapter apiClientAdapter;

    private DataPrescreening data;
    private boolean flagCheckDhn = false;
    private boolean flagCheckDukcapil = false;
    private boolean flagCheckSlik = false;
    private boolean flagCheckSikp = false;
    private boolean isKUR = false;
    private boolean isKawin = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ao_activity_hotprospek_prescreening);
        ButterKnife.bind(this);
        apiClientAdapter = new ApiClientAdapter(this, 240, TimeUnit.SECONDS);
        idAplikasi = getIntent().getIntExtra("idAplikasi", 0);
        kodeProduct = getIntent().getStringExtra("kodeProduct");
        backgroundStatusBar();
        AppUtil.toolbarRegular(this, "Prescreening");

        btn_download.setOnClickListener(this);
        btn_remaks.setOnClickListener(this);

        if (kodeProduct.equalsIgnoreCase("127") || kodeProduct.equalsIgnoreCase("128")|| kodeProduct.equalsIgnoreCase("318")|| kodeProduct.equalsIgnoreCase("319")|| kodeProduct.equalsIgnoreCase("320")|| kodeProduct.equalsIgnoreCase("841")){
            ll_sikp.setVisibility(View.VISIBLE);
            isKUR = true;
        }

        tv_info.setText(Html.fromHtml(Stringinfo.info_prescreening));
    }

    @Override
    protected void onResume() {
        super.onResume();
        setDisable();
        loadPrescreening();
    }

    private void backgroundStatusBar(){
        Window window = getWindow();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    private void scrollPageAutomatic(){
        sv_prescreening.post(new Runnable() {
            @Override
            public void run() {
                sv_prescreening.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });
    }

    private void setDisable(){
        iv_btndhn.setVisibility(View.GONE);
        iv_btndukcapil.setVisibility(View.GONE);
        iv_btnslik.setVisibility(View.GONE);
        iv_btnsikp.setVisibility(View.GONE);
        btn_send.setVisibility(View.GONE);

    }

    private void loadPrescreening() {
        loading.setVisibility(View.VISIBLE);
        Prescreening req = new Prescreening(idAplikasi);
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().inquiryPrescreening(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                loading.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()){
                        if(response.body().getStatus().equalsIgnoreCase("00"))
                        {
                            Gson gson = new Gson();
                            String dataPrescreeningString = response.body().getData().get("prescreening").toString();
                            data = gson.fromJson(dataPrescreeningString, DataPrescreening.class);

                            if (data.getsTATUSKAWIN().equalsIgnoreCase("2")){
                                isKawin = true;
                            }

                            if (!data.getdHN().trim().isEmpty()) {
                                flagCheckDhn = true;
                                setCustomView(Boolean.parseBoolean(data.getdHN()), iv_completedhn, tv_dhn);
                            }

                            if (!data.getdUKCAPIL().trim().isEmpty() ) {
                                flagCheckDukcapil = true;
                                setCustomView(Boolean.parseBoolean(data.getdUKCAPIL()), iv_completedukcapil, tv_dukcapil);
                            }

                            if (!data.getsLIK().trim().isEmpty() ) {
                                btn_download.setVisibility(View.VISIBLE);
                                btn_remaks.setVisibility(View.VISIBLE);
                                flagCheckSlik = true;
                                setCustomView(Boolean.parseBoolean(data.getsLIK()), iv_completeslik, tv_slik);
                            }


                            if (!data.getsIKP().trim().isEmpty()) {
                                flagCheckSikp = true;
                                setCustomView(Boolean.parseBoolean(data.getsIKP()), iv_completesikp, tv_sikp);
                            }


                            if (data.getrESULT() != null)
                            {
                                ll_info.setVisibility(View.GONE);
                                ll_hasilprescreening.setVisibility(View.VISIBLE);
                                String resultString = data.getrESULT();
                                tv_hasilprescreening.setText(resultString);
                                if (resultString.equalsIgnoreCase("merah")){
                                    tv_hasilprescreening.setBackgroundResource(R.drawable.shapered);
                                }
                                else if (resultString.equalsIgnoreCase("kuning")){
                                    tv_hasilprescreening.setBackgroundResource(R.drawable.shapeyellow);
                                }
                                else{
                                    tv_hasilprescreening.setBackgroundResource(R.drawable.shapegreen);
                                }
                                scrollPageAutomatic();
                            }
                        }
                        else {
                            AppUtil.notiferror(PrescreeningActivity_Apr.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(PrescreeningActivity_Apr.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                AppUtil.notiferror(PrescreeningActivity_Apr.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    public void setCustomView(Boolean bol, ImageView iv, TextView tv)
    {
        iv.setImageResource(R.drawable.ic_checklist);
        tv.setVisibility(View.VISIBLE);
        if (bol){
            tv.setText("LOLOS");
            tv.setTextColor(getResources().getColor(R.color.colorGreenSuccess));
        }
        else{
            tv.setText("TIDAK LOLOS");
            tv.setTextColor(getResources().getColor(R.color.colorError));
        }
    }

    private void downloadSlik(int id){
        loading.setVisibility(View.VISIBLE);
        tv_loading.setVisibility(View.VISIBLE);
        tv_loading.setText("Downloading...");
        ReqDownloadSlik req = new ReqDownloadSlik();
        req.setIdAplikasi(String.valueOf(idAplikasi));
        Call<ParseResponse> call = null;
        if (id == 1){
            call = apiClientAdapter.getApiInterface().downloadSlik(req);
        }
        else{
            call = apiClientAdapter.getApiInterface().downloadSlikPasangan(req);
        }
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                loading.setVisibility(View.GONE);
                tv_loading.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()){
                        if(response.body().getStatus().equalsIgnoreCase("00"))
                        {
                            String fileName = "Hasil SLIK ID APLIKASI : "+String.valueOf(idAplikasi)+"-"+new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime())+".pdf";

                            String dataString="";
                            try {
                                dataString = response.body().getData().get("SLIKPDFDebitur").getAsString();
                            }
                            catch(Exception e){
                                dataString = response.body().getData().get("mup").getAsString();
                                AppUtil.logSecure("dataStringnull",e.getMessage());
                            }
                            byte[] data = Base64.decode(dataString, Base64.DEFAULT);
                            new DownloadTask(PrescreeningActivity_Apr.this, data, fileName, "approved");
                        }
                        else {
                            AppUtil.notiferror(PrescreeningActivity_Apr.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(PrescreeningActivity_Apr.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                tv_loading.setVisibility(View.GONE);
                AppUtil.notiferror(PrescreeningActivity_Apr.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_download:
                CustomDialog.DialogDownloadSlik(PrescreeningActivity_Apr.this, isKawin, "Download Hasil SLIK", "Silahkan download hasil SLIK", this);
                break;
            case R.id.btn_remaks:
                Bundle bundle = new Bundle();
                bundle.putInt("idAplikasi", idAplikasi);
                bundle.putInt("statusKawin", AppUtil.parseIntWithDefault(data.getsTATUSKAWIN(), 0));

                RouteApp routeApp = new RouteApp(this);
                routeApp.openActivity(RemaksSlikActivity_Apr.class, bundle);
        }
    }

    @Override
    public void success(boolean val) {
        if(val){
            Intent intent = new Intent(this, HotprospekActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
        }

    }

    @Override
    public void confirm(boolean val) {

    }

    @Override
    public void downloadSLik(int id) {
        downloadSlik(id);
    }
}
