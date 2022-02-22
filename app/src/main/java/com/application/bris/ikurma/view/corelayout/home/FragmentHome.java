package com.application.bris.ikurma.view.corelayout.home;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.application.bris.ikurma.BuildConfig;
import com.application.bris.ikurma.page_aom.listener.HotprospekHomeListener;
import com.application.bris.ikurma.page_aom.listener.PipelineHomeListener;
import com.application.bris.ikurma.page_aom.view.approved_multifaedahmikro.KmgApprovedActivity;
import com.application.bris.ikurma.page_aom.view.hotprospek_multifaedahmikro.KmgHotprospekDetailActivity;
import com.application.bris.ikurma.page_aom.view.pipeline_multifaedahmikro.KmgPipelineDetailActivity;
import com.application.bris.ikurma.page_aom.view.rejected_multifaedahmikro.KmgRejectedActivity;
import com.application.bris.ikurma.page_monitoring.MonitoringPencairanActivity;
import com.application.bris.ikurma.view.corelayout.login.LoginActivity;
import com.application.bris.ikurma.view.corelayout.login.LoginActivity2;
import com.application.bris.ikurma.view.corelayout.menu.MenuPilihProdukActivity;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import androidx.fragment.app.Fragment;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.application.bris.ikurma.R;
import com.application.bris.ikurma.adapter.hotprospek.HotprospekHomeAdapater;
import com.application.bris.ikurma.api.config.UriApi;
import com.application.bris.ikurma.api.model.Error;
import com.application.bris.ikurma.api.model.ParseResponse;
import com.application.bris.ikurma.api.model.ParseResponseError;
import com.application.bris.ikurma.api.model.request.general.home;
import com.application.bris.ikurma.api.service.ApiClientAdapter;
import com.application.bris.ikurma.config.menu.Menu;
import com.application.bris.ikurma.adapter.menu.MenuAdapter;
import com.application.bris.ikurma.database.AppPreferences;
import com.application.bris.ikurma.listener.menu.MenuClickListener;
import com.application.bris.ikurma.model.menu.ListViewMenu;
import com.application.bris.ikurma.adapter.pipeline.PipelineHomeAdapater;
import com.application.bris.ikurma.page_aom.model.hotprospek;
import com.application.bris.ikurma.page_aom.model.pipeline;
import com.application.bris.ikurma.page_aom.view.hotprospek.HotprospekDetailActivity;
import com.application.bris.ikurma.page_aom.view.pipeline.PipelineDetailActivity;
import com.application.bris.ikurma.util.AppBarStateChangedListener;
import com.application.bris.ikurma.util.AppUtil;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by PID on 3/29/2019.
 */

public class FragmentHome extends Fragment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener, MenuClickListener, PipelineHomeListener, HotprospekHomeListener {
    private View view;

    @BindView(R.id.appbar)
    AppBarLayout appbar;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsing_toolbar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_toolbartitle)
    TextView tv_toolbartitle;
    @BindView(R.id.refresh)
    SwipeRefreshLayout swipeRefreshLayout;
    @BindView(R.id.rv_menu)
    RecyclerView rv_menu;
    @BindView(R.id.rv_pipeline)
    RecyclerView rv_pipeline;
    @BindView(R.id.rv_hotprospek)
    RecyclerView rv_hotprospek;
    @BindView(R.id.progress_menu)
    RelativeLayout progress_menu;
    @BindView(R.id.sm_placeholder_pipeline)
    ShimmerFrameLayout sm_placeholder_pipeline;
    @BindView(R.id.sm_placeholder_hotprospek)
    ShimmerFrameLayout sm_placeholder_hotprospek;
    @BindView(R.id.ll_emptydata_pipeline)
    LinearLayout ll_emptydata_pipeline;
    @BindView(R.id.ll_emptydata_hotprospek)
    LinearLayout ll_emptydata_hotprospek;
    @BindView(R.id.iv_morepipeline)
    ImageView iv_morepipeline;
    @BindView(R.id.iv_morehotprospek)
    ImageView iv_morehotprospek;
    @BindView(R.id.iv_profilpicture)
    ImageView iv_profilpicture;
    @BindView(R.id.tv_nama)
    TextView tv_nama;
    @BindView(R.id.tv_username)
    TextView tv_username;

    private List<ListViewMenu> dataMenu;
    private List<pipeline> dataPipeline;
    private int jumlahPipeline = 0;
    private List<hotprospek> dataHotprospek;
    private int jumlahHotprospek;
    private int jumlahApproved;
    private int jumlahRejected;
    private MenuAdapter adapterMenu;
    private PipelineHomeAdapater adapaterPipeline;
    private HotprospekHomeAdapater adapterHotprospek;
    private GridLayoutManager layoutMenu;
    private int coloumMenu = 4;
    private LinearLayoutManager layoutPipelineHome;
    private LinearLayoutManager layoutHotprospekHome;
    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        try {
            view = inflater.inflate(R.layout.fragment_home, container, false);
        }
        catch (Exception e){
            e.printStackTrace();
//            Log.e("Error yaw", e);
        }



        ButterKnife.bind(this, view);
        apiClientAdapter = new ApiClientAdapter(getContext());
        appPreferences = new AppPreferences(getContext());
        collapsing_toolbar.setStatusBarScrimColor(getResources().getColor(R.color.colorBackgroundTransparent));
        checkCollapse();

        //disable onlick for AOM NPF
        if(appPreferences.getFidRole()!=122){
            iv_morepipeline.setOnClickListener(this);
            iv_morehotprospek.setOnClickListener(this);
        }

        loadProfil();
        loadData();
        initializeMenu();
        initializePipelineHome();
        initializeHotprospekHome();

        swipeRefreshLayout.setOnRefreshListener(this);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
