package com.nyan.twtest.presentation.ui.dashboard

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.nyan.twtest.R
import com.nyan.twtest.databinding.FragmentDashboardBinding
import com.nyan.twtest.presentation.base.BaseFragment
import com.nyan.twtest.presentation.ui.details.DetailsFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment: BaseFragment<FragmentDashboardBinding>({
        inflater,
        container,
        attachToParent ->
    FragmentDashboardBinding.inflate(inflater, container, attachToParent)
}) {

    companion object {
        fun newInstance() = DashboardFragment()
    }

    private val viewModel: DashboardViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bActivity?.setStatusBarColor(ContextCompat.getColor(requireContext(), R.color.teal))

        bind.btnNext.setOnClickListener {
            viewModel.openNext()
            bActivity?.replaceFragment(
                fragment = DetailsFragment.newInstance(1),
                addToBackStack = true
            )
        }
    }



}