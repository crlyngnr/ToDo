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
import androidx.navigation.fragment.findNavController
import com.example.todo.R
import com.example.todo.databinding.FragmentKayitBinding
import com.example.todo.ui.viewmodel.KayitViewModel
import com.google.android.material.datepicker.MaterialCalendar
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*
import java.util.logging.SimpleFormatter


@AndroidEntryPoint
class KayitFragment : Fragment() {
    private lateinit var binding: FragmentKayitBinding
    private lateinit var viewModel: KayitViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_kayit,container,false)
        binding.kayitFragment=this
        binding.gorevEkleTextBaslik= "Görev Ekle"

        binding.appCompatImageButton2.setOnClickListener {
            val dp = MaterialDatePicker.Builder.datePicker()
                .setTitleText("Tarih Seç")
                .build()

            dp.addOnPositiveButtonClickListener { selectedDate ->
                val calendar = Calendar.getInstance()
                calendar.timeInMillis = selectedDate
                val df = SimpleDateFormat("d MMM yyyy EEE", Locale.getDefault())
                val formattedDate = df.format(calendar.time)
                binding.textInputLayoutZaman.setText(formattedDate)
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
        val tempViewModel :  KayitViewModel by viewModels()
        viewModel = tempViewModel
    }
    fun btnKaydet(gorev_ad : String,  gorev_detay: String, gorev_tarih: String, gorev_saat : String){
        if (gorev_ad.isNullOrEmpty() || gorev_detay.isNullOrEmpty() || gorev_tarih.isNullOrEmpty() || gorev_saat.isNullOrEmpty()) {
            // Eksik veri var, kullanıcıyı uyarı mesajıyla bilgilendirin.
            Toast.makeText(requireContext(), "Lütfen tüm bilgileri giriniz", Toast.LENGTH_SHORT).show()
            return
        }
        viewModel.gorevKaydet(gorev_ad,gorev_detay,gorev_tarih,gorev_saat)
        findNavController().navigate(R.id.kayitToAnasayfa)
    }
}