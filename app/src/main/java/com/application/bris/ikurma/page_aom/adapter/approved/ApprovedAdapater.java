package com.application.bris.ikurma.page_aom.adapter.approved;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.application.bris.ikurma.R;
import com.application.bris.ikurma.api.config.UriApi;
import com.application.bris.ikurma.database.AppPreferences;
import com.application.bris.ikurma.page_aom.listener.ApproveRejectListener;
import com.application.bris.ikurma.page_aom.model.Approved;
import com.application.bris.ikurma.util.AppUtil;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.load.model.LazyHeaders;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PID on 4/26/2019.
 */

public class ApprovedAdapater extends RecyclerView.Adapter<ApprovedAdapater.HotprospekViewHolder> implements Filterable {

    private Context context;
    private List<Approved> data;
    private List<Approved> datafiltered;
    private ApproveRejectListener approvedListener;
    AppPreferences appPreferences;

    public ApprovedAdapater(Context context, List<Approved> data, ApproveRejectListener approvedListener) {
        this.context = context;
        this.data = data;
        this.datafiltered = data;
        this.approvedListener = approvedListener;
    }

    @NonNull
    @Override
    public HotprospekViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.ao_item_approved, parent, false);
        appPreferences= new AppPreferences(context);
        return new HotprospekViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final HotprospekViewHolder holder, final int position) {
        final Approved datas = datafiltered.get(position);

        GlideUrl glideUrl = new GlideUrl(UriApi.Baseurl.URL+UriApi.foto.urlPhoto+datas.getfIDPHOTO(), new LazyHeaders.Builder()
                .addHeader("Authorization", "Bearer "+appPreferences.getToken())
                .build());

        Glide
                .with(context)
                .load(glideUrl)
                .centerCrop()
                .placeholder(R.drawable.banner_placeholder)
                .into(holder.iv_foto);

        holder.tv_id_aplikasi.setText(String.valueOf(datas.getiDAPLIKASI()));
        holder.tv_nama.setText(datas.getnAMADEBITUR1());
        holder.tv_produk.setText(datas.getnAMAPRODUK());
        holder.tv_plafond.setText(AppUtil.parseRupiah(String.valueOf(datas.getpLAFONDINDUK())));
        holder.tv_tenor.setText(String.valueOf(datas.getjW())+" Bulan");
        holder.tv_tanggalentry.setText(AppUtil.parseTanggalGeneral(datas.gettANGGALENTRY(), "ddMMyyyy", "dd-MM-yyyy"));
        holder.tv_status.setText(datas.getsTATUSAPLIKASI());
        holder.tv_noakad.setText(datas.getnOAKAD());
        layoutQard(datas, holder.ll_qardh, holder.tv_noakad_qardh, holder.tv_jatuhtempo_qardh, datas.getId_tujuan());
        holder.cv_approved_front.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                approvedListener.onApproveRejectSelect(datas.getfIDCIFLAS(), datas.getiDAPLIKASI(), datas.getkODEPRODUK());
            }
        });
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
                } else {
                    List<Approved> filteredList = new ArrayList<>();
                    for (Approved row : data){
                        if(row.getnAMADEBITUR1().toLowerCase().contains(charString.toLowerCase()) || row.getnAMAPRODUK().toLowerCase().contains(charString.toLowerCase())
                            || Long.toString(row.getpLAFONDINDUK()).toLowerCase().contains(charString.toLowerCase()) || Integer.toString(row.getjW()).toLowerCase().contains(charString.toLowerCase())){
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
                datafiltered = (ArrayList<Approved>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class HotprospekViewHolder extends RecyclerView.ViewHolder {
        private CardView cv_approved_front;
        private ImageView iv_foto;
        private TextView tv_id_aplikasi, tv_nama, tv_produk, tv_plafond, tv_tenor, tv_tanggalentry, tv_status, tv_noakad, tv_noakad_qardh, tv_jatuhtempo_qardh;
        private LinearLayout ll_qardh;

        public HotprospekViewHolder(View itemView) {
            super(itemView);
            cv_approved_front = (CardView) itemView.findViewById(R.id.cv_approved_front);
            tv_id_aplikasi = (TextView) itemView.findViewById(R.id.tv_id_aplikasi);
            iv_foto = (ImageView) itemView.findViewById(R.id.iv_foto);
            tv_nama = (TextView) itemView.findViewById(R.id.tv_nama);
            tv_produk = (TextView) itemView.findViewById(R.id.tv_produk);
            tv_plafond = (TextView) itemView.findViewById(R.id.tv_plafond);
            tv_tenor = (TextView) itemView.findViewById(R.id.tv_tenor);
            tv_tanggalentry = (TextView) itemView.findViewById(R.id.tv_tanggal_entry);
            tv_status = (TextView) itemView.findViewById(R.id.tv_do);
            tv_noakad = (TextView) itemView.findViewById(R.id.tv_noakad);
            ll_qardh = (LinearLayout) itemView.findViewById(R.id.ll_qardh);
            tv_noakad_qardh = (TextView) itemView.findViewById(R.id.tv_noakad_qardh);
            tv_jatuhtempo_qardh = (TextView) itemView.findViewById(R.id.tv_jatuhtempo_qardh);
        }
    }

    public void layoutQard(Approved data, LinearLayout ll_qardh, TextView tv_noakad_qardh, TextView tv_jatuhtempo_qardh, int tujuanPenggunaan)
    {
        ll_qardh.setVisibility(View.GONE);
        switch (tujuanPenggunaan){
            case 40 :
                break;
            case 41 :
                break;
            case 45 :
                break;
            case 46 :
                break;
            case 47 :
                break;
            case 48 :
                break;
            case 49 :
                break;
            case 50 :
                ll_qardh.setVisibility(View.VISIBLE);
                tv_noakad_qardh.setText(data.getNo_akad_qard());
                tv_jatuhtempo_qardh.setText(AppUtil.parseTanggalGeneral(data.getJt_qardh(), "ddMMyyyy", "dd-MM-yyyy"));
                break;
            case 51 :
                ll_qardh.setVisibility(View.VISIBLE);
                tv_noakad_qardh.setText(data.getNo_akad_qard());
                tv_jatuhtempo_qardh.setText(AppUtil.parseTanggalGeneral(data.getJt_qardh(), "ddMMyyyy", "dd-MM-yyyy"));
                break;
            case 52 :
                ll_qardh.setVisibility(View.VISIBLE);
                tv_noakad_qardh.setText(data.getNo_akad_qard());
                tv_jatuhtempo_qardh.setText(AppUtil.parseTanggalGeneral(data.getJt_qardh(), "ddMMyyyy", "dd-MM-yyyy"));
                break;
            case 53 :
                ll_qardh.setVisibility(View.VISIBLE);
                tv_noakad_qardh.setText(data.getNo_akad_qard());
                tv_jatuhtempo_qardh.setText(AppUtil.parseTanggalGeneral(data.getJt_qardh(), "ddMMyyyy", "dd-MM-yyyy"));
                break;
            case 54 :
                ll_qardh.setVisibility(View.VISIBLE);
                tv_noakad_qardh.setText(data.getNo_akad_qard());
                tv_jatuhtempo_qardh.setText(AppUtil.parseTanggalGeneral(data.getJt_qardh(), "ddMMyyyy", "dd-MM-yyyy"));
                break;
            case 55 :
                ll_qardh.setVisibility(View.VISIBLE);
                tv_noakad_qardh.setText(data.getNo_akad_qard());
                tv_jatuhtempo_qardh.setText(AppUtil.parseTanggalGeneral(data.getJt_qardh(), "ddMMyyyy", "dd-MM-yyyy"));
                break;
            case 56 :
                ll_qardh.setVisibility(View.VISIBLE);
                tv_noakad_qardh.setText(data.getNo_akad_qard());
                tv_jatuhtempo_qardh.setText(AppUtil.parseTanggalGeneral(data.getJt_qardh(), "ddMMyyyy", "dd-MM-yyyy"));
                break;
            case 57 :
                ll_qardh.setVisibility(View.VISIBLE);
                tv_noakad_qardh.setText(data.getNo_akad_qard());
                tv_jatuhtempo_qardh.setText(AppUtil.parseTanggalGeneral(data.getJt_qardh(), "ddMMyyyy", "dd-MM-yyyy"));
                break;
        }
    }
}
