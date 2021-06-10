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
import android.widget.EditText;

import com.application.bris.ikurma.R;
import com.application.bris.ikurma.database.pojo.LknPojo;
import com.application.bris.ikurma.page_aom.model.DataLkn;
import com.application.bris.ikurma.util.AppUtil;
import com.application.bris.ikurma.util.NumberTextWatcherForThousand;
import com.application.bris.ikurma.util.NumberTextWatcherCanNolForThousand;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import java.math.BigDecimal;
import java.math.RoundingMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

/**
 * Created by PID on 6/15/2019.
 */

@SuppressLint("ValidFragment")
public class FragmentAnalisaKebutuhanModalKerja extends Fragment implements Step, TextWatcher{


    //STEP 1
    @BindView(R.id.tf_persediaaninventori1)
    TextFieldBoxes tf_persediaaninventori1;
    @BindView(R.id.et_persediaaninventori1)
    EditText et_persediaaninventori1;
    @BindView(R.id.tf_dohpersediaaninventori1)
    TextFieldBoxes tf_dohpersediaaninventori1;
    @BindView(R.id.et_dohpersediaaninventori1)
    EditText et_dohpersediaaninventori1;
    @BindView(R.id.tf_piutangdagang1)
    TextFieldBoxes tf_piutangdagang1;
    @BindView(R.id.et_piutangdagang1)
    EditText et_piutangdagang1;
    @BindView(R.id.tf_dohpiutangdagang1)
    TextFieldBoxes tf_dohpiutangdagang1;
    @BindView(R.id.et_dohpiutangdagang1)
    EditText et_dohpiutangdagang1;
    @BindView(R.id.tf_utangdagang1)
    TextFieldBoxes tf_utangdagang1;
    @BindView(R.id.et_utangdagang1)
    EditText et_utangdagang1;
    @BindView(R.id.tf_dohutangdagang1)
    TextFieldBoxes tf_dohutangdagang1;
    @BindView(R.id.et_dohutangdagang1)
    EditText et_dohutangdagang1;
    @BindView(R.id.tf_wineraca)
    TextFieldBoxes tf_wineraca;
    @BindView(R.id.et_wineraca)
    EditText et_wineraca;
    @BindView(R.id.tf_grossprofitmargin)
    TextFieldBoxes tf_grossprofitmargin;
    @BindView(R.id.et_grossprofitmargin)
    EditText et_grossprofitmargin;

    @BindView(R.id.tf_dohmodalkerja)
    TextFieldBoxes tf_dohmodalkerja;
    @BindView(R.id.et_dohmodalkerja)
    EditText et_dohmodalkerja;
    @BindView(R.id.tf_winormal)
    TextFieldBoxes tf_winormal;
    @BindView(R.id.et_winormal)
    EditText et_winormal;

    private Realm realm;
    private DataLkn data;

    public static String val_persediaanInventori1 ="";
    public static String val_piutangDagang1 ="";
    public static String val_utangDagang1 ="";
    public static String val_wiNeraca ="";
    public static String val_dohPersediaanInventori ="";
    public static String val_dohPiutangDagang ="";
    public static String val_dohUtangDagang ="";
    public static String val_grossProfitMargin ="";
    public static String val_persediaanInventori2 ="";
    public static String val_piutangDagang2 ="";
    public static String val_utangDagang2 ="";
    public static String val_dohKebutuhanModalKerja ="";
    public static String val_wiNormal ="";


    public FragmentAnalisaKebutuhanModalKerja() {
    }

    public FragmentAnalisaKebutuhanModalKerja(DataLkn mdata) {
        data = mdata;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ao_fragment_analisakebutuhanmodalkerja, container, false);
        ButterKnife.bind(this, view);
        realm = Realm.getDefaultInstance();
        disableTextfield();
        onChangeText();
        setData();

