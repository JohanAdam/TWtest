package com.nyan.twtest.presentation.ui.dashboard

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.nyan.twtest.R
import com.nyan.twtest.databinding.FragmentDashboardBinding
import com.nyan.twtest.presentation.base.BaseFragment
import com.nyan.twtest.presentation.ui.adapter.HeroesAdapter
import com.nyan.twtest.presentation.ui.details.DetailsFragment
import com.nyan.twtest.presentation.utils.EventObserver
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

    private lateinit var heroesAdapter: HeroesAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        setObservers()
        viewModel.getHeroes()
    }

    private fun initViews() {
        bActivity?.setToolbarTitle(ContextCompat.getString(requireContext(), R.string.app_name), false)

        heroesAdapter = HeroesAdapter(emptyList()) {
            bActivity?.replaceFragment(
                fragment = DetailsFragment.newInstance(it.id),
                addToBackStack = true
            )
        }

        bind.rvHeroes.apply {
            this.adapter = heroesAdapter
            this.layoutManager = LinearLayoutManager(requireContext())
            this.addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
        }
    }

    private fun setObservers() {

        viewModel.loading.observe(viewLifecycleOwner, EventObserver { isLoad ->
            if (isLoad) showLoading() else hideLoading()
        })

        viewModel.msg.observe(viewLifecycleOwner, EventObserver {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })

        viewModel.heroList.observe(viewLifecycleOwner, EventObserver {
            it?.let {
                heroesAdapter.updateData(it)
            }
        })

    }


}