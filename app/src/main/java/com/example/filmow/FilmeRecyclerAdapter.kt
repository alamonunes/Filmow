package com.example.filmow

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.filme_layout.view.*

class FilmeRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private var filmes: List<Filme> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return FilmeViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.filme_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is FilmeViewHolder ->{
                holder.bind(filmes.get(position))
            }
        }
    }

    override fun getItemCount() : Int {
        return filmes.size
    }

    fun submitList(filme: List<Filme>){
        filmes = filme
    }

    fun update(){
        notifyDataSetChanged()
    }

    class FilmeViewHolder constructor(itemView: View): RecyclerView.ViewHolder(itemView){
        val filmeTitle: TextView = itemView.tvFilme
        val filmeAvaliacao: TextView = itemView.tvAvaliacao

        fun bind(filme: Filme){
            filmeTitle.setText(filme.titulo)
            filmeAvaliacao.setText(filme.avaliacao)

        }
    }

}