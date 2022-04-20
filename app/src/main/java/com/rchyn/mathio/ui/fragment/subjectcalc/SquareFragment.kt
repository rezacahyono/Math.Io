package com.rchyn.mathio.ui.fragment.subjectcalc

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.rchyn.mathio.R
import com.rchyn.mathio.databinding.FragmentSquareBinding
import com.rchyn.mathio.databinding.MainToolbarBinding
import com.rchyn.mathio.util.Util.setHideSoftKeyboard
import com.rchyn.mathio.viewmodel.MainViewModel


class SquareFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()

    private var _binding: FragmentSquareBinding? = null
    private lateinit var bindingToolbar: MainToolbarBinding
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSquareBinding.inflate(layoutInflater, container, false)
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
            title.text = getString(R.string.square_title)
        }

        viewModel.areaOfSquare.observe(viewLifecycleOwner) {
            if (it != null) {
                binding.tvResult.visibility = View.VISIBLE
                binding.tvResult.text = resources.getString(R.string.result, it)
            } else {
                binding.tvResult.visibility = View.GONE
            }
        }

        binding.btnCalculate.setOnClickListener {
            if (entryValidated()) {
                viewModel.calculateSquare(
                    binding.edtSide.text.toString()
                )
            }
            setHideSoftKeyboard(requireActivity())
        }

    }

    private fun entryValidated(): Boolean {
        val s = binding.edtSide.text.toString()
        if (s.isEmpty()) {
            binding.layoutEdtSide.isErrorEnabled = true
            binding.layoutEdtSide.error = getString(R.string.erro_message)
            return false
        }
        binding.layoutEdtSide.isErrorEnabled = false
        binding.layoutEdtSide.error = null
        return true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}