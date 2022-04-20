package com.rchyn.mathio.ui.fragment.subjectcalc

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.rchyn.mathio.R
import com.rchyn.mathio.databinding.FragmentRhombusBinding
import com.rchyn.mathio.databinding.MainToolbarBinding
import com.rchyn.mathio.util.Util.setHideSoftKeyboard
import com.rchyn.mathio.viewmodel.MainViewModel

class RhombusFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()

    private var _binding: FragmentRhombusBinding? = null
    private lateinit var bindingToolbar: MainToolbarBinding
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRhombusBinding.inflate(layoutInflater, container, false)
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
            title.text = getString(R.string.rhombus_title)
        }


        viewModel.areaOfRhombus.observe(viewLifecycleOwner) {
            if (it != null) {
                binding.tvResult.visibility = View.VISIBLE
                binding.tvResult.text = resources.getString(R.string.result, it)
            } else {
                binding.tvResult.visibility = View.GONE
            }
        }

        binding.btnCalculate.setOnClickListener {
            if (entryValidated()) {
                viewModel.calculateRhombus(
                    binding.edtDiagonalOne.text.toString(),
                    binding.edtDiagonalTwo.text.toString()
                )
            }
            setHideSoftKeyboard(requireActivity())
        }
    }

    private fun entryValidated(): Boolean {
        val d1 = binding.edtDiagonalOne.text.toString()
        val d2 = binding.edtDiagonalTwo.text.toString()
        return when {
            d1.isEmpty() -> {
                binding.layoutEdtDiagonalOne.isErrorEnabled = true
                binding.layoutEdtDiagonalOne.error = getString(R.string.erro_message)
                false

            }
            d2.isEmpty() -> {
                binding.layoutEdtDiagonalTwo.isErrorEnabled = true
                binding.layoutEdtDiagonalTwo.error = getString(R.string.erro_message)
                false
            }
            else -> {
                binding.layoutEdtDiagonalOne.isErrorEnabled = false
                binding.layoutEdtDiagonalOne.error = null
                binding.layoutEdtDiagonalTwo.isErrorEnabled = false
                binding.layoutEdtDiagonalTwo.error = null
                true
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}