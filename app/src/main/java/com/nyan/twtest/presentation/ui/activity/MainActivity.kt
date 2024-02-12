package com.nyan.twtest.presentation.ui.activity

import com.nyan.twtest.presentation.ui.dashboard.DashboardFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity: BasicActivity<DashboardFragment>() {

    override fun initFragment(): DashboardFragment {
        return DashboardFragment.newInstance()
    }

}