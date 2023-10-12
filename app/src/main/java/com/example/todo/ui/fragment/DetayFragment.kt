package com.example.todo.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import com.example.todo.R
import com.example.todo.databinding.FragmentDetayBinding
import com.example.todo.ui.viewmodel.DetayViewModel
import com.example.todo.utils.gecisYap
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class DetayFragment : Fragment() {
    private lateinit var binding: FragmentDetayBinding
    private lateinit var viewModel: DetayViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_detay, container, false)
        binding.gorevDetayFragment = this
        binding.gorevDetayTextBaslik = "Görev Detay"
        val bundle : DetayFragmentArgs by navArgs()
        val gelenGorev = bundle.gorev
        binding.gorevNesnesi = gelenGorev
        binding.appCompatImageButton2.setOnClickListener {
            val dp = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Tarih Seç")
                .build()

            dp.addOnPositiveButtonClickListener { selectedDate ->
                val calendar = Calendar.getInstance()
                calendar.timeInMillis = selectedDate
                val df = SimpleDateFormat("d MMM yyyy EEE", Locale.getDefault())
                val formattedDate = df.format(calendar.time)
                binding.textInputLayoutTarih.setText(formattedDate)
            }

            dp.show(requireActivity().supportFragmentManager, "Tarih Seç")
        }
        binding.imageButton2.setOnClickListener {
            val tp = MaterialTimePicker.Builder()
                .setTitleText("Saat Seç")
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .build()

            tp.addOnPositiveButtonClickListener {
                val selectedHour = tp.hour
                val selectedMinute = tp.minute

                val formattedTime = String.format("%02d:%02d", selectedHour, selectedMinute)
                binding.textInputLayoutSaat.setText(formattedTime)
            }

            tp.show(requireActivity().supportFragmentManager, "Saat Seç")
        }
        return binding.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tempViewModel : DetayViewModel by viewModels()
        viewModel = tempViewModel
    }
    fun btnGuncelle(gorev_id : Int,gorev_ad : String,  gorev_detay: String, gorev_tarih: String, gorev_saat : String){
        if (gorev_ad.isNullOrEmpty() || gorev_detay.isNullOrEmpty() || gorev_tarih.isNullOrEmpty() || gorev_saat.isNullOrEmpty()) {
            // Eksik veri var, kullanıcıyı uyarı mesajıyla bilgilendirin.
            Toast.makeText(requireContext(), "Lütfen tüm bilgileri giriniz", Toast.LENGTH_SHORT).show()
            return
        }
        viewModel.gorevGuncelle(gorev_id,gorev_ad,gorev_detay,gorev_tarih,gorev_saat)
        findNavController().navigate(R.id.detayToAnasayfa)
    }
    fun btnBack(){
        findNavController().navigate(R.id.detayToAnasayfa)
    }
}