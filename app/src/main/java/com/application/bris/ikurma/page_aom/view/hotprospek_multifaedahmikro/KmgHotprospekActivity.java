package com.application.bris.ikurma.page_aom.view.hotprospek_multifaedahmikro;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.application.bris.ikurma.R;
import com.application.bris.ikurma.api.model.Error;
import com.application.bris.ikurma.api.model.ParseResponseArr;
import com.application.bris.ikurma.api.model.ParseResponseError;
import com.application.bris.ikurma.api.model.request.hotprospek.listHotprospek;
import com.application.bris.ikurma.api.service.ApiClientAdapter;
import com.application.bris.ikurma.database.AppPreferences;
import com.application.bris.ikurma.page_aom.adapter.hotprospek_multifaedahmikro.HotprospekKmgAdapater;
import com.application.bris.ikurma.page_aom.listener.HotprospekListener;
import com.application.bris.ikurma.page_aom.model.hotprospek;
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

public class KmgHotprospekActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, HotprospekListener {

    @BindView(R.id.tb_regular)
    Toolbar tb_regular;
    @BindView(R.id.rv_listhotprospek)
    RecyclerView rv_listhotprospek;
    @BindView(R.id.refresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.sm_placeholder)
    ShimmerFrameLayout sm_placeholder;
    @BindView(R.id.ll_emptydata)
    LinearLayout ll_emptydata;

    private SearchView searchView;
    List<hotprospek> dataHotprospek;
    HotprospekKmgAdapater adapaterHotprospek;

    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ao_activity_hotprospek);
        main();
        initializeHotprospek();
        sm_placeholder.startShimmer();
        backgroundStatusBar();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        initializeHotprospek();
//        sm_placeholder.startShimmer();
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

        searchHotprospek();

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
        AppUtil.toolbarRegular(this, "Daftar Hotprospek");
        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.setDistanceToTriggerSync(220);
    }

    public void initializeHotprospek(){
        listHotprospek req = new listHotprospek();
        req.setUid(String.valueOf(appPreferences.getUid()));
        req.setKodeSkk(appPreferences.getKodeKantor());
        req.setNamaNasabah("");
        Call<ParseResponseArr> call = apiClientAdapter.getApiInterface().listHotprospekKmg(req);
        call.enqueue(new Callback<ParseResponseArr>() {
            @Override
            public void onResponse(Call<ParseResponseArr> call, Response<ParseResponseArr> response) {
                sm_placeholder.stopShimmer();
                sm_placeholder.setVisibility(View.GONE);
                rv_listhotprospek.setVisibility(View.VISIBLE);

                try {
                    if(response.isSuccessful()){
                        if(response.body().getStatus().equalsIgnoreCase("00")){
                            String listDataString = response.body().getData().toString();
                            Gson gson = new Gson();
                            Type type = new TypeToken<List<hotprospek>>() {}.getType();

                            dataHotprospek = gson.fromJson(listDataString, type);

                            if (dataHotprospek.size() > 0){
                                ll_emptydata.setVisibility(View.GONE);
                                adapaterHotprospek = new HotprospekKmgAdapater(KmgHotprospekActivity.this, dataHotprospek, KmgHotprospekActivity.this);
                                rv_listhotprospek.setLayoutManager(new LinearLayoutManager(KmgHotprospekActivity.this));
                                rv_listhotprospek.setItemAnimator(new DefaultItemAnimator());
                                rv_listhotprospek.setAdapter(adapaterHotprospek);
                            }
                            else {
                                ll_emptydata.setVisibility(View.VISIBLE);
                            }
                        }
                        else{
                            AppUtil.notiferror(KmgHotprospekActivity.this, findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else{
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(KmgHotprospekActivity.this, findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponseArr> call, Throwable t) {
                AppUtil.notiferror(KmgHotprospekActivity.this, findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
            }
        });
    }

    private void searchHotprospek(){
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                try {
                    adapaterHotprospek.getFilter().filter(query);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                try {
                    adapaterHotprospek.getFilter().filter(query);
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
        swipeRefreshLayout.setRefreshing(false);
        rv_listhotprospek.setVisibility(View.GONE);
        initializeHotprospek();
        sm_placeholder.setVisibility(View.VISIBLE);
        sm_placeholder.startShimmer();
//        HotprospekActivity.this.recreate();
    }

    @Override
    public void onHotprospekSelect(int id) {
        Intent it = new Intent(this, KmgHotprospekDetailActivity.class);
        it.putExtra("idAplikasi", id);
        startActivity(it);
    }
}
