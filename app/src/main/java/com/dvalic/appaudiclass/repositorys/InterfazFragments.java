package com.dvalic.appaudiclass.repositorys;

import android.os.Bundle;

import kotlin.Unit;
import kotlin.reflect.KVisibility;

public interface InterfazFragments {

    //Todo MainMenu
    void showMainMenufragment();
    void showModelsfragment();


    //Todo Utility
    void showBars(Boolean visibility);
    void showWebPage(String url);
    void showSnackbar(String url, int type);
    void showWebView(Bundle bundle);
    void showPdf(Bundle bundle);
    void showDialog(String title,String description,String textPositiveButton,String textNegativeButton);
}
