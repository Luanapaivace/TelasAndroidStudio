package com.example.naraktelaevento

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.appcompat.widget.PopupMenu
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment

class TelaEvento : Fragment() {

    private val CONTAINER_ID = R.id.container

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tela_evento, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbar = view.findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbarEvento)
        toolbar.setNavigationOnClickListener {
            parentFragmentManager.popBackStack()
        }

        val cardAdicionar = view.findViewById<CardView>(R.id.cardAdicionarEvento)
        cardAdicionar.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(CONTAINER_ID, AdicionarEventoFragment())
                .addToBackStack("adicionar_evento")
                .commit()
        }

        val btnAdd = view.findViewById<ImageButton>(R.id.btnAdd)
        btnAdd.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(CONTAINER_ID, AdicionarEventoFragment())
                .addToBackStack("adicionar_evento")
                .commit()
        }

        val cardEvento = view.findViewById<CardView>(R.id.cardEvento)
        cardEvento.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(CONTAINER_ID, VisualizarEventoFragment())
                .addToBackStack("visualizar_evento")
                .commit()
        }

        val btnMenu = view.findViewById<ImageButton>(R.id.menuEvento)
        btnMenu.setOnClickListener { v ->
            val popup = PopupMenu(requireContext(), v)
            popup.menuInflater.inflate(R.menu.menu_evento, popup.menu)
            popup.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.menu_editar -> {
                        parentFragmentManager.beginTransaction()
                            .replace(CONTAINER_ID, EditarEventoFragment())
                            .addToBackStack("editar_evento")
                            .commit()
                        true
                    }
                    R.id.menu_excluir -> {
                        parentFragmentManager.beginTransaction()
                            .replace(CONTAINER_ID, ExcluirEventoFragment())
                            .addToBackStack("excluir_evento")
                            .commit()
                        true
                    }
                    else -> false
                }
            }
            popup.show()
        }
    }
}
