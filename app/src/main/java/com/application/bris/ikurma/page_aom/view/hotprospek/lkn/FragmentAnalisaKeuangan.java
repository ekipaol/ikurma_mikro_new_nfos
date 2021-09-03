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
import android.widget.Toast;

import com.application.bris.ikurma.BuildConfig;
import com.application.bris.ikurma.R;
import com.application.bris.ikurma.database.pojo.LknPojo;
import com.application.bris.ikurma.page_aom.model.DataLkn;
import com.application.bris.ikurma.util.AppUtil;
import com.application.bris.ikurma.util.KeyValue;
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
public class FragmentAnalisaKeuangan extends Fragment implements Step, TextWatcher{

    @BindView(R.id.tf_pendapatanusaha)
    TextFieldBoxes tf_pendapatanusaha;
    @BindView(R.id.et_pendapatanusaha)
    EditText et_pendapatanusaha;
    @BindView(R.id.tf_hargapokokpenjualanusaha)
    TextFieldBoxes tf_hargapokokpenjualanusaha;
    @BindView(R.id.et_hargapokokpenjualanusaha)
    EditText et_hargapokokpenjualanusaha;
    @BindView(R.id.tf_sewakontrakusaha)
    TextFieldBoxes tf_sewakontrakusaha;
    @BindView(R.id.et_sewakontrakusaha)
    EditText et_sewakontrakusaha;
    @BindView(R.id.tf_gajipegawaiusaha)
    TextFieldBoxes tf_gajipegawaiusaha;
    @BindView(R.id.et_gajipegawaiusaha)
    EditText et_gajipegawaiusaha;
    @BindView(R.id.tf_telponlistrikairusaha)
    TextFieldBoxes tf_telponlistrikairusaha;
    @BindView(R.id.et_telponlistrikairusaha)
    EditText et_telponlistrikairusaha;
    @BindView(R.id.tf_transportasiusaha)
    TextFieldBoxes tf_transportasiusaha;
    @BindView(R.id.et_transportasiusaha)
    EditText et_transportasiusaha;
    @BindView(R.id.tf_pengeluaranlainnyausaha)
    TextFieldBoxes tf_pengeluaranlainnyausaha;
    @BindView(R.id.et_pengeluaranlainnyausaha)
    EditText et_pengeluaranlainnyausaha;
    @BindView(R.id.tf_pengeluaranusaha)
    TextFieldBoxes tf_pengeluaranusaha;
    @BindView(R.id.et_pengeluaranusaha)
    EditText et_pengeluaranusaha;
    @BindView(R.id.tf_keuntunganusaha)
    TextFieldBoxes tf_keuntunganusaha;
    @BindView(R.id.et_keuntunganusaha)
    EditText et_keuntunganusaha;
    @BindView(R.id.tf_penghasilanlainnyausaha)
    TextFieldBoxes tf_penghasilanlainnyausaha;
    @BindView(R.id.et_penghasilanlainnyausaha)
    EditText et_penghasilanlainnyausaha;
    @BindView(R.id.tf_totalpenghasilan)
    TextFieldBoxes tf_totalpenghasilan;
    @BindView(R.id.et_totalpenghasilan)
    EditText et_totalpenghasilan;
    @BindView(R.id.tf_pajakretribusiusaha)
    TextFieldBoxes tf_pajakretribusiusaha;
    @BindView(R.id.et_pajakretribusiusaha)
    EditText et_pajakretribusiusaha;
    @BindView(R.id.tf_belanjarumahtanggart)
    TextFieldBoxes tf_belanjarumahtanggart;
    @BindView(R.id.et_belanjarumahtanggart)
    EditText et_belanjarumahtanggart;
    @BindView(R.id.tf_sewakontrakrt)
    TextFieldBoxes tf_sewakontrakrt;
    @BindView(R.id.et_sewakontrakrt)
    EditText et_sewakontrakrt;
    @BindView(R.id.tf_pendidikanrt)
    TextFieldBoxes tf_pendidikanrt;
    @BindView(R.id.et_pendidikanrt)
    EditText et_pendidikanrt;
    @BindView(R.id.tf_telponlistrikairrt)
    TextFieldBoxes tf_telponlistrikairrt;
    @BindView(R.id.et_telponlistrikairrt)
    EditText et_telponlistrikairrt;
    @BindView(R.id.tf_transportasirt)
    TextFieldBoxes tf_transportasirt;
    @BindView(R.id.et_transportasirt)
    EditText et_transportasirt;
    @BindView(R.id.tf_pengeluaranlainnyart)
    TextFieldBoxes tf_pengeluaranlainnyart;
    @BindView(R.id.et_pengeluaranlainnyart)
    EditText et_pengeluaranlainnyart;
    @BindView(R.id.tf_totalpengeluaranrt)
    TextFieldBoxes tf_totalpengeluaranrt;
    @BindView(R.id.et_totalpengeluaranrt)
    EditText et_totalpengeluaranrt;
    @BindView(R.id.tf_sisapenghasilan)
    TextFieldBoxes tf_sisapenghasilan;
    @BindView(R.id.et_sisapenghasilan)
    EditText et_sisapenghasilan;

