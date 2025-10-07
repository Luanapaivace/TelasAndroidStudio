package com.example.naraktelaevento

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UsuarioAdapter(
    private val listaUsuarios: List<Usuario>
) : RecyclerView.Adapter<UsuarioAdapter.UsuarioViewHolder>() {

    inner class UsuarioViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val avatar: TextView = view.findViewById(R.id.avatarUsuario)
        val nome: TextView = view.findViewById(R.id.nomeUsuarioItem)
        val btnExpand: ImageView = view.findViewById(R.id.btnExpandUsuario)
        val detalhesLayout: LinearLayout = view.findViewById(R.id.layoutDetalhesUsuario)
        val dataEmprestimo: TextView = view.findViewById(R.id.dataEmprestimo)
        val dataDevolucao: TextView = view.findViewById(R.id.dataDevolucao)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsuarioViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_usuario, parent, false)
        return UsuarioViewHolder(view)
    }

    override fun onBindViewHolder(holder: UsuarioViewHolder, position: Int) {
        val usuario = listaUsuarios[position]
        holder.avatar.text = usuario.nome.first().toString().uppercase()
        holder.nome.text = usuario.nome
        holder.dataEmprestimo.text = "Data do Empréstimo: ${usuario.dataEmprestimo}"
        holder.dataDevolucao.text = "Data da Devolução: ${usuario.dataDevolucao}"

        holder.detalhesLayout.visibility = View.GONE
        holder.btnExpand.setImageResource(R.drawable.ic_chevron_right)

        holder.btnExpand.setOnClickListener {
            if (holder.detalhesLayout.visibility == View.GONE) {
                holder.detalhesLayout.visibility = View.VISIBLE
                holder.btnExpand.setImageResource(R.drawable.ic_expand_more)
            } else {
                holder.detalhesLayout.visibility = View.GONE
                holder.btnExpand.setImageResource(R.drawable.ic_chevron_right)
            }
        }
    }

    override fun getItemCount(): Int = listaUsuarios.size
}
