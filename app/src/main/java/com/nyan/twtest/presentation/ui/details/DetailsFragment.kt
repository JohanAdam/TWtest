package com.nyan.twtest.presentation.ui.details

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.nyan.twtest.R
import com.nyan.twtest.databinding.FragmentDetailsBinding
import com.nyan.twtest.presentation.base.BaseFragment
import com.nyan.twtest.presentation.ui.adapter.DetailsAdapter
import com.nyan.twtest.presentation.utils.EventObserver
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment: BaseFragment<FragmentDetailsBinding>({
        inflater,
        container,
        attachToParent ->
    FragmentDetailsBinding.inflate(inflater, container, attachToParent)
}) {

    companion object {
        private const val ARG_TEST_ID = "test_id"

        fun newInstance(id: Int) = DetailsFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG_TEST_ID, id)
            }
        }
    }

    private val viewModel: DetailViewModel by viewModels()

    private lateinit var detailsAdapter: DetailsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bActivity?.setStatusBarColor(ContextCompat.getColor(requireContext(), R.color.brown))

//        val textData = arguments?.getString(ARG_TEST_TEXT, "-")
//        bind.tvText.text = textData


//        viewModel.msg.observe(viewLifecycleOwner, EventObserver {
//            bind.tvText.text = it
//        })

        initView()

        viewModel.getDetails(1)
        setObservers()
    }

    private fun initView() {

        detailsAdapter = DetailsAdapter(emptyList())
        bind.rv.apply {
            this.adapter = detailsAdapter
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

        viewModel.result.observe(viewLifecycleOwner, EventObserver {
            bind.tvText.text = it.name

            it.types?.let { typeList ->
                detailsAdapter.updateData(typeList)
            }
        })

    }

}