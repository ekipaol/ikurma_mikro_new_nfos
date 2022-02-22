package com.application.bris.ikurma.page_aom.view.hotprospek.scoring;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.application.bris.ikurma.R;
import com.application.bris.ikurma.api.model.Error;
import com.application.bris.ikurma.api.model.ParseResponse;
import com.application.bris.ikurma.api.model.ParseResponseError;
import com.application.bris.ikurma.api.model.request.hotprospek.inquiryScoring;
import com.application.bris.ikurma.api.service.ApiClientAdapter;
import com.application.bris.ikurma.database.AppPreferences;
import com.application.bris.ikurma.page_aom.model.DataScoringNos;
import com.application.bris.ikurma.util.AppUtil;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScoringActivityNos extends AppCompatActivity  {



    @BindView(R.id.btn_selesai)
    Button btn_send;
    @BindView(R.id.tv_hasil_scoring_nos)
    TextView tv_hasil_scoring_nos;
    @BindView(R.id.progressbar_loading)
    RelativeLayout loading;


    private int idAplikasi;
    private int cif;
    private boolean isRiwayat=false;
    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;

    private DataScoringNos data;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ao_activity_scoring_nos_mikro);
        ButterKnife.bind(this);
        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);
        idAplikasi = getIntent().getIntExtra("idAplikasi", 0);
        cif = getIntent().getIntExtra("cif", 0);
        backgroundStatusBar();
        AppUtil.toolbarRegular(this, getString(R.string.submenu_hotprospek_scoring));

        if(getIntent().hasExtra("isRiwayat")){
                isRiwayat=getIntent().getBooleanExtra("isRiwayat",false);
        }


        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               backToDetailHotprospek();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
      loading.setVisibility(View.VISIBLE);
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

    private void loadData() {
        inquiryScoring req = new inquiryScoring(cif, idAplikasi);

        Call<ParseResponse> call;


        //halaman approved atau rejected, ambil scoring dari inquiryScoring supaya gak update flag
        if(isRiwayat){
            call = apiClientAdapter.getApiInterface().inquiryScoring(req);
        }
        else{
            call = apiClientAdapter.getApiInterface().hitungScoring(req);
        }

        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                loading.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()){
                        if (response.body().getStatus().equalsIgnoreCase("00")){
                            Gson gson = new Gson();
                            data = gson.fromJson(response.body().getData().toString(), DataScoringNos.class);
                            setData();
                        }
                        else{
                            AppUtil.notiferror(ScoringActivityNos.this, findViewById(android.R.id.content), response.body().getMessage());
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    finish();
                                }
                            }, 2000);
                        }
                    }
                    else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(ScoringActivityNos.this, findViewById(android.R.id.content), error.getMessage());
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                finish();
                            }
                        }, 2000);
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            finish();
                        }
                    }, 2000);
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                AppUtil.notiferror(ScoringActivityNos.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                }, 2000);
            }
        });

    }

    public void setData()
    {
      tv_hasil_scoring_nos.setText("Scoring Berhasil Dilakukan");

      if(data.getHasilScoring().toLowerCase().contains("tidak direkomendasikan")){
          tv_hasil_scoring_nos.setText("Tidak Direkomendasikan");
          tv_hasil_scoring_nos.setBackgroundColor(getResources().getColor(R.color.red_btn_bg_color));
      }
      else{
          tv_hasil_scoring_nos.setBackgroundColor(getResources().getColor(R.color.main_green_color));
      }
    }


    public void backToDetailHotprospek() {

          finish();

    }





}