package com.example.todo.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todo.data.entity.Gorevler
import com.example.todo.data.repo.GorevlerRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AnasayfaViewModel @Inject constructor(var gRepo : GorevlerRepository): ViewModel() {
    var gorevlerListesi = MutableLiveData<List<Gorevler>>()

    init {  //AnasayfaViewModel oluşturulduğu anda çalış anlamına geliyor
        gorevleriYukle()
    }

    fun gorevSil(gorev_id : Int){
        CoroutineScope(Dispatchers.Main).launch {
            gRepo.gorevSil(gorev_id)
            gorevleriYukle()
        }
    }
    fun gorevleriYukle(){
        CoroutineScope(Dispatchers.Main).launch {
            gorevlerListesi.value = gRepo.gorevleriYukle()
        }
    }
    fun gorevAra(aramaKelimesi : String) {
        CoroutineScope(Dispatchers.Main).launch {
            gorevlerListesi.value = gRepo.gorevAra(aramaKelimesi)
        }
    }
}