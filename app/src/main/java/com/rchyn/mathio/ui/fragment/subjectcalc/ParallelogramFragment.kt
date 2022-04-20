package com.rchyn.mathio.ui.fragment.subjectcalc

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.rchyn.mathio.R
import com.rchyn.mathio.databinding.FragmentParallelogramBinding
import com.rchyn.mathio.databinding.MainToolbarBinding
import com.rchyn.mathio.util.Util.setHideSoftKeyboard
import com.rchyn.mathio.viewmodel.MainViewModel


class ParallelogramFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()

    private var _binding: FragmentParallelogramBinding? = null
    private lateinit var bindingToolbar: MainToolbarBinding
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentParallelogramBinding.inflate(layoutInflater, container, false)
        bindingToolbar = binding.mainToolbar
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindingToolbar.apply {
            btnBack.setOnClickListener {
                findNavController().navigateUp()
                viewModel.resetCalculate()
            }
            title.text = getString(R.string.parallelogram_title)
        }

        viewModel.areaOfParallelogram.observe(viewLifecycleOwner) {
            if (it != null) {
                binding.tvResult.visibility = View.VISIBLE
                binding.tvResult.text = resources.getString(R.string.result, it)
            } else {
                binding.tvResult.visibility = View.GONE
            }
        }

        binding.btnCalculate.setOnClickListener {
            if (entryValidated()) {
                viewModel.calculateParallelogram(
                    binding.edtBase.text.toString(),
                    binding.edtHeight.text.toString()
                )
            }
            setHideSoftKeyboard(requireActivity())
        }
    }

    private fun entryValidated(): Boolean {
        val b = binding.edtBase.text.toString()
        val h = binding.edtHeight.text.toString()
        return when {
            b.isEmpty() -> {
                binding.layoutEdtBase.isErrorEnabled = true
                binding.layoutEdtBase.error = getString(R.string.erro_message)
                false
            }
            h.isEmpty() -> {
                binding.layoutEdtHeight.isErrorEnabled = true
                binding.layoutEdtHeight.error = getString(R.string.erro_message)
                false
            }
            else -> {
                binding.layoutEdtBase.isErrorEnabled = false
                binding.layoutEdtBase.error = null
                true
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}