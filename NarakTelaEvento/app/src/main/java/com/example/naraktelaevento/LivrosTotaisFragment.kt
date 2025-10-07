package com.example.naraktelaevento

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LivrosTotaisFragment : Fragment() {

    private val CONTAINER_ID = R.id.container
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: LivroAdapter
    private lateinit var listaOriginal: List<Livro>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_livros_totais, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbar = view.findViewById<Toolbar>(R.id.toolbarLivrosTotais)
        toolbar.setNavigationOnClickListener {
            parentFragmentManager.popBackStack()
        }

        val searchView = view.findViewById<SearchView>(R.id.searchViewLivros)

        recyclerView = view.findViewById(R.id.recyclerLivrosTotais)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        listaOriginal = listOf(
            Livro(
                "Ciências da Computação",
                "Nell Dale / John Lewis",
                R.drawable.capa_ciencia_computacao,
                "Descrição completa do livro de Ciência da Computação...",
                "2018",
                quantidadeTotal = 5,
                quantidadeDisponivel = 3
            ),
            Livro(
                "Matemática Discreta",
                "Clifford Sten",
                R.drawable.capa_matematica_discreta,
                "Descrição completa do livro de Matemática Discreta...",
                "2015",
                quantidadeTotal = 4,
                quantidadeDisponivel = 1
            ),
            Livro(
                "Computação em Nuvem",
                "Thomas Erl",
                R.drawable.capa_computacao_nuvem,
                "Descrição completa do livro de Computação em Nuvem...",
                "2020",
                quantidadeTotal = 3,
                quantidadeDisponivel = 1
            )
        )

        adapter = LivroAdapter(listaOriginal) { livro ->
            abrirDetalhesLivro(livro)
        }
        recyclerView.adapter = adapter

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val texto = newText ?: ""
                filtrarLista(texto)
                return true
            }
        })
    }

    private fun filtrarLista(query: String) {
        if (query.isEmpty()) {
            adapter.updateList(listaOriginal)
            return
        }

        val listaFiltrada = listaOriginal.filter { livro ->
            livro.titulo.contains(query, ignoreCase = true)
        }

        adapter.updateList(listaFiltrada)
    }

    private fun abrirDetalhesLivro(livro: Livro) {
        val fragment = InforLivroEmprestimoFragment()
        val bundle = Bundle()
        bundle.putString("titulo", livro.titulo)
        bundle.putString("autor", livro.autor)
        bundle.putString("descricao", livro.descricao)
        bundle.putString("ano", livro.ano)
        bundle.putInt("capa", livro.capaResId)
        bundle.putInt("quantidadeTotal", livro.quantidadeTotal)
        bundle.putInt("quantidadeDisponivel", livro.quantidadeDisponivel)

        fragment.arguments = bundle

        parentFragmentManager.beginTransaction()
            .replace(CONTAINER_ID, fragment)
            .addToBackStack("info_livro")
            .commit()
    }
}
