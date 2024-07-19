package com.sozge.yemektarifleri.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.sozge.yemektarifleri.databinding.RecyclerRowBinding
import com.sozge.yemektarifleri.model.Tarif
import com.sozge.yemektarifleri.view.ListeFragmentDirections

class TarifAdapter(val tarifList: List<Tarif>) : RecyclerView.Adapter<TarifAdapter.TarifHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TarifHolder {
        val recyclerRowBinding = RecyclerRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TarifHolder(recyclerRowBinding)
    }

    override fun onBindViewHolder(holder: TarifHolder, position: Int) {
        holder.binding.recyclerViewTextView.text = tarifList[position].isim
        holder.itemView.setOnClickListener {
            val action = ListeFragmentDirections.actionListeFragmentToTarifFragment(bilgi = "eski", id = tarifList[position].id)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return tarifList.size
    }

    class TarifHolder(val binding: RecyclerRowBinding) : RecyclerView.ViewHolder(binding.root) {
    }
}