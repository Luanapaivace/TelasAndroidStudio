package com.example.naraktelaevento

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.MaterialToolbar

class TelaPerfilAdm : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tela_perfil_adm, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbar = view.findViewById<MaterialToolbar>(R.id.toolbar)
        toolbar.setNavigationOnClickListener {
            parentFragmentManager.popBackStack()
        }

        val nomeAdm = "Thiago Narak"
        val emailAdm = "narak1234@admin.com"
        val idAdm = "000001"

        view.findViewById<TextView>(R.id.avatar).text = nomeAdm.firstOrNull()?.toString() ?: "?"
        view.findViewById<TextView>(R.id.nomeUsuario).text = nomeAdm
        view.findViewById<TextView>(R.id.idUsuario).text = idAdm

        view.findViewById<TextView>(R.id.nomeUsuarioDetalhe).text = "Nome: $nomeAdm"
        view.findViewById<TextView>(R.id.idUsuarioDetalhe).text = "ID: $idAdm"
        view.findViewById<TextView>(R.id.emailUsuario).text = emailAdm

    }
}
