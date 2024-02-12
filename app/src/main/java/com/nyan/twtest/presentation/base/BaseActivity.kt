package com.nyan.twtest.presentation.base

import android.view.LayoutInflater
import android.view.View
import androidx.annotation.ColorInt
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.viewbinding.ViewBinding
import com.nyan.twtest.R
import com.nyan.twtest.presentation.ui.dialog.LoadingDialog

abstract class BaseActivity<T : ViewBinding> : AppCompatActivity() {

    protected lateinit var bind: T
    private var loadingDialog: LoadingDialog? = null
    private var frameLayout: Int? = null

    fun bindContentView(inflate: (LayoutInflater) -> T) {
        bind = inflate(layoutInflater)
        setContentView(bind.root)
    }

    fun showLoading() {
        if (loadingDialog == null) {
            loadingDialog = LoadingDialog()
        }
        loadingDialog?.show(supportFragmentManager, LoadingDialog::class.java.simpleName)
    }

    fun hideLoading() {
        loadingDialog?.dismiss()
    }

    fun setStatusBarColor(@ColorInt color: Int) {
        window.statusBarColor = color
    }

    fun replaceFragment(fragment: Fragment, addToBackStack: Boolean) {
        val frameLayoutId = R.id.frameLayout

        bind.root.findViewById<View>(frameLayoutId)?.let {
            val fm: FragmentManager = supportFragmentManager
            val ft = fm.beginTransaction()

            ft.replace(frameLayoutId, fragment, fragment.javaClass.canonicalName)
            if (addToBackStack) {
                ft.addToBackStack(fragment.javaClass.simpleName)
            }
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            ft.commit()
        } ?: run {
            throw Exception("Activity must have FrameLayout with id frame_layout")
        }
    }
}