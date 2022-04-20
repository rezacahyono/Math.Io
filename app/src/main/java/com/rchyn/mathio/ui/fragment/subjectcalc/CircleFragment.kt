package com.rchyn.mathio.ui.fragment.subjectcalc

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.rchyn.mathio.R
import com.rchyn.mathio.databinding.FragmentCircleBinding
import com.rchyn.mathio.databinding.MainToolbarBinding
import com.rchyn.mathio.util.Util.setHideSoftKeyboard
import com.rchyn.mathio.viewmodel.MainViewModel


class CircleFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()

    private var _binding: FragmentCircleBinding? = null
    private lateinit var bindingToolbar: MainToolbarBinding
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCircleBinding.inflate(layoutInflater, container, false)
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
            title.text = getString(R.string.circle_title)
        }

        viewModel.areaOfCircle.observe(viewLifecycleOwner) {
            if (it != null) {
                binding.tvResult.visibility = View.VISIBLE
                binding.tvResult.text = resources.getString(R.string.result, it)
            } else {
                binding.tvResult.visibility = View.GONE
            }
        }

        binding.btnCalculate.setOnClickListener {
            if (entryValidated()) {
                viewModel.calculateCircle(
                    binding.edtRadius.text.toString()
                )
            }
            setHideSoftKeyboard(requireActivity())

        }

    }

    private fun entryValidated(): Boolean {
        val r = binding.edtRadius.text.toString()
        if (r.isEmpty()) {
            binding.layoutEdtRadius.isErrorEnabled = true
            binding.layoutEdtRadius.error = getString(R.string.erro_message)
            return false
        }
        binding.layoutEdtRadius.isErrorEnabled = false
        binding.layoutEdtRadius.error = null
        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}