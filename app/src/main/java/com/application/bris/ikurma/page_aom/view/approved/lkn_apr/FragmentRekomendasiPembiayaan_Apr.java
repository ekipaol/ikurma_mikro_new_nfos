package com.application.bris.ikurma.page_aom.view.approved.lkn_apr;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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
import com.application.bris.ikurma.page_aom.model.CekRekomendasi;
import com.application.bris.ikurma.page_aom.model.DataLkn;
import com.application.bris.ikurma.util.AppUtil;
import com.application.bris.ikurma.util.NumberTextWatcherCanNolForThousand;
import com.application.bris.ikurma.util.NumberTextWatcherForThousand;
import com.google.gson.Gson;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

/**
 * Created by PID on 6/15/2019.
 */

@SuppressLint("ValidFragment")
public class FragmentRekomendasiPembiayaan_Apr extends Fragment implements Step{

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

    private DataLkn data;

    private String dataCekRekomendasiString;
    private CekRekomendasi dataCekRekomendasi;

    private ApiClientAdapter apiClientAdapter;
    private AppPreferences appPreferences;

    public FragmentRekomendasiPembiayaan_Apr() {
    }

    public FragmentRekomendasiPembiayaan_Apr(DataLkn mdata) {
        data = mdata;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ao_fragment_rekomendasipembiayaan, container, false);
        ButterKnife.bind(this, view);
        apiClientAdapter = new ApiClientAdapter(getContext());
        appPreferences = new AppPreferences(getContext());
        onChangeText();
        setDisable();
        setData();
        cekRekomendasi();
        tv_produk.setText(LknActivity_Apr.namaProduct);
        tv_tujuanpembiayaan.setText(LknActivity_Apr.tujuanPembiayaan);
        tv_plafondinduk.setText(AppUtil.parseRupiah(Integer.toString(LknActivity_Apr.plafond)));
        tv_tenor.setText(Integer.toString(LknActivity_Apr.jw)+" Bulan");
        et_jenisangsuran.setText("Reguler");
        et_intervaljenisangsuran.setText("");
        return view;
    }

    private void setDisable(){
        et_nilaimodalkerja.setEnabled(false);
        et_waktumodalkerja.setEnabled(false);
        et_nilaiinvestasi.setEnabled(false);
        et_waktuinvestasi.setEnabled(false);
        et_nilaikonsumtif.setEnabled(false);
        et_waktukonsumtif.setEnabled(false);
        et_nilaitakeover.setEnabled(false);
        et_waktutakeover.setEnabled(false);
        et_waktuqardh.setEnabled(false);
        et_totalrekomendasikomite.setEnabled(false);
        et_angsuranpinjamansaatini.setEnabled(false);
        et_totaleksposur.setEnabled(false);
        et_margin.setEnabled(false);
        et_jenisangsuran.setEnabled(false);
        et_intervaljenisangsuran.setEnabled(false);
        btn_cekrekomendasi.setVisibility(View.GONE);
    }

    private void setData(){
        try {
            if (data.getiDLKN2() != null) {
                et_nilaimodalkerja.setText(data.getpEMBIAYAANPRODUKTIF1());
                et_nilaiinvestasi.setText(data.getpEMBIAYAANPRODUKTIF2());
                et_nilaikonsumtif.setText(data.getpEMBIAYAANKONSUMTIF());
                et_nilaitakeover.setText(data.getpEMBIAYAANTAKEOVER());
                et_waktumodalkerja.setText(data.getjWPRODUKTIF1());
                et_waktuinvestasi.setText(data.getjWPRODUKTIF2());
                et_waktukonsumtif.setText(data.getjWKONSUMTIF());
                et_waktutakeover.setText(data.getjWTAKEOVER());
                et_waktuqardh.setText(data.getjATUHTEMPOQARDH());
                et_totalrekomendasikomite.setText(data.gettOTALPEMBIAYAAN());
                et_margin.setText(data.getmARGINFLATBULAN());
            }
            et_angsuranpinjamansaatini.setText(String.valueOf(data.getaNGSURANALL()));
            et_totaleksposur.setText(String.valueOf(data.gettOTALEKSPOSUREBRIS()));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

   private void onChangeText(){
       et_nilaimodalkerja.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_nilaimodalkerja));
       et_nilaiinvestasi.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_nilaiinvestasi));
       et_nilaikonsumtif.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_nilaikonsumtif));
       et_nilaitakeover.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_nilaitakeover));
       et_totalrekomendasikomite.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_totalrekomendasikomite));
       et_angsuranpinjamansaatini.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_angsuranpinjamansaatini));
       et_totaleksposur.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_totaleksposur));
   }

    private void cekRekomendasi() {
       loading.setVisibility(View.VISIBLE);
       try {
           String cif = LknActivity_Apr.cif;
           int idAplikasi = LknActivity_Apr.idAplikasi;
           Double marginFlat = Double.parseDouble(et_margin.getText().toString());
           int rekomendasiNilaiPbyModalKerja = (!et_nilaimodalkerja.getText().toString().trim().isEmpty() || !et_nilaimodalkerja.getText().toString().trim().equalsIgnoreCase("")) ? Integer.parseInt(NumberTextWatcherForThousand.trimCommaOfString(et_nilaimodalkerja.getText().toString())) : 0;
           int rekomendasiNilaiPbyInvestasi = (!et_nilaiinvestasi.getText().toString().trim().isEmpty() || !et_nilaiinvestasi.getText().toString().trim().equalsIgnoreCase("")) ? Integer.parseInt(NumberTextWatcherForThousand.trimCommaOfString(et_nilaiinvestasi.getText().toString())) : 0;
           int rekomendasiNilaiPbyKonsumtif = (!et_nilaikonsumtif.getText().toString().trim().isEmpty() || !et_nilaikonsumtif.getText().toString().trim().equalsIgnoreCase("")) ? Integer.parseInt(NumberTextWatcherForThousand.trimCommaOfString(et_nilaikonsumtif.getText().toString())) : 0;
           int rekomendasiNilaiPbyTakeover = (!et_nilaitakeover.getText().toString().trim().isEmpty() || !et_nilaitakeover.getText().toString().trim().equalsIgnoreCase("")) ? Integer.parseInt(NumberTextWatcherForThousand.trimCommaOfString(et_nilaitakeover.getText().toString())) : 0;
           int jangkaWaktuPbyModalKerja = (!et_waktumodalkerja.getText().toString().trim().isEmpty() || !et_waktumodalkerja.getText().toString().trim().equalsIgnoreCase("")) ? Integer.parseInt(NumberTextWatcherForThousand.trimCommaOfString(et_waktumodalkerja.getText().toString())) : 0;
           int jangkaWaktuPbyInvestasi = (!et_waktuinvestasi.getText().toString().trim().isEmpty() || !et_waktuinvestasi.getText().toString().trim().equalsIgnoreCase("")) ? Integer.parseInt(NumberTextWatcherForThousand.trimCommaOfString(et_waktuinvestasi.getText().toString())) : 0;
           int jangkaWaktuPbyKonsumtif = (!et_waktukonsumtif.getText().toString().trim().isEmpty() || !et_waktukonsumtif.getText().toString().trim().equalsIgnoreCase("")) ? Integer.parseInt(NumberTextWatcherForThousand.trimCommaOfString(et_waktukonsumtif.getText().toString())) : 0;
           int jangkaWaktuPbyTakeover = (!et_waktutakeover.getText().toString().trim().isEmpty() || !et_waktutakeover.getText().toString().trim().equalsIgnoreCase("")) ? Integer.parseInt(NumberTextWatcherForThousand.trimCommaOfString(et_waktutakeover.getText().toString())) : 0;
           long sisaPenghasilan = Long.parseLong(data.getsISAPENGHASILAN());
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
                               Gson gson = new Gson();
                               dataCekRekomendasiString = response.body().getData().toString();
                               dataCekRekomendasi = gson.fromJson(dataCekRekomendasiString, CekRekomendasi.class);

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


    @Nullable
    @Override
    public VerificationError verifyStep() {
        return  null;
    }

    @Override
    public void onSelected() {
    }

    @Override
    public void onError(@NonNull VerificationError verificationError) {

    }
}
