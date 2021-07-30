package com.dvalic.appaudiclass.repositorys

import android.os.Bundle

interface InterfazFragments {

    //Todo MainMenu
    fun showMainMenufragment()
    fun showModelsfragment()
    fun showVersionsFragment(bundle: Bundle?)

    //Todo Utility
    fun showBars(visibility: Boolean)
    fun showWebPage(url: String?)
    fun showSnackbar(url: String?, type: Int)
    fun showWebView(bundle: Bundle?)
    fun showPdf(bundle: Bundle?)
    fun showDialog(
        title: String?,
        description: String?,
        textPositiveButton: String?,
        textNegativeButton: String?,
        actionPositive: Unit?,
        actionNegative: Unit?
    )
}