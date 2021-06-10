package com.application.bris.ikurma.page_aom.dialog;


/**
 * Created by idong on 06/05/2019.
 */

import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.application.bris.ikurma.R;
import com.application.bris.ikurma.page_aom.model.MListInstansi;
import com.application.bris.ikurma.page_aom.listener.LoadDataInputPipelineKmgListener;
import com.application.bris.ikurma.page_aom.model.MKategoriNasabahPensiun;
import com.application.bris.ikurma.page_aom.model.MProgramKmg;
import com.application.bris.ikurma.page_aom.model.MRekananDM;
import com.application.bris.ikurma.page_aom.model.MTujuanPenggunaan;

import java.util.List;

public class DialogInputDataPipelineKmg extends DialogFragment {
    private ImageView btn_close;
    private TextView tv_title;
    private DialogInputDataPipelineKmg.ProgramAdapater produkAdapaterProgram;
    private DialogInputDataPipelineKmg.InstitusiAdapater produkAdapaterInstitusi;
    private DialogInputDataPipelineKmg.RekananDMAdapater produkAdapaterRekananDM;
    private DialogInputDataPipelineKmg.TujuanAdapter produkAdapterTujuan;
    private DialogInputDataPipelineKmg.InstansiAdapter produkAdapterInstansi;
    private DialogInputDataPipelineKmg.KategoriNasabahAdapater produkAdapterKategoriNasabah;

    private RecyclerView rv_produk;
    private static List<?> dataProgram, dataInstitusi, dataRekananDM, dataTujuan, dataInstansi, dataKategNasabah;
    private static LoadDataInputPipelineKmgListener listener;
    public static final String TAG = "example_dialog";
    public List<MProgramKmg> dataKeyValueProgram;
    public List<MKategoriNasabahPensiun> dataKeyValueInstitusi;
    public List<MRekananDM> dataKeyValueRekananDM;
    public List<MTujuanPenggunaan> dataKeyValueTujuan;
    public List<MListInstansi> dataKeyValueInstansi;
    public List<MKategoriNasabahPensiun> dataKeyValueKategNasabah;
    public static String dialog;

    private static String title;

