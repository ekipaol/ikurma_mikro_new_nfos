package com.application.bris.ikurma.page_aom.view.approved.agunan_apr;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.application.bris.ikurma.R;
import com.application.bris.ikurma.api.config.UriApi;
import com.application.bris.ikurma.database.AppPreferences;
import com.application.bris.ikurma.page_aom.model.ListAgunan;
import com.application.bris.ikurma.page_aom.model.ListInfo;
import com.application.bris.ikurma.util.AppUtil;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PID on 4/26/2019.
 */

public class AgunanTerikatAdapater_Apr extends RecyclerView.Adapter<AgunanTerikatAdapater_Apr.PipelineViewHolder> implements Filterable {

    private Context context;
    private List<ListAgunan> data;
    private List<ListAgunan> datafiltered;
    private List<ListInfo> dataInfo;
    private List<ListInfo> datafilteredInfo;
    AppPreferences appPreferences;

    public AgunanTerikatAdapater_Apr(Context context, List<ListAgunan> data, List<ListInfo> dataInfo) {
        this.context = context;
        this.data = data;
        this.datafiltered = data;
        this.dataInfo = dataInfo;
        this.datafilteredInfo = dataInfo;
    }

    @NonNull
    @Override
    public PipelineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_agunan, parent, false);
        appPreferences=new AppPreferences(context);
        return new PipelineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final PipelineViewHolder holder, final int position) {
        final ListAgunan datas = datafiltered.get(position);


        GlideUrl glideUrl = new GlideUrl(UriApi.Baseurl.URL+UriApi.foto.urlPhoto+datas.getID(), new LazyHeaders.Builder()
                .addHeader("Authorization", "Bearer "+appPreferences.getToken())
                .build());

        Glide
                .with(context)
                .load(glideUrl)
                .centerCrop()
                .placeholder(R.drawable.banner_placeholder)
                .into(holder.iv_foto);
        holder.tv_nama.setText(datas.getNAMA_DEBITUR_1());
        holder.tv_jenis.setText(datas.getDESC_AGUNAN());
        holder.tv_id_agunan.setText("ID Agunan : " + datas.getFID_AGUNAN());
        holder.tv_id_aplikasi.setText(datas.getID_APLIKASI());
        holder.tv_plafond_induk.setText(AppUtil.parseRupiah(String.valueOf(datas.getPLAFOND_INDUK())));
        holder.tv_cover_plafon.setText(AppUtil.parseRupiah(String.valueOf(datas.getNILAI_COVER_PLAFOND())));
        holder.tv_nilai_ikat.setText(AppUtil.parseRupiah(String.valueOf(datas.getNILAI_PENGIKATAN())));
        final String jenisAgunan = datas.getDESC_AGUNAN();

        Log.d("Jenis", jenisAgunan);
        holder.cv_agunan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = null;
                switch (jenisAgunan.toLowerCase()){
                    case "tanah dan bangunan":
                        intent = new Intent(context, AgunanTanahBangunanInputActivity_Apr.class);
                        break;
                    case "kendaraan":
                        intent = new Intent(context, AgunanKendaraanInputActivity_Apr.class);
                        break;
                    case "tanah kosong":
                        intent = new Intent(context, ActivityAgunanTanahKosong_Apr.class);
                        break;
                    case "kios":
                        intent = new Intent(context, AgunanKiosInputActivity_Apr.class);
                        break;
                    case "deposito":
                        intent = new Intent(context, AgunanDepositoInputActivity_Apr.class);
                        break;
                    default:
                        break;
                }
                intent.putExtra("idAplikasi", holder.tv_id_aplikasi.getText().toString());
                intent.putExtra("cif", datafiltered.get(position).getFID_CIF_LAS());
                intent.putExtra("idAgunan", datas.getFID_AGUNAN());
                intent.putExtra("loan_type", String.valueOf(dataInfo.get(0).getLOAN_TYPE()));
                intent.putExtra("tp_produk", String.valueOf(dataInfo.get(0).getFID_TP_PRODUK()));
                intent.putExtra("jenisAgunan", datas.getDESC_AGUNAN());
                intent.putExtra("prev", "terikat");
                context.startActivity(intent);
            }
        });

        switch (datas.getDESC_AGUNAN()){
            case "TANAH DAN BANGUNAN":
                holder.tv_jenis.setBackgroundResource(R.drawable.round_tanahbangunan);
                break;
            case "KENDARAAN":
                holder.tv_jenis.setBackgroundResource(R.drawable.round_kendaraan);
                break;
            case "TANAH KOSONG":
                holder.tv_jenis.setBackgroundResource(R.drawable.round_tanahkosong);
                break;
            case "KIOS":
                holder.tv_jenis.setBackgroundResource(R.drawable.round_kios);
                break;
            case "DEPOSITO":
                holder.tv_jenis.setBackgroundResource(R.drawable.round_deposito);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return datafiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()){
                    datafiltered = data;
                    datafilteredInfo = dataInfo;
                } else {
                    List<ListAgunan> filteredList = new ArrayList<>();
                    for (ListAgunan row : data){
                        if(row.getNAMA_DEBITUR_1().toLowerCase().contains(charString.toLowerCase()) || row.getDESC_AGUNAN().toLowerCase().contains(charString.toLowerCase())
                            || row.getFID_AGUNAN().toLowerCase().contains(charString.toLowerCase()) || row.getID_APLIKASI().toLowerCase().contains(charString.toLowerCase())
                            || String.valueOf(row.getPLAFOND_INDUK()).toLowerCase().contains(charString.toLowerCase())){
                            filteredList.add(row);
                        }
                    }
                    datafiltered = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = datafiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults filterResults) {
                datafiltered = (ArrayList<ListAgunan>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class PipelineViewHolder extends RecyclerView.ViewHolder {
        private ImageView iv_foto;
        private CardView cv_agunan;
        private TextView tv_nama, tv_jenis, tv_id_agunan, tv_id_aplikasi, tv_plafond_induk, tv_cover_plafon, tv_nilai_ikat ;

        public PipelineViewHolder(View itemView) {
            super(itemView);
            iv_foto = (ImageView) itemView.findViewById(R.id.iv_foto);
            cv_agunan = (CardView) itemView.findViewById(R.id.cv_agunan);
            tv_nama = (TextView) itemView.findViewById(R.id.tv_nama);
            tv_jenis = (TextView) itemView.findViewById(R.id.tv_jenis);
            tv_id_agunan = (TextView) itemView.findViewById(R.id.tv_id_agunan);
            tv_id_aplikasi = (TextView) itemView.findViewById(R.id.tv_id_aplikasi);
            tv_plafond_induk = (TextView) itemView.findViewById(R.id.tv_plafond_induk);
            tv_cover_plafon = (TextView) itemView.findViewById(R.id.tv_cover_plafon);
            tv_nilai_ikat = (TextView) itemView.findViewById(R.id.tv_nilai_ikat);
        }
    }
}
