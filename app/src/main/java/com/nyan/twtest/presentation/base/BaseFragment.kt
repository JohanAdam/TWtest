package com.nyan.twtest.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<T : ViewBinding>(
    private val inflate: (LayoutInflater, ViewGroup?, Boolean) -> T
) : Fragment() {

    private var _bind: T? = null
    protected val bind get() = _bind!!
    val bActivity: BaseActivity<*>?
        get() = activity as? BaseActivity<*>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _bind = inflate(inflater, container, false)
        return bind.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Set the binding instance to null to release the references
        _bind = null
    }

    fun showLoading() {
        bActivity?.showLoading()
    }

    fun hideLoading() {
        bActivity?.hideLoading()
    }
}