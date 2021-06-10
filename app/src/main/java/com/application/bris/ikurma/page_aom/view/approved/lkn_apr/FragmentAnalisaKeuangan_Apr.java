package com.application.bris.ikurma.page_aom.view.approved.lkn_apr;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.application.bris.ikurma.R;
import com.application.bris.ikurma.page_aom.model.DataLkn;
import com.application.bris.ikurma.util.NumberTextWatcherCanNolForThousand;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;

import java.math.BigDecimal;

import butterknife.BindView;
import butterknife.ButterKnife;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

/**
 * Created by PID on 6/15/2019.
 */

@SuppressLint("ValidFragment")
public class FragmentAnalisaKeuangan_Apr extends Fragment implements Step{

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

    private DataLkn data;

    public static BigDecimal valBd_hargapokokPenjualan = new BigDecimal(0);
    public static BigDecimal valBd_pendapatanUsaha = new BigDecimal(0);
    public static BigDecimal valBd_grossProfitMargin = new BigDecimal(0);



    public FragmentAnalisaKeuangan_Apr() {
    }

    public FragmentAnalisaKeuangan_Apr(DataLkn mdata) {
        data = mdata;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ao_fragment_analisakeuangan, container, false);
        ButterKnife.bind(this, view);
        setDisable();
        onChangeText();
        setData();

        return view;
    }

    private void setDisable(){
        et_pendapatanusaha.setEnabled(false);
        et_hargapokokpenjualanusaha.setEnabled(false);
        et_sewakontrakusaha.setEnabled(false);
        et_gajipegawaiusaha.setEnabled(false);
        et_telponlistrikairusaha.setEnabled(false);
        et_transportasiusaha.setEnabled(false);
        et_pengeluaranlainnyausaha.setEnabled(false);
        et_pengeluaranusaha.setEnabled(false);
        et_keuntunganusaha.setEnabled(false);
        et_penghasilanlainnyausaha.setEnabled(false);
        et_totalpenghasilan.setEnabled(false);
        et_pajakretribusiusaha.setEnabled(false);
        et_belanjarumahtanggart.setEnabled(false);
        et_sewakontrakrt.setEnabled(false);
        et_pendidikanrt.setEnabled(false);
        et_telponlistrikairrt.setEnabled(false);
        et_transportasirt.setEnabled(false);
        et_pengeluaranlainnyart.setEnabled(false);
        et_totalpengeluaranrt.setEnabled(false);
        et_sisapenghasilan.setEnabled(false);
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
       et_pendapatanusaha.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_pendapatanusaha));
       et_hargapokokpenjualanusaha.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_hargapokokpenjualanusaha));
       et_sewakontrakusaha.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_sewakontrakusaha));
       et_gajipegawaiusaha.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_gajipegawaiusaha));
       et_telponlistrikairusaha.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_telponlistrikairusaha));
       et_transportasiusaha.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_transportasiusaha));
       et_pengeluaranlainnyausaha.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_pengeluaranlainnyausaha));
       et_pengeluaranusaha.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_pengeluaranusaha));
       et_keuntunganusaha.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_keuntunganusaha));
       et_penghasilanlainnyausaha.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_penghasilanlainnyausaha));
       et_totalpenghasilan.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_totalpenghasilan));
       et_pajakretribusiusaha.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_pajakretribusiusaha));
       et_sisapenghasilan.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_sisapenghasilan));

       //RT
       et_belanjarumahtanggart.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_belanjarumahtanggart));
       et_sewakontrakrt.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_sewakontrakrt));
       et_pendidikanrt.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_pendidikanrt));
       et_telponlistrikairrt.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_telponlistrikairrt));
       et_transportasirt.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_transportasirt));
       et_pengeluaranlainnyart.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_pengeluaranlainnyart));
       et_totalpengeluaranrt.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_totalpengeluaranrt));

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
