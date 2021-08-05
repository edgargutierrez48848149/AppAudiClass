package com.dvalic.appaudiclass.ui.main.fragments.us.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dvalic.appaudiclass.ui.main.fragments.us.UsAgencyFragment
import com.dvalic.appaudiclass.ui.main.fragments.us.UsAppFragment
import com.dvalic.appaudiclass.ui.main.fragments.us.UsCompanyFragment

class ViewPagerUs(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val item = 3

    override fun getItemCount(): Int {
        return item
    }

    override fun createFragment(position: Int): Fragment {
        var fragment= Fragment()
        when (position) {
            0 -> fragment = UsCompanyFragment()
            1 -> fragment = UsAgencyFragment()
            2 -> fragment = UsAppFragment()
        }
        return fragment
    }
}