    public static DialogInputDataPipelineKmg display(FragmentManager fragmentManager, String titleId, List<?> mdata, LoadDataInputPipelineKmgListener mlistener, String mdialog) {
        dialog = mdialog;
        title = titleId;
        listener = mlistener;

        if (dialog.equalsIgnoreCase("program")) {
            dataProgram = (List<MProgramKmg>) mdata;
        } else if (dialog.equalsIgnoreCase("institusi")) {
            dataInstitusi = (List<MKategoriNasabahPensiun>) mdata;
        } else if (dialog.equalsIgnoreCase("rekanan dm")) {
            dataRekananDM = (List<MRekananDM>) mdata;
        } else if (dialog.equalsIgnoreCase("tujuan")) {
            dataTujuan = (List<MTujuanPenggunaan>) mdata;
        } else if (dialog.equalsIgnoreCase("instansi")) {
            dataInstansi = (List<MListInstansi>) mdata;
        } else if (dialog.equalsIgnoreCase("kategori nasabah")) {
            dataKategNasabah = (List<MKategoriNasabahPensiun>) mdata;
        }

        DialogInputDataPipelineKmg dialogAddress = new DialogInputDataPipelineKmg();
        dialogAddress.show(fragmentManager, TAG);
        return dialogAddress;
    }

//    public static DialogRekananDM display(FragmentManager fragmentManager, String titleId, List<?> mdata, LoadDataInputPipelineListener mlistener, String dialog) {
//        title = titleId;
//        dataRekananDM = (List<MRekananDM>) mdata;
//        listener = mlistener;
//        dialog = dialog;
//        DialogRekananDM dialogAddress = new DialogRekananDM();
//        dialogAddress.show(fragmentManager, TAG);
//        return dialogAddress;
//    }
//
//    public static DialogKategoriNasabah display(FragmentManager fragmentManager, String titleId, List<?> mdata, LoadDataInputPipelineListener mlistener, String dialog) {
//        title = titleId;
//        dataKategNasabah = (List<MKategoriNasabahPensiun>) mdata;
//        listener = mlistener;
//        dialog = dialog;
//        DialogKategoriNasabah dialogAddress = new DialogKategoriNasabah();
//        dialogAddress.show(fragmentManager, TAG);
//        return dialogAddress;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.AppTheme_FullScreenDialog);
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
            dialog.getWindow().setWindowAnimations(R.style.AppTheme_Slide_Produk);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.dialog_produk, container, false);
        btn_close = (ImageView) view.findViewById(R.id.btn_close);
        tv_title = (TextView) view.findViewById(R.id.tv_title);
        rv_produk = (RecyclerView) view.findViewById(R.id.rv_produk);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        customToolbar();
        initializeProduct(dialog);
    }

    public void customToolbar(){
        backgroundStatusBar();
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        tv_title.setTextSize(13);
        tv_title.setText("Pilih "+title);
    }

    private void backgroundStatusBar(){
        Window window = getDialog().getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.setStatusBarColor(getResources().getColor(R.color.colorWhite));
        }
    }

    public void initializeProduct(String dialog){

        if (dialog.equalsIgnoreCase("program")) {
            dataKeyValueProgram = (List<MProgramKmg>) dataProgram;

            produkAdapaterProgram = new DialogInputDataPipelineKmg.ProgramAdapater(getContext(), dataKeyValueProgram, title, listener);
            rv_produk.setLayoutManager(new LinearLayoutManager(getContext()));
            rv_produk.setItemAnimator(new DefaultItemAnimator());
            rv_produk.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
            rv_produk.setAdapter(produkAdapaterProgram);
        } else if (dialog.equalsIgnoreCase("institusi")) {
            dataKeyValueInstitusi = (List<MKategoriNasabahPensiun>) dataInstitusi;

            produkAdapaterInstitusi = new DialogInputDataPipelineKmg.InstitusiAdapater(getContext(), dataKeyValueInstitusi, title, listener);
            rv_produk.setLayoutManager(new LinearLayoutManager(getContext()));
            rv_produk.setItemAnimator(new DefaultItemAnimator());
            rv_produk.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
            rv_produk.setAdapter(produkAdapaterInstitusi);
        } else if (dialog.equalsIgnoreCase("rekanan dm")) {
            dataKeyValueRekananDM = (List<MRekananDM>) dataRekananDM;

            produkAdapaterRekananDM = new DialogInputDataPipelineKmg.RekananDMAdapater(getContext(), dataKeyValueRekananDM, title, listener);
            rv_produk.setLayoutManager(new LinearLayoutManager(getContext()));
            rv_produk.setItemAnimator(new DefaultItemAnimator());
            rv_produk.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
            rv_produk.setAdapter(produkAdapaterRekananDM);
        }  else if (dialog.equalsIgnoreCase("tujuan")) {
            dataKeyValueTujuan = (List<MTujuanPenggunaan>) dataTujuan;

            produkAdapterTujuan = new DialogInputDataPipelineKmg.TujuanAdapter(getContext(), dataKeyValueTujuan, title, listener);
            rv_produk.setLayoutManager(new LinearLayoutManager(getContext()));
            rv_produk.setItemAnimator(new DefaultItemAnimator());
            rv_produk.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
            rv_produk.setAdapter(produkAdapterTujuan);
        } else if (dialog.equalsIgnoreCase("instansi")) {
            dataKeyValueInstansi = (List<MListInstansi>) dataInstansi;

            produkAdapterInstansi = new DialogInputDataPipelineKmg.InstansiAdapter(getContext(), dataKeyValueInstansi, title, listener);
            rv_produk.setLayoutManager(new LinearLayoutManager(getContext()));
            rv_produk.setItemAnimator(new DefaultItemAnimator());
            rv_produk.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
            rv_produk.setAdapter(produkAdapterInstansi);
        } else if (dialog.equalsIgnoreCase("kategori nasabah")) {
            dataKeyValueKategNasabah = (List<MKategoriNasabahPensiun>) dataKategNasabah;

            produkAdapterKategoriNasabah = new DialogInputDataPipelineKmg.KategoriNasabahAdapater(getContext(), dataKeyValueKategNasabah, title, listener);
            rv_produk.setLayoutManager(new LinearLayoutManager(getContext()));
            rv_produk.setItemAnimator(new DefaultItemAnimator());
            rv_produk.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
            rv_produk.setAdapter(produkAdapterKategoriNasabah);
        }

    }


    //CLASS ADAPTER PRODUCT
    public class ProgramAdapater extends RecyclerView.Adapter<ProgramAdapater.ProductViewHolder> {

        private Context context;
        private List<?> data;
        private String title;
        private LoadDataInputPipelineKmgListener listener;

        public ProgramAdapater(Context context, List<?> data, String title, LoadDataInputPipelineKmgListener listener) {
            this.context = context;
            this.data = (List<MProgramKmg>) data;
            this.title = title;
            this.listener = listener;
        }

        @NonNull
        @Override
        public ProgramAdapater.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            View view = layoutInflater.inflate(R.layout.item_product, parent, false);
            return new ProgramAdapater.ProductViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ProductViewHolder holder, final int position) {
            final MProgramKmg datas  = (MProgramKmg) data.get(position);

            holder.tv_title.setVisibility(View.GONE);

            holder.tv_product.setText(datas.getPROGRAM_NAME());
            holder.rl_product.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onSelectProgram(title, datas);
                    dismiss();
                }
            });
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        public class ProductViewHolder extends RecyclerView.ViewHolder {
            public TextView tv_title, tv_product;
            public RelativeLayout rl_product;
            public ProductViewHolder(View itemView) {
                super(itemView);
                tv_title = (TextView) itemView.findViewById(R.id.tv_title);
                tv_product = (TextView) itemView.findViewById(R.id.tv_product);
                rl_product = (RelativeLayout) itemView.findViewById(R.id.rl_product);
            }
        }
    }

    public class InstitusiAdapater extends RecyclerView.Adapter<InstitusiAdapater.ProductViewHolder> {

        private Context context;
        private List<?> data;
        private String title;
        private LoadDataInputPipelineKmgListener listener;

        public InstitusiAdapater(Context context, List<?> data, String title, LoadDataInputPipelineKmgListener listener) {
            this.context = context;
            this.data = (List<MKategoriNasabahPensiun>) data;
            this.title = title;
            this.listener = listener;
        }

        @NonNull
        @Override
        public InstitusiAdapater.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            View view = layoutInflater.inflate(R.layout.item_product, parent, false);
            return new InstitusiAdapater.ProductViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ProductViewHolder holder, final int position) {
            final MKategoriNasabahPensiun datas  = (MKategoriNasabahPensiun) data.get(position);

            holder.tv_title.setVisibility(View.GONE);

            holder.tv_product.setText(datas.getNama());
            holder.rl_product.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onSelectInstitusi(title, datas);
                    dismiss();
                }
            });
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        public class ProductViewHolder extends RecyclerView.ViewHolder {
            public TextView tv_title, tv_product;
            public RelativeLayout rl_product;
            public ProductViewHolder(View itemView) {
                super(itemView);
                tv_title = (TextView) itemView.findViewById(R.id.tv_title);
                tv_product = (TextView) itemView.findViewById(R.id.tv_product);
                rl_product = (RelativeLayout) itemView.findViewById(R.id.rl_product);
            }
        }
    }

    public class RekananDMAdapater extends RecyclerView.Adapter<RekananDMAdapater.ProductViewHolder> {

        private Context context;
        private List<?> data;
        private String title;
        private LoadDataInputPipelineKmgListener listener;

        public RekananDMAdapater(Context context, List<?> data, String title, LoadDataInputPipelineKmgListener listener) {
            this.context = context;
            this.data = (List<MRekananDM>) data;
            this.title = title;
            this.listener = listener;
        }

        @NonNull
        @Override
        public RekananDMAdapater.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            View view = layoutInflater.inflate(R.layout.item_product, parent, false);
            return new RekananDMAdapater.ProductViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ProductViewHolder holder, final int position) {
            final MRekananDM datas  = (MRekananDM) data.get(position);

            holder.tv_title.setVisibility(View.GONE);

            holder.tv_product.setText(datas.getREKANAN_DIRECT_MARKETING());
            holder.rl_product.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onSelectRekananDM(title, datas);
                    dismiss();
                }
            });
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        public class ProductViewHolder extends RecyclerView.ViewHolder {
            public TextView tv_title, tv_product;
            public RelativeLayout rl_product;
            public ProductViewHolder(View itemView) {
                super(itemView);
                tv_title = (TextView) itemView.findViewById(R.id.tv_title);
                tv_product = (TextView) itemView.findViewById(R.id.tv_product);
                rl_product = (RelativeLayout) itemView.findViewById(R.id.rl_product);
            }
        }
    }

    public class TujuanAdapter extends RecyclerView.Adapter<TujuanAdapter.ProductViewHolder> {

        private Context context;
        private List<?> data;
        private String title;
        private LoadDataInputPipelineKmgListener listener;

        public TujuanAdapter(Context context, List<?> data, String title, LoadDataInputPipelineKmgListener listener) {
            this.context = context;
            this.data = (List<MTujuanPenggunaan>) data;
            this.title = title;
            this.listener = listener;
        }

        @NonNull
        @Override
        public TujuanAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            View view = layoutInflater.inflate(R.layout.item_product, parent, false);
            return new TujuanAdapter.ProductViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ProductViewHolder holder, final int position) {
            final MTujuanPenggunaan datas  = (MTujuanPenggunaan) data.get(position);

            holder.tv_title.setVisibility(View.GONE);

            holder.tv_product.setText(datas.getDESC1());
            holder.rl_product.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onSelectTujuan(title, datas);
                    dismiss();
                }
            });
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        public class ProductViewHolder extends RecyclerView.ViewHolder {
            public TextView tv_title, tv_product;
            public RelativeLayout rl_product;
            public ProductViewHolder(View itemView) {
                super(itemView);
                tv_title = (TextView) itemView.findViewById(R.id.tv_title);
                tv_product = (TextView) itemView.findViewById(R.id.tv_product);
                rl_product = (RelativeLayout) itemView.findViewById(R.id.rl_product);
            }
        }
    }

    public class InstansiAdapter extends RecyclerView.Adapter<InstansiAdapter.ProductViewHolder> {

        private Context context;
        private List<?> data;
        private String title;
        private LoadDataInputPipelineKmgListener listener;

        public InstansiAdapter(Context context, List<?> data, String title, LoadDataInputPipelineKmgListener listener) {
            this.context = context;
            this.data = (List<MTujuanPenggunaan>) data;
            this.title = title;
            this.listener = listener;
        }

        @NonNull
        @Override
        public InstansiAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            View view = layoutInflater.inflate(R.layout.item_product, parent, false);
            return new InstansiAdapter.ProductViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ProductViewHolder holder, final int position) {
            final MListInstansi datas  = (MListInstansi) data.get(position);

            holder.tv_title.setVisibility(View.GONE);

            holder.tv_product.setText(datas.getNAMA());
            holder.rl_product.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onSelectInstansi(title, datas);
                    dismiss();
                }
            });
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        public class ProductViewHolder extends RecyclerView.ViewHolder {
            public TextView tv_title, tv_product;
            public RelativeLayout rl_product;
            public ProductViewHolder(View itemView) {
                super(itemView);
                tv_title = (TextView) itemView.findViewById(R.id.tv_title);
                tv_product = (TextView) itemView.findViewById(R.id.tv_product);
                rl_product = (RelativeLayout) itemView.findViewById(R.id.rl_product);
            }
        }
    }

    public class KategoriNasabahAdapater extends RecyclerView.Adapter<KategoriNasabahAdapater.ProductViewHolder> {

        private Context context;
        private List<?> data;
        private String title;
        private LoadDataInputPipelineKmgListener listener;

        public KategoriNasabahAdapater(Context context, List<?> data, String title, LoadDataInputPipelineKmgListener listener) {
            this.context = context;
            this.data = (List<MKategoriNasabahPensiun>) data;
            this.title = title;
            this.listener = listener;
        }

        @NonNull
        @Override
        public KategoriNasabahAdapater.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            View view = layoutInflater.inflate(R.layout.item_product, parent, false);
            return new KategoriNasabahAdapater.ProductViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ProductViewHolder holder, final int position) {
            final MKategoriNasabahPensiun datas  = (MKategoriNasabahPensiun) data.get(position);

            holder.tv_title.setVisibility(View.GONE);

            holder.tv_product.setText(datas.getNama());
            holder.rl_product.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onSelectKategNasabah(title, datas);
                    dismiss();
                }
            });
        }

        @Override
        public int getItemCount() {
            return data.size();
        }

        public class ProductViewHolder extends RecyclerView.ViewHolder {
            public TextView tv_title, tv_product;
            public RelativeLayout rl_product;
            public ProductViewHolder(View itemView) {
                super(itemView);
                tv_title = (TextView) itemView.findViewById(R.id.tv_title);
                tv_product = (TextView) itemView.findViewById(R.id.tv_product);
                rl_product = (RelativeLayout) itemView.findViewById(R.id.rl_product);
            }
        }
    }
}

