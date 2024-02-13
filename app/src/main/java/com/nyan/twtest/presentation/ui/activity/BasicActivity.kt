package com.nyan.twtest.presentation.ui.activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.nyan.twtest.R
import com.nyan.twtest.databinding.ActivityBasicBinding
import com.nyan.twtest.presentation.base.BaseActivity

abstract class BasicActivity<F: Fragment>: BaseActivity<ActivityBasicBinding>() {

    lateinit var fragment: F
        private set

    @Suppress("UNCHECKED_CAST")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindContentView(ActivityBasicBinding::inflate)

        toolbar = bind.toolbar
        setSupportActionBar(toolbar)
        toolbar?.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        savedInstanceState?.let {
            fragment = supportFragmentManager.findFragmentByTag("baseFragment") as F
        } ?: let {
            fragment = initFragment()
            supportFragmentManager.beginTransaction().add(R.id.frameLayout, fragment, "baseFragment").commit()
        }
    }

    abstract fun initFragment(): F

}