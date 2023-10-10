package com.example.todo.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull
import java.io.Serializable

@Entity(tableName = "gorevler")
class Gorevler(@PrimaryKey(autoGenerate = true)
               @ColumnInfo("gorev_id") @NotNull var gorev_id : Int,
               @ColumnInfo("gorev_ad")@NotNull  var gorev_ad:String,
               @ColumnInfo("gorev_detay")@NotNull  var gorev_detay : String,
               @ColumnInfo("gorev_tarih")@NotNull  var gorev_tarih: String,
               @ColumnInfo("gorev_saat") @NotNull var gorev_saat: String) : Serializable {
}