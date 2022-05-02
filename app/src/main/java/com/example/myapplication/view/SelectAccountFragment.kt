package com.example.myapplication.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentSelectAccountBinding
import com.example.myapplication.viewModel.ViewModelAccount


class SelectAccountFragment : Fragment() {
    private val viewModel: ViewModelAccount by viewModels()
    lateinit var binding: FragmentSelectAccountBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSelectAccountBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.buttonSearch.setOnClickListener {

            viewModel.findAccount(binding.textViewFindAccount.text.toString().toInt())
                .observe(viewLifecycleOwner) {
                    binding.textViewAccountType.text = it.accountType
                    binding.textViewInventory.text = it.Inventory.toString()
                }
        }

    }

}