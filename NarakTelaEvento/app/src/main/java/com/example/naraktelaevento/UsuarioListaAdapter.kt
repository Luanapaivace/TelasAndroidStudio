package com.example.naraktelaevento

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UsuarioListaAdapter(
    private val listaUsuarios: List<UsuarioDetalhes>,
    private val onClick: (UsuarioDetalhes) -> Unit
) : RecyclerView.Adapter<UsuarioListaAdapter.UsuarioViewHolder>() {

    inner class UsuarioViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val avatar: TextView = view.findViewById(R.id.avatarUsuario)
        val nome: TextView = view.findViewById(R.id.nomeUsuarioItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsuarioViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_usuario_lista, parent, false)
        return UsuarioViewHolder(view)
    }

    override fun onBindViewHolder(holder: UsuarioViewHolder, position: Int) {
        val usuario = listaUsuarios[position]
        holder.avatar.text = usuario.nome.firstOrNull()?.toString()?.uppercase() ?: "?"
        holder.nome.text = usuario.nome

        holder.itemView.setOnClickListener {
            onClick(usuario)
        }
    }

    override fun getItemCount(): Int = listaUsuarios.size
}
