package com.application.bris.ikurma.page_aom.view.hotprospek.lkn;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.application.bris.ikurma.R;
import com.application.bris.ikurma.api.model.Error;
import com.application.bris.ikurma.api.model.ParseResponse;
import com.application.bris.ikurma.api.model.ParseResponseError;
import com.application.bris.ikurma.api.model.request.hotprospek.cekRekomendasi;
import com.application.bris.ikurma.api.service.ApiClientAdapter;
import com.application.bris.ikurma.database.AppPreferences;
import com.application.bris.ikurma.database.pojo.LknPojo;
import com.application.bris.ikurma.page_aom.model.CekRekomendasi;
import com.application.bris.ikurma.page_aom.model.DataLkn;
import com.application.bris.ikurma.util.AppUtil;
import com.application.bris.ikurma.util.NumberTextWatcherForThousand;
import com.application.bris.ikurma.util.NumberTextWatcherCanNolForThousand;
import com.google.gson.Gson;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;
import java.math.BigDecimal;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

/**
 * Created by PID on 6/15/2019.
 */

@SuppressLint("ValidFragment")
public class FragmentRekomendasiPembiayaan extends Fragment implements Step, TextWatcher{

    @BindView(R.id.tv_produk)
    TextView tv_produk;
    @BindView(R.id.tv_tujuanpembiayaan)
    TextView tv_tujuanpembiayaan;
    @BindView(R.id.tv_plafondinduk)
    TextView tv_plafondinduk;
    @BindView(R.id.tv_tenor)
    TextView tv_tenor;
    @BindView(R.id.tf_nilaimodalkerja)
    TextFieldBoxes tf_nilaimodalkerja;
    @BindView(R.id.et_nilaimodalkerja)
    EditText et_nilaimodalkerja;
    @BindView(R.id.tf_waktumodalkerja)
    TextFieldBoxes tf_waktumodalkerja;
    @BindView(R.id.et_waktumodalkerja)
    EditText et_waktumodalkerja;
    @BindView(R.id.tf_nilaiinvestasi)
    TextFieldBoxes tf_nilaiinvestasi;
    @BindView(R.id.et_nilaiinvestasi)
    EditText et_nilaiinvestasi;
    @BindView(R.id.tf_waktuinvestasi)
    TextFieldBoxes tf_waktuinvestasi;
    @BindView(R.id.et_waktuinvestasi)
    EditText et_waktuinvestasi;
    @BindView(R.id.tf_nilaikonsumtif)
    TextFieldBoxes tf_nilaikonsumtif;
    @BindView(R.id.et_nilaikonsumtif)
    EditText et_nilaikonsumtif;
    @BindView(R.id.tf_waktukonsumtif)
    TextFieldBoxes tf_waktukonsumtif;
    @BindView(R.id.et_waktukonsumtif)
    EditText et_waktukonsumtif;
    @BindView(R.id.tf_nilaitakeover)
    TextFieldBoxes tf_nilaitakeover;
    @BindView(R.id.et_nilaitakeover)
    EditText et_nilaitakeover;
    @BindView(R.id.tf_waktutakeover)
    TextFieldBoxes tf_waktutakeover;
    @BindView(R.id.et_waktutakeover)
    EditText et_waktutakeover;
    @BindView(R.id.tf_waktuqardh)
    TextFieldBoxes tf_waktuqardh;
    @BindView(R.id.et_waktuqardh)
    EditText et_waktuqardh;
    @BindView(R.id.tf_totalrekomendasikomite)
    TextFieldBoxes tf_totalrekomendasikomite;
    @BindView(R.id.et_totalrekomendasikomite)
    EditText et_totalrekomendasikomite;


    @BindView(R.id.tf_angsuranpinjamansaatini)
    TextFieldBoxes tf_angsuranpinjamansaatini;
    @BindView(R.id.et_angsuranpinjamansaatini)
    EditText et_angsuranpinjamansaatini;

    @BindView(R.id.tf_totaleksposur)
    TextFieldBoxes tf_totaleksposur;
    @BindView(R.id.et_totaleksposur)
    EditText et_totaleksposur;


    @BindView(R.id.tf_margin)
    TextFieldBoxes tf_margin;
    @BindView(R.id.et_margin)
    EditText et_margin;
    @BindView(R.id.tf_jenisangsuran)
    TextFieldBoxes tf_jenisangsuran;
    @BindView(R.id.et_jenisangsuran)
    EditText et_jenisangsuran;
    @BindView(R.id.tf_intervaljenisangsuran)
    TextFieldBoxes tf_intervaljenisangsuran;
    @BindView(R.id.et_intervaljenisangsuran)
    EditText et_intervaljenisangsuran;
    @BindView(R.id.ll_btn_cekrekomendasi)
    LinearLayout ll_btn_cekrekomendasi;
    @BindView(R.id.btn_cekrekomendasi)
    Button btn_cekrekomendasi;
    @BindView(R.id.ll_hasilrekomendasipembiayaan)
    LinearLayout ll_hasilrekomendasipembiayaan;
    @BindView(R.id.tv_hasilrekomendasi)
    TextView tv_hasilrekomendasi;
    @BindView(R.id.tv_rekomendasiangsuran)
    TextView tv_rekomendasiangsuran;
    @BindView(R.id.tv_disposableincome)
    TextView tv_disposableincome;
    @BindView(R.id.tv_idir)
    TextView tv_idir;
    @BindView(R.id.progressbar_loading)
    RelativeLayout loading;
    @BindView(R.id.sv_rekomendasipembiayaan)
    ScrollView sv_rekomendasipembiayaan;

