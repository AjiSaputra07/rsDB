package com.example.pasienrumahsakit

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.pasienrumahsakit.data.User
import com.example.pasienrumahsakit.data.userViewModel
import com.example.pasienrumahsakit.databinding.FragmentUpdateBinding


class UpdateFragment : Fragment() {
    // Menambahkan variable arguments
    private val args by navArgs<UpdateFragmentArgs>()
    // Untuk memanggil user view model
    private lateinit var mUserViewModel: userViewModel
    private var _binding: FragmentUpdateBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUpdateBinding.inflate(inflater,container,false)
        val view = binding.root

        mUserViewModel = ViewModelProvider(this).get(userViewModel::class.java)

        binding.etNoKTP.setText(args.currentUser.noKTP.toString())
        binding.etNama.setText(args.currentUser.nama)
        binding.etAlamat.setText(args.currentUser.alamat)
        binding.etNoHP.setText(args.currentUser.noHP.toString())

        // Untuk update data pada mysql
        binding.btnUbah.setOnClickListener {
            updateUser(args.currentUser.id)
        }

        // Untuk menghapus data pada mysql
        // Membuat alert dialog untuk menampilkan pilihan ya dan tidak menghapus
        binding.btnHapus.setOnClickListener {
            val id1 = args.currentUser.id
            val builder = AlertDialog.Builder(requireContext())
            builder.setMessage("Yakin Ingin Menghapus Data Ini?").setCancelable(false)
                .setPositiveButton("ya"){
                    dialog,id -> deleteUser(id1)
                }
                .setNegativeButton("tidak"){
                    dialog,id ->
                }
            val alert = builder.create()
            alert.show()
        }
        return view
    }
    // fun update data pada mysql
    private fun updateUser(id:Int){
        val noKTP = binding.etNoKTP.text.toString()
        val nama = binding.etNama.text.toString()
        val alamat = binding.etAlamat.text.toString()
        val noHP = Integer.parseInt(binding.etNoHP.text.toString())

        val user = User(args.currentUser.id,noKTP,nama,alamat,noHP)
        mUserViewModel.updateUser(user)
        findNavController().navigate(R.id.action_updateFragment_to_listFragment)
    }

    // fun untuk menghapus data pada mysqlite
    private fun deleteUser(id: Int){
        val noKTP = binding.etNoKTP.text.toString()
        val nama = binding.etNama.text.toString()
        val alamat = binding.etAlamat.text.toString()
        val noHP = Integer.parseInt(binding.etNoHP.text.toString())

        val user = User(args.currentUser.id,noKTP,nama,alamat,noHP)
        mUserViewModel.deleteUser(user)
        Toast.makeText(context,"Data Berhasil Dihapus",Toast.LENGTH_SHORT).show()
        findNavController().navigate(R.id.action_updateFragment_to_listFragment)
    }
}