        return view;
    }

    private void setData(){
        try {
            if (data.getiDLKN2() != null) {
                et_persediaaninventori1.setText(data.getiNVENTORY());
                et_piutangdagang1.setText(data.getpIUTANGDAGANG());
                et_utangdagang1.setText(data.getuTANGDAGANG());
                et_wineraca.setText(data.getwINERACA());
                et_dohpersediaaninventori1.setText(String.valueOf(data.getdOHINVENTORY()));
                et_dohpiutangdagang1.setText(String.valueOf(data.getdOHPIUTANG()));
                et_dohutangdagang1.setText(String.valueOf(data.getdOHUTANG()));
                et_grossprofitmargin.setText(data.getgPM());
                et_dohmodalkerja.setText(String.valueOf(data.getkEBUTUHANMODAL()));
                et_winormal.setText(data.getwINORMAL());
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

   private void onChangeText(){
       et_persediaaninventori1.addTextChangedListener(this);
       et_persediaaninventori1.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_persediaaninventori1));
       et_piutangdagang1.addTextChangedListener(this);
       et_piutangdagang1.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_piutangdagang1));
       et_utangdagang1.addTextChangedListener(this);
       et_utangdagang1.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_utangdagang1));
       et_winormal.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_winormal));
       et_wineraca.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_wineraca));
   }

   private void disableTextfield(){
       et_wineraca.setInputType(InputType.TYPE_NULL);
       et_wineraca.setFocusable(false);
       et_dohpersediaaninventori1.setInputType(InputType.TYPE_NULL);
       et_dohpersediaaninventori1.setFocusable(false);
       et_dohpiutangdagang1.setInputType(InputType.TYPE_NULL);
       et_dohpiutangdagang1.setFocusable(false);
       et_dohutangdagang1.setInputType(InputType.TYPE_NULL);
       et_dohutangdagang1.setFocusable(false);
       et_grossprofitmargin.setInputType(InputType.TYPE_NULL);
       et_grossprofitmargin.setFocusable(false);
       et_dohmodalkerja.setInputType(InputType.TYPE_NULL);
       et_dohmodalkerja.setFocusable(false);
       et_winormal.setInputType(InputType.TYPE_NULL);
       et_winormal.setFocusable(false);
   }

    private VerificationError validateForm(){
        if (et_persediaaninventori1.getText().toString().isEmpty() || et_persediaaninventori1.getText().toString().equalsIgnoreCase("")){
            tf_persediaaninventori1.setError("Format "+ tf_persediaaninventori1.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_persediaaninventori1.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_piutangdagang1.getText().toString().isEmpty() || et_piutangdagang1.getText().toString().equalsIgnoreCase("")){
            tf_piutangdagang1.setError("Format "+ tf_piutangdagang1.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_piutangdagang1.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_utangdagang1.getText().toString().isEmpty() || et_utangdagang1.getText().toString().equalsIgnoreCase("")){
            tf_utangdagang1.setError("Format "+ tf_utangdagang1.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_utangdagang1.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_wineraca.getText().toString().isEmpty() || et_wineraca.getText().toString().equalsIgnoreCase("")){
            tf_wineraca.setError("Format "+ tf_wineraca.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_wineraca.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_dohpersediaaninventori1.getText().toString().isEmpty() || et_dohpersediaaninventori1.getText().toString().equalsIgnoreCase("")){
            tf_dohpersediaaninventori1.setError("Format "+ tf_dohpersediaaninventori1.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_dohpersediaaninventori1.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_dohpiutangdagang1.getText().toString().isEmpty() || et_dohpiutangdagang1.getText().toString().equalsIgnoreCase("")){
            tf_dohpiutangdagang1.setError("Format "+ tf_dohpiutangdagang1.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_dohpiutangdagang1.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_dohutangdagang1.getText().toString().isEmpty() || et_dohutangdagang1.getText().toString().equalsIgnoreCase("")){
            tf_dohutangdagang1.setError("Format "+ tf_dohutangdagang1.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_dohutangdagang1.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_grossprofitmargin.getText().toString().isEmpty() || et_grossprofitmargin.getText().toString().equalsIgnoreCase("")){
            tf_grossprofitmargin.setError("Format "+ tf_grossprofitmargin.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_grossprofitmargin.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_dohmodalkerja.getText().toString().isEmpty() || et_dohmodalkerja.getText().toString().equalsIgnoreCase("")){
            tf_dohmodalkerja.setError("Format "+ tf_dohmodalkerja.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_dohmodalkerja.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_winormal.getText().toString().isEmpty() || et_winormal.getText().toString().equalsIgnoreCase("")){
            tf_winormal.setError("Format "+ tf_winormal.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_winormal.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else {
            setDataOnListerner();
            return null;
        }
    }

    public void setDataOnListerner(){
        try {
            val_persediaanInventori1 = (!et_persediaaninventori1.getText().toString().isEmpty() || !et_persediaaninventori1.getText().toString().equalsIgnoreCase("")) ? NumberTextWatcherForThousand.trimCommaOfString(et_persediaaninventori1.getText().toString().trim()) : "0";
            val_piutangDagang1 = (!et_piutangdagang1.getText().toString().isEmpty() || !et_piutangdagang1.getText().toString().equalsIgnoreCase("")) ? NumberTextWatcherForThousand.trimCommaOfString(et_piutangdagang1.getText().toString().trim()) : "0";
            val_utangDagang1 = (!et_utangdagang1.getText().toString().isEmpty() || !et_utangdagang1.getText().toString().equalsIgnoreCase("")) ? NumberTextWatcherForThousand.trimCommaOfString(et_utangdagang1.getText().toString().trim()) : "0";
            val_wiNeraca = (!et_wineraca.getText().toString().isEmpty() || !et_wineraca.getText().toString().equalsIgnoreCase("")) ? NumberTextWatcherForThousand.trimCommaOfString(et_wineraca.getText().toString().trim()) : "0";
            val_dohPersediaanInventori = (!et_dohpersediaaninventori1.getText().toString().isEmpty() || !et_dohpersediaaninventori1.getText().toString().equalsIgnoreCase("")) ? NumberTextWatcherForThousand.trimCommaOfString(et_dohpersediaaninventori1.getText().toString().trim()) : "0";
            val_dohPiutangDagang = (!et_dohpiutangdagang1.getText().toString().isEmpty() || !et_dohpiutangdagang1.getText().toString().equalsIgnoreCase("")) ? NumberTextWatcherForThousand.trimCommaOfString(et_dohpiutangdagang1.getText().toString().trim()) : "0";
            val_dohUtangDagang = (!et_dohutangdagang1.getText().toString().isEmpty() || !et_dohutangdagang1.getText().toString().equalsIgnoreCase("")) ? NumberTextWatcherForThousand.trimCommaOfString(et_dohutangdagang1.getText().toString().trim()) : "0";
            val_grossProfitMargin = (!et_grossprofitmargin.getText().toString().isEmpty() || !et_grossprofitmargin.getText().toString().equalsIgnoreCase("")) ? NumberTextWatcherForThousand.trimCommaOfString(et_grossprofitmargin.getText().toString().trim()) : "0";
            val_persediaanInventori2 = (!et_dohpersediaaninventori1.getText().toString().isEmpty() || !et_dohpersediaaninventori1.getText().toString().equalsIgnoreCase("")) ? NumberTextWatcherForThousand.trimCommaOfString(et_dohpersediaaninventori1.getText().toString().trim()) : "0";
            val_piutangDagang2 = (!et_dohpiutangdagang1.getText().toString().isEmpty() || !et_dohpiutangdagang1.getText().toString().equalsIgnoreCase("")) ? NumberTextWatcherForThousand.trimCommaOfString(et_dohpiutangdagang1.getText().toString().trim()) : "0";
            val_utangDagang2 = (!et_dohutangdagang1.getText().toString().isEmpty() || !et_dohutangdagang1.getText().toString().equalsIgnoreCase("")) ? NumberTextWatcherForThousand.trimCommaOfString(et_dohutangdagang1.getText().toString().trim()) : "0";
            val_dohKebutuhanModalKerja = (!et_dohmodalkerja.getText().toString().isEmpty() || !et_dohmodalkerja.getText().toString().equalsIgnoreCase("")) ? NumberTextWatcherForThousand.trimCommaOfString(et_dohmodalkerja.getText().toString().trim()) : "0";
            val_wiNormal = (!et_winormal.getText().toString().isEmpty() || !et_winormal.getText().toString().equalsIgnoreCase("")) ? NumberTextWatcherForThousand.trimCommaOfString(et_winormal.getText().toString().trim()) : "0";


            final LknPojo d = new LknPojo();
            d.setiNVENTORY(val_persediaanInventori1);
            d.setpIUTANGDAGANG(val_piutangDagang1);
            d.setuTANGDAGANG(val_utangDagang1);
            d.setwINERACA(val_wiNeraca);
            d.setdOHINVENTORY(val_dohPersediaanInventori);
            d.setdOHPIUTANG(val_dohPiutangDagang);
            d.setdOHUTANG(val_dohUtangDagang);
            d.setpERPUTARANPERSEDIAAN(AppUtil.parseLongWithDefault(val_persediaanInventori2, 0));
            d.setpERPUTARANPIUTANG(AppUtil.parseLongWithDefault(val_piutangDagang2, 0));
            d.setpERPUTARANUTANG(AppUtil.parseLongWithDefault(val_utangDagang2, 0));
            d.setkEBUTUHANMODAL(AppUtil.parseLongWithDefault(val_dohKebutuhanModalKerja, 0));
            d.setwINORMAL(val_wiNormal);

            LknPojo dataR = realm.where(LknPojo.class).equalTo("uuid", LknActivity.UUIDR).findFirst();

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
        LknPojo dataRealm = realm.where(LknPojo.class).equalTo("uuid", LknActivity.UUIDR).findFirst();
        et_grossprofitmargin.setText(dataRealm.getgPM());
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

            BigDecimal wiNeraca = new BigDecimal(0);
            BigDecimal dohPersediaanInvetori1 = new BigDecimal(0);
            BigDecimal dohPiutangDagang1 = new BigDecimal(0);
            BigDecimal dohUtangDagang1 = new BigDecimal(0);
            BigDecimal dohModalKerja = new BigDecimal(0);
            BigDecimal wiNormal = new BigDecimal(0);

            if (et_persediaaninventori1.getText().toString().trim().length() > 0 || !et_persediaaninventori1.getText().toString().isEmpty()){
                wiNeraca = wiNeraca.add(new BigDecimal(NumberTextWatcherForThousand.trimCommaOfString(et_persediaaninventori1.getText().toString().trim())));
                dohPersediaanInvetori1 = new BigDecimal(NumberTextWatcherForThousand.trimCommaOfString(et_persediaaninventori1.getText().toString().trim()))
                        .divide(FragmentAnalisaKeuangan.valBd_hargapokokPenjualan, 2 , BigDecimal.ROUND_HALF_UP)
                        .multiply(new BigDecimal(30)).setScale(0, BigDecimal.ROUND_HALF_UP);
                dohModalKerja = dohModalKerja.add(dohPersediaanInvetori1);
            }

            if (et_piutangdagang1.getText().toString().trim().length() > 0 || !et_piutangdagang1.getText().toString().isEmpty()){
                wiNeraca = wiNeraca.add(new BigDecimal(NumberTextWatcherForThousand.trimCommaOfString(et_piutangdagang1.getText().toString().trim())));
                dohPiutangDagang1 = new BigDecimal(NumberTextWatcherForThousand.trimCommaOfString(et_piutangdagang1.getText().toString().trim()))
                        .divide(FragmentAnalisaKeuangan.valBd_pendapatanUsaha, 2, BigDecimal.ROUND_HALF_UP)
                        .multiply(new BigDecimal(30)).setScale(0, BigDecimal.ROUND_HALF_UP);
                dohModalKerja = dohModalKerja.add(dohPiutangDagang1);
            }

            if (et_utangdagang1.getText().toString().trim().length() > 0 || !et_utangdagang1.getText().toString().isEmpty()){
                wiNeraca = wiNeraca.subtract(new BigDecimal(NumberTextWatcherForThousand.trimCommaOfString(et_utangdagang1.getText().toString().trim())));
                dohUtangDagang1 = new BigDecimal(NumberTextWatcherForThousand.trimCommaOfString(et_utangdagang1.getText().toString().trim()))
                        .divide(FragmentAnalisaKeuangan.valBd_hargapokokPenjualan, 2, BigDecimal.ROUND_HALF_UP)
                        .multiply(new BigDecimal(30)).setScale(0, BigDecimal.ROUND_HALF_UP);
                dohModalKerja = dohModalKerja.subtract(dohUtangDagang1);
            }

            et_wineraca.setText(NumberTextWatcherCanNolForThousand.getDecimalFormat(String.valueOf(wiNeraca))); //SET WI NERACA
            et_dohpersediaaninventori1.setText(NumberTextWatcherCanNolForThousand.getDecimalFormat(String.valueOf(dohPersediaanInvetori1))); //SET DOH PERSEDIAAN INVENTORI 1
            et_dohpiutangdagang1.setText(NumberTextWatcherCanNolForThousand.getDecimalFormat(String.valueOf(dohPiutangDagang1))); //SET DOH PIUTANG DAGANG 1
            et_dohutangdagang1.setText(NumberTextWatcherCanNolForThousand.getDecimalFormat(String.valueOf(dohUtangDagang1))); //SET DOH UTANG DAGANG 1
            et_dohmodalkerja.setText(NumberTextWatcherCanNolForThousand.getDecimalFormat(String.valueOf(dohModalKerja))); //SET DOH MODAL KERJA
            wiNormal = new BigDecimal(NumberTextWatcherForThousand.trimCommaOfString(et_dohmodalkerja.getText().toString().trim()))
                    .divide(new BigDecimal(30), 2, BigDecimal.ROUND_HALF_EVEN)
                    .multiply(FragmentAnalisaKeuangan.valBd_hargapokokPenjualan).setScale(0, RoundingMode.HALF_EVEN);

            et_winormal.setText(NumberTextWatcherCanNolForThousand.getDecimalFormat(String.valueOf(wiNormal))); //SET WI NORMAL
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
