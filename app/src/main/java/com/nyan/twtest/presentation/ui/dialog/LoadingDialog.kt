package com.nyan.twtest.presentation.ui.dialog

import android.view.LayoutInflater
import android.view.ViewGroup
import com.nyan.twtest.databinding.DialogLoadingBinding
import com.nyan.twtest.presentation.base.BaseDialogFragment

class LoadingDialog : BaseDialogFragment<DialogLoadingBinding>() {

    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): DialogLoadingBinding {
        return DialogLoadingBinding.inflate(inflater, container, false)
    }

}
