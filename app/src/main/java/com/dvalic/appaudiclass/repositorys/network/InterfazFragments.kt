package com.dvalic.appaudiclass.repositorys.network

import android.os.Bundle

interface InterfazFragments {

    //Todo MainMenu
    fun showMainMenufragment()
    fun showModelsfragment()
    fun showVersionsFragment(bundle: Bundle?)
    fun showAcount()

    //Todo Utility
    fun checkUserExistence()
    fun logOut()
    fun logIn()
    fun showBars(visibility: Boolean)
    fun showWebPage(url: String?)
    fun showSnackbar(url: String?, type: Int)
    fun showWebView(bundle: Bundle?)
    fun showPdf(bundle: Bundle?)
    fun showDialog(title: String?, description: String?, textPositiveButton: String?, textNegativeButton: String?, actionPositive: InterfazDialogAction?, actionNegative: InterfazDialogAction?)
}