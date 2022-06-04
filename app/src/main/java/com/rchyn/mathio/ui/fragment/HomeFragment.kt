package com.rchyn.mathio.ui.fragment

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.rchyn.mathio.R
import com.rchyn.mathio.adapter.ItemSubjectAdapter
import com.rchyn.mathio.data.Datasource
import com.rchyn.mathio.databinding.CustomDialogBinding
import com.rchyn.mathio.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var adapter: ItemSubjectAdapter

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setRecyclerView()
    }

    private fun setRecyclerView() {
        val dataset = Datasource.getDatasetSubject()
        adapter = ItemSubjectAdapter(dataset)
        binding.apply {
            recyclerSubject.layoutManager = GridLayoutManager(requireContext(),2)
            recyclerSubject.adapter = adapter
        }
        adapter.setOnItemCallbackAdapter(object : ItemSubjectAdapter.OnItemCallbackListener {
            override fun onClicked(position: Int) {
                setCustomAlertDialog(position)
            }
        })
    }

    private fun navigate(position: Int) {
        when (position) {
            0 -> findNavController().navigate(R.id.action_homeFragment_to_rectangleFragment)
            1 -> findNavController().navigate(R.id.action_homeFragment_to_squareFragment)
            2 -> findNavController().navigate(R.id.action_homeFragment_to_triangleFragment)
            3 -> findNavController().navigate(R.id.action_homeFragment_to_circleFragment)
            4 -> findNavController().navigate(R.id.action_homeFragment_to_parallelogramFragment)
            5 -> findNavController().navigate(R.id.action_homeFragment_to_rhombusFragment)
        }
    }

    private fun setCustomAlertDialog(position: Int) {
        val builder = AlertDialog.Builder(requireContext())
        val bindingDialog = CustomDialogBinding.inflate(layoutInflater)
        val viewDialog = bindingDialog.root

        builder.setView(viewDialog)

        val dialog = builder.create()
        dialog.create()

        val dataset = Datasource.getDatasetSubject()
        bindingDialog.apply {
            tvMessage.text = getString(R.string.message_alert, getString(dataset[position].title))
            btnYes.setOnClickListener {
                navigate(position)
                dialog.dismiss()
            }
            btnNo.setOnClickListener {
                dialog.dismiss()
            }
        }

        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}