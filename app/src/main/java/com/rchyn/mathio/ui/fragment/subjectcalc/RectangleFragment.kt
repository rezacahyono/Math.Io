package com.rchyn.mathio.ui.fragment.subjectcalc


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.rchyn.mathio.R
import com.rchyn.mathio.databinding.FragmentRectangleBinding
import com.rchyn.mathio.databinding.MainToolbarBinding
import com.rchyn.mathio.util.Util.setHideSoftKeyboard
import com.rchyn.mathio.viewmodel.MainViewModel


class RectangleFragment : Fragment() {

    private val viewModel: MainViewModel by activityViewModels()

    private var _binding: FragmentRectangleBinding? = null
    private lateinit var bindingToolbar: MainToolbarBinding
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRectangleBinding.inflate(layoutInflater, container, false)
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
            title.text = resources.getString(R.string.rectangle_title)
        }

        viewModel.areaOfRectangle.observe(viewLifecycleOwner) {
            if (it != null) {
                binding.tvResult.visibility = View.VISIBLE
                binding.tvResult.text = resources.getString(R.string.result, it)
            } else {
                binding.tvResult.visibility = View.GONE
            }
        }

        binding.btnCalculate.setOnClickListener {
            if (entryValidated()) {
                viewModel.calculateRectangle(
                    binding.edtLength.text.toString(),
                    binding.edtWidth.text.toString()
                )
            }
            setHideSoftKeyboard(requireActivity())
        }

    }

    private fun entryValidated(): Boolean {
        val l = binding.edtLength.text.toString()
        val w = binding.edtWidth.text.toString()
        return when {
            l.isEmpty() -> {
                binding.layoutEdtLength.isErrorEnabled = true
                binding.layoutEdtLength.error = getString(R.string.erro_message)
                false

            }
            w.isEmpty() -> {
                binding.layoutEdtWidth.isErrorEnabled = true
                binding.layoutEdtWidth.error = getString(R.string.erro_message)
                false
            }
            else -> {
                binding.layoutEdtLength.isErrorEnabled = false
                binding.layoutEdtLength.error = null
                binding.layoutEdtWidth.isErrorEnabled = false
                binding.layoutEdtWidth.error = null
                true
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}