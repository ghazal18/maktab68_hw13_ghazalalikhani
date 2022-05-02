package com.example.myapplication.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.example.myapplication.R
import com.example.myapplication.dataBase.UserAccountAdapter
import com.example.myapplication.databinding.FragmentShowAccountBinding
import com.example.myapplication.viewModel.ViewModelAccount


class ShowAccountFragment : Fragment() {
    private val viewModel: ViewModelAccount by viewModels()
    lateinit var binding: FragmentShowAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_show_account, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = UserAccountAdapter()
        viewModel.getAllAccounts().observe(viewLifecycleOwner) {
            if (it != null) {
                binding.accountRv.adapter = adapter
                adapter.submitList(it)
            }
        }
    }


}