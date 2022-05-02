package com.example.myapplication

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.myapplication.databinding.FragmentShowInformationBinding



class ShowInformationFragment : Fragment() {
    lateinit var binding: FragmentShowInformationBinding
    val profileViewModel: ProfileViewModel by viewModels()
    lateinit var sp: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        sp = this.requireActivity().getSharedPreferences("userInformation", Context.MODE_PRIVATE)
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentShowInformationBinding.inflate(layoutInflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        getInformationAndSet()
        binding.editButton.setOnClickListener(::goToEdit)

    }

    private fun goToEdit(view: View?) {
        findNavController().navigate(R.id.action_showInformationFragment_to_profileFragment)
    }

    private fun getInformationAndSet() {

        binding.tvName.text = sp.getString("name","")
        binding.tvFather.text = sp.getString("fathersName","")
        binding.tvPhone.text = sp.getString("phone","")
        binding.tvPost.text = sp.getString("postCode","")

    }


}