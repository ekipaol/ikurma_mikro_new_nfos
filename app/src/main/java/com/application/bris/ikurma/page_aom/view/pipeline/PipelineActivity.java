package com.application.bris.ikurma.page_aom.view.pipeline;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.application.bris.ikurma.R;
import com.application.bris.ikurma.api.model.Error;
import com.application.bris.ikurma.api.model.ParseResponseArr;
import com.application.bris.ikurma.api.model.ParseResponseError;
import com.application.bris.ikurma.api.model.request.pipeline.listPipeline;
import com.application.bris.ikurma.api.service.ApiClientAdapter;
import com.application.bris.ikurma.database.AppPreferences;
import com.application.bris.ikurma.page_aom.adapter.pipeline.PipelineAdapater;
import com.application.bris.ikurma.page_aom.dialog.BSCekNasabah;
import com.application.bris.ikurma.page_aom.listener.PipelineListener;
import com.application.bris.ikurma.page_aom.model.pipeline;
import com.application.bris.ikurma.util.AppUtil;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PipelineActivity extends AppCompatActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener, PipelineListener {

    @BindView(R.id.tb_regular)
    Toolbar tb_regular;
    @BindView(R.id.rv_listpipeline)
    RecyclerView rv_listpipeline;
    @BindView(R.id.refresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.fab_addpipeline)
    FloatingActionButton fab_addpipeline;
    @BindView(R.id.sm_placeholder)
    ShimmerFrameLayout sm_placeholder;
    @BindView(R.id.ll_emptydata)
    LinearLayout ll_emptydata;

    private SearchView searchView;
    List<pipeline> dataPipeline;
    PipelineAdapater adapaterPipeline;

    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ao_activity_pipeline);
        main();
        backgroundStatusBar();
    }

    @Override
    protected void onResume() {
        super.onResume();
        initializePipeline();
        sm_placeholder.startShimmer();
    }

    @Override
    protected void onPause() {
        super.onPause();
        sm_placeholder.stopShimmer();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search_menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setQueryHint("Nama Nasabah, Produk, dll ..");

        searchPipeline();

        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                return true;
            case android.R.id.home:
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
        AppUtil.toolbarRegular(this, "Daftar Pipeline");
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setDistanceToTriggerSync(220);
        fab_addpipeline.setOnClickListener(this);
    }

    public void initializePipeline(){
//        apiClientAdapter=new ApiClientAdapter(this,"http://10.0.1.210:8080/MobileBRISIAPI/webresources/");
        listPipeline req = new listPipeline();
        req.setUid(String.valueOf(appPreferences.getUid()));
        req.setKodeSkk(appPreferences.getKodeKantor());
        req.setNamaNasabah("");

//        req.setUid("string");
//        req.setKodeSkk("string");
//        req.setNamaNasabah("string");
//        Toast.makeText(this, "mengirim parameter testing ke list pipeline", Toast.LENGTH_SHORT).show();
        Call<ParseResponseArr> call = apiClientAdapter.getApiInterface().listPipeline(req);
        call.enqueue(new Callback<ParseResponseArr>() {
            @Override
            public void onResponse(Call<ParseResponseArr> call, Response<ParseResponseArr> response) {
                sm_placeholder.stopShimmer();
                sm_placeholder.setVisibility(View.GONE);
                try {
                    if(response.isSuccessful()){
                        if(response.body().getStatus().equalsIgnoreCase("00")){
                            String listDataString = response.body().getData().toString();
                            Gson gson = new Gson();
                            Type type = new TypeToken<List<pipeline>>() {}.getType();
                            dataPipeline = gson.fromJson(listDataString, type);
                            if (dataPipeline.size() > 0){
                                ll_emptydata.setVisibility(View.GONE);
                                adapaterPipeline = new PipelineAdapater(PipelineActivity.this, dataPipeline, PipelineActivity.this);
                                rv_listpipeline.setLayoutManager(new LinearLayoutManager(PipelineActivity.this));
                                rv_listpipeline.setItemAnimator(new DefaultItemAnimator());
                                rv_listpipeline.setAdapter(adapaterPipeline);
                            }
                            else {
                                ll_emptydata.setVisibility(View.VISIBLE);
                            }
                        }
                        else{
                            AppUtil.notiferror(PipelineActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else{
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(PipelineActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponseArr> call, Throwable t) {
                AppUtil.notiferror(PipelineActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    private void searchPipeline(){
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                try {
                    adapaterPipeline.getFilter().filter(query);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                try {
                    adapaterPipeline.getFilter().filter(query);
                    return false;
                }
                catch (Exception e){
                    e.printStackTrace();
                    return false;
                }
            }
        });
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        PipelineActivity.this.recreate();
    }

    @Override
    public void onPipelineSelect(int id) {
        Intent it = new Intent(this, PipelineDetailActivity.class);
        it.putExtra("idPipeline", id);
        startActivity(it);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.fab_addpipeline:
                BSCekNasabah.display(getSupportFragmentManager());
                break;
        }
    }
}