    private Realm realm;
    private DataLkn data;

    public static String val_pendapatanUsaha ="";
    public static String val_hargaPokokPenjualan ="";
    public static String val_sewaUsaha ="";
    public static String val_gajiPegawaiUsaha ="";
    public static String val_telponListrikAirUsaha ="";
    public static String val_transportasiUsaha ="";
    public static String val_pengeluaranLainnyaUsaha ="";
    public static String val_pengeluaranUsaha ="";
    public static String val_keuntunganUsaha ="";
    public static String val_penghasilanLainnya ="";
    public static String val_totalPenghasilan ="";
    public static String val_pajakRetribusi ="";
    public static String val_belanjaRT ="";
    public static String val_sewaRT ="";
    public static String val_pendidikanRT ="";
    public static String val_telponListrikAirRT ="";
    public static String val_transportasiRT ="";
    public static String val_pengeluaranLainnyaRT ="";
    public static String val_totalPengeluaranRT ="";
    public static String val_sisaPenghasilan ="";

    public static BigDecimal valBd_hargapokokPenjualan = new BigDecimal(0);
    public static BigDecimal valBd_pendapatanUsaha = new BigDecimal(0);
    public static BigDecimal valBd_grossProfitMargin = new BigDecimal(0);



    public FragmentAnalisaKeuangan() {
    }

    public FragmentAnalisaKeuangan(DataLkn mdata) {
        data = mdata;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ao_fragment_analisakeuangan, container, false);
        ButterKnife.bind(this, view);
        realm = Realm.getDefaultInstance();
        disableTextfield();
        onChangeText();
        setData();

        if (data.getiDLKN2() == null) {
            if(BuildConfig.IS_PRODUCTION==false){
                autoInputForTesting();
            }
        }

