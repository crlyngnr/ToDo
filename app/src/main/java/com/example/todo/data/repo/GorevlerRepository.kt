package com.example.todo.data.repo

import com.example.todo.data.datasource.GorevlerDataSource
import com.example.todo.data.entity.Gorevler

class GorevlerRepository(var gds : GorevlerDataSource) {
    suspend fun gorevKaydet(gorev_ad : String,  gorev_detay: String, gorev_tarih: String, gorev_saat : String) = gds.gorevKaydet(gorev_ad,gorev_detay,gorev_tarih,gorev_saat)
    suspend fun gorevGuncelle(gorev_id: Int,gorev_ad : String,  gorev_detay: String, gorev_tarih: String, gorev_saat : String) = gds.gorevGuncelle(gorev_id,gorev_ad,gorev_detay,gorev_tarih,gorev_saat)
    suspend fun gorevSil(gorev_id : Int) = gds.gorevSil(gorev_id)
    suspend fun gorevleriYukle() : List<Gorevler> =gds.gorevleriYukle()
    suspend fun gorevAra(aramaKelimesi : String): List<Gorevler> = gds.gorevAra(aramaKelimesi)
}