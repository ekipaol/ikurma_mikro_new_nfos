package com.application.bris.ikurma.page_aom.view.approved.prescreening_apr.remaksslik;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import com.application.bris.ikurma.R;
import com.application.bris.ikurma.api.config.UriApi;
import com.application.bris.ikurma.api.service.ApiClientAdapter;
import com.application.bris.ikurma.baseapp.RouteApp;
import com.application.bris.ikurma.database.AppPreferences;
import com.application.bris.ikurma.page_aom.dialog.DialogPreviewPhoto;
import com.application.bris.ikurma.page_aom.view.hotprospek.prescreening.model.ModelRemaksSlik;
import com.application.bris.ikurma.util.AppUtil;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.makeramen.roundedimageview.RoundedDrawable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by idong on 17/09/2019.
 */

public class RemaksSlikEditActivity_Apr extends AppCompatActivity{

    @BindView(R.id.progressbar_loading)
    RelativeLayout loading;
    @BindView(R.id.tv_bank)
    TextView tv_bank;
    @BindView(R.id.tv_plafond)
    TextView tv_plafond;
    @BindView(R.id.tv_outstanding)
    TextView tv_outstanding;
    @BindView(R.id.tv_angsuran)
    TextView tv_angsuran;
    @BindView(R.id.tv_tanggalmulai)
    TextView tv_tanggalmulai;
    @BindView(R.id.tv_tanggaljatuhtempo)
    TextView tv_tanggaljatuhtempo;
    @BindView(R.id.tv_kolektabilitas)
    TextView tv_kolektabilitas;
    @BindView(R.id.tv_status)
    TextView tv_status;
    @BindView(R.id.sp_remaks)
    Spinner sp_remaks;
    @BindView(R.id.btn_foto)
    ImageView btn_foto;
    @BindView(R.id.iv_foto)
    ImageView iv_foto;
    @BindView(R.id.btn_send)
    Button btn_send;
    @BindView(R.id.ll_bukti_lunas)
    LinearLayout ll_bukti_lunas;

    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    private String dataString = "";
    private String idType = "";
    private int position;
    private ModelRemaksSlik data;
    private ArrayAdapter remaksArr;
    private List<String> remaksData;
    private String[] remaks;
    private String isSelectPhoto = "";
    private final int TAKE_PICTURE= 1;
    private final int PICT_PICTURE = 0;
    private Uri uri_foto;
    private Bitmap bitmap_foto;
    private int val_idPhoto = 0;
    private int dataRemaksPosition = 0;
    private RouteApp routeApp;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ao_activity_remaksslikedit);
        ButterKnife.bind(this);
        apiClientAdapter = new ApiClientAdapter(this);
        appPreferences = new AppPreferences(this);
        routeApp = new RouteApp(this);
        Bundle extras = getIntent().getExtras();
        dataString = extras.getString("dataString");
        idType = extras.getString("idType");
        position = extras.getInt("position", 0);
        backgroundStatusBar();
        loadData();
        AppUtil.toolbarRegular(this, "Remarks Hasil SLIK");

        sp_remaks.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dataRemaksPosition = position;
                if (position == 1){
                    ll_bukti_lunas.setVisibility(View.VISIBLE);
                }
                else {
                    ll_bukti_lunas.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        iv_foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogPreviewPhoto.display(getSupportFragmentManager(), "Bukti Lunas", ((RoundedDrawable)iv_foto.getDrawable()).getSourceBitmap());
            }
        });

    }

    private void backgroundStatusBar(){
        try {
            Window window = getWindow();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void loadData() {

        Gson gson = new Gson();
        data = gson.fromJson(dataString, ModelRemaksSlik.class);

        tv_bank.setText(data.getkODEBANK()+" - "+data.getnAMABANK());
        tv_plafond.setText(AppUtil.parseRupiah(String.valueOf(data.getpLAFOND())));
        tv_outstanding.setText(AppUtil.parseRupiah(String.valueOf(data.getoS())));
        tv_angsuran.setText(AppUtil.parseRupiah(String.valueOf(data.getaNGSURAN())));
        tv_tanggalmulai.setText(AppUtil.parseTanggalGeneral(data.gettANGGALMULAI(), "yyyyMMdd", "dd-MM-yyyy"));
        tv_tanggaljatuhtempo.setText(AppUtil.parseTanggalGeneral(data.gettANGGALJATUHTEMPO(), "yyyyMMdd", "dd-MM-yyyy"));
        tv_kolektabilitas.setText(String.valueOf(data.getkOL()));
        tv_status.setText(data.getkONDISIKET());

        remaks = getResources().getStringArray(R.array.remaks);
        remaksData = new ArrayList<>(Arrays.asList(remaks));
        remaksArr = new ArrayAdapter<String>(this, R.layout.spinner_style_lefttext, remaksData);
        remaksArr.setDropDownViewResource(android.R.layout.simple_list_item_1);
        sp_remaks.setAdapter(remaksArr);

        dataRemaksPosition = data.getrEMARK();
        sp_remaks.setSelection(data.getrEMARK());

        Glide
                .with(this)
                .load(UriApi.Baseurl.URL+UriApi.foto.urlPhoto+data.getfIDPHOTO())
                .centerCrop()
                .placeholder(R.drawable.banner_placeholder)
                .into(iv_foto);

        val_idPhoto = data.getfIDPHOTO();

    }
}
