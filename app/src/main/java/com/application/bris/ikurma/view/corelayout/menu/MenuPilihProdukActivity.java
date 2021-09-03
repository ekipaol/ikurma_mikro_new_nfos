package com.application.bris.ikurma.view.corelayout.menu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.application.bris.ikurma.R;
import com.application.bris.ikurma.database.AppPreferences;
import com.application.bris.ikurma.page_aom.view.hotprospek.HotprospekActivity;
import com.application.bris.ikurma.page_aom.view.hotprospek_multifaedahmikro.KmgHotprospekActivity;
import com.application.bris.ikurma.page_aom.view.pipeline.PipelineActivity;
import com.application.bris.ikurma.page_aom.view.pipeline_multifaedahmikro.KmgPipelineActivity;
import com.application.bris.ikurma.util.AppUtil;
import com.application.bris.ikurma.view.corelayout.CoreLayoutActivity;


public class MenuPilihProdukActivity extends AppCompatActivity {
    ImageView bt_mikrofaedah, bt_multifaedahmikro;
    TextView tvNotifikasiMikroFaedah, tvNotifikasiMultiFaedahMikro;
    CardView cvMenuMultiFaedah,cvMenuMikroFaedah;
    AppPreferences appPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_pilih_produk);

        cvMenuMultiFaedah = findViewById(R.id.cv_menu_multi_faedah_mikro);
        cvMenuMikroFaedah = findViewById(R.id.cv_menu_mikro_faedah);


        bt_mikrofaedah = findViewById(R.id.bt_mikro_faedah);
        bt_multifaedahmikro = findViewById(R.id.bt_multi_faedah_mikro);

        tvNotifikasiMikroFaedah = findViewById(R.id.tv_notifikasi_mikro_faedah);
        tvNotifikasiMultiFaedahMikro = findViewById(R.id.tv_notifikasi_multi_faedah_mikro);

        tvNotifikasiMikroFaedah.setVisibility(View.GONE);
        tvNotifikasiMultiFaedahMikro.setVisibility(View.GONE);

        AppUtil.toolbarRegular(this, "Pilih Jenis Pembiayaan");
        appPreferences=new AppPreferences(this);

        otherViewChanges();

        //toolbar back configuration, hard to explain, just ask to mr eki. In short, this is needed so the activity flows as eki wants
        ImageView backToolbar=findViewById(R.id.btn_back);
        backToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent=new Intent(MenuPilihProdukActivity.this, CoreLayoutActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT );
                startActivity(intent);


            }
        });


        bt_mikrofaedah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getIntent().getStringExtra("jenisMenu").equalsIgnoreCase("hotprospek")){
                    Intent intent=new Intent(MenuPilihProdukActivity.this, HotprospekActivity.class);
                    startActivity(intent);
                }
                else if(getIntent().getStringExtra("jenisMenu").equalsIgnoreCase("pipeline")){
                    Intent intent=new Intent(MenuPilihProdukActivity.this, PipelineActivity.class);
                    startActivity(intent);
                }
            }
        });

        bt_multifaedahmikro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(getIntent().getStringExtra("jenisMenu").equalsIgnoreCase("hotprospek")){
                    Intent intent=new Intent(MenuPilihProdukActivity.this, KmgHotprospekActivity.class);
                    startActivity(intent);
                }
                else if(getIntent().getStringExtra("jenisMenu").equalsIgnoreCase("pipeline")){
                    Intent intent=new Intent(MenuPilihProdukActivity.this, KmgPipelineActivity.class);
                    startActivity(intent);
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        Intent intent=new Intent(MenuPilihProdukActivity.this, CoreLayoutActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT );
        startActivity(intent);
    }

    private void otherViewChanges(){
            cvMenuMultiFaedah.setVisibility(View.GONE);
    }


}
