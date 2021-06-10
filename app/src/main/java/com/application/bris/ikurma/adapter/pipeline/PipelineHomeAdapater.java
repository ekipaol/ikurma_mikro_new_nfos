package com.application.bris.ikurma.adapter.pipeline;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.application.bris.ikurma.R;
import com.application.bris.ikurma.api.config.UriApi;
import com.application.bris.ikurma.database.AppPreferences;
import com.application.bris.ikurma.page_aom.listener.PipelineHomeListener;
import com.application.bris.ikurma.page_aom.model.pipeline;
import com.application.bris.ikurma.util.AppUtil;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;

import java.util.List;

/**
 * Created by PID on 4/26/2019.
 */

public class PipelineHomeAdapater extends RecyclerView.Adapter<PipelineHomeAdapater.PipelineViewHolder> {

    private Context context;
    private List<pipeline> data;
    private PipelineHomeListener pipelineListener;
    AppPreferences appPreferences;

    public PipelineHomeAdapater(Context context, List<pipeline> data, PipelineHomeListener pipelineListener) {
        this.context = context;
        this.data = data;
        this.pipelineListener = pipelineListener;
    }

    @NonNull
    @Override
    public PipelineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_pipeline_front, parent, false);
        appPreferences=new AppPreferences(context);
        return new PipelineViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PipelineViewHolder holder, int position) {
        final pipeline datas = data.get(position);

        GlideUrl glideUrl = new GlideUrl(UriApi.Baseurl.URL+UriApi.foto.urlPhoto+datas.getFid_photo(), new LazyHeaders.Builder()
                .addHeader("Authorization", "Bearer "+appPreferences.getToken())
                .build());

        Glide
                .with(context)
                .load(glideUrl)
                .centerCrop()
                .placeholder(R.drawable.banner_placeholder)
                .into(holder.iv_foto);
        holder.tv_nama.setText(datas.getNama());
        holder.tv_produk.setText(datas.getNama_produk());
        holder.tv_plafond.setText(AppUtil.parseRupiah(Integer.toString(datas.getPlafond())));
        holder.tv_tenor.setText(datas.getJw()+" Bulan");
        holder.tv_waktu.setText(datas.getTgl_input());
        holder.btn_process.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pipelineListener.onPipelineSelect(datas.getId(), datas.getKode_produk());
            }
        });
        holder.cv_pipeline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pipelineListener.onPipelineSelect(datas.getId(), datas.getKode_produk());
            }
        });
    }

    @Override
    public int getItemCount() {
        return (data == null) ? 0 : data.size();
    }

    public class PipelineViewHolder extends RecyclerView.ViewHolder {
        private CardView cv_pipeline;
        private ImageView iv_foto;
        private TextView tv_nama, tv_produk, tv_plafond, tv_tenor, tv_waktu;
        private Button btn_process;
        public PipelineViewHolder(View itemView) {
            super(itemView);
            cv_pipeline = (CardView) itemView.findViewById(R.id.cv_pipeline);
            iv_foto = (ImageView) itemView.findViewById(R.id.iv_foto);
            tv_nama = (TextView) itemView.findViewById(R.id.tv_nama);
            tv_produk = (TextView) itemView.findViewById(R.id.tv_produk);
            tv_plafond = (TextView) itemView.findViewById(R.id.tv_plafond);
            tv_tenor = (TextView) itemView.findViewById(R.id.tv_tenor);
            tv_waktu = (TextView) itemView.findViewById(R.id.tv_waktu);
            btn_process = (Button) itemView.findViewById(R.id.btn_proses);
        }
    }
}
