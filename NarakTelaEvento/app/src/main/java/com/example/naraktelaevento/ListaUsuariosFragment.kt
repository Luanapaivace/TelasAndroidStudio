package com.example.naraktelaevento

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.appbar.MaterialToolbar

class ListaUsuariosFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_lista_usuarios, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toolbar = view.findViewById<MaterialToolbar>(R.id.toolbarUsuarios)
        toolbar.setNavigationOnClickListener {
            parentFragmentManager.popBackStack()
        }

        val recycler = view.findViewById<RecyclerView>(R.id.recyclerUsuarios)
        recycler.layoutManager = LinearLayoutManager(requireContext())

        val listaUsuarios = listOf(
            UsuarioDetalhes("Laura Rejane", "lrejane23@gmail.com", "1205468", 10),
            UsuarioDetalhes("Carlos Silva", "carlos.silva@gmail.com", "1205469", 5),
            UsuarioDetalhes("Maria Souza", "maria.souza@gmail.com", "1205470", 3),
            UsuarioDetalhes("André Pereira", "andre.pereira@gmail.com", "1205471", 7)
        )

        recycler.adapter = UsuarioListaAdapter(listaUsuarios) { usuario ->
            abrirPerfil(usuario)
        }
    }

    private fun abrirPerfil(usuario: UsuarioDetalhes) {
        val fragment = TelaPerfilUsuario()
        val bundle = Bundle()
        bundle.putString("nome", usuario.nome)
        bundle.putString("email", usuario.email)
        bundle.putString("id", usuario.id)
        bundle.putInt("quantidadeEmprestimos", usuario.quantidadeEmprestimos)
        fragment.arguments = bundle

        parentFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack("perfil_usuario")
            .commit()
    }
}
