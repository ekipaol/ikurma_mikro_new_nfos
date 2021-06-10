package com.application.bris.ikurma.config.menu;

import android.content.Context;

import com.application.bris.ikurma.R;
import com.application.bris.ikurma.model.menu.ListViewMenu;
import com.application.bris.ikurma.model.menu.ListViewSubmenuHotprospek;

import java.util.List;

/**
 * Created by PID on 28/04/2019.
 */

public class Menu {

    public static String MENU_ID = "idMenu";
    public static String MENU_ROOT = "rootMenu";

    /*************************** Main Menu AO***************************/
    public static void mainMenuAO(Context context, List<ListViewMenu> menu) {
        menu.add(new ListViewMenu(R.drawable.ico_pipeline, context.getString(R.string.menu_pipeline), 0, 0, 0, 0));
        menu.add(new ListViewMenu(R.drawable.ico_hotprospek, context.getString(R.string.menu_hotprospek), 0, 0, 0, 0));
        menu.add(new ListViewMenu(R.drawable.ico_approved, context.getString(R.string.menu_approved), 0, 0, 0, 0));;
        menu.add(new ListViewMenu(R.drawable.ico_rejected, context.getString(R.string.menu_rejected), 0,0,0, 0));
        menu.add(new ListViewMenu(R.drawable.ic_generalusericon, "Monitoring", 0, 0, 0, 0));
        menu.add(new ListViewMenu(R.drawable.ic_logout_front, "Logout", 0,0,0, 0));
    }

    public static void mainMenuAONpf(Context context, List<ListViewMenu> menu) {
//        menu.add(new ListViewMenu(R.drawable.ico_pipeline, context.getString(R.string.menu_pipeline), 0, 0, 0, 0));
//        menu.add(new ListViewMenu(R.drawable.ico_hotprospek, context.getString(R.string.menu_hotprospek), 0, 0, 0, 0));
//        menu.add(new ListViewMenu(R.drawable.ico_approved, context.getString(R.string.menu_approved), 0, 0, 0, 0));;
//        menu.add(new ListViewMenu(R.drawable.ico_rejected, context.getString(R.string.menu_rejected), 0,0,0, 0));
        menu.add(new ListViewMenu(R.drawable.ic_generalusericon, "Monitoring", 0, 0, 0, 0));
        menu.add(new ListViewMenu(R.drawable.ic_logout_front, "Logout", 0,0,0, 0));
    }

    /************************** Sub Menu Hotprospek ********************/
    public static void SubmenuHotprospek(Context context, List<ListViewSubmenuHotprospek> menu){
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_data_lengkap, context.getString(R.string.submenu_hotprospek_datalengkap)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_prescreening, context.getString(R.string.submenu_hotprospek_prescreening)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_economysector, context.getString(R.string.submenu_hotprospek_sektorekonomi)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_lkn, context.getString(R.string.submenu_hotprospek_lkn)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_rpc, context.getString(R.string.submenu_hotprospek_rpc)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_agunan, context.getString(R.string.submenu_hotprospek_agunan)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_documentcomplete, context.getString(R.string.submenu_hotprospek_kelengkapandokumen)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_scoring, context.getString(R.string.submenu_hotprospek_scoring)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_history, context.getString(R.string.submenu_hotprospek_history)));
    }

    public static void SubmenuHotprospekKmg(Context context, List<ListViewSubmenuHotprospek> menu){
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_data_lengkap, context.getString(R.string.submenu_hotprospek_datalengkap)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_prescreening, context.getString(R.string.submenu_hotprospek_prescreening)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_economysector, context.getString(R.string.submenu_hotprospek_sektorekonomi)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_rpc, "Data Finansial"));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_agunan, context.getString(R.string.submenu_hotprospek_agunan)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_scoring, context.getString(R.string.submenu_hotprospek_scoring)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_documentcomplete, context.getString(R.string.submenu_hotprospek_kelengkapandokumen)));
        menu.add(new ListViewSubmenuHotprospek(R.drawable.ic_history, context.getString(R.string.submenu_hotprospek_history)));
    }
}
