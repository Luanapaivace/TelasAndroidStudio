package com.example.naraktelaevento

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager

class MainActivity : AppCompatActivity() {

    lateinit var btn1: Button
    lateinit var btn2: Button
    lateinit var btn3: Button
    lateinit var fm: FragmentManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        fm = supportFragmentManager

        btn1 = findViewById(R.id.buttonFrag1)
        btn2 = findViewById(R.id.buttonFrag2)
        btn3 = findViewById(R.id.buttonFrag3)

        fm.beginTransaction()
            .replace(R.id.container, TelaHome())
            .commit()
    }

    override fun onStart() {
        super.onStart()

        btn1.setOnClickListener {
            fm.beginTransaction()
                .replace(R.id.container, LivrosEmprestimosFragment())
                .addToBackStack("home")
                .commit()
        }

        btn2.setOnClickListener {
            fm.beginTransaction()
                .replace(R.id.container, LivrosTotaisFragment())
                .addToBackStack("evento")
                .commit()
        }

        btn3.setOnClickListener {
            fm.beginTransaction()
                .replace(R.id.container, TelaPerfilAdm())
                .addToBackStack("perfil")
                .commit()
        }
    }
}
