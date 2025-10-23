package com.example.tp5

import android.os.Bundle
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val lista = mutableListOf<Animal>(
            Animal("Chat", "Mamal", R.drawable.cat),
            Animal("Chien", "Mamal", R.drawable.dog),
            Animal("Peroquet", "Oiseau", R.drawable.parrot),
            Animal("Serpent", "Reptile", R.drawable.snake)
        )

        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val radioGrid = findViewById<RadioButton>(R.id.affichageGrille)
        val radioLinear = findViewById<RadioButton>(R.id.affichageLineaire)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        radioLinear.isChecked = true

        val adapter = AdapterAnimaux(lista)
        recyclerView.adapter = adapter

        radioGroup.setOnCheckedChangeListener {_, id ->
            if(id == R.id.affichageGrille){
                recyclerView.layoutManager = GridLayoutManager(this, 2)
            }else if(id == R.id.affichageLineaire){
                recyclerView.layoutManager = LinearLayoutManager(this)
            }
        }
    }
}