        return view;
    }

    private void setData(){
        try {
            if (data.getiDLKN2() != null) {
                et_pendapatanusaha.setText(data.getpENDAPATANUSAHA());
                et_hargapokokpenjualanusaha.setText(data.gethARGAPOKOKPENJUALAN());
                et_sewakontrakusaha.setText(data.gethARGASEWA());
                et_gajipegawaiusaha.setText(data.getgAJIPEGAWAI());
                et_telponlistrikairusaha.setText(data.bIAYATELEPONLISTRIK);
                et_transportasiusaha.setText(data.getbIAYATRANSPORTASI());
                et_pengeluaranlainnyausaha.setText(data.getpENGELUARANLAINNYA());
                et_pengeluaranusaha.setText(data.getpENGELUARANUSAHA());
                et_keuntunganusaha.setText(data.getkEUNTUNGANUSAHA());
                et_penghasilanlainnyausaha.setText(data.getpENGHASILANLAINNYA());
                et_totalpenghasilan.setText(data.gettOTALPENGHASILAN());
                et_pajakretribusiusaha.setText(data.getpAJAK());
                et_belanjarumahtanggart.setText(data.getbELANJART());
                et_sewakontrakrt.setText(data.getbIAYASEWARUMAHRT());
                et_pendidikanrt.setText(data.getbIAYAPENDIDIKAN());
                et_telponlistrikairrt.setText(data.getbIAYATELEPONRT());
                et_transportasirt.setText(data.getbIAYATRANSPORTASIRT());
                et_pengeluaranlainnyart.setText(data.getpENGELUARANLAINNYART());
                et_totalpengeluaranrt.setText(data.gettOTALPENGELUARANRT());
                et_sisapenghasilan.setText(data.getsISAPENGHASILAN());

                valBd_grossProfitMargin = new BigDecimal(data.getgPM());
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

   private void onChangeText(){
       et_pendapatanusaha.addTextChangedListener(this);
       et_pendapatanusaha.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_pendapatanusaha));
       et_hargapokokpenjualanusaha.addTextChangedListener(this);
       et_hargapokokpenjualanusaha.addTextChangedListener(new NumberTextWatcherForThousand(et_hargapokokpenjualanusaha));
       et_sewakontrakusaha.addTextChangedListener(this);
       et_sewakontrakusaha.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_sewakontrakusaha));
       et_gajipegawaiusaha.addTextChangedListener(this);
       et_gajipegawaiusaha.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_gajipegawaiusaha));
       et_telponlistrikairusaha.addTextChangedListener(this);
       et_telponlistrikairusaha.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_telponlistrikairusaha));
       et_transportasiusaha.addTextChangedListener(this);
       et_transportasiusaha.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_transportasiusaha));
       et_pengeluaranlainnyausaha.addTextChangedListener(this);
       et_pengeluaranlainnyausaha.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_pengeluaranlainnyausaha));
       et_penghasilanlainnyausaha.addTextChangedListener(this);
       et_penghasilanlainnyausaha.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_penghasilanlainnyausaha));
       et_pajakretribusiusaha.addTextChangedListener(this);
       et_pajakretribusiusaha.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_pajakretribusiusaha));
       et_sisapenghasilan.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_sisapenghasilan));

       //RT
       et_belanjarumahtanggart.addTextChangedListener(this);
       et_belanjarumahtanggart.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_belanjarumahtanggart));
       et_sewakontrakrt.addTextChangedListener(this);
       et_sewakontrakrt.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_sewakontrakrt));
       et_pendidikanrt.addTextChangedListener(this);
       et_pendidikanrt.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_pendidikanrt));
       et_telponlistrikairrt.addTextChangedListener(this);
       et_telponlistrikairrt.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_telponlistrikairrt));
       et_transportasirt.addTextChangedListener(this);
       et_transportasirt.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_transportasirt));
       et_pengeluaranlainnyart.addTextChangedListener(this);
       et_pengeluaranlainnyart.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_pengeluaranlainnyart));
       et_totalpengeluaranrt.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_totalpengeluaranrt));

   }

   private void disableTextfield(){
       et_pengeluaranusaha.setInputType(InputType.TYPE_NULL);
       et_pengeluaranusaha.setFocusable(false);
       et_keuntunganusaha.setInputType(InputType.TYPE_NULL);
       et_keuntunganusaha.setFocusable(false);
       et_totalpenghasilan.setInputType(InputType.TYPE_NULL);
       et_totalpenghasilan.setFocusable(false);
       et_totalpengeluaranrt.setInputType(InputType.TYPE_NULL);
       et_totalpengeluaranrt.setFocusable(false);
       et_sisapenghasilan.setInputType(InputType.TYPE_NULL);
       et_sisapenghasilan.setFocusable(false);
   }

    private VerificationError validateForm(){
        if (et_pendapatanusaha.getText().toString().isEmpty() || et_pendapatanusaha.getText().toString().equalsIgnoreCase("")){
            tf_pendapatanusaha.setError("Format "+ tf_pendapatanusaha.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_pendapatanusaha.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_hargapokokpenjualanusaha.getText().toString().isEmpty() || et_hargapokokpenjualanusaha.getText().toString().equalsIgnoreCase("") || et_hargapokokpenjualanusaha.getText().toString().equalsIgnoreCase("0")){
            tf_hargapokokpenjualanusaha.setError("Format "+ tf_hargapokokpenjualanusaha.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_hargapokokpenjualanusaha.getLabelText()+" "+getString(R.string.title_validate_field)+" atau 0, minimal 1");
        }

        else if (et_sewakontrakusaha.getText().toString().isEmpty() || et_sewakontrakusaha.getText().toString().equalsIgnoreCase("")){
            tf_sewakontrakusaha.setError("Format "+ tf_sewakontrakusaha.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_sewakontrakusaha.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_gajipegawaiusaha.getText().toString().isEmpty() || et_gajipegawaiusaha.getText().toString().equalsIgnoreCase("")){
            tf_gajipegawaiusaha.setError("Format "+ tf_gajipegawaiusaha.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_gajipegawaiusaha.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_telponlistrikairusaha.getText().toString().isEmpty() || et_telponlistrikairusaha.getText().toString().equalsIgnoreCase("")){
            tf_telponlistrikairusaha.setError("Format "+ tf_telponlistrikairusaha.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_telponlistrikairusaha.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_transportasiusaha.getText().toString().isEmpty() || et_transportasiusaha.getText().toString().equalsIgnoreCase("")){
            tf_transportasiusaha.setError("Format "+ tf_transportasiusaha.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_transportasiusaha.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_pengeluaranlainnyausaha.getText().toString().isEmpty() || et_pengeluaranlainnyausaha.getText().toString().equalsIgnoreCase("")){
            tf_pengeluaranlainnyausaha.setError("Format "+ tf_pengeluaranlainnyausaha.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_pengeluaranlainnyausaha.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_pengeluaranusaha.getText().toString().isEmpty() || et_pengeluaranusaha.getText().toString().equalsIgnoreCase("")){
            tf_pengeluaranusaha.setError("Format "+ tf_pengeluaranusaha.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_pengeluaranusaha.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_keuntunganusaha.getText().toString().isEmpty() || et_keuntunganusaha.getText().toString().equalsIgnoreCase("")){
            tf_keuntunganusaha.setError("Format "+ tf_keuntunganusaha.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_keuntunganusaha.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_penghasilanlainnyausaha.getText().toString().isEmpty() || et_penghasilanlainnyausaha.getText().toString().equalsIgnoreCase("")){
            tf_penghasilanlainnyausaha.setError("Format "+ tf_penghasilanlainnyausaha.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_penghasilanlainnyausaha.getLabelText()+" "+getString(R.string.title_validate_field));
        }


        else if (et_totalpenghasilan.getText().toString().isEmpty() || et_totalpenghasilan.getText().toString().equalsIgnoreCase("")){
            tf_totalpenghasilan.setError("Format "+ tf_totalpenghasilan.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_totalpenghasilan.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_pajakretribusiusaha.getText().toString().isEmpty() || et_pajakretribusiusaha.getText().toString().equalsIgnoreCase("")){
            tf_pajakretribusiusaha.setError("Format "+ tf_pajakretribusiusaha.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_pajakretribusiusaha.getLabelText()+" "+getString(R.string.title_validate_field));
        }


        //RUMAH TANGGA
        else if (et_belanjarumahtanggart.getText().toString().isEmpty() || et_belanjarumahtanggart.getText().toString().equalsIgnoreCase("")){
            tf_belanjarumahtanggart.setError("Format "+ tf_belanjarumahtanggart.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_belanjarumahtanggart.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_sewakontrakrt.getText().toString().isEmpty() || et_sewakontrakrt.getText().toString().equalsIgnoreCase("")){
            tf_sewakontrakrt.setError("Format "+ tf_sewakontrakrt.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_sewakontrakrt.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else if (et_pendidikanrt.getText().toString().isEmpty() || et_pendidikanrt.getText().toString().equalsIgnoreCase("")){
            tf_pendidikanrt.setError("Format "+ tf_pendidikanrt.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_pendidikanrt.getLabelText()+" "+getString(R.string.title_validate_field));
        }
        else if (et_telponlistrikairrt.getText().toString().isEmpty() || et_telponlistrikairrt.getText().toString().equalsIgnoreCase("")){
            tf_telponlistrikairrt.setError("Format "+ tf_telponlistrikairrt.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_telponlistrikairrt.getLabelText()+" "+getString(R.string.title_validate_field));
        }
        else if (et_transportasirt.getText().toString().isEmpty() || et_transportasirt.getText().toString().equalsIgnoreCase("")){
            tf_transportasirt.setError("Format "+ tf_transportasirt.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_transportasirt.getLabelText()+" "+getString(R.string.title_validate_field));
        }
        else if (et_pengeluaranlainnyart.getText().toString().isEmpty() || et_pengeluaranlainnyart.getText().toString().equalsIgnoreCase("")){
            tf_pengeluaranlainnyart.setError("Format "+ tf_pengeluaranlainnyart.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_pengeluaranlainnyart.getLabelText()+" "+getString(R.string.title_validate_field));
        }
        else if (et_totalpengeluaranrt.getText().toString().isEmpty() || et_totalpengeluaranrt.getText().toString().equalsIgnoreCase("")){
            tf_totalpengeluaranrt.setError("Format "+ tf_totalpengeluaranrt.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_totalpengeluaranrt.getLabelText()+" "+getString(R.string.title_validate_field));
        }
        else if (et_sisapenghasilan.getText().toString().isEmpty() || et_sisapenghasilan.getText().toString().equalsIgnoreCase("")){
            tf_sisapenghasilan.setError("Format "+ tf_sisapenghasilan.getLabelText()+" "+getString(R.string.title_validate_field), true);
            return new VerificationError("Format "+ tf_sisapenghasilan.getLabelText()+" "+getString(R.string.title_validate_field));
        }

        else {
            setDataOnListerner();
            return null;
        }
    }

    public void setDataOnListerner(){
        try {
            //USAHA
            val_pendapatanUsaha = (!et_pendapatanusaha.getText().toString().isEmpty() || !et_pendapatanusaha.getText().toString().equalsIgnoreCase("")) ? NumberTextWatcherForThousand.trimCommaOfString(et_pendapatanusaha.getText().toString().trim()) : "0";
            val_hargaPokokPenjualan = (!et_hargapokokpenjualanusaha.getText().toString().isEmpty() || !et_hargapokokpenjualanusaha.getText().toString().equalsIgnoreCase("")) ? NumberTextWatcherForThousand.trimCommaOfString(et_hargapokokpenjualanusaha.getText().toString().trim()) : "0";
            val_sewaUsaha = (!et_sewakontrakusaha.getText().toString().isEmpty() || !et_sewakontrakusaha.getText().toString().equalsIgnoreCase("")) ? NumberTextWatcherForThousand.trimCommaOfString(et_sewakontrakusaha.getText().toString().trim()) : "0";
            val_gajiPegawaiUsaha = (!et_gajipegawaiusaha.getText().toString().isEmpty() || !et_gajipegawaiusaha.getText().toString().equalsIgnoreCase("")) ? NumberTextWatcherForThousand.trimCommaOfString(et_gajipegawaiusaha.getText().toString().trim()) : "0";
            val_telponListrikAirUsaha = (!et_telponlistrikairusaha.getText().toString().isEmpty() || !et_telponlistrikairusaha.getText().toString().equalsIgnoreCase("")) ? NumberTextWatcherForThousand.trimCommaOfString(et_telponlistrikairusaha.getText().toString().trim()) : "0";
            val_transportasiUsaha = (!et_transportasiusaha.getText().toString().isEmpty() || !et_transportasiusaha.getText().toString().equalsIgnoreCase("")) ? NumberTextWatcherForThousand.trimCommaOfString(et_transportasiusaha.getText().toString().trim()) : "0";
            val_pengeluaranLainnyaUsaha = (!et_pengeluaranlainnyausaha.getText().toString().isEmpty() || !et_pengeluaranlainnyausaha.getText().toString().equalsIgnoreCase("")) ? NumberTextWatcherForThousand.trimCommaOfString(et_pengeluaranlainnyausaha.getText().toString().trim()) : "0";
            val_pengeluaranUsaha = (!et_pengeluaranusaha.getText().toString().isEmpty() || !et_pengeluaranusaha.getText().toString().equalsIgnoreCase("")) ? NumberTextWatcherForThousand.trimCommaOfString(et_pengeluaranusaha.getText().toString().trim()) : "0";
            val_keuntunganUsaha = (!et_keuntunganusaha.getText().toString().isEmpty() || !et_keuntunganusaha.getText().toString().equalsIgnoreCase("")) ? NumberTextWatcherForThousand.trimCommaOfString(et_keuntunganusaha.getText().toString().trim()) : "0";
            val_penghasilanLainnya = (!et_penghasilanlainnyausaha.getText().toString().isEmpty() || !et_penghasilanlainnyausaha.getText().toString().equalsIgnoreCase("")) ? NumberTextWatcherForThousand.trimCommaOfString(et_penghasilanlainnyausaha.getText().toString().trim()) : "0";
            val_totalPenghasilan = (!et_totalpenghasilan.getText().toString().isEmpty() || !et_totalpenghasilan.getText().toString().equalsIgnoreCase("")) ? NumberTextWatcherForThousand.trimCommaOfString(et_totalpenghasilan.getText().toString().trim()) : "0";
            val_pajakRetribusi = (!et_pajakretribusiusaha.getText().toString().isEmpty() || !et_pajakretribusiusaha.getText().toString().equalsIgnoreCase("")) ? NumberTextWatcherForThousand.trimCommaOfString(et_pajakretribusiusaha.getText().toString().trim()) : "0";

            //RT
            val_belanjaRT = (!et_belanjarumahtanggart.getText().toString().isEmpty() || !et_belanjarumahtanggart.getText().toString().equalsIgnoreCase("")) ? NumberTextWatcherForThousand.trimCommaOfString(et_belanjarumahtanggart.getText().toString().trim()) : "0";
            val_sewaRT = (!et_sewakontrakrt.getText().toString().isEmpty() || !et_sewakontrakrt.getText().toString().equalsIgnoreCase("")) ? NumberTextWatcherForThousand.trimCommaOfString(et_sewakontrakrt.getText().toString().trim()) : "0";
            val_pendidikanRT = (!et_pendidikanrt.getText().toString().isEmpty() || !et_pendidikanrt.getText().toString().equalsIgnoreCase("")) ? NumberTextWatcherForThousand.trimCommaOfString(et_pendidikanrt.getText().toString().trim()) : "0";
            val_telponListrikAirRT = (!et_telponlistrikairrt.getText().toString().isEmpty() || !et_telponlistrikairrt.getText().toString().equalsIgnoreCase("")) ? NumberTextWatcherForThousand.trimCommaOfString(et_telponlistrikairrt.getText().toString().trim()) : "0";
            val_transportasiRT = (!et_transportasirt.getText().toString().isEmpty() || !et_transportasirt.getText().toString().equalsIgnoreCase("")) ? NumberTextWatcherForThousand.trimCommaOfString(et_transportasirt.getText().toString().trim()) : "0";
            val_pengeluaranLainnyaRT = (!et_pengeluaranlainnyart.getText().toString().isEmpty() || !et_pengeluaranlainnyart.getText().toString().equalsIgnoreCase("")) ? NumberTextWatcherForThousand.trimCommaOfString(et_pengeluaranlainnyart.getText().toString().trim()) : "0";
            val_totalPengeluaranRT = (!et_totalpengeluaranrt.getText().toString().isEmpty() || !et_totalpengeluaranrt.getText().toString().equalsIgnoreCase("")) ? NumberTextWatcherForThousand.trimCommaOfString(et_totalpengeluaranrt.getText().toString().trim()) : "0";
            val_sisaPenghasilan = (!et_sisapenghasilan.getText().toString().isEmpty() || !et_sisapenghasilan.getText().toString().equalsIgnoreCase("")) ? NumberTextWatcherForThousand.trimCommaOfString(et_sisapenghasilan.getText().toString().trim()) : "0";


            final LknPojo d = new LknPojo();
            //USAHA
            d.setpENDAPATANUSAHA(val_pendapatanUsaha);
            d.sethARGAPOKOKPENJUALAN(val_hargaPokokPenjualan);
            d.sethARGASEWA(val_sewaUsaha);
            d.setgAJIPEGAWAI(val_gajiPegawaiUsaha);
            d.setbIAYATELEPONLISTRIK(val_telponListrikAirUsaha);
            d.setbIAYATRANSPORTASI(val_transportasiUsaha);
            d.setpENGELUARANLAINNYA(val_pengeluaranLainnyaUsaha);
            d.setpENGELUARANUSAHA(val_pengeluaranUsaha);
            d.setkEUNTUNGANUSAHA(val_keuntunganUsaha);
            d.setpENGHASILANLAINNYA(val_penghasilanLainnya);
            d.settOTALPENGHASILAN(val_totalPenghasilan);
            d.setpAJAK(val_pajakRetribusi);

            //RT
            d.setbELANJART(val_belanjaRT);
            d.setbIAYASEWARUMAHRT(val_sewaRT);
            d.setbIAYAPENDIDIKAN(val_pendidikanRT);
            d.setbIAYATELEPONRT(val_telponListrikAirRT);
            d.setbIAYATRANSPORTASIRT(val_transportasiRT);
            d.setpENGELUARANLAINNYART(val_pengeluaranLainnyaRT);
            d.settOTALPENGELUARANRT(val_totalPengeluaranRT);
            d.setsISAPENGHASILAN(val_sisaPenghasilan);
            d.setgPM(String.valueOf(valBd_grossProfitMargin));

            LknPojo dataR = realm.where(LknPojo.class).equalTo("uuid", LknActivity.UUIDR).findFirst();

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

            BigDecimal pengeluaranUsaha = new BigDecimal(0);

            if (et_hargapokokpenjualanusaha.getText().toString().trim().length() > 0 || !et_hargapokokpenjualanusaha.getText().toString().isEmpty()){
                pengeluaranUsaha = pengeluaranUsaha.add(new BigDecimal(NumberTextWatcherForThousand.trimCommaOfString(et_hargapokokpenjualanusaha.getText().toString().trim())));
                valBd_hargapokokPenjualan = new BigDecimal(NumberTextWatcherForThousand.trimCommaOfString(et_hargapokokpenjualanusaha.getText().toString().trim()));
            }

            if (et_sewakontrakusaha.getText().toString().trim().length() > 0 || !et_sewakontrakusaha.getText().toString().isEmpty()){

                pengeluaranUsaha = pengeluaranUsaha.add(new BigDecimal(NumberTextWatcherForThousand.trimCommaOfString(et_sewakontrakusaha.getText().toString().trim())));
            }

            if (et_gajipegawaiusaha.getText().toString().trim().length() > 0 || !et_gajipegawaiusaha.getText().toString().isEmpty()){

                pengeluaranUsaha = pengeluaranUsaha.add(new BigDecimal(NumberTextWatcherForThousand.trimCommaOfString(et_gajipegawaiusaha.getText().toString().trim())));
            }

            if (et_telponlistrikairusaha.getText().toString().trim().length() > 0 || !et_telponlistrikairusaha.getText().toString().isEmpty()){

                pengeluaranUsaha = pengeluaranUsaha.add(new BigDecimal(NumberTextWatcherForThousand.trimCommaOfString(et_telponlistrikairusaha.getText().toString().trim())));
            }

            if (et_transportasiusaha.getText().toString().trim().length() > 0 || !et_transportasiusaha.getText().toString().isEmpty()){

                pengeluaranUsaha = pengeluaranUsaha.add(new BigDecimal(NumberTextWatcherForThousand.trimCommaOfString(et_transportasiusaha.getText().toString().trim())));
            }

            if (et_pengeluaranlainnyausaha.getText().toString().trim().length() > 0 || !et_pengeluaranlainnyausaha.getText().toString().isEmpty()){

                pengeluaranUsaha = pengeluaranUsaha.add(new BigDecimal(NumberTextWatcherForThousand.trimCommaOfString(et_pengeluaranlainnyausaha.getText().toString().trim())));
            }

            et_pengeluaranusaha.setText(NumberTextWatcherCanNolForThousand.getDecimalFormat(String.valueOf(pengeluaranUsaha))); //SET PENGELUARAN USAHA

            BigDecimal keuntunganUsaha = new BigDecimal(0);
            if (et_pendapatanusaha.getText().toString().trim().length() > 0 || !et_pendapatanusaha.getText().toString().isEmpty()){
                keuntunganUsaha = keuntunganUsaha.add(new BigDecimal(NumberTextWatcherForThousand.trimCommaOfString(et_pendapatanusaha.getText().toString().trim()))).subtract(pengeluaranUsaha);
                valBd_pendapatanUsaha = new BigDecimal(NumberTextWatcherForThousand.trimCommaOfString(et_pendapatanusaha.getText().toString().trim()));
            }

            et_keuntunganusaha.setText(NumberTextWatcherCanNolForThousand.getDecimalFormat(String.valueOf(keuntunganUsaha))); // SET KEUNTUNGAN USAHA

            BigDecimal totalPenghasilan = new BigDecimal(0);
            if (et_penghasilanlainnyausaha.getText().toString().trim().length() > 0 || !et_penghasilanlainnyausaha.getText().toString().isEmpty()){

                totalPenghasilan = totalPenghasilan.add(new BigDecimal(NumberTextWatcherForThousand.trimCommaOfString(et_penghasilanlainnyausaha.getText().toString().trim()))).add(keuntunganUsaha);
            }

            et_totalpenghasilan.setText(NumberTextWatcherCanNolForThousand.getDecimalFormat(String.valueOf(totalPenghasilan))); //SET TOTAL PENGHASILAN


            valBd_grossProfitMargin = (valBd_pendapatanUsaha.subtract(valBd_hargapokokPenjualan)).divide(valBd_pendapatanUsaha, 2, RoundingMode.HALF_UP).multiply(new BigDecimal(100)).setScale(1, BigDecimal.ROUND_HALF_UP);


        /* ######################## RT ######################### */

            BigDecimal pengeluaranRT = new BigDecimal(0);

            if (et_belanjarumahtanggart.getText().toString().trim().length() > 0 || !et_belanjarumahtanggart.getText().toString().isEmpty()){

                pengeluaranRT = pengeluaranRT.add(new BigDecimal(NumberTextWatcherForThousand.trimCommaOfString(et_belanjarumahtanggart.getText().toString().trim())));
            }

            if (et_sewakontrakrt.getText().toString().trim().length() > 0 || !et_sewakontrakrt.getText().toString().isEmpty()){

                pengeluaranRT = pengeluaranRT.add(new BigDecimal(NumberTextWatcherForThousand.trimCommaOfString(et_sewakontrakrt.getText().toString().trim())));
            }

            if (et_pendidikanrt.getText().toString().trim().length() > 0 || !et_pendidikanrt.getText().toString().isEmpty()){

                pengeluaranRT = pengeluaranRT.add(new BigDecimal(NumberTextWatcherForThousand.trimCommaOfString(et_pendidikanrt.getText().toString().trim())));
            }

            if (et_telponlistrikairrt.getText().toString().trim().length() > 0 || !et_telponlistrikairrt.getText().toString().isEmpty()){

                pengeluaranRT = pengeluaranRT.add(new BigDecimal(NumberTextWatcherForThousand.trimCommaOfString(et_telponlistrikairrt.getText().toString().trim())));
            }

            if (et_transportasirt.getText().toString().trim().length() > 0 || !et_transportasirt.getText().toString().isEmpty()){

                pengeluaranRT = pengeluaranRT.add(new BigDecimal(NumberTextWatcherForThousand.trimCommaOfString(et_transportasirt.getText().toString().trim())));
            }

            if (et_pengeluaranlainnyart.getText().toString().trim().length() > 0 || !et_pengeluaranlainnyart.getText().toString().isEmpty()){

                pengeluaranRT = pengeluaranRT.add(new BigDecimal(NumberTextWatcherForThousand.trimCommaOfString(et_pengeluaranlainnyart.getText().toString().trim())));
            }

            et_totalpengeluaranrt.setText(NumberTextWatcherCanNolForThousand.getDecimalFormat(String.valueOf(pengeluaranRT))); //SET PENGELUARAN RT


            BigDecimal sisaPenghasilan = new BigDecimal(0);
            if (et_pajakretribusiusaha.getText().toString().trim().length() > 0 || !et_pajakretribusiusaha.getText().toString().isEmpty()){
                sisaPenghasilan = totalPenghasilan.subtract((pengeluaranRT.add(new BigDecimal(NumberTextWatcherForThousand.trimCommaOfString(et_pajakretribusiusaha.getText().toString().trim())))));
            }

            et_sisapenghasilan.setText(NumberTextWatcherCanNolForThousand.getDecimalFormat(String.valueOf(sisaPenghasilan)));
        }
        catch (Exception e){
            e.printStackTrace();
            AppUtil.showToastShort(getContext(), e.getMessage());
        }



    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    private void autoInputForTesting(){


        Toast.makeText(getContext(), "Isi data otomatis untuk testing", Toast.LENGTH_SHORT).show();

        if (data.getiDLKN2() != null) {
            et_pendapatanusaha.setText("9000000");
            et_hargapokokpenjualanusaha.setText("100");
            et_sewakontrakusaha.setText("");
            et_gajipegawaiusaha.setText("0");
            et_telponlistrikairusaha.setText("0");
            et_transportasiusaha.setText("0");
            et_pengeluaranlainnyausaha.setText("0");
            et_pengeluaranusaha.setText("0");
            et_keuntunganusaha.setText("0");
            et_penghasilanlainnyausaha.setText("0");
            et_totalpenghasilan.setText("0");
            et_pajakretribusiusaha.setText("");
            et_belanjarumahtanggart.setText("0");
            et_sewakontrakrt.setText("0");
            et_pendidikanrt.setText("0");
            et_telponlistrikairrt.setText("0");
            et_transportasirt.setText("0");
            et_pengeluaranlainnyart.setText("0");
            et_totalpengeluaranrt.setText("0");
            et_sisapenghasilan.setText("");

//            valBd_grossProfitMargin = new BigDecimal(data.getgPM());
        }


    }
}
