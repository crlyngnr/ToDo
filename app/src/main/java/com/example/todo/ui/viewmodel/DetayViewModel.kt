package com.example.todo.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.todo.data.repo.GorevlerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetayViewModel @Inject constructor(var gRepo : GorevlerRepository) : ViewModel() {
    fun gorevGuncelle(gorev_id : Int,gorev_ad : String,  gorev_detay: String, gorev_tarih: String, gorev_saat : String){
        CoroutineScope(Dispatchers.Main).launch {
            gRepo.gorevGuncelle(gorev_id,gorev_ad,gorev_detay,gorev_tarih,gorev_saat)
        }
    }
}