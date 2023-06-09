package com.example.pasienrumahsakit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.pasienrumahsakit.data.User
import com.example.pasienrumahsakit.data.userViewModel
import com.example.pasienrumahsakit.databinding.FragmentAddBinding


class AddFragment : Fragment() {
    private var _binding: FragmentAddBinding? = null
    private val binding get()= _binding!!
    private lateinit var mUserViewModel: userViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddBinding.inflate(inflater,container,false)

        val view = binding.root

        mUserViewModel = ViewModelProvider(this).get(userViewModel::class.java)
        binding.btnSimpan.setOnClickListener {
            insertDataToDatabase()
            Toast.makeText(context, "Data Berhasil Disimpan", Toast.LENGTH_SHORT).show()
            this.findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }
            return view
    }
    private fun insertDataToDatabase(){
        val noKTP = binding.etNoKTP.text.toString()
        val nama = binding.etNama.text.toString()
        val alamat = binding.etAlamat.text.toString()
        val noHP = Integer.parseInt(binding.etNoHP.text.toString())

        val user = User(0, noKTP, nama, alamat, noHP)
        mUserViewModel.addUser(user)

//        binding.btnSimpan.setOnClickListener {

//        }
    }

}