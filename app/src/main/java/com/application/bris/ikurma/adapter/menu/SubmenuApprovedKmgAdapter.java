package com.application.bris.ikurma.adapter.menu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.application.bris.ikurma.R;
import com.application.bris.ikurma.listener.menu.MenuClickListener;
import com.application.bris.ikurma.model.menu.ListViewSubmenuHotprospek;
import com.application.bris.ikurma.page_aom.model.hotprospekKmg;

import java.util.List;

public class SubmenuApprovedKmgAdapter extends RecyclerView.Adapter<SubmenuApprovedKmgAdapter.MenuViewHolder> {
    private List<ListViewSubmenuHotprospek> listMenu;
    private hotprospekKmg data;
    private Context context;
    private MenuClickListener mMenuClickListener;
    private int flagPrescreening, flagDataLengkap, flagSektorEkonomi, flagLKN, flagRPC, flagAgunan, flagKelengkapanData, flagScoring,flagFinansial;

    public SubmenuApprovedKmgAdapter(Context context, List<ListViewSubmenuHotprospek> menu, hotprospekKmg mdata, MenuClickListener menuClickListener) {
        this.context = context;
        this.listMenu = menu;
        this.data = mdata;
        this.mMenuClickListener = menuClickListener;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_submenu_hotprospek, parent, false);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubmenuApprovedKmgAdapter.MenuViewHolder holder, final int position) {
        holder.iv_iconmenu.setImageResource(listMenu.get(position).getIcon());
        holder.tv_titlemenu.setText(listMenu.get(position).getTitle());

        holder.rl_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMenuClickListener.onMenuClick(listMenu.get(position).getTitle());
            }
        });

        flagPrescreening = data.getFlag_prescreening();
        flagDataLengkap = data.getFlag_data_lengkap();
        flagSektorEkonomi = data.getFlag_data_pembiayaan();
        flagFinansial=data.getFlag_finansial();
        flagAgunan = data.getFlag_agunan();
        flagKelengkapanData = data.getFlag_dokumen();
        flagScoring = data.getFlag_scoring();

        if (flagDataLengkap == 1 && position == 0){
            holder.iv_complete.setVisibility(View.VISIBLE);
        }

        if (flagPrescreening == 1 && position == 1){
            holder.iv_complete.setVisibility(View.VISIBLE);
        }

        if (flagSektorEkonomi == 1 && position == 2){
            holder.iv_complete.setVisibility(View.VISIBLE);
        }

        if (flagFinansial == 1 && position == 3){
            holder.iv_complete.setVisibility(View.VISIBLE);
        }

        if (flagAgunan == 1 && position == 4){
            holder.iv_complete.setVisibility(View.VISIBLE);
        }

        if (flagScoring == 1 && position == 5){
            holder.iv_complete.setVisibility(View.VISIBLE);
        }

        if (flagKelengkapanData == 1 && position == 6){
            holder.iv_complete.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return listMenu.size();
    }

    public class MenuViewHolder extends RecyclerView.ViewHolder {
        private RelativeLayout rl_menu;
        private ImageView iv_iconmenu, iv_complete;
        private TextView tv_titlemenu;
        private TextView tv_opsi;

        public MenuViewHolder(View itemView) {
            super(itemView);
            rl_menu = (RelativeLayout) itemView.findViewById(R.id.rl_menu);
            iv_iconmenu = (ImageView) itemView.findViewById(R.id.iv_iconmenu);
            tv_titlemenu = (TextView) itemView.findViewById(R.id.tv_titlemenu);
            iv_complete = (ImageView) itemView.findViewById(R.id.iv_complete);
            tv_opsi = (TextView) itemView.findViewById(R.id.tv_opsi);
        }

    }
}