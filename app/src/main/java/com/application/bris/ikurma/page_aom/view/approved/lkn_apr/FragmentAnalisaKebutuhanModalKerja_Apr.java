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

import butterknife.BindView;
import butterknife.ButterKnife;
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes;

/**
 * Created by PID on 6/15/2019.
 */

@SuppressLint("ValidFragment")
public class FragmentAnalisaKebutuhanModalKerja_Apr extends Fragment implements Step{


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

    private DataLkn data;
    public FragmentAnalisaKebutuhanModalKerja_Apr() {
    }

    public FragmentAnalisaKebutuhanModalKerja_Apr(DataLkn mdata) {
        data = mdata;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ao_fragment_analisakebutuhanmodalkerja, container, false);
        ButterKnife.bind(this, view);
        onChangeText();
        setDisable();
        setData();

        return view;
    }

    private void setDisable(){
        et_persediaaninventori1.setEnabled(false);
        et_dohpersediaaninventori1.setEnabled(false);
        et_piutangdagang1.setEnabled(false);
        et_dohpiutangdagang1.setEnabled(false);
        et_utangdagang1.setEnabled(false);
        et_dohutangdagang1.setEnabled(false);
        et_wineraca.setEnabled(false);
        et_grossprofitmargin.setEnabled(false);
        et_dohmodalkerja.setEnabled(false);
        et_winormal.setEnabled(false);
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
       et_persediaaninventori1.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_persediaaninventori1));
       et_piutangdagang1.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_piutangdagang1));
       et_utangdagang1.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_utangdagang1));
       et_winormal.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_winormal));
       et_wineraca.addTextChangedListener(new NumberTextWatcherCanNolForThousand(et_wineraca));
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