    private Realm realm;
    private DataLkn data;

    public static String val_modalKerja ="";
    public static String val_investasi ="";
    public static String val_konsumtif ="";
    public static String val_takeover ="";
    public static String val_jwModalKerja ="";
    public static String val_jwInvestasi ="";
    public static String val_jwKonsumtif ="";
    public static String val_jwTakeover ="";
    public static String val_qardh ="";
    public static String val_totalRekomendasiKomite ="";
    public static String val_angsuranSaatIni ="";
    public static String val_totalEksposur ="";
    public static String val_margin ="";
    public static String val_jenisAngsuran ="";
    public static String val_intervalJenisAngsuran ="";
    public static String val_rekomendasiAngsuran ="";
    public static String val_disposableIncome ="";
    public static String val_idir ="";
    public static String val_marginModalKerja ="";
    public static String val_marginInvestasi ="";
    public static String val_marginKonsumtif ="";
    public static String val_marginTakeover ="";

    private String dataCekRekomendasiString;
    private CekRekomendasi dataCekRekomendasi;

    public static boolean flagCekRekomendasi = false;

    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;
    boolean isKur=false;

    public FragmentRekomendasiPembiayaan() {
    }

    public FragmentRekomendasiPembiayaan(DataLkn mdata) {
        data = mdata;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ao_fragment_rekomendasipembiayaan, container, false);
        ButterKnife.bind(this, view);
        realm = Realm.getDefaultInstance();
        apiClientAdapter = new ApiClientAdapter(getContext());
        appPreferences = new AppPreferences(getContext());
        disableTextfield();
        onChangeText();
        validateInputTujuanPenggunaan();
        btn_cekrekomendasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateFormCekRekomendasi())
                    cekRekomendasi();
            }
        });
        tv_produk.setText(LknActivity.namaProduct);
        tv_tujuanpembiayaan.setText(LknActivity.tujuanPembiayaan);
        tv_plafondinduk.setText(AppUtil.parseRupiah(Integer.toString(LknActivity.plafond)));
        tv_tenor.setText(Integer.toString(LknActivity.jw)+" Bulan");
        et_jenisangsuran.setText("Reguler");
        et_intervaljenisangsuran.setText("");

        setData();

        if (LknActivity.kodeProduct.equalsIgnoreCase("127") || LknActivity.kodeProduct.equalsIgnoreCase("128") || LknActivity.kodeProduct.equalsIgnoreCase("840")|| LknActivity.kodeProduct.equalsIgnoreCase("318")|| LknActivity.kodeProduct.equalsIgnoreCase("319")|| LknActivity.kodeProduct.equalsIgnoreCase("320")|| LknActivity.kodeProduct.equalsIgnoreCase("841")){
          isKur=true;
        }

        if (isKur){
            et_margin.setText(data.getmARGINFLATBULAN());
            et_margin.setEnabled(false);
        }

        return view;
    }



    private void setData(){
        try {
            if (data.getiDLKN2() != null) {
                et_nilaimodalkerja.setText((!data.getpEMBIAYAANPRODUKTIF1().equalsIgnoreCase("") ? data.getpEMBIAYAANPRODUKTIF1() : "0" ));
                et_nilaiinvestasi.setText((!data.getpEMBIAYAANPRODUKTIF2().equalsIgnoreCase("") ? data.getpEMBIAYAANPRODUKTIF2() : "0" ));
                et_nilaikonsumtif.setText((!data.getpEMBIAYAANKONSUMTIF().equalsIgnoreCase("") ? data.getpEMBIAYAANKONSUMTIF() : "0"));
                et_nilaitakeover.setText((!data.getpEMBIAYAANTAKEOVER().equalsIgnoreCase("") ? data.getpEMBIAYAANTAKEOVER() : "0"));
                et_waktumodalkerja.setText((!data.getjWPRODUKTIF1().equalsIgnoreCase("") ? data.getjWPRODUKTIF1() : "0"));
                et_waktuinvestasi.setText((!data.getjWPRODUKTIF2().equalsIgnoreCase("") ? data.getjWPRODUKTIF2() : "0"));
                et_waktukonsumtif.setText((!data.getjWKONSUMTIF().equalsIgnoreCase("") ? data.getjWKONSUMTIF() : "0"));
                et_waktutakeover.setText((!data.getjWTAKEOVER().equalsIgnoreCase("") ? data.getjWTAKEOVER() : "0"));
                et_waktuqardh.setText((!data.getjATUHTEMPOQARDH().equalsIgnoreCase("") ? data.getjATUHTEMPOQARDH() : "0"));
                et_totalrekomendasikomite.setText(data.gettOTALPEMBIAYAAN());
                et_margin.setText(data.getmARGINFLATBULAN());
            }
            et_angsuranpinjamansaatini.setText(String.valueOf(data.getaNGSURANALL()));
            et_totaleksposur.setText(String.valueOf(data.gettOTALEKSPOSUREBRIS()));

            flagCekRekomendasi = false;
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void disableTextfield(){
        et_totalrekomendasikomite.setInputType(InputType.TYPE_NULL);
        et_totalrekomendasikomite.setFocusable(false);
        et_angsuranpinjamansaatini.setInputType(InputType.TYPE_NULL);
        et_angsuranpinjamansaatini.setFocusable(false);
        et_totaleksposur.setInputType(InputType.TYPE_NULL);
        et_totaleksposur.setFocusable(false);
        et_jenisangsuran.setInputType(InputType.TYPE_NULL);
        et_jenisangsuran.setFocusable(false);
        et_intervaljenisangsuran.setInputType(InputType.TYPE_NULL);
        et_intervaljenisangsuran.setFocusable(false);
    }

   private void onChangeText(){
       et_nilaimodalkerja.addTextChangedListener(this);
       et_nilaimodalkerja.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_nilaimodalkerja));
       et_nilaiinvestasi.addTextChangedListener(this);
       et_nilaiinvestasi.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_nilaiinvestasi));
       et_nilaikonsumtif.addTextChangedListener(this);
       et_nilaikonsumtif.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_nilaikonsumtif));
       et_nilaitakeover.addTextChangedListener(this);
       et_nilaitakeover.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_nilaitakeover));

       et_totalrekomendasikomite.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_totalrekomendasikomite));
       et_angsuranpinjamansaatini.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_angsuranpinjamansaatini));
       et_totaleksposur.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_totaleksposur));
   }

   private void validateInputTujuanPenggunaan(){
       et_nilaimodalkerja.setEnabled(false);
       et_nilaimodalkerja.setText("0");
       et_waktumodalkerja.setEnabled(false);
       et_waktumodalkerja.setText("0");
       et_nilaiinvestasi.setEnabled(false);
       et_nilaiinvestasi.setText("0");
       et_waktuinvestasi.setEnabled(false);
       et_waktuinvestasi.setText("0");
       et_nilaikonsumtif.setEnabled(false);
       et_nilaikonsumtif.setText("0");
       et_waktukonsumtif.setEnabled(false);
       et_waktukonsumtif.setText("0");
       et_nilaitakeover.setEnabled(false);
       et_nilaitakeover.setText("0");
       et_waktutakeover.setEnabled(false);
       et_waktutakeover.setText("0");
       et_waktuqardh.setEnabled(false);
       et_waktuqardh.setText("0");
       switch (LknActivity.idTujuanPembiayaan){
           case 40 :
               et_nilaimodalkerja.setEnabled(true);
               et_nilaimodalkerja.getText().clear();
               et_waktumodalkerja.setEnabled(true);
               et_waktumodalkerja.getText().clear();
               break;
           case 41 :
               et_nilaiinvestasi.setEnabled(true);
               et_nilaiinvestasi.getText().clear();
               et_waktuinvestasi.setEnabled(true);
               et_waktuinvestasi.getText().clear();
               break;
           case 45 :
               et_nilaikonsumtif.setEnabled(true);
               et_nilaikonsumtif.getText().clear();
               et_waktukonsumtif.setEnabled(true);
               et_waktukonsumtif.getText().clear();
               break;
           case 46 :
               et_nilaimodalkerja.setEnabled(true);
               et_nilaimodalkerja.getText().clear();
               et_waktumodalkerja.setEnabled(true);
               et_waktumodalkerja.getText().clear();
               et_nilaiinvestasi.setEnabled(true);
               et_nilaiinvestasi.getText().clear();
               et_waktuinvestasi.setEnabled(true);
               et_waktuinvestasi.getText().clear();
               break;
           case 47 :
               et_nilaiinvestasi.setEnabled(true);
               et_nilaiinvestasi.getText().clear();
               et_waktuinvestasi.setEnabled(true);
               et_waktuinvestasi.getText().clear();
               et_nilaikonsumtif.setEnabled(true);
               et_nilaikonsumtif.getText().clear();
               et_waktukonsumtif.setEnabled(true);
               et_waktukonsumtif.getText().clear();
               break;
           case 48 :
               et_nilaimodalkerja.setEnabled(true);
               et_nilaimodalkerja.getText().clear();
               et_waktumodalkerja.setEnabled(true);
               et_waktumodalkerja.getText().clear();
               et_nilaikonsumtif.setEnabled(true);
               et_nilaikonsumtif.getText().clear();
               et_waktukonsumtif.setEnabled(true);
               et_waktukonsumtif.getText().clear();
               break;
           case 49 :
               et_nilaikonsumtif.setEnabled(true);
               et_nilaikonsumtif.getText().clear();
               et_waktukonsumtif.setEnabled(true);
               et_waktukonsumtif.getText().clear();
               et_nilaiinvestasi.setEnabled(true);
               et_nilaiinvestasi.getText().clear();
               et_waktuinvestasi.setEnabled(true);
               et_waktuinvestasi.getText().clear();
               et_nilaimodalkerja.setEnabled(true);
               et_nilaimodalkerja.getText().clear();
               et_waktumodalkerja.setEnabled(true);
               et_waktumodalkerja.getText().clear();
               break;
           case 50 :
               et_nilaitakeover.setEnabled(true);
               et_nilaitakeover.getText().clear();
               et_waktutakeover.setEnabled(true);
               et_waktutakeover.getText().clear();
//               et_waktuqardh.setEnabled(true);
               et_waktuqardh.setText("14");
               break;
           case 51 :
               et_nilaimodalkerja.setEnabled(true);
               et_nilaimodalkerja.getText().clear();
               et_waktumodalkerja.setEnabled(true);
               et_waktumodalkerja.getText().clear();
               et_nilaitakeover.setEnabled(true);
               et_nilaitakeover.getText().clear();
               et_waktutakeover.setEnabled(true);
               et_waktutakeover.getText().clear();
//               et_waktuqardh.setEnabled(true);
               et_waktuqardh.setText("14");
               break;
           case 52 :
               et_nilaiinvestasi.setEnabled(true);
               et_nilaiinvestasi.getText().clear();
               et_waktuinvestasi.setEnabled(true);
               et_waktuinvestasi.getText().clear();
               et_nilaitakeover.setEnabled(true);
               et_nilaitakeover.getText().clear();
               et_waktutakeover.setEnabled(true);
               et_waktutakeover.getText().clear();
//               et_waktuqardh.setEnabled(true);
               et_waktuqardh.setText("14");
               break;
           case 53 :
               et_nilaikonsumtif.setEnabled(true);
               et_nilaikonsumtif.getText().clear();
               et_waktukonsumtif.setEnabled(true);
               et_waktukonsumtif.getText().clear();
               et_nilaitakeover.setEnabled(true);
               et_nilaitakeover.getText().clear();
               et_waktutakeover.setEnabled(true);
               et_waktutakeover.getText().clear();
//               et_waktuqardh.setEnabled(true);
               et_waktuqardh.setText("14");
               break;
           case 54 :
               et_nilaitakeover.setEnabled(true);
               et_nilaitakeover.getText().clear();
               et_waktutakeover.setEnabled(true);
               et_waktutakeover.getText().clear();
//               et_waktuqardh.setEnabled(true);
               et_waktuqardh.setText("14");
               et_nilaiinvestasi.setEnabled(true);
               et_nilaiinvestasi.getText().clear();
               et_waktuinvestasi.setEnabled(true);
               et_waktuinvestasi.getText().clear();
               et_nilaimodalkerja.setEnabled(true);
               et_nilaimodalkerja.getText().clear();
               et_waktumodalkerja.setEnabled(true);
               et_waktumodalkerja.getText().clear();
               break;
           case 55 :
               et_nilaitakeover.setEnabled(true);
               et_nilaitakeover.getText().clear();
               et_waktutakeover.setEnabled(true);
               et_waktutakeover.getText().clear();
//               et_waktuqardh.setEnabled(true);
               et_waktuqardh.setText("14");
               et_nilaiinvestasi.setEnabled(true);
               et_nilaiinvestasi.getText().clear();
               et_waktuinvestasi.setEnabled(true);
               et_waktuinvestasi.getText().clear();
               et_nilaikonsumtif.setEnabled(true);
               et_nilaikonsumtif.getText().clear();
               et_waktukonsumtif.setEnabled(true);
               et_waktukonsumtif.getText().clear();
               break;
           case 56 :
               et_nilaitakeover.setEnabled(true);
               et_nilaitakeover.getText().clear();
               et_waktutakeover.setEnabled(true);
               et_waktutakeover.getText().clear();
//               et_waktuqardh.setEnabled(true);
               et_waktuqardh.setText("14");
               et_nilaimodalkerja.setEnabled(true);
               et_nilaimodalkerja.getText().clear();
               et_waktumodalkerja.setEnabled(true);
               et_waktumodalkerja.getText().clear();
               et_nilaikonsumtif.setEnabled(true);
               et_nilaikonsumtif.getText().clear();
               et_waktukonsumtif.setEnabled(true);
               et_waktukonsumtif.getText().clear();
               break;
           case 57 :
               et_nilaitakeover.setEnabled(true);
               et_nilaitakeover.getText().clear();
               et_waktutakeover.setEnabled(true);
               et_waktutakeover.getText().clear();
//               et_waktuqardh.setEnabled(true);
               et_waktuqardh.setText("14");
               et_nilaiinvestasi.setEnabled(true);
               et_nilaiinvestasi.getText().clear();
               et_waktuinvestasi.setEnabled(true);
               et_waktuinvestasi.getText().clear();
               et_nilaimodalkerja.setEnabled(true);
               et_nilaimodalkerja.getText().clear();
               et_waktumodalkerja.setEnabled(true);
               et_waktumodalkerja.getText().clear();
               et_nilaikonsumtif.setEnabled(true);
               et_nilaikonsumtif.getText().clear();
               et_waktukonsumtif.setEnabled(true);
               et_waktukonsumtif.getText().clear();
               break;
       }
   }

   private boolean validateTotalPlafond(){
       try {
           BigDecimal totalPlafondPengajuan = new BigDecimal(String.valueOf(LknActivity.plafond));

           String modalkerja = (!et_nilaimodalkerja.getText().toString().trim().isEmpty() || !et_nilaimodalkerja.getText().toString().trim().equalsIgnoreCase("")) ? et_nilaimodalkerja.getText().toString().trim() : "0";
           String investasi = (!et_nilaiinvestasi.getText().toString().trim().isEmpty() || !et_nilaiinvestasi.getText().toString().trim().equalsIgnoreCase("")) ? et_nilaiinvestasi.getText().toString().trim() : "0";
           String konsumtif = (!et_nilaikonsumtif.getText().toString().trim().isEmpty() || !et_nilaikonsumtif.getText().toString().trim().equalsIgnoreCase("")) ? et_nilaikonsumtif.getText().toString().trim() : "0";
           String takeover = (!et_nilaitakeover.getText().toString().trim().isEmpty() || !et_nilaitakeover.getText().toString().trim().equalsIgnoreCase("")) ? et_nilaitakeover.getText().toString().trim() : "0";

           BigDecimal totalPlafondInput = new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(modalkerja))
                   .add(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(investasi)))
                   .add(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(konsumtif)))
                   .add(new BigDecimal(NumberTextWatcherCanNolForThousand.trimCommaOfString(takeover)));
           if (totalPlafondPengajuan.compareTo(totalPlafondInput) == 0){
               return true;
           }
           else{
               return false;
           }
       }
       catch (Exception e){
           AppUtil.showToastShort(getContext(), e.getMessage());
           return false;
       }
   }


    private void cekRekomendasi() {
       loading.setVisibility(View.VISIBLE);
       try {
           String cif = LknActivity.cif;
           int idAplikasi = LknActivity.idAplikasi;
           Double marginFlat = Double.parseDouble(et_margin.getText().toString());
           int rekomendasiNilaiPbyModalKerja = (!et_nilaimodalkerja.getText().toString().trim().isEmpty() || !et_nilaimodalkerja.getText().toString().trim().equalsIgnoreCase("")) ? Integer.parseInt(NumberTextWatcherForThousand.trimCommaOfString(et_nilaimodalkerja.getText().toString())) : 0;
           int rekomendasiNilaiPbyInvestasi = (!et_nilaiinvestasi.getText().toString().trim().isEmpty() || !et_nilaiinvestasi.getText().toString().trim().equalsIgnoreCase("")) ? Integer.parseInt(NumberTextWatcherForThousand.trimCommaOfString(et_nilaiinvestasi.getText().toString())) : 0;
           int rekomendasiNilaiPbyKonsumtif = (!et_nilaikonsumtif.getText().toString().trim().isEmpty() || !et_nilaikonsumtif.getText().toString().trim().equalsIgnoreCase("")) ? Integer.parseInt(NumberTextWatcherForThousand.trimCommaOfString(et_nilaikonsumtif.getText().toString())) : 0;
           int rekomendasiNilaiPbyTakeover = (!et_nilaitakeover.getText().toString().trim().isEmpty() || !et_nilaitakeover.getText().toString().trim().equalsIgnoreCase("")) ? Integer.parseInt(NumberTextWatcherForThousand.trimCommaOfString(et_nilaitakeover.getText().toString())) : 0;
           int jangkaWaktuPbyModalKerja = (!et_waktumodalkerja.getText().toString().trim().isEmpty() || !et_waktumodalkerja.getText().toString().trim().equalsIgnoreCase("")) ? Integer.parseInt(NumberTextWatcherForThousand.trimCommaOfString(et_waktumodalkerja.getText().toString())) : 0;
           int jangkaWaktuPbyInvestasi = (!et_waktuinvestasi.getText().toString().trim().isEmpty() || !et_waktuinvestasi.getText().toString().trim().equalsIgnoreCase("")) ? Integer.parseInt(NumberTextWatcherForThousand.trimCommaOfString(et_waktuinvestasi.getText().toString())) : 0;
           int jangkaWaktuPbyKonsumtif = (!et_waktukonsumtif.getText().toString().trim().isEmpty() || !et_waktukonsumtif.getText().toString().trim().equalsIgnoreCase("")) ? Integer.parseInt(NumberTextWatcherForThousand.trimCommaOfString(et_waktukonsumtif.getText().toString())) : 0;
           int jangkaWaktuPbyTakeover = (!et_waktutakeover.getText().toString().trim().isEmpty() || !et_waktutakeover.getText().toString().trim().equalsIgnoreCase("")) ? Integer.parseInt(NumberTextWatcherForThousand.trimCommaOfString(et_waktutakeover.getText().toString())) : 0;
           long sisaPenghasilan = Long.parseLong(FragmentAnalisaKeuangan.val_sisaPenghasilan);
           int angsuranPinjaman = (!et_angsuranpinjamansaatini.getText().toString().trim().isEmpty() || !et_angsuranpinjamansaatini.getText().toString().trim().equalsIgnoreCase("")) ? Integer.parseInt(NumberTextWatcherForThousand.trimCommaOfString(et_angsuranpinjamansaatini.getText().toString())) : 0;
           int totalRekomendasiKomite = (!et_totalrekomendasikomite.getText().toString().trim().isEmpty() || !et_totalrekomendasikomite.getText().toString().trim().equalsIgnoreCase("")) ? Integer.parseInt(NumberTextWatcherForThousand.trimCommaOfString(et_totalrekomendasikomite.getText().toString())) : 0;
           int totalExposure = (!et_totaleksposur.getText().toString().trim().isEmpty() || !et_totaleksposur.getText().toString().trim().equalsIgnoreCase("")) ? Integer.parseInt(NumberTextWatcherForThousand.trimCommaOfString(et_totaleksposur.getText().toString())) : 0;


           cekRekomendasi req = new cekRekomendasi(cif, appPreferences.getUid(), idAplikasi, marginFlat, rekomendasiNilaiPbyModalKerja, rekomendasiNilaiPbyInvestasi, rekomendasiNilaiPbyKonsumtif, rekomendasiNilaiPbyTakeover, jangkaWaktuPbyModalKerja, jangkaWaktuPbyInvestasi, jangkaWaktuPbyKonsumtif, jangkaWaktuPbyTakeover, sisaPenghasilan, angsuranPinjaman, totalRekomendasiKomite, totalExposure); //101678
           Call<ParseResponse> call = apiClientAdapter.getApiInterface().cekRekomendasi(req);
           call.enqueue(new Callback<ParseResponse>() {
               @Override
               public void onResponse(Call<ParseResponse> call, Response<ParseResponse> response) {
                   loading.setVisibility(View.GONE);
                   try {
                       if (response.isSuccessful()) {
                           if (response.body().getStatus().equalsIgnoreCase("00")) {
//                               ll_hasilrekomendasipembiayaan.setVisibility(View.VISIBLE);
                               AppUtil.notifsuccess(getContext(), getActivity().findViewById(android.R.id.content), "Cek Rekomendasi Berhasil");
                               Gson gson = new Gson();
                               dataCekRekomendasiString = response.body().getData().toString();
                               dataCekRekomendasi = gson.fromJson(dataCekRekomendasiString, CekRekomendasi.class);
                               flagCekRekomendasi = true;

                               tv_hasilrekomendasi.setText(dataCekRekomendasi.getValidasiLKN());
                               tv_rekomendasiangsuran.setText("Rp. "+NumberTextWatcherForThousand.getDecimalFormat(String.valueOf(dataCekRekomendasi.getRekomendasiAngsuran())));
                               tv_disposableincome.setText("Rp. "+NumberTextWatcherForThousand.getDecimalFormat(String.valueOf(dataCekRekomendasi.getDisposableIncome())));
                               tv_idir.setText(NumberTextWatcherForThousand.getDecimalFormat(String.valueOf(dataCekRekomendasi.getiDIR()))+" %");

                               sv_rekomendasipembiayaan.post(new Runnable() {
                                   @Override
                                   public void run() {
                                       sv_rekomendasipembiayaan.fullScroll(ScrollView.FOCUS_DOWN);
                                   }
                               });

                           } else {
                               AppUtil.notiferror(getContext(), getActivity().findViewById(android.R.id.content), response.body().getMessage());
                           }
                       } else {
                           Error error = ParseResponseError.confirmEror(response.errorBody());
                           AppUtil.notiferror(getContext(), getActivity().findViewById(android.R.id.content), error.getMessage());
                       }
                   } catch (Exception e) {
                       e.printStackTrace();
                   }
               }

               @Override
               public void onFailure(Call<ParseResponse> call, Throwable t) {
                   loading.setVisibility(View.GONE);
                   AppUtil.notiferror(getContext(), getActivity().findViewById(android.R.id.content), getString(R.string.txt_connection_failure));
               }
           });
       }
       catch (Exception e){
           loading.setVisibility(View.GONE);
           e.printStackTrace();
       }
    }

    private boolean validateFormCekRekomendasi(){
        if (et_margin.getText().toString().isEmpty() || et_margin.getText().toString().equalsIgnoreCase("")){
            tf_margin.setError("Format "+ tf_margin.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return false;
        }
        return true;
    }

    private VerificationError validateForm(){
        if (et_totalrekomendasikomite.getText().toString().isEmpty() || et_totalrekomendasikomite.getText().toString().equalsIgnoreCase("")){
            tf_totalrekomendasikomite.setError("Format "+ tf_totalrekomendasikomite.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_totalrekomendasikomite.getLabelText()+" "+getString(R.string.title_validate_field));
        }
        else if (et_angsuranpinjamansaatini.getText().toString().isEmpty() || et_angsuranpinjamansaatini.getText().toString().equalsIgnoreCase("")){
            tf_angsuranpinjamansaatini.setError("Format "+ tf_angsuranpinjamansaatini.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_angsuranpinjamansaatini.getLabelText()+" "+getString(R.string.title_validate_field));
        }
        else if (et_totaleksposur.getText().toString().isEmpty() || et_totaleksposur.getText().toString().equalsIgnoreCase("")){
            tf_totaleksposur.setError("Format "+ tf_totaleksposur.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_totaleksposur.getLabelText()+" "+getString(R.string.title_validate_field));
        }
        else if (et_margin.getText().toString().isEmpty() || et_margin.getText().toString().equalsIgnoreCase("")){
            tf_margin.setError("Format "+ tf_margin.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_margin.getLabelText()+" "+getString(R.string.title_validate_field));
        }
        else if (flagCekRekomendasi == false){
            return new VerificationError("Silahkan Cek Rekomendasi Terlebih dahulu");
        }
        else if (validateTotalPlafond() == false){
            return new VerificationError("Total Plafond yang diinput tidak sama dengan Plafond Pengajuan");
        }
        else {
            setDataOnListerner();
            return null;
        }
    }

    public void setDataOnListerner(){
        try {
            val_modalKerja = (!et_nilaimodalkerja.getText().toString().isEmpty() || !et_nilaimodalkerja.getText().toString().equalsIgnoreCase("")) ? NumberTextWatcherForThousand.trimCommaOfString(et_nilaimodalkerja.getText().toString().trim()) : "0";
            val_investasi = (!et_nilaiinvestasi.getText().toString().isEmpty() || !et_nilaiinvestasi.getText().toString().equalsIgnoreCase("")) ? NumberTextWatcherForThousand.trimCommaOfString(et_nilaiinvestasi.getText().toString().trim()) : "0";
            val_konsumtif = (!et_nilaikonsumtif.getText().toString().isEmpty() || !et_nilaikonsumtif.getText().toString().equalsIgnoreCase("")) ? NumberTextWatcherForThousand.trimCommaOfString(et_nilaikonsumtif.getText().toString().trim()) : "0";
            val_takeover = (!et_nilaitakeover.getText().toString().isEmpty() || !et_nilaitakeover.getText().toString().equalsIgnoreCase("")) ? NumberTextWatcherForThousand.trimCommaOfString(et_nilaitakeover.getText().toString().trim()) : "0";
            val_jwModalKerja = (!et_waktumodalkerja.getText().toString().isEmpty() || !et_waktumodalkerja.getText().toString().equalsIgnoreCase("")) ? NumberTextWatcherForThousand.trimCommaOfString(et_waktumodalkerja.getText().toString().trim()) : "0";
            val_jwInvestasi = (!et_waktuinvestasi.getText().toString().isEmpty() || !et_waktuinvestasi.getText().toString().equalsIgnoreCase("")) ? NumberTextWatcherForThousand.trimCommaOfString(et_waktuinvestasi.getText().toString().trim()) : "0";
            val_jwKonsumtif = (!et_waktukonsumtif.getText().toString().isEmpty() || !et_waktukonsumtif.getText().toString().equalsIgnoreCase("")) ? NumberTextWatcherForThousand.trimCommaOfString(et_waktukonsumtif.getText().toString().trim()) : "0";
            val_jwTakeover = (!et_waktutakeover.getText().toString().isEmpty() || !et_waktutakeover.getText().toString().equalsIgnoreCase("")) ? NumberTextWatcherForThousand.trimCommaOfString(et_waktutakeover.getText().toString().trim()) : "0";
            val_qardh = (!et_waktuqardh.getText().toString().isEmpty() || !et_waktuqardh.getText().toString().equalsIgnoreCase("")) ? NumberTextWatcherForThousand.trimCommaOfString(et_waktuqardh.getText().toString().trim()) : "0";
            val_totalRekomendasiKomite = (!et_totalrekomendasikomite.getText().toString().isEmpty() || !et_totalrekomendasikomite.getText().toString().equalsIgnoreCase("")) ? NumberTextWatcherForThousand.trimCommaOfString(et_totalrekomendasikomite.getText().toString().trim()) : "0";
            val_margin = (!et_margin.getText().toString().isEmpty() || !et_margin.getText().toString().equalsIgnoreCase("")) ? NumberTextWatcherForThousand.trimCommaOfString(et_margin.getText().toString().trim()) : "0";
            val_jenisAngsuran = "1";
            val_intervalJenisAngsuran = "0";
            val_disposableIncome = (!tv_disposableincome.getText().toString().isEmpty() || !tv_disposableincome.getText().toString().equalsIgnoreCase("")) ? NumberTextWatcherForThousand.trimCommaOfString(tv_disposableincome.getText().toString().trim()) : "0";
            val_idir = NumberTextWatcherForThousand.trimCommaOfString(tv_idir.getText().toString().trim());
            val_marginModalKerja = String.valueOf(dataCekRekomendasi.getMarginProduktif1());
            val_marginInvestasi = String.valueOf(dataCekRekomendasi.getMarginProduktif2());
            val_marginKonsumtif = String.valueOf(dataCekRekomendasi.getMarginKonsumtif());
            val_marginTakeover = String.valueOf(dataCekRekomendasi.getMarginTO());
            val_rekomendasiAngsuran = String.valueOf(dataCekRekomendasi.getRekomendasiAngsuran());


            final LknPojo d = new LknPojo();

            d.setpEMBIAYAANPRODUKTIF1(val_modalKerja);
            d.setpEMBIAYAANPRODUKTIF2(val_investasi);
            d.setpEMBIAYAANKONSUMTIF(val_konsumtif);
            d.setpEMBIAYAANTAKEOVER(val_takeover);
            d.setjWPRODUKTIF1(val_jwModalKerja);
            d.setjWPRODUKTIF2(val_jwInvestasi);
            d.setjWKONSUMTIF(val_jwKonsumtif);
            d.setjWTAKEOVER(val_jwTakeover);
            d.setjATUHTEMPOQARDH(val_qardh);
            d.settOTALPEMBIAYAAN(val_totalRekomendasiKomite);
            d.setmARGINFLATBULAN(val_margin);
            d.setjENISANGSURAN(val_jenisAngsuran);
            d.setiNTERVALIRREGULAR(val_intervalJenisAngsuran);
            d.setmARGINEFEKTIFPRODUKTIF1(val_marginModalKerja);
            d.setmARGINEFEKTIFPRODUKTIF2(val_marginInvestasi);
            d.setmARGINEFEKTIFKONSUMTIF(val_marginKonsumtif);
            d.setmARGINEFEKTIFTAKEOVER(val_marginTakeover);
            d.setrEKOMENDASIANGSURAN(val_rekomendasiAngsuran);
            d.setrEKOMENDASIPUTUSAN("1");
            d.setaNGSURANEXISTING(String.valueOf(data.getaNGSURANALL()));

            LknPojo dataR = realm.where(LknPojo.class).equalTo("uuid", LknActivity.UUIDR).findFirst();

            d.setiNVENTORY(dataR.getiNVENTORY());
            d.setpIUTANGDAGANG(dataR.getpIUTANGDAGANG());
            d.setuTANGDAGANG(dataR.getuTANGDAGANG());
            d.setwINERACA(dataR.getwINERACA());
            d.setdOHINVENTORY(dataR.getdOHINVENTORY());
            d.setdOHPIUTANG(dataR.getdOHPIUTANG());
            d.setdOHUTANG(dataR.getdOHUTANG());
            d.setpERPUTARANPERSEDIAAN(dataR.getpERPUTARANPERSEDIAAN());
            d.setpERPUTARANPIUTANG(dataR.getpERPUTARANPIUTANG());
            d.setpERPUTARANUTANG(dataR.getpERPUTARANUTANG());
            d.setkEBUTUHANMODAL(dataR.getkEBUTUHANMODAL());
            d.setwINORMAL(dataR.getwINORMAL());
            d.setpENDAPATANUSAHA(dataR.getpENDAPATANUSAHA());
            d.sethARGAPOKOKPENJUALAN(dataR.gethARGAPOKOKPENJUALAN());
            d.sethARGASEWA(dataR.gethARGASEWA());
            d.setgAJIPEGAWAI(dataR.getgAJIPEGAWAI());
            d.setbIAYATELEPONLISTRIK(dataR.getbIAYATELEPONLISTRIK());
            d.setbIAYATRANSPORTASI(dataR.getbIAYATRANSPORTASI());
            d.setpENGELUARANLAINNYA(dataR.getpENGELUARANLAINNYA());
            d.setpENGELUARANUSAHA(dataR.getpENGELUARANUSAHA());
            d.setkEUNTUNGANUSAHA(dataR.getkEUNTUNGANUSAHA());
            d.setpENGHASILANLAINNYA(dataR.getpENGHASILANLAINNYA());
            d.settOTALPENGHASILAN(dataR.gettOTALPENGHASILAN());
            d.setpAJAK(dataR.getpAJAK());
            d.setbELANJART(dataR.getbELANJART());
            d.setbIAYASEWARUMAHRT(dataR.getbIAYASEWARUMAHRT());
            d.setbIAYAPENDIDIKAN(dataR.getbIAYAPENDIDIKAN());
            d.setbIAYATELEPONRT(dataR.getbIAYATELEPONRT());
            d.setbIAYATRANSPORTASIRT(dataR.getbIAYATRANSPORTASIRT());
            d.setpENGELUARANLAINNYART(dataR.getpENGELUARANLAINNYART());
            d.settOTALPENGELUARANRT(dataR.gettOTALPENGELUARANRT());
            d.setsISAPENGHASILAN(dataR.getsISAPENGHASILAN());
            d.setgPM(dataR.getgPM());
            d.setUuid(dataR.getUuid());
            d.setfIDAPLIKASI(dataR.getfIDAPLIKASI());
            d.setfIDCIFLAS(dataR.getfIDCIFLAS());
            d.settANGGALPENILAIAN(dataR.gettANGGALPENILAIAN());
            d.setsTATUSPERMOHONAN(dataR.getsTATUSPERMOHONAN());
            d.setnAMAORANGDITEMUI(dataR.getnAMAORANGDITEMUI());
            d.sethUBUNGAN(dataR.gethUBUNGAN());
            d.setbIDANGUSAHA(dataR.getbIDANGUSAHA());
            d.setnAMAUSAHA(dataR.getnAMAUSAHA());
            d.setlAMAUSAHA(dataR.getlAMAUSAHA());
            d.settELEPON(dataR.gettELEPON());
            d.setaLAMATUSAHA(dataR.getaLAMATUSAHA());
            d.setlOKASIUSAHA(dataR.getlOKASIUSAHA());
            d.setsTATUSTEMPATUSAHA(dataR.getsTATUSTEMPATUSAHA());
            d.setjENISTEMPATUSAHA(dataR.getjENISTEMPATUSAHA());
            d.setaSPEKPEMASARAN(dataR.getaSPEKPEMASARAN());
            d.setjENISUSAHA(dataR.getjENISUSAHA());
            d.setjARAKLOKASI(dataR.getjARAKLOKASI());
            d.setfIDPHOTODEPAN(dataR.getfIDPHOTODEPAN());
            d.setfIDPHOTODALAM(dataR.getfIDPHOTODALAM());
            d.setfIDPHOTOLINGKUNGAN(dataR.getfIDPHOTOLINGKUNGAN());

            realm.executeTransaction (new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.insertOrUpdate(d);
                }
            });
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public VerificationError verifyStep() {
        return  validateForm();
    }

    @Override
    public void onSelected() {
        flagCekRekomendasi = false;
    }

    @Override
    public void onError(@NonNull VerificationError verificationError) {
        AppUtil.notiferror(getContext(), getActivity().findViewById(android.R.id.content), verificationError.getErrorMessage());
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        try {
             /* ################# USAHA ################### */

            BigDecimal totalRekomendasiKomite = new BigDecimal(0);
            if (et_nilaimodalkerja.getText().toString().trim().length() > 0 || !et_nilaimodalkerja.getText().toString().isEmpty()){
                totalRekomendasiKomite = totalRekomendasiKomite.add(new BigDecimal(NumberTextWatcherForThousand.trimCommaOfString(et_nilaimodalkerja.getText().toString().trim())));
            }

            if (et_nilaiinvestasi.getText().toString().trim().length() > 0 || !et_nilaiinvestasi.getText().toString().isEmpty()){
                totalRekomendasiKomite = totalRekomendasiKomite.add(new BigDecimal(NumberTextWatcherForThousand.trimCommaOfString(et_nilaiinvestasi.getText().toString().trim())));
            }

            if (et_nilaikonsumtif.getText().toString().trim().length() > 0 || !et_nilaikonsumtif.getText().toString().isEmpty()){
                totalRekomendasiKomite = totalRekomendasiKomite.add(new BigDecimal(NumberTextWatcherForThousand.trimCommaOfString(et_nilaikonsumtif.getText().toString().trim())));
            }

            if (et_nilaitakeover.getText().toString().trim().length() > 0 || !et_nilaitakeover.getText().toString().isEmpty()){
                totalRekomendasiKomite = totalRekomendasiKomite.add(new BigDecimal(NumberTextWatcherForThousand.trimCommaOfString(et_nilaitakeover.getText().toString().trim())));
            }

            et_totalrekomendasikomite.setText(NumberTextWatcherCanNolForThousand.getDecimalFormat(String.valueOf(totalRekomendasiKomite))); //SET TOTAL REKOMENDASI KOMITE
        }
        catch (Exception e){
            e.printStackTrace();
            AppUtil.showToastShort(getContext(), e.getMessage());
        }


    }

    @Override
    public void afterTextChanged(Editable s) {
    }
}
