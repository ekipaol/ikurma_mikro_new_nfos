package com.application.bris.ikurma.page_aom.view.hotprospek.prescreening.remaksslik;

import android.os.Build;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;
import com.application.bris.ikurma.R;
import com.application.bris.ikurma.api.model.Error;
import com.application.bris.ikurma.api.model.ParseResponse;
import com.application.bris.ikurma.api.model.ParseResponseError;
import com.application.bris.ikurma.api.model.request.hotprospek.Prescreening;
import com.application.bris.ikurma.api.service.ApiClientAdapter;
import com.application.bris.ikurma.page_aom.listener.MemosalesListener;
import com.application.bris.ikurma.page_aom.view.hotprospek.prescreening.model.ModelRemaksSlik;
import com.application.bris.ikurma.util.AppUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by idong on 20/06/2019.
 */

public class RemaksSlikActivity extends AppCompatActivity implements MemosalesListener{

    @BindView(R.id.tb_regular)
    Toolbar tb_regular;
    @BindView(R.id.vp_remaksslik)
    ViewPager vp_remaksslik;
    @BindView(R.id.tab_remaksslik)
    TabLayout tab_remaksslik;
    @BindView(R.id.progressbar_loading)
    RelativeLayout loading;

    public static int idAplikasi;
    private int statusKawin;
    private ApiClientAdapter apiClientAdapter;
    public String slikNasabahString = "";
    public String slikPasanganString = "";
    public int flagMemosales;
    public static List<ModelRemaksSlik> slikNasabah;
    public static List<ModelRemaksSlik> slikPasangan;
    private ViewPagerAdapter adapter = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ao_activity_remaksslik);
        ButterKnife.bind(this);
        apiClientAdapter = new ApiClientAdapter(this);
        Bundle extras = getIntent().getExtras();
        idAplikasi = extras.getInt("idAplikasi");
        statusKawin = extras.getInt("statusKawin");
        flagMemosales = extras.getInt("flagMemosales");
        backgroundStatusBar();
        AppUtil.toolbarRegular(this, "Hasil SLIK");
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    private void backgroundStatusBar(){
        try {
            Window window = getWindow();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                tb_regular.setElevation(0);
                window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void loadData() {
        loading.setVisibility(View.VISIBLE);
        Prescreening req = new Prescreening(idAplikasi);
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().inquiryRemaksSlik(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                loading.setVisibility(View.GONE);
                try {
                    if (response.isSuccessful()){
                        if (response.body().getStatus().equalsIgnoreCase("00")){
                            Gson gson = new Gson();
                            Type type = new TypeToken<List<ModelRemaksSlik>>() {}.getType();
                            if (statusKawin == 2 &&response.body().getData().get("dtMemosalesPasangan")!=null){
                                slikNasabahString = response.body().getData().get("dtMemosales").toString();
                                slikPasanganString = response.body().getData().get("dtMemosalesPasangan").toString();

                                slikNasabah = gson.fromJson(slikNasabahString, type);
                                slikPasangan = gson.fromJson(slikPasanganString, type);
                            }
                            else{
                                slikNasabahString = response.body().getData().get("dtMemosales").toString();
                                slikNasabah = gson.fromJson(slikNasabahString, type);
                                slikPasangan = Collections.emptyList();
                            }

                            adapter = new ViewPagerAdapter(getSupportFragmentManager());
                            vp_remaksslik.setAdapter(adapter);
                            tab_remaksslik.setupWithViewPager(vp_remaksslik);
                            adapter.notifyDataSetChanged();
                        }
                        else{
                            AppUtil.notiferror(RemaksSlikActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                            finish();
                        }
                    }
                    else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(RemaksSlikActivity.this, findViewById(android.R.id.content), error.getMessage());
                        finish();
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                loading.setVisibility(View.GONE);
                AppUtil.notiferror(RemaksSlikActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
                finish();
            }
        });

    }

    @Override
    public void onFlagMemosales(int flag) {

    }

    // Adapter for the viewpager using FragmentPagerAdapter
    class ViewPagerAdapter extends FragmentStatePagerAdapter {

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fr = null;
            Bundle bdl = new Bundle();
            if (statusKawin == 2){
                switch (position){
                    case 0:
                        fr = new FragmentRemaksSlikNasabah();
                        bdl.putString("dataSlikNasabah", slikNasabahString);
                        fr.setArguments(bdl);
                        return fr;

                    case 1:
                        fr = new FragmentRemaksSlikPasangan();
                        bdl.putString("dataSlikPasangan", slikPasanganString);
                        fr.setArguments(bdl);
                        return fr;
                    default:
                        return null;
                }
            }
            else{
                switch (position){
                    case 0:
                        fr = new FragmentRemaksSlikNasabah();
                        bdl.putString("dataSlikNasabah", slikNasabahString);
                        fr.setArguments(bdl);
                        return fr;
                    default:
                        return null;
                }
            }

        }

        @Override
        public int getCount() {
            if (statusKawin == 2){
                return 2;
            }
            else{
                return 1;
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0: return "Slik Nasabah";
                case 1: return "Slik Pasangan";
                default: return null;
            }
        }
    }

}
