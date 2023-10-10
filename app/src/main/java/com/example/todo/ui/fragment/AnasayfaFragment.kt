package com.example.todo.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todo.R
import com.example.todo.data.entity.Gorevler
import com.example.todo.databinding.FragmentAnasayfaBinding
import com.example.todo.ui.adapter.GorevlerAdapter
import com.example.todo.ui.viewmodel.AnasayfaViewModel
import com.example.todo.utils.gecisYap
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AnasayfaFragment : Fragment() {
    private lateinit var binding: FragmentAnasayfaBinding
    private lateinit var viewModel: AnasayfaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_anasayfa, container, false)
        binding.anasayfaFragment = this
        binding.anasayfaToolbarBaslik = "Görevler"
        viewModel.gorevlerListesi.observe(viewLifecycleOwner){
            val gorevlerAdapter  = GorevlerAdapter(requireContext(),it,viewModel)
            binding.gorevlerAdapter = gorevlerAdapter

        }
        binding.fab.setOnClickListener {
            Navigation.gecisYap(it,R.id.detayGecis)
        }
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    viewModel.gorevAra(newText)
                }
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    viewModel.gorevAra(query)
                }
                return true
            }
        })
        return binding.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : AnasayfaViewModel by viewModels()
        viewModel = tempViewModel
    }
    fun btnFab(it:View){
        Navigation.findNavController(it).navigate(R.id.kayitGecis)
    }

    override fun onResume() {
        super.onResume()
        viewModel.gorevleriYukle()
    }
}