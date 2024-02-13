package com.nyan.twtest.presentation.ui.details

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.nyan.twtest.R
import com.nyan.twtest.databinding.FragmentDetailsBinding
import com.nyan.twtest.presentation.base.BaseFragment
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
        private const val ARG_ID = "hero_id"

        fun newInstance(id: Int) = DetailsFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG_ID, id)
            }
        }
    }

    private val viewModel: DetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()

        viewModel.getDetails(arguments?.getInt(ARG_ID))
        setObservers()
    }

    private fun initView() { }

    private fun setObservers() {
        viewModel.loading.observe(viewLifecycleOwner, EventObserver { isLoad ->
            if (isLoad) showLoading() else hideLoading()
        })

        viewModel.msg.observe(viewLifecycleOwner, EventObserver {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })

        viewModel.result.observe(viewLifecycleOwner, EventObserver {
            it.photo?.let { drawableRes ->
                bind.ivProfile.setImageDrawable(ContextCompat.getDrawable(requireContext(), drawableRes))
            }
        })

    }

}