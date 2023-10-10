package com.example.todo.data.datasource

import android.widget.Toast
import com.example.todo.data.entity.Gorevler
import com.example.todo.room.GorevlerDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GorevlerDataSource(var gDao : GorevlerDao) {
    suspend fun gorevleriYukle() : List<Gorevler> =
        withContext(Dispatchers.IO){
            return@withContext gDao.gorevleriYukle()
        }
    suspend fun gorevKaydet(gorev_ad : String,  gorev_detay: String, gorev_tarih: String, gorev_saat : String){
        if (gorev_ad.isNullOrEmpty() || gorev_detay.isNullOrEmpty() || gorev_tarih.isNullOrEmpty() || gorev_saat.isNullOrEmpty()) {
            // Eksik veri var, işlemi yapmadan çıkabilirsiniz.
            return
        }
        val yeniGorev = Gorevler(0,gorev_ad,gorev_detay,gorev_tarih,gorev_saat)
        gDao.gorevKaydet(yeniGorev)
    }
    suspend fun gorevGuncelle(gorev_id: Int,gorev_ad : String,  gorev_detay: String, gorev_tarih: String, gorev_saat : String){
        val guncelGorev = Gorevler(gorev_id,gorev_ad,gorev_detay,gorev_tarih,gorev_saat)
        gDao.gorevGuncelle(guncelGorev)
    }
    suspend fun gorevSil(gorev_id : Int){
        val silinenGorev = Gorevler(gorev_id,"","","","")
        gDao.gorevSil(silinenGorev)
    }
    suspend fun gorevAra(aramaKelimesi : String): List<Gorevler> =
        withContext(Dispatchers.IO){
            return@withContext gDao.gorevAra(aramaKelimesi)
        }
}