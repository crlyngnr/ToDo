package com.example.todo.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.data.entity.Gorevler
import com.example.todo.databinding.DetayListBinding
import com.example.todo.ui.fragment.AnasayfaFragmentDirections
import com.example.todo.ui.viewmodel.AnasayfaViewModel
import com.example.todo.utils.gecisYap
import com.google.android.material.snackbar.Snackbar

class GorevlerAdapter(var mContext : Context,var gorevlerListesi : List<Gorevler>, var viewModel: AnasayfaViewModel)
    : RecyclerView.Adapter<GorevlerAdapter.CardViewHolder>(){

        inner class CardViewHolder(var tasarim : DetayListBinding) : RecyclerView.ViewHolder(tasarim.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val binding : DetayListBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext),
            R.layout.detay_list,parent,false)
        return CardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val gorev = gorevlerListesi.get(position)
        val g = holder.tasarim
        g.gorevNesnesi = gorev

        g.cardViewGorev.setOnClickListener {
            val gecis = AnasayfaFragmentDirections.detayGecis(gorev = gorev)
            Navigation.gecisYap(it,gecis)
        }
        g.imageViewSil.setOnClickListener{
            Snackbar.make(it,"${gorev.gorev_ad} silinsin mi ?",Snackbar.LENGTH_SHORT).setAction("EVET"){
                viewModel.gorevSil(gorev.gorev_id)
            }.show()
        }

    }
    override fun getItemCount(): Int {
        return gorevlerListesi.size
    }
}