package com.example.naraktelaevento

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.MaterialToolbar

class TelaPerfilUsuario : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tela_perfil_usuario, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbar = view.findViewById<MaterialToolbar>(R.id.toolbar)
        toolbar.setNavigationOnClickListener {
            parentFragmentManager.popBackStack()
        }

        val args = requireArguments()
        val nomeUsuario = args.getString("nome", "Usuario")
        val emailUsuario = args.getString("email", "email@exemplo.com")
        val idUsuario = args.getString("id", "000000")
        val quantidadeEmprestimos = args.getInt("quantidadeEmprestimos", 0)

        view.findViewById<TextView>(R.id.avatar).text = nomeUsuario.firstOrNull()?.toString() ?: "?"
        view.findViewById<TextView>(R.id.nomeUsuario).text = nomeUsuario
        view.findViewById<TextView>(R.id.idUsuario).text = idUsuario

        view.findViewById<TextView>(R.id.nomeUsuarioDetalhe).text = "Nome: $nomeUsuario"
        view.findViewById<TextView>(R.id.idUsuarioDetalhe).text = "ID: $idUsuario"
        view.findViewById<TextView>(R.id.quantidadeEmprestimos).text =
            "Quantidade de Empréstimos Total: $quantidadeEmprestimos"
        view.findViewById<TextView>(R.id.emailUsuario).text = emailUsuario
    }
}
