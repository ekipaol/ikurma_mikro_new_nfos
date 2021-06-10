package com.application.bris.ikurma.adapter.hotprospek;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.application.bris.ikurma.R;
import com.application.bris.ikurma.api.config.UriApi;
import com.application.bris.ikurma.database.AppPreferences;
import com.application.bris.ikurma.page_aom.listener.HotprospekHomeListener;
import com.application.bris.ikurma.page_aom.model.hotprospek;
import com.application.bris.ikurma.util.AppUtil;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;

import java.util.List;

/**
 * Created by PID on 4/26/2019.
 */

public class HotprospekHomeAdapater extends RecyclerView.Adapter<HotprospekHomeAdapater.PipelineViewHolder> {

    private Context context;
    private List<hotprospek> data;
    private HotprospekHomeListener hotprospekListener;
    AppPreferences appPreferences;

    public HotprospekHomeAdapater(Context context, List<hotprospek> data, HotprospekHomeListener hotprospekListener) {
        this.context = context;
        this.data = data;
        this.hotprospekListener = hotprospekListener;
    }

    @NonNull
    @Override
    public PipelineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_hotprospek_front, parent, false);
        appPreferences=new AppPreferences(context);
        return new PipelineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PipelineViewHolder holder, int position) {

        final hotprospek datas = data.get(position);

        GlideUrl glideUrl = new GlideUrl(UriApi.Baseurl.URL+UriApi.foto.urlPhoto+datas.getFid_photo(), new LazyHeaders.Builder()
                .addHeader("Authorization", "Bearer "+appPreferences.getToken())
                .build());

        Glide
                .with(context)
                .load(glideUrl)
                .centerCrop()
                .placeholder(R.drawable.banner_placeholder)
                .into(holder.iv_foto);

        holder.tv_id_aplikasi.setText(String.valueOf(datas.getId_aplikasi()));
        holder.tv_nama.setText(datas.getNama_debitur_1());
        holder.tv_produk.setText(datas.getNama_produk());
        holder.tv_plafond.setText(AppUtil.parseRupiah(String.valueOf(datas.getPlafond_induk())));
        holder.tv_tenor.setText(String.valueOf(datas.getJw())+" Bulan");
        holder.tv_tanggalentry.setText(AppUtil.parseTanggalGeneral(datas.getTanggal_entry(), "ddMMyyyy", "dd-MM-yyyy"));
        holder.tv_status.setText(datas.getStatus_aplikasi());
        holder.cv_hotprospek_front.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hotprospekListener.onHotprospekSelect(datas.getId_aplikasi(), datas.getKode_produk());
            }
        });
    }

    @Override
    public int getItemCount() {
        return (data == null) ? 0 : data.size();
    }

    public class PipelineViewHolder extends RecyclerView.ViewHolder {
        private CardView cv_hotprospek_front;
        private ImageView iv_foto;
        private TextView tv_id_aplikasi, tv_nama, tv_produk, tv_plafond, tv_tenor, tv_tanggalentry, tv_status;

        public PipelineViewHolder(View itemView) {
            super(itemView);
            cv_hotprospek_front = (CardView) itemView.findViewById(R.id.cv_hotprospek_front);
            tv_id_aplikasi = (TextView) itemView.findViewById(R.id.tv_id_aplikasi);
            iv_foto = (ImageView) itemView.findViewById(R.id.iv_foto);
            tv_nama = (TextView) itemView.findViewById(R.id.tv_nama);
            tv_produk = (TextView) itemView.findViewById(R.id.tv_produk);
            tv_plafond = (TextView) itemView.findViewById(R.id.tv_plafond);
            tv_tenor = (TextView) itemView.findViewById(R.id.tv_tenor);
            tv_tanggalentry = (TextView) itemView.findViewById(R.id.tv_tanggal_entry);
            tv_status = (TextView) itemView.findViewById(R.id.tv_do);
        }
    }
}
