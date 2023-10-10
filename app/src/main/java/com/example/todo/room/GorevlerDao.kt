package com.example.todo.room

import androidx.room.*
import com.example.todo.data.entity.Gorevler

@Dao
interface GorevlerDao {
    @Query("SELECT * FROM gorevler")
    suspend fun gorevleriYukle() : List<Gorevler>

    @Insert
    suspend fun gorevKaydet(kisi:Gorevler)

    @Update
    suspend fun gorevGuncelle(kisi: Gorevler)

    @Delete
    suspend fun gorevSil(kisi:Gorevler)

    @Query("SELECT * FROM gorevler WHERE gorev_ad LIKE '%' || :aramaKelimesi || '%' OR gorev_detay LIKE '%' || :aramaKelimesi || '%'")
    suspend fun gorevAra(aramaKelimesi: String): List<Gorevler>

}