package com.example.myapplication

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.INVISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.example.myapplication.dataBase.UserAccount
import com.example.myapplication.databinding.FragmentCreateAccountBinding
import com.example.myapplication.viewModel.ViewModelAccount

class CreateAccountFragment : Fragment() {
    lateinit var binding: FragmentCreateAccountBinding
    val viewModel: ViewModelAccount by viewModels()
    lateinit var sp: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sp = this.requireActivity().getSharedPreferences("userInformation", Context.MODE_PRIVATE)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateAccountBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val numberOfAccounts = sp.getInt("number", 0)
        var x = numberOfAccounts

        binding.buttonSet.setOnClickListener {
            if (x == 0) {
                binding.buttonSet.visibility = INVISIBLE
            } else {
                var acc = UserAccount(
                    0,
                    binding.editTextAccountNum.text.toString().toInt(),
                    binding.editTextAccountType.text.toString(),
                    binding.editTextTextMojodi.text.toString().toInt()
                )
                viewModel.addToREP(acc)
                Toast.makeText(context, "Account created", Toast.LENGTH_SHORT).show()
                x--
                makeFildsEmpty()
            }
        }


    }

    private fun makeFildsEmpty() {
        binding.editTextAccountNum.setText("")
        binding.editTextAccountType.setText("")
        binding.editTextTextMojodi.setText("")
    }

}