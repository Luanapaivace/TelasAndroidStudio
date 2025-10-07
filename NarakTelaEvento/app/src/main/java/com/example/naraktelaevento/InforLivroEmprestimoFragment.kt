package com.example.naraktelaevento

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar

class InforLivroEmprestimoFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_infor_livro_emprestimo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbar = view.findViewById<MaterialToolbar>(R.id.toolbarInfoLivro)
        toolbar.setNavigationOnClickListener {
            parentFragmentManager.popBackStack()
        }

        val titulo = arguments?.getString("titulo")
        val autor = arguments?.getString("autor")
        val descricao = arguments?.getString("descricao")
        val ano = arguments?.getString("ano")
        val capa = arguments?.getInt("capa") ?: 0
        val quantidadeTotal = arguments?.getInt("quantidadeTotal") ?: 0
        val quantidadeDisponivel = arguments?.getInt("quantidadeDisponivel") ?: 0


        view.findViewById<TextView>(R.id.textViewTitulo).text = titulo
        view.findViewById<TextView>(R.id.textViewAutor).text = autor
        view.findViewById<TextView>(R.id.textViewAno).text = ano
        view.findViewById<TextView>(R.id.textViewDescricao).text = descricao
        view.findViewById<ImageView>(R.id.imageCapa).setImageResource(capa)
        view.findViewById<TextView>(R.id.textViewQuantidadeTotal).text =
            "Quantidade Total: $quantidadeTotal"
        view.findViewById<TextView>(R.id.textViewQuantidadeDisponivel).text =
            "Quantidade Disponível: $quantidadeDisponivel"

        val listaUsuarios = when (titulo) {
            "Ciências da Computação" -> listOf(
                Usuario("André", "01/09/2025", "10/09/2025"),
                Usuario("Sofia", "02/09/2025", "18/09/2025")
            )
            "Computação em Nuvem" -> listOf(
                Usuario("Beatriz", "05/09/2025", "13/09/2025"),
                Usuario("Rafael", "07/09/2025", "17/09/2025")
            )
            else -> listOf(
                Usuario("Laura", "03/09/2025", "11/09/2025"),
                Usuario("Carlos", "04/09/2025", "12/09/2025"),
                Usuario("Maria", "05/09/2025", "15/09/2025")
            )
        }

        val recyclerUsuarios = view.findViewById<RecyclerView>(R.id.recyclerUsuarios)
        recyclerUsuarios.layoutManager = LinearLayoutManager(requireContext())
        recyclerUsuarios.adapter = UsuarioAdapter(listaUsuarios)
        recyclerUsuarios.post {
            val adapter = recyclerUsuarios.adapter
            if (adapter != null) {
                var totalHeight = 0
                for (position in 0 until adapter.itemCount) {
                    val holder = adapter.createViewHolder(recyclerUsuarios, adapter.getItemViewType(position))
                    adapter.onBindViewHolder(holder, position)
                    holder.itemView.measure(
                        View.MeasureSpec.makeMeasureSpec(recyclerUsuarios.width, View.MeasureSpec.EXACTLY),
                        View.MeasureSpec.UNSPECIFIED
                    )
                    totalHeight += holder.itemView.measuredHeight
                }
                val params = recyclerUsuarios.layoutParams
                params.height = totalHeight
                recyclerUsuarios.layoutParams = params
            }
        }
    }
}
