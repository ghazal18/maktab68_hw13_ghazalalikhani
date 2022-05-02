package com.example.myapplication.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.myapplication.ProfileViewModel
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentProfileBinding


class ProfileFragment : Fragment() {
    lateinit var binding: FragmentProfileBinding
    lateinit var profileViewModel: ProfileViewModel
    lateinit var sp: SharedPreferences
    lateinit var editor: SharedPreferences.Editor


    override fun onCreate(savedInstanceState: Bundle?) {
        sp = this.requireActivity().getSharedPreferences("userInformation", Context.MODE_PRIVATE)
        editor = sp.edit()
        profileViewModel = ViewModelProvider(this)[ProfileViewModel::class.java]
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setInformationAtFirst()
        binding.setInformation.setOnClickListener(::setButton)
        observers()


    }

    private fun observers() {
        var nameObserver = Observer<String> {
            Toast.makeText(activity, "اطلاعات ثبت شد!", Toast.LENGTH_SHORT).show()
            profileViewModel.sandInformationToEditor()
            findNavController().navigate(R.id.action_profileFragment_to_showInformationFragment)

        }
        var fatherObserver = Observer<String> {
            profileViewModel.sandInformationToEditor()
        }
        var phoneObserver = Observer<String> {
            profileViewModel.sandInformationToEditor()
        }
        var postCodeObserver = Observer<String> {
            profileViewModel.sandInformationToEditor()
        }
        profileViewModel.nameLiveData.observe(viewLifecycleOwner, nameObserver)
        profileViewModel.postCodeLiveData.observe(viewLifecycleOwner, postCodeObserver)
        profileViewModel.phoneNumberLiveData.observe(viewLifecycleOwner, phoneObserver)
        profileViewModel.fatherNameLiveData.observe(viewLifecycleOwner, fatherObserver)

    }

    private fun setInformationAtFirst() {
        binding.editTextName.setText(sp.getString("name", ""))
        binding.editTextFather.setText(sp.getString("fathersName", ""))
        binding.editTextPhone.setText(sp.getString("phone", ""))
        binding.editTextPostID.setText(sp.getString("postCode", ""))
    }

    fun setErrors(): Boolean {
        if (binding.editTextName.text.isNullOrBlank()) {
            binding.editTextName.error = "please enter name"
            return false
        }
        if (binding.editTextPhone.text.isNullOrBlank()) {
            binding.editTextPhone.error = "please enter phone"
            return false
        }
        if (binding.editTextFather.text.isNullOrBlank()) {
            binding.editTextFather.error = "please enter fathers name"
            return false
        }
        if (binding.editTextPostID.text.isNullOrBlank()) {
            binding.editTextPostID.error = "please enter post ID"
            return false
        }
        if (binding.editTextAccount.text.isNullOrBlank()) {
            binding.editTextAccount.error = "please enter post ID"
            return false
        }
        return true
    }

    private fun setButton(view: View?) {
        if (setErrors()) {

            profileViewModel.nameLiveData.value = binding.editTextName.text.toString()
            profileViewModel.fatherNameLiveData.value = binding.editTextFather.text.toString()
            profileViewModel.phoneNumberLiveData.value = binding.editTextPhone.text.toString()
            profileViewModel.postCodeLiveData.value = binding.editTextPostID.text.toString()
            editor.putInt("number", binding.editTextAccount.text.toString().toInt())
            editor.apply()


        }
    }


}