package com.example.naraktelaevento

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class LivroAdapter(
    private var listaLivros: List<Livro>,
    private val onClick: (Livro) -> Unit
) : RecyclerView.Adapter<LivroAdapter.LivroViewHolder>() {

    inner class LivroViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val capa: ImageView = view.findViewById(R.id.imgCapaLivro)
        val titulo: TextView = view.findViewById(R.id.txtNomeLivro)
        val autor: TextView = view.findViewById(R.id.txtAutorLivro)
        val card: CardView = view.findViewById(R.id.cardLivro)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LivroViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_livro, parent, false)
        return LivroViewHolder(view)
    }

    override fun onBindViewHolder(holder: LivroViewHolder, position: Int) {
        val livro = listaLivros[position]
        holder.capa.setImageResource(livro.capaResId)
        holder.titulo.text = livro.titulo
        holder.autor.text = livro.autor
        holder.card.setOnClickListener { onClick(livro) }
    }

    override fun getItemCount(): Int = listaLivros.size

    fun updateList(novaLista: List<Livro>) {
        listaLivros = novaLista
        notifyDataSetChanged()
    }
}