//        loadProfil();
//        loadData();
//        initializeMenu();
//        initializePipelineHome();
//        initializeHotprospekHome();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        getActivity().recreate();
    }

    private void loadProfil() {

//        if (appPreferences.getImageProfilBase64().equalsIgnoreCase("")){
//            String urlPhoto = UriApi.Baseurl.URL+UriApi.foto.urlPhotoProfil+appPreferences.getNik();
//            Glide
//                    .with(getContext())
//                    .asBitmap()
//                    .load(urlPhoto)
//                    .centerCrop()
//                    .placeholder(R.drawable.banner_placeholder)
//                    .into(new CustomTarget<Bitmap>() {
//                        @Override
//                        public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
//                            iv_profilpicture.setImageBitmap(resource);
//                            appPreferences.setImageProfilBase64(AppUtil.encodeImageTobase64(resource));
//                        }
//
//                        @Override
//                        public void onLoadCleared(@Nullable Drawable placeholder) {
//
//                        }
//                    });
//        }
//        else{
//            Glide
//                    .with(getContext())
//                    .asBitmap()
//                    .load(AppUtil.decodeImageTobase64(appPreferences.getImageProfilBase64()))
//                    .centerCrop()
//                    .placeholder(R.drawable.banner_placeholder)
//                    .into(iv_profilpicture);
//        }

        tv_nama.setText(appPreferences.getNama());
        tv_username.setText(appPreferences.getJabatan()+", "+appPreferences.getNamaKantor());
    }

    public void loadData(){
        progress_menu.setVisibility(View.VISIBLE);
        sm_placeholder_pipeline.startShimmer();
        sm_placeholder_hotprospek.startShimmer();
        home req = new home(appPreferences.getUid(), appPreferences.getKodeKantor());
        Call<ParseResponse> call = apiClientAdapter.getApiInterface().home(req);
        call.enqueue(new Callback<ParseResponse>() {
            @Override
            public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                progress_menu.setVisibility(View.GONE);
                sm_placeholder_pipeline.stopShimmer();
                sm_placeholder_pipeline.setVisibility(View.GONE);
                sm_placeholder_hotprospek.stopShimmer();
                sm_placeholder_hotprospek.setVisibility(View.GONE);
                try {
                    if(response.isSuccessful()){
                        if(response.body().getStatus().equalsIgnoreCase("00")){
                            if (response.body().getData().get("jlhPipeline").getAsInt() > 0){
                                Gson gson = new Gson();
                                Type typePipeline = new TypeToken<List<pipeline>>() {}.getType();
                                try{
                                    dataPipeline = gson.fromJson(response.body().getData().get("ListPipeline").toString(), typePipeline);
                                }
                                catch (NullPointerException e){
                                    AppUtil.logSecure("pipeline","pipeline is null");
                                    dataPipeline=new ArrayList<>();
                                }

                                if (dataPipeline.size() > 0){
                                    ll_emptydata_pipeline.setVisibility(View.GONE);
                                    initializePipelineHome();
                                }
                                else  {
                                    ll_emptydata_pipeline.setVisibility(View.VISIBLE);
                                }

                                jumlahPipeline = response.body().getData().get("jlhPipeline").getAsInt();
                                dataMenu.get(0).setJmlPipeline(jumlahPipeline);
                                adapterMenu.notifyItemChanged(0);
                            }
                            if (response.body().getData().get("jlhHotProspek").getAsInt() > 0){
                                Gson gson = new Gson();
                                Type typeHotprospek = new TypeToken<List<hotprospek>>() {}.getType();

                                try{
                                    dataHotprospek = gson.fromJson(response.body().getData().get("listHotProspek").toString(), typeHotprospek);
                                }
                                catch (NullPointerException e){
                                    dataHotprospek=new ArrayList<>();
                                    AppUtil.logSecure("hotprospek","hitprospek is null");
                                }

                                if (dataHotprospek.size() > 0){
                                    ll_emptydata_hotprospek.setVisibility(View.GONE);
                                    initializeHotprospekHome();
                                }
                                else  {
                                    ll_emptydata_hotprospek.setVisibility(View.VISIBLE);
                                }

                                jumlahHotprospek = response.body().getData().get("jlhHotProspek").getAsInt();
                                dataMenu.get(1).setJmlHotprospek(jumlahHotprospek);
                                adapterMenu.notifyItemChanged(1);
                            }
                            if (response.body().getData().get("jlhApproved").getAsInt() > 0){
                                jumlahApproved = response.body().getData().get("jlhApproved").getAsInt();
                                dataMenu.get(2).setJmlApproved(jumlahApproved);
                                adapterMenu.notifyItemChanged(2);
                            }
                            if (response.body().getData().get("jlhRejected").getAsInt() > 0){
                                jumlahRejected = response.body().getData().get("jlhRejected").getAsInt();
                                dataMenu.get(3).setJmlRejected(jumlahRejected);
                                adapterMenu.notifyItemChanged(3);
                            }
                        }
                        else {
                            AppUtil.notiferror(getContext(), getActivity().findViewById(android.R.id.content), response.body().getMessage());
                        }
                    }
                    else {
                        Error error = ParseResponseError.confirmEror(response.errorBody());
                        AppUtil.notiferror(getContext(), getActivity().findViewById(android.R.id.content), error.getMessage());
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ParseResponse> call, Throwable t) {
                progress_menu.setVisibility(View.GONE);
                sm_placeholder_pipeline.stopShimmer();
                sm_placeholder_pipeline.setVisibility(View.GONE);
                sm_placeholder_hotprospek.stopShimmer();
                sm_placeholder_hotprospek.setVisibility(View.GONE);
                AppUtil.showToastShort(getContext(), getString(R.string.txt_connection_failure));
            }
        });

    }

    public void initializeMenu(){
        rv_menu.setHasFixedSize(true);


        dataMenu = getListMenu();
        adapterMenu = new MenuAdapter(getContext(), dataMenu, this);
        layoutMenu = new GridLayoutManager(getContext(), coloumMenu);
        rv_menu.setLayoutManager(layoutMenu);
        rv_menu.setAdapter(adapterMenu);
        ViewCompat.setNestedScrollingEnabled(rv_menu, false);
    }

    private List<ListViewMenu> getListMenu() {
        List<ListViewMenu> menu = new ArrayList<>();

        //kalo ao npf gak bisa akses menu selain monitoring
        if(appPreferences.getFidRole()==122){
            Menu.mainMenuAONpf(getContext(), menu);
        }
        else{
            Menu.mainMenuAO(getContext(), menu);
        }

        return menu;
    }

    public void initializePipelineHome(){
        adapaterPipeline = new PipelineHomeAdapater(getContext(), dataPipeline, this);
        layoutPipelineHome = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rv_pipeline.setLayoutManager(layoutPipelineHome);
        rv_pipeline.setAdapter(adapaterPipeline);
        ViewCompat.setNestedScrollingEnabled(rv_pipeline, false);

        if (dataPipeline == null){
            ll_emptydata_pipeline.setVisibility(View.VISIBLE);
        }
    }

    public void initializeHotprospekHome(){
        adapterHotprospek = new HotprospekHomeAdapater(getContext(), dataHotprospek, this);
        layoutHotprospekHome = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rv_hotprospek.setLayoutManager(layoutHotprospekHome);
        rv_hotprospek.setAdapter(adapterHotprospek);
        ViewCompat.setNestedScrollingEnabled(rv_hotprospek, false);
        if (dataHotprospek == null){
            ll_emptydata_hotprospek.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onMenuClick(String menu) {

        if (menu.equalsIgnoreCase(getString(R.string.menu_pipeline))){
            Intent it = new Intent(getContext(), MenuPilihProdukActivity.class);
            it.putExtra("jenisMenu","pipeline");
            startActivity(it);
        }
        else if (menu.equalsIgnoreCase(getString(R.string.menu_hotprospek))){
            Intent it = new Intent(getContext(), MenuPilihProdukActivity.class);
            it.putExtra("jenisMenu","hotprospek");
            startActivity(it);
        }
        else if (menu.equalsIgnoreCase(getString(R.string.menu_approved))){
            Intent it = new Intent(getContext(), KmgApprovedActivity.class);
            startActivity(it);
        }
        else if (menu.equalsIgnoreCase(getString(R.string.menu_rejected))){
            Intent it = new Intent(getContext(), KmgRejectedActivity.class);
            startActivity(it);
        }
        else if (menu.equalsIgnoreCase("monitoring")){
            Intent it = new Intent(getContext(), MonitoringPencairanActivity.class);
            startActivity(it);
        }
        else if (menu.equalsIgnoreCase("logout")){
            final SweetAlertDialog logout=new SweetAlertDialog(getContext(),SweetAlertDialog.WARNING_TYPE);
            logout.setCanceledOnTouchOutside(false);
            logout.setTitleText("Keluar");
            logout.setContentText("Apakah anda yakin ingin log out dari aplikasi?");
            logout.setConfirmText("Ya");
            logout.setCancelText("Batal");
            logout.setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                @Override
                public void onClick(SweetAlertDialog sweetAlertDialog) {
                    logout.dismissWithAnimation();
//                    getActivity().finish();
//                    if(BuildConfig.IS_PRODUCTION==false){
                        Intent intent=new Intent(getContext(), LoginActivity2.class);
                        intent.putExtra("type","bdwelcome");
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
//                    }
//                    else{
//                        Intent intent=new Intent(getContext(), LoginActivity.class);
//                        intent.putExtra("type","bdlogin");
//                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//
//                        startActivity(intent);
//                    }

                }
            });
            logout.show();
        }

        else{
            AppUtil.showToastShort(getContext(), menu);
        }
    }

    private void checkCollapse(){
        appbar.addOnOffsetChangedListener(new AppBarStateChangedListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                if (state.name().equalsIgnoreCase("COLLAPSED") || state.name().equalsIgnoreCase("IDLE")){
                    tv_toolbartitle.setText("i-Kurma");
                    backgroundStatusBar();
                    swipeRefreshLayout.setEnabled(false);
                }
                else {
                    backgroundStatusBar();
                    tv_toolbartitle.setText("");
                }
            }
        });
    }

    private void backgroundStatusBar(){
        Window window = getActivity().getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorBackgroundTransparent));
        }
    }

    private void backgroundStatusBar(String state){
        Window window = getActivity().getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            if (state.equalsIgnoreCase("COLLAPSED") || state.equalsIgnoreCase("IDLE")){
                window.setStatusBarColor(getActivity().getResources().getColor(R.color.colorWhite));
            }
            else {
                window.setStatusBarColor(getActivity().getResources().getColor(R.color.colorBackgroundTransparent));
                swipeRefreshLayout.setEnabled(true);
                swipeRefreshLayout.setDistanceToTriggerSync(220);
            }
        }
    }

    @Override
    public void onPipelineSelect(int id, String kodeProduk) {
        Intent it;
        if (kodeProduk.equalsIgnoreCase("428") || kodeProduk.equalsIgnoreCase("429") || kodeProduk.equalsIgnoreCase("316") || kodeProduk.equalsIgnoreCase("317") || kodeProduk.equalsIgnoreCase("321") || kodeProduk.equalsIgnoreCase("430")){
            it = new Intent(getContext(), KmgPipelineDetailActivity.class);
        } else {
            it = new Intent(getContext(), PipelineDetailActivity.class);
        }
        it.putExtra("idPipeline", id);
        startActivity(it);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_morepipeline:
                moreActivity(MenuPilihProdukActivity.class, "pipeline");
                break;
            case R.id.iv_morehotprospek:
                moreActivity(MenuPilihProdukActivity.class, "hotprospek");
                break;
        }
    }

    @Override
    public void onHotprospekSelect(int id, String kodeProduk) {
        Intent it = null;
        if (kodeProduk.equalsIgnoreCase("428") || kodeProduk.equalsIgnoreCase("429") || kodeProduk.equalsIgnoreCase("316") || kodeProduk.equalsIgnoreCase("317") || kodeProduk.equalsIgnoreCase("321") || kodeProduk.equalsIgnoreCase("430")){
            it = new Intent(getContext(), KmgHotprospekDetailActivity.class);
        } else {
            it = new Intent(getContext(), HotprospekDetailActivity.class);
        }

//        if (id == 102307){
//            it = new Intent(getContext(), KmgHotprospekDetailActivity.class);
//        }

        it.putExtra("idAplikasi", id);
        startActivity(it);
    }

    public void moreActivity(Class<?> className, String jenisMenu){
        Intent it = new Intent(getContext(), className);
        it.putExtra("jenisMenu", jenisMenu);
        startActivity(it);
    }
}
